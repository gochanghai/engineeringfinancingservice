package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.BusinessManagerAuthen;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:28
 */
@Mapper
public interface FinancierAuthenDao extends BaseMapper<BusinessManagerAuthen> {

    BusinessManagerAuthen selectAuthDetailByUserId(Long userId);

}
