package org.huangzi.main.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.web.mapper.DepartmentMapper;
import org.huangzi.main.web.mapper.PostMapper;
import org.huangzi.main.web.entity.DepartmentEntity;
import org.huangzi.main.web.entity.PostEntity;
import org.huangzi.main.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:43
 * @description: 岗位类实现类
 */
@Service
@Primary
public class PostServiceImpl extends ServiceImpl<PostMapper, PostEntity> implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public APIResponse postList(PostEntity postEntity) {
        Page<PostEntity> page = new Page<>(postEntity.getCurrentPage(), postEntity.getPageSize());
        List<PostEntity> list = postMapper.list(page, postEntity);
        Integer total = postMapper.total();
        Map<String, Object> map = new HashMap<>();
        if (list == null) {
            map.put("dataList", new ArrayList<>());
            map.put("total", 0);
        }
        map.put("dataList", list);
        map.put("total", total);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getPost(PostEntity postEntity) {
        PostEntity postEntity1 = postMapper.selectById(postEntity.getId());
        if (postEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("dataInfo", postEntity1);
        return new APIResponse(map);
    }

    @Override
    public APIResponse insertPost(PostEntity postEntity) {
        PostEntity postEntity1 = postMapper.selectOne(new QueryWrapper<PostEntity>()
                .eq("delete_flag", 0)
                .eq("post_code", postEntity.getPostCode()));
        if (postEntity1 != null) {
            return new APIResponse(ConstConfig.RE_POST_CODE_ALREADY_EXIST_ERROR_CODE,
                    ConstConfig.RE_POST_CODE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        DepartmentEntity departmentEntity = departmentMapper.selectById(postEntity.getParentId());
        if (departmentEntity == null) {
            return new APIResponse(ConstConfig.RE_DEPARTMENT_NO_EXIST_ERROR_CODE, ConstConfig.RE_DEPARTMENT_NO_EXIST_ERROR_MESSAGE);
        }
        Integer res = postMapper.insert(postEntity);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deletePost(PostEntity postEntity) {
        PostEntity postEntity1 = postMapper.selectById(postEntity.getId());
        if (postEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        postEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        Integer res = postMapper.updateById(postEntity1);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updatePost(PostEntity postEntity) {
        PostEntity postEntity1 = postMapper.selectById(postEntity.getId());
        if (postEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        PostEntity postEntity2 = postMapper.selectOne(new QueryWrapper<PostEntity>()
                .eq("delete_flag", 0)
                .eq("post_code", postEntity.getPostCode()));
        if (postEntity2 != null) {
            return new APIResponse(ConstConfig.RE_POST_CODE_ALREADY_EXIST_ERROR_CODE,
                    ConstConfig.RE_POST_CODE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        DepartmentEntity departmentEntity = departmentMapper.selectById(postEntity.getParentId());
        if (departmentEntity == null) {
            return new APIResponse(ConstConfig.RE_DEPARTMENT_NO_EXIST_ERROR_CODE, ConstConfig.RE_DEPARTMENT_NO_EXIST_ERROR_MESSAGE);
        }
        Integer res = postMapper.updateById(postEntity1);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

}
