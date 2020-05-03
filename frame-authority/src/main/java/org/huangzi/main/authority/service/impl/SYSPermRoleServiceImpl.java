package org.huangzi.main.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.authority.entity.SYSPermRole;
import org.huangzi.main.authority.mapper.SYSPermRoleMapper;
import org.huangzi.main.authority.service.SYSPermRoleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: XGLLHZ
 * @date: 2020/4/30 下午10:20
 * @description: 权限-角色事务层实现类
 */
@Primary
@Service("SYSPermRoleServiceImpl")
public class SYSPermRoleServiceImpl extends ServiceImpl<SYSPermRoleMapper, SYSPermRole> implements SYSPermRoleService {

}
