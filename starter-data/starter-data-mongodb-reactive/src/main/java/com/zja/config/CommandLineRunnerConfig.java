/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-06 10:24
 * @Since:
 */
package com.zja.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunnerConfig {
    /*@Bean   // 1 Spring3之后官方推荐这种配置方式的原因
    public CommandLineRunner initData(@Qualifier("mongoOperations") MongoOperations mongo) {  // 2    MongoOperations提供对MongoDB的操作方法，由Spring注入的mongo实例已经配置好，直接使用即可
        return (String... args) -> {    // 3    CommandLineRunner也是一个函数式接口，其实例可以用lambda表达
            mongo.dropCollection(MyEvent.class);    // 4   如果有，先删除collection，生产环境慎用这种操作
            mongo.createCollection(MyEvent.class, CollectionOptions.empty().maxDocuments(200).size(100000).capped()); // 5  创建一个记录个数为10的capped的collection，容量满了之后，新增的记录会覆盖最旧的
        };
    }*/
}
