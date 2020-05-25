package org.huangzi.main.wx.mp.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author: XGLLHZ
 * @date: 2020/5/24 下午8:42
 * @description:
 */
public abstract class AbstractBuilder {

    public abstract WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMpXmlMessage,
                                            WxMpService wxMpService);

}
