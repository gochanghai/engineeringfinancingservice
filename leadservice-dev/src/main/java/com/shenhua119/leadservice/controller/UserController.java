package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取工程公司的用户
     * @param id
     * @return
     */
    @GetMapping("list")
    public Msg getCompanyUserById(Long id){
        System.out.println("用户" + id + " 获取账号");
        List<User> list = userService.list(new QueryWrapper<User>().eq("user_info_id", id));
        return Msg.success().add("list", list);
    }

    /**
     * 获取平台的用户
     * @param id
     * @return
     */
    @GetMapping("users")
    public Msg getUserListByRoleId(Long id){
        System.out.println("用户" + id + " 获取账号");
        List<User> list = userService.list(new QueryWrapper<User>().eq("role_id", id));
        return Msg.success().add("list", list);
    }

    /**
     * 修改头像
     * @param id
     * @param headimgur
     * @return
     */
    @PutMapping("change_headimgurl")
    public Msg changeHeadimgurl(Long id, String headimgur){
        boolean result = userService.updateHeadimgurl(id,headimgur);
        // 判断是否修改成功
        if (result){
            return Msg.success();
        }
        return Msg.fail();

    }

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PutMapping("change_password")
    public Msg changePassword(Long id, String oldPassword, String newPassword){
        System.out.println("用户修改密码1");
        boolean result = userService.updatePassword(id,oldPassword,newPassword);
        // 判断密码是否修改成功
        if (result){
            return Msg.success();
        }
        return Msg.fail();

    }


}
