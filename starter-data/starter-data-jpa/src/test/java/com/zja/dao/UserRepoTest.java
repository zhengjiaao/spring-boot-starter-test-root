package com.zja.dao;

import com.zja.JpaApplicationTest;
import com.zja.entitys.User;
import com.zja.entitys.UserAttachment;
import com.zja.manager.UserSystemManager;
import com.zja.model.request.UserBindOrgsRequest;
import com.zja.model.request.UserBindRolesRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 用户-单元测试类
 *
 * @author: zhengja
 * @since: 2024/04/15 13:35
 */
public class UserRepoTest extends JpaApplicationTest {

    @Autowired
    UserRepo repo;

    @Autowired
    UserSystemManager manager;

    @Test
    public void test_1() {
        User user = new User();
        user.setName("李四");
        user.setLoginName("list");
        user.setPassword("111");
        user.setState(1);

        repo.save(user);

        User user2 = new User();
        user2.setName("李四2");
        user2.setLoginName("list2");
        user2.setPassword("222");
        user2.setState(1);

        repo.save(user2);

        User user3 = new User();
        user3.setName("李四3");
        user3.setLoginName("list3");
        user3.setPassword("333");
        user3.setState(1);
        UserAttachment attachment = new UserAttachment();
        attachment.setPosition("158****888");
        attachment.setEmail("123@qq.com");
        user3.setUserAttachment(attachment);

        repo.save(user3);
    }

    @Test
    public void test_2() {
        String userId = "661cc69e77f6742bf8406562";
        List<String> orgIds = Arrays.asList("661cbece77f6f3abc4c6561b", "661cbece77f6f3abc4c6561f");
        manager.bindUserOrges(new UserBindOrgsRequest(userId,orgIds));
    }

    @Test
    public void test_3() {
        String userId = "661cc69e77f6742bf8406562";
        List<String> orgIds = Arrays.asList("661cbece77f6f3abc4c6561b");
        manager.bindUserOrges(new UserBindOrgsRequest(userId,orgIds));
    }

    @Test
    public void test_4() {
        String userId = "661cc69e77f6742bf8406562";
        List<String> roleIds = Arrays.asList("661cca4c77f6fa7b5b5794be", "661cca4c77f6fa7b5b5794c2");
        manager.bindUserRoles(new UserBindRolesRequest(userId,roleIds));
    }

    @Test
    public void test_5() {
        String userId = "661cc69e77f6742bf8406562";
        List<String> roleIds = Arrays.asList("661cca4c77f6fa7b5b5794be");
        manager.bindUserRoles(new UserBindRolesRequest(userId,roleIds));
    }

    @Test
    public void test_6() {

    }

    @Test
    public void test_7() {

    }

}
