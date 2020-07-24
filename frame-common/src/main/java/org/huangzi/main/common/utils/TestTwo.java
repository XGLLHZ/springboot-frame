package org.huangzi.main.common.utils;

/**
 * @author: XGLLHZ
 * @date: 2020/7/22 下午5:32
 * @description:
 */
public interface TestTwo<T> {

    T test(T t);

    void test1(TestOne<?> testOne);

    <E> E test2(TestOne<E> testOne);

}
