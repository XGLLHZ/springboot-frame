package org.huangzi.main.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.SPUEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 下午2:42
 * @description: spu 事务层接口
 */
public interface SPUService extends IService<SPUEntity> {

    /**
     * 列表
     * @param spuEntity
     * @return
     */
    APIResponse getList(SPUEntity spuEntity);

    /**
     * 详情
     * @param spuEntity
     * @return
     */
    APIResponse getSPU(SPUEntity spuEntity);

    /**
     * 新增
     * @param spuEntity
     * @return
     */
    APIResponse insertSPU(SPUEntity spuEntity);

    /**
     * 删除
     * @param spuEntity
     * @return
     */
    APIResponse deleteSPU(SPUEntity spuEntity);

    /**
     * 修改
     * @param spuEntity
     * @return
     */
    APIResponse updateSPU(SPUEntity spuEntity);

}
