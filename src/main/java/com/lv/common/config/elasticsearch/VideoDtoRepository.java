package com.lv.common.config.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VideoDtoRepository extends ElasticsearchRepository<EsVideoDto, Long> {
    EsVideoDto findByTitleLike(String keyword);  // 这个命名是有规定的，详见SpringData官网；es搜索复杂条件不推荐使此方式
}
