package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.common.annotation.Log;
import com.shenhua119.leadservice.entity.BusinessManager;
import com.shenhua119.leadservice.entity.CompanyUser;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.*;
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
    private BusinessManagerService businessManagerService;
    @Autowired
    private CompanyUserService companyUserService;
    @Autowired
    private FinanceCompanyService fundCompanyService;

    @Log("用户登录")
    @PostMapping("login")
    public Msg login(String username, String password){

        System.out.println(username + "用户登录");
        /**
         * 使用用户名登陆
         */
        User user1= userService.getOne(new QueryWrapper<User>().eq("username",username));
        // 判断用户名是否存在
        if ( user1 != null){
            if (user1.getUsername().equals(username) && user1.getPassword().equals(password)){
                System.out.println("用户名登录：" + username);
                System.out.println("roleId: " + user1.getRoleId());
                // 用户角色判断
                if ( user1.getRoleId() == 1 ){
                    CompanyUser companyUser = companyUserService.getOne(new QueryWrapper<CompanyUser>().eq("user_id", user1.getId()));
                    return Msg.success().add("userInfo",user1)
                            .add("companyId",companyUser.getCompanyId())
                            .add("fileBasePath",fileBasePath);
                }
                if ( user1.getRoleId() == 2 ){
                    return Msg.success().add("userInfo",user1).add("fileBasePath",fileBasePath);
                }
                if ( user1.getRoleId() == 3 ){

                    BusinessManager financier = businessManagerService.getById(user1.getUserInfoId());

                    return Msg.success().add("userInfo",user1)
                                        .add("nickname", financier.getName())
                                        .add("realname", financier.getName())
                                        .add("fileBasePath",fileBasePath);
                }
                return Msg.success().add("userInfo",user1).add("fileBasePath",fileBasePath);
            }
            return Msg.fail().add("message","密码错误");
        }

        /**
         * 使用手机号登陆
         */
        User user2 = userService.getOne(new QueryWrapper<User>().eq("phone",username));
        // 判断用户是否存在
        if ( user2 != null){
            if (user2.getPhone().equals(username) && user2.getPassword().equals(password)){
                System.out.println("手机登录： " + username);
                System.out.println("roleId: " + user2.getRoleId());
                if (user2.getRoleId() == 3){
                    BusinessManager businessManager = businessManagerService.getById(user2.getUserInfoId());
                    return Msg.success().add("userInfo",user2).add("userInfo2", businessManager)
                            .add("fileBasePath",fileBasePath);
                }
                return Msg.success().add("userInfo",user2).add("fileBasePath",fileBasePath);
            }
            return Msg.fail().add("message","密码错误");
        }
        return Msg.fail().add("message", "用户名不存在");
    }

}
