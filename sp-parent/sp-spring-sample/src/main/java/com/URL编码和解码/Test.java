package com.URL编码和解码;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/15 13:33
 **/
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String baseUrl = "https://www.baidu.com" +
                "?name=%s" +
                "&age=%s";

        String name = "李雅秋";
        String age = "18";
        String formatUrl = String.format(baseUrl, URLEncoder.encode(name, StandardCharsets.UTF_8.toString()), age);
        System.out.println(formatUrl);
        System.out.println(URLDecoder.decode(formatUrl, StandardCharsets.UTF_8.toString()));
    }
}
