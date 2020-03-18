package org.huangzi.main.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.PostEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:39
 * @description: 岗位类事务层接口
 */
public interface PostService extends IService<PostEntity> {

    /**
     * 获取数据总数-分页
     * @param postEntity
     * @return
     */
    APIResponse postList(PostEntity postEntity);

    /**
     * 获取单个数据
     * @param postEntity
     * @return
     */
    APIResponse getPost(PostEntity postEntity);

    /**
     * 新增
     * @param postEntity
     * @return
     */
    APIResponse insertPost(PostEntity postEntity);

    /**
     * 删除
     * @param postEntity
     * @return
     */
    APIResponse deletePost(PostEntity postEntity);

    /**
     * 修改
     * @param postEntity
     * @return
     */
    APIResponse updatePost(PostEntity postEntity);

}
