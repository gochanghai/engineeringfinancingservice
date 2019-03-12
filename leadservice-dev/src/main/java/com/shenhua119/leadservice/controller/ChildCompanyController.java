package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.BranchCompany;
import com.shenhua119.leadservice.service.ChildCompanyService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-04 1:51
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("cc")
public class ChildCompanyController {

    @Autowired
    private ChildCompanyService childCompanyService;

    // 保存数据
    @PostMapping("save")
    public Msg save(BranchCompany cc){
        System.out.println(cc.toString());
        boolean result = childCompanyService.save(cc);
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }

    // 获取数据
    @GetMapping("list")
    public Msg list(Long id){
        System.out.println("获取数据");
        // 使用条件构造获取数据
        List<BranchCompany> list = childCompanyService
                .list(new QueryWrapper<BranchCompany>().eq("p_id",id));
        return Msg.success().add("list", list);
    }
}
