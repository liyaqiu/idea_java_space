package com.sp.session_authorize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author eric
 * @date 2022/3/19 12:46
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
public class SessionUser {
    private String userName;
    private String password;
    private List<String> permissions;
}
