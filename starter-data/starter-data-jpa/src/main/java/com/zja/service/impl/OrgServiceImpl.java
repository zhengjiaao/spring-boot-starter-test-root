/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 17:20
 * @Since:
 */
package com.zja.service.impl;

import cn.hutool.core.util.StrUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zja.dao.OrgRepo;
import com.zja.entitys.Org;
import com.zja.entitys.QOrg;
import com.zja.entitys.QUser;
import com.zja.entitys.User;
import com.zja.manager.UserSystemManager;
import com.zja.mapper.OrgMapper;
import com.zja.mapper.RoleMapper;
import com.zja.mapper.UserMapper;
import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.OrgBindRolesRequest;
import com.zja.model.request.OrgPageSearchRequest;
import com.zja.model.request.OrgRequest;
import com.zja.model.request.OrgUpdateRequest;
import com.zja.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: zhengja
 * @since: 2023/08/10 17:20
 */
@Slf4j
@Validated
@Service
@Transactional
public class OrgServiceImpl implements OrgService {

    @Autowired
    OrgRepo repo;

    @Autowired
    UserSystemManager manager;

    @Autowired
    OrgMapper mapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public OrgDTO findById(String id) {
        Org entity = this.getOrgById(id);
        return mapper.map(entity);
    }

    private Org getOrgById(String orgId) {
        return this.getOrgById(orgId, "传入的组织机构 id=「{}」 有误！");
    }

    private Org getParentOrg(String parentOrgId) {
        return this.getOrgById(parentOrgId, "传入的父组织机构 id=「{}」 有误！");
    }

    private Org getOrgById(String orgId, String errorMsgTemplate) {
        return repo.findById(orgId).orElseThrow(() -> new RuntimeException(StrUtil.format(errorMsgTemplate, orgId)));
    }

    @Override
    public PageData<OrgDTO> pageList(OrgPageSearchRequest request) {
        int page = request.getPage();
        int size = request.getSize();
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

        //查询条件
        Specification<Org> spec = buildQuery(request);
        //分页查询
        Page<Org> sourcePage = repo.findAll(spec, PageRequest.of(page, size, sort));

        return PageData.of(mapper.mapList(sourcePage.getContent()), page, size, sourcePage.getTotalElements());
    }

    private Specification<Org> buildQuery(OrgPageSearchRequest request) {
        // 构建查询条件
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 关键词
            if (!StringUtils.isEmpty(request.getName())) {
                predicates.add(cb.equal(root.get("name"), request.getName()));
            }
            // 将条件连接在一起
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }

    @Override
    public PageData<UserDTO> queryOrgUsers(String orgId, Integer page, Integer size) {
        JPAQuery<User> query = queryFactory.selectFrom(QUser.user)
                .innerJoin(QUser.user.orgs, QOrg.org)
                .where(QOrg.org.id.eq(orgId))
                .offset((long) page * size)
                .limit(size);

        List<UserDTO> userDTOList = query.fetch().stream().map(userMapper::map).collect(Collectors.toList());
        long totalSize = query.fetchCount();
        return PageData.of(userDTOList, page, size, totalSize);
    }

    @Override
    public List<RoleDTO> queryOrgRoles(String orgId) {
        Org org = this.getOrgById(orgId);
        return roleMapper.mapList(org.getRoles());
    }

    @Override
    public Boolean checkOrgName(String orgName) {
        Optional<Org> byName = repo.findByName(orgName);
        return !byName.isPresent();
    }

    @Override
    public OrgDTO add(OrgRequest request) {
        // 检验是否有重名的
        Optional<Org> byName = repo.findByName(request.getName());
        if (byName.isPresent()) {
            throw new UnsupportedOperationException("机构名称不能重复");
        }
        Org org = mapper.map(request);
        String parentOrgId = request.getParentOrgId();
        Org parentOrg = this.getParentOrg(parentOrgId);
        // 设置父机构，同时更新层级信息、是否有子集
        org.updateParent(parentOrg);
        org = repo.save(org);
        return mapper.map(org);
    }

    @Override
    public List<OrgDTO> addBatch(List<OrgRequest> orgRequests) {
        return orgRequests.stream().map(this::add).collect(Collectors.toList());
    }

    @Override
    public OrgDTO update(String id, OrgUpdateRequest request) {
        // 校验下组织机构是否存在
        Org org = this.getOrgById(id);

        // 更新普通字段
        BeanUtils.copyProperties(request, org);

        if (!org.getParent().getId().equals(request.getParentOrgId())) {
            Org newParentOrg = this.getParentOrg(request.getParentOrgId());
            org.updateParent(newParentOrg);
        }
        org = repo.save(org);

        return mapper.map(org);
    }

    @Override
    public void deleteById(String id) {
        if (!repo.findById(id).isPresent()) {
            return;
        }
        repo.deleteById(id);
    }

    public void deleteBatch(List<String> ids) {
        ids.forEach(this::deleteById);
    }

    // ===================组织机构和角色、用户关联==================

    @Override
    public void bindOrgRoles(OrgBindRolesRequest orgBindRolesRequest) {
        manager.bindOrgRoles(orgBindRolesRequest);
    }

    @Override
    public void bindOrgUsers(String id, List<String> userIds) {
        manager.bindOrgUsers(id, userIds);
    }

    @Override
    public void unbindOrgUsers(String id, List<String> userIds) {
        manager.unbindOrgUsers(id, userIds);
    }

}