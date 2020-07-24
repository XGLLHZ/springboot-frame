package org.huangzi.main.common.service;

import org.huangzi.main.common.entity.LogEntity;
import org.huangzi.main.common.utils.APIResponse;

/**
 * @author: XGLLHZ
 * @date: 2020/7/20 下午3:09
 * @description: 日志 es 事务层接口
 */
public interface LogEsService {

    /**
     * 向 es 中导入数据
     * @param logEntity
     * @return
     */
    APIResponse importLog(LogEntity logEntity);

    /**
     * 从 es 中查询数据
     * @param logEntity
     * @return
     */
    APIResponse getList(LogEntity logEntity);

}
