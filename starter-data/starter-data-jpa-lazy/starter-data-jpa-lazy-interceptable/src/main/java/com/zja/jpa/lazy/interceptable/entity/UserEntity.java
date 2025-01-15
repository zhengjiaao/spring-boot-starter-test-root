/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-18 16:20
 * @Since:
 */
package com.zja.jpa.lazy.interceptable.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_user")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private int age;
}
