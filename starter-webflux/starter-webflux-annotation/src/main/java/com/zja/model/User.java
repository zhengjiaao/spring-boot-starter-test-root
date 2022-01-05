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

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String name;
    private long age;

    public User(String name) {
        this.name = name;
    }

    public User(String name, long age) {
        this.name = name;
        this.age = age;
    }
}
