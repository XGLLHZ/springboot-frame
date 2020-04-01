package org.huangzi.main.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.goods.entity.*;
import org.huangzi.main.goods.mapper.*;
import org.huangzi.main.goods.service.SKUAttributeService;
import org.huangzi.main.goods.service.SKUService;
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
 * @date: 2020/4/1 下午5:27
 * @description: sku 事务层实现类
 */
@Primary
@Service("SKUServiceImpl")
public class SKUServiceImpl extends ServiceImpl<SKUMapper, SKUEntity> implements SKUService {

    @Autowired
    private SKUMapper skuMapper;

    @Autowired
    private SPUMapper spuMapper;

    @Autowired
    private SKUAttributeMapper skuAttributeMapper;

    @Autowired
    private SKUAttributeService skuAttributeService;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private AttributeValueMapper attributeValueMapper;

    @Override
    public APIResponse getList(SKUEntity skuEntity) {
        Page<SKUEntity> page = new Page<>(skuEntity.getCurrentPage(), skuEntity.getPageSize());
        List<SKUEntity> list = skuMapper.getList(page, skuEntity);
        Integer total = skuMapper.getTotal(skuEntity);
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
    public APIResponse getSKU(SKUEntity skuEntity) {
        SKUEntity skuEntity1 = skuMapper.selectById(skuEntity.getId());
        if (skuEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<SKUAttributeEntity> list = skuAttributeMapper.selectList(new QueryWrapper<SKUAttributeEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("sku_id", skuEntity.getId()));
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        skuEntity1.setAttributeList(list);
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, skuEntity1);
        return new APIResponse(map);
    }

    @Override
    @Transactional
    public APIResponse insertSKU(SKUEntity skuEntity) {
        SKUEntity skuEntity1 = skuMapper.selectOne(new QueryWrapper<SKUEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("sku_code", skuEntity.getSkuCode()));
        if (skuEntity1 != null) {
            return new APIResponse(ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_CODE, ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        SPUEntity spuEntity = spuMapper.selectById(skuEntity.getSpuId());
        if (spuEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        skuEntity.setSpuName(spuEntity.getSpuName());
        int res = skuMapper.insert(skuEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SKUAttributeEntity> list = skuEntity.getAttributeList();
        List<Integer> ids = new ArrayList<>();
        List<Integer> ids1 = new ArrayList<>();
        for (SKUAttributeEntity skuAttributeEntity : list) {
            skuAttributeEntity.setSkuId(skuEntity.getId());
            ids.add(skuAttributeEntity.getAttributeId());
            ids1.add(skuAttributeEntity.getAttributeValueId());
        }
        List<AttributeEntity> list1 = attributeMapper.selectBatchIds(ids);
        List<AttributeValueEntity> list2 = attributeValueMapper.selectBatchIds(ids1);
        if (list1 == null || list2 == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        for (SKUAttributeEntity skuAttributeEntity : list) {
            for (AttributeEntity attributeEntity : list1) {
                if (skuAttributeEntity.getAttributeId().equals(attributeEntity.getId())) {
                    skuAttributeEntity.setAttributeName(attributeEntity.getAttributeName());
                }
            }
            for (AttributeValueEntity attributeValueEntity : list2) {
                if (skuAttributeEntity.getAttributeValueId().equals(attributeValueEntity.getId())) {
                    skuAttributeEntity.setAttributeValue(attributeValueEntity.getAttributeValue());
                }
            }
        }
        boolean result = skuAttributeService.saveBatch(list);
        if (!result) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse deleteSKU(SKUEntity skuEntity) {
        SKUEntity skuEntity1 = skuMapper.selectById(skuEntity.getId());
        if (skuEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        skuEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = skuMapper.updateById(skuEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SKUAttributeEntity> list = skuAttributeMapper.selectList(new QueryWrapper<SKUAttributeEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("sku_id", skuEntity.getId()));
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        for (SKUAttributeEntity skuAttributeEntity : list) {
            skuAttributeEntity.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        }
        boolean result = skuAttributeService.updateBatchById(list);
        if (!result) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse updateSKU(SKUEntity skuEntity) {
        SKUEntity skuEntity1 = skuMapper.selectById(skuEntity.getId());
        if (skuEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = skuMapper.updateById(skuEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SKUAttributeEntity> lists = skuAttributeMapper.selectList(new QueryWrapper<SKUAttributeEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("sku_id", skuEntity.getId()));
        if (lists == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        for (SKUAttributeEntity skuAttributeEntity : lists) {
            skuAttributeEntity.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        }
        boolean result = skuAttributeService.updateBatchById(lists);
        if (!result) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SKUAttributeEntity> list = skuEntity.getAttributeList();
        List<Integer> ids = new ArrayList<>();
        List<Integer> ids1 = new ArrayList<>();
        for (SKUAttributeEntity skuAttributeEntity : list) {
            skuAttributeEntity.setSkuId(skuEntity.getId());
            ids.add(skuAttributeEntity.getAttributeId());
            ids1.add(skuAttributeEntity.getAttributeValueId());
        }
        List<AttributeEntity> list1 = attributeMapper.selectBatchIds(ids);
        List<AttributeValueEntity> list2 = attributeValueMapper.selectBatchIds(ids1);
        if (list1 == null || list2 == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        for (SKUAttributeEntity skuAttributeEntity : list) {
            for (AttributeEntity attributeEntity : list1) {
                if (skuAttributeEntity.getAttributeId().equals(attributeEntity.getId())) {
                    skuAttributeEntity.setAttributeName(attributeEntity.getAttributeName());
                }
            }
            for (AttributeValueEntity attributeValueEntity : list2) {
                if (skuAttributeEntity.getAttributeValueId().equals(attributeValueEntity.getId())) {
                    skuAttributeEntity.setAttributeValue(attributeValueEntity.getAttributeValue());
                }
            }
        }
        result = skuAttributeService.saveBatch(list);
        if (!result) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

}
