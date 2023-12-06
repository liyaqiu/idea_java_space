package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        Map<Object,Object> map = new HashMap<>();
        map.put("1","1");
        map.put(1,1);
        System.out.println(map.size());
        String.valueOf(100L);
        Integer i =1213;
        System.out.println(i.hashCode());

    }
}
