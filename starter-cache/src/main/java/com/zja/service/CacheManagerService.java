/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-18 16:17
 * @Since:
 */
package com.zja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CacheManagerService {

    @Autowired
    private CacheManager cacheManager;

    public Cache getCache(String name) {
        return cacheManager.getCache(name);
    }

    public Set<String> getCacheNames() {
        return (Set<String>) cacheManager.getCacheNames();
    }


}
