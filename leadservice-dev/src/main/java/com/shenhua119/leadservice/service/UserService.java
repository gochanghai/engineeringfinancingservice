package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    public List<User> getUserList();

}
