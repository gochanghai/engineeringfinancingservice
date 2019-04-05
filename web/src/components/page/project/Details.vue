<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>项目详情</span>
                    </div>
                    <!-- 项目信息 -->
                    <div class="info-box">
                        <div class="info-title">项目信息</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn">展开</div>
                            <div class="info-content">
                                <!-- 个人身份信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">项目基本信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="甲方名称：" style="margin-bottom: 0">{{projectInfo.contract.partyAName}}</el-form-item>
                                        <el-form-item label="项目地址：" style="margin-bottom: 0">{{projectInfo.contract.projectAdress}}</el-form-item>
                                        <el-form-item label="合同金额：" style="margin-bottom: 0">{{projectInfo.contract.contractAmount}}</el-form-item>
                                        <el-form-item label="预计结算金额：" style="margin-bottom: 0">{{projectInfo.contract.shouldSettlemenMoney}}</el-form-item>
                                        <el-form-item label="保函要求：" style="margin-bottom: 0">{{projectInfo.contract.guaranteeClaim}}</el-form-item>
                                        <el-form-item label="施工期限：" style="margin-bottom: 0">{{projectInfo.contract.constructionStartDate + ' 至 ' + projectInfo.contract.constructionEndDate}}</el-form-item>
                                        <el-form-item label="项目施工进度：" style="margin-bottom: 0">{{projectInfo.project.projectProgress}}%</el-form-item>
                                        <el-form-item label="项目结算方式：" style="margin-bottom: 0">{{projectInfo.contract.cettlementMethod}}</el-form-item>
                                        <el-form-item label="项目付款方式" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item style="width: 900px; margin: 0 30px">
                                            <!-- 第一列 -->
                                            <el-form style="float: left; width: 250px">
                                                <el-form-item label="预付款：" style="margin-bottom: 0">{{projectInfo.contract.shouldPayment}}万</el-form-item>
                                                <el-form-item label="进度款：" style="margin-bottom: 0">{{projectInfo.contract.progressPaymentMethod}}</el-form-item>
                                                <el-form-item label="验收款付款比例至：" style="margin-bottom: 0">{{projectInfo.contract.checkPaymentRatio}}%</el-form-item>
                                                <el-form-item label="结算款付款比例至：" style="margin-bottom: 0">{{projectInfo.contract.ettlementPaymentRatio}}%</el-form-item>
                                                <el-form-item label="质保金质保金比例：" style="margin-bottom: 0">{{projectInfo.contract.warrantyMoneyPaymentRatio}}%</el-form-item>
                                            </el-form>
                                            <!-- 第二列 -->
                                            <el-form style="float: left; width: 300px">
                                                <el-form-item label="预付款支付日期：" style="margin-bottom: 0">{{projectInfo.contract.shouldPaymentDate}}</el-form-item>
                                                <el-form-item label="月结算日：" style="margin-bottom: 0">{{projectInfo.contract.monthSettlementDay}}</el-form-item>
                                                <el-form-item label="验收款备注：" style="margin-bottom: 0">{{projectInfo.contract.checkPaymentDesc}}</el-form-item>
                                                <el-form-item label="结算款备注：" style="margin-bottom: 0">{{projectInfo.contract.ettlementDesc}}</el-form-item>
                                                <el-form-item label="质保期：" style="margin-bottom: 0">{{projectInfo.contract.warranty}}</el-form-item>
                                            </el-form>
                                            <!-- 第三列 -->
                                            <el-form style="float: left; width: 300px">
                                                <el-form-item label="质保期支付方式：" style="margin-bottom: 0">{{projectInfo.contract.warrantyPayMethod}}</el-form-item>
                                                <el-form-item label="进度付款比例至：" style="margin-bottom: 0">{{projectInfo.contract.progressPaymentRatio}}%</el-form-item>
                                            </el-form>
                                        </el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.contract.contractFile" class="image">
                                                    <span>项目合同</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.contract.contractPaymentFile" class="image">
                                                    <span>合同付款方式页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.contract.inContractLiabilityBook" class="image">
                                                    <span>经营承包责任书</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.contract.constructionSitePhoto" class="image">
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
                                        <el-form-item label="项目利润率：" style="margin-bottom: 0">{{projectInfo.cost.profitMargin}}%</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.cost.progressReturnAccount" class="image">
                                                    <span>工程进度回款台账</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.cost.outputValueTable" class="image">
                                                    <span>项目产值表</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.cost.costAnalysisTable" class="image">
                                                    <span>项目成本分析表</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.cost.costAccount" class="image">
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
                                        <el-form-item label="前期累计开票额：" style="margin-bottom: 0">{{projectInfo.payment.sumBillAmount}}万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.payment.invoiceFile" class="image">
                                                    <span>发票凭证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="前期累计回款额：" style="margin-bottom: 0">{{projectInfo.payment.sumPaybackAmount}}万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.payment.transferFile" class="image">
                                                    <span>转账凭证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.payment.transferToInvoiceFile" class="image">
                                                    <span>对应发票凭证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="前期累计付款额：" style="margin-bottom: 0">{{projectInfo.payment.sumPaymentAmount}}万</el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.payment.buyContractFile" class="image">
                                                    <span>采购合同</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.payment.buyInvoiceFile" class="image">
                                                    <span>采购发票</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.payment.deliveryNote" class="image">
                                                    <span>送货单</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img :src="filesystem + projectInfo.payment.banckTransfer" class="image">
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
                                        <el-form-item label="是否购买意外险：" style="margin-bottom: 0">
                                            <div v-if="projectInfo.other.isBuyInsur === 1" class="content-lable2">是</div>
                                            <div v-else class="content-lable2">否</div>
                                        </el-form-item>
                                        <div v-show="projectInfo.other.isBuyInsur === 1">
                                            <el-form-item label="" style="margin-bottom: 0">
                                                <el-form inline style="border-bottom: 0">
                                                    <el-form-item label="保险到期日" style="margin-bottom: 0">{{projectInfo.other.insurEndDate}}</el-form-item>
                                                    <el-form-item label="保额" style="margin-bottom: 0">{{projectInfo.other.insurAmount}}万</el-form-item>
                                                    <el-form-item label="参保人数" style="margin-bottom: 0">{{projectInfo.other.insurPersonNumber}}人</el-form-item>
                                                </el-form>
                                            </el-form-item>
                                            <el-form-item label="保单凭证：" style="margin-bottom: 0">
                                                <div class="file-box">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <img :src="filesystem + projectInfo.other.insurFile" class="image">
                                                        <!--<span>房产证明</span>-->
                                                    </el-card>
                                                </div>
                                            </el-form-item>
                                            <el-form-item label="特殊事项（选填）：" style="margin-bottom: 0">{{projectInfo.other.unusualDesc}}</el-form-item>
                                        </div>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">编辑</div>

                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'project-dateils',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                filesystem: localStorage.getItem("fileBasePath"),
                projectId: this.$route.query.id,
                projectInfo: null,
            }
        },
        components: {
        },
        computed: {
        },
        created(){
            this.getDataInfo();
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            // 获取信息
            getDataInfo(){
                let _than = this;
                this.$axios.get('api/project/details',{params:{
                        id: this.projectId
                    }}).then(function (response) {
                    console.log(response);
                    _than.projectInfo = response.data.extend;
                }).catch(function (error) {
                    console.log(error);
                });
            },


        }
    }

</script>

<style scoped>
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


    }

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

    /*上传*/

</style>
