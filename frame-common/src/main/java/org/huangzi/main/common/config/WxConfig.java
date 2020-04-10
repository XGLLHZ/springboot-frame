package org.huangzi.main.common.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.dto.WxConfigDto;
import org.huangzi.main.common.utils.ConstConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: XGLLHZ
 * @date: 2020/4/10 下午6:01
 * @description: 微信配置文件
 */
@Configuration
@EnableConfigurationProperties(WxConfigDto.class)
public class WxConfig {

    private WxConfigDto wxConfigDto;
    private static Map<String, WxMaService> serviceMap = Maps.newHashMap();
    private static Map<String, WxMaMessageRouter> messageRouterMap = Maps.newHashMap();

    @Autowired
    public WxConfig(WxConfigDto wxConfigDto) {
        this.wxConfigDto = wxConfigDto;
    }

    /**
     * 项目启动时运行 初始化微信配置
     *
     * 类加载顺序
     * 构造方法 - @Autowried - @PostConstruct - 静态方法
     */
    @PostConstruct
    public void init() {
        List<WxConfigDto.Config> list = this.wxConfigDto.getConfigs();
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_MA_LOGIN_FAIL_CODE, ConstConfig.RE_MA_LOGIN_FAIL_MESSAGE);
        }
        serviceMap = list.stream()
                .map(item -> {
                    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
                    config.setAppid(item.getAppId());
                    config.setSecret(item.getAppSecret());
                    config.setToken(item.getToken());
                    config.setAesKey(item.getAesKey());
                    config.setMsgDataFormat(item.getMsgFormat());

                    WxMaService wxMaService = new WxMaServiceImpl();
                    wxMaService.setWxMaConfig(config);
                    messageRouterMap.put(item.getAppId(), this.newRouter(wxMaService));
                    return wxMaService;
                }).collect(Collectors.toMap(s -> s.getWxMaConfig().getAppid(), item -> item));
    }

    /**
     * 获取微信配置
     * @param appId
     * @return
     */
    public static WxMaService getService(String appId) {
        WxMaService wxMaService = serviceMap.get(appId);
        if (wxMaService == null) {
            throw  new ExceptionDto(ConstConfig.RE_MA_LOGIN_FAIL_CODE, ConstConfig.RE_MA_LOGIN_FAIL_MESSAGE);
        }
        return wxMaService;
    }

    public static WxMaMessageRouter getRouter(String appId) {
        return messageRouterMap.get(appId);
    }

    private WxMaMessageRouter newRouter(WxMaService service) {
        final WxMaMessageRouter router = new WxMaMessageRouter(service);
        router
                .rule().handler(logHandler).next()
                .rule().async(false).content("模板").handler(templateMsgHandler).end()
                .rule().async(false).content("文本").handler(textHandler).end()
                .rule().async(false).content("图片").handler(picHandler).end()
                .rule().async(false).content("二维码").handler(qrcodeHandler).end();
        return router;
    }

    private final WxMaMessageHandler templateMsgHandler = (wxMessage, context, service, sessionManager) -> {
        service.getMsgService().sendTemplateMsg(WxMaTemplateMessage.builder()
                .templateId("此处更换为自己的模板id")
                .formId("自己替换可用的formid")
                .data(Lists.newArrayList(
                        new WxMaTemplateData("keyword1", "339208499", "#173177")))
                .toUser(wxMessage.getFromUser())
                .build());
        return null;
    };

    private final WxMaMessageHandler logHandler = (wxMessage, context, service, sessionManager) -> {
        System.out.println("收到消息：" + wxMessage.toString());
        service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + wxMessage.toJson())
                .toUser(wxMessage.getFromUser()).build());
        return null;
    };

    private final WxMaMessageHandler textHandler = (wxMessage, context, service, sessionManager) -> {
        service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("回复文本消息")
                .toUser(wxMessage.getFromUser()).build());
        return null;
    };

    private final WxMaMessageHandler picHandler = (wxMessage, context, service, sessionManager) -> {
        try {
            WxMediaUploadResult uploadResult = service.getMediaService()
                    .uploadMedia("image", "png",
                            ClassLoader.getSystemResourceAsStream("tmp.png"));
            service.getMsgService().sendKefuMsg(
                    WxMaKefuMessage
                            .newImageBuilder()
                            .mediaId(uploadResult.getMediaId())
                            .toUser(wxMessage.getFromUser())
                            .build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;
    };

    private final WxMaMessageHandler qrcodeHandler = (wxMessage, context, service, sessionManager) -> {
        try {
            final File file = service.getQrcodeService().createQrcode("123", 430);
            WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", file);
            service.getMsgService().sendKefuMsg(
                    WxMaKefuMessage
                            .newImageBuilder()
                            .mediaId(uploadResult.getMediaId())
                            .toUser(wxMessage.getFromUser())
                            .build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;
    };

}
