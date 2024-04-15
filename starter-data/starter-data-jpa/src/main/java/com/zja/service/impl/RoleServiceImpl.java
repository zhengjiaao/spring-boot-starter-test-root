package com.zja.service.impl;

import cn.hutool.core.util.StrUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zja.dao.RoleRepo;
import com.zja.entitys.QRole;
import com.zja.entitys.QUser;
import com.zja.entitys.Role;
import com.zja.entitys.User;
import com.zja.manager.UserSystemManager;
import com.zja.mapper.OrgMapper;
import com.zja.mapper.RoleMapper;
import com.zja.mapper.UserMapper;
import com.zja.model.dto.OrgDTO;
import com.zja.model.dto.PageData;
import com.zja.model.dto.RoleDTO;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.Role2UsersRequest;
import com.zja.model.request.RolePageSearchRequest;
import com.zja.model.request.RoleRequest;
import com.zja.model.request.RoleUpdateRequest;
import com.zja.service.RoleService;
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
 * @since: 2024/02/21 16:04
 */
@Slf4j
@Validated
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo repo;

    @Autowired
    RoleMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    UserSystemManager manager;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public RoleDTO findById(String id) {
        Role entity = repo.findById(id).orElseThrow(() -> new RuntimeException("传入的 id 有误！"));
        return mapper.map(entity);
    }

    @Override
    public PageData<RoleDTO> pageList(RolePageSearchRequest request) {
        int page = request.getPage();
        int size = request.getSize();
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

        // 查询条件
        Specification<Role> spec = buildQuery(request);
        // 分页查询
        Page<Role> sourcePage = repo.findAll(spec, PageRequest.of(page, size, sort));

        return PageData.of(mapper.mapList(sourcePage.getContent()), page, size, sourcePage.getTotalElements());
    }

    private Specification<Role> buildQuery(RolePageSearchRequest request) {
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
    public Boolean checkRoleName(String roleName) {
        Optional<Role> byName = repo.findByRoleName(roleName);
        return !byName.isPresent();
    }

    @Override
    public RoleDTO add(RoleRequest request) {
        Optional<Role> byName = repo.findByRoleName(request.getName());
        if (byName.isPresent()) {
            throw new UnsupportedOperationException("角色名称不能重复");
        }
        Role role = mapper.map(request);
        role = repo.save(role);
        return mapper.map(role);
    }

    @Override
    public List<RoleDTO> addBatch(List<RoleRequest> roleRequests) {
        return roleRequests.stream().map(this::add).collect(Collectors.toList());
    }

    @Override
    public RoleDTO update(String id, RoleUpdateRequest request) {
        Role entity = repo.findById(id).orElseThrow(() -> new RuntimeException("传入的 id 有误！"));
        // 更新属性
        BeanUtils.copyProperties(request, entity);
        entity = repo.save(entity);
        return mapper.map(entity);
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
        repo.deleteAllByIdInBatch(ids);
    }


    // ===================角色和用户关联==================

    /**
     * 为角色增量绑定用户
     *
     * @param role2UsersRequest 角色用户关联实体
     */
    @Override
    public void bindRoleUsers(Role2UsersRequest role2UsersRequest) {
        manager.bindRoleUsers(role2UsersRequest);
    }

    /**
     * 取消绑定角色用户
     * @param role2UsersRequest 角色用户关联实体
     */
    @Override
    public void unbindRoleUsers(Role2UsersRequest role2UsersRequest) {
        manager.unbindRoleUsers(role2UsersRequest);
    }

    /**
     * 查看角色关联的用户 （分页）
     *
     * @param id   角色 id
     * @param key  关键词（用户名或登录名）
     * @param page 页码
     * @param size 每页数量
     * @return PageData<UserDTO>
     */
    @Override
    public PageData<UserDTO> queryRoleUsersByPage(String id, String key, Integer page, Integer size) {
        QUser user = QUser.user;
        QRole role = QRole.role;

        // 筛选条件
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(role.id.eq(id));
        if (StrUtil.isNotEmpty(key)) {
            booleanBuilder.and(user.loginName.like("%" + key + "%").or(user.name.like("%" + key + "%")));
        }

        // 构建查询
        JPAQuery<User> query = queryFactory.selectFrom(user)
                .innerJoin(user.roles, role)
                .where(booleanBuilder)
                .orderBy(user.sort.asc())
                .offset((long) page * size)
                .limit(size);
        // 截取对应的页
        List<UserDTO> userDTOList = query.fetch().stream().map(userMapper::map).collect(Collectors.toList());
        // 获取数量
        long totalSize = query.fetchCount();

        return PageData.of(userDTOList, page, size, totalSize);
    }

    // ===================角色和组织机构关联==================

    /**
     * 绑定角色与指定组织机构列表的关联关系
     *
     * @param id     角色 id
     * @param orgIds 机构 id 列表
     */
    @Override
    public void bindRoleOrges(String id, List<String> orgIds) {
        manager.bindRoleOrges(id, orgIds);
    }

    @Override
    public List<OrgDTO> queryRoleOrges(String id) {
        Role role = repo.findById(id).orElseThrow(() -> new RuntimeException("传入的 id 有误！"));
        return orgMapper.mapList(role.getOrgs());
    }

}