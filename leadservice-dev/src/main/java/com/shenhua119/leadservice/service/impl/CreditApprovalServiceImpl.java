package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.CreditApprovalDao;
import com.shenhua119.leadservice.entity.CreditExamineapprove;
import com.shenhua119.leadservice.entity.ProjectCreditApply;
import com.shenhua119.leadservice.service.CreditApplyService;
import com.shenhua119.leadservice.service.CreditApprovalService;
import com.shenhua119.leadservice.service.EngineeringCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
@Service
public class CreditApprovalServiceImpl extends ServiceImpl<CreditApprovalDao, CreditExamineapprove> implements CreditApprovalService {

    @Autowired
    private CreditApplyService creditApplyService;
    @Autowired
    private EngineeringCompanyService engineeringCompanyService;

    /**
     * 审批提交
     *
     * @param ca
     */
    @Override
    public boolean approveSubmit(CreditExamineapprove ca) {
        ProjectCreditApply projectCreditApply = creditApplyService.getById(ca.getApplyId());
        System.out.println(projectCreditApply.toString());
        // 保存授信审批信息
        updateById(ca);

        /**
         * 工程公司审批：1:通过 2：退回 -1：拒绝
         */
        if (ca.getResult() != null){
            // 担保通过
            if (ca.getResult() == 1){

                projectCreditApply.setStep(3).setStatus(0);
                return creditApplyService.updateById(projectCreditApply);
            }
            // 担保退回
            if (ca.getResult() == 2){
                projectCreditApply.setStep(2).setStatus(2);
                return creditApplyService.updateById(projectCreditApply);
            }
            // 担保拒绝
            if (ca.getResult() == -1){
                projectCreditApply.setStep(2).setStatus(-1);
                return creditApplyService.updateById(projectCreditApply);
            }
        }

        /**
         * 平台审批：1:通过 2：退回 -1：拒绝
         */
        if (ca.getResult() != null){
            // 平台通过
            if (ca.getResult() == 1){
                projectCreditApply.setStep(4).setStatus(0);
                // 设置资金方ID
                projectCreditApply.setFcompanyId(engineeringCompanyService.getById(projectCreditApply.getCompanyId()).getFComId());
                return creditApplyService.updateById(projectCreditApply);
            }
            // 平台退回
            if (ca.getResult() == 2){
                projectCreditApply.setStep(3).setStatus(2);
                return creditApplyService.updateById(projectCreditApply);
            }
            // 平台拒绝
            if (ca.getResult() == -1){
                projectCreditApply.setStep(3).setStatus(-1);
                return creditApplyService.updateById(projectCreditApply);
            }
        }

        /**
         * 资金方批复：1:通过 3：特批 -1：拒绝
         */
        if ( ca.getResult() != null){
            // 资金方通过
            if (ca.getResult() == 1){
                projectCreditApply.setStep(5).setStatus(0);
                return creditApplyService.updateById(projectCreditApply);
            }
            // 资金方特批
            if (ca.getResult() == 3){
                projectCreditApply.setStep(4).setStatus(3).setCreditType(2);
                return creditApplyService.updateById(projectCreditApply);
            }
            // 资金方拒绝
            if (ca.getResult() == -1){
                projectCreditApply.setStep(4).setStatus(-1);
                return creditApplyService.updateById(projectCreditApply);
            }
        }
        return false;
    }
}
