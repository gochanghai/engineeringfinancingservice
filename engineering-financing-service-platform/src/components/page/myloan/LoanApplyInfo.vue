<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>申请放款</span>
                    </div>
                    <!-- 放款申请信息 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <!-- 申请信息 -->
                                <div class="content-info-box">
                                    <el-form ref="form" label-width="100px" class="loan-apply-info" :model="form">
                                        <el-form-item label="申请编号：" style="margin-bottom: 0">{{form.applyNo}}</el-form-item>
                                        <el-form-item label="申请日期：" style="margin-bottom: 0">{{form.applyDate}}</el-form-item>
                                        <el-form-item label="申请人：" style="margin-bottom: 0">{{form.name}}</el-form-item>
                                        <el-form-item label="项目名称：" style="margin-bottom: 0">{{ form.projectName}}</el-form-item>
                                        <el-form-item label="申请金额：" style="margin-bottom: 0">{{form.applyAmount}}</el-form-item>
                                        <el-form-item label="贷款周期：" style="margin-bottom: 0">{{ form.loanCycle | 0}}期</el-form-item>
                                    </el-form>
                                </div>
                            </div>
                            <div class="prochase-title"><h4>采购信息</h4></div>
                            <div class="info-content">
                                <el-table :data="purchs" border class="table" ref="multipleTable">
                                    <el-table-column label="采购合同编号" prop="contractNo" align="center"/>
                                    <el-table-column label="采购合同附件" width="100" align="center">
                                        <template slot-scope="scope">
                                            <img v-if="scope.row.contractFile !== null" class="avatar" :src=" filesystem + scope.row.contractFile">
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="采购订单总金额" prop="orderAmount" width="120" align="center"/>
                                    <el-table-column label="采购订单附件" width="100" align="center">
                                        <template slot-scope="scope">
                                            <img v-if="scope.row.orderFile !== null" class="avatar" :src=" filesystem + scope.row.orderFile">
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="采购发票总金额" prop="invoiceAmount" width="120" align="center"/>
                                    <el-table-column label="采购发票附件" width="100" align="center">
                                        <template slot-scope="scope">
                                            <img v-if="scope.row.invoiceFile !== null" class="avatar" :src=" filesystem + scope.row.invoiceFile">
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="送货单附件" width="100" align="center">
                                        <template slot-scope="scope">
                                            <img v-if="scope.row.deliveryFile !== null" class="avatar" :src=" filesystem + scope.row.deliveryFile">
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="供应商银行账户名称" prop="accountName" align="center"/>
                                    <el-table-column label="供应商银行账号" prop="bankAccount" align="center"/>
                                    <el-table-column label="开户行" prop="bank" align="center"/>
                                </el-table>
                            </div>
                        </div>
                    </div>

                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div @click="gotoReturn" class="info-bottom-btn1">返回</div>
                        <!--<div class="info-bottom-btn2">保存</div>-->
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'apply-loan-info',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                filesystem: localStorage.getItem("fileBasePath"),
                id: this.$route.query.id,
                form:{
                    loanNo: null,
                    loanDate: '',
                    loanPerson: null,
                    pId: null,
                    loanAmount: null,
                    loanCycle: null,
                    status: 0
                },
                purchs:[
                    {
                    contractNo: '',contractFile:null,orderAmount:'',orderFile:null,
                    invoiceAmount: null, invoiceFile: null, deliveryFile:null, bankAccount: null,
                    accountName: null, bank: null
                }
                ],
            }
        },
        components: {
        },
        computed: {
        },
        created(){
            this.getProjectList();
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            // 获取项目数据
            getProjectList(){
                let _than = this;
                this.$axios.get('la/f/info',{params:{
                        id: this.id
                    }}).then(function (response) {
                    console.log(response);
                    _than.form = response.data.extend.applyInfo;
                    _than.purchs = response.data.extend.applyInfo.purchaseOrders;
                }).catch(function (error) {

                });
            },
            // 保存采购信息
            savePurchInfo() {
                let _that = this;
                let purchs = _that.purchs;
                console.log(purchs);
                if (purchs.length > 0) {
                    for( let index in purchs) {
                        console.log(index,purchs[index]);
                        _that.$axios.post('la/purchase/save',
                            _that.qs.stringify({
                                pId: _that.form.pId,
                                loanNo: _that.form.loanNo,
                                contractNo: purchs[index].contractNo,
                                contractFile:purchs[index].contractFile,
                                orderSumAmount:purchs[index].orderSumAmount,
                                orderFile: purchs[index].orderFile,
                                invoiceSumAmount: purchs[index].invoiceSumAmount,
                                invoiceFile: purchs[index].invoiceFile,
                                deliveryBillFile: purchs[index].deliveryBillFile,
                                bankCardNo: purchs[index].bankCardNo,
                                bankAccountName: purchs[index].bankAccountName,
                                openAccountBank: purchs[index].openAccountBank
                            })
                        ).then(function (response) {
                            console.log(response);
                            // this.$router.push("business-manager-list")
                        }).catch(function (error) {
                            console.log(error);
                        })
                    };
                }
            },
            // 保存
            saveAndSubmit(){
                let _than = this;
                this.$axios.post('la/save',
                    this.qs.stringify(
                        {
                            loanNo: this.form.loanNo,
                            fId: this.form.fId,
                            loanAmount: this.form.loanAmount,
                            pId: this.form.pId,
                            loanCycle: this.form.loanCycle,
                            loanDate: this.form.loanDate,
                            status: this.form.status,
                        }
                    )).then(function (response) {
                    console.log(response);
                }).catch(function (error) {
                    console.log(error);
                });
                // 保存采购信息
                this.savePurchInfo();

                this.$router.push("my-loan-apply-list")
            },
            // 返回按钮
            gotoReturn(){
                this.$router.push({
                    path:'my-loan-el',
                })
            }
        }
    }

</script>


<style>

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


    .content-info-box{
        padding: 20px 20px;
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
        background:rgba(255,178,103,1);
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
    .loan-apply-info{
        width: 600px;
        margin: 0 auto 0 0;
    }

    .prochase-title{
        display: block;
        height: 40px;
        /*padding: 20px 10px;*/
        padding-left: 30px;
        margin-bottom: 10px;
        background:rgba(226,226,226,1);
        /*color: #000;*/
    }
    .prochase-title>h4{
        width: 80px;
        height: 40px;
        line-height: 40px;
        text-align: center;
    }
    .prochase-btn-close{
        font-size: 30px;
        color: #c0c4cc;
        margin-right: 10px;
    }
    .prochase-btn-add{
        font-size: 30px;
        color: #ff8208
    }



    /*上传*/
    .el-upload--text{
        /*margin: 10px 10px;*/
        width: 40px;
        height: 40px;
    }
    .avatar-uploader{
        height: 40px;
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
        width: 40px;
        height: 40px;
        line-height: 40px;
        text-align: center;
    }
    .avatar {
        width: 40px;
        height: 40px;
        display: block;
    }

</style>
