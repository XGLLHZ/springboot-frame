package org.huangzi.main.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午9:05
 * @description: http 工具类
 */
public class HttpUtil {

    /**
     * 获取 request 对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

}
