package com.zja.amqp.rabbit.listener.service;

import com.zja.amqp.rabbit.listener.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 13:54
 */
@Service
public class UserService {

    @Transactional // 保证事务原子性
    public void save(User user) {
        System.out.println("保存用户：" + user);
    }

    @Transactional // 保证事务原子性
    public void update(User user) {
        System.out.println("更新用户：" + user);
    }

    @Transactional // 保证事务原子性
    public void delete(String userId) {
        System.out.println("删除用户：" + userId);
    }
}
