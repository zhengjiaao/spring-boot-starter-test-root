/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 13:02
 * @Since:
 */
package com.zja.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zja.dao.UserRepo;
import com.zja.entitys.*;
import com.zja.manager.UserSystemManager;
import com.zja.mapper.OrgMapper;
import com.zja.mapper.UserMapper;
import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.*;
import com.zja.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: zhengja
 * @since: 2023/08/10 13:02
 */
@Slf4j
@Validated
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo repo;

    @Autowired
    UserSystemManager manager;

    @Autowired
    UserMapper mapper;

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public UserDTO findById(String id) {
        User entity = this.getUserById(id);
        return mapper.map(entity);
    }

    private User getUserById(String userId) {
        return repo.findById(userId).orElseThrow(() -> new RuntimeException(StrUtil.format("传入的用户 id=「{}」 有误！", userId)));
    }

    // 采用 JPAQueryFactory 方式
    public PageData<UserDTO> pageList(UserPageSearchRequest request, Integer page, Integer size) {
        QUser user = QUser.user;
        QRole role = QRole.role;
        QOrg org = QOrg.org;
        // 构建查询条件
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        // 关键词
        if (StrUtil.isNotEmpty(request.getKey())) {
            booleanBuilder.and(user.loginName.like("%" + request.getKey() + "%").or(user.name.like("%" + request.getKey() + "%")));
        }
        // 角色
        if (CollUtil.isNotEmpty(request.getRoleIds())) {
            booleanBuilder.and(role.id.in(request.getRoleIds()));
        }
        // 组织
        if (CollUtil.isNotEmpty(request.getOrgIds())) {
            booleanBuilder.and(org.id.in(request.getOrgIds()));
        }
        // 状态
        if (request.getState() != null) {
            booleanBuilder.and(user.state.goe(request.getState()));
            if (request.getState() > 0) {
                booleanBuilder.and(user.state.goe(request.getState()));
            } else if (request.getState() < 0) {
                booleanBuilder.and(user.state.loe(request.getState()));
            }
        }

        JPAQuery<User> query = queryFactory.selectFrom(user)
                .distinct()
                .leftJoin(user.roles, role)
                .leftJoin(user.orgs, org)
                .where(booleanBuilder)
                .orderBy(user.sort.asc());
        // 分页处理
        long totalSize = query.fetchCount();
        List<User> fetch = query
                .offset((long) page * size)
                .limit(size)
                .fetch();
        return PageData.of(fetch, page, size, totalSize).map(mapper::mapList);
    }

    // 采用 jpa 方式
    @Override
    public PageData<UserDTO> pageList(UserPageSearchRequest request) {
        int page = request.getPage();
        int size = request.getSize();
        Sort sort = Sort.by(Sort.Direction.ASC, "sort");

        // 构建查询条件
        Specification<User> spec = buildQuery(request);
        // 分页查询
        Page<User> sourcePage = repo.findAll(spec, PageRequest.of(page, size, sort));

        return PageData.of(mapper.mapList(sourcePage.getContent()), page, size, sourcePage.getTotalElements());
    }

    private Specification<User> buildQuery(UserPageSearchRequest request) {
        // 构建查询条件
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            // 关键词
            String key = request.getKey();
            if (StrUtil.isNotEmpty(key)) {
                predicates.add(cb.or(
                        cb.like(root.get("name"), "%" + key + "%"),
                        cb.like(root.get("loginName"), "%" + key + "%"))
                );
            }
            // 角色
            List<String> roleIds = request.getRoleIds();
            if (CollUtil.isNotEmpty(roleIds)) {
                Join<User, Role> roleJoin = root.join("roles");
                predicates.add(roleJoin.get("id").in(roleIds));
            }
            // 组织
            List<String> orgIds = request.getOrgIds();
            if (CollUtil.isNotEmpty(orgIds)) {
                Join<User, Org> orgJoin = root.join("orgs");
                predicates.add(orgJoin.get("id").in(orgIds));
            }
            // 状态
            Integer state = request.getState();
            if (state != null) {
                if (state > 0) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("state"), state));
                } else if (state < 0) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("state"), state));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Boolean checkLoginName(String loginName) {
        Optional<User> byLoginName = repo.findByLoginName(loginName);
        return !byLoginName.isPresent();
    }

    @Override
    public UserDTO add(UserRequest request) {
        Optional<User> byLoginName = repo.findByLoginName(request.getLoginName());
        if (byLoginName.isPresent()) {
            throw new UnsupportedOperationException("用户登录名不能重复");
        }
        User user = mapper.map(request);
        if (CollUtil.isNotEmpty(request.getOrgIds())) {
            manager.bindUserOrges(user, request.getOrgIds());
        }
        user = repo.save(user);
        return mapper.map(user);
    }

    @Override
    public List<UserDTO> addBatch(List<UserRequest> userRequests) {
        return userRequests.stream().map(this::add).collect(Collectors.toList());
    }

    @Override
    public UserDTO update(String id, UserUpdateRequest request) {
        User user = this.getUserById(id);
        user.setName(request.getName());
        // loginName 字段只有是非内置用户才能修改
        if (!user.getInternal()) {
            user.setLoginName(request.getLoginName());
        }
        user.setState(request.getState());
        if (StrUtil.isNotEmpty(request.getPassword())) {
            user.setPassword(request.getPassword());
        }
        user.setUserAttachment(mapper.mapAttachment(request.getUserAttachment()));
        if (CollUtil.isNotEmpty(request.getOrgIds())) {
            manager.bindUserOrges(user, request.getOrgIds());
        }
        user = repo.save(user);
        return mapper.map(user);
    }

    @Override
    public void deleteById(String id) {
        if (!repo.findById(id).isPresent()) {
            return;
        }
        repo.deleteById(id);
    }

    @Override
    public void deleteBatch(List<String> ids) {
        ids.forEach(this::deleteById);
    }

    // ===================用户和组织机构、角色关联==================

    @Override
    public void bindUserRoles(UserBindRolesRequest userBindRolesRequest) {
        manager.bindUserRoles(userBindRolesRequest);
    }

    /**
     * 查看用户已关联的角色（包括用户关联的组织机构下绑定的角色）
     *
     * @param id 用户 id
     */
    @Override
    public List<RoleDTO> queryUserRoles(String id) {
        User user = this.getUserById(id);
        // todo 待写此逻辑，用户 + 用户所属组织机构下绑定的角色
        return null;
    }

    @Override
    public void bindUserOrges(UserBindOrgsRequest userBindOrgsRequest) {
        manager.bindUserOrges(userBindOrgsRequest);
    }

    @Override
    public List<OrgDTO> queryUserOrges(String id) {
        User user = this.getUserById(id);
        return orgMapper.mapList(user.getOrgs());
    }
}