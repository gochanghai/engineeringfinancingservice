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
    private FinancierService financierService;
    @Autowired
    private LoanApprovalService loanApprovalService;
    @Autowired
    private MessageService messageService;

    /**
     * 保存申请数据
     * @param loanApply
     * @return
     */
    @PostMapping("save")
    public Msg save(LoanApplyEntity loanApply){
        System.out.println(loanApply.toString());
        // 根据项目ID 得到项目信息，从而得到工程公司ID
        ProjectEntity project = projectService.getById(loanApply.getPId());
        // 设置工程公司ID
        loanApply.setComId(project.getComId());
        boolean result = loanApplyInfoService.save(loanApply);
        System.out.println("放款申请信息保存成功: " + result);
        LoanApprovalEntity approval = new LoanApprovalEntity();
        approval.setId(loanApply.getId()).setApplyId(loanApply.getId());
        boolean b1 = loanApprovalService.save(approval);
        if (result){
            FinancierEntity financier = financierService.getById(project.getFId());
            String content = "亲爱的用户，融资人"+financier.getName()+"请放款，待您放款审批，请您及时处理，便于后期业务的开展！";
            boolean b = messageService.productionMessage(1, "待您放款审批通知", content, project.getComId());
        }
        return Msg.success();
    }

    /**
     * 保存采购订单数据
     * @param purchaseOrder
     * @return
     */
    @PostMapping("purchase/save")
    public Msg savePurchase(ProjectPurchaseOrderEntity purchaseOrder){
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
        List<LoanApplyEntity> list = loanApplyInfoService.listByFinancierId(id);
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
        List<LoanApplyEntity> list = loanApplyInfoService.listByEngCompanyId(id);
        return Msg.success().add("list",list);
    }

    /**
     * 获取平台数据
      * @return
     */
    @GetMapping("list")
    public Msg list(){
        System.out.println("获取数据");
        List<LoanApplyEntity> list = loanApplyInfoService.listAll();
        return Msg.success().add("list",list);
    }
    /**
     * 获取资金方数据
     * @param id
     * @return
     */
    @GetMapping("fund/list")
    public Msg listByFundCompanyId(Long id){
        System.out.println("资金方："+ id +" 获取数据");
        List<LoanApplyEntity> list = loanApplyInfoService.listByFundCompanyId(id);
        return Msg.success().add("list",list);
    }

    @GetMapping("f/info")
    public Msg loanApplyInfoByiId(Long id){
        System.out.println("获取一条放款申请："+ id +" 信息明细");
        LoanApplyEntity loanApply = loanApplyInfoService.loanApplyById(id);
        return Msg.success().add("applyInfo",loanApply);
    }
}
