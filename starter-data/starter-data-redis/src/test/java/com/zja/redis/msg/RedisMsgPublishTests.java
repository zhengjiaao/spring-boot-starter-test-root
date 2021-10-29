/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-28 17:04
 * @Since:
 */
package com.zja.redis.msg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试Redis 发布、订阅
 * 先启动 {@link com.zja.RedisApplication},再进行测试类
 */
@SpringBootTest
public class RedisMsgPublishTests {

    @Autowired
    private RedisMsgPublish redisMsgPublish;

    @Test
    public void pubMsg_V1() {
        redisMsgPublish.pubMsg("channel-1", "channel-1 消息体");
    }

    @Test
    public void pubMsg_V2() {
        redisMsgPublish.pubMsg("channel-2", "channel-2 消息体");
    }
}
