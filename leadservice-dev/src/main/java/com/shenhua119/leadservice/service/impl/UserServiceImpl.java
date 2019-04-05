package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<User> getUserList() {

        return userDao.selectList(null);
    }

    /**
     * 修改用户头像
     * @param id
     * @param headimgurl
     * @return
     */
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

    /**
     * 修改昵称
     * @param id
     * @param nickname
     * @return
     */
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

    /**
     * 使用用户名或手机号码登陆
     * username = username || phone
     * @param username
     * @param password
     * @return
     */
    @Override
    public User loginByUsernameOrPhone(String username, String password) {
        User user = null;
        // 使用用户名登陆
        var where0 = new QueryWrapper<User>().eq("username",username);
        user = getOne(where0);
        if (null != user){
            // 判断密码是否正确
            if (password.equals(user.getPassword())){
                return user;
            }
        }

        // 使用手机号码登陆
        var where1 = new QueryWrapper<User>().eq("phone",username);
        user = getOne(where1);
        if (null != user) {
            // 判断密码是否正确
            if (password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public boolean updatePassword(Long id, String oldPassword, String newPassword) {

        User user = userDao.selectById(id);
        // 判断对象是否为空
        if (user != null){
            // 比较原始密码是否一致
            System.out.println("修改密码2" + user.toString());
            if (user.getPassword().equals(oldPassword)){
                System.out.println("修改密码3");
                user.setPassword(newPassword).setStatus(1);
                updateById(user);
                return true;
            }
            return false;
        }
        return false;
    }

}
