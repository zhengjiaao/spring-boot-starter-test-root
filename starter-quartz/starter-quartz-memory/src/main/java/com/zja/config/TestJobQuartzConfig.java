/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-16 16:09
 * @Since:
 */
package com.zja.config;


import com.zja.job.TestJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestJobQuartzConfig {
    /**
     * 定时任务
     * @return
     */
    @Bean
    public JobDetail testJobDetail() {
        return JobBuilder.newJob(TestJob.class)
                .withIdentity("testJobDetail")
                .storeDurably()
                .build();
    }

    /**
     * 触发器，每间隔一段时间触发定时任务
     * @param jobDetail 具体执行的定时任务
     * @return
     */
    @Bean
    public Trigger testJobTrigger(@Qualifier("testJobDetail") JobDetail jobDetail) {
        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(20) // 定时任务间隔时间
                .repeatForever(); // 触发器无限循环触发
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("testJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
