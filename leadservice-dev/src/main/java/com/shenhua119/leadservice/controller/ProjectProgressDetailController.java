package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.ProjectProgressDetail;
import com.shenhua119.leadservice.service.ProjectProgressDetailService;
import com.shenhua119.leadservice.service.ProjectService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:22
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/project")
public class ProjectProgressDetailController {

    @Autowired
    private ProjectProgressDetailService projectProgressDetailService;
    @Autowired
    private ProjectService projectContractInfoService;

    /**
     * 保存项目进度明细
     * @param progress
     * @return
     */
    @PostMapping("progress")
    public Msg save(ProjectProgressDetail progress){
        System.out.println("项目" + progress.getProjectId() + " 添加项目进度");
        boolean result = projectProgressDetailService.saveAndProjectProgress(progress);
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 获取单个项目的进度明细列表
     * @param projectId
     * @return
     */
    @GetMapping("progress/list")
    public Msg list(Long projectId){
        System.out.println("项目: " + projectId + " 获取数据");
        // 使用条件构造获取数据
        var where = new QueryWrapper<ProjectProgressDetail>().eq("project_id", projectId).orderByDesc("date");
        List<ProjectProgressDetail> list = projectProgressDetailService.list(where);
        return Msg.success().add("list", list);
    }
}
