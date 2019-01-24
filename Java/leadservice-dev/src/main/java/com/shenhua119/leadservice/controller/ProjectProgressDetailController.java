package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.ProjectEntity;
import com.shenhua119.leadservice.entity.ProjectProgressDetail;
import com.shenhua119.leadservice.service.ProjectService;
import com.shenhua119.leadservice.service.ProjectProgressDetailService;
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
@RequestMapping("ppd")
public class ProjectProgressDetailController {

    @Autowired
    private ProjectProgressDetailService projectProgressDetailService;
    @Autowired
    private ProjectService projectContractInfoService;

    // 保存数据
    @PostMapping("save")
    public Msg save(ProjectProgressDetail ppd){
        System.out.println("项目" + ppd.getProjectId() + " 添加项目进度");
        boolean result1 = projectProgressDetailService.save(ppd);
        ProjectEntity pc = new ProjectEntity();
        pc.setId(ppd.getProjectId()).setProjectProgress(ppd.getConstructionProgress());
        if (result1){
            boolean result2 = projectContractInfoService.updateById(pc);
            return Msg.success();
        }
        return Msg.fail();
    }

    // 获取数据
    @GetMapping("list")
    public Msg list(Long id){
        System.out.println("项目: " + id + " 获取数据");
        // 使用条件构造获取数据
        List<ProjectProgressDetail> list = projectProgressDetailService
                .list(new QueryWrapper<ProjectProgressDetail>().eq("project_id", id).orderByDesc("date"));
        return Msg.success().add("list", list);
    }
}
