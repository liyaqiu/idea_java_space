package com;

import com.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

/**
 * @author lyq
 * @date 2022/3/12 11:33
 */
@SpringBootTest
public class MailTest {

    @Autowired
    MailService mailService;

    @Test
    public void test0(){
        mailService.sendSimpleMail();
    }
    @Test
    public void test1() throws MessagingException {
        mailService.sendMultiPartMail();
    }
}
