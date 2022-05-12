package com.listen.enentlisten.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/2/24 1:08
 * 配置方式1
 *      如果使用,@Component配置，获取的事件相对较少，部分启动的时候的初始化事件可能监听不到
 * 配置方式2
 *      配置在META-INF spring.factories文件
 *      org.springframework.context.ApplicationListener=\
 *          com.listen.enentlisten.custom.MyListener
 *
 */
@Slf4j
@Component
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        EventObject eventObject = (EventObject) myEvent.getSource();
        log.error("监听到事件:{}",eventObject);
    }
}
