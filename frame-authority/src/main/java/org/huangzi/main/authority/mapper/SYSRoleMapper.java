package org.huangzi.main.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.authority.entity.SYSPermission;
import org.huangzi.main.authority.entity.SYSRole;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 10:14
 * @description: 系统-角色表mapper接口
 */
public interface SYSRoleMapper extends BaseMapper<SYSRole> {

    /**
     * 获取数据列表
     * @param page
     * @param sysRole
     * @return
     */
    List<SYSRole> list(Page<SYSRole> page, @Param("condition") SYSRole sysRole);

    /**
     * 获取数据总数
     * @return
     */
    Integer total(@Param("condition") SYSRole sysRole);

    /**
     * 根据角色id获取其对应的权限列表
     * @param roleId
     * @return
     */
    List<SYSPermission> permRoleList(@Param("condition") int roleId);

}
