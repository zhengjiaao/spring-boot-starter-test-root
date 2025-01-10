package com.zja.amqp.rabbit.send.controller;

import com.alibaba.fastjson.JSON;
import com.zja.amqp.rabbit.send.model.Organization;
import com.zja.amqp.rabbit.send.model.SyncMessage;
import com.zja.amqp.rabbit.send.model.User;
import com.zja.amqp.rabbit.send.service.SyncProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 14:00
 */
@RequestMapping("/rest/SyncProducer")
@RestController
public class SyncProducerController {

    @Autowired
    SyncProducerService service;

    @PostMapping("/send/user/add")
    public String sendUserByAdd() {
        // 同步消息，基础通知，只有一个消费者可以消费
        // service.sendSyncMessage("ADD", "USER", "1", new User("1", "zhengja", "123456"));

        // 同步消息：广播通知，每个消费者都可以消费，推荐每个消费者对应一个队列，并且每个队列中消费完成后，消息会被删除
        SyncMessage syncMessage = new SyncMessage();
        syncMessage.setEntityId("1");
        syncMessage.setEntityType("USER");
        syncMessage.setOperationType("ADD");
        syncMessage.setEntityData(JSON.toJSONString(new User("1", "zhengja", "123456")));
        service.sendSyncMessage(syncMessage);
        return "sendUser";
    }

    @PutMapping("/send/user/update")
    public String sendUserByUpdate() {
        service.sendSyncMessage("UPDATE", "USER", "1", new User("1", "zhengja", "123456"));
        return "sendUser";
    }

    @DeleteMapping("/send/user/delete")
    public String sendUserByDelete() {
        service.sendSyncMessage("DELETE", "USER", "1", new User("1", "zhengja", "123456"));
        return "sendUser";
    }

    @PostMapping("/send/org/add")
    public String sendOrgByAdd() {
        service.sendSyncMessage("ADD", "ORGANIZATION", "1", new Organization("1", "组织机构"));
        return "sendSyncMessage";
    }

}
