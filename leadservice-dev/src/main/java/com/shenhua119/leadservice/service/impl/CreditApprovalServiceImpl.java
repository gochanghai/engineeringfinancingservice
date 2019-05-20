package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.CreditApprovalDao;
import com.shenhua119.leadservice.entity.*;
import com.shenhua119.leadservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class CreditApprovalServiceImpl extends ServiceImpl<CreditApprovalDao, CreditExamineapprove> implements CreditApprovalService {

    @Autowired
    private CreditApplyService creditApplyService;
    @Autowired
    private EngineeringCompanyService engineeringCompanyService;
    @Autowired
    private SignAgreementService signAgreementService;
    @Autowired
    private CompanyFinanceCustomerService customerService;
    @Autowired
    private EngineeringCompanyCreditService companyCreditService;


    /**
     * 审批提交
     * @param ca
     */
    @Override
    public boolean approveSubmit(CreditExamineapprove ca) {
        ProjectCreditApply creditApply = creditApplyService.getById(ca.getApplyId());
        System.out.println(creditApply.toString());
        // 保存授信审批信息
        ca.setApplyNo(creditApply.getApplyNo());
        save(ca);
        /** 1:通过 2：退回 -1：拒绝 3：特批 */
        if (ca.getResult() != null){
            // 通过
            if (ca.getResult() == 1){
                // 设置资金方ID
                if(creditApply.getStep() == 3){
                    var where = new QueryWrapper<CompanyProductCredit>().eq("company_id", creditApply.getCompanyId());
                    CompanyProductCredit companyCredit = companyCreditService.getOne(where);
                    if(companyCredit != null){
                        creditApply.setFcompanyId(companyCredit.getFcompanyId());
                    }
                }
                // 资金方授信金额
                if(creditApply.getStep() == 4){
                    creditApply.setCreditAmount(ca.getAmount());
                }
                creditApply.setStep(1+ creditApply.getStep()).setStatus(0);
                return creditApplyService.updateById(creditApply);
            }
            // 退回
            if (ca.getResult() == 2){
                creditApply.setStep(creditApply.getStep() - 1).setStatus(2);
                return creditApplyService.updateById(creditApply);
            }
            // 拒绝
            if (ca.getResult() == -1){
                creditApply.setStatus(-1);
                return creditApplyService.updateById(creditApply);
            }
            // 资金方特批
            if (ca.getResult() == 3){
                creditApply.setStep(creditApply.getStep() - 1).setStatus(2).setCreditType(2);
                return creditApplyService.updateById(creditApply);
            }
        }
        return false;
    }

    /**
     * 签署协议
     * @param apply
     * @return
     */
    @Override
    public boolean signAgreement(ProjectCreditApply apply) {
        ProjectCreditApply apply1 = creditApplyService.getById(apply.getId());
        if(apply1 != null && apply.getStep() == 6){
            SignAgreement agreement = new SignAgreement();
            agreement.setCreditId(apply1.getId())
                     .setUserId(apply.getUserId())
                     .setSignName(apply.getName());
            if(apply.getStatus() == 1){
                agreement.setAgreementName("借款合同");
                signAgreementService.save(agreement);
                agreement.setAgreementName("保证合同");
                signAgreementService.save(agreement);
            }else {
                agreement.setAgreementName("担保合同");
                signAgreementService.save(agreement);
            }
            // 判断商务经理是否签署协议
            if (apply1.getStep() == 6 && apply1.getStatus() == 1){
                apply1.setStatus(0).setStep(7);
                return creditApplyService.updateById(apply1);
            }
            // 判断担保方是否签署协议
            if (apply1.getStep() == 6 && apply1.getStatus() == 2){
                apply1.setStatus(0).setStep(7);
                return creditApplyService.updateById(apply1);
            }
            return creditApplyService.updateById(apply1.setStatus(apply.getStatus()));
        }
        if(apply1 != null && apply1.getStep() == 7){
            if(apply.getStatus() == 1){
                apply1.setStep(apply1.getStep() + 1);
                creditApplyService.updateById(apply1);
                var where = new QueryWrapper<CompanyFinanceCustomer>().eq("customer_id", apply1.getUserId());
                CompanyFinanceCustomer customer = customerService.getOne(where);
                if(customer == null){
                    customer = new CompanyFinanceCustomer();
                    customer.setCustomerId(apply1.getUserId()).setUserId(apply.getUserId());
                }
                if(!customerService.updateById(customer)){
                    return customerService.save(customer);
                }
                return true;
            }
        }
        return false;
    }
}
