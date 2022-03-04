/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-04 13:12
 * @Since:
 */
package com.zja.controller;

import com.zja.aop.WebLogFilter;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

    @WebLogFilter(value = "WebLogFilter 测试log功能", desc = "必须传入")
    @ApiOperation("测试log功能")
    @GetMapping("{id}")
    public ResponseEntity queryById(@PathVariable Long id) {

        //异常
        if (0 == id) {
            System.out.println("throw an exception");
            throw new RuntimeException("mock a server exception");
        }

        //正常
        System.out.println("test OK");
        return ResponseEntity.ok(id);
    }
}
