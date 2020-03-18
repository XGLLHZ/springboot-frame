package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/2/3 下午2:41
 * @description: 数据字典实体类
 */
@Data
@Accessors(chain = true)
@TableName("bus_dict")
public class DictionaryEntity extends BaseEntity implements Serializable {

    private Integer parentId;   //所属 id

    private String dictName;   //字典名称

    private String dictDesc;   //描述

    private String dictCode;   //字典 code

    private String dictValue;   //字典 value

    private Integer dictSort;   //排序

    @TableField(exist = false)
    private List<DictionaryEntity> dictList;   //字典详情

}
