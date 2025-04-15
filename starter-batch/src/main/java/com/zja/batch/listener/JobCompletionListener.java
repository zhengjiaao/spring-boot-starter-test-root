package com.zja.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * 作业监听器
 *
 * @Author: zhengja
 * @Date: 2025-04-15 17:14
 */
@Component
public class JobCompletionListener extends JobExecutionListenerSupport {
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            long duration = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
            System.out.println("!!! JOB FINISHED! Time: " + duration + "ms");
        }
    }
}