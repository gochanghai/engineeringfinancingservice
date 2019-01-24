package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.*;
import com.shenhua119.leadservice.service.*;
import com.shenhua119.leadservice.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查Api
 * @author liuchanghai
 * @create 2019-01-10 0:17
 */
@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api")
public class CheckController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private EngineeringCompanyService engineeringCompanyService;
    @Autowired
    private FinancierService financierService;
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private FinanceCompanyService fundCompanyService;

    /**
     * 检查用户名是否可用
     * @param userName
     * @return
     */
    @GetMapping("checkUserName")
    public Msg checkUsername(String userName) {
        List<User> list = userService.list(new QueryWrapper<User>().eq("user_name", userName));
        if(list.size() >0 ){
            return Msg.success().add("result",1).add("message","用户名已存在");
        }
        return Msg.success().add("result",0).add("message","用户名可以使用");
    }

    /**
     * 检查手机号是否可用
     * @param phone
     * @return
     */
    @GetMapping("checkPhone")
    public Msg checkPhone(String phone) {
        List<User> list = userService.list(new QueryWrapper<User>().eq("phone", phone));
        System.out.println(list.size() + list.toString());
        if(list.size() >0 ){
            return Msg.success().add("result",1).add("message","手机号已被使用");
        }
        return Msg.success().add("result",0).add("message","手机号可以使用");
    }

    /**
     * 检查统一社会信用代码是否可用
     * @param creditCode
     * @return
     */
    @GetMapping("checkCreditCode")
    public Msg checkCreditCode(String creditCode) {
        List<EngineeringCompanyEntity> list = engineeringCompanyService.list(new QueryWrapper<EngineeringCompanyEntity>()
                .eq("credit_code_number", creditCode));
        if(list.size() >0 ){
            return Msg.success().add("result",1).add("message","统一社会信用代码已被使用");
        }
        return Msg.success().add("result",0).add("message","统一社会信用代码可以使用");
    }

    /**
     * 检查身份证号是否可用
     * @param idCard
     * @return
     */
    @GetMapping("checkIdCard")
    public Msg checkIdCard(String idCard) {
        List<FinancierEntity> list = financierService.list(new QueryWrapper<FinancierEntity>()
                .eq("id_card", idCard));
        if(list.size() >0 ){
            return Msg.success().add("result",1).add("message","身份证号已被使用");
        }
        return Msg.success().add("result",0).add("message","身份证号可以使用");
    }

    /**
     * 检查银行卡号是否可用
     * @param bankCard
     * @return
     */
    @GetMapping("checkBankCard")
    public Msg checkBankCard(String bankCard) {
        List<BankCardEntity> list = bankCardService.list(new QueryWrapper<BankCardEntity>()
                .eq("card_no", bankCard));
        if(list.size() >0 ){
            return Msg.success().add("result",1).add("message","银行卡号已被使用");
        }
        return Msg.success().add("result",0).add("message","银行卡号可以使用");
    }

    /**
     * 检查资金方企业名称是否可用
     * @param bankFullName
     * @return
     */
    @GetMapping("checkBankFullName")
    public Msg checkBankFullName(String bankFullName) {
        List<FinanceCompanyEntity> list = fundCompanyService.list(new QueryWrapper<FinanceCompanyEntity>()
                .eq("company_full_name", bankFullName));
        if(list.size() >0 ){
            return Msg.success().add("result",1).add("message","企业名称已被使用");
        }
        return Msg.success().add("result",0).add("message","企业名称可以使用");
    }
}
