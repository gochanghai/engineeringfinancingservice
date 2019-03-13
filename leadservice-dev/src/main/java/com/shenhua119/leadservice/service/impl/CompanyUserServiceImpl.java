package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.CompanyUserDao;
import com.shenhua119.leadservice.entity.CompanyUser;
import com.shenhua119.leadservice.service.CompanyUserService;
import org.springframework.stereotype.Service;

@Service
public class CompanyUserServiceImpl extends ServiceImpl<CompanyUserDao, CompanyUser> implements CompanyUserService {
}
