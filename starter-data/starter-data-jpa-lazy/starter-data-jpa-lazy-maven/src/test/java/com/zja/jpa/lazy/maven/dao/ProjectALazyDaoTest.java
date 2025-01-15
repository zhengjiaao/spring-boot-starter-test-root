package com.zja.jpa.lazy.maven.dao;

import com.alibaba.fastjson.JSON;
import com.zja.jpa.lazy.maven.dao.ProjectALazyRepo;
import com.zja.jpa.lazy.maven.entity.ProjectALazy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: zhengja
 * @Date: 2024-07-01 15:31
 */
@SpringBootTest
public class ProjectALazyDaoTest {

    @Autowired
    ProjectALazyRepo repo;

    @Test
    public void add_test() {
        // repo.deleteAll(); // 感到疑惑是添加清理数据时，会导致下放插入数据保存失败，创建时间是NULL异常

        for (int i = 1; i < 10; i++) {
            ProjectALazy p = new ProjectALazy();
            p.setName("名称-" + i);

            p.setConfigJson(JSON.parseObject("{\"key\":\"value\"}")); // json 字段
            p.setConfigText("大文本字段"); // 大文本字段或json文本
            repo.save(p);
        }
    }

    @Test
    @Transactional // 必须添加此注解，否则报错：大型对象无法被使用在自动确认事物交易模式
    public void findByName_test() {
        Optional<ProjectALazy> optional = repo.findByName("名称-1");
        optional.ifPresent(project -> {
            System.out.println(project.getId());
            System.out.println(project.getName());
            System.out.println(project.getCreateTime());
            System.out.println(project.getLastModifiedDate());
            System.out.println("-------------特殊字段-------------");
            System.out.println(project.getConfigJson()); // 有 @Transactional 不会懒加载
            System.out.println(project.getConfigText());
            System.out.println("--------------------------");
        });
    }

    @Test
    public void findAll_test() {
        List<ProjectALazy> projectList = repo.findAll(); // 支持懒加载
        System.out.println("总条数：" + projectList.size());
        projectList.forEach(project -> {
            System.out.println(project.getId());
            System.out.println(project.getName());
            System.out.println(project.getCreateTime());
            System.out.println(project.getLastModifiedDate());
            System.out.println("-------------特殊字段-------------");
            System.out.println(project.getConfigJson());
            System.out.println(project.getConfigText());
            System.out.println("--------------------------");
        });
    }

    @Test
    public void findAllPage_test() {
        int page = 0;
        int size = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

        Page<ProjectALazy> pageEntity = repo.findAll(PageRequest.of(page, size, sort)); // 支持懒加载
        List<ProjectALazy> projectList = pageEntity.getContent();
        projectList.forEach(project -> {
            System.out.println(project.getId());
            System.out.println(project.getName());
            System.out.println(project.getCreateTime());
            System.out.println(project.getLastModifiedDate());
            System.out.println("-------------特殊字段-------------");
            System.out.println(project.getConfigJson());
            System.out.println(project.getConfigText());
            System.out.println("--------------------------");
        });
    }

    @Test
    public void findAllPage_test2() {
        int page = 0;
        int size = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

        // 查询条件
        ProjectALazy request = new ProjectALazy();
        request.setName("名称-1");
        Specification<ProjectALazy> spec = buildQuery(request);

        Page<ProjectALazy> pageEntity = repo.findAll(spec, PageRequest.of(page, size, sort)); // 支持懒加载
        List<ProjectALazy> projectList = pageEntity.getContent();
        projectList.forEach(project -> {
            System.out.println(project.getId());
            System.out.println(project.getName());
            System.out.println(project.getCreateTime());
            System.out.println(project.getLastModifiedDate());
            System.out.println("-------------特殊字段-------------");
            // System.out.println(project.getConfigJson());
            // System.out.println(project.getConfigText());
            System.out.println("--------------------------");
        });
    }

    private Specification<ProjectALazy> buildQuery(ProjectALazy request) {
        // 构建查询条件
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 关键词
            if (!StringUtils.isEmpty(request.getName())) {
                predicates.add(cb.like(root.get("name"), request.getName() + "%"));
            }
            // 将条件连接在一起
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }

    @Test
    public void deleteAll_test() {
        repo.deleteAll();
    }

}
