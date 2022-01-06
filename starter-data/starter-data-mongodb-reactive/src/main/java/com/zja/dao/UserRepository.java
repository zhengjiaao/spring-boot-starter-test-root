/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 17:20
 * @Since:
 */
package com.zja.dao;

import com.zja.model.User;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByUsername(String username);     // 2
    Mono<Long> deleteByUsername(String username);

    /**
     * @Tailable仅支持有大小限制的（“capped”）collection
     * 而自动创建的collection是不限制大小的，因此我们需要先手动创建 {@link }
     */
    //无限流：无限地发出SSE，而不会有“完成信号”出现
    @Tailable // 1 @Tailable注解的作用类似于linux的tail命令，被注解的方法将发送无限流，需要注解在返回值为Flux这样的多个元素的Publisher的方法上；
    Flux<User> findBy(); // 2 findAll()是想要的方法，但是在ReactiveMongoRepository中我们够不着，所以使用findBy()代替

}
