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
import com.zja.entitys.relationship.manyToOne.MOAParent;
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
public interface AParentRepo extends
        JpaRepository<AParent, Long>,
        CrudRepository<AParent, Long>,
        JpaSpecificationExecutor<AParent> {

    // 解除父实体与子实体的关联
    // delete from om_a_parent_childs where child_id=?
    // delete from om_a_parent_childs where parent_id=?
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM om_a_parent_childs WHERE parent_id = :parentId", nativeQuery = true)
    void unbindChildren(@Param("parentId") Long parentId);

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
    // void unbindChildren2(@Param("parentId") Long parentId);

    // todo 会导致中间表记录未删除，只是把 parent_id字段设置为null
    // 解除父实体与子实体的关联
    // @Transactional
    // @Modifying
    // @Query("UPDATE AChild c SET c.parent = null WHERE c.parent.id = :parentId")
    // void removeChildrenAssociation(@Param("parentId") Long parentId);

}
