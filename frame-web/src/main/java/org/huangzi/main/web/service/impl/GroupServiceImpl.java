package org.huangzi.main.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.web.entity.GroupEntity;
import org.huangzi.main.web.entity.GroupUserEntity;
import org.huangzi.main.web.mapper.GroupMapper;
import org.huangzi.main.web.mapper.GroupUserMapper;
import org.huangzi.main.web.service.GroupService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 下午2:28
 * @description: 群组事务层实现类
 */
@Primary
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupEntity> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GroupUserMapper groupUserMapper;

    @Override
    public APIResponse getList(GroupEntity groupEntity) {
        List<GroupEntity> list = groupMapper.selectList(new QueryWrapper<GroupEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE));
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put(ConstConfig.DATA_LIST, list);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getGroup(GroupEntity groupEntity) {
        GroupEntity groupEntity1 = groupMapper.selectById(groupEntity.getId());
        if (groupEntity1 == null) {
            throw new ExceptionDto(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<GroupUserEntity> list = groupUserMapper.selectList(new QueryWrapper<GroupUserEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("group_id", groupEntity.getId()));
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        long onlineNum = list.stream().filter(s -> s.getOnlineStatus() == 1).count();
        long offlineNum = list.stream().filter(s -> s.getOnlineStatus() == 2).count();
        groupEntity1.setOnlineNum((int) onlineNum);
        groupEntity1.setOfflineNum((int) offlineNum);
        groupEntity1.setUserList(list);
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_INFO, groupEntity1);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getMyGroup(GroupEntity groupEntity) {
        List<GroupUserEntity> list = groupUserMapper.selectList(new QueryWrapper<GroupUserEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("user_id", groupEntity.getUserId()));
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<GroupEntity> list1 = new ArrayList<>();
        if (list.size() > 0) {
            List<Integer> ids = list.stream().map(GroupUserEntity::getGroupId).collect(Collectors.toList());
            list1 = groupMapper.selectBatchIds(ids);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_LIST, list1);
        return new APIResponse(map);
    }

}
