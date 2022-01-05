/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 15:28
 * @Since:
 */
package com.zja.model;

import lombok.Data;

@Data
public class Customer {
    private long age;

    public Customer(long age) {
        this.age = age;
    }
}
