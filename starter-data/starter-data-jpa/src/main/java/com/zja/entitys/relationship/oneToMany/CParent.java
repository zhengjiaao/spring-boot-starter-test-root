/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 15:15
 * @Since:
 */
package com.zja.entitys.relationship.oneToMany;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: zhengja
 * @since: 2023/09/28 15:15
 */
@Data
@Entity
@Table(name = "om_c_parent")
public class CParent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    // 配合 子实体 维护外键方式
    // @OneToMany(mappedBy = "parent")
    // private List<CChild> children = new ArrayList<>();

    // todo 不推荐此方式，不好维护 应该由多的一方维护中间表，方便分页查询
    // 父实体 维护外键方式
    @OneToMany  // 注意：mappedBy 与 @JoinTable 会冲突，不能同时使用
    // @OneToMany(cascade = CascadeType.ALL)  // 级联操作Child子实体
    @JoinTable(name = "om_c_parent_childs",
            joinColumns = @JoinColumn(name = "parent_id"),          // joinColumns 引用所属实体表id
            inverseJoinColumns = @JoinColumn(name = "child_id"))    // inverseJoinColumns 引用所属关联表id
    @OrderBy("sort")
    // 默认值详解 @OneToMany : mappedBy关联对象、cascade级联操作Child、fetch加载方式、orphanRemoval 不允许单独删除Child
    // @OneToMany(mappedBy = "", cascade = {}, fetch = FetchType.LAZY, orphanRemoval = false)
    private Set<CChild> children = new HashSet<>();
    // private List<CChild> children = new ArrayList<>();


    //@OneToMany 用于在父实体中定义一对多的关联关系，父实体持有子实体的集合或列表。但是不方便分页查询子实体，需要自定义查询。
    public Page<CChild> getChildrenPage(int pageNumber, int pageSize) {
        // private Set<CChild> children = new HashSet<>();
        List<CChild> childList = new ArrayList<>(children);  // 将子实体集合转换为列表

        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min((startIndex + pageSize), childList.size());

        List<CChild> pageChildren = childList.subList(startIndex, endIndex);
        return new PageImpl<>(pageChildren, PageRequest.of(pageNumber, pageSize), childList.size());
    }

    // public Page<CChild> getChildrenPage(int pageNumber, int pageSize) {
    //     // private List<CChild> children = new ArrayList<>();
    //     Pageable pageable = PageRequest.of(pageNumber, pageSize);
    //     int startIndex = (int) pageable.getOffset();
    //     int endIndex = Math.min((startIndex + pageable.getPageSize()), children.size());
    //
    //     List<CChild> pageChildren = children.subList(startIndex, endIndex);
    //     return new PageImpl<>(pageChildren, pageable, children.size());
    // }
}
