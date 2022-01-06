# 集成 websocket 的四种方案

- [native](./src/main/java/com/zja/nativewebsocket) springboot 原生注解
- [spring](./src/main/java/com/zja/springwebsocket) springboot  spring封装
- [STOMP](./src/main/java/com/zja/stompwebsocket) springboot  STOMP 

- [tio](./src/main/java/com/zja/tio) Tio 收费的，不推荐

后台依赖：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

前端js：

```
<!--引用依赖-->
<!--    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>-->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.2/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/index.js"></script>

```

