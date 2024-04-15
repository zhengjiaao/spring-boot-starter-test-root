package com.zja.controller;

import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.*;
import com.zja.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2024/02/21 14:50
 */
@Validated
@RestController
@RequestMapping("/rest/org")
@Api(tags = {"组织机构管理页面"})
public class OrgController {

    @Autowired
    OrgService service;

    @GetMapping("/query/{id}")
    @ApiOperation("查询单个组织机构详情")
    public OrgDTO queryById(@NotBlank @PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping("/page/list")
    @ApiOperation("分页查询组织机构列表")
    public PageData<OrgDTO> pageList(@Valid OrgPageSearchRequest pageSearchRequest) {
        return service.pageList(pageSearchRequest);
    }

    @PostMapping("/add")
    @ApiOperation("添加组织机构")
    public OrgDTO add(@Valid @RequestBody OrgRequest request) {
        return service.add(request);
    }

    @PostMapping("/add/batch")
    @ApiOperation("批量添加")
    public List<OrgDTO> add(@Valid @RequestBody List<OrgRequest> orgRequests) {
        return service.addBatch(orgRequests);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新组织机构")
    public OrgDTO update(@NotBlank @PathVariable("id") String id,
                         @Valid @RequestBody OrgUpdateRequest updateRequest) {
        return service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除组织机构")
    public void deleteById(@NotBlank @PathVariable("id") String id) {
        service.deleteById(id);
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation("批量删除组织机构")
    public void deleteBatch(@RequestBody List<String> ids) {
        service.deleteBatch(ids);
    }

    // ===================组织机构和用户关联==================

    @PostMapping("/{id}/org2users")
    @ApiOperation("组织机构关联用户列表（增量）")
    public void bindOrgUsersV2(@PathVariable("id") String id, @Valid @RequestBody List<String> userIds) {
        service.bindOrgUsers(id, userIds);
    }

    @DeleteMapping("/{id}/org2users")
    @ApiOperation("解除组织机构与指定用户列表的关联关系")
    public void unbindRoleUsers(@PathVariable("id") String id, @RequestBody List<String> userIds) {
        service.unbindOrgUsers(id, userIds);
    }

    @GetMapping("/page/user/list")
    @ApiOperation("分页查询组织机构下的用户列表")
    public PageData<UserDTO> pageUserList(String orgId, BasePageRequest pageRequest) {
        return service.queryOrgUsers(orgId, pageRequest.getPage(), pageRequest.getSize());
    }

    // ===================组织机构和角色关联==================

    @PostMapping("/{id}/org2roles")
    @ApiOperation("为组织机构绑定角色")
    public void bindOrgRoles(@PathVariable("id") String id, @RequestBody List<String> roleIds) {
        service.bindOrgRoles(new OrgBindRolesRequest(id, roleIds));
    }

    @GetMapping("/{id}/roles")
    @ApiOperation("查看组织机构已经授予的角色")
    public List<RoleDTO> queryOrgRoles(@ApiParam("组织机构 id") @PathVariable("id") String orgId) {
        return service.queryOrgRoles(orgId);
    }

}