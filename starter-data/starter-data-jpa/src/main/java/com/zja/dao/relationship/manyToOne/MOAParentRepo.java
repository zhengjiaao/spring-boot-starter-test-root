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
 * @since: 2024/04/22 15:52
 */
@Repository
public interface MOAParentRepo extends
        JpaRepository<MOAParent, Long>,
        CrudRepository<MOAParent, Long>,
        JpaSpecificationExecutor<MOAParent> {

    // 解除父实体与子实体的关联
    @Transactional
    @Modifying
    @Query("UPDATE MOAChild c SET c.parent = null WHERE c.parent.id = :parentId")
    void removeChildrenAssociation(@Param("parentId") Long parentId);


    // 删除父实体（存在外键，不能直接删除父实体，需要先解除绑定的子实体 或 先删除关联子实体）
    @Transactional
    @Modifying
    @Query("DELETE FROM MOAParent p WHERE p.id = :parentId")
    void deleteParent(@Param("parentId") Long parentId);

}
