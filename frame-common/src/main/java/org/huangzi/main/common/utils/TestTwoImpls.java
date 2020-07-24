package org.huangzi.main.common.utils;


/**
 * @author: XGLLHZ
 * @date: 2020/7/22 下午5:34
 * @description:
 */
public class TestTwoImpls implements TestTwo<String> {

    @Override
    public String test(String s) {
        return s;
    }

    @Override
    public void test1(TestOne<?> testOne) {

    }

    @Override
    public <E> E test2(TestOne<E> testOne) {
        return null;
    }

}
