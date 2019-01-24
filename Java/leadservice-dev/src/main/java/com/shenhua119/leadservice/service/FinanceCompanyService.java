package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.FinanceCompanyEntity;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-07 1:45
 */
public interface FinanceCompanyService extends IService<FinanceCompanyEntity> {

    List<FinanceCompanyEntity> listCompanyNameAndComId();
}
