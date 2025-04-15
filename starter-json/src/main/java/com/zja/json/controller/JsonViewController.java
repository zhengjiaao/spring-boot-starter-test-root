package com.zja.json.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zja.json.model.JsonViews;
import com.zja.json.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 字段过滤
 *
 * @Author: zhengja
 * @Date: 2025-04-14 14:01
 */
@RequestMapping("/json")
@RestController
public class JsonViewController {

    // 在控制器中使用
    @GetMapping("/{id}")
    @JsonView(JsonViews.Public.class) // 仅返回Public视图的数据
    public Product getProductPublic(@PathVariable Long id) {
        return new Product(id, "Product Name", new BigDecimal("100.00"));
    }

    @GetMapping("/{id}/internal")
    @JsonView(JsonViews.Internal.class) // 仅返回Internal视图的数据
    public Product getProductInternal(@PathVariable Long id) {
        return new Product(id, "Product Name", new BigDecimal("100.00"));
    }
}
