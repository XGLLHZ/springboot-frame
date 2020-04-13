package org.huangzi.main.ma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.ma.entity.DynamicEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午4:34
 * @description: 动态事务层接口
 */
public interface DynamicService extends IService<DynamicEntity> {

    /**
     * 列表-分页
     * @param dynamicEntity
     * @return
     */
    APIResponse getList(DynamicEntity dynamicEntity);

    /**
     * 新增
     * @param dynamicEntity
     * @return
     */
    APIResponse insertDynamic(DynamicEntity dynamicEntity);

    /**
     * 删除
     * @param dynamicEntity
     * @return
     */
    APIResponse deleteDynamic(DynamicEntity dynamicEntity);

    /**
     * 修改
     * @param dynamicEntity
     * @return
     */
    APIResponse updateDynamic(DynamicEntity dynamicEntity);

}
