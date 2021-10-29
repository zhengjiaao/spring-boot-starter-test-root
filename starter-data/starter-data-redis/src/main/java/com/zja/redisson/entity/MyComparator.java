package com.zja.redisson.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyComparator implements Serializable {
    private Integer a;

    public MyComparator(Integer a) {
        this.a = a;
    }
}
