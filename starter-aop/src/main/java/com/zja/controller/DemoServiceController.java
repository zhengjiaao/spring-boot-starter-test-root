/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-04 15:32
 * @Since:
 */
package com.zja.controller;

import com.zja.dao.DemoDao;
import com.zja.service.DemoAService;
import com.zja.service.DemoBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
public class DemoServiceController {

    @Autowired
    DemoAService demoAService;

    @Autowired
    DemoBService demoBService;

    @Autowired
    DemoDao demoDao;

    @GetMapping("demoa")
    public String demoA() {
        return demoAService.find();
    }

    @GetMapping("demob")
    public String demoB() {
        return demoBService.find();
    }

    @GetMapping("demod")
    public String demoD() {
        return demoDao.test();
    }

}
