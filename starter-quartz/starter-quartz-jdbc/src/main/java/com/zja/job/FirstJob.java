/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-16 16:45
 * @Since:
 */
package com.zja.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自动配置
 * @DisallowConcurrentExecution 注解，禁止并发执行，保证了在集群环境中，定时任务一次只有一台服务器在运行. 可选的
 */
@Slf4j
@DisallowConcurrentExecution
public class FirstJob extends QuartzJobBean {

    private final AtomicInteger counts = new AtomicInteger();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        log.info("FirstJob 执行第{}次, 当前时间: {}", counts.incrementAndGet(), now);
    }
}
