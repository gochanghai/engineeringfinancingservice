package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.ProjectProgressDetail;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:20
 */
public interface ProjectProgressDetailService extends IService<ProjectProgressDetail> {
    /**
     * 保存进度明细并更新项目进度
     * @param progress
     * @return
     */
    boolean saveAndProjectProgress(ProjectProgressDetail progress);
}
