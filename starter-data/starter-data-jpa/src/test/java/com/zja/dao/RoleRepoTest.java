package com.zja.dao;

import com.zja.JpaApplicationTest;
import com.zja.entitys.Role;
import com.zja.manager.UserSystemManager;
import com.zja.model.request.Role2UsersRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * 角色-单元测试类
 *
 * @author: zhengja
 * @since: 2024/04/15 13:35
 */
public class RoleRepoTest extends JpaApplicationTest {

    @Autowired
    RoleRepo repo;

    @Autowired
    UserSystemManager manager;

    @Test
    public void test_1() {
        Role role = new Role();
        role.setRoleName("角色1");
        role.setState(1);

        repo.save(role);

        Role role2 = new Role();
        role2.setRoleName("角色2");
        role2.setState(1);

        repo.save(role2);

        Role role3 = new Role();
        role3.setRoleName("角色3");
        role3.setState(1);

        repo.save(role3);
    }

    @Test
    public void test_2() {
        String roleId = "661cca4c77f6fa7b5b5794c2";
        List<String> userIds = Arrays.asList("661cc69e77f6742bf840655e", "661cc69e77f6742bf8406560");
        manager.bindRoleUsers(new Role2UsersRequest(roleId,userIds));
    }

    @Test
    public void test_3() {
        String roleId = "661cca4c77f6fa7b5b5794c2";
        List<String> userIds = Arrays.asList("661cc69e77f6742bf840655e");
        manager.unbindRoleUsers(new Role2UsersRequest(roleId,userIds));
    }

    @Test
    public void test_4() {
        String roleId = "661cca4c77f6fa7b5b5794c2";
        List<String> orgIds = Arrays.asList("661cbece77f6f3abc4c6561b", "661cbece77f6f3abc4c6561f");
        manager.bindRoleOrges(roleId,orgIds);
    }

    @Test
    public void test_5() {
        String roleId = "661cca4c77f6fa7b5b5794c2";
        List<String> orgIds = Arrays.asList("661cbece77f6f3abc4c6561f");
        manager.bindRoleOrges(roleId,orgIds);
    }

    @Test
    public void test_6() {

    }

    @Test
    public void test_7() {

    }

}