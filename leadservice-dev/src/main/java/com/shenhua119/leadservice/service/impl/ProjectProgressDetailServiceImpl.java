package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.ProjectProgressDetailDao;
import com.shenhua119.leadservice.entity.Project;
import com.shenhua119.leadservice.entity.ProjectProgressDetail;
import com.shenhua119.leadservice.service.ProjectProgressDetailService;
import com.shenhua119.leadservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:21
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class ProjectProgressDetailServiceImpl extends ServiceImpl<ProjectProgressDetailDao, ProjectProgressDetail> implements ProjectProgressDetailService {
    @Autowired
    private ProjectService projectService;
    /**
     * 保存进度明细并更新项目进度
     * @param progress
     * @return
     */
    @Override
    public boolean saveAndProjectProgress(ProjectProgressDetail progress) {
        System.out.println(progress.toString());
        boolean b = save(progress);
        if (b){
            Project project = projectService.getById(progress.getProjectId());
            project.setProjectProgress(progress.getProgressRatio());
            return projectService.updateById(project);
        }
        return false;
    }
}
