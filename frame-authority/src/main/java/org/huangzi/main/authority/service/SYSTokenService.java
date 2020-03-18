package org.huangzi.main.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.authority.entity.SYSToken;

/**
 * @author: XGLLHZ
 * @date: 2019/9/6 10:31
 * @description: token事务层
 */
public interface SYSTokenService extends IService<SYSToken> {

    /**
     * 创建token
     * @param userId
     * @return
     */
    String createToken(int userId);

    /**
     * 验证token
     * @param token
     * @return 1001：通过；1002：未通过（过期）
     */
    Integer checkToken(String token);

}
