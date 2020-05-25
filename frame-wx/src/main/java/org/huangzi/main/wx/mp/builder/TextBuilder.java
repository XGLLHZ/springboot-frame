package org.huangzi.main.wx.mp.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 * @author: XGLLHZ
 * @date: 2020/5/24 下午8:44
 * @description:
 */
public class TextBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMpXmlMessage, WxMpService wxMpService) {
        WxMpXmlOutTextMessage m = WxMpXmlOutMessage
                .TEXT()
                .content(content)
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser())
                .build();
        return m;
    }

}
