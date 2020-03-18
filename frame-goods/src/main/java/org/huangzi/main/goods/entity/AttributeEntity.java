package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:22
 * @description: 属性实体类
 */
@Data
@Accessors(chain = true)
@TableName("goods_attribute")
public class AttributeEntity extends BaseEntity implements Serializable {

    private Integer categoryId;   //分类 id

    private String attributeCode;   //属性编码

    private String attributeName;   //属性名称

}
