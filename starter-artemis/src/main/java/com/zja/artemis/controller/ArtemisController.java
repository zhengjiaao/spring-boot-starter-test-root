package com.zja.artemis.controller;

import com.zja.artemis.jms.MessageProducer;
import com.zja.artemis.model.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 16:12
 */
@RestController
public class ArtemisController {

    @Autowired
    MessageProducer messageProducer;

    @RequestMapping("/send-email")
    public void sendEmail() {
        // 发送测试消息

        EmailMessage emailMessage = EmailMessage.builder()
                .to( "zhengja@163.com")
                .subject("测试邮件")
                .body("测试邮件内容").build();

        messageProducer.sendEmail(emailMessage);
    }
}
