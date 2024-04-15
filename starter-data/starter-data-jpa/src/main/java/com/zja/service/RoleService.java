package com.zja.service;

import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.Role2UsersRequest;
import com.zja.model.request.RolePageSearchRequest;
import com.zja.model.request.RoleRequest;
import com.zja.model.request.RoleUpdateRequest;

import java.util.List;

/**
 * 角色服务
 *
 * @author: zhengja
 * @since: 2024/02/21 16:08
 */
public interface RoleService {

    /**
     * 查询角色
     */
    RoleDTO findById(String id);

    /**
     * 分页查询角色
     */
    PageData<RoleDTO> pageList(RolePageSearchRequest request);

    /**
     * 检验角色名是否可用
     */
    Boolean checkRoleName(String roleName);

    /**
     * 新增角色
     */
    RoleDTO add(RoleRequest request);

    /**
     * 批量添加角色
     */
    List<RoleDTO> addBatch(List<RoleRequest> roleRequests);

    /**
     * 更新角色
     *
     * @param id
     */
    RoleDTO update(String id, RoleUpdateRequest request);

    /**
     * 删除角色
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 批量删除角色
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);

    /**
     * 为角色增量绑定用户
     *
     * @param role2UsersRequest 角色用户关联实体
     */
    void bindRoleUsers(Role2UsersRequest role2UsersRequest);

    /**
     * 解除角色与指定用户列表的关联关系
     *
     * @param role2UsersRequest 角色用户关联实体
     */
    void unbindRoleUsers(Role2UsersRequest role2UsersRequest);

    /**
     * 查看角色关联的用户 （分页）
     *
     * @param id   角色 id
     * @param key  关键词（用户名或登录名）
     * @param page 页码
     * @param size 每页数量
     * @return PageData<UserDTO>
     */
    PageData<UserDTO> queryRoleUsersByPage(String id, String key, Integer page, Integer size);

    /**
     * 绑定角色与指定组织机构列表的关联关系
     *
     * @param id     角色 id
     * @param orgIds 机构 id 列表
     */
    void bindRoleOrges(String id, List<String> orgIds);

    /**
     * 查看角色关联的组织机构
     *
     * @param id 角色 id
     * @return List<OrgDTO>
     */
    List<OrgDTO> queryRoleOrges(String id);
}