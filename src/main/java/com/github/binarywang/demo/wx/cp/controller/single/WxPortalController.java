package com.github.binarywang.demo.wx.cp.controller.single;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.binarywang.demo.wx.cp.config.single.WxCpConfiguration;
import com.github.binarywang.demo.wx.cp.model.WeChatEvent;
import com.github.binarywang.demo.wx.cp.service.WeChatService;
import com.github.binarywang.demo.wx.cp.utils.JsonUtils;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizJsonMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wx/cp/portal/{agentId}")
@Slf4j
public class WxPortalController {


    @Value("${wechat.cp.corpId}")
    private String corpId;
    @Value("${wechat.cp.appConfigs[0].secret}")
    private String corpSecret;

    @Value("yFNhGCzBkMFFfr006I")
    private String token;

    @Value("48LSq2qjDYhB5ySWswtUB0QCiUJtPPPYOpxa2K2mAv8")
    private String aes;

    @Autowired
    WeChatService weChatService;
    //    @GetMapping("/verify")
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@PathVariable Integer agentId,
                          @RequestParam(name = "msg_signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) throws AesException {
        log.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
            signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        String sEchoStr; //需要返回的明文
        final WxCpService wxCpService = WxCpConfiguration.getCpService(agentId);
        if (wxCpService == null) {
            throw new IllegalArgumentException(String.format("未找到对应agentId=[%d]的配置，请核实！", agentId));
        }

//        if (wxCpService.checkSignature(signature, timestamp, nonce, echostr)) {
//            return new WxCpCryptUtil(wxCpService.getWxCpConfigStorage()).decrypt(echostr);
//        }

        WXBizJsonMsgCrypt wxcpt = new WXBizJsonMsgCrypt(token, aes, corpId);
        sEchoStr = wxcpt.VerifyURL(signature, timestamp,
            nonce, echostr);
        System.out.println("verifyurl echostr: " + sEchoStr);

        return sEchoStr;

    }

    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@PathVariable Integer agentId,
                       @RequestBody String requestBody,
                       @RequestParam("msg_signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce) throws JsonProcessingException {
        log.info("\n接收微信请求：[signature=[{}], timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
            signature, timestamp, nonce, requestBody);

        final WxCpService wxCpService = WxCpConfiguration.getCpService(agentId);
        WxCpXmlMessage inMessage = WxCpXmlMessage.fromEncryptedXml(requestBody, wxCpService.getWxCpConfigStorage(),
            timestamp, nonce, signature);
        log.debug("\n消息解密后内容为：\n{} ", JsonUtils.toJson(inMessage));
        String jsonString = JsonUtils.toJson(inMessage);

        //处理解密后的消息串
        ObjectMapper objectMapper = new ObjectMapper();
        WeChatEvent weChatEvent = objectMapper.readValue(jsonString, WeChatEvent.class);
        weChatService.judgeEvent(weChatEvent);

        WxCpXmlOutMessage outMessage = this.route(agentId, inMessage);
        if (outMessage == null) {
            return "";
        }

        String out = outMessage.toEncryptedXml(wxCpService.getWxCpConfigStorage());
        log.debug("\n组装回复信息：{}", out);
        return out;
    }

    private WxCpXmlOutMessage route(Integer agentId, WxCpXmlMessage message) {
        try {
            return WxCpConfiguration.getRouters().get(agentId).route(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }


}
