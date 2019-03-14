package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.BranchCompany;
import com.shenhua119.leadservice.service.BranchCompanyService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分公司访问控制器
 * @author liuchanghai
 * @create 2018-12-04 1:51
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/company")
public class BranchCompanyController {

    @Autowired
    private BranchCompanyService branchCompanyService;

    /**
     * 添加新的分公司
     * @param branch
     * @return
     */
    @PostMapping("branch")
    public Msg save(BranchCompany branch){
        System.out.println(branch.toString());
        boolean result = branchCompanyService.save(branch);
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 获取我的分公司
     * @param companyId
     * @return
     */
    @GetMapping("branch/list")
    public Msg myBranchCompany(Long companyId){
        System.out.println("获取数据");
        // 根据总公司ID获取我的分公司
        var where = new QueryWrapper<BranchCompany>().eq("p_id",companyId);
        List<BranchCompany> list = branchCompanyService.list(where);
        return Msg.success().add("list", list);
    }

    /**
     * 获取一个分公司的信息
     * @param id
     * @return
     */
    @GetMapping("branch")
    public Msg getOne(Long id){
        System.out.println("获取数据");
        BranchCompany branchCompany = branchCompanyService.getById(id);
        return Msg.success().add("branchCompany", branchCompany);
    }

    /**
     * 更新一个分公司的信息
     * @param branch
     * @return
     */
    @PutMapping("branch")
    public Msg updateBranchCompany(BranchCompany branch){
        System.out.println("获取数据");
        boolean b = branchCompanyService.updateById(branch);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }


    /**
     * 删除一个分公司的信息
     * @param id
     * @return
     */
    @DeleteMapping("branch")
    public Msg delete(Long id){
        System.out.println("获取数据");
        boolean b = branchCompanyService.removeById(id);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }
}
