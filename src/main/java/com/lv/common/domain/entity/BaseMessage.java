package com.lv.common.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 消息实体，所有消息都需要继承此类
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 1:04 AM
 */
@Data
public abstract class BaseMessage {
    /**
     * 业务键，用于RocketMQ控制台查看消费情况
     */
    protected String key = "default";
    /**
     * 发送消息来源，用于排查问题
     */
    protected String source = "default";
    /**
     * 发送时间
     */
    protected LocalDateTime sendTime = LocalDateTime.now();

    /**
     * 重试次数，用于判断重试次数，超过重试次数发送异常警告
     */
    protected Integer retryTimes = 0;
}
