<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item>新增资金方</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" :rules="rules" label-width="180px">
                    <el-form-item label="企业全称" prop="companyFullName">
                        <el-input v-model="form.companyFullName" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="联系人" prop="contactPerson">
                        <el-input v-model="form.contactPerson" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" prop="contactNumber">
                        <el-input v-model="form.contactNumber" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="合作分行">
                        <el-input v-model="form.cooperationBank" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="分行地址">
                        <el-input v-model="form.cooperationBankAddress" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <label>系统登录账户</label>
                    </el-form-item>
                    <el-form-item label="用户名" prop="userName">
                        <el-input v-model="form.userName" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                        <el-input v-model="form.phone" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <el-button type="primary" @click="onReturn" class="btn-margin">取消</el-button>
                        <el-button type="primary" @click="save" class="btn-margin">保存</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: 'addFundCompany',
        data: function(){
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

                dialogImageUrl: '',
                dialogVisible: false,
                isAgree: false
            }
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
