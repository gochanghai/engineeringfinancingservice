package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.FinanceProduct;
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
@RequestMapping("api/product")
public class FinanceProductController {

    @Autowired
    private FinanceProductService financeProductService;


    /**
     * 获取一个产品的信息
     * @param id
     * @return
     */
    @GetMapping("id")
    public Msg getProductById(Long id) {
        System.out.println("获取一个产品的信息：" + id);
        FinanceProduct product = financeProductService.getById(id);
        return Msg.success().add("product", product);
    }
    /**
     * 保存产品
     * @param product
     * @return
     */
    @PostMapping("")
    public Msg save(FinanceProduct product) {
        boolean result = financeProductService.save(product);
        System.out.println("新增金融产品成功");
        return Msg.success();
    }

    /**
     * 获取产品列表
     * @return
     */
    @GetMapping("list")
    public Msg list() {
        System.out.println("获取数据");
        List<Map> list = financeProductService.selectAll();
        return Msg.success().add("list", list);
    }

}