package com.evenListen.globalevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 如果使用,@Component配置，获取的事件相对较少。
 * @author lyq
 * @date 2022/2/24 1:08
 * 配置方式1
 *      如果使用,@Component配置，获取的事件相对较少。部分启动的时候的初始化事件可能监听不到
 * 配置方式2
 *      配置在META-INF spring.factories文件
 *      org.springframework.context.ApplicationListener=\
 *          com.listen.globalevent.MyApplicationListener
 */
@Slf4j
public class MyApplicationListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        System.out.println("MyApplicationListener---"+applicationEvent.getTimestamp());
//        System.out.println("MyApplicationListener---"+applicationEvent.getSource());
//        System.out.println("MyApplicationListener---"+applicationEvent.getClass());
    }
}
