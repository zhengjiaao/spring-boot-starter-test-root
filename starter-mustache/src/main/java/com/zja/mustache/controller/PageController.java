package com.zja.mustache.controller;

import com.zja.mustache.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 15:42
 */
@Controller
public class PageController {

    // http://localhost:8080/
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Mustache Demo");
        model.addAttribute("features", Arrays.asList(
                "Spring Boot 2.7",
                "Mustache Templates",
                "Auto Configuration"
        ));
        return "index";
    }

    // http://localhost:8080/user
    @GetMapping("/user")
    public String userProfile(Model model) {
        User user = new User("Alice", 28, "alice@example.com");
        model.addAttribute("title", "User Profile");
        model.addAttribute("user", user);
        model.addAttribute("showEmail", true);
        return "user";
    }
}