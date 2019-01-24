package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.FinanceCompanyEntity;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.FinanceCompanyService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-07 1:42
 * 资金公司控制器
 */
@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("fc")
public class FinanceCompanyController {

    @Autowired
    private FinanceCompanyService financeCompanyService;
    @Autowired
    private UserService userService;

    // 保存数据
    @PostMapping("save")
    public Msg save(FinanceCompanyEntity fc){
        System.out.println(fc.getCompanyFullName() + fc.getContactPerson());
        boolean result = financeCompanyService.save(fc);
        if (result){
            fc = financeCompanyService.getOne(new QueryWrapper<FinanceCompanyEntity>().eq("phone",fc.getPhone()));
            User user = new User();
            user.setUserName(fc.getUserName()).setPhone(fc.getPhone()).setPassword("123456")
                    .setUserInfoId(fc.getId()).setUserType(1).setRoleId(2L);
            boolean result2 = userService.save(user);
            return Msg.success();
        }

        return Msg.fail();
    }

    // 获取数据
    @GetMapping("list")
    public Msg list(){
        System.out.println("获取数据");
        List<FinanceCompanyEntity> list = financeCompanyService.list(null);
        return Msg.success().add("list", list);
    }

    // 获取数据
    @GetMapping("options")
    public Msg fundNCompanyameList(){
        System.out.println("options 获取数据");
        List<FinanceCompanyEntity> list = financeCompanyService.listCompanyNameAndComId();
        return Msg.success().add("list", list);
    }
}
