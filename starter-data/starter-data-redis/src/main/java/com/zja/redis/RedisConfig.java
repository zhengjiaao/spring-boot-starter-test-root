/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 16:53
 * @Since:
 */
package com.zja.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.zja.redis.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * RedisConfig
 *
 * RedisTemplate 配置
 * RedisUtil 配置
 */
@Configuration
//@EnableCaching  // 启用注解式 缓存 否则 RedisCacheManager 或 CacheManager 无效
public class RedisConfig {

    /**
     * RedisTemplate (可选的，默认不需要手动注入)
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisTemplate.setConnectionFactory(factory);

        /**
         * redis 默认 JDK序列化(JdkSerializationRedisSerializer)，是最高效的,但value值会是("rO0ABXQAAXF0AAF3")
         * redis Jackson2JsonRedisSerializer 序列化,json占用的内存最小
         */
        Jackson2JsonRedisSerializer jsonSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //设置对象映射
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jsonSerializer.setObjectMapper(objectMapper);

        //设置 Redis key/value 序列化的方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jsonSerializer);

        //设置Redis Hash key/value 序列化的方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jsonSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }


    /**
     * HashOperations 对hash类型的数据操作: 针对map类型的数据操作
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * ValueOperations 对redis字符串类型数据操作： 简单K-V操作
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * ListOperations 对链表类型的数据操作: 针对list类型的数据操作
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * SetOperations 对无序集合类型的数据操作： set类型数据操作
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * ZSetOperations 对有序集合类型的数据操作 ：zset类型数据操作
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    /**
     * RedisUtil RedisTemplate封装的工具类
     */
    @Bean
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        return new RedisUtil(redisTemplate);
    }

    //注解缓存前缀 cache:key
    private static final String prefixCacheName = "cache:";

    //设置不同cacheName的过期时间
    public static final String OUTBOUNDLINE_CACHE = "OUTBOUNDLINE_CACHE";
    public static final String THIRDAGENT_CACHE = "THIRDAGENT_CACHE";

    /**
     * RedisCacheManager  注解方式进行缓存 (可选的，默认不需要手动注入)
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        //设置json序列化
        Jackson2JsonRedisSerializer jsonSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jsonSerializer.setObjectMapper(objectMapper);

        //RedisCache 默认配置
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
//                .disableCachingNullValues()     //禁止缓存null value，若返回空值会抛异常(不推荐)
                .entryTtl(Duration.ofDays(1))   //设置注解缓存1天
                .prefixCacheNameWith(prefixCacheName)  //设置注解缓存前缀
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(jsonSerializer));

        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        //设置不同cacheName的过期时间(可选的，采用该方法可以满足一定的需求，但是使用上不够灵活)
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>(16);
        cacheConfigurations.put(OUTBOUNDLINE_CACHE, RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put(THIRDAGENT_CACHE, RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30)));

        //初始化RedisCacheManager
//        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig, cacheConfigurations);

        //使用 自定义 Redis 缓存管理器(支持注解设置过期时间)
        return new CustomizeRedisCacheManager(redisCacheWriter, defaultCacheConfig, cacheConfigurations);
    }


    /**
     * 自定义Redis 缓存管理器
     * 添加自定义 {@link CustomizeRedisCache} 支持注解设置缓存时间
     */
    class CustomizeRedisCacheManager extends RedisCacheManager {

        private RedisCacheWriter cacheWriter;
        private RedisCacheConfiguration defaultCacheConfig;

        public CustomizeRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
            super(cacheWriter, defaultCacheConfiguration);
            this.cacheWriter = cacheWriter;
        }

        public CustomizeRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, String... initialCacheNames) {
            super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
            this.cacheWriter = cacheWriter;
            this.defaultCacheConfig = defaultCacheConfiguration;
        }

        public CustomizeRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, boolean allowInFlightCacheCreation, String... initialCacheNames) {
            super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
            this.cacheWriter = cacheWriter;
            this.defaultCacheConfig = defaultCacheConfiguration;
        }

        public CustomizeRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
            super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
            this.cacheWriter = cacheWriter;
        }

        public CustomizeRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
            super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
            this.cacheWriter = cacheWriter;
            this.defaultCacheConfig = defaultCacheConfiguration;
        }

        //用于返回自定义的 redisCache
        @Override
        protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
            return new CustomizeRedisCache(name, cacheWriter, cacheConfig != null ? cacheConfig : defaultCacheConfig);

        }
    }


    /**
     * 自定义Redis缓存 重写 put 方法：支持自定义key设置时间
     * {@link org.springframework.data.redis.cache.RedisCache}
     */
    class CustomizeRedisCache extends RedisCache {

        private RedisCacheWriter cacheWriter;
        private RedisCacheConfiguration cacheConfig;

        //cacheNames 校验规则：cacheNames#60 或 cacheNames#3*60
        String REGEX_STR = ".*\\#\\d+$";
        String REGEX_STR_V2 = ".*\\#\\d+\\*\\d+$";
        //时间 校验规则：3*60
        String REGEX_STR_TIME = "\\d+\\*\\d+$";

        //分割标识
        private static final String Splitter = "\\#";
        private static final String SplitterTime = "\\*";

        /**
         * Create new {@link RedisCache}.
         */
        protected CustomizeRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
            super(name, cacheWriter, cacheConfig);
            this.cacheWriter = cacheWriter;
            this.cacheConfig = cacheConfig;
        }

        @Override
        public void put(Object key, @Nullable Object value) {

            String name = super.getName();

            //如果注解中 cacheNames 符合自定义格式 test#60 或 test#60*3，则执行
            if (Pattern.matches(REGEX_STR, name) || Pattern.matches(REGEX_STR_V2, name)) {
                List<String> keyList = Arrays.asList(name.split(Splitter));
                String cachekeyName = keyList.get(0);

                String ttlValue = keyList.get(1);
                Long ttl = this.ttl(ttlValue);

                Object cacheValue = preProcessCacheValue(value);

                if (!isAllowNullValues() && cacheValue == null) {
                    throw new IllegalArgumentException(String.format(
                            "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.",
                            name));
                }
                //使用自定义时间
                cacheWriter.put(cachekeyName, serializeCacheKey(createCacheKey(key)), serializeCacheValue(cacheValue), Duration.ofSeconds(ttl));
            } else {
                //不符合自定义，按原来逻辑处理
                super.put(key, value);
            }
        }

        /**
         * 重写
         * @see CustomizeRedisCache #prefixCacheKey(String)
         * @param key
         */
        @Override
        protected String createCacheKey(Object key) {

            String convertedKey = convertKey(key);
            if (!cacheConfig.usePrefix()) {
                return convertedKey;
            }

            return prefixCacheKey(convertedKey);
        }

        /**
         * 现有key 值格式为 key#ttl , 改方法将key 值后边的#ttl 去掉 ；例如test# 10；改方法处理后为test
         * @param key
         */
        private String prefixCacheKey(String key) {
            String name = super.getName();

            if (Pattern.matches(REGEX_STR, name) || Pattern.matches(REGEX_STR_V2, name)) {
                List<String> keyList = Arrays.asList(name.split(Splitter));
                String cachekeyName = keyList.get(0);

                return cacheConfig.getKeyPrefixFor(cachekeyName) + key;
            }

            // allow contextual cache names by computing the key prefix on every call.
            return cacheConfig.getKeyPrefixFor(name) + key;
        }

        /**
         * 计算过期时间表达式
         * @param ttlValue 3*60 --> 180 秒
         * @return 计算结果 180 秒
         */
        private Long ttl(String ttlValue) {
            Long ttl = -1L;

            if (Pattern.matches(REGEX_STR_TIME, ttlValue)) {
                List<String> ttlValueList = Arrays.asList(ttlValue.split(SplitterTime));
                ttl = Long.valueOf(ttlValueList.get(0)) * Long.valueOf(ttlValueList.get(1));
            } else {
                ttl = Long.valueOf(ttlValue);
            }
            return ttl;
        }
    }


}
