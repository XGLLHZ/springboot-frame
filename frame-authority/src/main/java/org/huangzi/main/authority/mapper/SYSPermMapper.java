package org.huangzi.main.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.authority.entity.SYSPermission;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 10:16
 * @description: 系统-权限表mapper接口
 */
public interface SYSPermMapper extends BaseMapper<SYSPermission> {

    /**
     * 获取数据列表
     * @param page
     * @param condition
     * @return
     */
    List<SYSPermission> list(Page<SYSPermission> page, @Param("condition") SYSPermission condition);

    /**
     * 获取数据总数
     * @return
     */
    Integer total();

    /**
     * 获取所有权限及其对应的角色
     * @return
     */
    List<SYSPermission> allUrlRole();

    /**
     * 根据权限即资源（url）获取详情
     * @param permUrl
     * @return
     */
    SYSPermission getByUrl(@Param("condition") String permUrl);

    List<SYSPermission> getAllPerm();

}
