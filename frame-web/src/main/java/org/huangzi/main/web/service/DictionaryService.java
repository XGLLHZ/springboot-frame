package org.huangzi.main.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.DictionaryEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/2/3 下午3:02
 * @description: 数据字典事务层接口
 */
public interface DictionaryService extends IService<DictionaryEntity> {

    /**
     * 获取数据列表-分页
     * @param dictionaryEntity
     * @return
     */
    APIResponse dictList(DictionaryEntity dictionaryEntity);

    /**
     * 获取单个数据详情
     * @param dictionaryEntity
     * @return
     */
    APIResponse getDict(DictionaryEntity dictionaryEntity);

    /**
     * 新增
     * @param dictionaryEntity
     * @return
     */
    APIResponse insertDict(DictionaryEntity dictionaryEntity);

    /**
     * 删除
     * @param dictionaryEntity
     * @return
     */
    APIResponse deleteDict(DictionaryEntity dictionaryEntity);

    /**
     * 修改
     * @param dictionaryEntity
     * @return
     */
    APIResponse updateDict(DictionaryEntity dictionaryEntity);

}
