package org.huangzi.main.common.utils;

import java.util.Random;

/**
 * @author: XGLLHZ
 * @date: 2019/9/12 17:32
 * @description: 随机数
 */
public class RandomUtil {

    /**
     * 生成 6 位验证码
     * @return 字符串
     */
    public static String sixCode() {
        Random random = new Random();
        String randoms = "";
        for (int i = 0; i < 6; i++ ) {
            randoms += random.nextInt(10);
        }
        return randoms;
    }

    /**
     * 生成 6 位验证码
     * @return 数组
     */
    public static String[] sixCodeArray() {
        Random random = new Random();
        String randoms = "";
        for (int i = 0; i < 6; i++) {
            randoms += random.nextInt(10);
        }
        return randoms.split(",");
    }

}
