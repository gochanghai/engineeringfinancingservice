package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.ProjectDao;
import com.shenhua119.leadservice.entity.Project;
import com.shenhua119.leadservice.entity.ProjectList;
import com.shenhua119.leadservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-05 19:06
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<ProjectList> selectByCompanyId(Long companyId) {
        return projectDao.selectByCompanyId(companyId);
    }

    @Override
    public List<Project> selectByFId(Long fId) {
        return projectDao.selectByFId(fId);
    }
}
