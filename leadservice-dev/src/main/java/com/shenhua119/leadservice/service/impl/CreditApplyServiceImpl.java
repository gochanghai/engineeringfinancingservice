package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.CreditApplyDao;
import com.shenhua119.leadservice.entity.ProjectCreditApply;
import com.shenhua119.leadservice.entity.CreditListView;
import com.shenhua119.leadservice.service.CreditApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
@Service
public class CreditApplyServiceImpl extends ServiceImpl<CreditApplyDao, ProjectCreditApply> implements CreditApplyService {

    @Autowired
    private CreditApplyDao creditApplyDao;

    @Override
    public List<CreditListView> selectByFinancierId(Long id) {
        return creditApplyDao.selectByFinancierId(id);
    }

    @Override
    public List<CreditListView> listByCompanyId(Long id) {
        return creditApplyDao.selectListByCompanyId(id);
    }

    @Override
    public List<CreditListView> listAll() {
        return creditApplyDao.selectListAll();
    }

    @Override
    public List<CreditListView> listByFundCompanyId(Long id) {
        return creditApplyDao.selectListByFundCompanyId(id);
    }
}
