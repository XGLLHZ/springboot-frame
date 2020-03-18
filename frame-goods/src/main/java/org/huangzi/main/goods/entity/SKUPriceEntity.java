package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:40
 * @description: sku-价格 实体类
 */
@Data
@Accessors(chain = true)
@TableName("goods_sku_price")
public class SKUPriceEntity extends BaseEntity implements Serializable {

    private Integer skuId;   //sku id

    private String priceCode;   //价格编码

    private String priceName;   //价格名称

    private Integer priceValue;   //价格值

}
