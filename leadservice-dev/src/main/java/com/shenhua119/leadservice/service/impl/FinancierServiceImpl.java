package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.entity.FinancierEntity;
import com.shenhua119.leadservice.service.FinancierService;
import com.shenhua119.leadservice.dao.FinancierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:31
 */
@Service
public class FinancierServiceImpl extends ServiceImpl<FinancierDao, FinancierEntity> implements FinancierService {

    @Autowired
    private FinancierDao financierDao;

    @Override
    public List<FinancierEntity> selectAll() {
        return financierDao.selectAll();
    }

    @Override
    public List<FinancierEntity> listByCompanyId(Long companyId) {
        return financierDao.selectListByComId(companyId);
    }
}
