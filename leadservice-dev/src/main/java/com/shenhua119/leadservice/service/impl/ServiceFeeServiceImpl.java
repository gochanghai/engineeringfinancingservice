package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.ServiceFeeDao;
import com.shenhua119.leadservice.entity.ServiceFee;
import com.shenhua119.leadservice.service.ServiceFeeService;
import org.springframework.stereotype.Service;

@Service
public class ServiceFeeServiceImpl extends ServiceImpl<ServiceFeeDao, ServiceFee> implements ServiceFeeService {
}
