package org.huangzi.main.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.web.entity.DictionaryEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/2/3 下午3:01
 * @description: 数据字典表 mapper 接口
 */
public interface DictionaryMapper extends BaseMapper<DictionaryEntity> {

    /**
     * 获取数据列表-分页
     * @param page
     * @param dictionaryEntity
     * @return
     */
    List<DictionaryEntity> list(Page<DictionaryEntity> page, @Param("condition") DictionaryEntity dictionaryEntity);

    /**
     * 获取数据总数
     * @return
     */
    Integer total();

}
