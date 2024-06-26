/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 12:39
 * @Since:
 */
package com.zja.entitys.relationship.oneToOne;

import lombok.Data;

import javax.persistence.*;

/**
 * 雇员
 *
 * @author: zhengja
 * @since: 2023/09/28 12:39
 */
@Data
@Entity
@Table(name = "oo_employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employeeNumber")
    private Integer id;

    private String employeeName; // 雇员名称

    @OneToOne // 默认情况下不级联任何操作。 默认值有：@OneToOne(fetch = FetchType.EAGER, optional = true, orphanRemoval = true)
    // @OneToOne(cascade = CascadeType.REMOVE) // 设置级联删除Office，若单独删除Office会报错。
    // 默认值详解: @OneToOne fetch 默认级联查询方式急加载、cascade
    // @OneToOne(mappedBy = "", fetch = FetchType.EAGER, cascade = {}, optional = true, orphanRemoval = false)
    @JoinColumn(name = "officeCode")
    private Office office; // 每个雇员仅有一个办公室。一个办公室仅有一个雇员使用。
}