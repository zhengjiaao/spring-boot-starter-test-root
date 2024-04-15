/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 14:37
 * @Since:
 */
package com.zja.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zja.dao.OrgRepo;
import com.zja.dao.RoleRepo;
import com.zja.dao.UserRepo;
import com.zja.entitys.Org;
import com.zja.entitys.Role;
import com.zja.entitys.User;
import com.zja.entitys.base.BaseEntity;
import com.zja.model.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: zhengja
 * @since: 2023/08/10 14:37
 */
@Component
@Transactional
public class UserSystemManager {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrgRepo orgRepo;

    @Autowired
    private RoleRepo roleRepo;


    private User getUserById(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new RuntimeException(StrUtil.format("传入的用户 id=「{}」 有误！", userId)));
    }

    private Org getOrgById(String orgId) {
        return orgRepo.findById(orgId).orElseThrow(() -> new RuntimeException(StrUtil.format("传入的组织机构 id=「{}」 有误！", orgId)));
    }

    private Role getRoleById(String roleId) {
        return roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException(StrUtil.format("传入的角色 id=「{}」 有误！", roleId)));
    }

    // ===================用户与组织机构 关联==================

    public void bindUserOrges(User user, List<String> orgIds) {
        List<Org> orgList = orgIds.stream().map(this::getOrgById).collect(Collectors.toList());
        user.getOrgs().clear();
        user.getOrgs().addAll(orgList);
    }

    public void bindUserOrges(UserBindOrgsRequest userBindOrgsRequest) {
        User user = this.getUserById(userBindOrgsRequest.getUserId());

        // 绑定 & 解绑
        List<Org> orgList = userBindOrgsRequest.getOrgIds().stream().map(this::getOrgById).collect(Collectors.toList());
        Set<Org> orgs = user.getOrgs();
        orgs.clear();
        orgs.addAll(orgList);
        userRepo.save(user);
    }

    // ===================用户与角色 关联==================
    public void bindUserRoles(UserBindRolesRequest userBindRolesRequest) {
        User user = this.getUserById(userBindRolesRequest.getUserId());

        // 绑定
        List<String> newRoleIds = userBindRolesRequest.getRoleIds();
        newRoleIds.stream()
                .map(this::getRoleById)
                .forEach(role -> {
                    role.getUsers().add(user);
                    roleRepo.save(role);
                });

        // 移除
        List<String> oldRoleIds = user.getRoles().stream().map(BaseEntity::getId).collect(Collectors.toList());
        Collection<String> subtract = CollUtil.subtract(oldRoleIds, newRoleIds);
        subtract.stream()
                .map(this::getRoleById)
                .forEach(role -> {
                    role.getUsers().remove(user);
                    roleRepo.save(role);
                });
    }

    // ===================组织机构和用户关联==================

    @Deprecated
    public void bindOrgUsers(Org2UsersRequest org2UsersRequest) {
        Org org = this.getOrgById(org2UsersRequest.getOrgId());
        List<String> newUserIds = org2UsersRequest.getUserIds();

        // 绑定
        newUserIds.stream()
                .map(this::getUserById)
                .forEach(user -> {
                    user.getOrgs().add(org);
                    userRepo.save(user);
                });

        // 移除
        List<String> oldUserIds = org.getUsers().stream().map(BaseEntity::getId).collect(Collectors.toList());
        Collection<String> subtract = CollUtil.subtract(oldUserIds, newUserIds);
        subtract.stream().map(this::getUserById)
                .forEach(user -> {
                    user.getOrgs().remove(org);
                    userRepo.save(user);
                });
    }

    /**
     * 增量绑定 机构<=>[用户...]
     */
    public void bindOrgUsers(String id, List<String> userIds) {
        Org org = this.getOrgById(id);

        // 绑定
        userIds.stream()
                .map(this::getUserById)
                .forEach(user -> {
                    user.getOrgs().add(org);
                    userRepo.save(user);
                });
    }

    /**
     * 组织机构取消与指定用户绑定
     */
    public void unbindOrgUsers(String id, List<String> userIds) {
        Org org = this.getOrgById(id);

        // 移除
        userIds.stream().map(this::getUserById)
                .forEach(user -> {
                    user.getOrgs().remove(org);
                    userRepo.save(user);
                });
    }

    // ===================组织机构和角色关联==================

    public void bindOrgRoles(OrgBindRolesRequest orgBindRolesRequest) {
        Org org = this.getOrgById(orgBindRolesRequest.getOrgId());
        List<String> newRoleIds = orgBindRolesRequest.getRoleIds();

        // 绑定
        newRoleIds.stream()
                .map(this::getRoleById)
                .forEach(role -> {
                    role.getOrgs().add(org);
                    roleRepo.save(role);
                });

        // 查看组织机构已经关联的角色
        List<String> oldRoleIds = org.getRoles().stream().map(BaseEntity::getId).collect(Collectors.toList());
        // 与新的角色列表取差集，得出需要移除当前组织机构的角色
        Collection<String> subtract = CollUtil.subtract(oldRoleIds, newRoleIds);

        // 移除
        subtract.stream().map(this::getRoleById)
                .forEach(role -> {
                    role.getOrgs().remove(org);
                    roleRepo.save(role);
                });
    }

    // ===================角色和用户关联==================

    public void bindRoleUsers(Role2UsersRequest role2UsersRequest) {
        Role role = getRoleById(role2UsersRequest.getRoleId());

        // 增量绑定
        List<String> usersId = role2UsersRequest.getUsersId();
        List<User> users = userRepo.findByIdIn(usersId);
        role.getUsers().addAll(users);
        roleRepo.save(role);
    }

    public void unbindRoleUsers(Role2UsersRequest role2UsersRequest) {
        Role role = getRoleById(role2UsersRequest.getRoleId());

        // 解绑
        List<User> users = userRepo.findByIdIn(role2UsersRequest.getUsersId());
        users.forEach(role.getUsers()::remove);
        roleRepo.save(role);
    }


    // ===================角色和组织机构关联==================

    public void bindRoleOrges(String id, List<String> orgIds) {
        Role role = this.getRoleById(id);

        role.getOrgs().clear();
        List<Org> orgList = orgIds.stream().map(this::getOrgById).collect(Collectors.toList());
        role.getOrgs().addAll(orgList);
        roleRepo.save(role);
    }
}
