package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:38
 * @description: sku-属性-选项 实体类
 */
@Data
@Accessors(chain = true)
@TableName("goods_sku_attribute_option")
public class SKUAttributeOptionEntity extends BaseEntity implements Serializable {

    private Integer skuId;   //sku id

    private Integer attributeOptionId;   //属性选项 id

}
