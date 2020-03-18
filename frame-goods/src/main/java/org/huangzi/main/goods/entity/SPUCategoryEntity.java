package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:19
 * @description: spu-分类 实体类
 */
@Data
@Accessors(chain = true)
@TableName("goods_spu_category")
public class SPUCategoryEntity extends BaseEntity implements Serializable {

    private Integer spuId;

    private Integer categoryId;

}
