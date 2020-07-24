package org.huangzi.main.common.utils;

import lombok.Data;
import lombok.ToString;
import org.huangzi.main.common.service.LogEsService;
import org.huangzi.main.common.service.impl.LogEsServiceImpl;

/**
 * @author: XGLLHZ
 * @date: 2020/7/22 下午3:46
 * @description:
 */

@Data
@ToString
class Dog {

    private String name;

    private Integer gender;

    public void finalize() throws Throwable {
        System.out.println("对象被释放-->" + this);
    }

}

@Data
@ToString
public class JavaTest {

    private final String NAME = "XGLLHZ";

    private String userName;

    private Integer age;

    @Data
    @ToString
    static class Cat extends BaseEntity {
        private String name;
    }

    public static void main(String[] args) {
        /*Dog dog = new Dog();
        dog.setName("轩妹");
        dog.setGender(2);

        dog = null;
        System.gc();

        LogEsService logEsService = new LogEsServiceImpl();*/

    }

}
