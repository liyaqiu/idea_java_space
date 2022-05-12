package com.listen.enentlisten.Default;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/5/12 15:53
 * 配置方式1
 *      如果使用,@Component配置，获取的事件相对较少。部分启动的时候的初始化事件可能监听不到
 * 配置方式2
 *      配置在META-INF spring.factories文件
 *      org.springframework.context.ApplicationListener=\
 *          com.listen.enentlisten.Default.DefaultStartListener
 *
 * 常用事件
 * ApplicationStartingEvent 在应用运行但未进行任何处理时,发送此事件
 * ApplicationEnvironmentPreparedEvent 当Environment被使用，且上下文创建之前,发送此事件
 * ApplicationPreparedEvent 在开始刷新之前，bean定义被加载之后，发送此事件
 * ApplicationStartedEvent 在上下文刷新之后且所有的应用和命令运行器被调用之前发送，发送此事件
 * ApplicationReadyEvent 在应用程序和命令运行器调用之后，发送此事件，用于通知应用已经准备处理请求
 * ApplicationFailedEvent 启动时发生异常，发送此事件
 **/
@Slf4j
public class DefaultStartListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {

        //System.out.println("DefaultStartListenerDefaultStartListenerDefaultStartListenerDefaultStartListenerDefaultStartListener");
    }
}
