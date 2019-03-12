package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.*;
import com.shenhua119.leadservice.service.*;
import com.shenhua119.leadservice.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-04 2:25
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/authen")
public class AuthenController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinancierService financierService;
    @Autowired
    private FinancierAuthenService financierAuthenService;
    @Autowired
    private FinancierHouseInfoService financierHouseInfoService;
    @Autowired
    private FinancierCarInfoService financierCarInfoService;
    @Autowired
    private BankCardService bankCardService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("bm_info_details")
    public Msg getBusinessManagerInfo(Long id){
        System.out.println("bm_info_details: " + id);
        BusinessManager financier = financierService.getById(id);
        BusinessManagerAuthen authen = financierAuthenService.getById(id);
        List<Car> cars = financierCarInfoService.list(new QueryWrapper<Car>().eq("f_id", id));
        List<House> houses = financierHouseInfoService.list(new QueryWrapper<House>().eq("f_id", id));
        return Msg.success()
                .add("baseinfo",financier)
                .add("authenInfo",authen)
                .add("cars",cars)
                .add("houses",houses);
    }
    // 保存数据
    @PostMapping("bankCard/save")
    public Msg saveBackCardInfo(BankCard bankCard){
        System.out.println("save BankCardInfo" + bankCard.toString());
        boolean result = bankCardService.save(bankCard);
        return Msg.success();
    }

    // 保存数据
    @PostMapping("save")
    public Msg save(BusinessManagerAuthen financier, BankCard bankCard){
        System.out.println("save financierBaseInfo: " + financier.toString());
        System.out.println("save bankCard: " + bankCard.toString());
        logger.info(financier.toString());
        logger.info(bankCard.toString());
        bankCard.setUserId(financier.getId()).setMobile(financier.getBankCardPhone()).setCardNo(financier.getBankCardNo());
        boolean result = financierAuthenService.updateById(financier);
        bankCardService.updateById(bankCard);
        return Msg.success();
    }

    // 保存数据
    @PostMapping("save/car")
    public Msg saveCar(Car car){
        logger.info(car.toString());
        System.out.println("save car " + car.toString());
        boolean result = financierCarInfoService.saveOrUpdate(car);
        return Msg.success();
    }

    // 保存数据
    @PostMapping("save/house")
    public Msg saveHouse(House house){
        logger.info(house.toString());
        System.out.println("save house" + house.toString());
        boolean result = financierHouseInfoService.saveOrUpdate(house);
        return Msg.success();
    }

}
