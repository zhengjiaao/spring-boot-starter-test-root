package com.zja.dao.relationship.oneToMany;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.oneToMany.CChild;
import com.zja.entitys.relationship.oneToMany.CParent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 多表操作，存在外键关联情况
 * <p>
 * 由 Parent 父实体 维护外键
 *
 * @author: zhengja
 * @since: 2024/04/19 10:06
 */
public class CParentAndCChildRepoTest extends JpaApplicationTest {

    @Autowired
    private CParentRepo parentRepo;

    @Autowired
    private CChildRepo childRepo;

    @Test
    public void deleteAllParentAndChild_test() {
        parentRepo.deleteAll();
        childRepo.deleteAll();
    }

    @Test
    @Transactional
    @Commit
    public void saveParentAndChild_test1() {

        // 先保存 Child 子实体
        CChild aChild1 = new CChild();
        aChild1.setName("Child 11");

        CChild aChild2 = new CChild();
        aChild2.setName("Child 22");

        childRepo.save(aChild1);
        childRepo.save(aChild2);

        CParent aParent = new CParent();
        aParent.setName("Parent 11");
        // Add children
        aParent.getChildren().add(aChild1);
        aParent.getChildren().add(aChild2);

        // 再保存 Parent 父实体，同时关联外键关系，采用了中间表
        parentRepo.save(aParent);

        assertNotNull(aParent.getId());
        assertNotNull(aChild1.getId());
        assertNotNull(aChild2.getId());
        assertEquals(2, aParent.getChildren().size());
    }

    // 通过 Parent 记录操作 Child：必须先配置 CascadeType.ALL
    @Test
    @Transactional
    @Commit
    public void saveParentAndChild_test2() {

        CChild aChild1 = new CChild();
        aChild1.setName("Child 33");
        // childRepo.save(CChild1);

        CChild aChild2 = new CChild();
        aChild2.setName("Child 44");
        // childRepo.save(CChild2);

        CParent aParent = new CParent();
        aParent.setName("Parent 22");
        // Add children
        aParent.getChildren().add(aChild1);
        aParent.getChildren().add(aChild2);

        // 先保存Parent父实体，再保存所有Child子实体，最后保存中间表的关联关系
        parentRepo.save(aParent);

        assertNotNull(aParent.getId());
        assertNotNull(aChild1.getId());
        assertNotNull(aChild2.getId());
        assertEquals(2, aParent.getChildren().size());
    }

    @Test
    @Transactional
    public void findChildByParent_test1() {
        Long parentId = 97L;
        Optional<CParent> byId = parentRepo.findById(parentId);
        if (byId.isPresent()) {
            CParent parent = byId.get();
            System.out.println(parent);
        }
    }

    // 分页查询子实体
    @Test
    @Transactional
    public void findChildByParent_test2() {
        Long parentId = 97L;
        Optional<CParent> byId = parentRepo.findById(parentId);
        if (byId.isPresent()) {
            CParent parent = byId.get();

            int pageNumber = 0; // 页码，从0开始
            int pageSize = 10; // 每页大小

            Page<CChild> childPage = parent.getChildrenPage(pageNumber, pageSize);  // 执行自定义分页查询

            List<CChild> childList = childPage.getContent();  // 获取查询结果列表
            childList.forEach(child -> System.out.println(child.getName()));
        }
    }

    @Test
    @Transactional
    @Commit
    public void updateParentAndChild_test1() {
        Long parentId = 105L;

        // 通过 Parent 级联操作 Child 更新、删除
        Optional<CParent> parentOptional = parentRepo.findById(parentId);
        if (parentOptional.isPresent()) {

            CParent parent = parentOptional.get();

            // todo 通过 Parent 记录操作 Child：必须先配置 CascadeType.ALL
            // 添加子实体
            // CChild aChild3 = new CChild();
            // aChild3.setName("Child 55");
            // parent.getChildren().add(aChild3);

            // 移除子实体
            Set<CChild> childSet = parent.getChildren();
            for (CChild child : childSet) {
                // 解除与 Child 绑定关系
                if ("Child 11".equals(child.getName())) {
                    childSet.remove(child);
                }
            }

            // childSet.clear(); // 解除与全部Child 绑定关系

            // 保存父实体，并关联子实体，维护中间表和外键
            parentRepo.save(parent);
        }
    }

    /**
     * 无法删除：外键约束失败
     */
    @Test
    @Transactional
    @Commit
    public void deleteChild_test() {
        Long childId = 100L;
        Long parentId = 102L;

        // todo 无法删除：外键约束失败
        // childRepo.deleteById(childId);
        // childRepo.deleteAll();

        Optional<CChild> childOptional = childRepo.findById(childId);
        Optional<CParent> parentOptional = parentRepo.findById(parentId);

        if (parentOptional.isPresent() && childOptional.isPresent()) {
            CParent parent = parentOptional.get();
            // 取消绑定
            // parent.getChildren().remove(childOptional.get());
            parent.getChildren().removeIf(c -> c.getId().equals(childOptional.get().getId()));
            parentRepo.save(parent);
        }

        // 再删除
        childRepo.deleteById(childId);
    }

    /**
     * 正常删除（删除父数据，同时删除关联关系【中间表】）
     */
    @Test
    public void deleteParent_test() {
        Long parentId = 97L;
        parentRepo.deleteById(parentId);
        // parentRepo.deleteAll();
    }

}
