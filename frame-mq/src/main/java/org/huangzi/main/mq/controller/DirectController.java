package org.huangzi.main.mq.controller;

import org.huangzi.main.common.utils.APIResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: XGLLHZ
 * @date: 2020/6/10 下午11:10
 * @description: 直连式 mq 控制器
 */
@RestController
@RequestMapping("/mq/direct")
public class DirectController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/send")
    public APIResponse sendMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "只知道是时候拿着鲜花";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-HH-mm HH:mm:ss"));
        Map<String, Object> map = new HashMap<>(3);
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("DirectExchangeTest", "DirectBindingTest", map);
        return new APIResponse();
    }

}
