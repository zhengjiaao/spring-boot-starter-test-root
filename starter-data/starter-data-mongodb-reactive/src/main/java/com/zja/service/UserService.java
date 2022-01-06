/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 17:21
 * @Since:
 */
package com.zja.service;

import com.zja.dao.UserRepository;
import com.zja.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 保存或更新。
     * 如果传入的user没有id属性，由于username是unique的，在重复的情况下有可能报错，
     * 这时找到以保存的user记录用传入的user更新它。
     */
    public Mono<User> save(User user) {
        return userRepository.save(user)
                .onErrorResume(e ->     // 1 进行错误处理
                        userRepository.findByUsername(user.getUsername())   // 2 找到username重复的记录
                                .flatMap(originalUser -> {      // 4 由于函数式为User -> Publisher，所以用flatMap
                                    user.setId(originalUser.getId());
                                    return userRepository.save(user);   // 3 拿到ID从而进行更新而不是创建
                                }));
    }

    public Mono<Long> deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Flux<User> findBy() {
        return userRepository.findBy();
    }
}
