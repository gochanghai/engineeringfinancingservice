<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item>企业信息</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box" style="color: #606266">
                <el-form ref="form" :model="form" label-width="180px">
                    <el-form-item label="公司名称">
                        {{company.companyName}}
                    </el-form-item>
                    <el-form-item label="公司地址">
                        {{company.companyAddress}}
                    </el-form-item>
                    <el-form-item label="企业法人">
                        {{company.enterpriseLegalPerson}}
                    </el-form-item>
                    <el-form-item label="法人身份证号">
                        {{ company.legalPersonIdCard}}
                    </el-form-item>
                    <el-form-item label="">
                        <el-col :span="11" style="color: #606266">
                            <div class="image-box">
                                <img class="img"
                                     :src="filesysip + company.legalPersonIdCardFile1"
                                     @click="handlePictureCardPreview(company.legalPersonIdCardFile1)"/>
                            </div>
                            法人身份证正面
                        </el-col>
                        <el-col :span="11" style="color: #606266">
                            <div class="image-box">
                                <img class="img"
                                     :src="filesysip + company.legalPersonIdCardFile2"
                                     @click="handlePictureCardPreview(company.legalPersonIdCardFile2)"/>
                            </div>
                            法人身份证反面
                        </el-col>
                    </el-form-item>
                    <el-form-item label="社会统一信用代码证编号">
                        {{ company.socialUnifiedCreditCodeNumber}}
                    </el-form-item>
                    <el-form-item label="社会统一信用代码证">
                        <div class="image-box">
                            <img class="img"
                                 :src="filesysip + company.socialUnifiedCreditCodeCertificate"
                                 @click="handlePictureCardPreview(company.socialUnifiedCreditCodeCertificate)"/>
                        </div>
                    </el-form-item>
                    <el-form-item label="对公账户名称">
                        {{company.publicAccountName}}
                    </el-form-item>
                    <el-form-item label="开户行">
                        {{company.openAccountBank}}
                    </el-form-item>
                    <el-form-item label="账号">
                        {{ company.bankAccount}}
                    </el-form-item>
                    <el-form-item label="" style="text-align: center">系统登录账户</el-form-item>
                </el-form>
            </div>
            <div style="color: #606266">
                <el-form :inline="true" :model="form" class="demo-form-inline" label-width="180px">
                    <el-row>
                        <el-form-item label="账号一(普通资料员)"></el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="用户名">
                            {{company.userName1}}
                        </el-form-item>
                        <el-form-item label="手机号码" label-width="100px">
                            {{company.phone1}}
                        </el-form-item>
                    </el-row>

                    <el-row>
                        <el-form-item label="账号二(有审核权限)"></el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="用户名">
                            {{company.userName2}}
                        </el-form-item>
                        <el-form-item label="手机号码" label-width="100px">
                            {{company.phone2}}
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="企业用户授权书">
                            <div class="image-box">
                                <img class="img"
                                     :src="filesysip + company.authorizationFile"
                                     @click="handlePictureCardPreview(company.authorizationFile)"/>
                            </div>
                        </el-form-item>
                    </el-row>
                </el-form>
            </div>
            <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
        </div>

    </div>
</template>

<script>
    export default {
        name: 'addProjectCreditApply',
        data: function(){
            return {
                filesysip: localStorage.getItem("fileBasePath"),
                companyId: localStorage.getItem('userInfoId'),
                company: null,
                form: {
                    companyName: null,
                    companyAddress: null,
                    enterpriseLegalPerson: null,
                    legalPersonIdCard: null,
                    legalPersonIdCardFileP: null,
                    legalPersonIdCardFile: null,
                    socialUnifiedCreditCodeNumber: null,
                    socialUnifiedCreditCodeCertificate: null,
                    publicAccountName: null,
                    openAccountBank: null,
                    bankAccount: null,
                    userName1: null,
                    phone1: null,
                    userName2: null,
                    phone2: null,
                    authorizationFile: null
                },
                dialogImageUrl: '',
                dialogVisible: false,
            }
        },
        created() {
            // this.getData();
            // 获取Table数据
            this.getComData();
        },
        methods: {
            // 获取企业信息
            getComData(id){
                let _than = this;
                this.$axios.get('api/engcom/company_info',{params:{
                        id: this.companyId
                    }}).then(function (response) {
                    console.log(response);
                    _than.company = response.data.extend.company;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            handlePictureCardPreview(filePath) {
                this.dialogImageUrl = 'http://192.168.1.89/' + filePath;
                this.dialogVisible = true;
            },

            onReturn() {
                this.$message.success('返回！');
                this.$router.push("business-manager-list")
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
        padding-left: 200px;
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
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

    .img{
        width: 100%;
        height: 100%;
    }
</style>
