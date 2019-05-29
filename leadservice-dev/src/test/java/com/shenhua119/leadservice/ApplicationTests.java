package com.shenhua119.leadservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Test
    public void contextLoads() {
        String body ="{\"name\":\"JSON\",\"address\":\"北京市西城区\",\"age\":25}";
        String resStr = body.substring( 1, body.length()-1);
        System.out.println(resStr);
        String[] strings = resStr.split(",");
        Map<String, String> map = new HashMap<>();
        for (String item: strings) {
            String[] ss = item.split(":");
            map.put(ss[0],ss[1]);
            System.out.println(ss[0].substring(1,ss[0].length() - 1)+ "==" +ss[1].substring(1,ss[1].length() - 1));
        }
        System.out.println(map.get("idCard"));
    }

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gochanghai@aliyun.com");
        message.setTo("942059473@qq.com");
        message.setSubject("主题：springboot aliyun 简单邮件");
        message.setText("springboot aliyun 测试邮件内容");

        mailSender.send(message);
    }



}
