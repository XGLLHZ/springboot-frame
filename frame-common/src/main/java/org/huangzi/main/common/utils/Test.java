package org.huangzi.main.common.utils;

import org.huangzi.main.common.entity.TestEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: XGLLHZ
 * @date: 2020/7/22 下午5:26
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        /*TestOne<Integer> testOne = new TestOne<>(1);
        TestOne<String> testOne1 = new TestOne<>("香格里拉皇子");
        TestOne testOne2 = new TestOne("人世间子");
        System.out.println(testOne.getValue() + " " + testOne1.getValue() + " " + testOne2.getValue());
        Integer integer = 1;*/
        /*TestTwoImpl<Integer> testTwo = new TestTwoImpl<>();
        TestOne<String> testOne = new TestOne<>("叫三声沫沫");
//        TestTwoImpl.testThree(testTwo);

        Integer a = 128;
        Integer b = 128;
        System.out.println(a == b);   //-128 - 127*/

        Class c = TestEntity.class;
        System.out.println(c.getName());
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println("方法名: " + method.getName());
            System.out.println("返回类型: " + method.getReturnType().getName());
            Class[] paramTypes = method.getParameterTypes();
            for (Class cs : paramTypes) {
                System.out.print("参数类型: " + cs.getName() + ", \n");
            }
        }

        TestEntity.builder()
                .id(1)
                .testName("香格里拉皇子")
                .testMessage("哈哈哈")
                .build();

    }

}
