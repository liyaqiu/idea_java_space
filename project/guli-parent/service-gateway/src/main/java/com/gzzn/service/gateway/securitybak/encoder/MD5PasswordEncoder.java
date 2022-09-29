package com.gzzn.service.gateway.securitybak.encoder;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/9/21 19:33
 **/
@Component
public class MD5PasswordEncoder implements PasswordEncoder {

    private static final String SALT = "abcdefg123321";

    @Override
    public String encode(CharSequence password) {
        return DigestUtil.md5Hex(password+SALT);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(DigestUtil.md5Hex(rawPassword+SALT).equals(encodedPassword)){
            return true;
        }
        return false;
    }
}
