/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-06 10:31
 * @Since:
 */
package com.zja.controller;

import com.zja.dao.MyEventRepository;
import com.zja.model.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 无限流 示例 发起post请求，同时get请求可以实时获取到新增的数据，类 websocket双通道
 * 测试-未完成
 */
@RestController
@RequestMapping("/events")
public class MyEventController {

    @Autowired
    private MyEventRepository myEventRepository;

    @PostMapping(path = "/post", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE) // 1   指定传入的数据是application/stream+json，与getEvents方法的区别在于这个方法是consume这个数据流
    public Mono<Void> loadEvents(@RequestBody Flux<MyEvent> events) {
        return this.myEventRepository.insert(events).then();    // 2    insert返回的是保存成功的记录的Flux，但我们不需要，使用then方法表示“忽略数据元素，只返回一个完成信号”
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> getEvents() {
        return this.myEventRepository.findBy().log();
    }
}
