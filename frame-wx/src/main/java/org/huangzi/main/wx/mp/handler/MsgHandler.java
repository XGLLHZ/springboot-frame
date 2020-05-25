package org.huangzi.main.wx.mp.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.huangzi.main.wx.mp.builder.TextBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/5/24 下午8:48
 * @description: 默认处理器
 */
@Slf4j
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

        if (!wxMpXmlMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMpXmlMessage.getContent(), "你好", "客服")
                    && wxMpService.getKefuService().kfOnlineList()
                    .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMpXmlMessage.getToUser())
                        .toUser(wxMpXmlMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        //TODO 组装回复消息
//        String content = "收到信息内容：" + JsonUtils.toJson(wxMpXmlMessage);

        return new TextBuilder().build("收到信息内容", wxMpXmlMessage, wxMpService);

    }

}
