# starter-thymeleaf

spring-boot-starter-thymeleaf 是一个 Spring Boot Starter，它简化了在 Spring Boot 应用程序中集成 Thymeleaf 模板引擎的过程。Thymeleaf 是一种现代服务器端 Java 模板引擎，可以处理 HTML、XML、JavaScript、CSS 等文件类型，特别适合构建动态网页。

通过引入这个依赖，你可以轻松地在 Spring Boot 项目中使用 Thymeleaf 来创建和渲染模板，而无需进行繁琐的配置。Spring Boot 会自动配置 Thymeleaf 的基本设置，使你可以快速开始开发。

1. 添加依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

2. 创建控制器

```java
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "hello"; // 返回模板名称
    }
}
```

3. 创建 Thymeleaf 模板

> 在 src/main/resources/templates 目录下创建一个名为 hello.html 的 Thymeleaf 模板文件。

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Hello Thymeleaf</title>
</head>
<body>
    <h1 th:text="${message}">Default Message</h1>
</body>
</html>
```

5. 访问页面

启动应用程序后，打开浏览器并访问 http://localhost:8080/hello，你应该会看到页面上显示 Hello, Thymeleaf!。

resource.properties 的常见用途：不过，Spring Boot 更常见的做法是使用 application.properties 或 application.yml 文件来管理配置。

resource.properties 的常见用途

- 国际化（i18n）支持

如果你正在实现多语言支持，resource.properties 可能是用来存储不同语言的消息资源文件。例如：

messages.properties
messages_zh_CN.properties
messages_en_US.properties

- 自定义配置

项目中可能有一些特定的配置项不适合放在 application.properties 中，可以选择创建一个 resource.properties 文件来管理这些配置。例如，数据库连接池的额外参数、第三方服务的 API 密钥等。

- 模板引擎配置

在某些情况下，resource.properties 可能用于配置 Thymeleaf 或其他模板引擎的特定属性，如模板路径、缓存设置等。
