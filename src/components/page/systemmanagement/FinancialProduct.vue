<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <!--<el-breadcrumb-item>首页</el-breadcrumb-item>-->
                <!--<el-breadcrumb-item>我的融资列表</el-breadcrumb-item>-->
                <el-breadcrumb-item>新增金融产品</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box" style="width: 55%">
                <div style="width: 100%">
                    <div style="padding: 10px 20px; text-align: left">基本信息</div>
                    <div style="width: 50%">
                        <!--<el-form ref="form" :model="form" label-width="180px">-->
                            <!--<el-form-item label="企业全称">-->
                                <!--<el-input v-model="form.companyName" class="input-width"></el-input>-->
                            <!--</el-form-item>-->
                        <!--</el-form>-->
                    </div>
                    <div style="width: 50%">
                        <!--<el-form ref="form" :model="form" label-width="180px">-->
                            <!--<el-form-item label="企业全称">-->
                                <!--<el-input v-model="form.companyName" class="input-width"></el-input>-->
                            <!--</el-form-item>-->
                        <!--</el-form>-->
                    </div>
                </div>
                <el-form ref="form" :model="form" label-width="180px">
                    <el-form-item label="产品编号">
                        <el-input v-model="form.productNo" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="产品名称">
                        <el-input v-model="form.productName" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="产品大类">
                        <el-select v-model="form.productTypeId" placeholder="请选择">
                            <el-option key="1" label="工程贷" value="1"></el-option>
                            <el-option key="2" label="房易贷" value="2"></el-option>
                            <el-option key="3" label="信用贷" value="3"></el-option>
                            <el-option key="4" label="融资租赁" value="4"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="基础年利率">
                        <el-input v-model="form.baseYearRate" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="资金渠道">
                        <el-select v-model="form.fComId" placeholder="请选择">
                            <el-option v-for="funcom in this.fundComOptions" :key="funcom.id" :label="funcom.cooperationBank" :value="funcom.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <label>产品配置</label>
                    </el-form-item>
                    <el-form-item label="期限类型">
                        <el-radio-group v-model="form.deadlineType">
                            <el-radio label="y">年</el-radio>
                            <el-radio label="q">季</el-radio>
                            <el-radio label="m">月</el-radio>
                            <el-radio label="d">日</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="期限范围">
                        <el-col :span="5">
                            <el-select v-model="form.deadlineMin" placeholder="">
                                <el-option v-for="item in 12" :label="item" :value="item"></el-option>
                            </el-select>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="5">
                            <el-select v-model="form.deadlineMax" placeholder="">
                                <el-option v-for="item in 12" :label="item" :value="item"></el-option>
                            </el-select>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="收取服务费类型">
                        <el-select v-model="form.serviceFeeType" placeholder="请选择">
                            <el-option key="1" label="按比例一次性收取" value="1"></el-option>
                            <el-option key="2" label="按贷款周期" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="服务费（年化）">
                        <el-input v-model="form.serviceFeeRate" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="逾期罚息利率">
                        <el-input v-model="form.lateChargeRate" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="是否可申请展期">
                        <el-radio-group v-model="form.isDelay">
                            <el-radio label="1">是</el-radio>
                            <el-radio label="0">否</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="还款方式">
                        <el-radio-group v-model="form.repaymentType">
                            <el-radio label="1">按月付息到期还本</el-radio>
                            <el-radio label="2">等额本金</el-radio>
                            <el-radio label="3">等额本息</el-radio>
                            <el-radio label="4">一次性还清本息</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <label>担保配置</label>
                    </el-form-item>
                    <el-form-item label="是否担保">
                        <el-radio-group v-model="form.isAssure">
                            <el-radio label="1">是</el-radio>
                            <el-radio label="0">否</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="担保公司">
                        <el-select v-model="form.assureComId" placeholder="请选择">
                            <el-option v-for="engicom in this.engiComOptions" :key="engicom.id"
                                       :label="engicom.companyName" :value="engicom.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="收取担保费类型">
                        <el-select v-model="form.assureFeeType" placeholder="请选择">
                            <el-option key="1" label="按比例一次性收取" value="按比例一次性收取"></el-option>
                            <el-option key="2" label="按贷款周期" value="按贷款周期"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="服务费比例">
                        <el-input v-model="form.assureServiceFeeRate" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="抵押类型">
                        <el-radio-group v-model="form.mortgageType">
                            <el-radio label="无"></el-radio>
                            <el-radio label="房抵"></el-radio>
                            <el-radio label="车抵"></el-radio>
                            <el-radio label="应收账款抵"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <el-button type="primary" @click="onReturn" class="btn-margin">取消</el-button>
                        <el-button type="primary" @click="save" class="btn-margin">保存</el-button>
                        <el-button type="primary" @click="saveAndSubmit" class="btn-margin">保存并提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: 'addFinancialProduct',
        data: function(){
            return {
                form: {
                    productNumber: null,
                    productName: null,
                    productType: null,
                    baseYearRate: null,
                    fundCompanyId: null,
                    deadlineTypeId: null,
                    lateChargeRate: null,
                    deadlineMin: null,
                    deadlineMax: null,
                    isDelay: null,
                    serviceFeeType: null,
                    serviceFeeRate: null,
                    repaymentType: null,
                    isAssure: null,
                    assureFeeType: null,
                    assureCompanyId: null,
                    assureServiceFeeRate: null,
                    mortgageType: null,
                    status: 0
                },
                fundComOptions: null,
                engiComOptions: null,
                dialogImageUrl: '',
                dialogVisible: false,
                isAgree: false
            }
        },
        created() {
            this.getDataList();
        },
        methods: {
            // 获取Table数据
            getDataList(){
                // 获取获取资金公司数据
                this.$axios.get('fc/options').then((response) => {
                    console.log(response.data.extend);
                    this.fundComOptions = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });

                // 获取担保公司数据
                this.$axios.get('api/engcom/list').then((response) => {
                    console.log(response.data.extend);
                    this.engiComOptions = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            onReturn() {
                this.$message.success('返回！');
                this.$router.push("business-manager-list")
            },
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
            saveAndSubmit(){
                this.$message.success('提交成功！');
                this.$router.push("financial-product-list");
            }

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
        padding-left: 200px;
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }

    .input-width{
        width: 500px;
    }
    .btn-margin{
        margin: 40px 40px 50px 0;

    }
</style>
