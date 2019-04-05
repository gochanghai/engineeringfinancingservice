package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.FinanceProductDao;
import com.shenhua119.leadservice.entity.FinanceProduct;
import com.shenhua119.leadservice.service.FinanceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
@Service
public class FinanceProductServiceImpl extends ServiceImpl<FinanceProductDao, FinanceProduct> implements FinanceProductService {

    @Autowired
    FinanceProductDao financeProductDao;

    @Override
    public List<Map> selectAll() {
        return financeProductDao.selectAll();
    }
}
