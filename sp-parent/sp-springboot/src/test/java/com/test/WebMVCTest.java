package com.test;

import com.Launcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author lyq
 * @date 2022/3/8 14:32
 */
@SpringBootTest(classes = {Launcher.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//开启虚拟MVC调用
@AutoConfigureMockMvc
public class WebMVCTest {

    @Test
    public void test0(@Autowired MockMvc mockMvc) throws Exception {
        //构建虚拟请求信息
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/test2");
        //发起请求
        ResultActions resultActions = mockMvc.perform(requestBuilder);
        //对请求是否成功判断
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());


        //对响应头内容判断
        resultActions.andExpect(MockMvcResultMatchers.header().string("content-type", "application/json"));


        //对响应内容判断
        resultActions.andExpect(MockMvcResultMatchers.content().string("hello-world"));

        //对响应json内容判断
        //resultActions.andExpect(MockMvcResultMatchers.content().json("{json字符串}"));
    }
}
