package org.huangzi.main.wx.ma.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 上午10:35
 * @description: 动态实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ma_dynamic")
public class DynamicEntity extends BaseEntity implements Serializable {

    private Integer userId;   //用户 id

    private String content;   //内容

    @TableField(exist = false)
    private List<String> fileList;   //文件名列表

}
