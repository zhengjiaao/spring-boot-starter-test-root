/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 9:54
 * @Since:
 */
package com.zja.dao.audit;

import com.zja.entitys.audit.AuditUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zhengja
 * @since: 2023/09/28 9:54
 */
@Repository
public interface AuditUserEntityRepo extends
        JpaRepository<AuditUserEntity, String>,
        CrudRepository<AuditUserEntity, String>,
        JpaSpecificationExecutor<AuditUserEntity> {

}
