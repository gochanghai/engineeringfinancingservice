package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.ProjectCreditEntity;
import com.shenhua119.leadservice.service.ProjectCreditService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-07 19:38
 */
@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("pcc")
public class ProjectCreditInfoController {
    @Autowired
    private ProjectCreditService projectCreditService;

    //
    @PostMapping("save")
    public Msg saveFinancier(ProjectCreditEntity projectCreditEntity){
        System.out.println();

        boolean result = projectCreditService.save(projectCreditEntity);
        System.out.println("保存成功： " + result);
        return  Msg.success().add("financier", projectCreditEntity);
    }

    //
    @GetMapping("list")
    public Msg list(){
        List<ProjectCreditEntity> list = projectCreditService.list(null);
        System.out.println("获取成功： " + list.size());
        return  Msg.success().add("list",list);
    }
}
