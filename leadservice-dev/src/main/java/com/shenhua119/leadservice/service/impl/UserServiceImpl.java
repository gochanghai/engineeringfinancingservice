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

    @Override
    public boolean updateHeadimgurl(Long id, String headimgurl) {
        User user = userDao.selectById(id);
        // 判断对象是否为空
        if (user != null){
            user.setHeadimgurl(headimgurl);
            userDao.updateById(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateNickname(Long id, String nickname) {
        User user = userDao.selectById(id);
        // 判断对象是否为空
        if (user != null){
            user.setNickname(nickname);
            userDao.updateById(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(Long id, String oldPassword, String newPassword) {

        User user = userDao.selectById(id);
        // 判断对象是否为空
        if (user != null){
            // 比较原始密码是否一致
            System.out.println("修改密码2");
            if (user.getPassword().equals(oldPassword)){
                System.out.println("修改密码3");
                user.setPassword(newPassword);
                updateById(user);
                return true;
            }
            return false;
        }
        return false;
    }

}
