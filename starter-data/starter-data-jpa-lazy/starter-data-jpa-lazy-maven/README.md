# starter-data-jpa-lazy-maven

## jpa 懒加载配置

### 采用 hibernate-enhance-maven-plugin + @Basic(fetch = FetchType.LAZY) 注解，实现懒加载。

缺点：偶尔遇到不生效，可能需要频繁编译maven项目，才会用生效。

采用`hibernate-enhance-maven-plugin` + `@Basic(fetch = FetchType.LAZY)` 注解，实现懒加载。

pom.xml文件

```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.hibernate.orm.tooling</groupId>
            <artifactId>hibernate-enhance-maven-plugin</artifactId>
            <version>${hibernate.version}</version>
            <executions>
                <execution>
                    <configuration>
                        <!--启用简单字段的延迟加载-->
                        <enableLazyInitialization>true</enableLazyInitialization>
                    </configuration>
                    <goals>
                        <goal>enhance</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

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
public class Project {

    @Id
    private String id = String.valueOf(System.currentTimeMillis());

    /**
     * 名称
     */
    @Column(nullable = false, length = 100)
    private String name;

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
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private LocalDateTime createTime;
}

```

测试

```java

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