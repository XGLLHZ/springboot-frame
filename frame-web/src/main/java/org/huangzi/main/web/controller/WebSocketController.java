package org.huangzi.main.web.controller;

import com.alibaba.fastjson.JSON;
import org.huangzi.main.web.dto.MessageDto;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 下午12:04
 * @description:
 */
@Component
@ServerEndpoint("/chat/private/{id}/{userId}/{userName}")
public class WebSocketController {

    //群组中的每一个成员的 websocket 连接
    private static ConcurrentHashMap<String, List<Session>> groupMap = new ConcurrentHashMap<>();

    //群组中的消息
    private static ConcurrentHashMap<String, List<MessageDto>> groupMsgMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id, @PathParam("userId") String userId,
                       @PathParam("userName") String userName) {
        List<Session> list = groupMap.get(id);
        if (list != null && list.size() > 0 && list.get(0).getPathParameters().get("userId").equals(userId)) {
//            System.out.println("用户 " + userName + " 再次进入聊天室 " + id);
        } else {
            list = groupMap.computeIfAbsent(id, k -> new ArrayList<>());
            list.add(session);
            onMessage(id, userId, userName, "{'content':'用户 " + userName + " 上线了';" + "'onlineNum':" + list.size() + "}");
//            System.out.println("用户 " + userName + " 进入聊天室 " + id);
        }
    }

    @OnMessage
    public void onMessage(@PathParam("id") String id, @PathParam("userId") String userId,
                          @PathParam("userName") String userName, String content) {
        List<Session> list = groupMap.get(id);
        list.forEach(item -> {
            MessageDto messageDto = JSON.parseObject(content, MessageDto.class);
            messageDto.setFromUser(userName);
            messageDto.setOnlineNum(list.size());
            String json = JSON.toJSONString(messageDto);
            try {
                item.getBasicRemote().sendText(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//        System.out.println("用户 " + userName + " 在聊天室 " + id + " 中发送了消息: " + content);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id, @PathParam("userId") String userId,
                        @PathParam("userName") String userName) {
        List<Session> list = groupMap.get(id);
        list.remove(session);
        onMessage(id, userId, userName, "{'content':'用户 " + userName + " 下线了'}");
//        System.out.println("用户 " + userName + " 退出聊天室 " + id);
    }

    @OnError
    public void onError(Throwable throwable) {
//        System.out.println("出错: " + throwable.getMessage());
    }

}
