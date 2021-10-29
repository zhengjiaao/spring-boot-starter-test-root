package com.zja.redisson.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyKey implements Serializable {
    private String key;

    public MyKey(String key) {
        this.key = key;
    }
}
