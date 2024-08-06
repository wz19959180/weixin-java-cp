package com.github.binarywang.demo.wx.cp.model.video.ptzControl;

import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-09 15:58
 * @Description: 预置点控制(添加/删除/定位)接口请求参数
 */
@Data
public class OperatePresetPointRequest {
    private Data data;
    @lombok.Data
    public static class Data{
        /** 视频通道编码*/
        private String channelId;
        /** 预置点编码*/
        private String presetPointCode;
        /** 预置点名称*/
        private String presetPointName;
        /** 操作类型：1=定位，2=添加，3=删除*/
        private String operateType;
    }
}
