package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.*;
import com.shenhua119.leadservice.service.*;
import com.shenhua119.leadservice.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-04 0:31
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/project")
public class ProjectController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectCostService projectCostService;
    @Autowired
    private ProjectContractService projectContractService;
    @Autowired
    private ProjectPaymentService projectPaymentService;
    @Autowired
    private ProjectOtherService projectOtherService;

    /**
     * 保存项目
     * @param project
     * @return
     */
    @PostMapping("")
    public Msg save(Project project){
        boolean b = projectService.save(project);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 获取工程公司的项目
     * @param companyId
     * @return
     */
    @GetMapping("list")
    public Msg list(Long companyId){
        System.out.println("获取数据");
        List<ProjectList> list = projectService.selectByCompanyId(companyId);
        return Msg.success().add("list",list);
    }

    /**
     * 获取商务经理的项目
     * @param userId
     * @return
     */
    @GetMapping("list/bm")
    public Msg getProjectListByUserId(Long userId){
        System.out.println("获取数据");
        var where = new QueryWrapper<Project>().eq("user_id", userId);
        List<Project> list = projectService.list(where);
        return Msg.success().add("list",list);
    }

    /**
     * 获取项目详情信息
     * @param id
     * @return
     */
    @GetMapping("details")
    public Msg projectInfoDetails(Long id){
        System.out.println("details: " + id);
        Project project = projectService.getById(id);
        ProjectContract contract = projectContractService.getById(id);
        ProjectPayment payment = projectPaymentService.getById(id);
        ProjectCost cost = projectCostService.getById(id);
        ProjectOther other = projectOtherService.getById(id);
        return Msg.success()
                .add("project",project)
                .add("contract",contract)
                .add("payment",payment)
                .add("cost",cost)
                .add("other",other);
    }
}
