package com.zja.artemis.jms;

import com.zja.artemis.model.EmailMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 16:11
 */
@Component
public class MessageConsumer {

    // 监听指定队列
    @JmsListener(destination = "email.queue")
    public void receiveEmail(EmailMessage email) {
        System.out.println("Received email: " + email.getTo());
        System.out.println("Subject: " + email.getSubject());
        System.out.println("Body: " + email.getBody());
    }

    // 带选择器的监听（可选）
    @JmsListener(
            destination = "email.queue",
            selector = "X_MSG_TYPE = 'EMAIL'"
    )
    public void receiveEmailWithSelector(EmailMessage email) {
        System.out.println("[Selector] Received email: " + email.getTo());
    }
}