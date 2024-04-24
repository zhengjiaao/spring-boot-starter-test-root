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
import java.util.ArrayList;
import java.util.List;

/**
 * 一对多：不存在中间表
 * <p>
 * 外键关联，一般子实体类这边维护，因此由 Child 来关联
 *
 * @author: zhengja
 * @since: 2023/09/28 15:15
 */
@Data
@Entity
@Table(name = "om_b_parent")
public class BParent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    /**
     * 排列顺序
     */
    @Column(name = "sort", nullable = false, unique = true)
    private Long sort = IdGeneratorUtil.nextSortLong();

    @OneToMany(mappedBy = "parent")
    // @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) //todo 不可用，无法做到关联外键问题。 保存时，会先保存子实体，再关联子实体，但是无法关联外键。
    // @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE) // 删除操作时：会先删除关联的所有子实体，再删除父实体
    @OrderBy("sort")
    // mappedBy 拥有关系的字段（对应Child 属性名称 DParent parent）。必需，除非关系是单向的。
    // @OneToMany(mappedBy = "", cascade = {}, fetch = FetchType.LAZY, orphanRemoval = false) // 默认值详解: mappedBy 拥有关系的字段。必需，除非关系是单向的。 cascade 默认为不级联任何操作。 fetch 默认懒加载。orphanRemoval 将删除操作应用于已从关系中删除的实体
    private List<BChild> children = new ArrayList<>(); // 支持重复，排序
    // private Set<DChild> children = new ArrayList<>();//不重复，无序

}
