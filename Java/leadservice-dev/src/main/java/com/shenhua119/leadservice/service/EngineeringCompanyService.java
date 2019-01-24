package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.EngineeringCompanyEntity;
import com.shenhua119.leadservice.entity.EngineeringCompanyView;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:00
 */
public interface EngineeringCompanyService extends IService<EngineeringCompanyEntity> {

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
    EngineeringCompanyEntity getEngineeringCompanyById(Long id);
}
