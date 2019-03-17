package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.ProjectContract;
import com.shenhua119.leadservice.service.ProjectContractService;
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
public class ProjectContractController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProjectContractService projectContractService;

    /**
     * 获取项目合同信息
     * @param projectId
     * @return
     */
    @GetMapping("contract")
    public Msg get(Long projectId){
        System.out.println("获取数据");
        var where = new QueryWrapper<ProjectContract>().eq("project_id", projectId);
        ProjectContract contract = projectContractService.getOne(where);
        return Msg.success().add("contract",contract);
    }

    /**
     * 保存项目合同信息
     * @param contract
     * @return
     */
    @PostMapping("contract")
    public Msg save(ProjectContract contract){
        System.out.println(contract.toString());
        logger.info(contract.toString());
        boolean b = projectContractService.save(contract);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 更新项目合同信息
     * @param contract
     * @return
     */
    @PutMapping("contract")
    public Msg update(ProjectContract contract){
        System.out.println("更新项目合同信息");
        boolean b = projectContractService.updateById(contract);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }
}
