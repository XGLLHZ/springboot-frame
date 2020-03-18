package org.huangzi.main.common.utils;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午8:21
 * @description: 堆栈工具类
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        }
    }

}
