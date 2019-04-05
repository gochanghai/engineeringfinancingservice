package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.CreditExamineapprove;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
public interface CreditApprovalService extends IService<CreditExamineapprove> {

    /** 审批提交 */
    boolean approveSubmit(CreditExamineapprove ca);
}
