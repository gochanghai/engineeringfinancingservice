package com.shenhua119.leadservice.controller;

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

    // 保存数据
    @PostMapping("info/save")
    public Msg projectInfoSave(ProjectContractEntity contract, ProjectCostEntity cost, ProjectPaymentEntity payment, ProjectOtherEntity other){
        System.out.println(contract.toString());
        System.out.println(cost.toString());
        System.out.println(payment.toString());
        System.out.println(other.toString());
        logger.info(contract.toString());
        logger.info(cost.toString());
        logger.info(payment.toString());
        logger.info(other.toString());
        boolean result0 =  projectContractService.updateById(contract);
        boolean result1 = projectPaymentService.updateById(payment);
        boolean result2 = projectCostService.updateById(cost);
        boolean result3 = projectOtherService.updateById(other);
        return Msg.success();
    }

    // 保存数据
    @PostMapping("save")
    public Msg save(ProjectEntity project){
        System.out.println(project.toString());
        boolean result = projectService.save(project.setProjectProgress(0.0));
        if (result == true){
            ProjectContractEntity contract = new ProjectContractEntity();
            ProjectCostEntity cost = new ProjectCostEntity();
            ProjectPaymentEntity payment = new ProjectPaymentEntity();
            ProjectOtherEntity other = new ProjectOtherEntity();
            contract.setId(project.getId());
            projectContractService.save(contract);
            cost.setId(project.getId());
            projectCostService.save(cost);
            payment.setId(project.getId());
            projectPaymentService.save(payment);
            other.setId(project.getId());
            projectOtherService.save(other);
            return Msg.success();
        }
        return Msg.fail();
    }

    // 获取数据
    @GetMapping("list")
    public Msg list(Long id){
        System.out.println("获取数据");
//        List<ProjectEntity>  list = projectContractInfoService.list(new QueryWrapper<ProjectEntity>()
//                .eq("company_id",id));
        List<ProjectList> list1 = projectService.selectByCompanyId(id);
        System.out.println(list1.size());
        return Msg.success().add("list",list1);
    }
}
