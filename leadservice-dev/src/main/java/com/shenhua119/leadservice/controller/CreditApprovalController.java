package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.CreditApprovalEntity;
import com.shenhua119.leadservice.service.CreditApprovalService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuchanghai
 * @create 2019-01-04 14:25
 */
@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("credit-appr")
public class CreditApprovalController {

    @Autowired
    private CreditApprovalService creditApprovalService;

    // 保存数据
    @PostMapping("ec/save")
    public Msg save(CreditApprovalEntity ca){
        System.out.println(ca);
        boolean result = creditApprovalService.approveSubmit(ca);
        if (result){
            return Msg.success().add("ok","ok");
        }
        return Msg.fail();
    }


}
