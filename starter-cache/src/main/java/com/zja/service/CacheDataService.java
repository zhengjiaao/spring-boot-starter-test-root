/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-18 16:05
 * @Since:
 */
package com.zja.service;

import com.zja.constants.CacheKeyConstants;
import com.zja.model.UserDTO;
import com.zja.model.UserRequest;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;

// 可选的 @CacheConfig
//@CacheConfig(cacheNames = {"myCache"})
//@CacheConfig(cacheNames = CacheKeyConstants.EntityCacheKey.DATA)
@Service
public class CacheDataService {

    /**
     * @CachePut 功能：将数据存入数据库的同时对数据进行缓存。
     * value 指定缓存块名称,key 指定数据的索引
     */
    @CachePut(value = "myCache", key = "#p0")
    //@CacheEvict(value = "myCache", key = "#a") // 推荐删除,保证数据一致性
    public int save(int a) {
        System.out.println("进入【save2】方法");
        return 1;
    }

    /**
     * @Cacheable 功能：value值定位缓存块，通过key值从缓存中查找数据。
     * 实际应用：实际查找数据时，会先检索缓存，如果没找到再检索数据库，然后缓存。
     */
    //@Caching
    //@Cacheable(value = "",key = "") // value定位缓存块，key在缓存块中查找数据
    //@Cacheable(key = "targetClass + methodName +#p0") //此处没写value
    //@Cacheable(value = "myCache", key = "#a", unless = "#result==null") //空值不会被缓存
    @Cacheable(value = "myCache", key = "#a"/*,key = "#p0"*/)
    public int find(int a) {
        System.out.println("进入【find】方法");
        return a;
    }

    // 无参数方方法缓存
    @Cacheable(value = "user:find2", key = "find2")
    public int find2(int a) {
        System.out.println("进入【find】方法");
        return a;
    }

    /**
     * 根据请求对象 Object.hashCode() 缓存
     *
     * @return UserDTO
     */
    @Cacheable(value = "user:findUserDTO", key = "#request.hashCode()")
    public UserDTO findUserDTO(UserRequest request) {
        System.out.println("进入【findUserDTO】方法");
        return new UserDTO(request.getId(), request.getName(), new Date());
    }

    // list 请求对象缓存
    @Cacheable(value = "user:findUserDTOList", key = "#requestList.hashCode()")
    public List<UserDTO> findUserDTOList(List<UserRequest> requestList) {
        System.out.println("进入【findUserDTO】方法");
        return Arrays.asList(new UserDTO("1", "666", new Date()), new UserDTO("2", "888", new Date()));
    }

    @Cacheable(value = "user:findUserDTOList", key = "#objectMap.hashCode()")
    public List<UserDTO> findUserDTOList(Map<String,Object> objectMap) {
        System.out.println("进入【findUserDTO】方法");
        return Arrays.asList(new UserDTO("1", "666", new Date()), new UserDTO("2", "888", new Date()));
    }

    /**
     * 功能：将数据存入数据库的同时对数据进行缓存。
     * value指定缓存块名称
     * key指定数据的索引
     */
    @CachePut(value = "myCache", key = "#a")
    //@CacheEvict(value = "myCache", key = "#a") // 推荐删除,保证数据一致性
    public int update(int a) {
        System.out.println("进入【update】方法");
        return 2;
    }

    /**
     * @CacheEvict 功能：在指定的缓存块搜索数据，存在则从缓存中移除。
     * 实际应用：与数据库访问接口配合使用，如果数据存在于数据表中，会同时移除数据库中的数据。
     */
    @CacheEvict(value = "myCache", key = "#a")
    public int delete(int a) {
        System.out.println("进入【delete】方法");
        return a;
    }

    // 方法调用后 清空所有缓存
    @CacheEvict(value = "myCache", allEntries = true)
    // 方法调用前 清空所有缓存
    //@CacheEvict(value="myCache",beforeInvocation=true)
    public void delectAll() {
        System.out.println("进入【delectAll】方法");
    }
}
