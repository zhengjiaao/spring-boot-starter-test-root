package com.zja.service;

import com.zja.model.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhengja
 * @since: 2024/04/11 10:44
 */
@SpringBootTest
public class CacheDataServiceTest {

    @Autowired
    CacheDataService service;

    // 简单 保存缓存、更新缓存、查找缓存、删除缓存
    @Test
    public void test_1() {
        service.save(1); // 进入方法，保存数据，并缓存数据
        int i = service.find(1); // 不进入方法，直接从缓存中取数据
        System.out.println(i);

        service.update(1); // 进入方法，更新数据，并更新缓存数据
        int i2 = service.find(1); // 不进入方法，直接从缓存中取数据
        System.out.println(i2);

        service.delete(1); // 进入方法，删除数据，并删除缓存
        service.delectAll(); // 进入方法，删除全部数据，并删除全部缓存
    }

    // 对象
    @Test
    public void test_2() {
        service.findUserDTO(new UserRequest("1", "李四")); // 进入方法，返回数据，并进行缓存数据
        service.findUserDTO(new UserRequest("1", "李四")); // 不进入方法，直接从缓存中取数据

        service.findUserDTO(new UserRequest("2", "李四")); // 进入方法，返回数据，并进行缓存数据
    }

    // list
    @Test
    public void test_3() {
        List<UserRequest> requestList = new ArrayList<>();
        requestList.add(new UserRequest("1", "李四"));
        service.findUserDTOList(requestList); // 进入方法，返回数据，并进行缓存数据
        service.findUserDTOList(requestList); // 不进入方法，直接从缓存中取数据

        List<UserRequest> requestList2 = new ArrayList<>();
        requestList2.add(new UserRequest("1", "李四"));
        requestList2.add(new UserRequest("2", "李四"));
        service.findUserDTOList(requestList2); // 进入方法，返回数据，并进行缓存数据
    }

    // map
    @Test
    public void test_4() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("1", "李四");

        service.findUserDTOList(objectMap);// 进入方法，返回数据，并进行缓存数据
        service.findUserDTOList(objectMap);// 不进入方法，直接从缓存中取数据

        Map<String, Object> objectMap2 = new HashMap<>();
        objectMap2.put("2", "李四");
        service.findUserDTOList(objectMap2);// 进入方法，返回数据，并进行缓存数据
    }

}
