package com.zja.jpa.lazy.interceptable.controller;
import com.zja.jpa.lazy.interceptable.model.base.PageData;
import com.zja.jpa.lazy.interceptable.model.dto.ProjectDTO;
import com.zja.jpa.lazy.interceptable.model.dto.ProjectPageDTO;
import com.zja.jpa.lazy.interceptable.model.request.ProjectPageRequest;
import com.zja.jpa.lazy.interceptable.model.request.ProjectRequest;
import com.zja.jpa.lazy.interceptable.model.request.ProjectUpdateRequest;
import com.zja.jpa.lazy.interceptable.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 项目 接口层（一般与页面、功能对应）
 * @author: zhengja
 * @since: 2024/09/27 9:27
 */
@Validated
@RestController
@RequestMapping("/rest/project")
@Api(tags = {"项目管理页面"})
public class ProjectController {

    @Autowired
    ProjectService service;

    @GetMapping("/query/{id}")
    @ApiOperation("查询单个项目详情")
    public ProjectDTO queryById(@NotBlank @PathVariable("id") String id) {
        return service.queryById(id);
    }

    @GetMapping("/page/list")
    @ApiOperation("分页查询项目列表")
    public PageData<ProjectPageDTO> pageList(@Valid ProjectPageRequest pageRequest) {
        return service.pageList(pageRequest);
    }

    @PostMapping("/add")
    @ApiOperation("添加项目")
    public ProjectDTO add(@Valid @RequestBody ProjectRequest request) {
        return service.add(request);
    }

    @PostMapping("/add/batch")
    @ApiOperation("批量添加项目")
    public List<ProjectDTO> add(@Valid @RequestBody List<ProjectRequest> orgRequests) {
        return service.addBatch(orgRequests);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新项目")
    public ProjectDTO update(@NotBlank @PathVariable("id") String id,
                         @Valid @RequestBody ProjectUpdateRequest updateRequest) {
        return service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除项目")
    public boolean deleteById(@NotBlank @PathVariable("id") String id) {
        return service.deleteById(id);
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation("批量删除项目")
    public void deleteBatch(@RequestBody List<String> ids) {
        service.deleteBatch(ids);
    }
}