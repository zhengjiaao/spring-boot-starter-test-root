package com.zja.controller;

import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.*;
import com.zja.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2024/02/21 14:57
 */
@Validated
@RestController
@RequestMapping("/rest/user")
@Api(tags = {"用户管理页面"})
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/query/{id}")
    @ApiOperation("查询单个用户详情")
    public UserDTO queryById(@NotBlank @PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping("/page/list")
    @ApiOperation("分页查询用户列表")
    public PageData<UserDTO> pageList(@Valid UserPageSearchRequest pageSearchRequest) {
        return service.pageList(pageSearchRequest);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public UserDTO add(@Valid @RequestBody UserRequest request) {
        return service.add(request);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新用户")
    public UserDTO update(@NotBlank @PathVariable("id") String id,
                          @Valid @RequestBody UserUpdateRequest updateRequest) {
        return service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除用户")
    public void deleteById(@NotBlank @PathVariable("id") String id) {
        service.deleteById(id);
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation("批量删除用户")
    public void deleteBatch(@RequestBody List<String> ids) {
        service.deleteBatch(ids);
    }

    // ===================用户和角色关联==================

    @PostMapping("/{id}/user2roles")
    @ApiOperation("为用户绑定角色")
    public void bindUserRoles(@PathVariable("id") String id, @RequestBody List<String> roleIds) {
        service.bindUserRoles(new UserBindRolesRequest(id, roleIds));
    }

    @GetMapping("/{id}/roles")
    @ApiOperation("查看用户已关联的角色（包括用户关联的组织机构下绑定的角色）")
    public List<RoleDTO> queryUserRoles(@PathVariable("id") String id) {
        return service.queryUserRoles(id);
    }

    // ===================用户和组织机构关联==================

    @PostMapping("/{id}/user2orges")
    @ApiOperation("将用户添加到组织机构下")
    public void bindUserOrges(@PathVariable("id") String id, @RequestBody List<String> orgIds) {
        service.bindUserOrges(new UserBindOrgsRequest(id, orgIds));
    }

    @GetMapping("/{id}/orges")
    @ApiOperation("查看用户绑定的组织机构")
    public List<OrgDTO> queryUserOrges(@PathVariable("id") String id) {
        return service.queryUserOrges(id);
    }
}