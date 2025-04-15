package com.zja.json.controller;

import com.zja.json.model.ApiResponse;
import com.zja.json.model.PageResponse;
import com.zja.json.model.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhengja
 * @Date: 2025-04-14 13:56
 */
@RequestMapping("/user")
@RestController
public class UserController {

    // 1. 返回简单JSON
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return new User() {{
            setId(id);
            setUsername("jackson_user");
            setCreateTime(LocalDateTime.now());
            setRoles(Arrays.asList("ADMIN", "USER"));
            setSecretField("sensitive-data");
        }};
    }

    @GetMapping("/v2/{id}")
    public User getUserV2(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("jackson_user");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDate(new Date());
        return user;
    }

    // 2. 接收JSON请求体
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(1L);
        user.setCreateTime(LocalDateTime.now());
        return user;
    }

    // 3. 返回复杂嵌套JSON
    @GetMapping("/complex")
    public ApiResponse<List<User>> getComplexResponse() {
        List<User> users = Arrays.asList(
                new User() {{
                    setId(1L);
                    setUsername("user1");
                }},
                new User() {{
                    setId(2L);
                    setUsername("user2");
                }}
        );
        return ApiResponse.success(users);
    }

    // 4. 分页查询
    @GetMapping
    public PageResponse<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<User> content = Arrays.asList(
                new User() {{
                    setId(1L);
                    setUsername("user1");
                }},
                new User() {{
                    setId(2L);
                    setUsername("user2");
                }}
        );

        return new PageResponse<>(
                content,
                page,
                size,
                2,
                1
        );
    }
}
