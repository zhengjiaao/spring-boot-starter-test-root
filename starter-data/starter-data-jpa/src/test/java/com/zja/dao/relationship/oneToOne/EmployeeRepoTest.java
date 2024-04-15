/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 13:10
 * @Since:
 */
package com.zja.dao.relationship.oneToOne;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.oneToOne.Employee;
import com.zja.entitys.relationship.oneToOne.Office;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author: zhengja
 * @since: 2023/09/28 13:10
 */
public class EmployeeRepoTest extends JpaApplicationTest {

    @Autowired
    private EmployeeRepo repo;

    @Autowired
    private OfficeRepo officeRepo;

    @Test
    public void save_test() {
        Office office = new Office();
        office.setOfficeName("办公室-1");
        officeRepo.save(office);

        Employee employee = new Employee();
        employee.setEmployeeName("雇员-李四");
        employee.setOffice(office); //必须先存在库表中
        repo.save(employee);

        System.out.println(employee); // Employee(id=10, employeeName=雇员-李四, office=Office(code=9, officeName=办公室-1))
        System.out.println(employee.getOffice()); //Office(code=9, officeName=办公室-1)
    }

    @Test
    @Transactional
    public void find_test() {
        Integer id = 6;

        Optional<Employee> byId = repo.findById(id);
        Employee employee = byId.orElseThrow(() -> new RuntimeException("id 错误"));
        System.out.println(employee.getEmployeeName()); //雇员-李四

        // 默认，是急加载，会级联查询
        // 也可以设置位懒加载 @OneToOne(fetch = FetchType.LAZY)，注意，设置懒加载可能会出现no Session错误，需要添加 @Transactional
        Office office = employee.getOffice();
        System.out.println(office.getOfficeName()); // 办公室-1
    }


    @Test
    public void update_test() {
        Integer id = 5;

        Optional<Employee> byId = repo.findById(id);
        Employee employee = byId.orElseThrow(() -> new RuntimeException("id 错误"));
        System.out.println(employee.getEmployeeName()); //雇员-李四

        Office office = employee.getOffice();
        System.out.println(office.getOfficeName()); // 办公室-1

        //更新操作: 有效更新
        office.setOfficeName("办公室-2");
        officeRepo.save(office);

        //todo 更新操作: 无效更新
       /* office.setOfficeName("办公室-2");
        employee.setOffice(office);
        repo.save(employee);*/

        Optional<Employee> byId2 = repo.findById(id);
        Employee employee2 = byId2.orElseThrow(() -> new RuntimeException("id 错误"));
        System.out.println(employee2.getEmployeeName()); //雇员-李四

        Office office2 = employee2.getOffice();
        System.out.println(office2.getOfficeName()); //办公室-2
    }


    @Test
    public void delete_test() {
        Integer id = 4;

        Optional<Employee> byId = repo.findById(id);
        Employee employee = byId.orElseThrow(() -> new RuntimeException("id 错误"));
        System.out.println(employee.getEmployeeName()); //雇员-李四

        repo.delete(employee); // 默认不会级联删除 Office，要设置级联删除 @OneToOne(cascade = CascadeType.REMOVE)

        Office office = employee.getOffice();
        //若Employee设置了@OneToOne(cascade = CascadeType.REMOVE),单独Office会报错。
        officeRepo.delete(office);   //删除 Office，不会级联删除 Employee
    }


}
