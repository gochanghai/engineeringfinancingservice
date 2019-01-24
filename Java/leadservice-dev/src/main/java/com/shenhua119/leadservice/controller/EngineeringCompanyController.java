package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.EngineeringCompanyCreditEntity;
import com.shenhua119.leadservice.entity.EngineeringCompanyEntity;
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
    public Msg update(EngineeringCompanyCreditEntity ecc){
        boolean result = engineeringCompanyCreditService.save(ecc.setCreateDate(new Date()));
        EngineeringCompanyEntity engineeringCompany = new EngineeringCompanyEntity();
        engineeringCompany.setId(ecc.getComId()).setFComId(ecc.getFComId()).setCreditAmount(ecc.getAmount());
        result = engineeringCompanyService.updateById(engineeringCompany);
        return Msg.success();
    }

    // 保存数据
    @PostMapping("save")
    public Msg save(EngineeringCompanyEntity ec){
        System.out.println(ec.toString());
        boolean result0 = false ;
        result0 = engineeringCompanyService.save(ec);
        if (result0){
          EngineeringCompanyEntity ec2 =  engineeringCompanyService.getOne(new QueryWrapper<EngineeringCompanyEntity>()
                    .eq("credit_code_number",ec.getCreditCodeNumber()));
          if (ec2 != null){
              User user = new User();
              user.setUserName(ec.getUserName1()).setPhone(ec.getPhone1()).setPassword("123456")
                      .setUserInfoId(ec2.getId()).setUserType(1).setRoleId(1L);
              boolean result1 = userService.save(user);
              user.setUserName(ec.getUserName2()).setPhone(ec.getPhone2()).setPassword("123456")
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
        EngineeringCompanyEntity engineeringCompany = engineeringCompanyService.getEngineeringCompanyById(id);
        return Msg.success().add("company", engineeringCompany);
    }
}
