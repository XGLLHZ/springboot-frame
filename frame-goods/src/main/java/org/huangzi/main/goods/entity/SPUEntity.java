package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午9:41
 * @description: spu 实体类（货物）
 */
@Data
@Accessors(chain = true)
@TableName("good_spu")
public class SPUEntity extends BaseEntity implements Serializable {

    private Integer brandId;   //品牌 id

    private String brandName;   //品牌名称

    private String spuCode;   //spu 编码

    private String spuName;   //spu 名称（产品名称）

    private String unit;   //单位

    private String guaranteeTime;   //保质期

    private String spuIntroduction;   //简介

}
