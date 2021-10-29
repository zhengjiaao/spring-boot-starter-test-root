package com.zja.redisson.service.impl;

import com.zja.redisson.service.SomeServiceInterface;

public class SomeServiceImpl implements SomeServiceInterface {

    @Override
    public String doSomeStuff(String str) {

        if (str.equals("a")){
            return "is a";
        }
        return "not is a";
    }
}
