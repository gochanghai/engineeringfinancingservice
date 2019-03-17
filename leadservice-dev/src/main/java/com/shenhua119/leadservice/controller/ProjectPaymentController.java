package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.ProjectPayment;
import com.shenhua119.leadservice.service.ProjectPaymentService;
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
public class ProjectPaymentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProjectPaymentService projectPaymentService;

    /**
     * 更新项目款项信息
     * @param payment
     * @return
     */
    @PutMapping("payment")
    public Msg update(ProjectPayment payment){
        boolean b = projectPaymentService.updateById(payment);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 保存项目款项信息
     * @param payment
     * @return
     */
    @PostMapping("payment")
    public Msg save(ProjectPayment payment){
        boolean result = projectPaymentService.save(payment);
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 获取项目款项信息
     * @param projectId
     * @return
     */
    @GetMapping("payment")
    public Msg get(Long projectId){
        System.out.println("获取数据");
        var where = new QueryWrapper<ProjectPayment>().eq("project_id", projectId);
        ProjectPayment payment = projectPaymentService.getOne(where);
        return Msg.success().add("payment",payment);
    }
}
