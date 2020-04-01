package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:38
 * @description: sku-属性-值 实体类
 */
@Data
@Accessors(chain = true)
@TableName("goods_sku_attribute")
public class SKUAttributeEntity extends BaseEntity implements Serializable {

    private Integer skuId;   //sku id

    private Integer attributeId;   //属性 id

    private String attributeName;   //属性名

    private Integer attributeValueId;   //属性值 id

    private String attributeValue;   //属性值

}
