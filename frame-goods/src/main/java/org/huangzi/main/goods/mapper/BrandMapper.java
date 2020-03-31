package org.huangzi.main.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.goods.entity.BrandEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:46
 * @description: 品牌 mapper 接口
 */
public interface BrandMapper extends BaseMapper<BrandEntity> {

    /**
     * 列表-分页
     * @param page
     * @param brandEntity
     * @return
     */
    List<BrandEntity> getList(Page<BrandEntity> page, @Param("condition") BrandEntity brandEntity);

    /**
     * 总数-分页
     * @param brandEntity
     * @return
     */
    Integer getTotal(@Param("condition") BrandEntity brandEntity);

}
