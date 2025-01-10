package com.zja.amqp.rabbit.listener.service;

import com.alibaba.fastjson.JSON;
import com.zja.amqp.rabbit.listener.model.Organization;
import com.zja.amqp.rabbit.listener.model.SyncMessage;
import com.zja.amqp.rabbit.listener.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 13:49
 */
@Slf4j
@Service
public class SyncConsumerService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @RabbitListener(queues = "syncQueue")
    // public void receiveSyncMessage(SyncMessage syncMessage) { // 必须使用路径一致的对象接收，否则会报错，提示：反序列化错误
    public void receiveSyncMessage(String message) {
        handleSyncMessage(message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "test.oms.sync.queue.2"),
            exchange = @Exchange(name = "test.oms.exchange.topic", type = ExchangeTypes.TOPIC),
            key = {"test.oms.sync.queue.2"}
    ))
    public void receiveSyncMessageV1(String message) {
        handleSyncMessage(message);
    }

    private void handleSyncMessage(String message) {
        SyncMessage syncMessage = JSON.parseObject(message, SyncMessage.class);
        String operationType = syncMessage.getOperationType();
        String entityType = syncMessage.getEntityType();
        String entityId = syncMessage.getEntityId();
        String entityData = syncMessage.getEntityData();

        switch (entityType) {
            case "USER":
                handleUserSync(operationType, entityId, entityData);
                break;
            case "ORGANIZATION":
                handleOrganizationSync(operationType, entityId, entityData);
                break;
            default:
                log.warn("Unknown entity type: {}", entityType);
        }
    }

    private void handleUserSync(String operationType, String userId, String userData) {
        switch (operationType) {
            case "ADD":
                User user = JSON.parseObject(userData, User.class);
                userService.save(user);
                break;
            case "UPDATE":
                User updateUser = JSON.parseObject(userData, User.class);
                userService.update(updateUser);
                break;
            case "DELETE":
                userService.delete(userId);
                break;
            default:
                log.warn("Unknown operation type: {}", operationType);
        }
    }

    private void handleOrganizationSync(String operationType, String orgId, String orgData) {
        switch (operationType) {
            case "ADD":
                Organization organization = JSON.parseObject(orgData, Organization.class);
                organizationService.save(organization);
                break;
            case "UPDATE":
                Organization updateOrganization = JSON.parseObject(orgData, Organization.class);
                organizationService.update(updateOrganization);
                break;
            case "DELETE":
                organizationService.delete(orgId);
                break;
            default:
                log.warn("Unknown operation type: {}", operationType);
        }
    }
}
