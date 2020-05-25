package org.huangzi.main.wx.mp.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.wx.mp.entity.MpConfigDto;
import org.huangzi.main.wx.mp.handler.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;

/**
 * @author: XGLLHZ
 * @date: 2020/5/23 下午3:36
 * @description: 公众号配置文件
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(MpConfigDto.class)
public class MpConfig {

    private final MpConfigDto mpConfigDto;

    private final LogHandler logHandler;

    private final SubscribeHandler subscribeHandler;

    private final UnSubscribeHandler unSubscribeHandler;

    private final TextMsgHandler textMsgHandler;

    private final MsgHandler msgHandler;

    @Bean
    public WxMpService wxMpService() {
        final List<MpConfigDto.Config> configs = this.mpConfigDto.getConfigs();
        if (configs == null) {
            log.error("配置文件出错了哦！");
            throw new ExceptionDto(3301, "配置文件出错了哦！");
        }
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setMultiConfigStorages(configs
                .stream().map(item -> {
                    WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
                    wxMpDefaultConfig.setAppId(item.getAppId());
                    wxMpDefaultConfig.setSecret(item.getAppSecret());
                    wxMpDefaultConfig.setToken(item.getToken());
                    wxMpDefaultConfig.setAesKey(item.getAesKey());
                    return wxMpDefaultConfig;
                }).collect(Collectors.toMap(WxMpDefaultConfigImpl::getAppId, item -> item, (o ,n) -> o)));
        return wxMpService;
    }

    @Bean
    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        //记录日志
        newRouter.rule().handler(this.logHandler).next();

        //关注事件
        newRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(this.subscribeHandler).end();

        //取消关注事件
        newRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(this.unSubscribeHandler).end();

        //文本消息
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(this.textMsgHandler).end();

        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }

}
