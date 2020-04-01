package org.huangzi.main.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.AttributeEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 上午9:36
 * @description: 类别属性事务层接口
 */
public interface AttributeService extends IService<AttributeEntity> {

    /**
     * 列表-分页
     * @param attributeEntity
     * @return
     */
    APIResponse getList(AttributeEntity attributeEntity);

    /**
     * 详情/根据属性 id 获取属性值
     * @param attributeEntity
     * @return
     */
    APIResponse getAttribute(AttributeEntity attributeEntity);

    /**
     * 新增
     * @param attributeEntity
     * @return
     */
    APIResponse insertAttribute(AttributeEntity attributeEntity);

    /**
     * 删除
     * @param attributeEntity
     * @return
     */
    APIResponse deleteAttribute(AttributeEntity attributeEntity);

    /**
     * 修改
     * @param attributeEntity
     * @return
     */
    APIResponse updateAttribute(AttributeEntity attributeEntity);

    /**
     * 根据类别 id 查找属性
     * @param attributeEntity
     * @return
     */
    APIResponse getAttributeByCategoryId(AttributeEntity attributeEntity);

}
