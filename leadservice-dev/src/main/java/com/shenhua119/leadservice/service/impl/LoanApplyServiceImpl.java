package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.LoanApplyDao;
import com.shenhua119.leadservice.entity.LoanExamineapprove;
import com.shenhua119.leadservice.entity.ProjectLoanApply;
import com.shenhua119.leadservice.service.LoanApplyService;
import com.shenhua119.leadservice.service.LoanApprovalService;
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
    @Autowired
    private LoanApprovalService loanApprovalService;

    /** 获取融资人的放款信息list **/
    @Override
    public List<ProjectLoanApply> listByFinancierId(Long id) {
        return loanApplyDao.selectByFinancierId(id);
    }

    /** 获取工程公司的放款信息list **/
    @Override
    public List<ProjectLoanApply> listByCompanyId(Long id) {
        return loanApplyDao.selectByCompanyId(id);
    }

    @Override
    public List<ProjectLoanApply> listAll() {
        return loanApplyDao.selectAll2();
    }

    @Override
    public List<ProjectLoanApply> listByFcompanyId(Long id) {
        return loanApplyDao.selectByFcompanyId(id);
    }

    @Override
    public ProjectLoanApply loanApplyById(Long id) {
        return loanApplyDao.selectLoanApplyInfoDetailsById(id);
    }

    @Override
    public boolean saveExamineApprove(LoanExamineapprove approve) {
//        var where = new QueryWrapper<ProjectLoanApply>().eq("company_id", approve.getApplyId());
        ProjectLoanApply apply = getById(approve.getApplyId());
        if(apply == null){
            return false;
        }
        if(approve.getResult() == 1){
            if(apply.getStep() == 2){
                apply.setFcompanyId(3L);
            }
            apply.setStep(apply.getStep() + 1);
        }
        if (approve.getResult() == -1){
            apply.setStatus(-1);
        }
        boolean save = loanApprovalService.save(approve);
        if(save){
            return updateById(apply);
        }
        return false;
    }
}
