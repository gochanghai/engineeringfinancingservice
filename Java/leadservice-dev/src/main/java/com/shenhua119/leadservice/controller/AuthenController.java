package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.BankCardEntity;
import com.shenhua119.leadservice.entity.FinancierAuthenEntity;
import com.shenhua119.leadservice.entity.FinancierCarEntity;
import com.shenhua119.leadservice.entity.FinancierHouseEntity;
import com.shenhua119.leadservice.service.BankCardService;
import com.shenhua119.leadservice.service.FinancierAuthenService;
import com.shenhua119.leadservice.service.FinancierCarInfoService;
import com.shenhua119.leadservice.service.FinancierHouseInfoService;
import com.shenhua119.leadservice.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private FinancierAuthenService financierAuthenService;
    @Autowired
    private FinancierHouseInfoService financierHouseInfoService;
    @Autowired
    private FinancierCarInfoService financierCarInfoService;
    @Autowired
    private BankCardService bankCardService;
    // 保存数据
    @PostMapping("bankCard/save")
    public Msg saveBackCardInfo(BankCardEntity bankCard){
        System.out.println("save BankCardInfo" + bankCard.toString());
        boolean result = bankCardService.save(bankCard);
        return Msg.success();
    }

    // 保存数据
    @PostMapping("save")
    public Msg save(FinancierAuthenEntity financier, BankCardEntity bankCard){
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
    public Msg saveCar(FinancierCarEntity car){
        logger.info(car.toString());
        System.out.println("save car " + car.toString());
        boolean result = financierCarInfoService.saveOrUpdate(car);
        return Msg.success();
    }

    // 保存数据
    @PostMapping("save/house")
    public Msg saveHouse(FinancierHouseEntity house){
        logger.info(house.toString());
        System.out.println("save house" + house.toString());
//        boolean result = financierHouseInfoService.saveOrUpdate(house);
        return Msg.success();
    }

}
