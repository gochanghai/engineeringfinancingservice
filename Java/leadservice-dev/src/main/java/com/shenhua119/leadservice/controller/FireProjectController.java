package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.common.annotation.Log;
import com.shenhua119.leadservice.entity.FireProjectEntity;
import com.shenhua119.leadservice.service.FireProjectService;
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
@RequestMapping("fire-project")
public class FireProjectController {

    @Autowired
    private FireProjectService fireProjectService;

    /**
     * <p>保存数据</p>
     * @param fp
     * @return
     */
    @Log("保存消防工程数据")
    @PostMapping("save")
    public Msg save(FireProjectEntity fp){
        System.out.println(fp);
        fp.setCreateDate(new Date());
        boolean result = fireProjectService.save(fp);
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }



    /**
     * <p>获取数据</p>
     * @return
     */
    @Log("获取消防工程数据")
    @GetMapping("list")
    public Msg list(){
        System.out.println("获取数据");
        // 使用条件构造获取数据
        List<FireProjectEntity> list = fireProjectService.list(null);
        return Msg.success().add("list", list);
    }

    @Log("批量导入消防工程数据")
    @PostMapping(value = "/importExecl")
    public Msg importExecl(@RequestParam("file") MultipartFile file){
        System.out.println("importExcel: ...");
        boolean result = fireProjectService.importXlsOrXlsx(file);
        return Msg.success();
    }
}
