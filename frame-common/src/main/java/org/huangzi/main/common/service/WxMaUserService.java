package org.huangzi.main.common.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;

/**
 * @author: XGLLHZ
 * @date: 2020/4/10 下午9:29
 * @description: 微信小程序用户相关
 */
public interface WxMaUserService {

    /**
     * 微信授权并获取用户信息
     * @param appId
     * @param code
     * @param signature
     * @param rawData
     * @param encryptedData
     * @param iv
     * @return
     */
    WxMaUserInfo wxLogin(String appId, String code, String signature, String rawData, String encryptedData, String iv);

}
