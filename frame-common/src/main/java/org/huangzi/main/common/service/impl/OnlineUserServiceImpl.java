package org.huangzi.main.common.service.impl;

import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.dto.OnlineUserDto;
import org.huangzi.main.common.service.OnlineUserService;
import org.huangzi.main.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        onlineUserEntity.setLoginTime(format.format(System.currentTimeMillis()));
        return redisUtil.setValue(ConstConfig.ONLINE_KEY + userId, onlineUserEntity, 24 * 60 * 60/1000);
    }

    @Override
    public APIResponse getListOnlineUser(String condition) {
        List<String> list = redisUtil.getListKey(ConstConfig.ONLINE_KEY + "*");
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        Collections.reverse(list);
        List<OnlineUserDto> list1 = new ArrayList<>();
        OnlineUserDto onlineUserEntity1;
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
