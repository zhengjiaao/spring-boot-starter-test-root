///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2022-03-11 11:12
// * @Since:
// */
//package com.zja.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/batch")
//public class UserBatchController {
//
//   /* @Autowired
//    JobLauncher jobLauncher;
//    @Autowired
//    Job job;*/
//
//    /**
//     * 启动一个批处理
//     * http://127.0.0.1:8080/batch/csv
//     */
//    @GetMapping("/csv")
//    public String hello() {
//        try {
//            //JobLauncher由框架提供，Job则是刚刚配置的，通过调用JobLauncher中的run方法启动一个批处理
////            jobLauncher.run(job, new JobParameters());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "执行任务失败.";
//        }
//        return "执行任务成功.";
//    }
//
//}
