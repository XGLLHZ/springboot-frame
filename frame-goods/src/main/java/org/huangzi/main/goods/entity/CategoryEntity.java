package org.huangzi.main.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:10
 * @description: 分类实体类
 */
@Data
@Accessors(chain = true)
@TableName("goods_category")
public class CategoryEntity extends BaseEntity implements Serializable {

    private Integer parentId;   //父 id

    private String categoryCode;   //分类编码

    private String categoryName;   //分类名称

}
