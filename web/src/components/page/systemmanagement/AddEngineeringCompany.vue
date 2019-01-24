<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item>新增工程公司</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" :rules="rules" label-width="180px">
                    <el-form-item label="公司名称" prop="companyName">
                        <el-input v-model="form.companyName" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="公司地址" prop="companyAddress">
                        <el-input v-model="form.companyAddress" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="企业法人" prop="person">
                        <el-input v-model="form.person" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="法人身份证号" prop="personIdCard">
                        <el-input v-model="form.personIdCard" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="">
                        <el-col :span="11" style="color: #606266">
                            <el-upload
                                action="http://192.168.1.98:8088/filesystem/upload/"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadIdCard1Btn}"
                                name="file"
                                :on-success="handleSuccess0"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            法人身份证正面
                        </el-col>
                        <el-col :span="11" style="color: #606266">
                            <el-upload
                                action="http://192.168.1.98:8088/filesystem/upload/"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadIdCard2Btn}"
                                name="file"
                                :on-success="handleSuccess1"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            法人身份证反面
                        </el-col>
                    </el-form-item>
                    <el-form-item label="社会统一信用代码证编号" prop="creditCodeNumber">
                        <el-input v-model="form.creditCodeNumber" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="社会统一信用代码证">
                        <el-upload
                            action="http://192.168.1.98:8088/filesystem/upload/"
                            list-type="picture-card"
                            :class="{disabled: isShowUploadSUCCCBtn}"
                            name="file"
                            :on-success="handleSuccess2"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove">
                            <i class="el-icon-plus"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="对公账户名称" prop="publicAccountName">
                        <el-input v-model="form.publicAccountName" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="开户行" prop="openAccountBank">
                        <el-input v-model="form.openAccountBank" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="账号" prop="bankAccount">
                        <el-input v-model="form.bankAccount" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="" style="text-align: center">系统登录账户</el-form-item>
                </el-form>
            </div>
            <div>
                <el-form :inline="true" :model="form" :rules="rules" class="demo-form-inline" label-width="180px">
                    <el-row>
                        <el-form-item label="账号一(普通资料员)"></el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="用户名" prop="userName1">
                            <el-input v-model="form.userName1" placeholder="用户名"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号码" prop="phone1" label-width="100px">
                            <el-input v-model="form.phone1" placeholder="手机号码"></el-input>
                        </el-form-item>
                    </el-row>

                    <el-row>
                        <el-form-item label="账号二(有审核权限)"></el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="用户名" prop="userName2">
                            <el-input v-model="form.userName2" placeholder="用户名"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号码" prop="phone2" label-width="100px">
                            <el-input v-model="form.phone2" placeholder="手机号码"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row>
                        <el-form-item label="企业用户授权书">
                            <el-upload
                                action="http://192.168.1.98:8088/filesystem/upload/"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadAuthFile}"
                                name="file"
                                :on-success="handleSuccess"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                        </el-form-item>
                    </el-row>
                </el-form>
                <el-row style="padding-left: 200px">
                    <el-button type="primary" @click="onReturn" class="btn-margin">返回</el-button>
                    <el-button type="primary" @click="save" class="btn-margin">保存</el-button>
                </el-row>
            </div>
        </div>
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'FindEngComInfo',
        data: function(){
            var checkCodeNumber = (rule, value, callback) => {
                console.log("社会统一信用代码证编号: check");
                if (!value) {
                    return callback(new Error('社会统一信用代码证编号不能为空'));
                }
                // 正则表达式校验
                var reg = /^[\dA-Z]{18}$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的社会统一信用代码证编号'));
                    } else {
                        this.$axios.get('api/checkCreditCode',{params:{
                                creditCode: value
                            }}).then(function (response) {
                            console.log(response);
                            if (response.data.extend.result == 1) {
                                callback(new Error(response.data.extend.message));
                            } else {
                                callback();
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                }, 1000);
            };
            var checkIdCard = (rule, value, callback) => {
                console.log("身份证号不能为空: check");
                if (!value) {
                    return callback(new Error('身份证号不能为空'));
                }
                // 正则表达式校验
                var reg=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的身份号'));
                    } else {
                        // if (value < 0) {
                        //     callback(new Error('产值只能0<= 产值'));
                        // } else {
                            callback();
                        // }
                    }
                }, 1000);
            };
            var checkUserName = (rule, value, callback) => {
                console.log("checkOutputValue: check");
                // 去除所有空格
                // value = value.replaceAll("\\s+", "");
                if (!value) {
                    return callback(new Error('用户名不能为空'));
                }
                // 正则表达式校验
                var reg=/^[a-zA-Z0-9_-]{4,16}$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('用户名只能由字母，数字，下划线，减号组成'));
                    } else {
                        this.$axios.get('api/checkUserName',{params:{
                                userName: value
                            }}).then(function (response) {
                            console.log(response);
                            if (response.data.extend.result == 1) {
                                callback(new Error(response.data.extend.message));
                            } else {
                                callback();
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                }, 1000);
            };
            var checkPhone = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('手机号不能为空'));
                }
                // 正则表达式校验
                var reg=/^1[34578]\d{9}$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的手机号'));
                    } else {
                        this.$axios.get('api/checkPhone',{params:{
                                phone: value
                            }}).then(function (response) {
                            console.log(response);
                            if (response.data.extend.result == 1) {
                                callback(new Error(response.data.extend.message));
                            } else {
                                callback();
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                }, 1000);
            };
            return {
                isShowUploadIdCard1Btn: false,
                isShowUploadIdCard2Btn: false,
                isShowUploadSUCCCBtn: false,
                isShowUploadAuthFile: false,
                form: {
                    companyName: null,
                    companyAddress: null,
                    person: null,
                    personIdCard: null,
                    idCardSideFace: null,
                    idCardSideBack: null,
                    creditCodeNumber: null,
                    creditCodeCertificate: null,
                    publicAccountName: null,
                    openAccountBank: null,
                    bankAccount: null,
                    userName1: null,
                    phone1: null,
                    userName2: null,
                    phone2: null,
                    authorizationFile: null
                },
                rules: {
                    companyName: [
                        { required: true, message: '请输入公司名称', trigger: 'blur' },
                    ],
                    person: [
                        { required: true, message: '请输入企业法人', trigger: 'blur' },
                    ],
                    companyAddress: [
                        { required: true, message: '请输入地址', trigger: 'blur' },
                    ],
                    creditCodeNumber: [
                        { required: true, message: '请输入统一信用代码', trigger: 'blur' },
                        { validator:  checkCodeNumber, trigger: 'blur' }
                    ],
                    personIdCard: [
                        { required: true, message: '请输入法人身份证号', trigger: 'blur' },
                        { validator: checkIdCard, trigger: 'blur' }
                    ],
                    userName1: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { validator:  checkUserName, trigger: 'blur' }
                    ],
                    phone1: [
                        { required: true, message: '请输入手机号', trigger: 'blur' },
                        { validator: checkPhone, trigger: 'blur' }
                    ],
                    userName2: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { validator:  checkUserName, trigger: 'blur' }
                    ],
                    phone2: [
                        { required: true, message: '请输入手机号', trigger: 'blur' },
                        { validator: checkPhone, trigger: 'blur' }
                    ]
                },

                dialogImageUrl: '',
                dialogVisible: false,
                isAgree: false
            }
        },
        methods: {

            handleSuccess0(response,file,files){
                this.form.idCardSideFace = response.extend.fileSystem.filePath;
                this.isShowUploadIdCard1Btn = true;
                console.log("response::: " + response);
            },
            handleSuccess1(response,file,files){
                this.form.idCardSideBack = response.extend.fileSystem.filePath;
                this.isShowUploadIdCard2Btn = true;
                console.log("response::: " + response);
            },
            handleSuccess2(response,file,files){
                this.form.creditCodeCertificate = response.extend.fileSystem.filePath;
                this.isShowUploadSUCCCBtn = true;
                console.log("response::: " + response);
            },
            handleSuccess(response,file,files){
                this.form.authorizationFile = response.extend.fileSystem.filePath;
                this.isShowUploadAuthFile = true;
                console.log("response::: " + response);
            },

            handleRemove(file, fileList) {
                this.isShowUploadAuthFile = true;
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
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                });
                this.$message.success('保存成功！');
                this.$axios.post('api/engcom/save',
                    this.qs.stringify(
                        {
                            companyName: this.form.companyName,
                            companyAddress: this.form.companyAddress,
                            person: this.form.person,
                            personIdCard: this.form.personIdCard,
                            idCardSideFace: this.form.idCardSideFace,
                            idCardSideBack: this.form.idCardSideBack,
                            creditCodeNumber: this.form.creditCodeNumber,
                            creditCodeCertificate: this.form.creditCodeCertificate,
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
            saveAndSubmit(){
                this.$message.success('提交成功！');
                this.$router.push("engineeringcompany-list")
            }

        }
    }
</script>

<style>

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

    .disabled .el-upload--picture-card {
        display: none;
    }
</style>
