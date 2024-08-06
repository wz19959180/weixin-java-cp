package com.github.binarywang.demo.wx.cp.model.video.videoReplay;

import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-07 13:48
 * @Description: rtsp以时间形式回放录像请求参数
 */
@Data
public class PlaybackByTimeRequest {
    private Data data;

    @lombok.Data
    public static class Data{
        private String channelId;
        private String recordSource;
        private String startTime;
        private String endTime;
        private String streamType;
        private String recordType;
    }
}
