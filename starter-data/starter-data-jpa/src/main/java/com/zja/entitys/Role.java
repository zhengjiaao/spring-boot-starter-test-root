/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-21 15:01
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
 * 角色表
 *
 * @author: zhengja
 * @since: 2023/07/21 15:01
 */
@Getter
@Setter
@Entity
@Table(name = "test_role", uniqueConstraints = {
        @UniqueConstraint(name = "uk_role_name", columnNames = {"role_name"}),
        @UniqueConstraint(name = "uk_role_sort", columnNames = {"sort"})
})
@EntityListeners(value = AuditingEntityListener.class)
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

    /**
     * 角色描述
     */
    private String remarks;

    /**
     * 当前角色状态，小于 0 表示停用
     */
    @Column(name = "state", nullable = false)
    private Integer state = 1;

    /**
     * 是否为内置角色（系统自动创建）
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

    @ManyToMany
    @JoinTable(name = "test_role_orgs",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "orgs_id"))
    @OrderBy("sort")
    private Set<Org> orgs = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "test_role_users",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    @OrderBy("sort")
    private Set<User> users = new LinkedHashSet<>();

    @PreRemove
    public void preRemove() {
        // 如果是内置的，不能删除
        if (internal) {
            throw new RuntimeException("内置角色不能删除");
        }
    }
}