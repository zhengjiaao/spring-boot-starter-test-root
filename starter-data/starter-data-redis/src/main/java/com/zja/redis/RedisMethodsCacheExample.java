/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 17:39
 * @Since:
 */
package com.zja.redis;

import com.zja.redis.entity.UserEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * redis 在类的方法上进行缓存
 *
 * 查询接口：首次查询缓存中没有，再到数据库查询，将查询返回的结果保存到缓存中，下次请求在缓存中取数据
 *
 * 源码：CacheManager 接口
 * @CacheConfig 类组 (可选的)
 *      cacheNames：
 *          group#60
 *          group#3*60
 */
@Component
//@CacheConfig(cacheNames = "group") // 需要启用  @EnableCaching
@CacheConfig(cacheNames = "group#60") // 自定义解析规则
public class RedisMethodsCacheExample {

    //询接口：首次查询缓存中没有，再到数据库查询，将查询返回的结果保存到缓存中，下次请求在缓存中取数据

    /**
     * @Cacheable value:组 (可选的), key:组员(必须的)
     * @return 返回数据，并将数据保存到redis
     */
    @Cacheable(key = "'findData'") //group::findData
//    @Cacheable(value = "findDataKey", key = "'findData'")  //findDataKey::findData   @Cacheable中value优先级高，会覆盖@CacheConfig中cacheNames
    public String findData() {
        System.out.println("未查询缓存");
        return "无参查询数据";
    }

    /**
     * @Cacheable 将参数作为 指定key(或key的部分)
     *
     * @param parameter 参数作为 key(可选的)
     */
    @Cacheable(key = "'findData:' + #p0")
//    @Cacheable(key = "'findData:' + #parameter")  // 同上 #p0
    //@Cacheable(key = "'findData:'+ #p0", keyGenerator = "wiselyKeyGenerator")  // 自定义key解析器
    public String findData(String parameter) {
        System.out.println("未查询缓存");
        return "有参查询: " + parameter;
    }

    /**
     * @Cacheable 查找数据的值为null处理
     * @return null
     */
//    @Cacheable(key = "'findDataValueIsNull'")
    @Cacheable(key = "'findDataValueIsNull'", unless = "#result == null")  //条件：若返回结果是null，则不进行存储
    public String findDataValueIsNull() {
        System.out.println("未查询缓存");
        return null;
    }

    /**
     * @Cacheable 缓存分页数据： 多个参数拼接key查询写法
     *
     * @param page  参数 1
     * @param pageSize 参数 2
     */
    @Cacheable(key = "#page+'-'+#pageSize")
    public Object findAllData(int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        System.out.println("未查询缓存");
        return "分页数据: " + pageStart;
    }

    /**
     * @Cacheable 缓存: key存在则获取缓存数据，key不存在则添加到缓存中
     *
     * @param parameter 参数
     */
    @Cacheable(key = "'key:' + #p0")
    public String saveData(String parameter) {
        System.out.println("未查询缓存");
        return "新增: " + parameter;
    }

    @Cacheable(key = "'key:' + #p0.id")
    public String saveObject(UserEntity userEntity) {
        System.out.println("未查询缓存");
        return "新增: " + userEntity;
    }

    /**
     * @CachePut 更新缓存：key存在则更新，key不存在则缓存
     *
     * @param parameter 参数
     */
    //@CachePut(key = "'rediskey:'+#p0")
    @CachePut(key = "'key:'+#p0")
    public String putData(String parameter) {
        System.out.println("未查询缓存");
        return "更新: " + parameter;
    }

    /**
     * @CacheEvict 删除缓存组下的 某个key
     */
    @CacheEvict(key = "'key:'+#p0")
    public String deleteData(String parameter) {
        return "删除缓存某个数据: " + parameter;
    }

    /**
     * @CacheEvict 清空缓存组下的 所有key
     */
    @CacheEvict(allEntries = true)
//    @CacheEvict(value="redis:cache", allEntries=true)
    public String deleteAllData() {
        return "清空缓存组下的 所有key：redis:cache";
    }


    //自定义缓存解析规则示例

    /**
     * @Cacheable value=cacheNames 自定义解析器，支持设置过期时间 60s
     * value：
     *      a#60
     *      a#3*60
     */
    @Cacheable(value = "customizeCacheNames#60*3", key = "'data'")
    public String customizeCacheNames() {
        System.out.println("未查询缓存");
        return "无参查询数据";
    }

}
