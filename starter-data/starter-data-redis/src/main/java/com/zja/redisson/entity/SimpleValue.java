package com.zja.redisson.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SimpleValue implements Serializable {
    private String value;

    public SimpleValue(String value) {
        this.value = value;
    }
}
