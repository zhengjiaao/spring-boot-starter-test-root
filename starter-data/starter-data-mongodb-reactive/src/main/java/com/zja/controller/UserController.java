/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 17:22
 * @Since:
 */
package com.zja.controller;

import com.zja.model.User;
import com.zja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public Mono<String> hello() {   // 【改】返回类型为Mono<String>
        return Mono.just("Welcome to reactive world ~");     // 【改】使用Mono.just生成响应式数据
    }

    @PostMapping("/save")
    public Mono<User> save(User user) {
        return this.userService.save(user);
    }

    @DeleteMapping("/{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return this.userService.deleteByUsername(username);
    }

    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }

    @GetMapping(value = "/list")
    public Flux<User> findAll() {
//        return this.userService.findAll();
        //查看日志 可以看到在三个onNext信号后是一个onComplete信号，这样的流是有限流。
        //这个时候如果在数据库中再新增一个User的话，已经结束的请求也不会再有新的内容出现了
        return this.userService.findAll().log();
    }

    //响应式 异步查看方式
    @GetMapping(value = "/list/v2", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> findAll2() {
//        return this.userService.findAll();
        //怎么看是不是异步的呢？
        return this.userService.findAll().delayElements(Duration.ofSeconds(1));
    }

    //无限流
    @GetMapping(path = "/list/v3", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> getEvents() {
        return this.userService.findBy();
    }

}
