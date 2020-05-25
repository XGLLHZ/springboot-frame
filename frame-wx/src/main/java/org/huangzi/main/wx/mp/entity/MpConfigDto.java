package org.huangzi.main.wx.mp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/5/23 下午3:39
 * @description: 公众号配置-支持多公众号
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "wx.mp")
public class MpConfigDto {

    private List<Config> configs;

    @Data
    public static class Config {

        private String appId;   // appId

        private String appSecret;   // appSecret

        private String token;   //消息 token

        private String aesKey;   //消息密文

    }

}
