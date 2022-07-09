package com;

import java.lang.invoke.ConstantCallSite;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author eric
 * @date 2022/7/9 13:53
 **/
public class Test {
    public static void main(String[] args) {
        int i = 254;
        byte b = (byte) i;
        System.out.println(b);
    }
}
