package org.huangzi.main.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.dto.LogEsDto;
import org.huangzi.main.common.entity.LogEntity;
import org.huangzi.main.common.mapper.LogMapper;
import org.huangzi.main.common.mapper.LogRepository;
import org.huangzi.main.common.service.LogEsService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: XGLLHZ
 * @date: 2020/7/20 下午3:10
 * @description:
 */
@Service
@Primary
public class LogEsServiceImpl implements LogEsService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Override
    public APIResponse importLog(LogEntity logEntity) {
        Page<LogEntity> page = new Page<>(logEntity.getCurrentPage(), logEntity.getPageSize());
        List<LogEntity> list = logMapper.list(page, logEntity);
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (list.size() > 0) {
            List<LogEsDto> list1 = JSON.parseArray(JSON.toJSONString(list), LogEsDto.class);
            logRepository.saveAll(list1);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse getList(LogEntity logEntity) {

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.queryStringQuery(logEntity.getOperateName()).field("operateName"));
        queryBuilder.addAggregation(AggregationBuilders.terms("logType").field("logType"));

        SearchHits result = restTemplate.search(queryBuilder.build(), LogEsDto.class);

        StringTerms stringTerms = (StringTerms) Objects.requireNonNull(result.getAggregations()).get("logType");

        List<String> list1 = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            String name = bucket.getKeyAsString();
            list1.add(name);
        }

        Map<String, Object> map = new HashMap<>(1);
        map.put(ConstConfig.DATA_LIST, result.getSearchHit(0));
        map.put("logType", list1);
        return new APIResponse(map);
    }

}
