package com.zja.dao;

import cn.hutool.core.util.StrUtil;
import com.zja.JpaApplicationTest;
import com.zja.define.OrgTypeEnum;
import com.zja.entitys.Org;
import com.zja.manager.UserSystemManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 组织机构-单元测试类
 *
 * @author: zhengja
 * @since: 2024/04/15 13:35
 */
public class OrgRepoTest extends JpaApplicationTest {

    @Autowired
    OrgRepo repo;

    @Autowired
    UserSystemManager manager;

    private Org getOrgById(String orgId) {
        return repo.findById(orgId).orElseThrow(() -> new RuntimeException(StrUtil.format("传入的组织机构 id=「{}」 有误！", orgId)));
    }

    @Test
    public void test_1() {
        Org org = new Org();
        org.setName("组织机构1-内置");
        org.setInternal(true);
        org.setOrgType(OrgTypeEnum.DEPARTMENT);

        repo.save(org);

        Org org2 = new Org();
        org2.setName("行政单位1");
        org2.setInternal(false);
        org2.setOrgType(OrgTypeEnum.ORGANIZATION);

        repo.save(org2);

        Org org3 = new Org();
        org3.setName("行政区划1");
        org3.setRegionCode("000000");
        org3.setInternal(false);
        org3.setOrgType(OrgTypeEnum.REGION);

       repo.save(org3);
    }

    @Test
    @Transactional(readOnly = true)
    public void test_2() {
        List<Org> orgList = repo.findAll();
        assertThat(orgList).isNotNull();

        for (Org org : orgList) {
            System.out.println(org.getName());
            // todo 错误：org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.zja.entitys.Org.roles, could not initialize proxy - no Session
            // todo 原因分析：@ManyToMany 默认为 fetch=fetchType.lazy，默认为延迟加载方式 ;另外，@OneToOne默认为fetch=fetchType.eager，不采用延迟加载的方式。
            // todo 解决方案：@Transactional(readOnly = true)
            System.out.println(org.getRoles());
            System.out.println(org.getUsers());
            System.out.println(org.getChildren());
        }
    }

    // 组织机构绑定用户
    @Test
    public void test_3() {
        String orgId = "661cbece77f6f3abc4c6561f";
        List<String> userIds = Arrays.asList("661cc69e77f6742bf840655e", "661cc69e77f6742bf8406560");
        manager.bindOrgUsers(orgId, userIds);
    }

    // 组织机构取消用户的绑定
    @Test
    public void test_4() {
        String orgId = "661cbece77f6f3abc4c6561f";
        List<String> userIds = Arrays.asList("661cc69e77f6742bf840655e");
        manager.unbindOrgUsers(orgId, userIds);
    }

    @Test
    public void test_5() {
        Org org4Parent = getOrgById("661cbece77f6f3abc4c6561f");

        Org org4 = new Org();
        org4.setName("行政区划-子机构");
        org4.setRegionCode("000001");
        org4.setInternal(false);
        org4.setOrgType(OrgTypeEnum.REGION);
        org4.setParent(org4Parent); // 父组织机构

        repo.save(org4);
    }

    @Test
    public void test_6() {
        String orgId = "661cbece77f6f3abc4c6561f";
        repo.deleteById(orgId);
    }

    @Test
    public void test_7() {

    }
}