/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-25 16:58
 * @Since:
 */
package com.zja.dao;

import com.zja.entitys.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: zhengja
 * @since: 2023/07/25 16:58
 */
@Repository
public interface OrgRepo extends
        JpaRepository<Org, String>,
        CrudRepository<Org, String>,
        JpaSpecificationExecutor<Org> {

    Optional<Org> findByName(String orgName);
}
