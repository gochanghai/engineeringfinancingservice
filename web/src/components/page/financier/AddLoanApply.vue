<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item>新增放款申请</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="180px">
                    <el-form-item label="申请编号">
                        <el-input v-model="form.loanNo" class="input-width" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="申请日期">
                        <el-date-picker type="date" placeholder="选择日期" v-model="form.loanDate" value-format="yyyy-MM-dd"  disabled style="width: 210px;"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="申请人">
                        <el-input v-model="userName" disabled class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="项目">
                        <el-select v-model="form.pId" placeholder="请选择项目">
                            <el-option v-for="item in projectList" :key="item.id" :label="item.projectName" :value="item.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="申请放款金额">
                        <el-input v-model="form.loanAmount" class="input-width"></el-input> 万元
                    </el-form-item>

                    <el-form-item label="贷款周期">
                        <el-select v-model="form.loanCycle" placeholder="请选择项目" style="width: 100px">
                            <el-option v-for="index in 24" :key="index" :label="index" :value="index"></el-option>
                        </el-select> 期
                    </el-form-item>
                </el-form>
            </div>
            <el-form>
                <el-form-item>
            <div class="info-title">
                <el-button type="primary" style="float: right" @click="addPurchInfo">添加采购信息</el-button>
                <h4 style="color: #606266">采购信息</h4>
            </div>
            <div class="purch-info">
                <el-table :data="purchs" border class="table" ref="multipleTable">
                    <el-table-column label="采购合同编号" width="150" align="center">
                        <template slot-scope="scope">
                            <el-input v-model="scope.row.contractNo" placeholder="请输入内容"></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column label="采购合同附件" width="120" align="center">
                        <template slot-scope="scope">
                            <div class="upload-file-btn" @click="uploadFile(scope.$index,1)" >↑ ↑</div>
                        </template>
                    </el-table-column>
                    <el-table-column label="采购订单总金额" width="120" align="center">
                        <template slot-scope="scope">
                            <el-input v-model="scope.row.orderSumAmount" placeholder="请输入内容"></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column label="采购订单附件" width="120" align="center">
                        <template slot-scope="scope">
                            <div class="upload-file-btn" @click="uploadFile(scope.$index,2)" >↑ ↑</div>
                        </template>
                    </el-table-column>
                    <el-table-column label="采购发票总金额" width="120" align="center">
                        <template slot-scope="scope">
                            <el-input v-model="scope.row.invoiceSumAmount" placeholder="请输入内容"></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column label="采购发票附件" width="120" align="center">
                        <template slot-scope="scope">
                            <div class="upload-file-btn" @click="uploadFile(scope.$index,3)" >↑ ↑</div>
                        </template>
                    </el-table-column>
                    <el-table-column label="送货单附件" width="120" align="center">
                        <template slot-scope="scope">
                            <div class="upload-file-btn" @click="uploadFile(scope.$index,4)" >↑ ↑</div>
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
                    <el-table-column label="操作" width="100" align="center">
                        <template slot-scope="scope">
                            <el-button size="mini" @click="removePurchInfo(scope.$index)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <template>
                <!--<table style="border: 1px solid #cccccc; width: 100%">-->
                    <!--<thead>-->
                    <!--<tr style="height: 50px">-->
                        <!--<td style="width: 150px">采购合同编号</td>-->
                        <!--<td style="width: 120px">采购合同附件</td>-->
                        <!--<td style="width: 120px">采购订单总金额</td>-->
                        <!--<td style="width: 120px">采购订单附件</td>-->
                        <!--<td style="width: 120px">采购发票总金额</td>-->
                        <!--<td style="width: 120px">采购发票附件</td>-->
                        <!--<td style="width: 120px">送货单附件</td>-->
                        <!--<td style="width: 200px">供应商银行账户名称</td>-->
                        <!--<td style="width: 150px">供应商银行账号</td>-->
                        <!--<td>开户行</td>-->
                        <!--<td style="width: 100px">操作</td>-->
                    <!--</tr>-->
                    <!--</thead>-->
                    <!--<tbody>-->
                    <!--<tr style="height: 50px" v-for="(purch, index) in purchs">-->
                        <!--<td><el-input v-model="purch.purchaseContractNumber" placeholder="请输入内容"></el-input></td>-->
                        <!--<td>-->
                            <!--<div class="upload-file-btn" @click="uploadFile(index)" >↑ ↑</div>-->
                        <!--</td>-->
                        <!--<td><el-input v-model="purch.purchaseOrderTotalMoney" placeholder="请输入内容"></el-input></td>-->
                        <!--<td>-->
                            <!--<div class="upload-file-btn">↑ ↑</div>-->
                        <!--</td>-->
                        <!--<td><el-input v-model="purch.purchaseInvoiceTotalMoney" placeholder="请输入内容"></el-input></td>-->
                        <!--<td>-->
                            <!--<div class="upload-file-btn">↑ ↑</div>-->
                        <!--</td>-->
                        <!--<td>-->
                            <!--<div class="upload-file-btn">↑ ↑</div>-->
                        <!--</td>-->
                        <!--<td><el-input v-model="purch.supplierBankIdCard" placeholder="请输入内容"></el-input></td>-->
                        <!--<td><el-input v-model="purch.supplierBankAccountName" placeholder="请输入内容"></el-input></td>-->
                        <!--<td><el-input v-model="purch.supplierOpenAccountBank" placeholder="请输入内容"></el-input></td>-->
                        <!--<td> <el-button size="mini" @click="removePurchInfo(index)">删除</el-button></td>-->
                    <!--</tr>-->
                    <!--</tbody>-->
                <!--</table>-->
                </template>
            </div>
                </el-form-item>
                <el-form-item style="text-align: center">
                    <el-button type="primary" @click="onReturn" class="btn-margin">取消</el-button>
                    <el-button type="primary" @click="saveAndSubmit" class="btn-margin">保存并提交</el-button>
                </el-form-item>
            </el-form>
            <div>
               <el-form>

               </el-form>
            </div>
        </div>
        <!-- 编辑弹出框 -->
        <el-dialog title="添加附件" :visible.sync="uploadFlieVisible" width="15%" center="">
              <div style="text-align: center">
                  <el-upload
                    :action="uploadPath"
                    list-type="picture-card"
                    :file-list="fileList"
                    :on-success="uploadFileSuccess"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove">
                    <i class="el-icon-plus"></i>
                  </el-upload>
              </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary">确 定</el-button>
            </span>
        </el-dialog>
    </div>


</template>

<script>
    export default {
        name: 'AddLoanApply',
        data: function(){
            return {
                uploadPath: 'http://192.168.1.98:8088/filesystem/upload/',
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
                purchs:[{
                    contractNo: '1',contractFile:'1',orderSumAmount:'',orderFile:'1',
                    invoiceSumAmount: null, invoiceFile: null, deliveryBillFile:null, bankCardNo: null,
                    bankAccountName: null, openAccountBank: null
                },{
                    contractNo: '1',contractFile:'1',orderSumAmount:'',orderFile:'1',
                    invoiceSumAmount: null, invoiceFile: null, deliveryBillFile:null, bankCardNo: null,
                    bankAccountName: null, openAccountBank: null
                }],
                file_index: null,
                file_name: null,
                fileList: [],
                dialogImageUrl: '',
                dialogVisible: false,
                uploadFlieVisible: false,
                isAgree: false,
            }
        },
        created() {
            this.getProjectList();
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
                _that.uploadFlieVisible = false;
                _that.fileList = [];

            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },

            onReturn() {
                this.$message.success('返回！');
                this.$router.push("my-loan-apply-list")
            },
            save(){
                this.$message.success('提交成功！');
                this.$router.push("my-loan-apply-list")
            },
            //添加采购信息
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
                console.log(item)
            },
            // 上传附件
            uploadFile(index,fileName){
                this.file_index = index;
                this.file_name = fileName;
                this.uploadFlieVisible = true;
                console.log("准备上传文件: " + index + fileName)
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
        padding-left: 40px;
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }
    .input-width{
        width: 210px;
    }
    .btn-margin{
        margin: 40px 40px 50px 0;
    }
    .info-title{
        padding: 20px 30px;
        background-color: #C0C4CC;
        color: #00d1b2;
    }
    .purch-info{
        margin-top: 15px;
        width: 100%;
        height: 300px;
        border: 2px solid #eaeefb;
        color: #606266;
    }
    .purch-info-title{
        list-style: none;
        width: 100%;
        height: 50px;
        border: 1px solid #eaeefb;
    }
    table,td{
        border: 1px solid #eaeefb;
    }
    table{
        border-collapse: collapse;
        text-align: center;
    }

    /*附件样式*/
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 2px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        height: 30px;
        width: 50px;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 16px;
        color: #8c939d;
        width: 50px;
        height: 25px !important;
        line-height: 25px !important;
        text-align: center;
    }
    .avatar {
        width: 50px;
        height: 25px !important;
        display: block;
    }
    .el-upload--text{
        height: 100% !important;
    }

    .upload-file-btn{
        border: 1px solid #FF8208;
        height: 18px;
        font-size: 12px;
        margin: 0 10px;
        color: #ff8208;
    }

</style>
