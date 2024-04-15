package com.zja.controller;

import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.Role2UsersRequest;
import com.zja.model.request.RolePageSearchRequest;
import com.zja.model.request.RoleRequest;
import com.zja.model.request.RoleUpdateRequest;
import com.zja.service.RoleService;
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
 * @since: 2024/04/15 11:09
 */
@Validated
@RestController
@RequestMapping("/rest/role")
@Api(tags = {"角色管理页面"})
public class RoleController {

    @Autowired
    RoleService service;

    @GetMapping("/query/{id}")
    @ApiOperation("查询单个角色详情")
    public RoleDTO queryById(@NotBlank @PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping("/page/list")
    @ApiOperation("分页查询角色列表")
    public PageData<RoleDTO> pageList(@Valid RolePageSearchRequest pageSearchRequest) {
        return service.pageList(pageSearchRequest);
    }

    @GetMapping("/check")
    @ApiOperation("检验角色名是否可用")
    public Boolean checkOrgName(@RequestParam String roleName) {
        return service.checkRoleName(roleName);
    }

    @PostMapping("/add")
    @ApiOperation("添加角色")
    public RoleDTO add(@Valid @RequestBody RoleRequest request) {
        return service.add(request);
    }

    @PostMapping("/add/batch")
    @ApiOperation("批量添加角色")
    public List<RoleDTO> addBatch(@Valid @RequestBody List<RoleRequest> roleRequests) {
        return service.addBatch(roleRequests);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新角色")
    public RoleDTO update(@NotBlank @PathVariable("id") String id,
                          @Valid @RequestBody RoleUpdateRequest updateRequest) {
        return service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除角色")
    public void deleteById(@NotBlank @PathVariable("id") String id) {
        service.deleteById(id);
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation("批量删除角色")
    public void deleteBatch(@RequestBody List<String> ids) {
        service.deleteBatch(ids);
    }


    // ===================角色和用户关联==================

    @PostMapping("/{id}/role2users")
    @ApiOperation("增量绑定角色与指定用户列表的关联关系")
    public void bindRoleUsers(@PathVariable("id") String id, @RequestBody List<String> userIds) {
        service.bindRoleUsers(new Role2UsersRequest(id, userIds));
    }

    @DeleteMapping("/{id}/role2users")
    @ApiOperation("解除角色与指定用户列表的关联关系")
    public void unbindRoleUsers(@PathVariable("id") String id, @RequestBody List<String> userIds) {
        service.unbindRoleUsers(new Role2UsersRequest(id, userIds));
    }

    @GetMapping("/{id}/users")
    @ApiOperation("查看角色绑定的用户(分页)")
    public PageData<UserDTO> queryRoleUsersByPage(@PathVariable("id") String id,
                                              @ApiParam(value = "关键词（用户名或登录名）") @RequestParam(required = false) String key,
                                              @ApiParam(value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer page,
                                              @ApiParam(value = "每页数量", defaultValue = "20") @RequestParam(defaultValue = "20") Integer size) {
        page = page - 1;
        return service.queryRoleUsersByPage(id, key, page, size);
    }

    // ===================角色和组织机构关联==================

    @PostMapping("/{id}/role2orges")
    @ApiOperation("绑定角色与指定组织机构列表的关联关系")
    public void bindRoleOrges(@PathVariable String id, @RequestBody List<String> orgIds) {
        service.bindRoleOrges(id, orgIds);
    }

    @GetMapping("/{id}/orges")
    @ApiOperation("查询绑定在该角色下的所有组织机构（不分页）")
    public List<OrgDTO> queryRoleOrges(@PathVariable("id") String id) {
        return service.queryRoleOrges(id);
    }

}