package org.huangzi.main.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.authority.entity.SYSRole;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 11:37
 * @description: 系统-角色事务层
 */
public interface SYSRoleService extends IService<SYSRole> {

    /**
     * 获取数据列表
     * @param sysRole
     * @return
     */
    APIResponse list(SYSRole sysRole);

    /**
     * 详情
     * @param sysRole
     * @return
     */
    APIResponse get(SYSRole sysRole);

    /**
     * 新增
     * @param sysRole
     * @return
     */
    APIResponse insert(SYSRole sysRole);

    /**
     * 删除
     * @param sysRole
     * @return
     */
    APIResponse delete(SYSRole sysRole);

    /**
     * 修改
     * @param sysRole
     * @return
     */
    APIResponse update(SYSRole sysRole);

}
