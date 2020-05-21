package org.huangzi.main.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.entity.GroupEntity;
import org.huangzi.main.common.mapper.GroupMapper;
import org.huangzi.main.common.service.GroupService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public APIResponse getList(GroupEntity groupEntity) {
        List<GroupEntity> list = groupMapper.selectList(new QueryWrapper<GroupEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE));
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put(ConstConfig.DATA_LIST, list);
        map.put(ConstConfig.TOTAL, list.size());
        return new APIResponse(map);
    }

    @Override
    public APIResponse getGroup(GroupEntity groupEntity) {
        return null;
    }

    @Override
    public APIResponse getMyGroup(GroupEntity groupEntity) {
        return null;
    }

}
