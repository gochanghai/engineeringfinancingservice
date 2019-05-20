package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    public List<User> getUserList();

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * 修改头像
     * @param id
     * @param headimgurl
     * @return
     */
    boolean updateHeadimgurl(Long id, String headimgurl);

    /**
     * 修改昵称
     * @param id
     * @param nickname
     * @return
     */
    boolean updateNickname(Long id, String nickname);

    /**
     * 使用用户名或手机号码登陆
     * username = username || phone
     * @param username
     * @param password
     * @return
     */
    User loginByUsernameOrPhone(String username, String password);

    List<User> findCompanyUsers(Long id);
}
