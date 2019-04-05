package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.LoanApplyDao;
import com.shenhua119.leadservice.entity.ProjectLoanApply;
import com.shenhua119.leadservice.service.LoanApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
@Service
public class LoanApplyServiceImpl extends ServiceImpl<LoanApplyDao, ProjectLoanApply> implements LoanApplyService {
    @Autowired
    private LoanApplyDao loanApplyDao;

    /** 获取融资人的放款信息list **/
    @Override
    public List<ProjectLoanApply> listByFinancierId(Long id) {
        return loanApplyDao.selectByFinancierId(id);
    }

    /** 获取工程公司的放款信息list **/
    @Override
    public List<ProjectLoanApply> listByEngCompanyId(Long id) {
        return loanApplyDao.selectByEngCompanyId(id);
    }

    @Override
    public List<ProjectLoanApply> listAll() {
        return loanApplyDao.selectAll2();
    }

    @Override
    public List<ProjectLoanApply> listByFundCompanyId(Long id) {
        return loanApplyDao.selectByFundCompanyId(id);
    }

    @Override
    public ProjectLoanApply loanApplyById(Long id) {
        return loanApplyDao.selectLoanApplyInfoDetailsById(id);
    }
}
