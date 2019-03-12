<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>新增工程公司</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <!-- 绑定银行卡 -->
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" :rules="rules" label-width="200px">
                                        <el-form-item label="公司名称：" prop="companyName">
                                            <el-input v-model="form.companyName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="公司地址：" prop="companyAddress">
                                            <el-input v-model="form.companyAddress" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="企业法人：" prop="person">
                                            <el-input v-model="form.person" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="法人身份证号：" prop="personIdCard">
                                            <el-input v-model="form.personIdCard" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="信用代码证编号：" prop="creditCodeNumber">
                                            <el-input v-model="form.creditCodeNumber" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="上传附件：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="idCardSideFace"
                                                            :show-file-list="false">
                                                        <img v-if="form.idCardSideFace !== null" class="avatar" :src=" filesystem + form.idCardSideFace">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>法人身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="idCardSideBack"
                                                            :show-file-list="false">
                                                        <img v-if="form.idCardSideBack !== null" class="avatar" :src=" filesystem + form.idCardSideBack">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>法人身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="creditCodeCertificate"
                                                            :show-file-list="false">
                                                        <img v-if="form.creditCodeCertificate !== null" class="avatar" :src=" filesystem + form.creditCodeCertificate">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传信用代码证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="对公账户名称：" prop="publicAccountName">
                                            <el-input v-model="form.publicAccountName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="开户行：" prop="openAccountBank">
                                            <el-input v-model="form.openAccountBank" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="账号：" prop="bankAccount">
                                            <el-input v-model="form.bankAccount" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item>系统登录账户</el-form-item>
                                        <el-form-item label="资料员用户名：" prop="userName1">
                                            <el-input v-model="form.userName1" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="资料员手机号：" prop="phone1">
                                            <el-input v-model="form.phone1" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="审核员用户名：" prop="userName2">
                                            <el-input v-model="form.userName2" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="审核员手机号：" prop="phone2">
                                            <el-input v-model="form.phone2" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="企业用户授权书：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="authorizationFile"
                                                            :show-file-list="false">
                                                        <img v-if="form.authorizationFile !== null" class="avatar" :src=" filesystem + form.authorizationFile">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <!--<span>法人身份证正面</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
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
        name: 'engineering-company-add',
        data() {
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
                name: localStorage.getItem('ms_username'),
                labelPosition: "right",
                uploadPath: localStorage.getItem("uploadPath"),
                filesystem: localStorage.getItem("fileBasePath"),
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
                    publicAccountName: [
                        { required: true, message: '请输入对公账户名称', trigger: 'blur' },
                    ],
                    openAccountBank: [
                        { required: true, message: '请输入开户行', trigger: 'blur' },
                    ],
                    bankAccount: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
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

            }
        },
        components: {
        },
        computed: {
        },
        created(){
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            // 法人身份证正面上传成功回调函数
            idCardSideFace(response,file,files){
                console.log(response);
                this.form.idCardSideFace = response.extend.fileSystem.filePath;
            },
            // 法人身份证反面上传成功回调函数
            idCardSideBack(response,file,files){
                this.form.idCardSideBack = response.extend.fileSystem.filePath;
            },
            // 信用代码证上传成功回调函数
            creditCodeCertificate(response,file,files){
                this.form.creditCodeCertificate = response.extend.fileSystem.filePath;
            },
            // 授权证书上传成功回调函数
            authorizationFile(response,file,files){
                this.form.authorizationFile = response.extend.fileSystem.filePath;
            },

            // 保存数据
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
                            address: this.form.companyAddress,
                            person: this.form.person,
                            idCard: this.form.personIdCard,
                            idCardSideFace: this.form.idCardSideFace,
                            idCardSideBack: this.form.idCardSideBack,
                            creditCode: this.form.creditCodeNumber,
                            creditCodeCert: this.form.creditCodeCertificate,
                            publicAccountName: this.form.publicAccountName,
                            openAccountBank: this.form.openAccountBank,
                            bankAccount: this.form.bankAccount,
                            username1: this.form.userName1,
                            phone1: this.form.phone1,
                            username2: this.form.userName2,
                            phone2: this.form.phone2,
                            authFile: this.form.authorizationFile
                        }
                    )).then(function (response) {
                    console.log(response);
                    this.$router.push("engineeringcompany-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

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
    .el-upload--text{
        /*margin: 10px 10px;*/
        width: 120px;
        height: 120px;
    }
    .avatar-uploader{
        height: 120px;
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
        width: 120px;
        height: 120px;
        line-height: 120px;
        text-align: center;
    }
    .avatar {
        width: 120px;
        height: 120px;
        display: block;
    }
</style>
