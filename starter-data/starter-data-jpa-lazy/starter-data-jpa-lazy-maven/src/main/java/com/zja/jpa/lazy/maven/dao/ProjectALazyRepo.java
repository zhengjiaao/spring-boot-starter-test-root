package com.zja.jpa.lazy.maven.dao;

import com.zja.jpa.lazy.maven.entity.ProjectALazy;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ProjectALazy SQL
 *
 * @author: zhengja
 * @since: 2025/01/15 15:21
 */
@Repository
public interface ProjectALazyRepo extends JpaRepository<ProjectALazy, String>, CrudRepository<ProjectALazy, String>,
        JpaSpecificationExecutor<ProjectALazy> {

    Optional<ProjectALazy> findByName(String name);
}