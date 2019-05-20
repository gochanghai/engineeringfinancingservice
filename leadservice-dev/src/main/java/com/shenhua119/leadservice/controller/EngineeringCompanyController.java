package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.CompanyProductCredit;
import com.shenhua119.leadservice.entity.EngineeringCompany;
import com.shenhua119.leadservice.service.EngineeringCompanyCreditService;
import com.shenhua119.leadservice.service.EngineeringCompanyService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import com.shenhua119.leadservice.vo.CompanyInfoVO;
import com.shenhua119.leadservice.vo.CompanyListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        boolean result = engineeringCompanyCreditService.save(ecc);
        EngineeringCompany engineeringCompany = new EngineeringCompany();
        result = engineeringCompanyService.updateById(engineeringCompany);
        return Msg.success();
    }

    /**
     * 保存工程公司的数据
     * @param engineering
     * @return
     */
    @PostMapping("save")
    public Msg save(EngineeringCompany engineering){
        System.out.println(engineering.toString());
        boolean b = engineeringCompanyService.saveAndCreateAccount(engineering);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }

    // 获取数据
    @GetMapping("list")
    public Msg list(){
        System.out.println("获取工程公司数据");
        List<CompanyListVO> list = engineeringCompanyService.listAll();
//        List<EngineeringCompany> list = engineeringCompanyService.list(null);
        return Msg.success().add("list", list);
    }

    // 获取企业信息
    @GetMapping("company_info")
    public Msg getEnginneringCompany(Long id){
        System.out.println("获取数据");
        CompanyInfoVO company = engineeringCompanyService.getCompanyInfoById(id);
        return Msg.success().add("company", company);
    }


    /**
     * 给企业授信
     * @param cpc
     * @return
     */
    @PostMapping("credit")
    public Msg saveCompanyCredit(CompanyProductCredit cpc){
        System.out.println("工程公司授信开始-------------------");
        boolean result = engineeringCompanyCreditService.save(cpc);
        System.out.println("工程公司授信结束---------------");
        if (result){
            return Msg.success();
        }
        return Msg.fail();

    }
}
