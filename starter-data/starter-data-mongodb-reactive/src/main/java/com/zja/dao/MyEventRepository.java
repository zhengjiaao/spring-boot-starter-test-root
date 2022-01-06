/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-06 10:30
 * @Since:
 */
package com.zja.dao;

import com.zja.model.MyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 *
 */
public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> {
    //@Tailable起作用的前提是至少有一条记录
    @Tailable // 1  @Tailable注解的作用类似于linux的tail命令，被注解的方法将发送无限流，需要注解在返回值为Flux这样的多个元素的Publisher的方法上
    Flux<MyEvent> findBy(); // 2    findAll()是想要的方法，但是在ReactiveMongoRepository中我们够不着，所以使用findBy()代替
}
