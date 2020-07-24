package org.huangzi.main.common.utils;

/**
 * @author: XGLLHZ
 * @date: 2020/7/22 下午5:25
 * @description:
 */
public class TestOne<T> {

    private T value;

    public TestOne(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
