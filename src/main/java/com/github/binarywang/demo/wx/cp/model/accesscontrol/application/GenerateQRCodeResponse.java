package com.github.binarywang.demo.wx.cp.model.accesscontrol.application;

import com.dahuatech.icc.oauth.http.IccResponse;
import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-01 17:10
 * @Description: 获取业主二维码字符串接口返回
 */
@Data
public class GenerateQRCodeResponse extends IccResponse {

    /** 二维码对应的字符串 */
    private String data;

}
