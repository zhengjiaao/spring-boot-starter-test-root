package com.zja.mapper;

import com.zja.entitys.project.Project;
import com.zja.model.dto.ProjectDTO;
import com.zja.model.dto.ProjectPageDTO;
import com.zja.model.request.ProjectRequest;
import com.zja.model.request.ProjectUpdateRequest;
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

    Project map(ProjectUpdateRequest request);

    ProjectDTO map(Project entity);

    Project map(ProjectDTO dto);

    List<ProjectPageDTO> mapList(List<Project> entityList);

    // Set、List、Map
    List<ProjectPageDTO> mapList(Collection<Project> Projects);
}
