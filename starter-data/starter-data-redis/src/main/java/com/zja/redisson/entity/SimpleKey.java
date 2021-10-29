package com.zja.redisson.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SimpleKey implements Serializable {
    private String key;

    public SimpleKey(String key) {
        this.key = key;
    }
}
