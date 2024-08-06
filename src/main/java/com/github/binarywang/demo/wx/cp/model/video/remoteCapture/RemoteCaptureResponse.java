package com.github.binarywang.demo.wx.cp.model.video.remoteCapture;

import com.dahuatech.icc.oauth.http.IccResponse;
import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-12 11:27
 * @Description: 设备远程抓图返回参数
 */
@Data
public class RemoteCaptureResponse extends IccResponse {
    /** 描述详情 */
    private String desc;
    /** 抓图信息 */
    private String data;
}
