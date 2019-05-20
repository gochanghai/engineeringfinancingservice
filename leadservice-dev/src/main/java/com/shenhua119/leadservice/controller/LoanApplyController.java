package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.*;
import com.shenhua119.leadservice.service.*;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 放款申请前端控制器
 * @author liuchanghai
 * @create 2018-12-04 1:51
 */

@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("la")
public class LoanApplyController {

    @Autowired
    private LoanApplyService loanApplyInfoService;
    @Autowired
    private ProjectPurchaseOrderService projectPurchaseOrderService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BusinessManagerService businessManagerService;
    @Autowired
    private LoanApprovalService loanApprovalService;
    @Autowired
    private MessageService messageService;

    /**
     * 保存申请数据
     * @param applyInfo
     * @return
     */
    @PostMapping("save")
    public Msg save(ProjectLoanApply applyInfo){
        System.out.println(applyInfo.toString());
        // 根据项目ID 得到项目信息，从而得到工程公司ID
        Project project = projectService.getById(applyInfo.getProjectId());
        // 设置工程公司ID
        applyInfo.setCompanyId(project.getCompanyId());
        boolean result = loanApplyInfoService.save(applyInfo);
        System.out.println("放款申请信息保存成功: " + result);
        LoanExamineapprove approval = new LoanExamineapprove();
        approval.setId(applyInfo.getId()).setApplyId(applyInfo.getId());
        boolean b1 = loanApprovalService.save(approval);
        if (result){
            String content = "亲爱的用户，融资人"+applyInfo.getName()+"请放款，待您放款审批，请您及时处理，便于后期业务的开展！";
            boolean b = messageService.productionMessage(1, "待您放款审批通知", content, project.getCompanyId());
        }
        return Msg.success();
    }

    /**
     * 保存采购订单数据
     * @param purchaseOrder
     * @return
     */
    @PostMapping("purchase/save")
    public Msg savePurchase(ProjectPurchaseOrder purchaseOrder){
        System.out.println(purchaseOrder.toString());
        boolean result = projectPurchaseOrderService.save(purchaseOrder);
        System.out.println("放款采购信息保存成功: " + result);
        return Msg.success();
    }

    /**
     * 获取融资人数据
     * @param id
     * @return
     */
    @GetMapping("f/list")
    public Msg listByFinancierId(Long id){
        System.out.println("融资人："+ id +" 获取数据");
        List<ProjectLoanApply> list = loanApplyInfoService.listByFinancierId(id);
        return Msg.success().add("list",list);
    }

    /**
     * 获取工程公司数据
     * @param id
     * @return
     */
    @GetMapping("com/list")
    public Msg listByEngCompanyId(Long id){
        System.out.println("工程公司： "+ id +" 获取数据");
        List<ProjectLoanApply> list = loanApplyInfoService.listByCompanyId(id);
        return Msg.success().add("list",list);
    }

    /**
     * 获取平台数据
      * @return
     */
    @GetMapping("list")
    public Msg list(){
        System.out.println("获取数据");
        List<ProjectLoanApply> list = loanApplyInfoService.listAll();
        return Msg.success().add("list",list);
    }
    /**
     * 获取资金方数据
     * @param id
     * @return
     */
    @PostMapping("fcompany/list")
    public Msg listByFundCompanyId(Long id){
        System.out.println("资金方："+ id +" 获取数据");
        List<ProjectLoanApply> list = loanApplyInfoService.listByFcompanyId(id);
        return Msg.success().add("list",list);
    }

    @GetMapping("f/info")
    public Msg loanApplyInfoByiId(Long id){
        System.out.println("获取一条放款申请："+ id +" 信息明细");
        ProjectLoanApply projectLoanApply = loanApplyInfoService.loanApplyById(id);
        return Msg.success().add("applyInfo", projectLoanApply);
    }
}
