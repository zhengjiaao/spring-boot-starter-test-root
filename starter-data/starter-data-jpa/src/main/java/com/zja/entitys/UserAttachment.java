/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 11:25
 * @Since:
 */
package com.zja.entitys;

import com.zja.entitys.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: zhengja
 * @since: 2023/08/10 11:25
 */
@Getter
@Setter
@Entity
@Table(name = "test_user_attribute")
public class UserAttachment extends BaseEntity {

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 手机号码
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 职位
     */
    @Column(name = "position")
    private String position;
}