package org.huangzi.main.common.controller;

import org.huangzi.main.common.utils.StringUtil;
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

    @RequestMapping("/test")
    public String test() {
        return "Hello Util1!";
    }

    @RequestMapping("ip")
    public String getIp(HttpServletRequest request) {
        String ip = StringUtil.getUserIp(request);
        String ipInfo = StringUtil.getAddressByIp(ip);
        String browser = StringUtil.getUserBrowser(request);
        return ip + " " + ipInfo + " " + browser;
    }

}
