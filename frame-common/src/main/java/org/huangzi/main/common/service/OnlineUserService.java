package org.huangzi.main.common.service;

import org.huangzi.main.common.utils.APIResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XGLLHZ
 * @date: 2020/2/4 下午5:32
 * @description: 在线用户事务层接口
 */
public interface OnlineUserService  {

    /**
     * 保存在线用户信息
     * @param request
     * @param userId
     * @param userName
     * @param userType
     * @return
     */
    boolean saveOnlineUserInfo(HttpServletRequest request, Integer userId, String userName, Integer userType);

    /**
     * 获取在线用户列表
     * @param condition
     * @return
     */
    APIResponse getListOnlineUser(String condition);

}
