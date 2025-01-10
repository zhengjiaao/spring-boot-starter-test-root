package com.zja.amqp.rabbit.listener.service;

import com.zja.amqp.rabbit.listener.model.Organization;
import org.springframework.stereotype.Service;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 13:55
 */
@Service
public class OrganizationService {

    public void save(Organization org) {
        System.out.println("保存组织机构: " + org);
    }

    public void update(Organization org) {
        System.out.println("更新组织机构: " + org);
    }

    public void delete(String organizationId) {
        System.out.println("删除组织机构: " + organizationId);
    }
}
