<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>授信项目详情</span>
                    </div>
                    <!-- 进度条 -->
                    <div class="info-box">
                        <div class="steps-box">
                            <ul class="step">
                                <li v-for="(item, index) in getStepStatusList" :key="index" :class="item.class">
                                    {{item.name}}
                                    <div class="triangle"></div>
                                </li>
                            </ul>
                        </div>
                        <div class="steps-box" v-show="false">
                            <!--<div class="triangle"></div>-->
                            <ul class="step">
                                <li class="active">
                                    已完善项目资料
                                    <div class="triangle"></div>
                                </li>
                                <li class="active">
                                    已提交担保
                                    <div class="triangle"></div>
                                </li>
                                <li class="active">
                                    已通过担保审批
                                    <div class="triangle"></div>
                                </li>
                                <li class="active2">
                                    平台已退回
                                    <div class="triangle"></div>
                                </li>
                                <li>
                                    资金方批复
                                    <div class="triangle"></div>
                                </li>
                                <li>
                                    签署协议
                                    <div class="triangle"></div>
                                </li>
                                <li>
                                    授信完成
                                    <div class="triangle"></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- 申请信息 -->
                    <div class="info-box">
                        <div class="info-title">申请信息</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn">展开</div>
                            <div class="info-content">
                                <div class="apply-table">
                                    <table border="1">
                                    <tr>
                                        <td rowspan="3" class="person">
                                            <p>申请人：{{applyInfo.name}}</p>
                                            <p>申请额度：{{applyInfo.applyAmount}}万元</p>
                                        </td>
                                        <td class="align-r">申请日期</td>
                                        <td class="align-l">{{applyInfo.applyDate}}</td>
                                        <td class="align-r">申请编号</td>
                                        <td class="align-l">{{applyInfo.applyNo}}</td>
                                        <td class="align-r">项目名称</td>
                                        <td class="align-l">{{projectInfo.projectName}}</td>
                                        <td class="align-r">项目合同编号</td>
                                        <td class="align-l">{{projectInfo.contractNo}}</td>
                                    </tr>
                                    <tr>
                                        <td class="align-r">申请事由</td>
                                        <td colspan="7" class="align-l">{{applyInfo.reason}}</td>
                                    </tr>
                                    <tr>
                                        <td class="align-r">个人近12个月银行流水</td>
                                        <td colspan="7" class="img">
                                            <img :src="fileUrl + applyInfo.bankWater">
                                            <!--<img src="../../../../static/img/gcd.png">-->
                                            <!--<img src="../../../../static/img/gcd.png">-->
                                            <!--<img src="../../../../static/img/gcd.png">-->
                                        </td>
                                    </tr>
                                </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 项目信息 -->
                    <div class="info-box">
                        <div class="info-title">项目信息</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn" @click="isPackUpProjectInfo = !isPackUpProjectInfo">{{ isPackUpProjectInfo ==  true ? '展开' :'收起'}}</div>
                            <div class="info-content" v-show="!isPackUpProjectInfo">
                                <!-- 项目基本信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">项目基本信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="甲方名称：" style="margin-bottom: 0">{{projectContractInfo.partyAName}}</el-form-item>
                                        <el-form-item label="项目地址：" style="margin-bottom: 0">{{projectContractInfo.address}}</el-form-item>
                                        <el-form-item label="合同金额：" style="margin-bottom: 0"> {{projectInfo.contractAmount}}</el-form-item>
                                        <el-form-item label="预计结算金额：" style="margin-bottom: 0">{{projectContractInfo.shouldClearAmount}}</el-form-item>
                                        <el-form-item label="保函要求：" style="margin-bottom: 0">{{projectContractInfo.guaranteeClaim}}</el-form-item>
                                        <el-form-item label="施工期限：" style="margin-bottom: 0">{{projectContractInfo.startDate}} 至 {{projectContractInfo.endDate}}</el-form-item>
                                        <el-form-item label="项目施工进度：" style="margin-bottom: 0">{{projectInfo.projectProgress}}%</el-form-item>
                                        <el-form-item label="项目结算方式：" style="margin-bottom: 0">{{projectContractInfo.clearType}}</el-form-item>
                                        <el-form-item label="项目付款方式" style="margin-bottom: 0;"></el-form-item>
                                        <el-form-item style="width: 900px; margin-bottom: 0">
                                            <!-- 第一列 -->
                                            <el-form style="float: left; width: 250px">
                                                <el-form-item label="预付款：" style="margin-bottom: 0">{{projectContractInfo.shouldPaymentAmount}}</el-form-item>
                                                <el-form-item label="进度款：" style="margin-bottom: 0">{{projectContractInfo.progressPaymentType}}</el-form-item>
                                                <el-form-item label="验收款付款比例至：" style="margin-bottom: 0">{{projectContractInfo.checkPaymentRatio}}%</el-form-item>
                                                <el-form-item label="结算款付款比例至：" style="margin-bottom: 0">{{projectContractInfo.clearPaymentRatio}}%</el-form-item>
                                                <el-form-item label="质保金质保金比例：" style="margin-bottom: 0">{{projectContractInfo.warrantyMoneyPaymentRatio}}%</el-form-item>
                                            </el-form>
                                            <!-- 第二列 -->
                                            <el-form style="float: left; width: 300px">
                                                <el-form-item label="预付款支付日期：" style="margin-bottom: 0">{{projectContractInfo.shouldPaymentDate}}</el-form-item>
                                                <el-form-item label="月结算日：" style="margin-bottom: 0">{{projectContractInfo.monthClearDay}}</el-form-item>
                                                <el-form-item label="验收款备注：" style="margin-bottom: 0">{{projectContractInfo.checkPaymentDesc}}</el-form-item>
                                                <el-form-item label="结算款备注：" style="margin-bottom: 0">{{projectContractInfo.clearDesc}}</el-form-item>
                                                <el-form-item label="质保期：" style="margin-bottom: 0">{{projectContractInfo.warranty}}年</el-form-item>
                                            </el-form>
                                            <!-- 第三列 -->
                                            <el-form style="float: left; width: 300px">
                                                <el-form-item label="质保期支付方式：" style="margin-bottom: 0">{{projectContractInfo.warrantyPayType}}</el-form-item>
                                                <el-form-item label="进度付款比例至：" style="margin-bottom: 0">{{projectContractInfo.progressPaymentRatio}}%</el-form-item>
                                            </el-form>
                                        </el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectContractInfo.contractFile" class="image">
                                                    <span>项目合同</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectContractInfo.contractPaymentFile" class="image">
                                                    <span>合同付款方式页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectContractInfo.inContractLiabilityBook" class="image">
                                                    <span>经营承包责任书</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectContractInfo.constructionSitePhoto" class="image">
                                                    <span>施工现场照片</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 项目成本信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">项目成本信息</div>
                                    <!--<div class="content-lable2">已婚</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="项目利润率：" style="margin-bottom: 0">{{projectCostInfo.profitMargin}}%</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectCostInfo.progressReturnAccount" class="image">
                                                    <span>工程进度回款台账</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectCostInfo.outputValueTable" class="image">
                                                    <span>项目产值表</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectCostInfo.costAnalysisTable" class="image">
                                                    <span>项目成本分析表</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectCostInfo.costAccount" class="image">
                                                    <span>项目成本台账</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 项目款项信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">项目款项信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="前期累计开票额：" style="margin-bottom: 0">{{projectPaymentInfo.totalBillAmount}}万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectPaymentInfo.invoice" class="image">
                                                    <span>发票凭证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="前期累计回款额：" style="margin-bottom: 0">{{projectPaymentInfo.totalPaybackAmount}}万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectPaymentInfo.transfer" class="image">
                                                    <span>转账凭证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectPaymentInfo.transferInvoice" class="image">
                                                    <span>对应发票凭证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="前期累计付款额：" style="margin-bottom: 0">{{projectPaymentInfo.totalPaymentAmount}}万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectPaymentInfo.purchaseContract" class="image">
                                                    <span>采购合同</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectPaymentInfo.purchaseInvoice" class="image">
                                                    <span>采购发票</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectPaymentInfo.deliveryNote" class="image">
                                                    <span>送货单</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectPaymentInfo.banckTransfer" class="image">
                                                    <span>银行转账凭证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 其它信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">其它信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="是否购买工商意外险" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="" style="margin-bottom: 0">
                                            <el-form inline style="border-bottom: 0">
                                                <el-form-item label="保险到期日" style="margin-bottom: 0">{{projectOtherInfo.insurEndDate}}</el-form-item>
                                                <el-form-item label="保额" style="margin-bottom: 0">{{projectOtherInfo.insurAmount}}万</el-form-item>
                                                <el-form-item label="参保人数" style="margin-bottom: 0">{{projectOtherInfo.insurPersonNumber}}人</el-form-item>
                                            </el-form>
                                        </el-form-item>
                                        <el-form-item label="保单凭证：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + projectOtherInfo.insurFile" class="image">
                                                    <!--<span>房产证明</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="特殊事项（选填）：" style="margin-bottom: 0">特殊事项{{projectOtherInfo.unusualDesc}}</el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 商务经理个人资信情况 -->
                    <div class="info-box">
                        <div class="info-title">商务经理个人资信情况</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn" @click="isPackUpBusinessInfo = !isPackUpBusinessInfo">{{ isPackUpBusinessInfo ==  true ? '展开' :'收起'}}</div>
                            <div class="info-content" v-show="!isPackUpBusinessInfo">
                                <!-- 个人身份信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">身份信息</div>
                                    <div class="content-lable2">已认证</div>
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="姓名：" style="margin-bottom: 0">{{authenInfo.name}}</el-form-item>
                                        <el-form-item label="身份证号：" style="margin-bottom: 0">{{authenInfo.idCard}}</el-form-item>
                                        <el-form-item label="户籍所在地：" style="margin-bottom: 0"> {{authenInfo.domicile}}</el-form-item>
                                        <el-form-item label="手机号码：" style="margin-bottom: 0">{{authenInfo.bankCardPhone}}</el-form-item>
                                        <el-form-item label="银行卡号：" style="margin-bottom: 0">{{authenInfo.bankCardNo}}</el-form-item>
                                        <el-form-item label="开户行：" style="margin-bottom: 0">{{authenInfo.bank}}</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.idCardSideFace" class="image">
                                                    <span>身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.idCardSideBack" class="image">
                                                    <span>身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.idCardHand" class="image">
                                                    <span>手持身份证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.householdRegHome" class="image">
                                                    <span>户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.householdRegPersonal" class="image">
                                                    <span>户口本个人页</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 婚姻信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">婚姻状况</div>
                                    <div class="content-lable2">{{authenInfo.marriageStatus}}</div>
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="配偶姓名：" style="margin-bottom: 0">{{authenInfo.spouseName }}</el-form-item>
                                        <el-form-item label="配偶身份证号：" style="margin-bottom: 0">{{authenInfo.spouseIdCard }}</el-form-item>
                                        <el-form-item label="配偶户籍所在地：" style="margin-bottom: 0"> {{authenInfo.spouseDomicile }}</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.sidCardSideFace" class="image">
                                                    <span>配偶身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.sidCardSideBack" class="image">
                                                    <span>配偶身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.marriageCert" class="image">
                                                    <span>结婚证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.shouseholdRegHome" class="image">
                                                    <span>配偶户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.shouseholdRegPersonal" class="image">
                                                    <span>配偶户口本个人页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" hidden class="card-file">
                                                    <img :src="fileUrl + authenInfo.divorceCert" class="image">
                                                    <span>离婚证</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 名下企业 -->
                                <div class="content-title">
                                    <div class="content-lable1">名下企业</div>
                                    <!--<div class="content-lable2">已婚</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="公司全称：" style="margin-bottom: 0">{{authenInfo.companyName }}</el-form-item>
                                        <el-form-item label="统一社会信用代码证：" style="margin-bottom: 0">{{authenInfo.creditCode }}</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.companyBusinessLicense" class="image">
                                                    <span>公司营业执照</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.articlesAssoCompany" class="image">
                                                    <span>公司章程</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.officeSpaceLeaseContract" class="image">
                                                    <span>办公场所租赁合同</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 社保信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">社保信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="社保电脑号：" style="margin-bottom: 0">{{authenInfo.socialInsurCardNo }}</el-form-item>
                                        <el-form-item label="当前购买社保公司：" style="margin-bottom: 0">{{authenInfo.socialInsurCompany }}</el-form-item>
                                        <el-form-item label="近一年缴纳社保清单：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + authenInfo.socialInsurDetails" class="image">
                                                    <!--<span>社保清单</span>-->
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <div class="content-title">
                                    <div class="content-lable1">个人资产</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <!-- 房产信息 -->
                                <div class="content-info-box">
                                    <el-form v-for="(house, index) in authenInfo.houses" :key="index">
                                        <el-form-item :label="'房产 - '+ (index+1)" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="房产所在地：" style="margin-bottom: 0">{{house.address}}</el-form-item>
                                        <el-form-item label="房产证明：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + house.premisesPermit" class="image">
                                                    <!--<span>房产证明</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="房产说明：" style="margin-bottom: 0">{{house.remarks}}</el-form-item>
                                    </el-form>
                                </div>
                                <!-- 车辆信息 -->
                                <div class="content-info-box">
                                    <el-form v-for="(car, index) in authenInfo.cars" :key="index">
                                        <el-form-item :label="'车辆 - '+ (index+1)" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="车牌号：" style="margin-bottom: 0">{{car.number}}</el-form-item>
                                        <el-form-item label="车辆行驶证：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="fileUrl + car.driveLicense" class="image">
                                                    <!--<span>车辆行驶证</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="车辆说明：" style="margin-bottom: 0">{{car.remarks}}</el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 审批记录 -->
                    <div class="info-box">
                        <div class="info-title">审批记录</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn" @click="isPackUpReplyInfo = !isPackUpReplyInfo">{{ isPackUpReplyInfo ==  true ? '展开' :'收起'}}</div>
                            <div v-show="!isPackUpReplyInfo">
                                <!-- 审批 -->
                                <div v-for="(item, index) in approveList" :key="index">
                                    <div class="info-content" >
                                        <div class="approve-title-box">
                                            <div class="approve-title-left">{{item.title}}</div>
                                            <div class="approve-title-right">评估人：{{item.name}}   评估时间：{{item.createTime}}</div>
                                        </div>
                                        <!-- 评估结果 -->
                                        <div class="approve-content-box">
                                            <div class="approve-item" v-for=" (item2, index) in item.list" :key="index">
                                                <div class="approve-item-left">{{item2.lable}}</div>
                                                <div class="approve-item-right">{{item2.value}}</div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <!-- 平台补充资料 -->
                                <!-- <div class="info-content">
                                    <div class="approve-title-box">
                                        <div class="approve-title-left">平台补充资料</div>
                                        <div class="approve-title-right">补充人：习总   补充时间：2019-01-01 19:30:26</div>
                                    </div> -->
                                    <!-- 特批平台补充资料 -->
                                    <!-- <div class="approve-content-box">
                                        <div class="approve-item" style="height: 100px">
                                            <div class="approve-item-left" style="height: 100px">补充说明</div>
                                            <div class="approve-item-right" style="height: 100px">是</div>
                                        </div>
                                        <div class="approve-item" style="height: 100px">
                                            <div class="approve-item-left" style="height: 100px">附件</div>
                                            <div class="approve-item-right">
                                                <img src="../../../../static/img/gcd.png" class="approve-add-file">
                                                <img src="../../../../static/img/gcd.png" class="approve-add-file">
                                            </div>
                                        </div>
                                        <div class="approve-item" style="height: 80px" hidden>
                                            <div class="info-bottom-box">
                                                <div class="info-bottom-btn1">取消</div>
                                                <div class="info-bottom-btn2">保存</div>
                                            </div>
                                        </div>
                                    </div> 
                                </div> -->
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1" @click="goBack">返回</div>
                        <div class="info-bottom-btn2" v-show="progress === 2 && role === '1'" @click="vouchApprovalVisible = !vouchApprovalVisible">立即审批</div>
                        <div class="info-bottom-btn2" v-show="progress === 3 && status === 0" v-if="role === '0'"  @click="platformApprovalVisible = !platformApprovalVisible">立即审批</div>
                        <div class="info-bottom-btn2" v-show="progress === 3 && status === 2" v-if="role === '0'"  @click="platformApprovalVisible = !platformApprovalVisible">立即补充资料</div>
                        <div class="info-bottom-btn2" v-show="progress === 4 || progress === 6" v-if="role === '2'" @click="replyVisible = !replyVisible">批复</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <!-- 担保审批弹出框 -->
        <el-dialog :visible.sync="vouchApprovalVisible" width="550px" center="">
            <el-form ref="form" :model="vform" label-width="130px">
                <el-form-item>
                    <el-form-item>
                        <el-radio-group v-model="vform.pResult">
                            <el-radio :label="1">是</el-radio>
                            <el-radio :label="-1">否</el-radio>
                            <el-radio :label="2">退回</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form-item>
                <el-form-item label="授信金额"  v-show="vform.pResult === 1">
                    <el-input type="input" v-model="vform.amount" placeholder="请输入授信金额" style="width: 300px"/>
                </el-form-item>
                <el-form-item label="授信期限"  v-show="vform.pResult === 1">
                    <el-date-picker
                        v-model="vform.date"
                        value-format="yyyy-MM-dd"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="还款方式（建议）"  v-show="vform.pResult === 1">
                    <el-input type="textarea" v-model="vform.repayOpinion" placeholder="请输入还款方式（建议）" style="width: 300px"/>
                </el-form-item>
                <el-form-item label="内控要点"  v-show="vform.pResult === 1">
                    <el-input type="textarea" v-model="vform.incontrol" placeholder="请输入内控要点" style="width: 300px"/>
                </el-form-item>
                <el-form-item label="贷后管理"  v-show="vform.pResult === 1">
                    <el-input type="textarea" v-model="vform.afterLoandesc" placeholder="请输入贷后管理" style="width: 300px"/>
                </el-form-item>
                <el-form-item label="拒绝原因"  v-show="vform.pResult === -1">
                    <el-input type="textarea" v-model="vform.opinion" placeholder="请输入拒绝原因" style="width: 300px"/>
                </el-form-item>
                <el-form-item label="退回原因"  v-show="vform.pResult === 2">
                    <el-input type="textarea" v-model="vform.opinion" placeholder="请输入退回原因" style="width: 300px"/>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="info" @click="vouchApprovalVisible = false">取 消</el-button>
                <el-button type="warning" @click="vouchApprovalCommit">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 平台审批弹出框 -->
        <el-dialog :visible.sync="platformApprovalVisible" width="500px" center="">
            <el-form ref="form" :model="pform" label-width="100px" v-if="status === 0">
                <el-form-item>
                    <el-form-item>
                        <el-radio-group v-model="pform.pResult">
                            <el-radio :label="1">通过</el-radio>
                            <el-radio :label="-1">拒绝</el-radio>
                            <el-radio :label="2">退回</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form-item>
                <el-form-item label="说明"  v-show="pform.pResult === 1">
                    <el-input type="textarea" v-model="pform.pDesc" placeholder="请输入通过说明" style="width: 300px"/>
                </el-form-item>
                <el-form-item label="拒绝原因"  v-show="pform.pResult === -1">
                    <el-input type="textarea" v-model="pform.pDesc" placeholder="请输入拒绝原因" style="width: 300px"/>
                </el-form-item>
                <el-form-item label="退回原因"  v-show="pform.pResult === 2">
                    <el-input type="textarea" v-model="pform.pDesc" placeholder="请输入退回原因" style="width: 300px"/>
                </el-form-item>
            </el-form>
            <el-form ref="form" :model="pform" label-width="100px" v-if="status === 2">
                <el-form-item label="补充说明">
                    <el-input type="textarea" v-model="pform.pDesc" placeholder="请输入通过说明" style="width: 300px"/>
                </el-form-item>
                <el-form-item label="附件">
                    <el-upload id="uploadfile"
                        class="upload-demo"
                        ref="upload"
                        :action="uploadPath"
                        :on-success="uploadSuccessP"
                        :limit="1"
                        :file-list="fileList"
                    >
                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="info" @click="platformApprovalVisible = false">取 消</el-button>
                <el-button type="warning" @click="platformCommit">提 交</el-button>
            </span>
        </el-dialog>

        <!-- 资金方批复弹出框 -->
        <el-dialog title="批复意见" :visible.sync="replyVisible" center width="550px">
            <el-form ref="form" :model="fform" label-width="120px">
                <el-form-item label="是否可授信" v-show="applyInfo.creditType === 1">
                    <el-radio-group v-model="fform.result">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                        <el-radio :label="3">特批(有条件同意)</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="是否可授信" v-show="applyInfo.creditType === 2">
                    <el-radio-group v-model="fform.result">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="授信金额"  v-show="fform.result === 1">
                    <el-input v-model="fform.amount" style="width: 100px"/> 万
                </el-form-item>
                <el-form-item label="授信期限"  v-show="fform.result === 1" style="width: 250px">
                    <el-date-picker
                            v-model="fform.date"
                            value-format="yyyy-MM-dd"
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="还款方式"  v-show="fform.result === 1">
                    <el-input v-model="fform.repayType" style="width: 350px"/>
                </el-form-item>
                <el-form-item label="批复文件"  v-show="fform.result === 1">
                    <el-upload id="uploadfile"
                        class="upload-demo"
                        ref="upload"
                        :action="uploadPath"
                        :on-success="uploadSuccess"
                        :limit="1"
                        :file-list="fileList"
                        :auto-upload="true">
                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    </el-upload>
                </el-form-item>
                <el-form-item label="拒绝原因"  v-show="fform.result === -1">
                    <el-input type="textarea" v-model="fform.desc" placeholder="请输入拒绝原因"  style="width: 350px"/>
                </el-form-item>
                <el-form-item label="补充意见"  v-show="fform.result === 3">
                    <el-input type="textarea" v-model="fform.desc" placeholder="请输入补充资料" style="width: 350px"/>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="replyVisible = false">取 消</el-button>
                <el-button type="primary" class="btn-submit" @click="replyCommit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'credit-info-dateils',
        data() {
            return {
                stepStatusList: [],
                name: localStorage.getItem('ms_username'),
                fileUrl: localStorage.getItem("fileBasePath"),
                uploadPath: localStorage.getItem("uploadPath"),
                role: localStorage.getItem("role"),
                isPackUpProjectInfo: false,
                isPackUpBusinessInfo: false,
                isPackUpReplyInfo: false,
                id: this.$route.query.id,
                applyInfo: {
                    projectName: '',
                    applyNo: "",
                    applyDate: "",
                    name: "",
                    projectId: null,
                    contractNo: '',
                    applyAmount: null,
                    reason: null,
                    bankWater: null,
                    creditType: 1,
                },
                projectInfo:{

                },
                projectContractInfo: {
                    partyAName: "",
                    address: "",
                    shouldClearAmount: "",
                    guaranteeClaim: "",
                    startDate: "",
                    endDate: "",
                    clearType: "",
                    shouldPaymentAmount: "",
                    shouldPaymentDate: "",
                    progressPaymentType: "",
                    monthClearDay: "",
                    progressPaymentRatio: "",
                    checkPaymentRatio: "",
                    checkPaymentDesc: "",
                    clearPaymentRatio: "",
                    clearDesc: "",
                    warrantyMoneyPaymentRatio: "",
                    warranty: "",
                    warrantyPayType: "",

                    contractFile: "",
                    contractPaymentFile: "",
                    inContractLiabilityBook: "",
                    constructionSitePhoto: ""
                },
                projectCostInfo: {
                    profitMargin: "",
                    progressReturnAccount: "",
                    outputValueTable: "",
                    costAnalysisTable: "",
                    costAccount: "",
                },
                projectPaymentInfo: {
                    totalBillAmount: "",
                    totalPaybackAmount: "",
                    totalPaymentAmount: "",
                    invoice: "",
                    transfer: "",
                    transferInvoice: "",
                    purchaseContract: "",
                    purchaseInvoice: "",
                    deliveryNote: "",
                    banckTransfer: "",
                },
                projectOtherInfo: {
                    isInsur: "",
                    insurEndDate: "",
                    insurAmount: "",
                    insurPersonNumber: "",
                    insurFile: "",
                    unusualDesc: ""
                },
                financierInfo: {

                },
                authenInfo: {
                    name: "",
                    idCard: '',
                    bankCardNo: '',
                    bank: '',
                    bankCardPhone: '',
                    cardTypeCode: '',
                    bankCode: '',
                    domicile: '',
                    idCardSideFace: '',
                    idCardSideBack: '',
                    idCardHand:'',
                    householdRegHome:'',
                    householdRegPersonal: '',
                    marriageStatus: '',
                    spouseName: '',
                    spouseIdCard: '',
                    spouseDomicile: '',
                    sidCardSideFace: '',
                    sidCardSideBack: '',
                    marriageCert: '',
                    shouseholdRegHome: '',
                    shouseholdRegPersonal:'',
                    divorceCert: '',
                    companyName: '',
                    creditCode: '',
                    companyBusinessLicense: '',
                    articlesAssoCompany: '',
                    officeSpaceLeaseContract: '',
                    socialInsurCardNo: '',
                    socialInsurCompany: '',
                    socialInsurDetails: '',
                    cars: [],
                    houses: [],
                },
                creditExams: [],
                approveList: [],
                // 审批进度及状态
                progress: '',
                status: 0,
                // 担保审批弹窗
                vouchApprovalVisible: false,
                platformApprovalVisible: false,
                replyVisible: false,
                // 担保审批表单
                vform: {
                    pResult: 1,
                    afterLoandesc: '',
                    incontrol: '',
                    opinion: '',
                    repayOpinion: '',
                    amount: '',
                    creditDate: '',
                    date: ""
                },

                // 平台审批表单
                pform: {
                    pResult: 1,
                    pDesc: '',
                    pPerson: '',
                    creditId: '',
                    file: ''
                },
                // 资金方审批表单
                creditDate: '',
                fform: {
                    result: 1,
                    amount: '',
                    repayType: '',
                    startTime: '',
                    endTime: '',
                    replyFile: '',
                    desc: '',
                    creditType: 1,
                    creditDate: '',
                    date: []
                },
                fileList: [],
            }
        },
        components: {
        },
        computed: {
        },
        watch:{
            creditDate:function() {
                this.fform.fCreditStartDate = this.creditDate[0];
                this.fform.fCreditEndDate = this.creditDate[1];
            },
            'vform.date':function() {
                this.vform.startTime = this.vform.date[0];
                this.vform.endTime = this.vform.date[1];
                this.vform.creditDate = this.vform.date[0] + " 至 " + this.vform.date[1];
            },
            'fform.date':function() {
                this.fform.startTime = this.fform.date[0];
                this.fform.endTime = this.fform.date[1];
                this.fform.creditDate = this.fform.date[0] + " 至 " + this.fform.date[1];
            },
        },
        created(){
            this.getData(this.id);
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            /**
             * 获取数据
             */
            getData(id) {
                let _than = this;
                this.$axios.get('api/credit/apply/info',{params:{id: id }}).then(res => {
                    console.log(res);
                    let data =  res.data.extend;
                    if(null != data){
                        if(null != data.applyInfo){
                            _than.applyInfo =data.applyInfo;
                            _than.progress = data.applyInfo.step;
                            _than.status = data.applyInfo.status;
                        }
                        if(null != data.projectInfo){
                            _than.projectInfo = data.projectInfo;
                        }
                        if(null != data.projectContractInfo){
                            _than.projectContractInfo = data.projectContractInfo;
                        }
                        if(null != data.projectCostInfo){
                            _than.projectCostInfo = data.projectCostInfo;
                        }
                        if(null != data.projectPaymentInfo){
                            _than.projectPaymentInfo = data.projectPaymentInfo;
                        }
                        if(null != data.projectOtherInfo){
                            _than.projectOtherInfo = data.projectOtherInfo;
                        }
                        if(null != data.financierInfo){
                            _than.financierInfo = data.financierInfo;
                        }
                        if(null != data.authenInfo){
                            _than.authenInfo = data.authenInfo;
                        }
                        _than.creditExams = data.creditExams;
                        let resultList = [];
                        for(let item of data.creditExams){
                            let data = {
                                createTime: item.createTime,
                                name: item.name,
                                title: item.title,
                                list: [],                               
                            }
                            
                            if(item.title === "担保评估结果"){
                                let list = [];
                                if(item.result === 1){
                                    list.push({lable: "是否可授信", value: "是"});
                                    list.push({lable: "授信金额", value: item.amount});
                                    list.push({lable: "授信期限", value: item.creditDate});
                                    list.push({lable: "还款方式（建议）", value: item.repayOpinion});
                                    list.push({lable: "内控要点", value: item.incontrol});
                                    list.push({lable: "贷后管理", value: item.afterLoandesc});
                                }                                
                                if(item.result === 2){
                                    list.push({lable: "是否可授信", value: "退回"});
                                    list.push({lable: "退回原因", value: item.opinion});
                                }
                                if(item.result === -1){
                                    list.push({lable: "是否可授信", value: "否"});
                                    list.push({lable: "拒绝原因", value: item.opinion});
                                }
                                console.log(list);
                                data.list = list;
                            }

                            if(item.title === "平台方审批结果"){
                                let list = [];
                                if(item.result === 1){
                                    list.push({lable: "是否可授信", value: "是"});
                                    list.push({lable: "说明", value: item.opinion});
                                }                                
                                if(item.result === 2){
                                    list.push({lable: "是否可授信", value: "退回"});
                                    list.push({lable: "退回原因", value: item.opinion});
                                }
                                if(item.result === -1){
                                    list.push({lable: "是否可授信", value: "否"});
                                    list.push({lable: "拒绝原因", value: item.opinion});
                                }
                                console.log(list);
                                data.list = list;
                            }

                            if(item.title === "平台补充资料"){
                                let list = [
                                    {lable: "补充说明", value: item.opinion},
                                    {lable: "附件", value: item.replyFile}
                                ];
                                data.list = list;
                            }

                            if(item.title === "资金方批复结果"){
                                let list = [];
                                if(item.result === 1){
                                    list.push({lable: "是否可授信", value: "是"});
                                    list.push({lable: "授信金额", value: item.amount});
                                    list.push({lable: "授信期限", value: item.creditDate});
                                    list.push({lable: "还款方式", value: item.repayType});
                                    list.push({lable: "批复文件", value: item.replyFile});
                                }                                
                                if(item.result === 3){
                                    list.push({lable: "是否可授信", value: "特批（有条件同意）"});
                                    list.push({lable: "特批原因", value: item.opinion});
                                }
                                if(item.result === -1){
                                    list.push({lable: "是否可授信", value: "否"});
                                    list.push({lable: "拒绝原因", value: item.opinion});
                                }
                                console.log(list);
                                data.list = list;
                            }
                            resultList.push(data);
                        }
                        _than.approveList = resultList;

                    }
                    if(null != data.applyInfo){
                        this.getStepStatusList(data.applyInfo.step, data.applyInfo.status);
                    }else{
                        this.getStepStatusList(0,0);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },

            getStepStatusList(step,status){
                let list = ['已完善项目资料','已提交担保','已通过担保审批','平台审批','资金方批复','签署协议','授信完成'];
                let list2 = [];
                for(let index in list){
                    let step1 = {name: '', class: ''};
                    step1.name = list[index];
                    index = 1+parseInt(index);
                    if(index <= step){                        
                        step1.class = 'active';                        
                        if(index == step && status == -1){
                            step1.class = 'active3';
                        }
                    }
                    if(index == 2 && step == 2){
                      step1.class = 'active';
                    }
                    if(index == step && status == 2){
                      step1.class = 'active2';
                    }
                    console.log(index);
                    // console.log(step);
                    list2.push(step1);
                }
                this.getStepStatusList = list2;
            },

            /**
             * 返回按钮
             */
            goBack(){
                // 返回上一步
                this.$router.go(-1);
            },
            /**
             * 担保审批提交数据
             */
            vouchApprovalCommit(){
                // this.$refs['form'].validate((valid) => {
                //     if (!valid) {
                //         return;
                //     }
                // });
                this.$axios.post('api/credit/appr/save',
                    this.qs.stringify(
                        {
                            applyId: this.id,
                            result: this.vform.pResult,
                            amount: this.vform.amount,
                            opinion: this.vform.opinion,
                            creditDate: this.vform.creditDate,
                            repayOpinion: this.vform.repayOpinion,
                            incontrol: this.vform.incontrol,
                            afterLoandesc: this.vform.afterLoandesc,
                            name: localStorage.getItem('ms_username'),
                            startTime: this.vform.startTime,
                            endTime: this.vform.endTime,
                            title: "担保评估结果"
                        }
                    )).then(res => {
                    console.log(res);
                    this.$message.success('提交成功！');
                    this.$router.go(-1);
                }).catch(function (error) {
                    console.log(error);
                });
            },
            /**
             * 平台审批提交按钮
             */
            platformCommit( ){
                // this.$refs['form'].validate((valid) => {
                //     if (!valid) {
                //         return;
                //     }
                // });
                if(this.status === 0){
                    this.$axios.post('api/credit/appr/save',
                    this.qs.stringify(
                        {
                            applyId: this.id,
                            result: this.pform.pResult,
                            opinion: this.pform.pDesc,
                            name: localStorage.getItem('ms_username'),
                            title: "平台方审批结果"
                        }
                    )).then(res => {
                        this.$message.success('提交成功！');
                        this.$router.go(-1);
                    }).catch(function (error) {
                        console.log(error);
                    });
                }

                if(this.status === 2){
                    this.$axios.post('api/credit/appr/save',
                    this.qs.stringify(
                        {   
                            result: 1,
                            applyId: this.id,
                            opinion: this.pform.pDesc,
                            replyFile: this.pform.file,
                            name: localStorage.getItem('ms_username'),
                            title: "平台补充资料"
                        }
                    )).then(res => {
                        this.$message.success('提交成功！');
                        this.$router.go(-1);
                    }).catch(function (error) {
                        console.log(error);
                    });
                }
                
                console.log(this.form);
            },
            /**
             * 资金方批复提交按钮
             */
            replyCommit(form){
                // this.$refs['form'].validate((valid) => {
                //     if (!valid) {
                //         return;
                //     }
                // });
                this.$refs.upload.submit();
                this.$axios.post('api/credit/appr/save',
                    this.qs.stringify(
                        {
                            applyId: this.id,
                            amount: this.fform.amount,
                            result: this.fform.result,
                            opinion: this.fform.desc,
                            creditType: this.fform.creditType,
                            name: localStorage.getItem('ms_username'),
                            title: "资金方批复结果",
                            repayType: this.fform.repayType,
                            creditDate: this.fform.creditDate,
                            startTime: this.fform.startTime,
                            endTime: this.fform.endTime,
                            replyFile: this.fform.replyFile,
                        }
                    )).then(res => {
                    console.log(res);
                    this.$message.success('提交成功！');
                    this.$router.go(-1)
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);
            },
            uploadSuccess(res,file,files){
                this.fform.replyFile = res.extend.fileSystem.filePath;
            },
            uploadSuccessP(res,file,files){
                console.log(res);
                this.pform.file = res.extend.fileSystem.filePath;
            },

        },
        filters: {
            resultFilter1(val){
                if(val === -1){
                    return "否"
                }
                if(val === 2){
                    return "退回"
                }
                if(val === 3){
                    return "特批(有条件同意)"
                }
                return "是"
            }
        }
    }

</script>

<style scoped>

    /* 进度条 */
    /*credit-step*/
    .steps-box {
        /*width: 100%;*/
        /*height: 100px;*/
        margin: 0 auto 20px auto;
    }
    ul,li {
        list-style: none;
    }
    .step {
        overflow: hidden;
    }
    .step li {
        position: relative;
        float: left;
        padding: 0 20px;
        line-height: 30px;
        color: #999;
        background: #e3e3e3;
        /*width: 152px;*/
    }
    .triangle {
        content: "";
        position: relative;
        position: absolute;
        top: 0;
        left: 100%;
        z-index: 1;
        border-left: 15px solid #fff;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
    }
    .triangle:after {
        content: "";
        position: absolute;
        left: -16px;
        top: -15px;
        border-left: 15px solid #e3e3e3;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
    }
    .step li.active {
        color: #fff;
        background: #ff8308;
        /*width: 152px;*/
    }
    .step li.active .triangle:after {
        border-left-color: #ff8308;
    }
    .step li.active2 {
        color: #fff;
        background: #FFB267;
        /*width: 152px;*/
    }
    .step li.active2 .triangle:after {
        border-left-color: #FFB267;
    }

    .step li.active3 {
        color: #fff;
        background: rgb(248, 28, 28);
        /*width: 152px;*/
    }
    .step li.active3 .triangle:after {
        border-left-color: rgb(248, 28, 28);
    }

    /* 申请信息 */
    .info-box{
        position: relative;
    }
    .info-title{
        font-size:14px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
        width: 100%;
        line-height:19px;
        margin: 0 auto;
        text-align: center;
        border: 1px dashed #ccc;
        padding: 20px 0;
    }
    .info-btn-box{
        width: 100%;
        padding-top: 20px;
        border-top: 1px dashed #ccc;
        border: 1px dashed #ccc;
    }
    .info-content{
        width: 100%;
        /*height: 300px;*/
    }
    .is-show-btn{
        height:19px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        color:rgba(255,130,8,1);
        width: 80px;
        line-height:19px;
        text-align: center;
        border: 1px solid rgba(255,130,8,1);
        margin-left: auto;
        border-radius: 2px;

    }


    /* 申请信息 */
    .apply-table {
        margin: 0;
        padding: 0;
        /*width: 1200px;*/
        margin: 20px 20px;
        font-size: 13px;
    }

    .apply-table table {
        width: 100%;
        border-collapse: collapse;
        border-spacing: 0;
    }
    .apply-table table td{
        border:1px solid #c0c0c0;
        padding:8px 10px;
    }

    .apply-table .person{
        width: 160px;
        height: 160px;
        /*background: #0099cc;*/
        text-align: center;
        line-height: 90px;
        color: #fff;
        background:rgba(92,185,246,1);
        box-shadow:1px 1px 4px 0px rgba(0,0,0,0.5);
        /*font-weight:bold;*/
    }
    .apply-table .person p{
        line-height: 2;
        text-align: left;
        padding:0 6px;
        font-size: 16px;
    }
    .apply-table .img{
        height:80px;
    }
    .apply-table .img img{
        height:100%;
        border-radius: 10px;
        margin-right: 10px;
    }
    .align-r{
        text-align: right;
    }
    .align-l{
        text-align: left;
    }

    /*  */

    .content-title{
        display: block;
        margin: 0 auto 0 0;
        padding: 10px 10px;
    }
    .content-lable1{
        display: block;
        float: left;
        height:20px;
        font-size:14px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
        line-height:20px;
        padding: 0 10px;
        /*width: 60px;*/
    }
    .content-lable2{
        display: block;
        float: left;
        width: 60px;
        height: 20px;
        background: #FFE699;
        font-size:12px;
        font-family:MicrosoftYaHei;
        color:rgba(187,114,0,1);
        line-height:20px;
        text-align: center;
    }
    .content-info-box{
        padding: 20px 20px 20px 150px;
    }

    .file-box{
        padding: 10px 0;
        text-align: center;
    }
    .card-file{
        float: left;
        width: 120px;
        /*height: 170px;*/
        margin-right: 20px;
    }
    .image {
        width: 100%;
        height: 120px;
        display: block;
    }

    .approve-title-box{
        margin-top: 20px;
        width: 100%;
        height:40px;
        background:rgba(92,185,246,1);
        border:1px solid rgba(92,185,246,1);
        color: #ffffff;
    }
    .approve-title-left{
        line-height: 40px;
        float: left;
        padding-left: 20px;
    }
    .approve-title-right{
        padding-right: 20px;
        float: right;
        line-height: 40px;
    }
    .approve-content-box{
        border-right: 1px solid #ccc;
        border-left: 1px solid #ccc;
    }
    .approve-item {
        /*display: block;*/
        border-bottom: 1px solid #ccc;
        height: 40px;
    }
    .approve-item-left{
        display: block;
        float: left;
        width: 20%;
        border-right: 1px solid #ccc;
        line-height: 40px;
        text-align: center;
    }
    .approve-item-right{
        width: 70%;
        padding-left: 20px;
        display: block;
        float: left;
        line-height: 40px;
        text-align: left;
    }
    .approve-add-file{
        width: 80px;
        height: 80px;
        margin: 10px 10px 10px 0;
        border-radius: 4px;
    }


    .info-bottom-box{
        position: relative;
        width: 260px;
        margin: 0 auto;
        text-align: center;
        padding: 30px 0 40px 0;

    }
    .info-bottom-btn1{
        position: relative;
        float: left;
        width:120px;
        height:30px;
        line-height: 30px;
        background:rgba(255,130,8,1);
        border-radius:5px;
        color: rgba(255,255,255,1);
    }
    .info-bottom-btn2{
        position: relative;
        float: left;
        margin-left: 20px;
        width:120px;
        height:30px;
        line-height: 30px;
        background:rgba(255,130,8,1);
        border-radius:5px;
        color: rgba(255,255,255,1);
    }

</style>

<style>
#uploadfile /deep/ .el-upload--text {
  width: 80px;
  height: 35px;
}
</style>
