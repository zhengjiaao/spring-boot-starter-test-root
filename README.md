# spring-boot-starter-test-root

> [springboot 2.x 官方文档](https://spring.io/projects/spring-boot)

## spring-boot-starter 全家桶(组件)示例

> 以下是已经完成的示例模块

- [starter-actuator 应用程序监控](./starter-actuator)
- [starter-amqp 消息中间件](./starter-amqp)
- [starter-aop 切面编程](./starter-aop)
- [starter-cache 注解式缓存](./starter-cache)
- [starter-data 组件](./starter-data)
    - [starter-data-elasticsearch](./starter-data/starter-data-elasticsearch)
    - [starter-data-jdbc](./starter-data/starter-data-jdbc)
    - [starter-data-jpa](./starter-data/starter-data-jpa)
    - [starter-data-mongodb](./starter-data/starter-data-mongodb)
    - [starter-data-mongodb-reactive](./starter-data/starter-data-mongodb-reactive)
    - [starter-data-redis 缓存库redis](./starter-data/starter-data-redis)
    - [starter-data-neo4j 图库](./starter-data/starter-data-neo4j)
    - [starter-data-rest](./starter-data/starter-data-rest)
- [starter-dubbo RPC调用](./starter-dubbo) 
    - [starter-dubbo2x 基于直连](./starter-dubbo/starter-dubbo2x) 
    - [starter-dubbo3x 基于nacos、zookeeper](./starter-dubbo/starter-dubbo3x) 
- [starter-freemarker 模板](./starter-freemarker) 
- [starter-jdbc 数据库操作](./starter-jdbc) 
- [starter-jta-atomikos 多数据源+分布式事务管理](./starter-jta-atomikos) 
- [starter-mail 发送邮件](./starter-mail) 
- [starter-quartz 定时任务](./starter-quartz)
- [starter-test 单元测试](./starter-test)
- [starter-thymeleaf 模板](./starter-thymeleaf)
- [starter-validation 参数校验](./starter-validation)
- [starter-web-19000 web服务](./starter-web-19000)
- [starter-web-services](./starter-web-services)
- [starter-webflux 响应式](./starter-webflux)
- [starter-websocket 即时通信](./starter-websocket)


## 适配的中间件版本

> 以下是你可能会用到的中间件

|                    | 官网文档 | github  | 使用版本下载  | 详细  |  推荐  |
| ----------------- | ---------- | ---------- | ---------- | ---------- | ---------- | 
| zookeeper        | [zookeeper.apache.org](http://zookeeper.apache.org/releases.html)    |  | [zookeeper-3.6.3-bin.tar.gz](https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.6.3/apache-zookeeper-3.6.3-bin.tar.gz)  |   |  |
| nacos            | [nacos.io/zh-cn](https://nacos.io/zh-cn/)          | [github.com/alibaba/nacos](https://github.com/alibaba/nacos) | [nacos-1.4.2](https://github.com/alibaba/nacos/releases/tag/1.4.2)  | 搭配dubbo2.x  |  |
| nacos            | [nacos.io/zh-cn](https://nacos.io/zh-cn/)          | [github.com/alibaba/nacos](https://github.com/alibaba/nacos) | [nacos-2.1.0](https://github.com/alibaba/nacos/releases/tag/2.1.0)  | 搭配dubbo3.x  |  |
| Git              | [git-scm.com](https://git-scm.com/)       |           | [git-latest](https://git-scm.com/downloads)  |   |  |
| Rabbitmq         | [Rabbitmq 官方](https://www.rabbitmq.com/)       | [Rabbitmq github](https://github.com/rabbitmq)        | [rabbitmq-server-3.11.5](https://github.com/rabbitmq/rabbitmq-server/releases/tag/v3.11.5)  |   |  |
