<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <!--<el-breadcrumb-item>首页</el-breadcrumb-item>-->
                <!--<el-breadcrumb-item>我的融资列表</el-breadcrumb-item>-->
                <el-breadcrumb-item>授信项目详情</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="credit-step">
                <div style="width: 100%; text-align: center">
                    <div class="triangle"></div>
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
            <div class="info-title">申请信息</div>
            <div class="apply-info-box">
                <div style="width: 185px;height: 100%; line-height: 100%; text-align: left; background-color: #5CB9F6; float: left">
                    <h3 style="padding: 30px 10px">{{this.dataInfo.financier.name}}</h3>
                    <h3 style="padding: 20px 10px">申请额度：{{this.dataInfo.creditApply.applyAmount}} 万</h3>
                </div>
                <div style="width: 100%; text-align: center">
                    <div style="width: 100%; height:30px;">
                        <div style="width: 200px; height: 100%; float: left">申请日期</div>
                        <div style="width: 200px; border-left: 1px solid #ccc; height: 100%; float: left">{{this.dataInfo.creditApply.date}}</div>
                        <div style="width: 100px; border-left: 1px solid #ccc; height: 100%; float: left">申请编号</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">{{this.dataInfo.creditApply.creditNo}}</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">项目名称</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">{{this.dataInfo.project.projectName}}</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">项目合同编号</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">{{this.dataInfo.project.contractNo}}</div>
                    </div>
                    <div style="width: 100%; height: 30px; border-top: 1px solid #ccc; border-bottom: 1px solid #ccc">
                        <div style="width: 200px; float: left; height: 30px; border-right: 1px solid #ccc;">申请事由</div>
                        <div style=" height: 30px; text-align: left">{{this.dataInfo.creditApply.originIncident}}</div>
                    </div>
                    <div style="width: 100%;  height: 100%;line-height: 116px; text-align: center">
                        <div style="width: 200px;  height: 100%; float: left; border-right: 1px solid #ccc;">个人近12个月银行流水</div>
                        <div style="width: 100%; height: 100%; text-align: left">
                            <img :src="filesysip + dataInfo.creditApply.bankListFile" style="width: 100px"
                                 @click="bigImageVisible(dataInfo.creditApply.bankListFile)"/>
                        </div>
                    </div>

                </div>
            </div>
            <!-- 项目信息 -->
            <div>
                <div class="info-title">项目信息</div>
                <div class="project-info">
                    <el-row style="padding-bottom: 10px">项目基本信息
                        <el-button style="float: right; color: #ff8208" size="mini" @click="isStep1 = !isStep1">
                            <div v-if="isStep1">收起</div>
                            <div v-else>展开</div>
                        </el-button>
                    </el-row>
                    <div class="form-box"  v-show="isStep1 === true">
                        <el-form ref="form" :model="form" label-width="180px">
                            <el-form-item label="甲方名称">
                                <span>{{this.dataInfo.contract.partyAName}}</span>
                            </el-form-item>
                            <el-form-item label="项目地址">
                                <span>{{this.dataInfo.contract.projectAdress}}</span>
                            </el-form-item>
                            <el-form-item label="合同金额">
                                <span>{{this.dataInfo.project.contractAmount}} 万元</span>
                            </el-form-item>
                            <el-form-item label="预计结算金额">
                                <span>{{this.dataInfo.contract.shouldSettlemenMoney}} 万元</span>
                            </el-form-item>
                            <el-form-item label="保函要求">
                                <span>{{this.dataInfo.contract.guaranteeClaim}}</span>
                            </el-form-item>
                            <el-form-item label="施工期限">
                                    <span>{{this.dataInfo.contract.constructionStartDate}} - {{this.dataInfo.contract.constructionEndDate}}</span>
                            </el-form-item>
                            <el-form-item label="项目施工进度">
                                <span>{{this.dataInfo.project.projectProgress}} %</span>
                            </el-form-item>
                            <el-form-item label="项目结算方式">
                                <span>{{this.dataInfo.contract.cettlementMethod}}</span>
                            </el-form-item>

                            <el-form-item label="项目付款方式">
                                <el-form :inline="true">
                                    <el-row>
                                        <el-form-item label="预付款">
                                            <span>{{this.dataInfo.contract.shouldPayment}} 万元</span>
                                        </el-form-item>
                                        <el-form-item label="预付款支付日期">
                                            <span>{{this.dataInfo.contract.shouldPaymentDate}}</span>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="进度款">
                                            <span>{{this.dataInfo.contract.progressPaymentMethod}}</span>
                                        </el-form-item>
                                        <el-form-item label="月结算日">
                                            <span>{{this.dataInfo.contract.monthSettlementDay}}</span>
                                        </el-form-item>
                                        <el-form-item label="进度付款比例至">
                                            <span>{{this.dataInfo.contract.progressPaymentRatio}}</span>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="验收款：付款比例至">
                                            <span>{{this.dataInfo.contract.checkPaymentRatio}}</span>
                                        </el-form-item>
                                        <el-form-item label="验收款备注">
                                            <span>{{this.dataInfo.contract.checkPaymentDesc}}</span>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="结算款：付款比例至">
                                            <span>{{this.dataInfo.contract.ettlementPaymentRatio}}</span>
                                        </el-form-item>
                                        <el-form-item label="结算款备注">
                                            <span>{{this.dataInfo.contract.ettlementDesc}}</span>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="质保金：质保金比例">
                                            <span>{{this.dataInfo.contract.warrantyMoneyPaymentRatio}}</span>
                                        </el-form-item>
                                        <el-form-item label="质保期">
                                            <span>{{this.dataInfo.contract.warranty}}</span>
                                        </el-form-item>
                                        <el-form-item label="质保期支付方式">
                                            <span>{{this.dataInfo.contract.warrantyPayMethod}}</span>
                                        </el-form-item>
                                    </el-row>

                                </el-form>
                            </el-form-item>
                            <div style="text-align:left; padding-left: 100px">
                                <el-form :inline="true">
                                    <el-form-item label="">
                                        项目合同
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.contract.contractFile"
                                                 @click="bigImageVisible(dataInfo.contract.contractFile)"/>
                                        </div>
                                    </el-form-item>
                                    <el-form-item label="">
                                        合同付款方式页附件凭证
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.contract.contractPaymentFile"
                                                 @click="bigImageVisible(dataInfo.contract.contractPaymentFile)"/>
                                        </div>
                                    </el-form-item>
                                    <el-form-item label="">
                                        内部经营承包责任书附件
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.contract.inContractLiabilityBook"
                                                 @click="bigImageVisible(dataInfo.contract.inContractLiabilityBook)"/>
                                        </div>
                                    </el-form-item>
                                    <el-form-item label="">
                                        施工现场图片
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.contract.constructionSitePhoto"
                                                 @click="bigImageVisible(dataInfo.contract.constructionSitePhoto)"/>
                                        </div>
                                    </el-form-item>
                                </el-form>
                            </div>
                        </el-form>
                    </div>
                </div>
            </div>
            <!-- 成本信息 -->
            <div>
                <div class="project-cost-info">
                    <el-row>项目成本信息
                        <el-button style="float: right; color: #ff8208" size="mini" @click="isStep2 = !isStep2">
                            <div v-if="isStep2">收起</div>
                            <div v-else>展开</div>
                        </el-button>
                    </el-row>
                    <div class="form-box" v-show="isStep2 === true">
                        <el-form ref="form" :model="form" label-width="150px">
                            <el-form-item label="项目利润率">
                                <span>{{this.dataInfo.cost.profitMargin}} %</span>
                            </el-form-item>
                            <div style="padding-left: 100px">
                                <el-form :inline="true">
                                    <el-form-item label="">
                                        工程进度回款台账
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.cost.progressReturnAccount"
                                                 @click="bigImageVisible(dataInfo.cost.progressReturnAccount)"/>
                                        </div>
                                    </el-form-item>
                                    <el-form-item label="">
                                        项目产值表
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.cost.outputValueTable"
                                                 @click="bigImageVisible(dataInfo.cost.outputValueTable)"/>
                                        </div>
                                    </el-form-item>
                                    <el-form-item label="">
                                        项目成本分析表
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.cost.costAnalysisTable"
                                                 @click="bigImageVisible(dataInfo.cost.costAnalysisTable)"/>
                                        </div>
                                    </el-form-item>
                                    <el-form-item label="">
                                        项目成本台账
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.cost.costAccount"
                                                 @click="bigImageVisible(dataInfo.cost.costAccount)"/>
                                        </div>
                                    </el-form-item>
                                </el-form>
                            </div>
                        </el-form>
                    </div>
                </div>
            </div>
            <!-- 款项信息 -->
            <div>
                <div class="project-cost-info">
                    <el-row>项目款项信息
                        <el-button style="float: right; color: #ff8208" size="mini" @click="isStep3 = !isStep3">
                            <div v-if="isStep3">收起</div>
                            <div v-else>展开</div>
                        </el-button>
                    </el-row>
                    <div class="form-box" v-show="isStep3 === true">
                        <el-form :inline="true" label-width="150px">
                            <el-row>
                                <el-form-item label="前期累计开票额">
                                    <span>{{this.dataInfo.payment.sumBillAmount}} 万</span>
                                </el-form-item>
                                <el-form-item label="">
                                    发票凭证
                                    <div class="image-box">
                                        <img class="img"
                                             :src="filesysip + this.dataInfo.payment.invoiceFile"
                                             @click="bigImageVisible(dataInfo.payment.invoiceFile)"/>
                                    </div>
                                </el-form-item>
                            </el-row>
                            <el-row>
                                <el-form-item label="前期累计回款额">
                                    <span>{{this.dataInfo.payment.sumPaybackAmount}} 万</span>
                                </el-form-item>
                                <el-form-item label="">
                                    转账凭证
                                    <div class="image-box">
                                        <img class="img"
                                             :src="filesysip + this.dataInfo.payment.transferFile"
                                             @click="bigImageVisible(dataInfo.payment.transferFile)"/>
                                    </div>
                                </el-form-item>
                                <el-form-item label="">
                                    对应发票凭证
                                    <div class="image-box">
                                        <img class="img"
                                             :src="filesysip + this.dataInfo.payment.transferToInvoiceFile"
                                             @click="bigImageVisible(dataInfo.payment.transferToInvoiceFile)"/>
                                    </div>
                                </el-form-item>
                            </el-row>
                            <el-row>
                                <el-form-item label="前期累计付款额">
                                    <span>{{this.dataInfo.payment.sumPaymentAmount}} 万</span>
                                </el-form-item>
                                <el-form-item label="">
                                    采购合同
                                    <div class="image-box">
                                        <img class="img"
                                             :src="filesysip + this.dataInfo.payment.buyContractFile"
                                             @click="bigImageVisible(dataInfo.payment.buyContractFile)"/>
                                    </div>
                                </el-form-item>
                                <el-form-item label="">
                                    采购发票
                                    <div class="image-box">
                                        <img class="img"
                                             :src="filesysip + this.dataInfo.payment.buyInvoiceFile"
                                             @click="bigImageVisible(dataInfo.payment.buyInvoiceFile)"/>
                                    </div>
                                </el-form-item>
                                <el-form-item label="">
                                    送货单
                                    <div class="image-box">
                                        <img class="img"
                                             :src="filesysip + this.dataInfo.payment.deliveryNote"
                                             @click="bigImageVisible(dataInfo.payment.deliveryNote)"/>
                                    </div>
                                </el-form-item>
                                <el-form-item label="">
                                    银行转账凭证
                                    <div class="image-box">
                                        <img class="img"
                                             :src="filesysip + this.dataInfo.payment.banckTransfer"
                                             @click="bigImageVisible(dataInfo.payment.banckTransfer)"/>
                                    </div>
                                </el-form-item>
                            </el-row>
                        </el-form>
                    </div>
                </div>
            </div>
            <!-- 其它信息 -->
            <div>
                <div class="project-cost-info">
                    <el-row>其它信息
                        <el-button style="float: right; color: #ff8208" size="mini" @click="isStep4 = !isStep4">
                            <div v-if="isStep4">收起</div>
                            <div v-else>展开</div>
                        </el-button>
                    </el-row>
                    <div class="form-box" v-show="isStep4 === true">
                        <el-form ref="form" :model="form" label-width="150px">
                            <el-form-item label="是否购买工商意外险">
                                <el-tag v-show="this.dataInfo.other.isBuyInsur === 1">是</el-tag>
                                <el-tag v-show="this.dataInfo.other.isBuyInsur === 0">否</el-tag>
                            </el-form-item>
                            <div style="padding-left: 40px; height: 220px; text-align: left">
                                <div v-show="isInsurance === 1">
                                    <el-form :inline="true" class="demo-form-inline" label-width="150px">
                                        <el-form-item label="保险到期日">
                                            <span>{{this.dataInfo.other.insurEndDate}}</span>
                                        </el-form-item>
                                        <el-form-item label="保额">
                                            <span>{{this.dataInfo.other.insurAmount}}万元</span>
                                        </el-form-item>
                                        <el-form-item label="人数">
                                            <span>{{this.dataInfo.other.insurPersonNumber}}</span>
                                        </el-form-item>
                                    </el-form>
                                    <el-form-item label="">
                                        保单凭证
                                        <div class="image-box">
                                            <img class="img"
                                                 :src="filesysip + this.dataInfo.other.insurFile"
                                                 @click="bigImageVisible(dataInfo.other.insurFile)"/>
                                        </div>
                                    </el-form-item>
                                </div>
                            </div>
                            <el-form-item label="特殊事项（选填）">
                                <span>123</span>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
            </div>
            <div>
                <div class="info-title" style="border-bottom: 1px dashed #999; padding: 20px 0; margin: 20px 0">融资人资信情况</div>
                <el-row>身份信息
                    <el-button style="float: right; color: #ff8208" size="mini" @click="isFinfo = !isFinfo">
                        <div v-if="isFinfo">收起</div>
                        <div v-else>展开</div>
                    </el-button>
                </el-row>
                <div style="height: 1000px; width: 100%" v-show=" isFinfo === true"></div>
            </div>
            <el-form>
                <div style="text-align: center">
                    <!--<el-form-item style="padding-left: 300px" v-show="isStep1 === true">-->
                        <!--<el-button type="primary" @click="onReturn" class="btn-margin">返回</el-button>-->
                        <!--<el-button type="primary" @click="secondStep" class="btn-margin">下一步</el-button>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item style="padding-left: 300px" v-show="isStep2 === true">-->
                        <!--<el-button type="primary" @click="oneStep" class="btn-margin">上一步</el-button>-->
                        <!--<el-button type="primary" @click="threeStep" class="btn-margin">下一步</el-button>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item style="padding-left: 300px" v-show="isStep3 === true">-->
                        <!--<el-button type="primary" @click="secondStep" class="btn-margin">上一步</el-button>-->
                        <!--<el-button type="primary" @click="fourStep" class="btn-margin">下一步</el-button>-->
                    <!--</el-form-item>-->
                    <el-form-item>
                        <!--<el-button type="primary" @click="threeStep" class="btn-margin">上一步</el-button>-->
                        <el-button class="btn-submit" @click="goReturn" >返回</el-button>
                        <el-button type="primary" v-show="dataInfo.creditApply.step === 2" class="btn-submit" @click="pingguVisible = true" >去审批</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </div>

        <!-- 评估弹出框 -->
        <el-dialog title="评估意见" :visible.sync="pingguVisible" center width="30%">
            <el-form ref="form" :model="form" label-width="120px">
                <el-form-item>
                    <el-radio-group v-model="form.ecResult">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                        <el-radio :label="2">退回</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="授信金额"  v-show="this.form.ecResult === 1">
                    <el-input v-model="form.ecAmount" style="width: 100px"></el-input> 万
                </el-form-item>
                <el-form-item label="授信期限"  v-show="this.form.ecResult === 1" style="width: 250px">
                    <el-date-picker
                        v-model="sxDate"
                        value-format="yyyy-MM-dd"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="还款方式"  v-show="this.form.ecResult === 1">
                    <el-input v-model="form.ecRepayType" style="width: 350px"></el-input>
                </el-form-item>
                <el-form-item label="内控要点"  v-show="this.form.ecResult === 1">
                    <el-input type="textarea" v-model="form.ecInControlDesc" placeholder="请输入内控要点" style="width: 350px"></el-input>
                </el-form-item>
                <el-form-item label="贷后管理"  v-show="this.form.ecResult === 1">
                    <el-input type="textarea" v-model="form.ecLoanAfterManageDesc" placeholder="请输入贷后管理" style="width: 350px"></el-input>
                </el-form-item>
                <el-form-item label="拒绝原因"  v-show="this.form.ecResult === -1">
                    <el-input type="textarea" v-model="form.ecDesc" placeholder="请输入拒绝原因"  style="width: 350px"></el-input>
                </el-form-item>
                <el-form-item label="退回原因"  v-show="this.form.ecResult === 2">
                    <el-input type="textarea" v-model="form.ecDesc" placeholder="请输入退回原因" style="width: 350px"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="pingguVisible = false">取 消</el-button>
                <el-button type="primary" class="btn-submit" @click="save">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 签署协议文件框 -->
        <el-dialog :visible.sync="uploadFileVisible" width="410px" center>
            <div class="del-dialog-cnt">已选择协议签署方式为：线下面签</div>
            <div class="del-dialog-cnt">请将签署好的协议扫描成一份PDF文档并上传。</div>
            <el-upload
                class="upload-demo"
                action="https://jsonplaceholder.typicode.com/posts/">
                <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <span slot="footer" class="dialog-footer">
                <el-button @click="uploadFileVisible = false">取 消</el-button>
                <el-button type="primary" class="btn-submit" @click="save1">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 图片弹出框 -->
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'find-credit-info-detail',
            data: function(){
                var checkDate = (rule, value, callback) => {
                    console.log("checkOutputValue: check");
                    if (!value) {
                        return callback(new Error('授信期限不能为空'));
                    }
            };
            return {
                filesysip: localStorage.getItem("fileBasePath"),
                creditId: this.$route.query.id,
                dataInfo: null,
                sxDate: null,
                pingguVisible: false,
                uploadFileVisible: false,

                dialogImageUrl: '',
                dialogVisible: false,
                isStep1: true,
                isStep2: true,
                isStep3: true,
                isStep4: true,
                isInsurance: 1,
                isFinfo: false,
                form: {
                    ecResult: 1,
                    ecAmount: '',
                    ecCreditDate:'',
                    ecRepayType: '',
                    ecInControlDesc: '',
                    ecLoanAfterManageDesc: '',
                    ecDesc: '',
                    ecPerson: '',

                    region: '',
                    date1: '',
                    date2: '',
                    delivery: true,
                    type: ['步步高'],
                    resource: '小天才',
                    desc: '',
                    options: []
                },
                rules: {
                    // pass: [
                    //     { validator: validatePass, trigger: 'blur' }
                    // ],
                    date: [
                        { validator:  checkDate, trigger: 'blur' }
                    ],
                    // idCard: [
                    //     { validator: checkIdCard, trigger: 'blur' }
                    // ]
                }


            }
        },
        created() {
            this.getCreditDataInfo();
        },
        // 监听器
        watch: {
            sxDate:function() {
                this.form.ecCreditDate = this.sxDate[0]+ " - " + this.sxDate[1];
            },
        },
        methods: {
            // 获取信息
            getCreditDataInfo(){
                let _than = this;
                this.$axios.get('credit/f/info',{params:{
                        id: this.creditId
                    }}).then(function (response) {
                    console.log(response);
                    _than.dataInfo = response.data.extend;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            // 附件放大预览
            bigImageVisible(imgPath){
                console.log(imgPath);
                this.dialogImageUrl = this.filesysip + imgPath;
                this.dialogVisible = true;
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            // 返回按钮
            goReturn() {
                this.$router.push("ec-credit-apply-list");
            },
            // 保存按钮
            save(){
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                });
                let _that = this;
                this.$axios.post('credit-appr/ec/save',
                    this.qs.stringify(
                        {   id:this.creditId,
                            creditId:this.creditId,
                            ecResult: this.form.ecResult,
                            ecAmount: this.form.ecAmount,
                            ecCreditDate: this.form.ecCreditDate,
                            ecRepayType: this.form.ecRepayType,
                            ecInControlDesc: this.form.ecInControlDesc,
                            ecLoanAfterManageDesc: this.form.ecLoanAfterManageDesc,
                            ecDesc: this.form.ecDesc,
                            ecPerson: this.ecPerson,
                        }
                    )).then(function (response) {
                    console.log(response);
                    _that.getCreditDataInfo();
                    _that.$message.success('提交成功！');
                    _that.pingguVisible = false;

                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
            // 签署协议
            save1(){

            },
        }
    }
</script>

<style scoped>

    .crumbs {
        background-color: #fff;
        padding-left: 50px;
        margin-bottom: 0;
    }

    .breadcrumb {
        line-height: 30px;
        padding-left: 20px;
        border-left: 4px solid #ff8208;
    }

    .breadcrumb el-breadcrumb-item {
        font-size: 18px;
    }

    .container {
        border: none;
        border-top: 1px solid #999;
        border-radius: 0;
    }

    .info-title{
        color: #999;
        text-align: center;
        font-size: 18px;
        margin-bottom: 20px;
    }
    .apply-info-box {
        margin: 30px 0;
        height: 180px;
        border: 1px solid #ccc;
        color: #cccccc;
    }
    .project-info{
        padding-top: 20px;
        border-top: 1px dashed #999;
    }
    .project-cost-info{
        padding-top: 20px;
        border-top: 1px dashed #999;
    }
    .form-box{
        padding-left: 100px;
        width: 90%;
    }
    .input-width{
        width: 210px;
    }
    .btn-margin{
        margin: 40px 40px 50px 0;

    }


    /*credit-step*/
    .credit-step {
        width: 100%;
        height: 100px;
        border-bottom: 1px dashed #ccc;
        margin-bottom: 10px;
        text-align: center;
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
        width: 152px;
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
        width: 152px;
    }

    .step li.active .triangle:after {
        border-left-color: #ff8308;
    }

    .step li.active2 {
        color: #fff;
        background: #FFB267;
        width: 152px;
    }

    .step li.active2 .triangle:after {
        border-left-color: #FFB267;
    }

    .btn-submit{
        background:rgba(255,130,8,1);
        border-radius:2px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        border:1px solid rgba(255,130,8,1);
    }

    .image-box{
        width: 148px;
        height: 148px;
        background-color: #fbfdff;
        border: 1px dashed #c0ccda;
        border-radius: 6px;
        box-sizing: border-box;
        cursor: pointer;
        line-height: 146px;
        vertical-align: top;
    }

    .box-card {
        width: 148px;
        height: 148px;
    }

    .img{
        width: 100%;
        height: 100%;
    }

</style>
