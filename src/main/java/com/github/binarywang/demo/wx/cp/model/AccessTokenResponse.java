package com.github.binarywang.demo.wx.cp.model;

import lombok.Data;

@Data
public class AccessTokenResponse {
    private String access_token;
    private int expires_in;

}
