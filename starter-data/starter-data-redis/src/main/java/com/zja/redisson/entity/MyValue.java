package com.zja.redisson.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyValue implements Serializable {
    private Object value;

    public MyValue(Object value) {
        this.value = value;
    }
}
