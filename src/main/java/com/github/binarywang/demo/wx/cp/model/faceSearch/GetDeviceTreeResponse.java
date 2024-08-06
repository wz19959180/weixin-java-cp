package com.github.binarywang.demo.wx.cp.model.faceSearch;

import com.dahuatech.icc.oauth.http.IccResponse;
import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-15 15:16
 * @Description: 查询设备树返回
 */
@Data
public class GetDeviceTreeResponse extends IccResponse {
    private String  data;
}
