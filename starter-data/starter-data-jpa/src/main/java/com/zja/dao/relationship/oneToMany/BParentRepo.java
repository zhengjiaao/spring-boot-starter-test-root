/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 15:19
 * @Since:
 */
package com.zja.dao.relationship.oneToMany;

import com.zja.entitys.relationship.oneToMany.BParent;
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
public interface BParentRepo extends
        JpaRepository<BParent, Long>,
        CrudRepository<BParent, Long>,
        JpaSpecificationExecutor<BParent> {


    // 解除父实体与子实体的关联
    @Transactional
    @Modifying
    @Query("UPDATE BChild c SET c.parent = null WHERE c.parent.id = :parentId")
    void removeChildrenAssociation(@Param("parentId") Long parentId);


    // 删除父实体（存在外键，不能直接删除父实体，需要先解除绑定的子实体 或 先删除关联子实体）
    @Transactional
    @Modifying
    @Query("DELETE FROM BParent p WHERE p.id = :parentId")
    void deleteParent(@Param("parentId") Long parentId);
}
