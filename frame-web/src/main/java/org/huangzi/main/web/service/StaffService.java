package org.huangzi.main.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.StaffEntity;


/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:39
 * @description: 员工类事务层接口
 */
public interface StaffService extends IService<StaffEntity> {

    /**
     * 获取数据列表-分页
     * @param staffEntity
     * @return
     */
    APIResponse staffList(StaffEntity staffEntity);

    /**
     * 获取单个数据
     * @param staffEntity
     * @return
     */
    APIResponse getStaff(StaffEntity staffEntity);

    /**
     * 新增
     * @param staffEntity
     * @return
     */
    APIResponse insertStaff(StaffEntity staffEntity);

    /**
     * 删除
     * @param staffEntity
     * @return
     */
    APIResponse deleteStaff(StaffEntity staffEntity);

    /**
     * 修改
     * @param staffEntity
     * @return
     */
    APIResponse updateStaff(StaffEntity staffEntity);

}
