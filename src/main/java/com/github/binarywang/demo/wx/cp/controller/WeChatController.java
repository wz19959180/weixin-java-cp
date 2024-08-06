package com.github.binarywang.demo.wx.cp.controller;
import com.github.binarywang.demo.wx.cp.config.annotation.RequiresAccessToken;
import com.github.binarywang.demo.wx.cp.config.aspect.AccessTokenContext;
import com.github.binarywang.demo.wx.cp.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeChatController {

    @Value("${wechat.cp.corpId}")
    private String corpId;
    @Value("${wechat.cp.appConfigs[0].secret}")
    private String corpSecret;
    @Autowired
    private WeChatService weChatService;
    @RequiresAccessToken
    @GetMapping("/test")
    public String test() {
        String accessToken = AccessTokenContext.getAccessToken();
        System.out.println(accessToken);
        return "SUCCESS";
    }

    @GetMapping("/getAccessToken")
    public String getAccessToken() {
        return weChatService.getAccessToken(corpId, corpSecret);
    }

    @GetMapping("/getDepartmentList")
    @RequiresAccessToken
    public String getDepartmentList() {
        String accessToken = AccessTokenContext.getAccessToken();
        weChatService.getDepartmentList(accessToken);
        System.out.println(accessToken);
        return "SUCCESS";
    }
}
