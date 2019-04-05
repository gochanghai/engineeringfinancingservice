package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.common.annotation.Log;
import com.shenhua119.leadservice.entity.CompanyUser;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.CompanyUserService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private UserService userService;
    @Autowired
    private CompanyUserService companyUserService;

    @Log("用户登录")
    @PostMapping("login")
    public Msg login(String username, String password) {
        System.out.println(username + "用户登录");
        User user = null;
        CompanyUser companyUser = null;
        Msg msg = new Msg();
        // 使用用户名登陆
        var where0 = new QueryWrapper<User>().eq("username", username);
        user = userService.getOne(where0);
        if (null != user) {
            // 判断密码是否正确
            if (!password.equals(user.getPassword())) {
                return Msg.fail().add("message", "密码错误");
            }
            return Msg.success().add("userId", user.getId());
        }else {
            // 使用手机号码登陆
            var where1 = new QueryWrapper<User>().eq("phone", username);
            user = userService.getOne(where1);
            if (null != user) {
                // 判断密码是否正确
                if (!password.equals(user.getPassword())) {
                    return Msg.fail().add("message", "密码错误");
                }
                return Msg.success().add("userId", user.getId());
            }
        }
        return Msg.fail().add("message", "用户名不存在");
    }

}
