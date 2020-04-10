package org.huangzi.main.common.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.huangzi.main.common.config.WxConfig;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.service.WxMaUserService;
import org.huangzi.main.common.utils.ConstConfig;

/**
 * @author: XGLLHZ
 * @date: 2020/4/10 下午9:32
 * @description: 微信小程序用户相关
 */
public class WxMaUserServiceImpl implements WxMaUserService {

    @Override
    public WxMaUserInfo wxLogin(String appId, String code, String signature, String rawData, String encryptedData, String iv) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(code)) {
            throw new ExceptionDto(ConstConfig.RE_MA_LOGIN_FAIL_CODE, ConstConfig.RE_MA_LOGIN_FAIL_MESSAGE);
        }
        // access_token 存放在 redis 中
        final WxMaService wxMaService = WxConfig.getService(appId);
        try {
            //获取 session_key
            // session_key 是根据 code 加密后得到的
            WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(code);
            if (result == null) {
                throw new ExceptionDto(ConstConfig.RE_MA_LOGIN_FAIL_CODE, ConstConfig.RE_MA_LOGIN_FAIL_MESSAGE);
            }
            //校验用户信息
            if (!wxMaService.getUserService().checkUserInfo(result.getSessionKey(), rawData, signature)) {
                throw new ExceptionDto(ConstConfig.RE_MA_LOGIN_FAIL_CODE, ConstConfig.RE_MA_LOGIN_FAIL_MESSAGE);
            }
            //获取用户信息
            WxMaUserInfo wxMaUserInfo = wxMaService.getUserService().getUserInfo(result.getSessionKey(), encryptedData, iv);
            if (wxMaUserInfo == null) {
                throw new ExceptionDto(ConstConfig.RE_MA_LOGIN_FAIL_CODE, ConstConfig.RE_MA_LOGIN_FAIL_MESSAGE);
            }
            return wxMaUserInfo;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

}
