package com.test;

import java.util.Objects;

/**
 * @author eric
 * @date 2022/11/25 22:41
 **/
public class Test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("1123,123");
        System.out.println(sb.delete(0, 1).toString());
    }
}
