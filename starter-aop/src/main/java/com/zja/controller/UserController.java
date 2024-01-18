/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-04 11:17
 * @Since:
 */
package com.zja.controller;

import com.zja.aop.MyBodyParam;
import com.zja.aop.MyParam;
import com.zja.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class UserController {

    @GetMapping("{id}")
    public ResponseEntity queryById(@PathVariable Long id) {
        return ResponseEntity.ok(id);
    }

    @PostMapping("/user/v1")
    public ResponseEntity queryById(@RequestBody @MyBodyParam UserDTO userDTO) {
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/user/v1")
    public ResponseEntity queryById(@RequestParam String id, @RequestParam @MyParam String name) {
        return ResponseEntity.ok(id + "=" + name);
    }
}
