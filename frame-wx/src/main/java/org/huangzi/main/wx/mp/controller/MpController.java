package org.huangzi.main.wx.mp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.dto.ExceptionDto;
import org.springframework.web.bind.annotation.*;

/**
 * @author: XGLLHZ
 * @date: 2020/5/22 下午10:53
 * @description: 公众号控制器
 */
@Slf4j
@RestController
@RequestMapping("/mp/chat/{appId}")
@AllArgsConstructor
public class MpController {

    private final WxMpService wxMpService;

    private final WxMpMessageRouter messageRouter;

    /**
     *
     * @param appId
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @LogAnnotation("公众号认证消息")
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String get(@PathVariable String appId,
                      @RequestParam(name = "signature", required = false) String signature,
                      @RequestParam(name = "timestamp", required = false) String timestamp,
                      @RequestParam(name = "nonce", required = false) String nonce,
                      @RequestParam(name = "echostr", required = false) String echostr) {

        log.info("\n接受到来自微信服务器的认证消息: [{}, {}, {}, {}, {}]", appId, signature, timestamp, nonce, echostr);

        if (!this.wxMpService.switchover(appId)) {
            log.error(String.format("\n未找到对应 appId = [%s] 的配置，请确认！", appId));
            throw new ExceptionDto(3302, String.format("未找到对应 appId = [%s] 的配置，请确认！", appId));
        }

        if (StringUtils.isAnyBlank(signature, timestamp, nonce)) {
            log.error("\n请求参数非法，请确认！");
            throw new ExceptionDto(3303, "请求参数非法，请确认！");
        }

        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "验签失败！";
    }

    @LogAnnotation("公众号交互消息")
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@PathVariable String appId,
                       @RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("openid") String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {

        log.info("\n接收微信请求: [appId=[{}]], [openid=[{}]], [signature=[{}]], [timestamp=[{}]], [nonce=[{}]], " +
                "[encType=[{}]], [msgSignature=[{}]], [requestBody=[\n{}\n]]", appId, openid, signature, timestamp,
                nonce, encType, msgSignature, requestBody);

        if (!this.wxMpService.switchover(appId)) {
            log.error(String.format("\n未找到对应 appId = [%s] 的配置，请确认！", appId));
            throw new ExceptionDto(3302, String.format("未找到对应 appId = [%s] 的配置，请确认！", appId));
        }

        if (StringUtils.isAnyBlank(signature, timestamp, nonce)) {
            log.error("\n请求参数非法，请确认！");
            throw new ExceptionDto(3303, "请求参数非法，请确认！");
        }

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new ExceptionDto(3304, "非法请求，可能属于伪造请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toXml();
        } else if ("aes".equalsIgnoreCase(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxMpService.getWxMpConfigStorage(),
                    timestamp, nonce, msgSignature);
            log.info("\n消息解密后的内容为: \n{}", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
        }

        log.info("\n组装回复的消息为: \n{}", out);
        return out;
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return this.messageRouter.route(message);
        } catch (Exception e) {
        }

        return null;
    }

}
