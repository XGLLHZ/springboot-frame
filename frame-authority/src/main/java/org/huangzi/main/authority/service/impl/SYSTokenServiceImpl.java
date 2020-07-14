package org.huangzi.main.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.TokenUtil;
import org.huangzi.main.authority.entity.SYSToken;
import org.huangzi.main.authority.mapper.SYSTokenMapper;
import org.huangzi.main.authority.service.SYSTokenService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: XGLLHZ
 * @date: 2019/9/6 10:34
 * @description: token事务层-实现类
 */
@Service
@Primary
@Component("sysTokenService")
public class SYSTokenServiceImpl extends ServiceImpl<SYSTokenMapper, SYSToken> implements SYSTokenService {

}
