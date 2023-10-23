package com.lv.common.template;

import com.lv.common.config.elasticsearch.EsVideoDto;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ElasticSearchService {
  @Resource
  private ElasticsearchTemplate elasticsearchTemplate;
	/**
	* 此bean加载完毕时同时将mapping信息发给es
	*/
  @PostConstruct
  void initIndex(){
    try {
      elasticsearchTemplate.indexOps(EsVideoDto.class).putMapping();
    } catch (Exception e) {
      log.warn("video mapping 添加失败。若有必要，请在kibana使用\nDELETE video");
    }
  }
}
