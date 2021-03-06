package org.huangzi.main.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.web.entity.GroupUserEntity;
import org.huangzi.main.web.mapper.GroupUserMapper;
import org.huangzi.main.web.service.GroupUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 下午2:29
 * @description: 群组-用户 事务层实现类
 */
@Primary
@Service
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUserEntity> implements GroupUserService {

}
