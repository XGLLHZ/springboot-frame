package org.huangzi.main.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.CategoryEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/3/31 下午9:26
 * @description: 分类事务层接口
 */
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 全部分类-树形结构
     * @param categoryEntity
     * @return
     */
    APIResponse getAllCategory(CategoryEntity categoryEntity);

    /**
     * 详情
     * @param categoryEntity
     * @return
     */
    APIResponse getCategory(CategoryEntity categoryEntity);

    /**
     * 新增
     * @param categoryEntity
     * @return
     */
    APIResponse insertCategory(CategoryEntity categoryEntity);

    /**
     * 删除
     * @param categoryEntity
     * @return
     */
    APIResponse deleteCategory(CategoryEntity categoryEntity);

    /**
     * 修改
     * @param categoryEntity
     * @return
     */
    APIResponse updateCategory(CategoryEntity categoryEntity);

}
