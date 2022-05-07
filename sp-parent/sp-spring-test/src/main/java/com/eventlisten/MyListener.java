package com.eventlisten;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/2/24 1:08
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
