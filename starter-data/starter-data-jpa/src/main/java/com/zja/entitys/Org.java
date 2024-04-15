/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-21 14:53
 * @Since:
 */
package com.zja.entitys;

import com.zja.define.OrgTypeEnum;
import com.zja.entitys.base.BaseTreeEntity;
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
 * 组织机构表
 *
 * @author: zhengja
 * @since: 2023/07/21 14:53
 */
@Getter
@Setter
@Entity
@Table(name = "test_org")
@EntityListeners(value = AuditingEntityListener.class)
public class Org extends BaseTreeEntity<Org> {

    /**
     * 组织机构名称
     */
    @Column(name = "org_name", nullable = false)
    private String name;

    /**
     * 当前组织机构状态
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 是否为内置组织机构（系统自动创建）
     */
    @Column(name = "internal")
    private Boolean internal = false;

    /**
     * 组织机构类型
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "org_type")
    private OrgTypeEnum orgType;

    /**
     * 行政区划代码
     */
    @Column(name = "region_code")
    private String regionCode;

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

    @ManyToMany(mappedBy = "orgs")
    @OrderBy("sort")
    private Set<User> users = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "orgs")
    @OrderBy("sort")
    private Set<Role> roles = new LinkedHashSet<>();

    @Override
    public void preRemove() {
        // 如果是内置的，不能删除
        if (internal) {
            throw new RuntimeException("内置机构不能删除");
        }
        // 需要将放弃维护的那个对象中的 org,user 对象移除
        unbindOrg();
        super.preRemove();
    }

    public void setState(Integer state) {
        this.updateOrgState(state);
    }

    /**
     * 递归修改机构的状态
     *
     * @param state 状态
     */
    private void updateOrgState(Integer state) {
        this.state = state;
        this.getChildren().forEach(x -> x.updateOrgState(state));
    }

    private void unbindOrg() {
        // 级联解绑用户和角色
        roles.forEach(role -> role.getOrgs().remove(this));
        users.forEach(user -> user.getOrgs().remove(this));
        super.getChildren().forEach(Org::unbindOrg);
    }

}