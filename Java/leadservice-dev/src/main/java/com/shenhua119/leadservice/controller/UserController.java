package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.FinancierHouseEntity;
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

    // 获取工程公司的用户
    @GetMapping("list")
    public Msg getCompanyUserById(Long id){
        System.out.println("用户" + id + " 获取账号");
        List<User> list = userService.list(new QueryWrapper<User>().eq("user_info_id", id));
        return Msg.success().add("list", list);
    }

    // 获取平台的用户
    @GetMapping("users")
    public Msg getUserListByRoleId(Long id){
        System.out.println("用户" + id + " 获取账号");
        List<User> list = userService.list(new QueryWrapper<User>().eq("role_id", id));
        return Msg.success().add("list", list);
    }


    // 使用post请求访问
    @PostMapping("/login")
    public Msg login(User user) {
        System.out.println("login");
        System.out.println("vue post test" + user.getUserName() + user.getPassword() + user.getUserId());
        return Msg.success().add("userInfo", "融资人，ID=100");
    }

    // 使用post请求访问
    @PostMapping("list2")
    public String login(@RequestBody List<FinancierHouseEntity> houses) {
        System.out.println(" vue post list");
        System.out.println("vue post size(): " + houses.size());
        return "vuepost";
    }

    @GetMapping("/vueget")
    public String getVueTest(User user){
        System.out.println("vue get test" + user.getUserName() + user.getPassword() + user.getUserId());
        System.out.println("vue get test");
        return "vueget";
    }
    @PutMapping("/vueput")
    public String putVueTest(User user){
        System.out.println("vue put test" + user.getUserName() + user.getPassword() + user.getUserId());
        return "vueput";
    }
    @DeleteMapping("/vuedelete")
    public String deleteVueTest(User user){
        System.out.println("vue delete test" + user.getUserName() + user.getPassword() + user.getUserId());
        System.out.println("vue delete test");
        return "vuedelete";
    }

    // 使用get请求访问
    @GetMapping("list2")
    public List<User> getUser() {
        System.out.println("12" +
                "45666");
        List<User> list = userService.getUserList();
        System.out.println("lis size； " + list.size() );
        return list;
    }
}
