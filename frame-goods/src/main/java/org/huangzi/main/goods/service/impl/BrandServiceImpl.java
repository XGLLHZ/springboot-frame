package org.huangzi.main.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.goods.entity.BrandEntity;
import org.huangzi.main.goods.mapper.BrandMapper;
import org.huangzi.main.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/3/31 下午4:00
 * @description: 品牌 事务层实现类
 */
@Primary
@Service("BrandServiceImpl")
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandEntity> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public APIResponse getList(BrandEntity brandEntity) {
        Page<BrandEntity> page = new Page<>(brandEntity.getCurrentPage(), brandEntity.getPageSize());
        List<BrandEntity> list = brandMapper.getList(page, brandEntity);
        Integer total = brandMapper.getTotal(brandEntity);
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
    public APIResponse getBrand(BrandEntity brandEntity) {
        BrandEntity brandEntity1 = brandMapper.selectById(brandEntity.getId());
        if (brandEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, brandEntity1);
        return new APIResponse(map);
    }

    @Override
    public APIResponse insertBrand(BrandEntity brandEntity) {
        BrandEntity brandEntity1 = brandMapper.selectOne(new QueryWrapper<BrandEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("brand_code", brandEntity.getBrandCode()));
        if (brandEntity1 != null) {
            return new APIResponse(ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_CODE,
                    ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        int res = brandMapper.insert(brandEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteBrand(BrandEntity brandEntity) {
        BrandEntity brandEntity1 = brandMapper.selectById(brandEntity.getId());
        if (brandEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        brandEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = brandMapper.updateById(brandEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updateBrand(BrandEntity brandEntity) {
        BrandEntity brandEntity1 = brandMapper.selectById(brandEntity.getId());
        if (brandEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = brandMapper.updateById(brandEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse getAllBrand(BrandEntity brandEntity) {
        List<BrandEntity> list = brandMapper.selectList(new QueryWrapper<BrandEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .like("brand_name", brandEntity.getBrandName()));
        Map<String, Object> map = new HashMap<>(1);
        if (list == null) {
            map.put(ConstConfig.DATA_LIST, new ArrayList<>());
            return new APIResponse(map);
        }
        map.put(ConstConfig.DATA_LIST, list);
        return new APIResponse(map);
    }

}
