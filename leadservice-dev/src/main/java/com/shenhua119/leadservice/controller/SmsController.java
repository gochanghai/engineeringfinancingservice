package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.common.Aliyunsms;
import com.shenhua119.leadservice.entity.SMSCode;
import com.shenhua119.leadservice.service.SMSCodeService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/sms")
public class SmsController {

    @Autowired
    private SMSCodeService smsCodeService;

    /**
     * 获取短信验证码
     * @param phone
     * @return
     */
    @GetMapping("code")
    public Msg getSmsCodeByPhoneNumber(String phone){
        System.out.println("用户获取" + phone );
        var code =""+ (int)((Math.random() * 9 + 1) * 100000) +"";
        boolean sms = Aliyunsms.sendSms(phone, code);
        SMSCode smsCode = new SMSCode();
        smsCode.setCheckCode(code).setPhoneNumber(phone).setValidTime(5*60);
        smsCodeService.save(smsCode);
        return Msg.success().add("code", code);
    }
}
