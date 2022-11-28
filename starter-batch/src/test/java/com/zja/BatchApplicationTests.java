/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-25 14:02
 * @Since:
 */
package com.zja;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class BatchApplicationTests {

    @Resource
    @Qualifier("oneJob")
    private Job job;

    @Resource
    private JobLauncher launcher;

    @Test
    public void oneJob_batch_test() {
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        try {
            launcher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException
                | JobRestartException
                | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
