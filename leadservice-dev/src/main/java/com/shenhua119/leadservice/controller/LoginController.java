package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.common.annotation.Log;
import com.shenhua119.leadservice.entity.FinancierEntity;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.EngineeringCompanyService;
import com.shenhua119.leadservice.service.FinancierService;
import com.shenhua119.leadservice.service.FinanceCompanyService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuchanghai
 * @create 2018-12-14 0:17
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api")
public class LoginController {

    @Value("${fastdfs-path.server_ip}")
    private String fileBasePath;

    @Autowired
    private UserService userService;
    @Autowired
    private FinancierService financierService;
    @Autowired
    private EngineeringCompanyService engineeringCompanyService;
    @Autowired
    private FinanceCompanyService fundCompanyService;

    @Log("用户登录")
    @PostMapping("login")
    public Msg login(String userName, String password){

        System.out.println(userName + "用户登录");

        User user1= userService.getOne(new QueryWrapper<User>().eq("user_name",userName));
        User user2 = userService.getOne(new QueryWrapper<User>().eq("phone",userName));
        // 判断是否是用户名登录
        if ( user1 != null){
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)){
                System.out.println("用户名登录：" + userName);
                System.out.println("roleId: " + user1.getRoleId());
                if ( user1.getRoleId() == 1 ){

                    return Msg.success().add("userInfo",user1).add("fileBasePath",fileBasePath);
                }
                if ( user1.getRoleId() == 2 ){

                    return Msg.success().add("userInfo",user1).add("fileBasePath",fileBasePath);
                }
                if ( user1.getRoleId() == 3 ){
                    FinancierEntity financierEntity = financierService.getById(user1.getUserInfoId());
                    return Msg.success().add("userInfo",user1).add("userInfo2", financierEntity)
                            .add("fileBasePath",fileBasePath);
                }
                return Msg.success().add("userInfo",user1).add("fileBasePath",fileBasePath);
            }
        }

        // 判断是否是用手机登录
        if ( user2 != null){
            if (user2.getPhone().equals(userName) && user2.getPassword().equals(password)){
                System.out.println("手机登录： " + userName);
                System.out.println("roleId: " + user2.getRoleId());
                if (user2.getRoleId() == 3){
                    FinancierEntity financierEntity = financierService.getById(user2.getUserInfoId());
                    return Msg.success().add("userInfo",user2).add("userInfo2", financierEntity)
                            .add("fileBasePath",fileBasePath);
                }
                return Msg.success().add("userInfo",user2).add("fileBasePath",fileBasePath);
            }
        }

        return Msg.fail().add("result", "用户名或密码不正确");
    }

}
