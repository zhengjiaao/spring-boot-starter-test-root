package com.zja.artemis.jms;

import com.zja.artemis.model.EmailMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 16:11
 */
@Service
public class MessageProducer {
    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public MessageProducer(JmsTemplate jmsTemplate, Queue emailQueue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = emailQueue;
    }

    public void sendEmail(EmailMessage email) {
        // 发送简单文本消息
        jmsTemplate.convertAndSend(queue, email);

        // 发送带属性的消息（可选）
        jmsTemplate.convertAndSend(queue, email, message -> {
            message.setStringProperty("X_MSG_TYPE", "EMAIL");
            return message;
        });
    }
}