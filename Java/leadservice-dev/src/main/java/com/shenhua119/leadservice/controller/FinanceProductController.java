package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.FinanceProductEntity;
import com.shenhua119.leadservice.service.FinanceProductService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author liuchanghai
 * @create 2018-12-04 1:51
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("fp")
public class FinanceProductController {

    @Autowired
    private FinanceProductService financeProductService;

    // 保存数据
    @PostMapping("save")
    public Msg save(FinanceProductEntity fp) {
        boolean result = financeProductService.save(fp);
        System.out.println("新增金融产品成功");
        return Msg.success();
    }

    // 获取数据
    @GetMapping("list")
    public Msg list() {
        System.out.println("获取数据");
        List<Map> list = financeProductService.selectAll();
        return Msg.success().add("list", list);
    }

}