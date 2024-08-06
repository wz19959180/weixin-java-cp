package com.github.binarywang.demo.wx.cp.model.video.ptzControl;

import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-10 11:57
 * @Description: 获取云台巡航线接口请求参数
 */
@Data
public class GetCruisesRequest {

    private Data data;

    @lombok.Data
    public static class Data{
        /** 视频通道编码*/
        private String channelId;
    }
}
