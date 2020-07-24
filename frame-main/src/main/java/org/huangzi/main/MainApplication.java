package org.huangzi.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 上午11:56
 * @description:
 */
@SpringBootApplication
@EnableElasticsearchRepositories("org.huangzi.main.common.mapper")
@MapperScan("org.huangzi.main")
public class MainApplication {

    public static void main(String[] args) {
        //springboot 内部通信为 netty 所以关系 es 中的 netty
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(MainApplication.class, args);
    }

}
