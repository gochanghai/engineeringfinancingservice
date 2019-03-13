package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.EngineeringCompany;
import com.shenhua119.leadservice.entity.EngineeringCompanyView;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:00
 */
public interface EngineeringCompanyService extends IService<EngineeringCompany> {

    /**
     *
     * @return
     */
    List<EngineeringCompanyView> listAll();

    /**
     *
     * @param id
     * @return
     */
    EngineeringCompany getEngineeringCompanyById(Long id);

    /**
     * 保存创建工程公司并且创建用户
     * @param engineering
     * @return
     */
    boolean saveAndCreateAccount(EngineeringCompany engineering);
}
