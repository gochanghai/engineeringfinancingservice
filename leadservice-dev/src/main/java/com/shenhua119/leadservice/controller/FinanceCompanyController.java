package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.FinanceCompany;
import com.shenhua119.leadservice.service.FinanceCompanyService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import com.shenhua119.leadservice.vo.FinanceCustomerVO;
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
@RequestMapping("api/finance")
public class FinanceCompanyController {

    @Autowired
    private FinanceCompanyService financeCompanyService;
    @Autowired
    private UserService userService;

    // 保存数据
    @PostMapping("save")
    public Msg save(FinanceCompany finance){
        System.out.println(finance);
        boolean result = financeCompanyService.saveAndCreateAccount(finance);
        if(result){
            return Msg.fail().add("message","数据保存成功");
        }
        return Msg.fail().add("message","数据保存失败");
    }

    // 获取数据
    @GetMapping("list")
    public Msg list(){
        System.out.println("获取数据");
        List<FinanceCompany> list = financeCompanyService.list(null);
        return Msg.success().add("list", list);
    }

    // 获取数据
    @GetMapping("info")
    public Msg get(Long id){
        System.out.println("获取数据");
        FinanceCompany financeCompany = financeCompanyService.getById(id);
        return Msg.success().add("data", financeCompany);
    }

    // 获取数据
    @GetMapping("options")
    public Msg fundNCompanyameList(){
        System.out.println("options 获取数据");
        List<FinanceCompany> list = financeCompanyService.listCompanyNameAndComId();
        return Msg.success().add("list", list);
    }

    // 获取数据
    @PostMapping("customer")
    public Msg findCustomerList(Long id){
        System.out.println("options 获取数据");
        List<FinanceCustomerVO> list = financeCompanyService.listCustomeByCompanyId(id);
        return Msg.success().add("list", list);
    }
}
