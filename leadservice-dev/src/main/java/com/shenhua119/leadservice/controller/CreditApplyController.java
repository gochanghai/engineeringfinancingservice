package com.shenhua119.leadservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenhua119.leadservice.entity.*;
import com.shenhua119.leadservice.service.*;
import com.shenhua119.leadservice.util.Msg;
import com.shenhua119.leadservice.util.SerialNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-16 3:02
 */
@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/credit")
public class CreditApplyController {

    /**项目信息业务接口*/
    @Autowired
    private ProjectService projectService;
    /** 授信申请业务接口 */
    @Autowired
    private CreditApplyService creditApplyService;
    @Autowired
    private BusinessManagerService businessManagerService;
    /**项目成本信息业务接口*/
    @Autowired
    private ProjectCostService projectCostInfoService;
    /**项目合同信息业务接口*/
    @Autowired
    private ProjectContractService projectContractService;
    /**授信项目款项信息业务接口*/
    @Autowired
    private ProjectPaymentService projectPaymentService;
    /**授信项目其它信息业务接口*/
    @Autowired
    private ProjectOtherService projectOtherInfoService;
    /**授信融资人认证业务接口*/
    @Autowired
    private FinancierAuthenService financierAuthenService;
    /**授信审批业务接口*/
    @Autowired
    private CreditApprovalService creditApprovalService;
    /**授信消息公告业务接口*/
    @Autowired
    private MessageService messageService;


    /***
     * 获取融资人的授信申请详细信息
     * @param id
     * @return
     */
    @GetMapping("apply/info")
    public Msg getCreditApplyInfoById(Long id){
        System.out.println("获取授信详细信息: " + id);
        // 获取申请信息
        ProjectCreditApply projectCreditApply = creditApplyService.getById(id);
        // 获取融资人简单信息
        BusinessManager financier = businessManagerService.getById(projectCreditApply.getUserId());
        // 获取项目简单信息
        Project project = projectService.getById(projectCreditApply.getProjectId());
        // 获取项目合同信息
        ProjectContract contract = projectContractService.getById(projectCreditApply.getProjectId());
        // 获取项目款项信息
        ProjectPayment payment = projectPaymentService.getById(projectCreditApply.getProjectId());
        // 获取项目成本信息
        ProjectCost cost = projectCostInfoService.getById(projectCreditApply.getProjectId());
        // 获取项目其它信息
        ProjectOther other = projectOtherInfoService.getById(projectCreditApply.getProjectId());
        // 获取融资人认证信息
        BusinessManagerAuthen authen = financierAuthenService.getAuthDetails(projectCreditApply.getUserId());

        List<CreditExamineapprove> creditExams = creditApprovalService.list(new QueryWrapper<CreditExamineapprove>().eq("apply_id", id));
        // 返回数据
        return Msg.success().add("applyInfo", projectCreditApply).add("financierInfo",financier)
                            .add("projectContractInfo",contract).add("projectInfo",project)
                            .add("projectPaymentInfo",payment).add("projectCostInfo",cost)
                            .add("authenInfo",authen).add("projectOtherInfo",other)
                            .add("creditExams", creditExams);
    }


    /**
     * 获取融资人的授信申请列表数据
     * @param id
     * @return
     */
    @GetMapping("b")
    public Msg getCreditApplyInfoByFinancierId(Long id){
//        List<ProjectCreditApply> list = creditApplyService.list(new QueryWrapper<ProjectCreditApply>().eq("f_id" ,id));
        List<CreditListView> list1 = creditApplyService.selectByFinancierId(id);
        return Msg.success().add("list",list1);
    }

    /**
     * 获取融资人的项目
     * @param id
     * @return
     */
    @GetMapping("project/list")
    public Msg getProjectListByFId(Long id){
        List<Project> list = projectService.list(new QueryWrapper<Project>().eq("user_id" ,id));
        // 生成一个申请编号
        String applyNo = SerialNumber.Getnum();
        return Msg.success().add("list",list).add("applyNo",applyNo);
    }

    /**
     * 获取工程公司授信数据
     * @param companyId
     * @return
     */
    @GetMapping("ec")
    public Msg getCreditApplyInfoByCompanyId(Long companyId){
        System.out.println("工程公司：" + companyId +" 获取授信数据");
        List<CreditListView> list = creditApplyService.listByCompanyId(companyId);
        return Msg.success().add("list",list);
    }

    /**
     * 获取平台获取授信数据
     * @return
     */
    @GetMapping("p")
    public Msg getCreditApplyInfoP(){
        System.out.println("平台获取授信数据");
        List<CreditListView> list = creditApplyService.listAll();
        return Msg.success().add("list",list);
    }

    /**
     * 获取资金方授信数据
     * @param id
     * @return
     */
    @GetMapping("f")
    public Msg getCreditApplyInfoByFundCompanyId(Long id){
        System.out.println("资金方获取授信数据");
        List<CreditListView> list = creditApplyService.listByFundCompanyId(id);
        return Msg.success().add("list",list);
    }

    /**
     * 保存授信申请
     * @param projectCreditApply
     * @return
     */
    @PostMapping("apply/insert")
    public Msg save(ProjectCreditApply projectCreditApply){
        System.out.println("申请授信： " + projectCreditApply);
        boolean result;

        // 判断是否提交
        if (projectCreditApply.getStatus() == 1) {
            //获取项目信息得到公司ID
            Project project = projectService.getById(projectCreditApply.getProjectId());
            //设置进度，初始化授信类型，设置公司ID
            projectCreditApply.setStep(1).setCreditType(1)
                    .setCompanyId(project.getCompanyId()).setStatus(0);
            // 保存授信申请信息
            result =  creditApplyService.save(projectCreditApply);

            //
            CreditExamineapprove cai = new CreditExamineapprove();


            //初始化授信审批记录表
            cai.setApplyNo(projectCreditApply.getApplyNo()).setApplyId(projectCreditApply.getId());

            boolean b = creditApprovalService.save(cai);
            if(b){
                String content = "亲爱的用户，融资人"+projectCreditApply.getName()
                        +"请的授信申请（"+project.getProjectName()
                        +"），待您完善项目资料，请您及时处理，便于后期业务的开展！";
                boolean b1 = messageService.productionMessage(1, "待您完善工程资料通知", content, project.getCompanyId());
            }
        }else {
            projectCreditApply.setStep(0);
            result =  creditApplyService.save(projectCreditApply);
        }
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 更新授信申请信息
     * @param projectCreditApply
     * @return
     */
    @PostMapping("apply/update")
    public Msg update(ProjectCreditApply projectCreditApply){
        System.out.println("申请授信： " + projectCreditApply);
        boolean result;

        // 判断是否提交
        if (projectCreditApply.getStatus() == 1) {
            //获取项目信息得到公司ID
            Project project = projectService.getById(projectCreditApply.getProjectId());
            //设置进度，初始化授信类型，设置公司ID
            projectCreditApply.setStep(1).setCreditType(1)
                    .setCompanyId(project.getCompanyId()).setStatus(0);
            // 保存授信申请信息
            result =  creditApplyService.updateById(projectCreditApply);

            //
            CreditExamineapprove cai = new CreditExamineapprove();


            //初始化授信审批记录表
            cai.setApplyNo(projectCreditApply.getApplyNo()).setApplyId(projectCreditApply.getId());

            boolean b = creditApprovalService.save(cai);
            if(b){
                String content = "亲爱的用户，融资人"+projectCreditApply.getName()
                        +"请的授信申请（"+project.getProjectName()
                        +"），待您完善项目资料，请您及时处理，便于后期业务的开展！";
                boolean b1 = messageService.productionMessage(1, "待您完善工程资料通知", content, project.getCompanyId());
            }
        }else {
            projectCreditApply.setStep(0);
            result =  creditApplyService.save(projectCreditApply);
        }
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 更新审批进度
     * @param creditApply
     * @return
     */
    @PostMapping("apply/status")
    public Msg updateStatus(ProjectCreditApply creditApply){
        System.out.println("申请授信： " + creditApply);
        boolean result = creditApplyService.updateById(creditApply);
        if (result){
            return Msg.success();
        }
        return Msg.fail();
    }

}
