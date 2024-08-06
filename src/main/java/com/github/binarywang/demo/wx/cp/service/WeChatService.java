package com.github.binarywang.demo.wx.cp.service;

import com.github.binarywang.demo.wx.cp.model.AccessTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

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
}
