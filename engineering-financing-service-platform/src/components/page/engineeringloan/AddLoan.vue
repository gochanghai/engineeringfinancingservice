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
                                <!-- 绑定银行卡 -->
                                <div class="content-info-box">
                                    <el-form ref="form" label-width="100px" class="loan-apply-box">
                                        <el-form-item label="申请编号：">
                                            <el-input v-model="form.loanNo" style="width: 200px"/>
                                        </el-form-item>
                                        <el-form-item label="申请日期：">
                                            <!--<el-input v-model="form.loanDate" style="width: 400px"/>-->
                                            <el-date-picker type="date" placeholder="选择日期" v-model="form.loanDate" value-format="yyyy-MM-dd"  disabled style="width: 200px;"/>
                                        </el-form-item>
                                        <el-form-item label="申请人：">
                                            <el-input v-model="userName" style="width: 200px"/>
                                        </el-form-item>
                                        <el-form-item label="项目名称：">
                                            <el-input v-model="name" style="width: 200px"/>
                                        </el-form-item>
                                        <el-form-item label="申请金额：">
                                            <el-input v-model="form.loanAmount" style="width: 200px"/> 万元
                                        </el-form-item>
                                        <el-form-item label="贷款周期：">
                                            <!--<el-input v-model="form.loanCycle" style="width: 200px"/> 期-->
                                            <el-select v-model="form.loanCycle" placeholder="请选择贷款周期" style="width: 200px">
                                                <el-option v-for="index in 24" :key="index" :label="index+' 期'" :value="index"></el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                            <div class="prochase-title"><h4>采购信息</h4></div>
                            <div class="info-content">
                                <el-table :data="purchs" border class="table" ref="multipleTable">
                                    <el-table-column label="采购合同编号" width="150" align="center">
                                        <template slot-scope="scope">
                                            <el-input v-model="scope.row.contractNo" placeholder="请输入内容"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="采购合同附件" width="120" align="center">
                                        <template slot-scope="scope">
                                            <!--<div class="upload-file-btn" @click="uploadFile(scope.$index,1)" >↑ ↑</div>-->
                                            <div @mouseenter="beforUploadListener(scope.$index,1)">
                                                <el-upload
                                                        class="avatar-uploader"
                                                        name="file"
                                                        :action="uploadPath"
                                                        :on-success="uploadFileSuccess"
                                                        :show-file-list="false">
                                                    <img v-if="scope.row.contractFile !== null" class="avatar" :src=" filesystem + scope.row.contractFile">
                                                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                </el-upload>
                                            </div>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="采购订单总金额" width="120" align="center">
                                        <template slot-scope="scope">
                                            <el-input v-model="scope.row.orderSumAmount" placeholder="请输入内容"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="采购订单附件" width="120" align="center">
                                        <template slot-scope="scope">
                                            <!--<div class="upload-file-btn" @click="uploadFile(scope.$index,2)" >↑ ↑</div>-->
                                            <div @mouseenter="beforUploadListener(scope.$index,2)">
                                                <el-upload
                                                        class="avatar-uploader"
                                                        name="file"
                                                        :action="uploadPath"
                                                        :on-success="uploadFileSuccess"
                                                        :show-file-list="false">
                                                    <img v-if="scope.row.orderFile !== null" class="avatar" :src=" filesystem + scope.row.orderFile">
                                                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                </el-upload>
                                            </div>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="采购发票总金额" width="120" align="center">
                                        <template slot-scope="scope">
                                            <el-input v-model="scope.row.invoiceSumAmount" placeholder="请输入内容"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="采购发票附件" width="120" align="center">
                                        <template slot-scope="scope">
                                            <!--<div class="upload-file-btn" @click="uploadFile(scope.$index,3)" >↑ ↑</div>-->
                                            <div @mouseenter="beforUploadListener(scope.$index,3)">
                                                <el-upload
                                                        class="avatar-uploader"
                                                        name="file"
                                                        :action="uploadPath"
                                                        :on-success="uploadFileSuccess"
                                                        :show-file-list="false">
                                                    <img v-if="scope.row.invoiceFile !== null" class="avatar" :src=" filesystem + scope.row.invoiceFile">
                                                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                </el-upload>
                                            </div>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="送货单附件" width="120" align="center">
                                        <template slot-scope="scope">
                                            <!--<i class="prochase-btn-close el-icon-lx-upload" @click="uploadFile(scope.$index,4)"></i>-->
                                            <div @mouseenter="beforUploadListener(scope.$index,4)">
                                                <el-upload
                                                        class="avatar-uploader"
                                                        name="file"
                                                        :action="uploadPath"
                                                        :on-success="uploadFileSuccess"
                                                        :show-file-list="false">
                                                    <img v-if="scope.row.deliveryBillFile !== null" class="avatar" :src=" filesystem + scope.row.deliveryBillFile">
                                                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                </el-upload>
                                            </div>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="供应商银行账户名称" width="200" align="center">
                                        <template slot-scope="scope">
                                            <el-input v-model="scope.row.bankAccountName" placeholder="请输入内容"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="供应商银行账号" width="150" align="center">
                                        <template slot-scope="scope">
                                            <el-input v-model="scope.row.bankCardNo" placeholder="请输入内容"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="开户行" align="center">
                                        <template slot-scope="scope">
                                            <el-input v-model="scope.row.openAccountBank" placeholder="请输入内容"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="操作" width="100" align="left">
                                        <template slot-scope="scope">
                                            <i class="prochase-btn-close el-icon-lx-roundclose" @click="removePurchInfo(scope.$index)"></i>
                                            <i v-if="purchs.length === scope.$index+1"
                                               class="prochase-btn-add el-icon-lx-roundadd" @click="addPurchInfo"></i>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </div>
                        </div>
                    </div>

                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">保存</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'apply-loan',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                uploadPath: localStorage.getItem("uploadPath"),
                filesystem: localStorage.getItem("fileBasePath"),
                userName: localStorage.getItem('user_name'),
                userId: localStorage.getItem('userInfoId'),
                projectList: [],
                form:{
                    fId: localStorage.getItem('userInfoId'),
                    loanNo: null,
                    loanDate: new Date(),
                    loanPerson: null,
                    pId: null,
                    loanAmount: null,
                    loanCycle: null,
                    status: 0
                },
                purchs:[
                    {
                    contractNo: '',contractFile:null,orderSumAmount:'',orderFile:null,
                    invoiceSumAmount: null, invoiceFile: null, deliveryBillFile:null, bankCardNo: null,
                    bankAccountName: null, openAccountBank: null
                }
                ],
                file_index: null,
                file_name: null,
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
                this.$axios.get('credit/project/list',{params:{
                        id: this.userId
                    }}).then(function (response) {
                    console.log(response);
                    _than.projectList = response.data.extend.list;
                    _than.form.loanNo = response.data.extend.applyNo;
                }).catch(function (error) {

                });
            },

            // 添加采购信息
            addPurchInfo(){
                //this.addVisible = true;
                this.purchs.push({
                    bankAccountName: null,
                    bankCardNo: null,
                    contractFile: null,
                    contractNo: null,
                    deliveryBillFile: null,
                    invoiceFile: null,
                    invoiceSumAmount: null,
                    openAccountBank: null,
                    orderFile: null,
                    orderSumAmount: null,
                });
            },
            // 删除采购信息
            removePurchInfo(item) {
                if (item !== -1) {
                    this.purchs.splice(item, 1)
                }
                // 如果全部删除了，就始终添加一条新的采购信息
                if (this.purchs.length === 0){
                    this.addPurchInfo();
                }
            },

            // 上传附件
            beforUploadListener(index,fileName){
                this.file_index = index;
                this.file_name = fileName;
                console.log("准备上传文件: " + index + fileName)
            },
            // 附件上传成功回调函数
            uploadFileSuccess(res,file, fileList) {
                let _that = this;
                if(_that.file_name === 1){
                    _that.purchs[_that.file_index].contractFile = res.extend.fileSystem.filePath;
                }else if (_that.file_name === 2) {
                    _that.purchs[_that.file_index].orderFile = res.extend.fileSystem.filePath;
                }else if (_that.file_name === 3) {
                    _that.purchs[_that.file_index].invoiceFile = res.extend.fileSystem.filePath;
                }else if (_that.file_name === 4) {
                    _that.purchs[_that.file_index].deliveryBillFile = res.extend.fileSystem.filePath;
                }

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
    .loan-apply-box{
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
        color: rgba(255,178,103,1);
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
