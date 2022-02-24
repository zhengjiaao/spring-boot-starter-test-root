/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-18 16:08
 * @Since:
 */
package com.zja.controller;

import com.zja.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private DataService dataService;

    @PostMapping("/save")
    public int save(){
        return dataService.save();
    }

    @GetMapping("/save2")
    public int save(int a){
        return dataService.save(a);
    }

    @PutMapping("/update")
    public int update(int a){
        return dataService.update(a);
    }

    @GetMapping("/delete")
    public int delete(int a){
        return dataService.delete(a);
    }

}
