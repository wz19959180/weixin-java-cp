package com.github.binarywang.demo.wx.cp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.binarywang.demo.wx.cp.config.annotation.RequiresAccessToken;
import com.github.binarywang.demo.wx.cp.controller.person.PersonDemo;
import com.github.binarywang.demo.wx.cp.mapper.PersonMapper;
import com.github.binarywang.demo.wx.cp.model.AccessTokenResponse;
import com.github.binarywang.demo.wx.cp.model.WeChatEvent;
import com.github.binarywang.demo.wx.cp.model.WechatUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WeChatService {

    private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";
    private static final String ACCESS_TOKEN_KEY = "WECHAT_ACCESS_TOKEN";
    @Value("${wechat.cp.corpId}")
    private String corpId;
    @Value("${wechat.cp.appConfigs[0].secret}")
    private String corpSecret;


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PersonDemo personDemo;

    @Autowired
    private PersonMapper personMapper;

    public String getAccessToken(String corpId, String corpSecret) {
        String accessToken = redisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
        if (accessToken == null) {
            String url = String.format(ACCESS_TOKEN_URL, corpId, corpSecret);
            AccessTokenResponse response = restTemplate.getForObject(url, AccessTokenResponse.class);
            if (response != null && response.getAccess_token() != null) {
                accessToken = response.getAccess_token();
                redisTemplate.opsForValue().set(ACCESS_TOKEN_KEY, accessToken, response.getExpires_in(), TimeUnit.SECONDS);
            } else {
                throw new RuntimeException("Failed to get access token from WeChat API");
            }
        }
        return accessToken;
    }

    public String getDepartmentList(String accessToken) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("access_token", accessToken);
        String forObject = restTemplate.getForObject(builder.toUriString(), String.class);
        log.info("getDepartmentList result"+forObject);
        return restTemplate.getForObject(builder.toUriString(), String.class);
    }


    public void judgeEvent(WeChatEvent weChatEvent) throws JsonProcessingException {
        log.info("当前待处理的微信回调消息:"+weChatEvent);
        if ("update_user".equals(weChatEvent.getChangeType())){
            updateUser(weChatEvent);

        }
        if ("create_user".equals(weChatEvent.getChangeType())){
            createUser(weChatEvent);
        }
        if ("delete_user".equals(weChatEvent.getChangeType())){
            deleteUser(weChatEvent);
        }
    }

    private void deleteUser(WeChatEvent weChatEvent) {
        //获取根据userid获取成员信息
        String infoById = getInfoById(weChatEvent.getUserId());
    }

    @RequiresAccessToken
    public String getInfoById(String userId){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get";
        String accessToken = getAccessToken(corpId, corpSecret);
        if (accessToken==null || "".equals(accessToken)){
            log.error("获取accessTOKEN失败");
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("access_token", accessToken)
            .queryParam("userid",userId);
        String forObject = restTemplate.getForObject(builder.toUriString(), String.class);
        log.info("根据id获取成员信息"+forObject);
        return forObject;
    }

    private void updateUser(WeChatEvent weChatEvent) throws JsonProcessingException {
        //获取根据userid获取成员信息
        String infoById = getInfoById(weChatEvent.getUserId());

        ObjectMapper objectMapper = new ObjectMapper();
        WechatUserInfo userInfo = objectMapper.readValue(infoById, WechatUserInfo.class);
        personDemo.updatePerson(userInfo);

    }

    private void createUser(WeChatEvent weChatEvent) throws JsonProcessingException {
        //获取根据userid获取成员信息
        String infoById = getInfoById(weChatEvent.getUserId());

        ObjectMapper objectMapper = new ObjectMapper();
        WechatUserInfo userInfo = objectMapper.readValue(infoById, WechatUserInfo.class);
        personDemo.addPerson(userInfo);

    }
}
