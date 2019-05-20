package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.ProjectOther;
import com.shenhua119.leadservice.service.ProjectOtherService;
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
public class ProjectOtherController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProjectOtherService projectOtherService;

    /**
     * 保存项目其它信息
     * @param other
     * @return
     */
    @PostMapping("other/insert")
    public Msg save(ProjectOther other){
        System.out.println("保存项目数据");
        boolean b = projectOtherService.save(other);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 更新项目其它信息
     * @param other
     * @return
     */
    @PostMapping("other/update")
    public Msg update(ProjectOther other){
        System.out.println("更新项目其它信息");
        boolean b = projectOtherService.updateById(other);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 获取项目其它信息
     * @param projectId
     * @return
     */
    @GetMapping("other")
    public Msg get(Long projectId){
        System.out.println("获取数据");
        var where = new QueryWrapper<ProjectOther>().eq("project_id", projectId);
        ProjectOther other = projectOtherService.getOne(where);
        return Msg.success().add("other",other);
    }

}
