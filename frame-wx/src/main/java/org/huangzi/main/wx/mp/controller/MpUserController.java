package org.huangzi.main.wx.mp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/5/26 上午11:53
 * @description: 微信公众号用户前端控制器
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mp/user")
public class MpUserController {

    private final WxMpService wxMpService;

    @LogAnnotation("获取公众号网页授权链接")
    @RequestMapping("/getUrl")
    public APIResponse getAuthorizeUrl(@RequestParam("redirectUrl") String redirectUrl) {
        //SNSAPI_USERINFO 弹出授权页面，可获取用户详细信息
        String url = wxMpService.oauth2buildAuthorizationUrl(redirectUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, "STATE");
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, url);
        return new APIResponse(map);
    }

    @LogAnnotation("授权后获取用户信息")
    @RequestMapping("/getUserInfo")
    public APIResponse getUserInfo(@RequestParam("code") String code) throws WxErrorException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        if (wxMpOAuth2AccessToken == null) {
            throw new ExceptionDto(ConstConfig.RE_MP_AUTHORIZE_ERROR_CODE, ConstConfig.RE_MP_AUTHORIZE_ERROR_MESSAGE);
        }
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
        if (wxMpUser == null) {
            throw new ExceptionDto(ConstConfig.RE_MP_AUTHORIZE_ERROR_CODE, ConstConfig.RE_MP_AUTHORIZE_ERROR_MESSAGE);
        }
        // TODO 微信用户信息持久化
        log.info("\n微信用户信息: {}", wxMpUser.toString());
        return new APIResponse();
    }

}
