package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.UserDao;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public List<User> getUserList() {

        return userDao.selectList(null);
    }

}
