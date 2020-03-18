package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:30
 * @description: sku 实体类（商品）
 */
@Data
@Accessors(chain = true)
@TableName("goods_sku")
public class SKUEntity extends BaseEntity implements Serializable {

    private Integer spuId;   //spu id

    private String skuCode;   //sku 编码

    private String skuName;   //sku 名称

    private String barCode;   //条形码

}
