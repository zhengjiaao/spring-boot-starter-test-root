package com.zja.json.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: zhengja
 * @Date: 2025-04-14 14:00
 */
// 在实体类中使用
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    // 字段过滤: 通过 @JsonView 实现了灵活的字段过滤，避免了不必要的敏感信息暴露。
    // 可以结合 Jackson 的其他注解（如 @JsonIgnore 或 @JsonProperty）进一步增强字段控制能力。

    @JsonView(JsonViews.Public.class)
    private Long id;

    @JsonView(JsonViews.Public.class)
    private String name;

    @JsonView(JsonViews.Internal.class)
    private BigDecimal costPrice;
}