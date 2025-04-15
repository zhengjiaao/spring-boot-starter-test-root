package com.zja.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 17:31
 */
@RestController
public class BatchUserController {

    @Resource
    @Qualifier("importUserJob")
    private Job job;

    @Resource
    private JobLauncher launcher;

    // http://localhost:8080/batch/user
    // 手动启动批处理任务
    @RequestMapping("/batch/user")
    public void batchUser() {
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        try {
            launcher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
