package org.huangzi.main.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: XGLLHZ
 * @date: 2020/6/10 下午10:56
 * @description: 直连型交换机
 */
@Configuration
public class DirectConfig {

    /**
     * 队列 DirectQueueTest
     * @return
     */
    @Bean
    public Queue directQueue() {
        //durable: 消息持久化 默认为 false
        //exclusive: 只能被当前创建的连接使用 默认为 false
        //autoDelete: 是否自动删除 默认为 false
        //一般设置队列的持久化就行，其他两个默认为 false
        return new Queue("DirectQueueTest", false);
    }

    /**
     * 交换机 DirectExchangeTest
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("DirectExchangeTest", true, false);
    }

    /**
     * 绑定 将对列和交换机绑定 匹配键：DirectBindingTest
     * @return
     */
    @Bean
    Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("DirectBindingTest");
    }

    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }

}
