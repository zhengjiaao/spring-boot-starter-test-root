/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-15 15:20
 * @Since:
 */
package com.zja.controller;
import com.zja.entitys.UserEntity;
import com.zja.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/post/save")
    public Object save(@RequestParam String userName,
                       @RequestParam int age) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setAge(age);
        userEntity.setCreateTime(new Date());
        return userRepository.save(userEntity);
    }

    @GetMapping("/get/count")
    public Object countByUserName(@RequestParam String userName) {
        return userRepository.countByUserName(userName);
    }

    @GetMapping("/get/delete")
    public Object deleteByUserName(@RequestParam String userName) {
        return userRepository.deleteByUserName(userName);
    }

    @GetMapping("/get/page")
    public Object findByUserName(@RequestParam String userName,
                                 @RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize) {
        //当前页， 每页记录数
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return userRepository.findByUserName(userName, pageable);
    }

    /**
     * 列举了四种排序方式：
     * 1）直接创建Sort对象，适合对单一属性做排序
     * 2）通过Sort.Order对象创建Sort对象，适合对单一属性做排序
     * 3）通过属性的List集合创建Sort对象，适合对多个属性，采取同一种排序方式的排序
     * 4）通过Sort.Order对象的List集合创建Sort对象，适合所有情况，比较容易设置排序方式
     */
    @GetMapping("/get/sort")
    public Object findByUserName(@RequestParam String userName) {
        return userRepository.findByUserName(userName, Sort.by("id", "createTime"));
    }

    @GetMapping("/get/entityname")
    public Object entityname(@RequestParam String userName) {
        return userRepository.findByUserName(userName);
    }

    @GetMapping("{id}")
    public ResponseEntity queryById(@PathVariable Long id) {
        return ResponseEntity.ok(id);
    }
}
