package org.huangzi.main.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.goods.entity.CategoryEntity;
import org.huangzi.main.goods.mapper.CategoryMapper;
import org.huangzi.main.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/3/31 下午9:30
 * @description: 分类事务层实现类
 */
@Primary
@Service("CategoryServiceImpl")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public APIResponse getAllCategory(CategoryEntity categoryEntity) {
        List<CategoryEntity> list = categoryMapper.selectList(new QueryWrapper<CategoryEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE));
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<CategoryEntity> list1 = new ArrayList<>();
        List<Integer> parentIds = new ArrayList<>();
        Integer parentId = ConstConfig.DELETE_FLAG_ZONE;
        Map<String, Object> map = new HashMap<>(2);
        if (list.size() == 0) {
            map.put(ConstConfig.DATA_LIST, list);
            map.put("parentIds", parentIds);
            return new APIResponse(map);
        }
        for (CategoryEntity categoryEntity1 : getRootNode(list, parentId)) {
            categoryEntity1 = buildTree(list, categoryEntity1);
            list1.add(categoryEntity1);
        }
        if (categoryEntity.getCategoryName() != null && !"".equals(categoryEntity.getCategoryName())) {
            List<CategoryEntity> list2 = categoryMapper.selectList(new QueryWrapper<CategoryEntity>()
                    .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                    .like("category_name", categoryEntity.getCategoryName()));
            if (list2 == null) {
                return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
            if (list2.size() == 0) {
                map.put(ConstConfig.DATA_LIST, new ArrayList<>());
                map.put("parentIds", parentIds);
                return new APIResponse(map);
            }
            List<Integer> ids = new ArrayList<>();
            for (CategoryEntity categoryEntity1 : list2) {
                ids.add(categoryEntity1.getParentId());
            }
            List<CategoryEntity> list3 = categoryMapper.selectBatchIds(ids);
            if (list3 == null) {
                return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
            for (CategoryEntity categoryEntity1 : list3) {
                parentIds.add(categoryEntity1.getId());
            }
        }
        map.put(ConstConfig.DATA_LIST, list1);
        map.put("parentIds", parentIds);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getCategory(CategoryEntity categoryEntity) {
        CategoryEntity categoryEntity1 = categoryMapper.selectById(categoryEntity.getId());
        if (categoryEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, categoryEntity1);
        return new APIResponse(map);
    }

    @Override
    public APIResponse insertCategory(CategoryEntity categoryEntity) {
        if (categoryEntity.getParentId() == null || categoryEntity.getParentId() == 0) {
            int res = categoryMapper.insert(categoryEntity);
            if (res <= 0) {
                return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
            return new APIResponse();
        }
        CategoryEntity categoryEntity1 = categoryMapper.selectById(categoryEntity.getParentId());
        if (categoryEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = categoryMapper.insert(categoryEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteCategory(CategoryEntity categoryEntity) {
        CategoryEntity categoryEntity1 = categoryMapper.selectById(categoryEntity.getId());
        if (categoryEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<CategoryEntity> list = categoryMapper.selectList(new QueryWrapper<CategoryEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("parent_id", categoryEntity.getId()));
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (list.size() > 0) {
            return new APIResponse(ConstConfig.RE_EXIST_CHILDREN_CODE, ConstConfig.RE_EXIST_CHILDREN_MESSAGE);
        }
        categoryEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = categoryMapper.updateById(categoryEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updateCategory(CategoryEntity categoryEntity) {
        CategoryEntity categoryEntity1 = categoryMapper.selectById(categoryEntity.getId());
        if (categoryEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = categoryMapper.updateById(categoryEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    /**
     * 获取根节点
     * @param list
     * @param parentId
     * @return
     */
    private List<CategoryEntity> getRootNode(List<CategoryEntity> list, Integer parentId) {
        List<CategoryEntity> list1 = new ArrayList<>();
        for (CategoryEntity categoryEntity : list) {
            if (categoryEntity.getParentId().equals(parentId)) {
                list1.add(categoryEntity);
            }
        }
        return list1;
    }

    /**
     * 构建树形结构
     * @param list
     * @param categoryEntity
     * @return
     */
    private CategoryEntity buildTree(List<CategoryEntity> list, CategoryEntity categoryEntity) {
        List<CategoryEntity> list1 = new ArrayList<>();
        for (CategoryEntity categoryEntity1 : list) {
            if (categoryEntity1.getParentId().equals(categoryEntity.getId())) {
                list1.add(buildTree(list, categoryEntity1));
            }
        }
        categoryEntity.setChildrenList(list1);
        return categoryEntity;
    }

}
