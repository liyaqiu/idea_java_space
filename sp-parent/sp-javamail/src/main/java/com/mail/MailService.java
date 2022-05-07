package com.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author lyq
 * @date 2022/3/12 11:34
 */
@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;


    public void sendSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("361234567@qq.com(eric)");
        message.setTo("361234567@qq.com");
        message.setSubject("今天记得学习!");
        message.setText("好好学习，天天向上。");
        javaMailSender.send(message);

    }

    public void sendMultiPartMail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom("361234567@qq.com(eric)");
        messageHelper.setTo("1262885678@qq.com");
        messageHelper.setSubject("今天记得学习!");
        messageHelper.setText("好好学习，天天向上。<a href='https://www.baidu.com'>有不懂问题可以点我</a>",true);

        messageHelper.addAttachment("pic.png", new File("D:\\idea_workspace\\sp-parent\\sp-javamail\\src\\main\\resources\\2.png"));
        messageHelper.addAttachment("log4j2.xml",new File("D:\\idea_workspace\\sp-parent\\sp-javamail\\src\\main\\resources\\log4j2.xml"));
        javaMailSender.send(message);

    }
}
