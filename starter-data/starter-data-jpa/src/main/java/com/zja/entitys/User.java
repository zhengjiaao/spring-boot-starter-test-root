/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-14 11:03
 * @Since:
 */
package com.zja.entitys;

import com.zja.entitys.base.BaseEntity;
import com.zja.util.IdGeneratorUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 用户表
 */
@Getter
@Setter
@Entity
@Table(name = "test_user", uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_login_name", columnNames = {"login_name"}),
        @UniqueConstraint(name = "uk_user_sort", columnNames = {"sort"})
})
@EntityListeners(value = AuditingEntityListener.class)
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Column(name = "user_name", nullable = false)
    private String name;

    /**
     * 登录名(不能重复)
     */
    @Column(name = "login_name", unique = true, nullable = false)
    private String loginName;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false, length = 2048)
    private String password;

    /**
     * 当前用户状态，小于 0 表示停用
     */
    @Column(name = "state", nullable = false)
    private Integer state;

    /**
     * 是否为内置用户（系统自动创建）
     */
    @Column(name = "internal", nullable = false)
    private Boolean internal = false;

    /**
     * 排列顺序
     */
    @Column(name = "sort", nullable = false, unique = true)
    private Long sort = IdGeneratorUtil.nextSortLong();

    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createTime;

    /**
     * 最后一次修改时间
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    /**
     * 用户在哪些组织机构下
     */
    @ManyToMany
    @JoinTable(name = "test_user_orgs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "orgs_id"))
    @OrderBy("sort")
    private Set<Org> orgs = new LinkedHashSet<>();

    /**
     * 用户直接拥有的角色
     */
    @ManyToMany(mappedBy = "users")
    @OrderBy("sort")
    private Set<Role> roles = new LinkedHashSet<>();

    /**
     * 用户属性信息 Attribute
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_attachment_id")
    private UserAttachment userAttachment;

    @PreRemove
    public void preRemove() {
        // 如果是内置的，不能删除
        if (internal) {
            throw new RuntimeException("内置用户不能删除");
        }
        // 需要将放弃维护的那个对象中的 user 对象移除
        roles.forEach(v -> v.getUsers().remove(this));
        roles.clear();
    }
}
