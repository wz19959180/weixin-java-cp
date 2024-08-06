package com.github.binarywang.demo.wx.cp.config.aspect;

public class AccessTokenContext {
    private static final ThreadLocal<String> accessTokenHolder = new ThreadLocal<>();

    public static void setAccessToken(String accessToken) {
        accessTokenHolder.set(accessToken);
    }

    public static String getAccessToken() {
        return accessTokenHolder.get();
    }

    public static void clear() {
        accessTokenHolder.remove();
    }
}
