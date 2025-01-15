package com.zja.jpa.lazy.maven.mapper;

import com.zja.jpa.lazy.maven.entity.Project;
import com.zja.jpa.lazy.maven.model.dto.ProjectDTO;
import com.zja.jpa.lazy.maven.model.dto.ProjectPageDTO;
import com.zja.jpa.lazy.maven.model.request.ProjectRequest;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Project 属性映射
 *
 * @author: zhengja
 * @since: 2024/09/27 9:29
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project map(ProjectRequest request);

    ProjectDTO map(Project entity);

    Project map(ProjectDTO dto);

    List<ProjectPageDTO> mapList(List<Project> entityList);

    // Set、List、Map
    List<ProjectPageDTO> mapList(Collection<Project> Projects);
}
