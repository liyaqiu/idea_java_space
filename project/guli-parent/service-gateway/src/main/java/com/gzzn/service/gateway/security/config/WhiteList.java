package com.gzzn.service.gateway.security.config;

import cn.hutool.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author eric
 * @date 2022/11/15 9:49
 **/
public class WhiteList {
    public static List<String> urls = new ArrayList<>();
    static {
        urls.add("/abc1");
        urls.add("/abc2");
        urls.add("/abc3");
        urls.add("/abc4");
    }

    public static void main(String[] args) {
        System.out.println(WhiteList.urls.contains("/abc1"));
    }
}
