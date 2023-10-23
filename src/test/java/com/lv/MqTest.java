package com.lv;

import com.lv.common.template.RocketMQEnhanceTemplate;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/31 11:51 PM
 */

@SpringBootTest
class MqTest {

    @Resource
    private RocketMQEnhanceTemplate rocketMQEnhanceTemplate;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void sendNormalMsg() {

    }

    @Test
    public void sendNormalMsg2(){
        Message<String> msg = MessageBuilder.withPayload("Hello,RocketMQ Normal_msg").build();
        for (int i = 0; i < 100; i++) {
            rocketMQTemplate.send("normal_msg", msg);
        }
    }
}
