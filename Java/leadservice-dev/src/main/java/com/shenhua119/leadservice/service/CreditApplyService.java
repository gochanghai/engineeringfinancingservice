package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.CreditApplyEntity;
import com.shenhua119.leadservice.entity.CreditListView;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:11
 */
public interface CreditApplyService extends IService<CreditApplyEntity> {

    List<CreditListView> selectByFinancierId(Long id);

    List<CreditListView> listByCompanyId(Long id);

    List<CreditListView> listAll();

    List<CreditListView> listByFundCompanyId(Long id);
}
