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

|           | 官网文档                                                              | github                                                       | 使用版本下载                                                                                                                          | 详细         | 推荐 |
|-----------|-------------------------------------------------------------------|--------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|------------|----| 
| zookeeper | [zookeeper.apache.org](http://zookeeper.apache.org/releases.html) |                                                              | [zookeeper-3.6.3-bin.tar.gz](https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.6.3/apache-zookeeper-3.6.3-bin.tar.gz) |            |    |
| nacos     | [nacos.io/zh-cn](https://nacos.io/zh-cn/)                         | [github.com/alibaba/nacos](https://github.com/alibaba/nacos) | [nacos-1.4.2](https://github.com/alibaba/nacos/releases/tag/1.4.2)                                                              | 搭配dubbo2.x |    |
| nacos     | [nacos.io/zh-cn](https://nacos.io/zh-cn/)                         | [github.com/alibaba/nacos](https://github.com/alibaba/nacos) | [nacos-2.1.0](https://github.com/alibaba/nacos/releases/tag/2.1.0)                                                              | 搭配dubbo3.x |    |
| Git       | [git-scm.com](https://git-scm.com/)                               |                                                              | [git-latest](https://git-scm.com/downloads)                                                                                     |            |    |
| Rabbitmq  | [Rabbitmq 官方](https://www.rabbitmq.com/)                          | [Rabbitmq github](https://github.com/rabbitmq)               | [rabbitmq-server-3.11.5](https://github.com/rabbitmq/rabbitmq-server/releases/tag/v3.11.5)                                      |            |    |

## 本地运行环境搭建

> 以下是你必须要安装的基础软件,可以使项目正常打包及运行.

|       | 官网文档                                                                              | github | 使用版本下载                                                                       | 详细 | 是否必须安装 |
|-------|-----------------------------------------------------------------------------------|--------|------------------------------------------------------------------------------|----|--------| 
| java  | [www.oracle.com/java8](https://www.oracle.com/java/technologies/downloads/#java8) |        | [java8 downloads](https://www.oracle.com/java/technologies/downloads/#java8) |    | **必须** |
| maven | [maven.apache.org](https://maven.apache.org/)                                     |        | [maven3.6.2 downloads](https://maven.apache.org/download.cgi)                |    | **必须** |

## 后续计划

> 以下是后续计划预研的技术

|              | 说明                            | 是否完成 | 
|--------------|-------------------------------|------|
| cloud-stream | 预研消息中间件kafka、rabbit、rocketmq等 | 计划中  |

## 你还可以学习其他项目

> 以下是你可能需要学习的其他项目及技术

|                                          | 资源地址                                                                                         | 说明                                                                                           |  |
|------------------------------------------|----------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|--|
| github/zhengjiaao                        | [github.com/zhengjiaao](https://github.com/zhengjiaao)                                       | 主页面，展示一些比较重要技术预研项目                                                                           |  |
| zhengjiaao/springcloud-test-root         | [springcloud-test-root](https://github.com/zhengjiaao/springcloud-test-root)                 | springcloud 全家桶(组件) 技术预研框架,内容较多，较基础，偏向于技术的应用，适合初学者快速掌握某项技术，欢迎Star，推荐学习                       |  |
| zhengjiaao/spring-boot-starter-test-root | [spring-boot-starter-test-root](https://github.com/zhengjiaao/spring-boot-starter-test-root) | spring-boot-starter 2.x 全家桶(组件) 技术预研框架,内容较多，较基础，偏向于技术的应用，适合初学者快速掌握某项技术，欢迎Star，推荐学习           |  |
| zhengjiaao/springboot-test-root          | [springboot-test-root](https://github.com/zhengjiaao/springboot-test-root)                   | springboot 2.x 技术预研框架,内容较多，较基础，偏向于技术的应用，适合初学者快速掌握某项技术，欢迎Star，推荐学习                            |  |
| zhengjiaao/spring5x                      | [spring5x](https://github.com/zhengjiaao/spring5x)                                           | spring 5.x 技术预研框架                                                                            |  |
| zhengjiaao/springboot-test-redis         | [springboot-test-redis](https://github.com/zhengjiaao/springboot-test-redis)                 | springboot 2.x + redis 项目实战-实例,很早之前学习redis写的，可以学习redis工具类、数据缓存、消息发布和订阅等                      |  |
| zhengjiaao/springboot-test-mybatis-root  | [springboot-test-mybatis-root](https://github.com/zhengjiaao/springboot-test-mybatis-root)   | springboot 2.x 集成 mybatis、mybatis-plus、分页插件 pagehelper。 使用 mybatis 实现简单的CRUD操作，动态插入、批量插入等操作。 |  |

## springboot 版本对应选型

- 参考地址：
    - https://start.spring.io/actuator/info
    - https://spring.io/projects/spring-cloud#overview
    - https://docs.spring.io/spring-boot/docs/{springboot-verion}/reference/htmlsingle/

| Spring Boot   | Spring Framework | Spring Cloud | spring-cloud-alibaba | Java        | Maven          | Gradle                     | Tomcat                   |
|---------------|------------------|--------------|----------------------|-------------|----------------|----------------------------|--------------------------|
| 3.2.x         | 6.0.x            | 2022.0.x     | 2021.x               | Java 17     | 3.6.3 or later | 7.x (7.5 or later) and 8.x | Tomcat 10.x              |
| 2.7.x         | 5.3.x            | 2021.0.x     | 2021.x               | Java 8 or 9 | 3.5+           | 6.8+                       | Tomcat 9.x               |
| 2.5.x         | 5.3.x            | 2020.0.x     | 2020.x               | Java 8 or 9 | 3.5+           | 6.8+                       | Tomcat 8.x or Tomcat 9.x |
| 2.3.x.RELEASE | 5.2.x.RELEASE    | Hoxton       | 2.2.x                | Java 8 or 9 | 3.3+           | 4.4+                       | Tomcat 8.x or Tomcat 9.x |
| 1.5.x.RELEASE | 4.x.x.RELEASE    | Edgware      | 1.5.x                | Java 7 or 8 | 3.2+           | 2.9+                       | Tomcat 7.x or Tomcat 8.x |
