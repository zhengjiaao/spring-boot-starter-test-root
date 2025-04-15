package com.zja.undertow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    // http://localhost:8080/large-json
    @GetMapping("/large-json")
    public List<Map<String, Object>> largeJson() {
        return IntStream.range(0, 1000)
                .mapToObj(i -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", i);
                    map.put("value", UUID.randomUUID().toString());
                    return map;
                })
                .collect(Collectors.toList());
    }

}