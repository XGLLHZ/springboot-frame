package org.huangzi.main.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.web.entity.GroupEntity;
import org.huangzi.main.common.utils.APIResponse;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 下午2:20
 * @description: 用户群组事务层接口
 */
public interface GroupService extends IService<GroupEntity> {

    /**
     * 群组列表-全部
     * @param groupEntity
     * @return
     */
    APIResponse getList(GroupEntity groupEntity);

    /**
     * 详情
     * @param groupEntity
     * @return
     */
    APIResponse getGroup(GroupEntity groupEntity);

    /**
     * 我的群组
     * @param groupEntity
     * @return
     */
    APIResponse getMyGroup(GroupEntity groupEntity);

}
