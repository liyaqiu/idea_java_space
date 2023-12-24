package com.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/2/26 13:04
 */
@Component
/**
 * 1，每次启动，由于controller是单例对象，spring启动的时候只赋值一次。就算这里配置多例，在controller里面也只能看到同样hash
 * 2.如果想每次获取多例对象，需要每次applicationContext.getBean()
 * 3.或者controller设置成为多例
 * */
@Scope("prototype")
public class People {

    public People(){
        System.out.println("people.init");
    }

}
