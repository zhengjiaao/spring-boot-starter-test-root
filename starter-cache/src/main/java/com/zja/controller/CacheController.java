/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-18 16:08
 * @Since:
 */
package com.zja.controller;

import com.zja.dto.UserDTO;
import com.zja.service.CacheDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheDataService cacheDataService;

    @GetMapping("/save")
    public int save(int a) {
        return cacheDataService.save(a);
    }

    @GetMapping("/find")
    public int find(int a) {
        return cacheDataService.find(a);
    }

    @PutMapping("/update")
    public int update(int a) {
        return cacheDataService.update(a);
    }

    @GetMapping("/delete")
    public int delete(int a) {
        return cacheDataService.delete(a);
    }

    @GetMapping("/delect/all")
    public void delectAll() {
        cacheDataService.delectAll();
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "user", key = "#name")
            },
            put = {
                    @CachePut(value = "user", key = "#result.id"),
                    @CachePut(value = "user", key = "#result.name"),
            }
    )
    @GetMapping("/find/user")
    public UserDTO getUserByName(String name) {

        System.out.println("进入【getUserByName】方法");

        //真实环境
        //UserDTO userDTO = userService.getUserByName(lastName);
        //模拟
        UserDTO userDTO = new UserDTO();
        userDTO.setId("123");
        userDTO.setName(name);
        userDTO.setDate(new Date());

        return userDTO;
    }
}
