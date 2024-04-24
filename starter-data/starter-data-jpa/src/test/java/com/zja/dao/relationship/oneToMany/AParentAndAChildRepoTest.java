package com.zja.dao.relationship.oneToMany;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.oneToMany.AChild;
import com.zja.entitys.relationship.oneToMany.AParent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 多表操作，存在外键关联情况
 * <p>
 * 由 Parent 父实体 维护外键
 *
 * @author: zhengja
 * @since: 2024/04/19 10:06
 */
public class AParentAndAChildRepoTest extends JpaApplicationTest {

    @Autowired
    private AParentRepo parentRepo;

    @Autowired
    private AChildRepo childRepo;

    @Test
    public void deleteAllParentAndChild_test() {
        childRepo.deleteAll();
        parentRepo.deleteAll();
    }

    @Test
    @Transactional
    @Commit
    public void saveParentAndChild_test1() {
        AParent parent = new AParent();
        parent.setName("Parent 11");

        // 先保存 Parent 父实体
        parentRepo.save(parent);

        AChild child1 = new AChild();
        child1.setName("Child 11");
        child1.setParent(parent);

        AChild child2 = new AChild();
        child2.setName("Child 22");
        child2.setParent(parent);

        // 再保存 Child 子实体，同时关联外键关系，采用了中间表
        childRepo.save(child1);
        childRepo.save(child2);

        assertNotNull(parent.getId());
        assertNotNull(child1.getId());
        assertNotNull(child2.getId());
    }

    @Test
    @Transactional
    public void findChildByParent_test1() {
        Long parentId = 118L;
        Optional<AParent> byId = parentRepo.findById(parentId);
        if (byId.isPresent()) {
            AParent parent = byId.get();
            System.out.println(parent.getName());
            parent.getChildren().forEach(child -> System.out.println(child.getName()));
        }
    }

    // 分页查询子实体
    @Test
    @Transactional
    public void findChildByParent_test2() {
        Long parentId = 118L;
        Optional<AParent> byId = parentRepo.findById(parentId);
        if (byId.isPresent()) {
            AParent parent = byId.get();

            int pageNumber = 0; // 页码，从0开始
            int pageSize = 10; // 每页大小

            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            // Page<AChild> childPage = childRepo.findChildrenByParent(parent, pageable);  // 执行分页查询
            // or
            Page<AChild> childPage = childRepo.findChildrenByParent(parentId, pageable);  // 执行分页查询

            List<AChild> childList = childPage.getContent();  // 获取查询结果列表
            childList.forEach(child -> System.out.println(child.getName()));
        }
    }

    @Test
    @Transactional
    @Commit
    public void updateParentAndChild_test1() {
        Long parentId = 130L;

        // 通过 Parent 级联操作 Child 更新、删除
        Optional<AParent> parentOptional = parentRepo.findById(parentId);
        if (parentOptional.isPresent()) {

            AParent parent = parentOptional.get();

            // 添加子实体
            AChild child3 = new AChild();
            child3.setName("Child 55");
            child3.setParent(parent);
            childRepo.save(child3);

            // 移除子实体
            List<AChild> childList = parent.getChildren();
            for (AChild child : childList) {
                // 解除与 Child 绑定关系
                if ("Child 11".equals(child.getName())) {
                    child.setParent(null);
                    childRepo.save(child);
                }
            }
        }
    }

    /**
     * 正常删除（删除子数据，同时删除关联关系【中间表】）
     */
    @Test
    @Transactional
    @Commit
    public void deleteChild_test() {
        Long childId = 132L;

        childRepo.deleteById(childId);
        // childRepo.deleteAll();
    }

    /**
     * 无法删除：外键约束失败
     */
    @Test
    @Transactional
    @Commit
    public void deleteParent_test() {
        Long parentId = 133L;

        // todo 无法删除：外键约束失败
        // parentRepo.deleteById(parentId);
        // parentRepo.deleteAll();

        Optional<AParent> optionalParent = parentRepo.findById(parentId);

        // 方式1
        if (optionalParent.isPresent()) {
            AParent parent = optionalParent.get();

            // 获取子实体列表
            List<AChild> children = parent.getChildren();

            // 解除父实体和子实体之间的绑定关系
            for (AChild child : children) {
                child.setParent(null);
            }

            // 保存父实体，更新数据库中的关联关系
            parentRepo.save(parent);
            // 删除父实体
            parentRepo.deleteById(parentId);
        }

        // 方式2 不推荐，手动编写原生sql，清理中间表记录
        // if (optionalParent.isPresent()) {
        //     AParent parent = optionalParent.get();
        //     // 解除绑定
        //     parentRepo.unbindChildren(parentId);
        //     // parentRepo.unbindChildren2(parentId);
        //     // 删除父实体
        //     parentRepo.delete(parent);
        // }
    }

}
