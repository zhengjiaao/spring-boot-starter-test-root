package com.zja.amqp.rabbit.send.service;

import com.alibaba.fastjson.JSON;
import com.zja.amqp.rabbit.send.model.SyncMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 13:49
 */
@Slf4j
@Service
public class SyncProducerService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    // 发送同步消息，只有一个消费者可以消费，并且消费完成后，消息会被删除
    public void sendSyncMessage(String operationType, String entityType, String entityId, Object entityData) {
        SyncMessage syncMessage = new SyncMessage();
        syncMessage.setOperationType(operationType);
        syncMessage.setEntityType(entityType);
        syncMessage.setEntityId(entityId);
        syncMessage.setEntityData(JSON.toJSONString(entityData));

        String syncMessageJson = JSON.toJSONString(syncMessage);
        amqpTemplate.convertAndSend("syncQueue", syncMessageJson);
        sendNotification(syncMessageJson);

        // amqpTemplate.convertAndSend("syncQueue", syncMessage); // 必须使用路径一致的com.zja.SyncMessage对象接收，否则消费端会报错，提示：反序列化错误
    }

    // 发送同步消息，广播通知，每个消费者都可以消费，消费者与队列一对一，并且每个队列中消费完成后，消息会被删除
    public void sendSyncMessage(SyncMessage syncMessage) {
        String syncMessageJson = JSON.toJSONString(syncMessage);
        // 发送给所有消费者，当 routingKey = "" 时，消息会被发送到所有绑定到该交换机的队列中
        amqpTemplate.convertAndSend("test.oms.exchange.fanout","", syncMessageJson);
        sendNotification(syncMessageJson);
    }

    private void sendNotification(String syncMessageJson) {
        // 发送通知逻辑
        log.info("Sending notification for syncMessage: {}", syncMessageJson);
    }
}
