package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.LoanExamineapprove;
import com.shenhua119.leadservice.service.LoanApprovalService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 贷款审批控制器 </p>
 * @author liuchanghai
 * @create 2019-01-06 22:30 *
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("loan_appr")
public class LoanApprovalController {

    @Autowired
    private LoanApprovalService loanApprovalService;

    /**
     * 保存工程公司审批信息
     * @param lai
     * @return
     */
    @PostMapping("ec_save")
    public Msg saveEngComApproval(LoanExamineapprove lai){
        System.out.println("ec: "  + lai);
        boolean b = loanApprovalService.save(lai);
        return Msg.success();
    }

    /**
     * 保存平台审批信息
     * @param lai
     * @return
     */
    @PostMapping("p_save")
    public Msg save(LoanExamineapprove lai){
        System.out.println("p: " + lai);
        boolean b = loanApprovalService.updateById(lai);
        return Msg.success();
    }

    /**
     * 保存资金方批复信息
     * @param lai
     * @return
     */
    @PostMapping("f_save")
    public Msg saveFundComApproval(LoanExamineapprove lai){
        System.out.println("fund com: " + lai);
        boolean b = loanApprovalService.updateById(lai);
        return Msg.success();
    }

}
