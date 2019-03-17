package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.ProjectCost;
import com.shenhua119.leadservice.service.ProjectCostService;
import com.shenhua119.leadservice.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuchanghai
 * @create 2018-12-04 0:31
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/project")
public class ProjectCostController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProjectCostService projectCostService;

    /**
     * 更新项目的成本信息
     * @param cost
     * @return
     */
    @PutMapping("cost")
    public Msg update(ProjectCost cost){
        boolean b = projectCostService.updateById(cost);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 保存项目成本信息
     * @param cost
     * @return
     */
    @PostMapping("cost")
    public Msg save(ProjectCost cost){
        boolean result = projectCostService.save(cost);
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 获取项目成本信息
     * @param projectId
     * @return
     */
    @GetMapping("cost")
    public Msg get(Long projectId){
        System.out.println("获取数据");
        var where = new QueryWrapper<ProjectCost>().eq("project_id", projectId);
        ProjectCost cost = projectCostService.getOne(where);
        return Msg.success().add("cost",cost);
    }

}
