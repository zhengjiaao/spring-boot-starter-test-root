/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-25 13:55
 * @Since:
 */
package com.zja.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class OneJobExecutionListener implements JobExecutionListener {

    private Logger logger = LoggerFactory.getLogger(OneJobExecutionListener.class);

    private long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        logger.info("beforeJob 任务处理开始...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long endTime = System.currentTimeMillis();
        logger.info("afterJob 任务处理结束，耗时:{} ms", (endTime - startTime));
    }
}
