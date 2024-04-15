package com.zja.entitys.base;

import cn.hutool.core.util.StrUtil;

import java.util.Set;

/**
 * 树形结构实体接口（抽取出通用部分）
 *
 * @author dengdh
 * @since 2022/11/22 09:35
 */
public interface IBaseTreeEntity<T extends IBaseTreeEntity<T>> {

    String getId();

    default String getName() {
        return "";
    };

    T getParent();

    void setParent(T parent);

    Set<T> getChildren();

    void setHasChildren(Boolean hasChildren);

    String getLevelIds();

    void setLevelIds(String levelIds);

    default String getLevelNames() {
        return "";
    }

    default void setLevelNames(String levelNames) {
    }

    default void updateParent(T parent) {
        // 排除掉自己的子集
        String newParentLevelIds = parent.getLevelIds();
        if (StrUtil.isNotEmpty(getLevelIds()) && newParentLevelIds.contains(getLevelIds())) {
            throw new UnsupportedOperationException("设置的父节点不能是自己或子集");
        }

        if (getParent() != null) {
            // 判断原有 parent 是否还具有子节点，如果没得则将 hasChildren 改为 false
            getParent().updateHasChildren((T) this);
        }

        // 更新当前节点的父节点
        setParent(parent);
        parent.setHasChildren(true);

        // 更新层级信息
        this.updateLevel(parent.getLevelNames(), parent.getLevelIds());
    }

    /**
     * 更新层级信息（id）
     *
     * @param parentLevelNames 父层级名称
     * @param parentLevelIds   父层级 id
     */
    default void updateLevel(String parentLevelNames, String parentLevelIds) {
        // 层级名称
        String parentLevelNamesCopy = StrUtil.isEmpty(parentLevelNames) ? "" : parentLevelNames + "/";
        this.setLevelNames(parentLevelNamesCopy + getName());
        // 层级 id
        String parentLevelIdsCopy = StrUtil.isEmpty(parentLevelIds) ? "" : parentLevelIds + "/";
        this.setLevelIds(parentLevelIdsCopy + getId());
        // 递归更新子集
        getChildren().forEach(x -> x.updateLevel(getLevelNames(), getLevelIds()));
    }

    /**
     * 更新是否还有子集
     */
    default void updateHasChildren(T entity) {
        getChildren().remove(entity);
        if (getChildren().isEmpty()) {
            setHasChildren(false);
        }
    }
}
