package com.zja.dao.relationship.manyToOne;

import com.zja.entitys.relationship.manyToOne.MOAChild;
import com.zja.entitys.relationship.manyToOne.MOAParent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhengja
 * @since: 2024/04/22 15:51
 */
@Repository
public interface MOAChildRepo extends
        JpaRepository<MOAChild, Long>,
        CrudRepository<MOAChild, Long>,
        JpaSpecificationExecutor<MOAChild> {

    // 按父实体分页查询其关联的子实体
    @Query("SELECT c FROM MOAChild c WHERE c.parent = :parent")
    Page<MOAChild> findChildrenByParent(@Param("parent") MOAParent parent, Pageable pageable);

    // 按父实体删除关联的子实体
    @Transactional
    void deleteByParent(MOAParent parent);

    // 删除父实体及其关联的子实体
    @Transactional
    @Modifying
    @Query("DELETE FROM MOAChild c WHERE c.parent = :parent")
    void deleteChildrenByParent(@Param("parent") MOAParent parent);

    // 解除父实体与子实体之间的关联
    @Transactional
    @Modifying
    @Query("UPDATE MOAChild c SET c.parent = null WHERE c.parent.id = :parentId")
    void removeChildrenAssociation(@Param("parentId") Long parentId);

}
