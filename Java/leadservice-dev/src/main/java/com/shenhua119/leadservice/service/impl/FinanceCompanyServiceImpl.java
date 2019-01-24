package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.FinanceCompanyDao;
import com.shenhua119.leadservice.entity.FinanceCompanyEntity;
import com.shenhua119.leadservice.service.FinanceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-07 1:45
 */

@Service
public class FinanceCompanyServiceImpl extends ServiceImpl<FinanceCompanyDao, FinanceCompanyEntity> implements FinanceCompanyService {

    @Autowired
    private FinanceCompanyDao financeCompanyDao;

    @Override
    public List<FinanceCompanyEntity> listCompanyNameAndComId() {
        return financeCompanyDao.selectListCompanyNameAndComId();
    }
}
