package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.common.annotation.Log;
import com.shenhua119.leadservice.entity.FireMaintenanceProjectEntity;
import com.shenhua119.leadservice.service.FireMaintenanceProjectService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-04 1:51
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("fire-maintenance-project")
public class FireMaintenanceProjectController {

    /** 维保项目业务逻辑接口 */
    @Autowired
    private FireMaintenanceProjectService fireMaintenanceProjectService;


    /**
     * 保存数据
     * @param fmp
     * @return
     */
    @Log("保存项目数据")
    @PostMapping("save")
    public Msg save(FireMaintenanceProjectEntity fmp){
        System.out.println(fmp);
        fmp.setCreateDate(new Date());
        boolean result = fireMaintenanceProjectService.save(fmp);
        if (result){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    /**
     * 获取数据
     * @return
     */
    @Log("获取项目数据")
    @GetMapping("list")
    public Msg list(){
        System.out.println("获取数据");
        // 使用条件构造获取数据
        List<FireMaintenanceProjectEntity> list = fireMaintenanceProjectService.list(null);
        return Msg.success().add("list", list);
    }

    /**
     * 批量导入项目数据
     * @param file
     * @return
     */
    @Log("批量导入维保项目数据")
    @PostMapping(value = "/importExecl")
    public Msg importExecl(@RequestParam("file") MultipartFile file){
        System.out.println("importExcel: ...");
        boolean result = fireMaintenanceProjectService.importXlsOrXlsx(file);
        return Msg.success();
    }
}
