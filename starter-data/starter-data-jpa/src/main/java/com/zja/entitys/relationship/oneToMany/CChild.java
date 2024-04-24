/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 15:15
 * @Since:
 */
package com.zja.entitys.relationship.oneToMany;

import com.zja.util.IdGeneratorUtil;
import lombok.Data;

import javax.persistence.*;

/**
 * 外键关联，一般子实体类这边维护，因此由 Child 来关联
 *
 * @author: zhengja
 * @since: 2023/09/28 15:15
 */
@Data
@Entity
@Table(name = "om_c_child")
public class CChild {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    /**
     * 排列顺序
     */
    @Column(name = "sort", nullable = false, unique = true)
    private Long sort = IdGeneratorUtil.nextSortLong();

    // 外键关联，一般子实体类这边维护，因此由 Child 来关联
    // Child 表会生成一个字段，默认 parent_id

    // 子实体 维护外键方式：子实体(含外键)、子实体+中间表(含外键)

    // (可选)方式 1：使用 Child 表关联方式，Child 表会生成一个字段，默认 parent_id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id2")
//    @JoinColumn(name = "parent_id3", referencedColumnName = "id")  // (可选地),referencedColumnName若为 Parent 主键id，则可以省略。
//    private CParent parent;

    // (可选)方式 2：使用中间表关联方式，中间表自动创建，会生成两个字段
    // @ManyToOne
    // @JoinTable(name = "child_parent",
    //         joinColumns = @JoinColumn(name = "child_id"),
    //         inverseJoinColumns = @JoinColumn(name = "parent_id"))
    // private CParent parent;
}
