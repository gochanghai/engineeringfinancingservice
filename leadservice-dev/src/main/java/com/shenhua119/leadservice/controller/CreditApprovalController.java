package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.CreditExamineapprove;
import com.shenhua119.leadservice.entity.ProjectCreditApply;
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
@RequestMapping("api/credit/appr")
public class CreditApprovalController {

    @Autowired
    private CreditApprovalService creditApprovalService;

    //保存工程公司审批信息
    @PostMapping("ec/save")
    public Msg save(CreditExamineapprove ca){
        System.out.println(ca);
        boolean result = creditApprovalService.save(ca);
        if (result){
            return Msg.success().add("message","成功");
        }
        return Msg.fail().add("message","失败");
    }

    // 保存数据
    @PostMapping("save")
    public Msg update(CreditExamineapprove ca){
        System.out.println(ca);
        boolean result = creditApprovalService.approveSubmit(ca);
        if (result){
            return Msg.success().add("message","成功");
        }
        return Msg.fail().add("message","失败");
    }

    // 保存数据
    @PostMapping("sign")
    public Msg signAgreement(ProjectCreditApply apply){
        System.out.println(apply);
        boolean result = creditApprovalService.signAgreement(apply);
        if (result){
            return Msg.success().add("message","成功");
        }
        return Msg.fail().add("message","失败");
    }


}
