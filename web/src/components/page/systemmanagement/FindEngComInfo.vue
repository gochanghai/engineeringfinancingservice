<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item>新增工程公司</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div style="padding-bottom: 20px; color: #606266">
                <div style="width: 100%; height: 40px">担保授信信息</div>
                <div style="width: 100%; height: 30px">
                    资金方：{{form.companyFullName}}
                </div>
                <div style="width: 100%; height: 30px">
                    担保授信额度：{{form.creditAmount}}.00万
                </div>
            </div>
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="180px">
                    <el-form-item label="公司名称">
                        <el-input v-model="form.companyName" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="公司地址">
                        <el-input v-model="form.companyAddress" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="企业法人">
                        <el-input v-model="form.person" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="法人身份证号">
                        <el-input v-model="form.personIdCard" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="">
                        <el-col :span="11" style="color: #606266">
                            <img :src="fileBasePath + form.idCardSideFace" width="150px" height="150px"/>
                            <p>法人身份证正面</p>
                        </el-col>
                        <el-col :span="11" style="color: #606266">
                            <img :src="fileBasePath + form.idCardSideBack" width="150px" height="150px"/>
                            <p>法人身份证反面</p>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="社会统一信用代码证编号">
                        <el-input v-model="form.creditCodeNumber" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="社会统一信用代码证">
                        <img :src="fileBasePath + form.creditCodeCertificate" width="150px" height="150px"/>
                    </el-form-item>
                    <el-form-item label="对公账户名称">
                        <el-input v-model="form.publicAccountName" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="开户行">
                        <el-input v-model="form.openAccountBank" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="账号">
                        <el-input v-model="form.bankAccount" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="" style="text-align: center">系统登录账户</el-form-item>
                </el-form>
            </div>
            <div>
                <el-form :inline="true" :model="form" class="demo-form-inline" label-width="180px">
                    <el-row>
                        <el-form-item label="账号一(普通资料员)"></el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="用户名">
                            <el-input v-model="form.userName1" placeholder="用户名"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号码" label-width="100px">
                            <el-input v-model="form.phone1" placeholder="手机号码"></el-input>
                        </el-form-item>
                    </el-row>

                    <el-row>
                        <el-form-item label="账号二(有审核权限)"></el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="用户名">
                            <el-input v-model="form.userName2" placeholder="用户名"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号码" label-width="100px">
                            <el-input v-model="form.phone2" placeholder="手机号码"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="企业用户授权书">
                            <img :src="fileBasePath + form.authorizationFile" width="150px" height="150px"/>
                        </el-form-item>
                    </el-row>
                </el-form>
                <el-row style="padding-left: 200px">
                    <el-button type="warning" @click="onReturn" class="btn-margin">返回</el-button>
                    <!--<el-button type="primary" @click="edit" class="btn-margin">编辑</el-button>-->
                </el-row>
            </div>

        </div>

    </div>
</template>

<script>
    export default {
        name: 'FindEngComInfo',
        data: function(){
            return {
                fileBasePath:localStorage.getItem("fileBasePath"),
                form: {
                    id:this.$route.query.id,
                    companyName: null,
                    companyAddress: null,
                    person: null,
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
                isAgree: false
            }
        },
        created() {
            this.getDataList();
        },
        methods: {
            // 获取Table数据
            getDataList(){
                this.$axios.get('api/engcom/company_info', {
                    params: {
                    id: this.form.id
                    }
                }).then((response) => {
                    console.log(response.data.extend);
                    this.form = response.data.extend.company;
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
                this.$router.push("engineeringcompany-list")
            },
            save(){
                this.$message.success('保存成功！');
                this.$axios.post('http://192.168.1.98:8088/ec/save',
                    this.qs.stringify(
                        {
                            companyName: this.form.companyName,
                            companyAddress: this.form.companyAddress,
                            enterpriseLegalPerson: this.form.enterpriseLegalPerson,
                            legalPersonIdCard: this.form.legalPersonIdCard,
                            legalPersonIdCardFile1: this.form.legalPersonIdCardFileP,
                            legalPersonIdCardFile2: this.form.legalPersonIdCardFile,
                            socialUnifiedCreditCodeNumber: this.form.socialUnifiedCreditCodeNumber,
                            socialUnifiedCreditCodeCertificate: this.form.socialUnifiedCreditCodeCertificate,
                            publicAccountName: this.form.publicAccountName,
                            openAccountBank: this.form.openAccountBank,
                            bankAccount: this.form.bankAccount,
                            userName1: this.form.userName1,
                            phone1: this.form.phone1,
                            userName2: this.form.userName2,
                            phone2: this.form.phone2,
                            authorizationFile: this.form.authorizationFile
                        }
                    )).then(function (response) {
                    console.log(response);
                    this.$router.push("engineeringcompany-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
            edit(){
                this.$message.success('提交成功！');
                this.$router.push("engineeringcompany-list")
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
        margin: 40px 50px;
    }
</style>
