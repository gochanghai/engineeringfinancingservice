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
                                            <p>申请人：李四</p>
                                            <p>申请额度：3000万元</p>
                                        </td>
                                        <td class="align-r">申请日期</td>
                                        <td class="align-l">2018-05-07</td>
                                        <td class="align-r">申请编号</td>
                                        <td class="align-l">20180507002</td>
                                        <td class="align-r">项目名称</td>
                                        <td class="align-l">天安云谷二期</td>
                                        <td class="align-r">项目合同编号</td>
                                        <td class="align-l">HT2017012501</td>
                                    </tr>
                                    <tr>
                                        <td class="align-r">申请事由</td>
                                        <td colspan="7" class="align-l">用于购买项目施工所需材料</td>
                                    </tr>
                                    <tr>
                                        <td class="align-r">个人近12个月银行流水</td>
                                        <td colspan="7" class="img">
                                            <img src="../../../../static/img/gcd.png">
                                            <img src="../../../../static/img/gcd.png">
                                            <img src="../../../../static/img/gcd.png">
                                            <img src="../../../../static/img/gcd.png">
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
                            <div class="is-show-btn">展开</div>
                            <div class="info-content">
                                <!-- 项目基本信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">项目基本信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="甲方名称：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="项目地址：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="合同金额：" style="margin-bottom: 0"> 深圳市</el-form-item>
                                        <el-form-item label="预计结算金额：" style="margin-bottom: 0">13534074557</el-form-item>
                                        <el-form-item label="保函要求：" style="margin-bottom: 0">6013822000982256896</el-form-item>
                                        <el-form-item label="施工期限：" style="margin-bottom: 0">2019-01-01 至 2021-01-01</el-form-item>
                                        <el-form-item label="项目施工进度：" style="margin-bottom: 0">13534074557</el-form-item>
                                        <el-form-item label="项目结算方式：" style="margin-bottom: 0">6013822000982256896</el-form-item>
                                        <el-form-item label="项目付款方式" style="margin-bottom: 0;"></el-form-item>
                                        <el-form-item style="width: 900px; margin-bottom: 0">
                                            <!-- 第一列 -->
                                            <el-form style="float: left; width: 250px">
                                                <el-form-item label="预付款：" style="margin-bottom: 0">张三</el-form-item>
                                                <el-form-item label="进度款：" style="margin-bottom: 0">张三</el-form-item>
                                                <el-form-item label="验收款付款比例至：" style="margin-bottom: 0">60%</el-form-item>
                                                <el-form-item label="结算款付款比例至：" style="margin-bottom: 0">70%</el-form-item>
                                                <el-form-item label="质保金质保金比例：" style="margin-bottom: 0">90%</el-form-item>
                                            </el-form>
                                            <!-- 第二列 -->
                                            <el-form style="float: left; width: 300px">
                                                <el-form-item label="预付款支付日期：" style="margin-bottom: 0">2019-12-31</el-form-item>
                                                <el-form-item label="月结算日：" style="margin-bottom: 0">26号</el-form-item>
                                                <el-form-item label="验收款备注：" style="margin-bottom: 0">张三</el-form-item>
                                                <el-form-item label="结算款备注：" style="margin-bottom: 0">张三</el-form-item>
                                                <el-form-item label="质保期：" style="margin-bottom: 0">3年 <span>质保期支付方式：2222</span></el-form-item>
                                            </el-form>
                                            <!-- 第三列 -->
                                            <el-form style="float: left; width: 300px">
                                                <el-form-item label="质保期支付方式：" style="margin-bottom: 0">分期付款</el-form-item>
                                                <el-form-item label="进度付款比例至：" style="margin-bottom: 0">90%</el-form-item>
                                            </el-form>
                                        </el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>项目合同</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>合同付款方式页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>经营承包责任书</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
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
                                        <el-form-item label="项目利润率：" style="margin-bottom: 0">60%</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>工程进度回款台账</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>项目产值表</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>项目成本分析表</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
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
                                        <el-form-item label="前期累计开票额：" style="margin-bottom: 0">1000万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <span>发票凭证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="前期累计回款额：" style="margin-bottom: 0">1000万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <span>转账凭证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <span>对应发票凭证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="前期累计付款额：" style="margin-bottom: 0">1000万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <span>采购合同</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <span>采购发票</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <span>送货单</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
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
                                                <el-form-item label="保险到期日" style="margin-bottom: 0">2019-12-31</el-form-item>
                                                <el-form-item label="保额" style="margin-bottom: 0">500万</el-form-item>
                                                <el-form-item label="参保人数" style="margin-bottom: 0">50人</el-form-item>
                                            </el-form>
                                        </el-form-item>
                                        <el-form-item label="保单凭证：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <!--<span>房产证明</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="特殊事项（选填）：" style="margin-bottom: 0">特殊事项（选填特殊事项（选填</el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 商务经理个人资信情况 -->
                    <div class="info-box">
                        <div class="info-title">商务经理个人资信情况</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn">展开</div>
                            <div class="info-content">
                                <!-- 个人身份信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">身份信息</div>
                                    <div class="content-lable2">已认证</div>
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="姓名：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="身份证号：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="户籍所在地：" style="margin-bottom: 0"> 深圳市</el-form-item>
                                        <el-form-item label="手机号码：" style="margin-bottom: 0">13534074557</el-form-item>
                                        <el-form-item label="银行卡号：" style="margin-bottom: 0">6013822000982256896</el-form-item>
                                        <el-form-item label="开户行：" style="margin-bottom: 0">中国银行</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>手持身份证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>户口本个人页</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 婚姻信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">婚姻状况</div>
                                    <div class="content-lable2">已婚</div>
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="配偶姓名：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="配偶身份证号：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="配偶户籍所在地：" style="margin-bottom: 0"> 深圳市</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>配偶身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>配偶身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>结婚证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>配偶户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>配偶户口本个人页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" hidden class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
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
                                        <el-form-item label="公司全称：" style="margin-bottom: 0">李四消防工程有限公司</el-form-item>
                                        <el-form-item label="统一社会信用代码证：" style="margin-bottom: 0">91440300MA5DM2AC5P</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>公司营业执照</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>公司章程</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
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
                                        <el-form-item label="社保电脑号：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="当前购买社保公司：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="近一年缴纳社保清单：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
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
                                    <el-form>
                                        <el-form-item label="房产 1" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="房产所在地：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="房产证明：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <!--<span>房产证明</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="房产说明：" style="margin-bottom: 0">房产说明</el-form-item>
                                    </el-form>
                                </div>
                                <!-- 车辆信息 -->
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="车辆 1" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="车牌号：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="车辆行驶证：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <!--<span>车辆行驶证</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="车辆说明：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 审批记录 -->
                    <div class="info-box">
                        <div class="info-title">审批记录</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn">展开</div>
                            <!-- 担保审批 -->
                            <div class="info-content">
                                <div class="approve-title-box">
                                    <div class="approve-title-left">担保评估结果</div>
                                    <div class="approve-title-right">评估人：习总   评估时间：2019-01-01 19:30:26</div>
                                </div>
                                <!-- 审批意见 -->
                                <div class="approve-content-box">
                                    <div class="approve-item">
                                        <div class="approve-item-left">是否可授信</div>
                                        <div class="approve-item-right">是</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">授信金额</div>
                                        <div class="approve-item-right">300万元</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">授信期限</div>
                                        <div class="approve-item-right">2019-01-01 至 2020-12-31</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">还款方式（建议）</div>
                                        <div class="approve-item-right">0000000是</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">内控要点</div>
                                        <div class="approve-item-right">是0000000000000</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">贷后管理</div>
                                        <div class="approve-item-right">是0000000000000</div>
                                    </div>
                                </div>
                            </div>
                            <!-- 平台审批 -->
                            <div class="info-content">
                                <div class="approve-title-box">
                                    <div class="approve-title-left">平台审批结果</div>
                                    <div class="approve-title-right">审批人：习总   审批时间：2019-01-01 19:30:26</div>
                                </div>
                                <!-- 审批意见 -->
                                <div class="approve-content-box" style="height: 160px">
                                    <div class="approve-item">
                                        <div class="approve-item-left">是否可授信</div>
                                        <div class="approve-item-right">是</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">补充意见</div>
                                        <div class="approve-item-right">经核对，资料均符合要求，同意授信。</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">拒接原因</div>
                                        <div class="approve-item-right">2019-01-01 至 2020-12-31</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">退回原因</div>
                                        <div class="approve-item-right">0000000是</div>
                                    </div>
                                </div>
                            </div>
                            <!-- 资金方审批 -->
                            <div class="info-content">
                                <div class="approve-title-box">
                                    <div class="approve-title-left">资金方批复结果</div>
                                    <div class="approve-title-right">批复人：习总   批复时间：2019-01-01 19:30:26</div>
                                </div>
                                <!-- 审批意见 -->
                                <div class="approve-content-box">
                                    <div class="approve-item">
                                        <div class="approve-item-left">是否可授信</div>
                                        <div class="approve-item-right">是</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">授信金额</div>
                                        <div class="approve-item-right">300万元</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">授信期限</div>
                                        <div class="approve-item-right">2019-01-01 至 2020-12-31</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">还款方式（建议）</div>
                                        <div class="approve-item-right">0000000是</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">内控要点</div>
                                        <div class="approve-item-right">是0000000000000</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">贷后管理</div>
                                        <div class="approve-item-right">是0000000000000</div>
                                    </div>
                                </div>
                            </div>
                            <!-- 平台补充资料 -->
                            <div class="info-content">
                                <div class="approve-title-box">
                                    <div class="approve-title-left">平台补充资料</div>
                                    <div class="approve-title-right">补充人：习总   补充时间：2019-01-01 19:30:26</div>
                                </div>
                                <!-- 特批平台补充资料 -->
                                <div class="approve-content-box">
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
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">批复</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'credit-info-dateils',
        data() {
            return {
                name: localStorage.getItem('ms_username'),


            }
        },
        components: {
        },
        computed: {
        },
        created(){
        },
        activated(){
        },
        deactivated(){
        },
        methods: {



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
