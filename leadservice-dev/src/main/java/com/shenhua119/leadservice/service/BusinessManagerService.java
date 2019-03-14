package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.BusinessManager;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:28
 */
public interface BusinessManagerService extends IService<BusinessManager> {

    List<BusinessManager> selectAll();

    List<BusinessManager> listByCompanyId(Long companyId);

    /**
     * 保存商务经理并创建账号
     * @param businessManager
     * @return
     */
    boolean saveAndCreateAccount(BusinessManager businessManager);
}
