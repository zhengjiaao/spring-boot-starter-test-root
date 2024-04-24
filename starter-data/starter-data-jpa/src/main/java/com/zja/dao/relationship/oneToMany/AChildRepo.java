/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 15:19
 * @Since:
 */
package com.zja.dao.relationship.oneToMany;

import com.zja.entitys.relationship.manyToMany.Course;
import com.zja.entitys.relationship.manyToOne.MOAChild;
import com.zja.entitys.relationship.manyToOne.MOAParent;
import com.zja.entitys.relationship.oneToMany.AChild;
import com.zja.entitys.relationship.oneToMany.AParent;
import com.zja.entitys.relationship.oneToMany.BChild;
import com.zja.entitys.relationship.oneToMany.BParent;
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
 * @since: 2023/09/28 15:19
 */
@Repository
public interface AChildRepo extends
        JpaRepository<AChild, Long>,
        CrudRepository<AChild, Long>,
        JpaSpecificationExecutor<AChild> {

    // 解除父实体与子实体的关联
    // delete from om_a_parent_childs where child_id=?
    // delete from om_a_parent_childs where parent_id=?
    @Transactional
    @Modifying
    @Query(value = "delete from om_a_parent_childs where child_id = :childId", nativeQuery = true)
    void unbindChildren(@Param("childId") Long childId);

    // todo 会导致中间表记录未删除，只是把 parent_id字段设置为null
    // 解除父实体与子实体的关联
    // @Transactional
    // @Modifying
    // @Query("UPDATE AChild c SET c.parent = null WHERE c.parent = :parent")
    // void unbindChildren(@Param("parent") AParent parent);

    // todo 会导致中间表记录未删除，只是把 parent_id字段设置为null
    // 解除父实体与子实体的关联
    // @Transactional
    // @Modifying
    // @Query("UPDATE AChild c SET c.parent = null WHERE c.parent.id = :parentId")
    // void unbindChildren(@Param("parentId") Long parentId);

    // todo 会导致中间表记录未删除，只是把 parent_id字段设置为null
    // 解除父实体与子实体的关联
    // @Transactional
    // @Modifying
    // @Query("UPDATE AChild c SET c.parent = null WHERE c.parent.id = :parentId")
    // void removeChildrenAssociation(@Param("parentId") Long parentId);

    // 按父实体分页查询其关联的子实体
    @Query("SELECT c FROM AChild c WHERE c.parent = :parent")
    Page<AChild> findChildrenByParent(@Param("parent") AParent parent, Pageable pageable);

    // 按父实体分页查询其关联的子实体
    @Query("SELECT c FROM AChild c WHERE c.parent.id = :parentId")
    Page<AChild> findChildrenByParent(@Param("parentId") Long parentId, Pageable pageable);

    // 删除关联的子实体
    @Transactional
    @Modifying
    @Query("DELETE FROM AChild c WHERE c.parent.id = :parentId")
    void deleteChildrenByParent(@Param("parentId") Long parentId);

    // 删除关联的子实体
    @Transactional
    @Modifying
    @Query("DELETE FROM AChild c WHERE c.parent = :parent")
    void deleteChildrenByParent(@Param("parent") AParent parent);
}
