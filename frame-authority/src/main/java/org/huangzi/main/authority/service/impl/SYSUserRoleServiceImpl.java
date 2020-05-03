package org.huangzi.main.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.authority.entity.SYSUserRole;
import org.huangzi.main.authority.mapper.SYSUserRoleMapper;
import org.huangzi.main.authority.service.SYSUserRoleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: XGLLHZ
 * @date: 2020/4/30 下午3:02
 * @description: 用户-角色事务层实现类
 */
@Primary
@Service("SYSUserRoleServiceImpl")
public class SYSUserRoleServiceImpl extends ServiceImpl<SYSUserRoleMapper, SYSUserRole> implements SYSUserRoleService {

}
