package com.sp.session_authorize;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author eric
 * @date 2022/3/19 12:56
 **/
@Component
public class SessionDatabase {
    Map<String,SessionUser> map = new HashMap<>();

    {
        map.put("liyaqiu", new SessionUser("liyaqiu", "123456", Arrays.asList("resource1Permission")));
        map.put("eric", new SessionUser("liyaqiu", "123456", Arrays.asList("resource2Permission")));
    }

    public SessionUser getUser(String userName){
        return map.get(userName);
    }

}
