package org.huangzi.main.common.service.impl;

import org.huangzi.main.common.dto.OnlineUserDto;
import org.huangzi.main.common.service.OnlineUserService;
import org.huangzi.main.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: XGLLHZ
 * @date: 2020/2/4 下午5:35
 * @description: 在线用户事务层实现类
 */
@Service
@Primary
public class OnlineUserServiceImpl implements OnlineUserService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean saveOnlineUserInfo(HttpServletRequest request, Integer userId, String userName, Integer userType) {
        OnlineUserDto onlineUserEntity = new OnlineUserDto();
        if (userId == null && userName == null && userType == null) {
            return false;
        }
        onlineUserEntity.setId(userId);
        onlineUserEntity.setUserName(userName);
        onlineUserEntity.setUserType(userType);
        String ip = StringUtil.getUserIp(request);
        onlineUserEntity.setLoginIp(ip);
        onlineUserEntity.setLoginAddress(StringUtil.getAddressByIp(ip));
        onlineUserEntity.setLoginBrowser(StringUtil.getUserBrowser(request));
        Long time = System.currentTimeMillis();
        onlineUserEntity.setLoginTime(time.toString());
        boolean res = redisUtil.setValue(ConstConfig.ONLINE_KEY + userId, onlineUserEntity, TokenUtil.EXPIRE_TIME/1000);
        if (!res) {
            return false;
        }
        return true;
    }

    @Override
    public APIResponse getListOnlineUser(String condition) {
        List<String> list = redisUtil.getListKey(ConstConfig.ONLINE_KEY + "*");
        if (list.size() < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        Collections.reverse(list);
        List<OnlineUserDto> list1 = new ArrayList<>();
        OnlineUserDto onlineUserEntity1 = null;
        for (String key : list) {
            onlineUserEntity1 = (OnlineUserDto) redisUtil.getValue(key);
            if (onlineUserEntity1 != null) {
                if (StringUtil.isNotEmpty(condition)) {
                    if (onlineUserEntity1.toString().contains(condition)) {
                        list1.add(onlineUserEntity1);
                    }
                } else {
                    list1.add(onlineUserEntity1);
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", list1);
        return new APIResponse(map);
    }

}
