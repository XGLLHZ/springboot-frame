package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:06
 * @description: 品牌实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("goods_brand")
public class BrandEntity extends BaseEntity implements Serializable {

    private String brandCode;   //品牌编码

    private String brandName;   //品牌名称

    private String brandIntroduction;   //简介

}
