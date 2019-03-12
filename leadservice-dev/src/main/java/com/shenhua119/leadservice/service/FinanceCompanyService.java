package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.FinanceCompany;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-07 1:45
 */
public interface FinanceCompanyService extends IService<FinanceCompany> {

    List<FinanceCompany> listCompanyNameAndComId();

    /**
     * 新建资金方并创建用户账号
     * @param finance
     * @return
     */
    boolean saveAndCreateAccount(FinanceCompany finance);
}
