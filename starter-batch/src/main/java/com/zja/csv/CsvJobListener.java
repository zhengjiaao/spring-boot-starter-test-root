///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2022-11-24 17:16
// * @Since:
// */
//package com.zja.csv;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobExecutionListener;
//
//@Slf4j
//public class CsvJobListener implements JobExecutionListener {
//
//    private long startTime;
//
//    @Override
//    public void beforeJob(JobExecution jobExecution) {
//        startTime = System.currentTimeMillis();
//        log.info("任务处理开始...");
//    }
//
//    @Override
//    public void afterJob(JobExecution jobExecution) {
//        long endTime = System.currentTimeMillis();
//        log.info("任务处理结束，耗时:{} ms", (endTime - startTime));
//    }
//}
