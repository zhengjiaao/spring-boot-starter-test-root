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
 * 一对多：不存在中间表
 * <p>
 * 在数据库中，外键通常建立在子表中，指向父表的主键。
 * <p>
 * 外键关联，一般子实体类这边维护，因此由 Child 来关联
 *
 * @author: zhengja
 * @since: 2023/09/28 15:15
 */
@Data
@Entity
@Table(name = "om_b_child")
public class BChild {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    /**
     * 排列顺序
     */
    @Column(name = "sort", nullable = false, unique = true)
    private Long sort = IdGeneratorUtil.nextSortLong();

    // 推荐，比较好维护
    // (可选)方式 1：使用 Child 表关联方式，Child 表会生成一个字段，默认 parent_id
    @ManyToOne
    // @ManyToOne(cascade = CascadeType.REMOVE) // todo 不可用，无法删除，当父实体存在多个子实体时，由于外键导致无法删除。
    @JoinColumn(name = "parent_id") // 设置关联外键字段的名称：会在 om_d_child 表中生成 parent_id 外键字段
    // @JoinColumn(name = "", nullable = true, unique = false, insertable = true, updatable = true, referencedColumnName = "", columnDefinition = "") // 默认配置
    @OrderBy("sort")
    private BParent parent;

    // 推荐，但没有方式 1 好维护
    // (可选)方式 2：使用中间表关联方式，中间表自动创建，会生成两个字段
    // @ManyToOne
    // @JoinTable(name = "om_a_parent_childs",
    //         joinColumns = @JoinColumn(name = "child_id"), // 映射关系为: 当前 Child 实体id
    //         inverseJoinColumns = @JoinColumn(name = "parent_id")) // 映射关系为: Parent 实体id
    // private AParent parent;
}
