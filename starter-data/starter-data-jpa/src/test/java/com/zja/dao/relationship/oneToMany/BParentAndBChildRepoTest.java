package com.zja.dao.relationship.oneToMany;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.manyToOne.MOAChild;
import com.zja.entitys.relationship.manyToOne.MOAParent;
import com.zja.entitys.relationship.oneToMany.BChild;
import com.zja.entitys.relationship.oneToMany.BParent;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 一对多：不存在中间表
 * <p>
 * 外键关联，一般子实体类这边维护，因此由 Child 来关联
 *
 * @author: zhengja
 * @since: 2024/04/19 14:46
 */
public class BParentAndBChildRepoTest extends JpaApplicationTest {


    @Autowired
    private BParentRepo parentRepo;

    @Autowired
    private BChildRepo childRepo;

    @Test
    public void deleteAllParentAndChild_test() {
        // 应先删除Child，因 Child 持有外键parent_id字段
        childRepo.deleteAll();
        parentRepo.deleteAll();
    }

    /**
     * 测试 保存父实体，再保存子实体
     * <p>
     * 先保存Parent到数据库中，再将其添加到Child对象的Parent属性。
     */
    @Test
    @Transactional
    @Commit
    public void saveParentAndChild_test1() {
        BParent bParent = new BParent();
        bParent.setName("Parent");

        // 保存 Parent 实体
        parentRepo.save(bParent);

        BChild bChild1 = new BChild();
        bChild1.setName("Child 11");
        bChild1.setParent(bParent); // 添加父

        BChild bChild2 = new BChild();
        bChild2.setName("Child 22");
        bChild2.setParent(bParent);

        // 保存 Child 实体时，会自动绑定，父id
        childRepo.save(bChild1);
        childRepo.save(bChild2);

        assertNotNull(bParent.getId());
        assertNotNull(bChild1.getId());
        assertNotNull(bChild2.getId());
    }

    @Deprecated
    @Disabled
    @Test
    @Transactional
    @Commit
    public void saveParentAndChild_test2() {

        BParent bParent = new BParent();
        bParent.setName("Parent 11");

        BChild bChild1 = new BChild();
        bChild1.setName("Child 33");
        // bChild1.setParent(bParent); // 添加父

        BChild bChild2 = new BChild();
        bChild2.setName("Child 44");
        // bChild2.setParent(bParent);

        // todo 无法做到关联外键: 在 BParent 父实体，添加@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)，虽然能保存父实体和子实体，但是无法做到关联外键。
        // 保存 Child 实体时，会自动绑定，父id
        // childRepo.save(bChild1);
        // childRepo.save(bChild2);

        bParent.getChildren().add(bChild1);
        bParent.getChildren().add(bChild2);

        // 保存 Parent 实体
        parentRepo.save(bParent);

        assertNotNull(bParent.getId());
        assertNotNull(bChild1.getId());
        assertNotNull(bChild2.getId());
    }

    /**
     * 按父实体分页查询子实体列表
     */
    @Test
    @Transactional
    public void findChildrenByParent_test() {
        Long parentId = 79L;
        BParent parent = parentRepo.findById(parentId).orElse(null); // 根据父实体ID获取父实体对象

        int pageNumber = 0; // 页码，从0开始
        int pageSize = 10; // 每页大小

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<BChild> childPage = childRepo.findChildrenByParent(parent, pageable);

        List<BChild> childList = childPage.getContent();
        childList.forEach(child -> System.out.println(child.getName()));
    }


    /**
     * 正常删除: 由子实体维护的外键
     */
    @Test
    public void deleteChild_test() {
        Long childId = 89L;

        childRepo.deleteById(childId);
        // childRepo.deleteAll();
    }

    /**
     * 无法删除：存在外键约束失败(需先解除所有关联的外键约束 或 删除所有关联的子实体)
     */
    @Test
    public void deleteParent_test() {
        Long parentId = 79L;

        // todo 在 BParent 父实体，添加 @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)，可以实现先删除子实体，再删除父实体效果
        parentRepo.deleteById(parentId);
        // parentRepo.deleteAll();
    }

    // 删除父实体(先解除绑定的子实体外键（不删子实体记录），再删除父实体)
    @Test
    public void deleteParent_test2() {
        Long parentId = 76L;

        // 方式1：先删除子实体，再删除父实体
        // BParent parent = parentRepo.findById(parentId).orElse(null); // 根据父实体ID获取父实体对象
        // if (parent != null) {
        //     childRepo.deleteByParent(parent); // 删除与父实体关联的子实体
        //     parentRepo.delete(parent); // 删除父实体
        // }

        // 方式2：先解除父实体与子实体的关联，再删除父实体
        parentRepo.removeChildrenAssociation(parentId); // 解除父实体与子实体的关联
        parentRepo.deleteParent(parentId); // 删除父实体
    }

}
