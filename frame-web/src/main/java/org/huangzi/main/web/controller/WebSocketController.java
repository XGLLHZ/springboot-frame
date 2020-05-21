package org.huangzi.main.common.controller;

import org.huangzi.main.common.dto.MessageDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 下午12:04
 * @description:
 */
@Component
@ServerEndpoint("/chat/private")
public class WebSocketController {

    @OnOpen
    public void onOpen(@PathParam("fromUser") String fromUser) {
        System.out.println("打开聊天: " + fromUser);
    }

    @OnMessage
    public void onMessage(@PathParam("fromUser") String fromUser, @PathParam("toUser") String toUser, String content) {
        System.out.println(fromUser + " 发送给 " + toUser + " " + content);
    }

    @OnClose
    public void onClose(@PathParam("fromUser") String fromUse) {
        System.out.println("关闭聊天: " + fromUse);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println("出错: " + throwable.getMessage());
    }

}
