/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-14 11:04
 * @Since:
 */
package com.zja.dao;

import com.zja.entitys.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 用法参考：https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface
 * <p>
 * Repository
 * CrudRepository 常用的
 * PagingAndSortingRepository 分页、排序
 * JpaRepository 常用的
 * QuerydslPredicateExecutor 扩展，可选的
 */
@Repository
public interface UserRepo extends
        JpaRepository<User, String>,
        CrudRepository<User, String>,
        JpaSpecificationExecutor<User> {

    Optional<User> findByLoginName(String loginName);

    List<User> findByIdIn(List<String> ids);
}
