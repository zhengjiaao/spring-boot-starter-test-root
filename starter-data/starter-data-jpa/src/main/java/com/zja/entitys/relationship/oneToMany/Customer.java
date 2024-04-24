/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 14:37
 * @Since:
 */
package com.zja.entitys.relationship.oneToMany;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/09/28 14:37
 */
@Data
@Entity
@Table(name = "om_customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerNumber")
    private Integer id;

    private String name; // 顾客名字

    @OneToMany // 一对多
    // referencedColumnName 在JoinTable注释中使用时，引用的键列(joinColumns)位于所属实体的实体表中，如果联接是反向联接(inverseJoinColumns)定义的一部分，则位于反向实体中。默认值（仅适用于使用单个联接列的情况）：与引用表的主键列同名。
    @JoinTable( //@JoinTable 使用关联表方式实现
            name = "om_customer_orders", // (可选的)默认为两个关联的主实体表的串联名称，用下划线分隔。
            joinColumns = @JoinColumn(name = "cid", referencedColumnName = "customerNumber"), // 以 cid 为外键进行关联，referencedColumnName 默认为被关联实体类主键，可以忽略
            inverseJoinColumns = @JoinColumn(name = "oid", referencedColumnName = "orderNumber")
    )
    private List<Order> orders = new ArrayList<>();
}
