package com.github.binarywang.demo.wx.cp.model.video.videoReplay;

import com.dahuatech.icc.oauth.http.IccResponse;
import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-07 15:29
 * @Description: HLS、RTMP录像回放（FLV不支持）接口返回结果
 */
@Data
public class HlsPlaybackResponse extends IccResponse {
    private Data data;
    @lombok.Data
    public static class Data{
        private String url;
    }
}
