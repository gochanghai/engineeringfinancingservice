package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.EngineeringCompanyDao;
import com.shenhua119.leadservice.entity.EngineeringCompanyEntity;
import com.shenhua119.leadservice.entity.EngineeringCompanyView;
import com.shenhua119.leadservice.service.EngineeringCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:01
 */
@Service
public class EngineeringCompanyServiceImpl extends ServiceImpl<EngineeringCompanyDao, EngineeringCompanyEntity> implements EngineeringCompanyService {

    @Autowired
    private EngineeringCompanyDao engineeringCompanyDao;

    @Override
    public List<EngineeringCompanyView> listAll() {
        return engineeringCompanyDao.selectAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public EngineeringCompanyEntity getEngineeringCompanyById(Long id) {
        return engineeringCompanyDao.getById(id);
    }
}
