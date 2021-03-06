package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:27
 * @description: 属性-值 实体类
 */
@Data
@Accessors(chain = true)
@TableName("goods_attribute_value")
public class AttributeValueEntity extends BaseEntity implements Serializable {

    private Integer attributeId;   //属性 id

    private String attributeValue;   //属性值

}
