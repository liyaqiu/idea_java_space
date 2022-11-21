package com.text;

/**
 * @author eric
 * @date 2022/11/3 16:06
 **/
public class Test {
    public static void main(String[] args) {
        ThreadLocal local = new ThreadLocal();
        local.set("");
        local.remove();
    }
}
