/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 16:30
 * @Since:
 */
package com.zja.dao.relationship.oneToMany;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.oneToMany.Child;
import com.zja.entitys.relationship.oneToMany.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: zhengja
 * @since: 2023/09/28 16:30
 */
public class ParentTest extends JpaApplicationTest {

    @Autowired
    private ParentRepo parentRepository;

    @Autowired
    private ChildRepo childRepository;

    @Test
    public void testCreateParent() {

        //先将Child对象保存到数据库中，然后再将其添加到Parent对象的children集合中。
        //否则无法保存Parent，会报错：正在尝试保存一个未持久化的实体对象
        Child child1 = new Child();
        child1.setName("Child 1");
        childRepository.save(child1);

        Child child2 = new Child();
        child2.setName("Child 2");
        childRepository.save(child2);

        Parent parent = new Parent();
        parent.setName("John");
        // Add children
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        // Save parent and children to the database
        parentRepository.save(parent);

        assertNotNull(parent.getId());
        assertNotNull(child1.getId());
        assertNotNull(child2.getId());
        assertEquals(2, parent.getChildren().size());
    }

    @Test
    @Transactional
    public void findParentWithChildren_test() {
        Child child1 = new Child();
        child1.setName("Child 1");
        childRepository.save(child1);

        Child child2 = new Child();
        child2.setName("Child 2");
        childRepository.save(child2);

        Parent parent = new Parent();
        parent.setName("John");
        // Add children
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        // Save parent and children to the database
        parentRepository.save(parent);  //添加 @Transactional后，未保存到表里，猜测是同一个事物childRepository还未保存导致的。

        assertNotNull(parent.getId());
        assertNotNull(child1.getId());
        assertNotNull(child2.getId());
        assertEquals(2, parent.getChildren().size());

        // Assert the parent and children are saved correctly
        Parent savedParent = parentRepository.findById(parent.getId()).orElse(null);
        assertNotNull(savedParent);
        assertEquals("John", savedParent.getName());
        assertEquals(2, savedParent.getChildren().size()); // 异常：lazily initialize，no Session ，推荐在API或方法上添加 @Transactional
        assertTrue(savedParent.getChildren().contains(child1));
        assertTrue(savedParent.getChildren().contains(child2));
    }

    @Test
    public void testDeleteParent() {
        // Create a parent to delete
        Child child1 = new Child();
        child1.setName("Child 1");
        childRepository.save(child1);

        Child child2 = new Child();
        child2.setName("Child 2");
        childRepository.save(child2);

        Parent parent = new Parent();
        parent.setName("John");
        // Add children
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        // Save parent and children to the database
        parentRepository.save(parent);

        // Delete the parent
        parentRepository.delete(parent);

        // Assert the parent is deleted correctly
        Parent deletedParent = parentRepository.findById(parent.getId()).orElse(null);
        assertNull(deletedParent);
    }

    @Test
    public void testDeleteChild() {
        Long childId = 103L;
        Child deletedChild = childRepository.findById(childId).orElse(null);
        assertNotNull(deletedChild);

        // Delete the child2
        childRepository.delete(deletedChild); //不能独立删除 Child
        assertNull(deletedChild);
    }


}
