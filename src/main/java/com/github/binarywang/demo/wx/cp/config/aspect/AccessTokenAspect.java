package com.github.binarywang.demo.wx.cp.config.aspect;

import com.github.binarywang.demo.wx.cp.config.annotation.RequiresAccessToken;
import com.github.binarywang.demo.wx.cp.service.WeChatService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessTokenAspect {

    @Value("${wechat.cp.corpId}")
    private String corpId;
    @Value("${wechat.cp.appConfigs[0].secret}")
    private String corpSecret;

    @Autowired
    private WeChatService weChatService;


    @Around("@annotation(requiresAccessToken)")
    public Object addAccessToken(ProceedingJoinPoint joinPoint, RequiresAccessToken requiresAccessToken) throws Throwable {
        String accessToken = weChatService.getAccessToken(corpId, corpSecret);

//        // 将access_token作为最后一个参数传递给目标方法
//        Object[] newArgs = new Object[args.length + 1];
//        System.arraycopy(args, 0, newArgs, 0, args.length);
//        newArgs[args.length] = accessToken;
//
//        return joinPoint.proceed(newArgs);

        AccessTokenContext.setAccessToken(accessToken);
        try {
            return joinPoint.proceed();
        } finally {
            AccessTokenContext.clear();
        }
    }
}
