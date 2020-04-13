package org.huangzi.main.ma.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 上午10:15
 * @description: 媒体实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ma_media")
public class MediaEntity extends BaseEntity implements Serializable {

    private Integer userId;   //用户 id

    private Integer objectId;   //对象 id: 动态等

    private Integer mediaType;   //媒体类型: 0: 全部; 1: 图片; 2: 音频; 3: 视频

    private String fileName;   //文件名（文件地址 url）

}
