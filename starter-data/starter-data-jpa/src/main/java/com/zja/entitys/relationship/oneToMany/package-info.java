package com.zja.entitys.relationship.oneToMany;

/*

一对多，存在两种情况：
1、无中间表（支持可重复、不可重复）
2、存在中间表（支持可重复、不可重复）

todo 一般由子实体维护中间表和外键，或者 由多的一方去维护中间表和外键。

1、@OneToMany 用于在父实体中定义一对多的关联关系，父实体持有子实体的集合或列表。（不方便分页查询）
2、@ManyToOne 用于在子实体中定义多对一的关联关系，子实体引用关联的父实体。（方便分页查询）

问题汇总：

1、jpa 一对多 外键一般由子类维护还是父类维护?

在JPA中，一对多关系中的外键通常由父实体（父类）来维护。

子实体（子类）保留对父实体的引用。在数据库中，外键通常建立在子表中，指向父表的主键。
在一对多关系中，通常有一个拥有主键的父实体（一方）和多个子实体（多方）。在JPA中，父实体将拥有一个指向子实体的外键。这种关系通常被称为"单向一对多关系"。

示例：

@Entity
public class Parent {
    @Id
    private Long id;

    // ...

    @OneToMany(mappedBy = "parent")
    private List<Child> children;

    // ...
}

@Entity
public class Child {
    @Id
    private Long id;

    // ...

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    // ...
}

2、private List<Child> children; 和 private Set<Child> children; 区别？

如果在一对多关系中的父实体中，子实体的顺序很重要，或者可能存在重复的子实体对象，那么可以选择使用List。
如果子实体的顺序不重要，且需要确保在父实体中不包含重复的子实体对象，那么可以选择使用Set。

3、如何手动删除数据，有外键关联时，无法删除表里的数据？

先删除中间表关联的数据，再删除子数据或父数据。

 */
