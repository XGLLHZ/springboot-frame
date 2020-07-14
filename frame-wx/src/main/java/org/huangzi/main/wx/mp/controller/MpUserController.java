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
import org.huangzi.main.wx.mp.vo.MpVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public APIResponse getAuthorizeUrl(@RequestBody MpVo mpVo) {
        //SNSAPI_USERINFO 弹出授权页面，可获取用户详细信息
        String url = wxMpService.oauth2buildAuthorizationUrl(mpVo.getRedirectUrl(), WxConsts.OAuth2Scope.SNSAPI_USERINFO, "STATE");
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, url);
        return new APIResponse(map);
    }

    @LogAnnotation("授权后获取用户信息")
    @RequestMapping("/getUserInfo")
    public APIResponse getUserInfo(@RequestBody MpVo mpVo) throws WxErrorException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(mpVo.getCode());
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

    //微信用户信息:
    // {
    //      "openId":"o5qN9wD24QmTo7gzEryItg5K-3Gk",
    //      "nickname":"香格里拉皇子",
    //      "sexDesc":"男",
    //      "sex":1,
    //      "language":"zh_CN",
    //      "city":"滨海新区",
    //      "province":"天津",
    //      "country":"中国",
    //      "headImgUrl":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erTxyBgqxZZ5iabajvvpHBCrJoFpiaxKO3gviaJPUkDebB2w1pibibBdFo2GkE9eX9wB5FBbQPqxT0kDGg/132",
    //      "privileges":[]
    // }

}
