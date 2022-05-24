package com.对象引用5种;

import lombok.ToString;

import java.lang.ref.SoftReference;

/**
 * @author eric
 * @date 2022/5/24 9:41
 **/
public class Person {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize 。。。我已经被回收");
    }
}
