<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>新增金融产品</span>
                    </div>
                    <!-- 项目信息 -->
                    <div class="info-box">
                        <div class="info-title">配置参数</div>
                        <div class="info-btn-box">
                            <!--<div class="is-show-btn">展开</div>-->
                            <div class="info-content">
                                <!-- 基本信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">基本信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <div class="form-box-l">
                                        <el-form label-width="150px">
                                            <el-form-item label="产品编号：" >
                                                <el-input v-model="form.productNo" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="产品名称：">
                                                <el-input v-model="form.productName" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="产品大类：">
                                                <el-select v-model="form.productTypeId" placeholder="请选择" style="width: 200px">
                                                    <el-option v-for="type in this.productTypes" :key="type.id" :label="type.name" :value="type.id"/>
                                                </el-select>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                    <div class="form-box-r">
                                        <el-form label-width="150px">
                                            <el-form-item label="基础年利率：" >
                                                <el-input v-model="form.baseYearRate" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="资金渠道：" >
                                                <el-select v-model="form.fComId" style="width: 200px" placeholder="请选择">
                                                    <el-option v-for="funcom in this.financeComList" :key="funcom.id" :label="funcom.companyFullName + funcom.cooperationBank" :value="funcom.id"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="产品图标：">
                                                <div class="file-box">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                action="https://jsonplaceholder.typicode.com/posts/"
                                                                :show-file-list="false">
                                                            <img v-if="name === null" class="avatar">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <!--<span>法人身份证正面</span>-->
                                                    </el-card>
                                                </div>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                                <!-- 产品配置 -->
                                <div class="content-title">
                                    <div class="content-lable1">产品配置</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <div class="form-box-l">
                                        <el-form label-width="150px">
                                            <el-form-item label="期限类型：">
                                                <el-radio-group v-model="form.deadlineType">
                                                    <el-radio label="y">年</el-radio>
                                                    <el-radio label="q">季</el-radio>
                                                    <el-radio label="m">月</el-radio>
                                                    <el-radio label="d">日</el-radio>
                                                </el-radio-group>
                                            </el-form-item>
                                            <el-form-item label="期限范围：">
                                                <el-input v-model="form.productNumber" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="收取服务费类型：">
                                                <el-select v-model="form.serviceFeeType" placeholder="请选择" style="width: 200px">
                                                    <el-option key="1" label="按比例一次性收取" value="按比例一次性收取"></el-option>
                                                    <el-option key="2" label="按贷款周期" value="按贷款周期"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                    <div class="form-box-r">
                                        <el-form label-width="150px">
                                            <el-form-item label="逾期罚息利率：" >
                                                <el-input v-model="form.lateChargeRate" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="是否可申请展期：" >
                                                <el-radio-group v-model="form.isDelay">
                                                    <el-radio label="1">是</el-radio>
                                                    <el-radio label="0">否</el-radio>
                                                </el-radio-group>
                                            </el-form-item>
                                            <el-form-item label="还款方式：" >
                                                <el-radio-group v-model="form.repaymentType">
                                                    <el-radio label="1">按月付息到期还本</el-radio>
                                                    <el-radio label="2">等额本金</el-radio>
                                                    <el-radio label="3">等额本息</el-radio>
                                                    <el-radio label="4">一次性还清本息</el-radio>
                                                </el-radio-group>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                                <!-- 担保配置 -->
                                <div class="content-title">
                                    <div class="content-lable1">担保配置</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <div class="form-box-l">
                                        <el-form label-width="150px">
                                            <el-form-item label="是否担保：" >
                                                <el-radio-group v-model="form.isAssure">
                                                    <el-radio label="1">是</el-radio>
                                                    <el-radio label="0">否</el-radio>
                                                </el-radio-group>
                                            </el-form-item>
                                            <el-form-item label="收取担保费类型：" >
                                                <el-select v-model="form.assureFeeType" style="width: 200px" placeholder="请选择">
                                                    <el-option key="1" label="按比例一次性收取" value="按比例一次性收取"></el-option>
                                                    <el-option key="2" label="按贷款周期" value="按贷款周期"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="服务费比例：">
                                                <el-input v-model="form.assureServiceFeeRate" style="width: 200px"></el-input>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                    <div class="form-box-r">
                                        <el-form label-width="150px">
                                            <el-form-item label="担保公司：" >
                                                <el-select v-model="form.assureComId" placeholder="请选择">
                                                    <el-option v-for="engicom in this.engineeringComList" :key="engicom.id"
                                                               :label="engicom.companyName" :value="engicom.id"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="抵押类型：" >
                                                <el-radio-group v-model="form.mortgageType">
                                                    <el-radio label="无"></el-radio>
                                                    <el-radio label="房抵"></el-radio>
                                                    <el-radio label="车抵"></el-radio>
                                                    <el-radio label="应收账款抵"></el-radio>
                                                </el-radio-group>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                                <!-- 审批信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">审批信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <!-- 审批信息 -->
                                <div class="content-info-box" hidden>
                                    <el-form>
                                        <el-form-item label="是否购买工商意外险" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="保险到期日：" style="margin-bottom: 0">2019-12-31</el-form-item>
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
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">保存</div>
                        <div class="info-bottom-btn2">保存并提交</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'add-product',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                financeComList: null,
                engineeringComList: null,
                productTypes:[
                    {id:1, name:'工程贷'},
                    {id:2, name:'房易贷'},
                    {id:3, name:'信用贷'},
                    {id:4, name:'融资租赁'},
                ],
                form: {
                    productNo: null,
                    productName: null,
                    productType: null,
                    baseYearRate: null,
                    fundCompanyId: null,
                    deadlineType: 'y',
                    lateChargeRate: null,
                    deadlineMin: null,
                    deadlineMax: null,
                    isDelay: '1',
                    serviceFeeType: '按比例一次性收取',
                    serviceFeeRate: null,
                    repaymentType: 1,
                    isAssure: '1',
                    assureFeeType: '按比例一次性收取',
                    assureCompanyId: null,
                    assureServiceFeeRate: '',
                    mortgageType: '无',
                    status: 0
                },

            }
        },
        components: {
        },
        computed: {
        },
        created(){
            this.getFinanceCompanyList();
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            // 获取Table数据
            getFinanceCompanyList(){
                // 获取获取资金公司数据
                let _this = this;
                this.$axios.get('fc/options').then((response) => {
                    console.log(response.data.extend);
                    _this.financeComList = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });

                // 获取担保公司数据
                this.$axios.get('api/engcom/list').then((response) => {
                    console.log(response.data.extend);
                    _this.engineeringComList = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            // 返回
            onReturn() {
                this.$message.success('返回！');
                this.$router.push("business-manager-list")
            },
            // 仅保存
            save(){
                // 产品状态
                // this.form.status = 0;
                this.$message.success('保存成功！');
                this.$axios.post('fp/save',
                    this.qs.stringify(
                        {
                            productNo: this.form.productNo,
                            productName: this.form.productName,
                            productTypeId: this.form.productTypeId,
                            baseYearRate: this.form.baseYearRate,
                            fComId: this.form.fComId,
                            deadlineType: this.form.deadlineType,
                            lateChargeRate: this.form.lateChargeRate,
                            deadlineMin: this.form.deadlineMin,
                            deadlineMax: this.form.deadlineMax,
                            isDelay: this.form.isDelay,
                            serviceFeeType: this.form.serviceFeeType,
                            serviceFeeRate: this.form.serviceFeeRate,
                            repaymentType: this.form.repaymentType,
                            isAssure: this.form.isAssure,
                            assureFeeType: this.form.assureFeeType,
                            assureComId: this.form.assureComId,
                            assureServiceFeeRate: this.form.assureServiceFeeRate,
                            mortgageType: this.form.mortgageType,
                            status: this.form.status
                        }
                    )).then(function (response) {
                    console.log(response);
                    this.$router.push("financial-product-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
            // 保存并提交
            saveAndSubmit(){
                this.$message.success('提交成功！');
                this.$router.push("financial-product-list");
            }


        }
    }

</script>

<style>
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
        /*解决高度塌陷问题*/
        zoom:1;
        overflow: hidden;
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
        /*width: 600px;*/
        margin: 0 auto;
        /*解决高度塌陷问题*/
        zoom:1;
        overflow: hidden;
    }
    .form-box-l{
        display: block;
        /*position: relative;*/
        float: left;
        width: 400px;
        margin-right: 50px;
    }

    .form-box-r{
        display: block;
        /*position: relative;*/
        float: left;
        width: 500px;
        margin-right: 50px;
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
        width: 400px;
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

    .file-box{
        padding: 10px 0;
        text-align: center;
    }
    .card-file{
        float: left;
        width: 100px;
        /*height: 170px;*/
        margin-right: 20px;
    }
    /*上传*/
    .el-upload--text{
        /*margin: 10px 10px;*/
        width: 100px;
        height: 100px;
    }
    .avatar-uploader{
        height: 100px;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 100px;
        height: 100px;
        line-height: 100px;
        text-align: center;
    }
    .avatar {
        width: 100px;
        height: 100px;
        display: block;
    }

</style>
