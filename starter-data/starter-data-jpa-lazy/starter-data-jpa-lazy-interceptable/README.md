# starter-data-jpa-lazy-interceptable

## jpa 懒加载配置

### 采用 实体类实现实体属性拦截接口 PersistentAttributeInterceptable + @Basic(fetch = FetchType.LAZY) 注解，实现自定义懒加载。

缺点：需要自己实现懒加载逻辑代码，才会用生效，包括`必须的 setter 方法，否则不会更新` and `必须的 getter 方法，否则不会懒加载`。

采用`PersistentAttributeInterceptable` + `@Basic(fetch = FetchType.LAZY)` 注解，实现懒加载。

配置文件（可选地，有些版本不需要配置）：

```yaml
spring:
  jpa:
    properties:
      hibernate:
        enable_lazy_load_no_trans: true # 默认false，若出现懒加载异常可打开
#    open-in-view: true # 默认false，可选地，若出现懒加载异常可打开
```

实体字段：

```java

@Getter
@Setter
@Entity
@Table(name = "t_project")
@EntityListeners(value = AuditingEntityListener.class)
public class Project implements Serializable, PersistentAttributeInterceptable {

    @Id
    private String id = String.valueOf(System.currentTimeMillis());

    /**
     * 名称
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * 项目周期
     */
    private String type;

    /**
     * 项目周期
     */
    private int cycle;

    /**
     * json 字符串存储字段
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "config_json")
    private JSONObject configJson;

    /**
     * 文本 or json字符串存储字段
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "config_text")
    private String configText;

    /**
     * 当前状态 正常 1，已删除 -1
     */
    @Column(name = "state")
    private Integer state = 1;

    /**
     * 是否为内置（true 系统自动创建）
     */
    @Column(name = "internal")
    private Boolean internal = false;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 排列顺序
     */
    @Column(name = "sort", nullable = false, unique = true)
    private Long sort = System.currentTimeMillis();

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private LocalDateTime createTime;

    /**
     * 最后一次修改时间
     */
    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


    // 以下是实现懒加载

    @Transient
    private PersistentAttributeInterceptor interceptor;

    @Override
    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
        return interceptor;
    }

    @Override
    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    // 必须的 getter 方法，否则不会懒加载
    public JSONObject getConfigJson() {
        // 避免二次读取数据库
        if (this.configJson != null) {
            return this.configJson;
        }

        // 自定义懒加载实现，需配合 @Basic(fetch = FetchType.LAZY) + enable_lazy_load_no_trans: true
        return (JSONObject) interceptor.readObject(this, "configJson", this.configJson);
    }

    // 必须的 getter 方法，否则不会懒加载
    public String getConfigText() {
        // 避免二次读取数据库
        if (this.configText != null) {
            return this.configText;
        }

        // 自定义懒加载实现，需配合 @Basic(fetch = FetchType.LAZY) + enable_lazy_load_no_trans: true
        return (String) interceptor.readObject(this, "configText", this.configText);
    }

    // 必须的 setter 方法，否则不会更新
    public void setConfigJson(JSONObject configJson) {
        if (configJson != null) {
            if (interceptor != null) {
                // 若更新不生效，未执行更新SQL语句时，需要 @Transactional 注解，若单元测试时，需配合 @Rollback(false)
                interceptor.writeObject(this, "configJson", this.configJson, configJson);
            }
        }

        this.configJson = configJson;
    }

    // 必须的 setter 方法，否则不会更新
    public void setConfigText(String configText) {
        if (configText != null) {
            if (interceptor != null) {
                // 若更新不生效，未执行更新SQL语句时，需要 @Transactional 注解，若单元测试时，需配合 @Rollback(false)
                interceptor.writeObject(this, "configText", this.configText, configText);
            } else { // 保存时，interceptor拦截器会是空
                // 可以选择抛出异常、记录日志或采取其他措施
                // throw new IllegalStateException("Interceptor is not initialized");
            }
        }
        this.configText = configText;
    }
}

```

测试

```java

@Test
@Rollback(false) // 默认回滚，测试数据不保存到数据库，@Rollback(false)：确保事务提交，以便可以看到数据库中的实际更新。
@Transactional
public void update_test() {
    Optional<Project> optional = repo.findByName("名称-1");
    optional.ifPresent(project -> {
        System.out.println(project.getId());
        System.out.println(project.getName());
        System.out.println(project.getCreateTime());
        System.out.println(project.getLastModifiedDate());
        System.out.println("-------------特殊字段-------------");
        System.out.println(project.getConfigJson());
        System.out.println(project.getConfigText());
        System.out.println("--------------------------");
    });

    Project project = optional.get();
    project.setCycle(3); // 可以生效

    // 若更新不生效，未执行更新SQL语句时，需要 @Transactional 注解，若单元测试时，需配合 @Rollback(false)
    project.setConfigJson(JSON.parseObject("{\"key\":\"value-更新后的值3\"}"));
    project.setConfigText("大文本字段-更新后的值3");

    repo.save(project);  // 更新,生效
    // repo.saveAndFlush(project);  // 强制刷新,生效
}

@Test
public void findAllPage_test() {
    int page = 0;
    int size = 2;
    Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

    // 查询条件
    Project request = new Project();
    request.setName("名称-1");
    Specification<Project> spec = buildQuery(request);

    Page<Project> pageEntity = repo.findAll(spec, PageRequest.of(page, size, sort)); // 支持懒加载
    List<Project> projectList = pageEntity.getContent();
    projectList.forEach(project -> {
        System.out.println(project.getId());
        System.out.println(project.getName());
        System.out.println(project.getCreateTime());
        System.out.println(project.getLastModifiedDate());
        System.out.println("-------------特殊字段-------------");
        System.out.println(project.getConfigJson());
        System.out.println(project.getConfigText());
        System.out.println("--------------------------");
    });
}

private Specification<Project> buildQuery(Project request) {
    // 构建查询条件
    return (root, query, cb) -> {
        List<Predicate> predicates = new ArrayList<>();
        // 关键词
        if (!StringUtils.isEmpty(request.getName())) {
            predicates.add(cb.like(root.get("name"), request.getName() + "%"));
        }
        // 将条件连接在一起
        return query.where(predicates.toArray(new Predicate[0])).getRestriction();
    };
}
```

测试结果：

```text
Hibernate: select project0_.id as id1_0_, project0_.create_time as create_t4_0_, project0_.cycle as cycle5_0_, project0_.internal as internal6_0_, project0_.last_modified_date as last_mod7_0_, project0_.name as name8_0_, project0_.remarks as remarks9_0_, project0_.sort as sort10_0_, project0_.state as state11_0_ from t_project project0_ where project0_.name like ? order by project0_.create_time desc limit ?
1719883977407
名称-1
2024-07-02T09:32:57.781
2024-07-02T09:32:57.781
-------------特殊字段-------------
Hibernate: select project_.config_json as config_j2_0_, project_.config_text as config_t3_0_ from t_project project_ where project_.id=?
{"key":"value"}
大文本字段
--------------------------
```

会再次查询懒加载字段。

测试：

```text
        System.out.println("-------------特殊字段-------------");
        // System.out.println(project.getConfigJson());
        // System.out.println(project.getConfigText());
        System.out.println("--------------------------");
```

测试结果：

```text
Hibernate: select project0_.id as id1_0_, project0_.create_time as create_t4_0_, project0_.cycle as cycle5_0_, project0_.internal as internal6_0_, project0_.last_modified_date as last_mod7_0_, project0_.name as name8_0_, project0_.remarks as remarks9_0_, project0_.sort as sort10_0_, project0_.state as state11_0_ from t_project project0_ where project0_.name like ? order by project0_.create_time desc limit ?
1719883977407
名称-1
2024-07-02T09:32:57.781
2024-07-02T09:32:57.781
-------------特殊字段-------------
--------------------------
```