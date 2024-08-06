package com.github.binarywang.demo.wx.cp.model.video.videoReplay;

import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-09 10:45
 * @Description: 录像下载请求参数
 */
@Data
public class VideoDownloadRequest {
    /** 通道编号*/
    private String channelId;
    /** 码流类型 1-主码流 2-辅码流*/
    private String subType;
    /** 开始时间*/
    private String startTime;
    /** 结束时间*/
    private String endTime;
    /** 录像回放类型 2-设备录像 3-中心录像*/
    private String videoType;
    /** 认证接口返回的access_token*/
    private String token;
    /** 录像类型 1-普通录像 2-报警录像*/
    private String recordType;
}
