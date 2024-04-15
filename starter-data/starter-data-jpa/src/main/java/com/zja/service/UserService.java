/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-25 17:29
 * @Since:
 */
package com.zja.service;

import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.*;

import java.util.List;

/**
 * 用户服务
 *
 * @author: zhengja
 * @since: 2023/07/25 17:29
 */
public interface UserService {

    /**
     * 查询用户
     *
     * @param id 用户id
     */
    UserDTO findById(String id);

    /**
     * 分页查询用户
     */
    PageData<UserDTO> pageList(UserPageSearchRequest pageSearchRequest);

    /**
     * 校验登录名
     *
     * @param loginName 登录名
     */
    Boolean checkLoginName(String loginName);

    /**
     * 新增用户
     */
    UserDTO add(UserRequest request);

    /**
     * 批量添加用户
     */
    List<UserDTO> addBatch(List<UserRequest> userRequests);

    /**
     * 更新用户
     *
     * @param id 用户id
     */
    UserDTO update(String id, UserUpdateRequest updateRequest);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void deleteById(String id);

    /**
     * 批量删除用户
     *
     * @param ids 用户 ids
     */
    void deleteBatch(List<String> ids);

    /**
     * 给用户绑定角色
     */
    void bindUserRoles(UserBindRolesRequest userBindRolesRequest);

    /**
     * 查看用户已关联的角色（包括用户关联的组织机构下绑定的角色）
     */
    List<RoleDTO> queryUserRoles(String id);

    /**
     * 给用户绑定组织机构
     */
    void bindUserOrges(UserBindOrgsRequest userBindOrgsRequest);

    /**
     * 查询当前用户绑定的组织机构
     */
    List<OrgDTO> queryUserOrges(String id);
}