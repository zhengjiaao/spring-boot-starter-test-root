/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 15:15
 * @Since:
 */
package com.zja.entitys.relationship.manyToOne;

import lombok.Data;

import javax.persistence.*;

/**
 * 子实体
 * <p>
 * 一对多：存在中间表
 * <p>
 * 外键关联，一般子实体类这边维护，因此由 Child 来关联
 *
 * @author: zhengja
 * @since: 2023/09/28 15:15
 */
@Data
@Entity
@Table(name = "mo_a_child")
public class MOAChild {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    // (可选)方式 1：使用Child表关联方式：Child表会生成一个字段，默认 parent_id
   @ManyToOne
//    @JoinColumn(name = "parent_id2")
//    @JoinColumn(name = "parent_id3", referencedColumnName = "id")  // (可选地),referencedColumnName若为 Parent 主键id，则可以省略。
   private MOAParent parent;

    // (可选)方式 2：使用中间表关联方式：child_parent中间表自动创建，会生成两个字段
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinTable(name = "child_parent",
//            joinColumns = @JoinColumn(name = "child_id"),
//            inverseJoinColumns = @JoinColumn(name = "parent_id"))
//    private Parent parent;
}
