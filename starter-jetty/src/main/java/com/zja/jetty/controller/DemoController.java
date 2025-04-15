package com.zja.jetty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 16:30
 */
@RestController
public class DemoController {

    // http://localhost:8080/hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Jetty!";
    }

}