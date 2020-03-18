package org.huangzi.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 上午11:56
 * @description:
 */
@SpringBootApplication
@MapperScan("org.huangzi.main")
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
