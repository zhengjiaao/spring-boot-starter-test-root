package com.zja.mustache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 15:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private String email;
}
