package org.huangzi.main.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.BrandEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/3/31 下午3:56
 * @description: 品牌事务层接口
 */
public interface BrandService extends IService<BrandEntity> {

    /**
     * 列表-分页
     * @param brandEntity
     * @return
     */
    APIResponse getList(BrandEntity brandEntity);

    /**
     * 详情
     * @param brandEntity
     * @return
     */
    APIResponse getBrand(BrandEntity brandEntity);

    /**
     * 新增
     * @param brandEntity
     * @return
     */
    APIResponse insertBrand(BrandEntity brandEntity);

    /**
     * 删除
     * @param brandEntity
     * @return
     */
    APIResponse deleteBrand(BrandEntity brandEntity);

    /**
     * 修改
     * @param brandEntity
     * @return
     */
    APIResponse updateBrand(BrandEntity brandEntity);

    /**
     * 全部
     * @param brandEntity
     * @return
     */
    APIResponse getAllBrand(BrandEntity brandEntity);

}
