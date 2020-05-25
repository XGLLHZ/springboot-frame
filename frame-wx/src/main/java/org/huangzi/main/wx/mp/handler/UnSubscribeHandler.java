package org.huangzi.main.wx.mp.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/5/25 下午8:33
 * @description: 取关事件处理器
 */
@Slf4j
@Component
public class UnSubscribeHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) {
        log.info("\n用户取关: openId = " + wxMpXmlMessage.getFromUser());
        return null;
    }

}
