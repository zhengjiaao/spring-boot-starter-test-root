/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 16:34
 * @Since:
 */
package com.zja.redis.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

/**
 *
 */
@Data
@Builder
@RedisHash("user")
public class UserEntity {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private Date createTime = new Date();
}
