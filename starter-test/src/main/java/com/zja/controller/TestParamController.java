/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-16 15:35
 * @Since:
 */
package com.zja.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test/param")
public class TestParamController {

    @RequestMapping("/post/simple")
    public Map<String, String> simple(String id) {
        return new HashMap<String, String>() {{
            put("code", "200");
            put("id", id);
        }};
    }

}
