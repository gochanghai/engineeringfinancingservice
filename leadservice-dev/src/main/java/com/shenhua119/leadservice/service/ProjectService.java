package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.Project;
import com.shenhua119.leadservice.entity.ProjectList;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-05 19:05
 */
public interface ProjectService extends IService<Project> {

    List<ProjectList> selectByCompanyId(Long companyId);

    List<Project> selectByFId(Long fId);
}
