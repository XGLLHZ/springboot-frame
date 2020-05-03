package org.huangzi.main.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.authority.entity.SYSPermission;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 11:38
 * @description: 系统-权限事务层
 */
public interface SYSPermService extends IService<SYSPermission> {

    /**
     * 获取数据列表
     * @param sysPermission
     * @return
     */
    APIResponse list(SYSPermission sysPermission);

    /**
     * 权限详情
     * @param sysPermission
     * @return
     */
    APIResponse get(SYSPermission sysPermission);

    /**
     * 新增
     * @param sysPermission
     * @return
     */
    APIResponse insert(SYSPermission sysPermission);

    /**
     * 删除
     * @param sysPermission
     * @return
     */
    APIResponse delete(SYSPermission sysPermission);

    /**
     * 修改
     * @param sysPermission
     * @return
     */
    APIResponse update(SYSPermission sysPermission);

    /**
     * 根据类型获取父级
     * @param sysPermission
     * @return
     */
    APIResponse getPermByPermType(SYSPermission sysPermission);

    /**
     * 构建菜单树
     * @param sysPermission
     * @return
     */
    APIResponse buildMenu(SYSPermission sysPermission);

    /**
     * 权限树
     * @return
     */
    APIResponse getPermTree();

    /**
     * 全部列表
     * @return
     */
    APIResponse getAllPerm();

}
