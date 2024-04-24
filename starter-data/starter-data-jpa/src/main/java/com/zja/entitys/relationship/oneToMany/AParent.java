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
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 父实体
 * <p>
 * 一对多：存在中间表
 * <p>
 * 外键关联，一般子实体类这边维护，因此由 Child 来关联
 *
 * @author: zhengja
 * @since: 2023/09/28 15:15
 */
@Getter // 不要使用@Data含toString()方法，避免循环引用，导致StackOverflowError 异常
@Setter
@Entity
@Table(name = "om_a_parent")
public class AParent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    /**
     * 排列顺序
     */
    @Column(name = "sort", nullable = false, unique = true)
    private Long sort = IdGeneratorUtil.nextSortLong();

    // 配合 子实体 维护外键方式
    @OneToMany(mappedBy = "parent")
    // @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) // todo 不可用，不会级联存储中间表关联记录
    private List<AChild> children = new ArrayList<>();
    // private Set<AChild> children = new HashSet<>();


    // todo 不推荐此方式，不好维护，应该由多的一方维护中间表，方便分页查询
    // 父实体 维护外键方式
    // @OneToMany  // 注意：mappedBy 与 @JoinTable 会冲突，不能同时使用
    // // @OneToMany(cascade = CascadeType.ALL)  // 级联操作Child子实体
    // @JoinTable(name = "om_a_parent_childs",
    //         joinColumns = @JoinColumn(name = "parent_id"),          // joinColumns 引用所属实体表id
    //         inverseJoinColumns = @JoinColumn(name = "child_id"))    // inverseJoinColumns 引用所属关联表id
    // @OrderBy("sort")
    // // 默认值详解 @OneToMany : mappedBy关联对象、cascade级联操作Child、fetch加载方式、orphanRemoval 不允许单独删除Child
    // // @OneToMany(mappedBy = "", cascade = {}, fetch = FetchType.LAZY, orphanRemoval = false)
    // private Set<AChild> children = new HashSet<>();
    // // private List<AChild> children = new ArrayList<>();


    //@OneToMany 用于在父实体中定义一对多的关联关系，父实体持有子实体的集合或列表。但是不方便分页查询子实体，需要自定义查询。
    // public Page<AChild> getChildrenPage(int pageNumber, int pageSize) {
    //     // private Set<AChild> children = new HashSet<>();
    //     List<AChild> childList = new ArrayList<>(children);  // 将子实体集合转换为列表
    //
    //     int startIndex = pageNumber * pageSize;
    //     int endIndex = Math.min((startIndex + pageSize), childList.size());
    //
    //     List<AChild> pageChildren = childList.subList(startIndex, endIndex);
    //     return new PageImpl<>(pageChildren, PageRequest.of(pageNumber, pageSize), childList.size());
    // }

    // public Page<AChild> getChildrenPage(int pageNumber, int pageSize) {
    //     // private List<AChild> children = new ArrayList<>();
    //     Pageable pageable = PageRequest.of(pageNumber, pageSize);
    //     int startIndex = (int) pageable.getOffset();
    //     int endIndex = Math.min((startIndex + pageable.getPageSize()), children.size());
    //
    //     List<AChild> pageChildren = children.subList(startIndex, endIndex);
    //     return new PageImpl<>(pageChildren, pageable, children.size());
    // }
}
