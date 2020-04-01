package org.huangzi.main.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.goods.entity.AttributeEntity;
import org.huangzi.main.goods.entity.AttributeValueEntity;
import org.huangzi.main.goods.entity.CategoryEntity;
import org.huangzi.main.goods.mapper.AttributeMapper;
import org.huangzi.main.goods.mapper.AttributeValueMapper;
import org.huangzi.main.goods.mapper.CategoryMapper;
import org.huangzi.main.goods.service.AttributeService;
import org.huangzi.main.goods.service.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 上午9:44
 * @description: 类别属性事务层实现类
 */
@Primary
@Service("AttributeServiceImpl")
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, AttributeEntity> implements AttributeService {

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private AttributeValueMapper attributeValueMapper;

    @Autowired
    private AttributeValueService attributeValueService;

    @Override
    public APIResponse getList(AttributeEntity attributeEntity) {
        Page<AttributeEntity> page = new Page<>(attributeEntity.getCurrentPage(), attributeEntity.getPageSize());
        List<AttributeEntity> list = attributeMapper.getList(page, attributeEntity);
        Integer total = attributeMapper.getTotal(attributeEntity);
        Map<String, Object> map = new HashMap<>(2);
        if (list == null || total == null) {
            map.put(ConstConfig.DATA_LIST, new ArrayList<>());
            map.put(ConstConfig.TOTAL, 0);
            return new APIResponse(map);
        }
        map.put(ConstConfig.DATA_LIST, list);
        map.put(ConstConfig.TOTAL, total);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getAttribute(AttributeEntity attributeEntity) {
        AttributeEntity attributeEntity1 = attributeMapper.selectById(attributeEntity.getId());
        if (attributeEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<AttributeValueEntity> list = attributeValueMapper.selectList(new QueryWrapper<AttributeValueEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("attribute_id", attributeEntity.getId()));
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        attributeEntity1.setAttributeValueList(list);
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, attributeEntity1);
        return new APIResponse(map);
    }

    @Override
    @Transactional
    public APIResponse insertAttribute(AttributeEntity attributeEntity) {
        AttributeEntity attributeEntity1 = attributeMapper.selectOne(new QueryWrapper<AttributeEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("attribute_code", attributeEntity.getAttributeCode()));
        if (attributeEntity1 != null) {
            return new APIResponse(ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_CODE, ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        CategoryEntity categoryEntity = categoryMapper.selectById(attributeEntity.getCategoryId());
        if (categoryEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = attributeMapper.insert(attributeEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (attributeEntity.getAttributeValueList() != null && attributeEntity.getAttributeValueList().size() > 0) {
            for (AttributeValueEntity attributeValueEntity : attributeEntity.getAttributeValueList()) {
                attributeValueEntity.setAttributeId(attributeEntity.getId());
            }
            boolean result = attributeValueService.saveBatch(attributeEntity.getAttributeValueList());
            if (!result) {
                return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse deleteAttribute(AttributeEntity attributeEntity) {
        AttributeEntity attributeEntity1 = attributeMapper.selectById(attributeEntity.getId());
        if (attributeEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        attributeEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = attributeMapper.updateById(attributeEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<AttributeValueEntity> list = attributeValueMapper.selectList(new QueryWrapper<AttributeValueEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("attribute_id", attributeEntity.getId()));
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (list.size() > 0) {
            for (AttributeValueEntity attributeValueEntity : list) {
                attributeValueEntity.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
            }
            boolean result = attributeValueService.updateBatchById(list);
            if (!result) {
                return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse updateAttribute(AttributeEntity attributeEntity) {
        AttributeEntity attributeEntity1 = attributeMapper.selectById(attributeEntity.getId());
        if (attributeEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = attributeMapper.updateById(attributeEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<AttributeValueEntity> list = attributeValueMapper.selectList(new QueryWrapper<AttributeValueEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("attribute_id", attributeEntity.getId()));
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (list.size() > 0) {
            for (AttributeValueEntity attributeValueEntity : list) {
                attributeValueEntity.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
            }
            boolean result = attributeValueService.updateBatchById(list);
            if (!result) {
                return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
        }
        if (attributeEntity.getAttributeValueList() != null && attributeEntity.getAttributeValueList().size() > 0) {
            for (AttributeValueEntity attributeValueEntity : attributeEntity.getAttributeValueList()) {
                attributeValueEntity.setAttributeId(attributeEntity.getId());
            }
            boolean result = attributeValueService.saveBatch(attributeEntity.getAttributeValueList());
            if (!result) {
                return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
        }
        return new APIResponse();
    }

    @Override
    public APIResponse getAttributeByCategoryId(AttributeEntity attributeEntity) {
        CategoryEntity categoryEntity = categoryMapper.selectById(attributeEntity.getCategoryId());
        if (categoryEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<AttributeEntity> list = attributeMapper.selectList(new QueryWrapper<AttributeEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("category_id", attributeEntity.getCategoryId()));
        Map<String, Object> map = new HashMap<>(1);
        if (list == null) {
            map.put(ConstConfig.DATA_LIST, new ArrayList<>());
            return new APIResponse(map);
        }
        map.put(ConstConfig.DATA_LIST, list);
        return new APIResponse(map);
    }

}
