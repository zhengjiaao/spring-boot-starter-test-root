/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-16 16:48
 * @Since:
 */
package com.zja.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 手动配置
 * 演示 CronScheduleBuilder
 */
@Component
public class SecondJobScheduleInit implements ApplicationRunner {

    private static final String ID = "SUMMERDAY";

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(SecondJob.class)
                .withIdentity(ID + " 02") //不能重复
                .storeDurably()
                .build();
        CronScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(ID + " 02Trigger") //不能重复
                .withSchedule(scheduleBuilder)
                .startNow() //立即執行一次任務
                .build();

        //添加调度任务：手动将触发器与任务绑定到调度器内
        // 不覆盖已有任务
//        scheduler.scheduleJob(jobDetail, trigger);

        Set<Trigger> setTriggers = new HashSet<>();
        setTriggers.add(trigger);
        //replace=true 覆盖已有任务，表示启动时对数据库中的quartz任务进行覆盖
        scheduler.scheduleJob(jobDetail, setTriggers, true);
    }
}
