package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.*;
import com.shenhua119.leadservice.service.*;
import com.shenhua119.leadservice.util.HttpUtils;
import com.shenhua119.leadservice.util.Msg;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BusinessManagerService businessManagerService;
    @Autowired
    private FinancierAuthenService financierAuthenService;
    @Autowired
    private FinancierHouseInfoService financierHouseInfoService;
    @Autowired
    private FinancierCarInfoService financierCarInfoService;
    @Autowired
    private BankCardService bankCardService;

    /**
     * 获取认证信息包括车辆和房子信息
     * @param id
     * @return
     */
    @GetMapping("bm_info")
    public Msg getBusinessManagerInfo(Long id){
        System.out.println("bm_info: " + id);
        BusinessManager regInfo = businessManagerService.getById(id);
        BusinessManagerAuthen authenInfo = financierAuthenService.getById(id);
        List<Car> cars = financierCarInfoService.list(new QueryWrapper<Car>().eq("user_id", id));
        List<House> houses = financierHouseInfoService.list(new QueryWrapper<House>().eq("user_id", id));
        return Msg.success()
                .add("reginfo",regInfo)
                .add("authenInfo",authenInfo)
                .add("cars",cars)
                .add("houses",houses);
    }

    /**
     * 保存银行卡信息
     * @param bankCard
     * @return
     */
    @PostMapping("bankCard/save")
    public Msg saveBackCardInfo(BankCard bankCard){
        System.out.println("save BankCardInfo" + bankCard.toString());

        String host = "https://b4bankcard.market.alicloudapi.com";
        String path = "/bank4Check";
        String method = "GET";
        String appcode = "ede2552d6dc848a2b893dbef1d2ac482";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("accountNo", bankCard.getAccountNo());
        querys.put("idCard", bankCard.getIdCard());
        querys.put("mobile", bankCard.getPhone());
        querys.put("name", bankCard.getRealName());

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 或者直接下载：
             * http://code.fegine.com/HttpUtils.zip
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             * 相关jar包（非pom）直接下载：
             * http://code.fegine.com/aliyun-jar.zip
             */
            HttpResponse res = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            String body = EntityUtils.toString(res.getEntity());
            System.out.println(body);
            String resStr = body.substring( 1, body.length()-1);
            System.out.println(resStr);
            String[] strings = resStr.split(",");
            Map<String, String> map = new HashMap<String, String>();
            for (String item: strings) {
                String[] ss = item.split(":");
                map.put(ss[0].substring(1,ss[0].length() - 1),ss[1].substring(1,ss[1].length() - 1));
            }
            if("01".equals(map.get("status"))){
                bankCard.setBank(map.get("bank"));
                bankCard.setCardName(map.get("cardName"));
                bankCard.setCardType(map.get("cardType"));
                bankCard.setProvince(map.get("province"));
                bankCard.setCity(map.get("city"));
                bankCard.setArea(map.get("area"));
                bankCard.setPrefecture(map.get("prefecture"));
                bankCard.setBirthday(map.get("birthday"));
                bankCard.setGender(map.get("sex"));
                bankCard.setAddrCode(map.get("addrCode"));
                bankCard.setLastCode(map.get("lastCode"));
                boolean b = bankCardService.save(bankCard);
                if(b){
                    return Msg.success().add("message","验证通过");
                }
            }
            if ("02".equals(map.get("status"))){
                return Msg.success().add("message","验证不通过【身份证或姓名手机与卡号不符】");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    /**
     * 保存认证信息
     * @param business
     * @param bankCard
     * @return
     */
    @PostMapping("save")
    public Msg save(BusinessManagerAuthen business, BankCard bankCard){
        System.out.println("save financierBaseInfo: " + business.toString());
        System.out.println("save bankCard: " + bankCard.toString());
        logger.info(business.toString());
        logger.info(bankCard.toString());
        bankCard.setUserId(business.getId()).setPhone(business.getBankCardPhone()).setAccountNo(business.getBankCardNo());
        business.setStatus(2);
        boolean res = financierAuthenService.save(business);
        if(res){
            BusinessManager bm = new BusinessManager();
            bm.setId(business.getUserId()).setStatus(1);
            res = businessManagerService.updateById(bm);
        }
        bankCardService.save(bankCard);
        if(res){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 保存车辆信息
     * @param car
     * @return
     */
    @PostMapping("save/car")
    public Msg saveCar(Car car){
        logger.info(car.toString());
        System.out.println("save car " + car.toString());
        boolean res = financierCarInfoService.save(car);
        if(res){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 保存房子信息
     * @param house
     * @return
     */
    @PostMapping("save/house")
    public Msg saveHouse(House house){
        logger.info(house.toString());
        System.out.println("save house" + house.toString());
        boolean res = financierHouseInfoService.save(house);
        if(res){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     *  获取用户的认证状态
     * @param userId
     * @return
     */
    @GetMapping("status")
    public Msg getUserAuthen(Long userId){
        System.out.println("获取用户的认证状态: " + userId);
        var where = new QueryWrapper<BusinessManagerAuthen>().eq("user_id",userId);
        BusinessManagerAuthen authen = financierAuthenService.getOne(where);
        if(null != authen){
            if(1 == authen.getStatus()){
                return Msg.success().add("name", "已认证").add("status",1);
            }

            if(2 == authen.getStatus()){
                return Msg.success().add("name", "认证审核中").add("status",2);
            }

            if(3 == authen.getStatus()){
                return Msg.success().add("name", "认证审核未通过").add("status",3);
            }
        }
        return Msg.success().add("name", "未认证").add("status",0);
    }

}
