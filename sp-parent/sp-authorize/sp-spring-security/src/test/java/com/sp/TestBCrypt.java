package com.sp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author eric
 * @date 2022/3/19 20:09
 **/

@Slf4j
public class TestBCrypt {

    @Test
    public void test(){

        String salt1 = BCrypt.gensalt();
        String passwd1 = BCrypt.hashpw("123456", salt1);
        log.info("passwd1 {} salt {}",passwd1,salt1);

        String salt2 = BCrypt.gensalt();
        String passwd2 = BCrypt.hashpw("123456", salt2);
        log.info("passwd1 {} salt {}",passwd2,salt2);

        log.info("{}",BCrypt.checkpw("123456", passwd1));
        log.info("{}",BCrypt.checkpw("123456", passwd2));
    }
}
