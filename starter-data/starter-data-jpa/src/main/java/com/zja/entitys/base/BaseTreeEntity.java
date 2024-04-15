/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-09 15:19
 * @Since:
 */
package com.zja.entitys.base;

import com.zja.util.IdGeneratorUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: zhengja
 * @since: 2023/08/09 15:19
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseTreeEntity<T extends BaseTreeEntity<T>> extends BaseEntity implements IBaseTreeEntity<T> {

    /**
     * 排列顺序
     */
    @Column(name = "sort", nullable = false, unique = true)
    private Long sort = IdGeneratorUtil.nextSortLong();

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "parent_id")
    private T parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    @OrderBy("sort")
    private Set<T> children = new LinkedHashSet<>();

    /**
     * 是否有子集
     */
    @Column(name = "has_children")
    private Boolean hasChildren = false;

    /**
     * 层级 id
     */
    @Column(name = "level_ids")
    private String levelIds;

    /**
     * 层级名称
     */
    @Column(name = "level_names")
    private String levelNames;

    @PreRemove
    public void preRemove() {
        // 更新是否有子集
        if (this.parent.getChildren().size() == 1) {
            this.parent.setHasChildren(false);
        }
    }
}
