package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.LoanExamineapprove;
import com.shenhua119.leadservice.service.LoanApplyService;
import com.shenhua119.leadservice.service.LoanApprovalService;
import com.shenhua119.leadservice.service.LoanService;
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
@RequestMapping("loan/appr")
public class LoanApprovalController {

    @Autowired
    private LoanApprovalService loanApprovalService;
    @Autowired
    private LoanApplyService loanApplyService;

    @Autowired
    private LoanService loanService;

    /**
     * 保存平台审批信息
     * @param approve
     * @return
     */
    @PostMapping("save")
    public Msg save(LoanExamineapprove approve){
        System.out.println("p: " + approve);
        boolean b = loanApplyService.saveExamineApprove(approve);
        return Msg.success();
    }

    /**
     * 保存资金方批复信息
     * @param lai
     * @return
     */
    @PostMapping("/f/save")
    public Msg saveFcomApproval(LoanExamineapprove lai){
        System.out.println("fund com: " + lai);
        boolean b = loanApplyService.saveExamineApprove(lai);
        if(b && lai.getResult() == 1){
//            b = loanService.save(loan);
        }
        if(b){
            return Msg.success();
        }
        return Msg.fail();
    }

}
