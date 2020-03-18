package org.huangzi.main.common.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: XGLLHZ
 * @date: 2020/2/3 下午10:09
 * @description: 字符串工具类
 * 继承于 org.apache.commons.lang3.StringUtils，内含各种工具方法
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

    private static final String UNKNOWN = "UNKNOWN";

    /**
     * 获取在线用户 ip 地址
     * @param request
     * @return
     */
    public static String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || StringUtil.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || StringUtil.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || StringUtil.UNKNOWN.equalsIgnoreCase(ip)) {
            //在用 getRemoteAddr 获取本机 ip 地址时，本地测试时用 127.0.0.1 代替 localhost，不然获取的是 ipv6 格式的
            ip = request.getRemoteAddr();
        }
        String localhost = "127.0.0.1";
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            try {
                //根据本机名获取本机 ip 地址
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ip;
    }

    /**
     * 根据在线用户 ip 获取用户所在省 市 区
     * @param ip
     * @return
     */
    public static String getAddressByIp(String ip) {
        DbSearcher dbSearcher = null;
        String path = "ip2region/ip2region.db";
        String name = "ip2region.db";
        try {
            DbConfig dbConfig = new DbConfig();
            File file = FileUtil.inputStreamToFile(new ClassPathResource(path).getStream(), name);
            dbSearcher = new DbSearcher(dbConfig, file.getPath());
            Method method = dbSearcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock = (DataBlock) method.invoke(dbSearcher, ip);
            String address = dataBlock.getRegion().replace("0|", "");
            char symbol = '|';
            if (address.charAt(address.length() - 1) == symbol) {
                address = address.substring(0, address.length() - 1);
            }
            return address.equals(ConstConfig.REGION) ? "内网IP" : address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取在线用户所用浏览器
     * @param request
     * @return
     */
    public static String getUserBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        Version version = browser.getVersion(request.getHeader("User-Agent"));
        if ("UNKNOWN".equals(browser) || version == null) {
            return "未知";
        }
        return browser.getName() + ":" + version.getVersion();
    }

}
