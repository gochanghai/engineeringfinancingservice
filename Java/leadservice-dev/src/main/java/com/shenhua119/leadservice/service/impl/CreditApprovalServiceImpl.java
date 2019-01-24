package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.CreditApprovalDao;
import com.shenhua119.leadservice.entity.CreditApplyEntity;
import com.shenhua119.leadservice.entity.CreditApprovalEntity;
import com.shenhua119.leadservice.service.CreditApplyService;
import com.shenhua119.leadservice.service.CreditApprovalService;
import com.shenhua119.leadservice.service.EngineeringCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
@Service
public class CreditApprovalServiceImpl extends ServiceImpl<CreditApprovalDao, CreditApprovalEntity> implements CreditApprovalService {

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
    public boolean approveSubmit(CreditApprovalEntity ca) {
        CreditApplyEntity creditApplyEntity = creditApplyService.getById(ca.getCreditId());
        System.out.println(creditApplyEntity.toString());
        creditApplyEntity.setUpdateDate(new Date());
        ca.setUpdateDate(new Date());
        // 保存授信审批信息
        updateById(ca);

        /**
         * 工程公司审批：1:通过 2：退回 -1：拒绝
         */
        if (ca.getEcResult() != null){
            // 担保通过
            if (ca.getEcResult() == 1){

                creditApplyEntity.setStep(3).setStatus(0);
                return creditApplyService.updateById(creditApplyEntity);
            }
            // 担保退回
            if (ca.getEcResult() == 2){
                creditApplyEntity.setStep(2).setStatus(2);
                return creditApplyService.updateById(creditApplyEntity);
            }
            // 担保拒绝
            if (ca.getEcResult() == -1){
                creditApplyEntity.setStep(2).setStatus(-1);
                return creditApplyService.updateById(creditApplyEntity);
            }
        }

        /**
         * 平台审批：1:通过 2：退回 -1：拒绝
         */
        if (ca.getPResult() != null){
            // 平台通过
            if (ca.getPResult() == 1){
                creditApplyEntity.setStep(4).setStatus(0);
                // 设置资金方ID
                creditApplyEntity.setFComId(engineeringCompanyService.getById(creditApplyEntity.getComId()).getFComId());
                return creditApplyService.updateById(creditApplyEntity);
            }
            // 平台退回
            if (ca.getPResult() == 2){
                creditApplyEntity.setStep(3).setStatus(2);
                return creditApplyService.updateById(creditApplyEntity);
            }
            // 平台拒绝
            if (ca.getPResult() == -1){
                creditApplyEntity.setStep(3).setStatus(-1);
                return creditApplyService.updateById(creditApplyEntity);
            }
        }

        /**
         * 资金方批复：1:通过 3：特批 -1：拒绝
         */
        if ( ca.getFResult() != null){
            // 资金方通过
            if (ca.getFResult() == 1){
                creditApplyEntity.setStep(5).setStatus(0);
                return creditApplyService.updateById(creditApplyEntity);
            }
            // 资金方特批
            if (ca.getFResult() == 3){
                creditApplyEntity.setStep(4).setStatus(3).setCreditType(2);
                return creditApplyService.updateById(creditApplyEntity);
            }
            // 资金方拒绝
            if (ca.getFResult() == -1){
                creditApplyEntity.setStep(4).setStatus(-1);
                return creditApplyService.updateById(creditApplyEntity);
            }
        }
        return false;
    }
}
