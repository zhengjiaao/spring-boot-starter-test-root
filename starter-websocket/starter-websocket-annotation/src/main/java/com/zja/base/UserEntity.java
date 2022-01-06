/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 13:06
 * @Since:
 */
package com.zja.base;

import lombok.Builder;
import lombok.Data;

/**
 *
 */
@Data
@Builder
public class UserEntity {
    private Long id;
    private String name;
    private Integer age;
}
