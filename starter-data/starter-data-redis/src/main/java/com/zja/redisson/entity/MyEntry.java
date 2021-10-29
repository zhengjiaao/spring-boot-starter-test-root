package com.zja.redisson.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyEntry implements Comparable<MyEntry>, Serializable {
    private String key;
    private Integer value;

    public MyEntry(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(MyEntry o) {
        return key.compareTo(o.key);
    }
}
