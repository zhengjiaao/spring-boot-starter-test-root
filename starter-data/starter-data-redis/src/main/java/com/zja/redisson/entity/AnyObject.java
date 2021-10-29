package com.zja.redisson.entity;

import java.io.Serializable;

public class AnyObject implements Serializable {

    private Integer a;

    public AnyObject(Integer a) {
        this.a = a;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "AnyObject{" +
                "a=" + a +
                '}';
    }
}
