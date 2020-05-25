package org.huangzi.main.wx.mp.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
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
 * @date: 2020/5/25 下午9:49
 * @description: 文本消息处理器
 */
@Slf4j
@Component
public class TextMsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        //判断消息是否为文本消息
        if (wxMpXmlMessage.getMsgType().equals(WxConsts.XmlMsgType.TEXT)) {
            //TODO: 这里可将消息持久化
        }

        //获取微信用户的基本信息
        //订阅号没有权限
        /*WxMpUser wxMpUser = wxMpService.getUserService().userInfo(wxMpXmlMessage.getFromUser(), "zh_CN");
        if (null != wxMpUser){
            log.info("\n微信用户信息: \n{}", wxMpUser);
            //下面两种响应方式都可以
            return new TextBuilder().build("您已解锁本站全部文章！",wxMpXmlMessage,wxMpService);
        }*/
        return new TextBuilder().build("您已解锁本站全部文章！",wxMpXmlMessage,wxMpService);
    }

}
