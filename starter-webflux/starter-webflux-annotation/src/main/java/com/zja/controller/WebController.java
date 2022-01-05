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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * swagger：http://localhost:8080/swagger-ui/index.html#/
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/hello")
    public User hello() {
        // ...
        return new User("hello", 0);
    }

    @GetMapping("/{age}")
    public User getUser(@PathVariable Long age) {
        // ...
        return new User("lisi", age);
    }

    @GetMapping("/{age}/customers")
    public List<Customer> getUserCustomers(@PathVariable Long age) {
        // ...
        return Stream.of(new Customer(age)).collect(Collectors.toList());
    }

    @DeleteMapping("/{age}")
    public User deleteUser(@PathVariable Long age) {
        // ...
        return new User("lisi", age);
    }
}
