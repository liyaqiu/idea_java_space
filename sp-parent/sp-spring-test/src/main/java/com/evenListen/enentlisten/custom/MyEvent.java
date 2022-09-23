package com.evenListen.enentlisten.custom;

import org.springframework.context.ApplicationEvent;

/**
 * @author lyq
 * @date 2022/2/24 1:09
 */

public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
