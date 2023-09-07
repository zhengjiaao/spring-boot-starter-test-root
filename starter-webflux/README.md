# starter-webflux

- [webflux 官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)


Spring WebFlux是一个新的 reactive web应用框架，自 Spring Framework 5.0引入。与Spring MVC不同的是,它不需要the Servlet API,是完全异步和非阻塞的，实现了Reactive Streams specification。

Spring WebFlux 有两种风格：函数式和基于注释的。基于注解的模型非常接近 Spring MVC 模型.  

两种实现方式：

- webflux 注解式编程 (内部实现路由和请求Handler)    
- webflux 函数式编程 使用 路由配置与实际请求Handler
