package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.FinanceProduct;

import java.util.List;
import java.util.Map;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:28
 */
public interface FinanceProductService extends IService<FinanceProduct> {

    List<Map> selectAll();
}
