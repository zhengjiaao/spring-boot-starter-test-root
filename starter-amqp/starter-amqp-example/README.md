# starter-amqp

**MQ消息中间件: rabbitmq**

- [rabbitmq 官网](https://www.rabbitmq.com/)
- [rabbitmq spring官网](https://spring.io/projects/spring-amqp)
- [rabbitmq github](https://github.com/rabbitmq)
- [rabbitmq 参考](https://www.jianshu.com/p/a205606182e7)

## 依赖引入

```xml
        <!--MQ rabbitmq-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
            <!--内置 amqp-client 5.9.0-->
        </dependency>
        <!--MQ rabbitmq Json 消息转换器-->
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>
```
