package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.BusinessManager;
import com.shenhua119.leadservice.service.BusinessManagerService;
import com.shenhua119.leadservice.service.FinancierAuthenService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商务经理访问控制器
 * @author liuchanghai
 * @create 2018-11-28 22:28
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/business")
public class BusinessManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BusinessManagerService businessManagerService;
    @Autowired
    private UserService userService;
    @Autowired
    private FinancierAuthenService financierAuthenService;

    /**
     * 获取一个商务经理的信息
     * @param id
     * @return
     */
    @GetMapping("get")
    public Msg getById(Long id){
        logger.info("get: " + id);
        BusinessManager financier = businessManagerService.getById(id);
        logger.info("获取成功： " + financier.toString());
        return  Msg.success().add("userInfo",financier);
    }

    /**
     * 保存商务经理信息
     * @param business
     * @return
     */
    @PostMapping("/")
    public Msg save(BusinessManager business){
        System.out.println(business.toString());
        boolean b = businessManagerService.saveAndCreateAccount(business);
        if (b){
            return Msg.success();
        }
        return  Msg.fail();
    }

    /**
     * 获取所有的商务经理
     * @return
     */
    @GetMapping("all")
    public Msg list(){
        List<BusinessManager> list = businessManagerService.selectAll();
        System.out.println("获取成功： " + list.size());
        return  Msg.success().add("list",list);
    }

    /**
     * 获取工程公司的商务经理
     * @param companyId
     * @return
     */
    @GetMapping("list")
    public Msg list(Long companyId){
        System.out.println("list: " + companyId);
        List<BusinessManager> list = businessManagerService.listByCompanyId(companyId);
        System.out.println("获取成功： " + list.size());
        return  Msg.success().add("list",list);
    }

    /**
     * 获取商务经理姓名及身份证号
     * @param userId
     * @return
     */
    @GetMapping("user_id")
    public Msg getNameAndIdCard(Long userId){
        System.out.println("获取商务经理姓名及身份证号: " + userId);
        var where = new QueryWrapper<BusinessManager>().eq("user_id", userId);
        BusinessManager manager = businessManagerService.getOne(where);
        System.out.println("获取商务经理姓名及身份证号获取成功： " + manager);
        return  Msg.success().add("name",manager.getName()).add("idCard",manager.getIdCard());
    }
}
