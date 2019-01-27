package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.LoanEntity;
import com.shenhua119.leadservice.service.LoanService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuchanghai
 * @create 2018-12-09 0:27
 */
@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("li")
public class LoanInfoController {

    //
    @Autowired
    private LoanService loanInfoService;

    // 保存数据
    @PostMapping("save")
    public Msg save(LoanEntity loanEntity){
        System.out.println();
        boolean result = loanInfoService.save(loanEntity);
        return Msg.success();
    }
}
