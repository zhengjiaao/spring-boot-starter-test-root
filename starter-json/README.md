# starter-json

SpringBoot 本身提供了 Jackson 作为默认json组件。

## 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-json</artifactId>
</dependency>
```

## 配置

```yaml
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    default-property-inclusion: non_null
    fail-on-unknown-properties: false
    fail-on-empty-beans: false
    serialization:
      indent_output: true
      write_dates_as_timestamps: false
      write_enums_using_to_string: true
    deserialization:
      fail_on_unknown_properties: false
      fail_on_empty_beans: false
      fail_on_null_for_primitives: false
      unwrap_single: true
      use_big_decimal_for_floats: true
      use_big_integer_for_ints: true
      use_javas_util_date: true
      use_lombok_setters: true
      use_lombok_getters: true
      use_lombok_withers: true
      use_lombok_setters_with_builder: true
      use_lombok_getters_with_builder: true
      use_lombok_withers_with_builder: true
      use_lombok_setters_with_all_args_constructor: true
```
