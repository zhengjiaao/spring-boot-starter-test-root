/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 15:19
 * @Since:
 */
package com.zja.dao.relationship.oneToMany;

import com.zja.entitys.relationship.manyToOne.MOAChild;
import com.zja.entitys.relationship.manyToOne.MOAParent;
import com.zja.entitys.relationship.oneToMany.BChild;
import com.zja.entitys.relationship.oneToMany.BParent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
public interface BChildRepo extends
        JpaRepository<BChild, Long>,
        CrudRepository<BChild, Long>,
        JpaSpecificationExecutor<BChild> {

    // 按父实体分页查询其关联的子实体
    @Query("SELECT c FROM BChild c WHERE c.parent = :parent")
    Page<BChild> findChildrenByParent(@Param("parent") BParent parent, Pageable pageable);

    // 按父实体删除关联的子实体
    @Transactional
    void deleteByParent(BParent parent);

}
