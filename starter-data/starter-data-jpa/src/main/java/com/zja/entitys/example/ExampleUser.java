package com.zja.entitys.example;

/**
 * @author: zhengja
 * @since: 2024/04/15 13:12
 */

import com.zja.entitys.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "example_user")
public class ExampleUser extends BaseEntity {

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String name;

    /**
     * 用户名
     */
    @Column(name = "LOGIN_NAME", nullable = false)
    private String loginName;

    /**
     * 住址
     */
    private String address;

    /**
     * 年龄
     */
    private int age;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
