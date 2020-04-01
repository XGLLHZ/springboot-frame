package org.huangzi.main.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.goods.entity.SPUEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:52
 * @description: spu mapper 接口
 */
public interface SPUMapper extends BaseMapper<SPUEntity> {

    /**
     * 列表-分页
     * @param page
     * @param spuEntity
     * @return
     */
    List<SPUEntity> getList(Page<SPUEntity> page, @Param("condition") SPUEntity spuEntity);

    /**
     * 数量
     * @param spuEntity
     * @return
     */
    Integer getTotal(@Param("condition") SPUEntity spuEntity);

}
