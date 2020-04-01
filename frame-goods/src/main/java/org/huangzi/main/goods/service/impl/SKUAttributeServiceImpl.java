package org.huangzi.main.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.goods.entity.SKUAttributeEntity;
import org.huangzi.main.goods.mapper.SKUAttributeMapper;
import org.huangzi.main.goods.service.SKUAttributeService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 下午10:34
 * @description: sku 事务层实现类
 */
@Primary
@Service("SKUAttributeServiceImpl")
public class SKUAttributeServiceImpl extends ServiceImpl<SKUAttributeMapper, SKUAttributeEntity> implements SKUAttributeService {

}
