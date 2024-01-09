package com.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 上下文 Holder
 */
public class WebContextHolder {


    private static final ThreadLocal<MyUser> user = new TransmittableThreadLocal<>();



    public static MyUser getUser() {
        return user.get();
    }

    public static void setUser(MyUser user) {
        WebContextHolder.user.set(user);
    }

    public static void clear() {
        user.remove();
    }

}

class MyUser{}
