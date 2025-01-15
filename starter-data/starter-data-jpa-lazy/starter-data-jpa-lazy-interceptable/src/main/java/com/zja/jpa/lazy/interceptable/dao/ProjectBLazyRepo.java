package com.zja.jpa.lazy.interceptable.dao;

import com.zja.jpa.lazy.interceptable.entity.ProjectBLazy;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ProjectBLazy SQL
 *
 * @author: zhengja
 * @since: 2025/01/15 15:21
 */
@Repository
public interface ProjectBLazyRepo extends JpaRepository<ProjectBLazy, String>, CrudRepository<ProjectBLazy, String>,
        JpaSpecificationExecutor<ProjectBLazy> {

    Optional<ProjectBLazy> findByName(String name);
}