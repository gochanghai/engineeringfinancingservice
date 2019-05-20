package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.EngineeringCompanyDao;
import com.shenhua119.leadservice.entity.CompanyUser;
import com.shenhua119.leadservice.entity.EngineeringCompany;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.CompanyUserService;
import com.shenhua119.leadservice.service.EngineeringCompanyService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.vo.CompanyInfoVO;
import com.shenhua119.leadservice.vo.CompanyListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置事务 抛出了异常，就会导致事务回滚
 * @author liuchanghai
 * @create 2018-12-06 18:01
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class EngineeringCompanyServiceImpl extends ServiceImpl<EngineeringCompanyDao, EngineeringCompany> implements EngineeringCompanyService {

    @Autowired
    private EngineeringCompanyDao engineeringCompanyDao;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyUserService companyUserService;

    @Override
    public List<CompanyListVO> listAll() {
        return engineeringCompanyDao.selectAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public EngineeringCompany getEngineeringCompanyById(Long id) {
        return engineeringCompanyDao.getById(id);
    }

    /**
     * 保存创建工程公司并且创建用户
     * @param engineering
     * @return
     */
    @Override
    public boolean saveAndCreateAccount(EngineeringCompany engineering) {
        // 保存工程公司
        boolean save = save(engineering);
        User user = null;
        CompanyUser companyUser = null;
        if (save){
            // 创建审批员账号
           user = new User();
           user.setUserType(1)
                   .setUsername(engineering.getUsername1())
                   .setPhone(engineering.getPhone1())
                   .setNickname("EC_" + engineering.getUsername1())
                   .setPassword("123456")
                   .setRoleId(1L);
            boolean save1 = userService.save(user);

            // 建立用户和用户的关系
            companyUser = new CompanyUser();
            companyUser.setUserId(user.getId()).setCompanyId(engineering.getId());
            companyUserService.save(companyUser);

            // 创建资料员账号
            user = new User();
            user.setUserType(2)
                    .setUsername(engineering.getUsername2())
                    .setPhone(engineering.getPhone2())
                    .setNickname("EC_" + engineering.getUsername2())
                    .setPassword("123456")
                    .setRoleId(1L);
            boolean save2 = userService.save(user);

            // 建立用户和用户的关系
            companyUser = new CompanyUser();
            companyUser.setUserId(user.getId()).setCompanyId(engineering.getId());
            companyUserService.save(companyUser);

            return true;
        }


        return false;
    }

    @Override
    public CompanyInfoVO getCompanyInfoById(Long id) {
        CompanyInfoVO companyInfoVO = engineeringCompanyDao.selectInfoById(id);
        return companyInfoVO;
    }
}
