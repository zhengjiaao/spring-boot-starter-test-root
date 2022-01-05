/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-27 17:35
 * @Since:
 */
package com.zja.controller;

import com.zja.model.Customer;
import com.zja.model.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * swagger：http://localhost:8080/swagger-ui/index.html#/
 */
@RestController
@RequestMapping("/webflux")
public class WebfluxController {

    @GetMapping("/hello")
    public Mono<User> hello() {
        // ...
        return Mono.just(new User("hello", 0));
    }

    @GetMapping("/{age}")
    public Mono<User> getUser(@PathVariable Long age) {
        // ...
        return Mono.just(new User("lisi", age));
    }

    @GetMapping("/{age}/customers")
    public Flux<Customer> getUserCustomers(@PathVariable Long age) {
        // ...
        //Flux 对应 List
        return Flux.just(new Customer(age));
    }

    @DeleteMapping("/{age}")
    public Mono<User> deleteUser(@PathVariable Long age) {
        // ...
        return Mono.just(new User("lisi", age));
    }
}
