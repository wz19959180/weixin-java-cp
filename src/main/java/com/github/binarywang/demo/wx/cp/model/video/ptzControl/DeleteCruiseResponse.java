package com.github.binarywang.demo.wx.cp.model.video.ptzControl;

import com.dahuatech.icc.oauth.http.IccResponse;
import lombok.Data;

/**
 * program:java-sdk-demo
 *
 * @Author: 355079
 * @Date:2024-04-10 20:11
 * @Description: 删除巡航线接口返回
 */
@Data
public class DeleteCruiseResponse extends IccResponse {

    /** 结果描述*/
    private String desc;
}
