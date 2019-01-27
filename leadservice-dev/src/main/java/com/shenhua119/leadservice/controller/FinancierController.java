package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.FinancierAuthenEntity;
import com.shenhua119.leadservice.entity.FinancierEntity;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.FinancierAuthenService;
import com.shenhua119.leadservice.service.FinancierService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-11-28 22:28
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("financier")
public class FinancierController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinancierService financierService;
    @Autowired
    private UserService userService;
    @Autowired
    private FinancierAuthenService financierAuthenService;

    @GetMapping("get")
    public Msg getById(Long id){
        logger.info("get: " + id);
        FinancierEntity financier = financierService.getById(id);
        logger.info("获取成功： " + financier.toString());
        return  Msg.success().add("userInfo",financier);
    }

    //
    @PostMapping("save")
    public Msg saveFinancier(FinancierEntity financier){
        System.out.println(financier.getName() + financier.getIdCard());
        boolean result = financierService.save(financier);

        // 判断手机号码是否存在
        if (financier.getPhone() != null){
            financier = financierService.getOne(new QueryWrapper<FinancierEntity>().eq("phone", financier.getPhone()));
            User user = new User();
            user.setPhone(financier.getPhone()).setPassword("123456").setUserType(1)
                    .setRoleId(3L).setUserInfoId(financier.getId());
            boolean result2 = userService.save(user);
            FinancierAuthenEntity authen = new FinancierAuthenEntity();
            authen.setId(financier.getId()).setFId(financier.getId());
            financierAuthenService.save(authen);
        }
        System.out.println("保存成功： " + result);
        return  Msg.success().add("financier", financier);
    }

    //
    @GetMapping("all")
    public Msg list(){
        List<FinancierEntity> list = financierService.selectAll();
        System.out.println("获取成功： " + list.size());
        return  Msg.success().add("list",list);
    }

    @GetMapping("list")
    public Msg list(Long id){
        System.out.println("list: " + id);
        List<FinancierEntity> list = financierService.listByCompanyId(id);
        System.out.println("获取成功： " + list.size());
        return  Msg.success().add("list",list);
    }
}
