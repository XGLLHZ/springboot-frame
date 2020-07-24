package org.huangzi.main.common.mapper;

import org.huangzi.main.common.dto.LogEsDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: XGLLHZ
 * @date: 2020/7/20 下午3:04
 * @description: 日志 es repository
 */
public interface LogRepository extends ElasticsearchRepository<LogEsDto, Integer> {

}
