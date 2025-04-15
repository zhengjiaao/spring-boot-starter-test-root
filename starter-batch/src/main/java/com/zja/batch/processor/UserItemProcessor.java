package com.zja.batch.processor;

import com.zja.batch.model.User;
import org.springframework.batch.item.ItemProcessor;

/**
 * 自定义处理器
 *
 * @Author: zhengja
 * @Date: 2025-04-15 17:14
 */
public class UserItemProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(final User user) {
        // 数据清洗：转换状态为大写
        String status = user.getStatus().toUpperCase();
        user.setStatus(status);
        return user;
    }
}