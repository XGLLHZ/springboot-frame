package org.huangzi.main.common.utils;

/**
 * @author: XGLLHZ
 * @date: 2020/7/22 下午5:33
 * @description:
 */
public class TestTwoImpl<T> implements TestTwo<T> {

    @Override
    public T test(T t) {
        return t;
    }

    @Override
    public void test1(TestOne<?> testOne) {

    }

    @Override
    public <E> E test2(TestOne<E> testOne) {
        E e = testOne.getValue();
        return e;
    }

    public static <T> void testTwo(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }

    public static <T> void testThree(TestOne<? extends Number> tTestOne) {
        System.out.println("哈哈" + tTestOne.getValue());
    }

}
