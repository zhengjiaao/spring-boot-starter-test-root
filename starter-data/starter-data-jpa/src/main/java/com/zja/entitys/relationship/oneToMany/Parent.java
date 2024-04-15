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

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: zhengja
 * @since: 2023/09/28 15:15
 */
@Data
@Entity
@Table(name = "parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true) //级联所有操作(例：级联删除)，允许独立删除Child
//    private List<Child> children = new ArrayList<>();

    //注意：mappedBy 与 @JoinTable 冲突
//    @OneToMany
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true) //级联删除Child，允许独立删除Child
    @JoinTable(name = "parent_childs",
            joinColumns = @JoinColumn(name = "parent_id"), //joinColumns 引用所属实体表id
            inverseJoinColumns = @JoinColumn(name = "child_id")) // inverseJoinColumns 引用所属关联表id
    private Set<Child> children = new HashSet<>();

}
