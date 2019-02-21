package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.LoanApplyEntity;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
public interface LoanApplyService extends IService<LoanApplyEntity> {

    /** 获取融资人的放款申请信息list */
    List<LoanApplyEntity> listByFinancierId(Long id);

    /** 获取工程公司的放款审批信息list */
    List<LoanApplyEntity> listByEngCompanyId(Long id);

    /** 获取平台的放款审批信息list */
    List<LoanApplyEntity> listAll();

    /** 获取资金方的放款批复信息list */
    List<LoanApplyEntity> listByFundCompanyId(Long id);
    /** 获取一条放款申请信息 */
    LoanApplyEntity loanApplyById(Long id);
}
