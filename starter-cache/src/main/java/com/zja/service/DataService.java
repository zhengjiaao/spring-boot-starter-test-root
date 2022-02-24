/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-18 16:05
 * @Since:
 */
package com.zja.service;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

//可选的 @CacheConfig
//@CacheConfig(cacheNames = {"myCache"})
@Service
public class DataService {

    /**
     * @CachePut
     * 功能：将数据存入数据库的同时对数据进行缓存。
     * value 指定缓存块名称,key 指定数据的索引
     */
    @CachePut(value = "myCache", key = "data")
    //@CachePut(key = "")
    public int save() {
        System.out.println("进入【save】方法");
        return 1;
    }

    @CachePut(value = "myCache", key = "data+#p0")
    public int save(int a) {
        System.out.println("进入【save】方法");
        return a;
    }

    /**
     * @Cacheable
     * 功能：value值定位缓存块，通过key值从缓存中查找数据。
     * 实际应用：实际查找数据时，会先检索缓存，如果没找到再检索数据库，然后缓存。
     */
    //@Caching
    //@Cacheable(value = "",key = "") // value定位缓存块，key在缓存块中查找数据
    @Cacheable(key = "targetClass + methodName +#p0")//此处没写value
    public int find(int a) {
        System.out.println("进入【find】方法");
        return a;
    }

    /**
     * 功能：将数据存入数据库的同时对数据进行缓存。
     * value指定缓存块名称
     * key指定数据的索引
     */
    @CachePut
    public int update(int a) {
        System.out.println("进入【update】方法");
        return a;
    }

    /**
     * @CacheEvict
     * 功能：在指定的缓存块搜索数据，存在则从缓存中移除。
     * 实际应用：与数据库访问接口配合使用，如果数据存在于数据表中，会同时移除数据库中的数据。
     */
    @CacheEvict(value = "", key = "")
    //@CacheEvict(key = "")
    public int delete(int a) {
        System.out.println("进入【delete】方法");
        return a;
    }

    //方法调用后清空所有缓存
    @CacheEvict(value = "myCache", allEntries = true)
    //方法调用前清空所有缓存
    //@CacheEvict(value="myCache",beforeInvocation=true)
    public void delectAll() {
        System.out.println("进入【delectAll】方法");
    }
}
