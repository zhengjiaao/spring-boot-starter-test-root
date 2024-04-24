package com.zja.dao.relationship.manyToMany;

import com.zja.entitys.relationship.manyToMany.Course;
import com.zja.entitys.relationship.manyToMany.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author: zhengja
 * @since: 2024/04/19 16:46
 */
@SpringBootTest
public class CourseAndStudentRepoTest {

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    StudentRepo studentRepo;

    @Test
    public void test_deleteAll() {
        studentRepo.deleteAll();
        courseRepo.deleteAll();
    }

    // 创建学生和课程并建立关联
    @Test
    public void test_1() {
        Student student = new Student();
        student.setName("John");

        Course course = new Course();
        course.setName("Math");

        Course course1 = new Course();
        course1.setName("Math");

        // 建立关联关系
        student.getCourses().add(course);

        // 先保存Course实体
        courseRepo.save(course);
        courseRepo.save(course1);

        // 再保存Student实体，会触发生成关联表数据
        studentRepo.save(student);
    }

    // 级联查询
    @Test
    @Transactional // 添加@Transactional，解决懒加载问题
    public void test_2() {
        String studentId = "662604be77f67486e638209a";
        String courseId = "662604be77f67486e638209b";

        System.out.println("==========Student==========");

        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            // 级联查询关联的实体
            List<Course> courseList = student.getCourses(); // 需添加@Transactional，解决懒加载问题
            courseList.forEach(e -> System.out.println(e.getName()));
        }

        System.out.println("==========Course==========");

        Optional<Course> optionalCourse = courseRepo.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            // 级联查询关联的实体
            List<Student> studentList = course.getStudents(); // 需添加@Transactional，解决懒加载问题
            studentList.forEach(e -> System.out.println(e.getName()));
        }
    }

    // Student 绑定 某个Course
    @Test
    @Transactional
    @Commit // 解决更新后，数据无法入库情况
    public void test_3() {
        String studentId = "6626033d77f692deea703d42";
        String courseId = "6626033a77f6dedf709911c4";

        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        Optional<Course> optionalCourse = courseRepo.findById(courseId);
        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();
            student.setName("Updated Name John");

            // 取消全部绑定
            // student.getCourses().clear();

            // 取消绑定
            // student.getCourses().remove(optionalCourse.orElse(null));

            // 添加绑定
            student.getCourses().add(optionalCourse.orElse(null));
            studentRepo.save(student);
        }
    }

    // Student 移除 某个Course
    @Test
    @Transactional
    @Commit // 解决更新后，数据无法入库情况
    public void test_4() {
        String studentId = "662604be77f67486e638209a";
        String courseId = "6626033a77f6dedf709911c4";

        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        Optional<Course> optionalCourse = courseRepo.findById(courseId);

        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();

            // 取消绑定
            student.getCourses().remove(optionalCourse.get());
            studentRepo.save(student);
        }
    }

    // 重置绑定，先取消全部绑定，再进行绑定
    @Test
    @Transactional
    @Commit
    public void test_5() {
        String studentId = "662604be77f67486e638209a";
        String courseId = "662604be77f67486e638209b";

        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        Optional<Course> optionalCourse = courseRepo.findById(courseId);
        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();
            student.setName("Updated Name John");

            // 取消全部绑定
            student.getCourses().clear();

            // 绑定
            student.getCourses().add(optionalCourse.orElse(null));
            studentRepo.save(student);
        }
    }


    // Student 级联操作 Course ，支持：新增、更新、删除、查询
    @Test
    @Transactional
    @Commit
    public void test_6() {
        // 在 Student 类中，添加 CascadeType.REMOVE 级联删除 Course
        // @ManyToMany(cascade = CascadeType.REMOVE)
        // private List<Course> courses = new ArrayList<>();

        String studentId = "662604be77f67486e638209a";

        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        // 删除 Student，同时级联删除绑定的所有 Course 记录
        // 注：这里不仅仅是删除绑定的中间表数据，还有 Course 记录
        optionalStudent.ifPresent(student -> studentRepo.delete(student));
    }

    // 分页查询
    @Test
    public void test_7() {

        int pageNumber = 0; // 页码，从0开始
        int pageSize = 10; // 每页大小

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // 按指定id查询
        Page<Course> coursePage = courseRepo.findCoursesByStudentId("6626033d77f692deea703d42", pageable);

        List<Course> courseList = coursePage.getContent();
        courseList.forEach(e -> System.out.println(e.getName()));
    }

    @Test
    public void test_8() {

        int pageNumber = 0; // 页码，从0开始
        int pageSize = 10; // 每页大小

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // 按指定ids查询
        List<String> studentIds = Arrays.asList("6626033d77f692deea703d42", "6626033d77f692deea703d41"); // 学生的ID列表
        Page<Course> coursePage = courseRepo.findCoursesByStudentIds(studentIds, pageable);

        List<Course> courseList = coursePage.getContent();
        courseList.forEach(e -> System.out.println(e.getName()));
    }
}
