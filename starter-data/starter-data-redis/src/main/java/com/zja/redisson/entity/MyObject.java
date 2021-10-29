package com.zja.redisson.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyObject implements Serializable {
    private String a;

    public MyObject(String a) {
        this.a = a;
    }
}
