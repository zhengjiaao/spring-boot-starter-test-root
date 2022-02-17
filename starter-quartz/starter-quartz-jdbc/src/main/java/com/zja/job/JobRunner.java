/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-17 17:03
 * @Since:
 */
package com.zja.job;

import com.zja.utils.QuartzSchedubleJobUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class JobRunner implements CommandLineRunner {

    @Autowired
    private QuartzSchedubleJobUtil quartzSchedubleJobUtil;

    @Override
    public void run(String... args) throws Exception {
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",1);
        quartzSchedubleJobUtil.deleteJob("job", "test");
        quartzSchedubleJobUtil.addJob(Job.class, "job", "test", "0 * * * * ?", map);

        map.put("name",2);
        quartzSchedubleJobUtil.deleteJob("job2", "test");
        quartzSchedubleJobUtil.addJob(Job.class, "job2", "test", "10 * * * * ?", map);

        map.put("name",3);
        quartzSchedubleJobUtil.deleteJob("job3", "test2");
        quartzSchedubleJobUtil.addJob(Job.class, "job3", "test2", "15 * * * * ?", map);
    }
}
