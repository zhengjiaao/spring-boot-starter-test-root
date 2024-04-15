/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 17:19
 * @Since:
 */
package com.zja.service;

import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.OrgBindRolesRequest;
import com.zja.model.request.OrgPageSearchRequest;
import com.zja.model.request.OrgRequest;
import com.zja.model.request.OrgUpdateRequest;

import java.util.List;

/**
 * 组织机构服务
 *
 * @author: zhengja
 * @since: 2023/08/10 17:19
 */
public interface OrgService {

    /**
     * 查询组织机构
     *
     * @param id 组织机构id
     */
    OrgDTO findById(String id);

    /**
     * 分页查询组织机构
     */
    PageData<OrgDTO> pageList(OrgPageSearchRequest request);

    PageData<UserDTO> queryOrgUsers(String orgId, Integer page, Integer size);

    /**
     * 查询组织机构已授权的角色
     */
    List<RoleDTO> queryOrgRoles(String orgId);

    /**
     * 校验组织机构名称是否可用
     *
     * @param orgName 组织机构名称
     * @return Boolean
     */
    Boolean checkOrgName(String orgName);

    /**
     * 新增组织机构
     */
    OrgDTO add(OrgRequest request);

    /**
     * 批量添加组织机构
     */
    List<OrgDTO> addBatch(List<OrgRequest> orgRequests);

    /**
     * 更新组织机构
     *
     * @param id 组织机构id
     */
    OrgDTO update(String id, OrgUpdateRequest request);

    /**
     * 删除组织机构
     *
     * @param id 组织机构id
     */
    void deleteById(String id);

    /**
     * 批量删除组织机构
     *
     * @param ids 组织机构ids
     */
    void deleteBatch(List<String> ids);

    /**
     * 组织机构关联用户列表（增量）
     */
    void bindOrgUsers(String id, List<String> userIds);

    /**
     * 解除组织机构与指定用户列表的关联关系
     */
    void unbindOrgUsers(String id, List<String> userIds);

    /**
     * 给组织机构绑定角色
     */
    void bindOrgRoles(OrgBindRolesRequest orgBindRolesRequest);
}