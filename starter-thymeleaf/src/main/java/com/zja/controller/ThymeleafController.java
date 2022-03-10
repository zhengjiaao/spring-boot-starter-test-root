/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-10 10:29
 * @Since:
 */
package com.zja.controller;

import com.zja.properties.ServerSetttings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @Autowired
    private ServerSetttings serverSetttings;

    //http://127.0.0.1:8080/thymeleaf/hello
    @GetMapping("hello")
    public String queryById() {
        return "thymeleaf/index";
    }

    //http://127.0.0.1:8080/thymeleaf/hello/v2
    @RequestMapping("hello/v2")
    public String hello(Model model) {
        model.addAttribute("server", serverSetttings);
        return "thymeleaf/index";
    }
}
