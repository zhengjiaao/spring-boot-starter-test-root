///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2023-09-28 15:18
// * @Since:
// */
//package com.zja.dao.relationship.oneToMany;
//
//import com.zja.JpaApplicationTest;
//import com.zja.entitys.relationship.oneToMany.Child;
//import com.zja.entitys.relationship.oneToMany.Parent;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author: zhengja
// * @since: 2023/09/28 15:18
// */
//public class ParentChildTest extends JpaApplicationTest {
//
//    @Autowired
//    private ParentRepo parentRepository;
//
//    @Autowired
//    private ChildRepo childRepository;
//
//    @Test
//    public void createParentWithChildren_test() {
//        Parent parent = new Parent();
//        parent.setName("John");
//
//        Child child1 = new Child();
//        child1.setName("Child 1");
//        child1.setParent(parent);
//
//        Child child2 = new Child();
//        child2.setName("Child 2");
//        child2.setParent(parent);
//
//        parent.getChildren().add(child1);
//        parent.getChildren().add(child2);
//
//        parentRepository.save(parent);
//
//        assertNotNull(parent.getId());
//        assertNotNull(child1.getId());
//        assertNotNull(child2.getId());
//        assertEquals(2, parent.getChildren().size());
//    }
//
//    @Test
//    @Transactional
//    public void findParentWithChildren_test() {
//        Parent parent = new Parent();
//        parent.setName("John");
//
//        Child child1 = new Child();
//        child1.setName("Child 1");
//        child1.setParent(parent);
//
//        Child child2 = new Child();
//        child2.setName("Child 2");
//        child2.setParent(parent);
//
//        parent.getChildren().add(child1);
//        parent.getChildren().add(child2);
//
//        parentRepository.save(parent);  //添加 @Transactional后，未保存到表里
//
//        Parent savedParent = parentRepository.findById(parent.getId()).orElse(null);
//        assertNotNull(savedParent);
//        assertEquals("John", savedParent.getName());
//        assertEquals(2, savedParent.getChildren().size()); // 异常：lazily initialize，no Session ，推荐在API或方法上添加 @Transactional
//        assertTrue(savedParent.getChildren().contains(child1));
//        assertTrue(savedParent.getChildren().contains(child2));
//    }
//
//    @Test
//    public void deleteParentWithChildren_test() {
//        Parent parent = new Parent();
//        parent.setName("John");
//
//        Child child1 = new Child();
//        child1.setName("Child 1");
//        child1.setParent(parent);
//
//        Child child2 = new Child();
//        child2.setName("Child 2");
//        child2.setParent(parent);
//
//        parent.getChildren().add(child1);
//        parent.getChildren().add(child2);
//
//        parentRepository.save(parent);
//
//        parentRepository.delete(parent);
//
//        assertNull(parentRepository.findById(parent.getId()).orElse(null));
//        assertNull(childRepository.findById(child1.getId()).orElse(null));
//        assertNull(childRepository.findById(child2.getId()).orElse(null));
//    }
//
//    @Test
//    public void deleteParentWithChildren_test2() {
//        Parent parent = new Parent();
//        parent.setName("John");
//
//        Child child1 = new Child();
//        child1.setName("Child 1");
//        child1.setParent(parent);
//
//        Child child2 = new Child();
//        child2.setName("Child 2");
//        child2.setParent(parent);
//
//        parent.getChildren().add(child1);
//        parent.getChildren().add(child2);
//
//        parentRepository.save(parent);
//
////        parentRepository.delete(parent);
//
//        //支持单独删除 child
//        childRepository.delete(child1);
//        childRepository.delete(child2);
//
//        assertNull(childRepository.findById(child1.getId()).orElse(null));
//        assertNull(childRepository.findById(child2.getId()).orElse(null));
//    }
//}
