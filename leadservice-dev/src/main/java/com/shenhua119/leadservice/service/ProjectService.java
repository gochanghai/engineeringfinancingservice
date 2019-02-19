package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.ProjectEntity;
import com.shenhua119.leadservice.entity.ProjectList;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-05 19:05
 */
public interface ProjectService extends IService<ProjectEntity> {

    List<ProjectList> selectByCompanyId(Long companyId);

    List<ProjectEntity> selectByFId(Long fId);
}
