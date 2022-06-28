/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-06-27 14:00
 * @Since:
 */
package com.zja.dubbo.consumer;

import com.zja.dubbo.api.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class DemoController {

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    DemoService demoService;

    /**
     * http://localhost:8081/consumer/dubbo/sayHello
     */
    @GetMapping("/sayHello")
    public ResponseEntity queryById() {
        return ResponseEntity.ok(demoService.sayHello("李四"));
    }
}
