package com.zja.jpa.lazy.interceptable.service;

import com.zja.jpa.lazy.interceptable.model.base.PageData;
import com.zja.jpa.lazy.interceptable.model.dto.ProjectDTO;
import com.zja.jpa.lazy.interceptable.model.dto.ProjectPageDTO;
import com.zja.jpa.lazy.interceptable.model.request.ProjectPageRequest;
import com.zja.jpa.lazy.interceptable.model.request.ProjectRequest;
import com.zja.jpa.lazy.interceptable.model.request.ProjectUpdateRequest;

import java.util.List;

/**
 * 项目 服务层
 *
 * @author: zhengja
 * @since: 2024/09/27 9:28
 */
public interface ProjectService {

    /**
     * 查询项目
     *
     * @param id 项目id
     */
    ProjectDTO queryById(String id);

    /**
     * 分页查询项目
     */
    PageData<ProjectPageDTO> pageList(ProjectPageRequest request);

    /**
     * 校验项目名称是否可用
     *
     * @param name 项目名称
     * @return Boolean
     */
    Boolean existName(String name);

    /**
     * 新增项目
     */
    ProjectDTO add(ProjectRequest request);

    /**
     * 批量添加项目
     */
    List<ProjectDTO> addBatch(List<ProjectRequest> requests);

    /**
     * 更新项目
     *
     * @param id 项目id
     */
    ProjectDTO update(String id, ProjectUpdateRequest request);

    /**
     * 删除项目
     *
     * @param id 项目id
     */
    boolean deleteById(String id);

    /**
     * 批量删除项目
     *
     * @param ids 项目ids
     */
    void deleteBatch(List<String> ids);

}