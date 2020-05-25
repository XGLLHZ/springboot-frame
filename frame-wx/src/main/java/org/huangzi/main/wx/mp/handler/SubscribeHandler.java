package org.huangzi.main.wx.mp.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.huangzi.main.wx.mp.builder.TextBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/5/25 下午8:26
 * @description: 关注事件处理器
 */
@Slf4j
@Component
public class SubscribeHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) {
        log.info("\n新关注用户: openId = " + wxMpXmlMessage.getFromUser());
        //订阅还没有权限
        /*try {
            WxMpUser wxMpUser = wxMpService.getUserService().userInfo(wxMpXmlMessage.getFromUser(), null);
            if (wxMpUser != null) {
                log.info("\n微信用户信息: " + wxMpUser.toString());
                //  TODO 微信用户信息持久化
            }
        } catch (WxErrorException e) {
            log.error("\n获取微信用户信息失败！");
            e.printStackTrace();
        }*/
        return new TextBuilder().build("官人，奴家等候多时了呢！", wxMpXmlMessage, wxMpService);
    }

}
