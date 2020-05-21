package org.huangzi.main.common.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 上午11:59
 * @description: websocket 消息实体类
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MessageDto implements Serializable {

    private Integer msgType;   //消息类型: 0: 全部; 1: 文本; 2: 图片; 3: 音频; 4: 视频; 5: 文件

    private String fromUser;   //发送方

    private String toUser;   //接收方

    private String content;   //消息内容

    private String sendTime;   //发送时间

}
