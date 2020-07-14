package org.huangzi.main.common.controller;

import org.huangzi.main.common.annotation.CheckToken;
import org.huangzi.main.common.config.DataConfig;
import org.huangzi.main.common.dto.TokenDto;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.RedisUtil;
import org.huangzi.main.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XGLLHZ
 * @date: 2019/11/7 14:29
 * @description: 工具模块测试
 */
@RestController
@RequestMapping("/util")
public class UtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/test")
    public String test() {
        redisUtil.setValue("xgllhz", "香格里拉皇子");
        System.out.println(redisUtil.getValue("xgllhz"));
        System.out.println(DataConfig.getFromAccount());
        return "Hello Util1!";
    }

    @RequestMapping("ip")
    public String getIp(HttpServletRequest request) {
        String ip = StringUtil.getUserIp(request);
        String ipInfo = StringUtil.getAddressByIp(ip);
        String browser = StringUtil.getUserBrowser(request);
        return ip + " " + ipInfo + " " + browser;
    }

    @CheckToken
    @RequestMapping("/token")
    public APIResponse testToken(@RequestBody TokenDto tokenDto) {
        System.out.println(tokenDto);
        return new APIResponse();
    }

}
