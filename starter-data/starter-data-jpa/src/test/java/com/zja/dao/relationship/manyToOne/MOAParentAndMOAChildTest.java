package com.zja.dao.relationship.manyToOne;

import com.zja.entitys.relationship.manyToOne.MOAChild;
import com.zja.entitys.relationship.manyToOne.MOAParent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * -单元测试类
 *
 * @author: zhengja
 * @since: 2024/04/22 15:50
 */
@SpringBootTest
public class MOAParentAndMOAChildTest {


    @Autowired
    MOAParentRepo moaParentRepo;

    @Autowired
    MOAChildRepo moaChildRepo;

    @Test
    public void test_1() {
        MOAParent parent = new MOAParent();
        parent.setName("parent");

        // 由子实体维护，并未做级联设置
        // parent.getChildren().add(child1);
        // parent.getChildren().add(child2);

        // 保存 Parent 实体
        moaParentRepo.save(parent);

        MOAChild child1 = new MOAChild();
        child1.setName("child1");
        MOAChild child2 = new MOAChild();
        child2.setName("child2");

        child1.setParent(parent);
        child2.setParent(parent);

        // 保存 Child 实体时，会自动绑定，父id
        moaChildRepo.save(child1);
        moaChildRepo.save(child2);
    }

    @Test
    @Transactional
    public void test_2() {
        Long parentId = 64L;
        Optional<MOAParent> byId = moaParentRepo.findById(parentId);
        if (byId.isPresent()) {
            MOAParent parent = byId.get();
            List<MOAChild> childList = parent.getChildren();
            childList.forEach(child -> System.out.println(child.getName()));
        }
    }

    @Test
    @Transactional
    public void test_3() {
        Long parentId = 64L;
        MOAParent parent = moaParentRepo.findById(parentId).orElse(null); // 根据父实体ID获取父实体对象

        int pageNumber = 0; // 页码，从0开始
        int pageSize = 10; // 每页大小

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<MOAChild> childPage = moaChildRepo.findChildrenByParent(parent, pageable);

        List<MOAChild> childList = childPage.getContent();
        childList.forEach(child -> System.out.println(child.getName()));
    }

    // Child 取消绑定 Parent
    @Test
    @Transactional
    @Commit
    public void test_4() {
        Long childId = 65L;
        Optional<MOAChild> byId = moaChildRepo.findById(childId);
        if (byId.isPresent()) {
            MOAChild child = byId.get();
            System.out.println(child.getParent().getName());

            // Child 取消绑定 Parent
            child.setParent(null);
            moaChildRepo.save(child);
        }

        // Child 取消绑定 Parent
        // Long parentId = 67L;
        // moaChildRepo.removeChildrenAssociation(parentId);
    }

    @Test
    public void test_5() {
        // 下面方法实现一样效果
        // Long childId = 68L;
        // moaChildRepo.deleteById(childId); // 可以直接删除，由子实体维护的外键

        Long parentId = 67L;
        MOAParent parent = moaParentRepo.findById(parentId).orElse(null); // 根据父实体ID获取父实体对象
        // moaChildRepo.deleteByParent(parent);
        moaChildRepo.deleteChildrenByParent(parent);
    }

    // 删除父实体
    @Test
    public void test_6() {
        Long parentId = 73L;
        // moaParentRepo.deleteById(parentId); // todo 直接删除 Parent 会报错，存在外键的情况

        // 方式1：先删除子实体，再删除父实体
        // MOAParent parent = moaParentRepo.findById(parentId).orElse(null); // 根据父实体ID获取父实体对象
        // if (parent != null) {
        //     moaChildRepo.deleteByParent(parent); // 删除与父实体关联的子实体
        //     moaParentRepo.delete(parent); // 删除父实体
        // }

        // 方式2：先解除父实体与子实体的关联，再删除父实体
        moaParentRepo.removeChildrenAssociation(parentId); // 解除父实体与子实体的关联
        moaParentRepo.deleteParent(parentId); // 删除父实体
    }

    @Test
    public void test7() {
        Long childId = 68L;
        moaChildRepo.deleteById(childId); // 可以直接删除，由子实体维护的外键
    }


}