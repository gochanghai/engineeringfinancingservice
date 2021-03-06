package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.BusinessManagerDao;
import com.shenhua119.leadservice.entity.BusinessManager;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.BusinessManagerService;
import com.shenhua119.leadservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:31
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class BusinessManagerServiceImpl extends ServiceImpl<BusinessManagerDao, BusinessManager> implements BusinessManagerService {

    @Autowired
    private BusinessManagerDao businessManagerDao;
    @Autowired
    private UserService userService;

    @Override
    public List<BusinessManager> selectAll() {
        return businessManagerDao.selectAll();
    }

    @Override
    public List<BusinessManager> listByCompanyId(Long companyId) {
        return businessManagerDao.selectListByComId(companyId);
    }

    /**
     * 保存商务经理并创建账号
     * @param businessManager
     * @return
     */
    @Override
    public boolean saveAndCreateAccount(BusinessManager businessManager) {
        // 给商务经理创建用户账号，初始化密码123456
        User user = new User();
        user.setPhone(businessManager.getPhone())
                .setNickname("BM_"+businessManager.getPhone())
                .setPassword("123456")
                .setUserType(3)
                .setRoleId(3L)
                .setCompanyId(businessManager.getCompanyId());
        boolean b = userService.save(user);
        if(b){
            // 保存商务经理
            businessManager.setUserId(user.getId());
          return save(businessManager);
        }
        return false;
    }
}
