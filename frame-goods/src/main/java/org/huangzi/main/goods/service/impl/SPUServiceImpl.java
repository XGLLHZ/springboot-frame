package org.huangzi.main.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.goods.entity.BrandEntity;
import org.huangzi.main.goods.entity.CategoryEntity;
import org.huangzi.main.goods.entity.SKUEntity;
import org.huangzi.main.goods.entity.SPUEntity;
import org.huangzi.main.goods.mapper.*;
import org.huangzi.main.goods.service.SPUService;
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
 * @date: 2020/4/1 下午3:27
 * @description: spu 事务层实现类
 */
@Primary
@Service("SPUServiceImpl")
public class SPUServiceImpl extends ServiceImpl<SPUMapper, SPUEntity> implements SPUService {

    @Autowired
    private SPUMapper spuMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SPUCategoryMapper spuCategoryMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SKUMapper skuMapper;

    @Override
    public APIResponse getList(SPUEntity spuEntity) {
        Page<SPUEntity> page = new Page<>(spuEntity.getCurrentPage(), spuEntity.getPageSize());
        List<SPUEntity> list = spuMapper.getList(page, spuEntity);
        Integer total = spuMapper.getTotal(spuEntity);
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
    public APIResponse getSPU(SPUEntity spuEntity) {
        SPUEntity spuEntity1 = spuMapper.selectById(spuEntity.getId());
        if (spuEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, spuEntity1);
        return new APIResponse(map);
    }

    @Override
    @Transactional
    public APIResponse insertSPU(SPUEntity spuEntity) {
        SPUEntity spuEntity1 = spuMapper.selectOne(new QueryWrapper<SPUEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("spu_code", spuEntity.getSpuCode()));
        if (spuEntity1 != null) {
            return new APIResponse(ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_CODE, ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        BrandEntity brandEntity = brandMapper.selectById(spuEntity.getBrandId());
        if (brandEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        spuEntity.setBrandName(brandEntity.getBrandName());
        List<CategoryEntity> list = categoryMapper.selectBatchIds(spuEntity.getIds());
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        String categoryIds = "";
        String categories = "";
        for (CategoryEntity categoryEntity : list) {
            categoryIds = categoryIds + categoryEntity.getId() + "-";
            categories = categories + categoryEntity.getCategoryName() + "-";
        }
        spuEntity.setCategoryIds(categoryIds.substring(0, categoryIds.length() - 1));
        spuEntity.setCategories(categories.substring(0, categories.length() - 1));
        int res = spuMapper.insert(spuEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteSPU(SPUEntity spuEntity) {
        SPUEntity spuEntity1 = spuMapper.selectById(spuEntity.getId());
        if (spuEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<SKUEntity> list = skuMapper.selectList(new QueryWrapper<SKUEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("spu_id", spuEntity.getIds()));
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (list.size() > 0) {
            return new APIResponse(ConstConfig.RE_EXIST_CHILDREN_CODE, ConstConfig.RE_EXIST_CHILDREN_MESSAGE);
        }
        spuEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = spuMapper.updateById(spuEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updateSPU(SPUEntity spuEntity) {
        SPUEntity spuEntity1 = spuMapper.selectById(spuEntity.getId());
        if (spuEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        BrandEntity brandEntity = brandMapper.selectById(spuEntity.getBrandId());
        if (brandEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        spuEntity.setBrandName(brandEntity.getBrandName());
        List<CategoryEntity> list = categoryMapper.selectBatchIds(spuEntity.getIds());
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        String categoryIds = "";
        String categories = "";
        for (CategoryEntity categoryEntity : list) {
            categoryIds = categoryIds + categoryEntity.getId() + "-";
            categories = categories + categoryEntity.getCategoryName() + "-";
        }
        spuEntity.setCategoryIds(categoryIds.substring(0, categoryIds.length() - 1));
        spuEntity.setCategories(categories.substring(0, categories.length() - 1));
        int res = spuMapper.updateById(spuEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

}
