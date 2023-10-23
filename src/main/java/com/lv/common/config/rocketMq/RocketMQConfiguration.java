package com.lv.common.config.rocketMq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ内置使用的转换器是
 * RocketMQMessageConverter，
 * 转换Json时使用的是MappingJackson2MessageConverter，
 * 但是这个转换器不支持时间类型
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 10:42 PM
 */
@Configuration
public class RocketMQConfiguration {

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.name-server}")
    private String nameServer;

    /**
     * 由于使用的Spring版本是3.0.0以上，与rocketMq不是很兼容，对于rocketMqTemplate
     * 的自动注入存在差异，如果不采用这种方式注入则会报出缺少bean的信息
     */
    @Bean("rocketMqTemplate")
    public RocketMQTemplate rocketMqTemplate(){
        RocketMQTemplate rocketMqTemplate = new RocketMQTemplate();
        DefaultMQProducer defaultMqProducer = new DefaultMQProducer();
        defaultMqProducer.setProducerGroup(producerGroup);
        defaultMqProducer.setNamesrvAddr(nameServer);
        rocketMqTemplate.setProducer(defaultMqProducer);
        rocketMqTemplate.setMessageConverter(new MyMQMessageConverter().getMessageConverter());
        return rocketMqTemplate;
    }
}
