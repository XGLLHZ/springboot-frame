package org.huangzi.main.common.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/4/10 下午5:51
 * @description: 微信配置
 */
@Data
@ConfigurationProperties(prefix = "wx.ma")
public class WxConfigDto {

    private List<Config> configs;

    @Data
    public static class Config {

        private String appId;   // app_id

        private String appSecret;   //app_secret

        private String token;   //消息token

        private String aesKey;   //消息密文

        private String msgFormat;   //消息格式

    }

}
