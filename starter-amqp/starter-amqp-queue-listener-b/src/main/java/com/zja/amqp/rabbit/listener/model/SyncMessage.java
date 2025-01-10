package com.zja.amqp.rabbit.listener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 13:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncMessage implements Serializable {
    private String messageId; // 消息唯一ID，每次发送消息时需要生成一个唯一的ID
    private String operationType; // 操作类型：ADD, UPDATE, DELETE
    private String entityType; // 实体类型：USER, ORGANIZATION
    private String entityId; // 实体ID
    private String entityData; // 实体数据（JSON格式）
}
