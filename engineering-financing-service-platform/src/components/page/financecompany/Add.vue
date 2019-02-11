<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>新增资金方</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <!-- 绑定银行卡 -->
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" :rules="rules" label-width="200px">
                                        <el-form-item label="企业全称：" prop="companyFullName">
                                            <el-input v-model="form.companyFullName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="联系人：" prop="contactPerson">
                                            <el-input v-model="form.contactPerson" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="联系电话：" prop="contactNumber">
                                            <el-input v-model="form.contactNumber" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="合作分行：" prop="cooperationBank">
                                            <el-input v-model="form.cooperationBank" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="分行地址：">
                                            <el-input v-model="form.cooperationBankAddress" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item>系统登录账户</el-form-item>
                                        <el-form-item label="用户名：" prop="userName">
                                            <el-input v-model="form.userName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="手机号：" prop="phone">
                                            <el-input v-model="form.phone" style="width: 400px"/>
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
        name: 'finance-company-add',
        data() {
            var checkFullName = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('企业全称不能为空'));
                }
                this.$axios.get('api/checkBakFullName',{params:{
                        bankFullName: value
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
                form: {
                    companyFullName: null,
                    contactPerson: null,
                    contactNumber: null,
                    cooperationBank: null,
                    cooperationBankAddress: null,
                    userName: null,
                    phone: null,
                    status: 0
                },
                rules: {
                    contactPerson: [
                        { required: true, message: '请输入公司名称', trigger: 'blur' },
                    ],
                    contactNumber: [
                        { required: true, message: '请输入企业法人', trigger: 'blur' },
                    ],
                    cooperationBankAddress: [
                        { required: true, message: '请输入地址', trigger: 'blur' },
                    ],
                    companyFullName: [
                        { required: true, message: '请输入企业名称', trigger: 'blur' },
                        { validator: checkFullName, trigger: 'blur' }
                    ],
                    userName: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { validator:  checkUserName, trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '请输入手机号', trigger: 'blur' },
                        { validator: checkPhone, trigger: 'blur' }
                    ],
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
            onReturn() {
                this.$message.success('返回！');
                this.$router.push("financial-company-list")
            },
            save(){
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                });
                let _that = this;
                this.$axios.post('fc/save',
                    this.qs.stringify(
                        {
                            companyFullName: this.form.companyFullName,
                            contactPerson: this.form.contactPerson,
                            contactNumber: this.form.contactNumber,
                            cooperationBank: this.form.cooperationBank,
                            cooperationBankAddress: this.form.cooperationBankAddress,
                            userName: this.form.userName,
                            phone: this.form.phone,
                            status: this.form.status
                        }
                    )).then(function (response) {
                    console.log(response);
                    this.$message.success('保存成功！');
                    _that.$router.push("financial-company-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);
            },
            saveAndSubmit(){
                this.$message.success('提交成功！');
                this.$router.push("my-credit-project-list")
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
