# starter-data-redis

>官网文档

- []()
- []()

## 说明

RedisTemplate 和 StringRedisTemplate 两者的数据是不共通的

RedisTemplate 使用 JdkSerializationRedisSerializer 将数据先序列化成字节数组，然后存入Redis数据库

StringRedisTemplate使用的是StringRedisSerializer
