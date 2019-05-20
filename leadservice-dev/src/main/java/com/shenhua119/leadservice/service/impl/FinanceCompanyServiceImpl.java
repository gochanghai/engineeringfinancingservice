package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.FinanceCompanyDao;
import com.shenhua119.leadservice.entity.FinanceCompany;
import com.shenhua119.leadservice.entity.User;
import com.shenhua119.leadservice.service.FinanceCompanyService;
import com.shenhua119.leadservice.service.UserService;
import com.shenhua119.leadservice.vo.FinanceCustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-07 1:45
 */

@Service
public class FinanceCompanyServiceImpl extends ServiceImpl<FinanceCompanyDao, FinanceCompany> implements FinanceCompanyService {

    @Autowired
    private FinanceCompanyDao financeCompanyDao;
    /**
     * 用户业务逻辑接口
     */
    @Autowired
    private UserService userService;

    @Override
    public List<FinanceCompany> listCompanyNameAndComId() {
        return financeCompanyDao.selectListCompanyNameAndComId();
    }


    /**
     * 配置统一事务 抛出了异常，就会导致事务回滚
     * @param finance
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean saveAndCreateAccount(FinanceCompany finance) {
        User user = new User();
        user.setUsername(finance.getUsername())
            .setPhone(finance.getPhone())
            .setNickname("FC_" + finance.getUsername())
            .setPassword("123456")
            .setUserType(1)
            .setRoleId(2L);
        boolean res = userService.save(user);
        if(res){
            finance.setUserId(user.getId());
            return save(finance);
        }
        return false;
    }

    @Override
    public List<FinanceCustomerVO> listCustomeByCompanyId(Long id) {
        return financeCompanyDao.selectCustomerList(id);
    }
}
