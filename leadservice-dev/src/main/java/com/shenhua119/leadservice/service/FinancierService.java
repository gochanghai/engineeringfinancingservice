package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.BusinessManager;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:28
 */
public interface FinancierService extends IService<BusinessManager> {

    List<BusinessManager> selectAll();

    List<BusinessManager> listByCompanyId(Long companyId);
}
