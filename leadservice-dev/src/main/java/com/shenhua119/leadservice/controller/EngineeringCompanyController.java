package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.CompanyProductCredit;
import com.shenhua119.leadservice.entity.EngineeringCompany;
import com.shenhua119.leadservice.entity.EngineeringCompanyView;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.EngineeringCompanyCreditService;
import com.shenhua119.leadservice.service.EngineeringCompanyService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-04 1:30
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/engcom")
public class EngineeringCompanyController {

    @Autowired
    private EngineeringCompanyService engineeringCompanyService;
    @Autowired
    private UserService userService;
    @Autowired
    private EngineeringCompanyCreditService engineeringCompanyCreditService;

    @PutMapping("update")
    public Msg update(CompanyProductCredit ecc){
        boolean result = engineeringCompanyCreditService.save(ecc.setCreateDate(new Date()));
        EngineeringCompany engineeringCompany = new EngineeringCompany();
//        engineeringCompany.setId(ecc.getComId()).setFComId(ecc.getFComId()).setCreditAmount(ecc.getAmount());
        result = engineeringCompanyService.updateById(engineeringCompany);
        return Msg.success();
    }

    // 保存数据
    @PostMapping("save")
    public Msg save(EngineeringCompany ec){
        System.out.println(ec.toString());
        boolean result0 = false ;
        result0 = engineeringCompanyService.save(ec);
        if (result0){
          EngineeringCompany ec2 =  engineeringCompanyService.getOne(new QueryWrapper<EngineeringCompany>()
                    .eq("credit_code",ec.getCreditCode()));
          if (ec2 != null){
              User user = new User();
              user.setUsername(ec.getUsername1()).setPhone(ec.getPhone1()).setPassword("123456")
                      .setUserInfoId(ec2.getId()).setUserType(1).setRoleId(1L);
              boolean result1 = userService.save(user);
              user.setUsername(ec.getUsername2()).setPhone(ec.getPhone2()).setPassword("123456")
                      .setUserInfoId(ec2.getId()).setUserType(2).setRoleId(1L);
              boolean result2 = userService.save(user);
          }
        }

        return Msg.success();
    }

    // 获取数据
    @GetMapping("list")
    public Msg list(){
        System.out.println("获取工程公司数据");
        List<EngineeringCompanyView> list = engineeringCompanyService.listAll();
        return Msg.success().add("list", list);
    }

    // 获取企业信息
    @GetMapping("company_info")
    public Msg getEnginneringCompany(Long id){
        System.out.println("获取数据");
        EngineeringCompany engineeringCompany = engineeringCompanyService.getEngineeringCompanyById(id);
        return Msg.success().add("company", engineeringCompany);
    }
}
