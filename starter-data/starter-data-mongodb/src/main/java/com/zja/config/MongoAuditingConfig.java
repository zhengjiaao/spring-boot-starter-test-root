/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-25 13:24
 * @Since:
 */
package com.zja.config;

import org.springframework.context.annotation.Configuration;

/**
 * mongdb 审计功能启用
 *
 * @EnableMongoAuditing 启用审计功能，支持以下四个注解
 *      @CreatedBy 创建者
 *      @LastModifiedBy 更新者
 *      @CreatedDate 创建时间
 *      @LastModifiedDate 最后更新时间
 */
//@EnableMongoAuditing
@Configuration
public class MongoAuditingConfig {
}
