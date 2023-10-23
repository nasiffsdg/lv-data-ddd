package com.lv.common.template;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "normal_msg", consumerGroup = "consumer_normal")
@Slf4j
public class NormalMsgConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
     log.info("Receive Normal Msg: {}", message);
    }
}
