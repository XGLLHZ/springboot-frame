package org.huangzi.main.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.goods.entity.AttributeValueEntity;
import org.huangzi.main.goods.mapper.AttributeValueMapper;
import org.huangzi.main.goods.service.AttributeValueService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 上午11:00
 * @description: 属性值事务层实现类
 */
@Primary
@Service("AttributeValueServiceImpl")
public class AttributeValueServiceImpl extends ServiceImpl<AttributeValueMapper, AttributeValueEntity> implements AttributeValueService {

}
