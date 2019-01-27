<template>
    <div class="login-wrap">
        <!-- logo -->
        <div class="logo"><img src="../../../static/img/login_logo.png"/></div>
        <!-- login -->
        <div class="ms-login" v-show=" 1 === 1">
            <div class="ms-title">用户登录</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')">
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <p class="forgot-tips">忘记密码？</p>
                <div class="login-btn">
                    <el-button type="primary" @click="loginSubmit">登录</el-button>
                </div>
                <p class="login-tips">Tips : 用户名和密码随便填。</p>
            </el-form>
        </div>
        <!-- activat -->
        <div class="ms-activat" v-show=" 1 === 0">
            <div class="ms-title">账户激活</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="请输入手机号码"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input placeholder="请输入验证码" v-model="ruleForm.username">
                        <template slot="append">获取验证码</template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="请输入重新输入密码"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
                </div>
                <p class="login-tips">Tips : 用户名和密码随便填。</p>
            </el-form>
        </div>
        <!-- forgot password -->
        <div class="ms-forgot" v-show=" 1===0">
            <div class="ms-title">修改密码</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="请输入手机号码"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input placeholder="请输入验证码" v-model="ruleForm.username">
                        <template slot="append">获取验证码</template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="请输入重新输入密码"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
                </div>
                <p class="login-tips">Tips : 用户名和密码随便填。</p>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                ruleForm: {
                    username: 'admin',
                    password: '123123'
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {

            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        localStorage.setItem('ms_username',this.ruleForm.username);
                        this.$router.push('/');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },

            loginSubmit() {
                console.log("123456");
                // this.$message.success('提交成功！');

                // delete
                // this.$axios.delete('http://192.168.1.98:8088/user/vuedelete',
                //     {params: {id:'12',name:'admin'}}).then(function (response) {
                //     console.log(response);
                // }).catch(function (error) {
                //     console.log(error);
                // });

                // get
                // this.$axios.get('http://192.168.1.98:8088/user/vueget',{params:{
                //     id: 12, name:'aswwaa'
                //     }}).then(function (response) {
                //     console.log(response);
                // }).catch(function (error) {
                //     console.log(error);
                // });

                this.$axios.post('http://192.168.1.98:8088/user/login',
                    this.qs.stringify({name: this.ruleForm.username,password: this.ruleForm.password})).then(function (response) {
                        localStorage.setItem('userInfo',response.data.extend.userInfo);
                }).catch(function (error) {
                    console.log(error);
                });

               console.log("wwwwwww: " + localStorage.getItem('userInfo'));
                // put
                // this.$axios.put('http://192.168.1.98:8088/user/vueput',
                //     this.qs.stringify({name: 'admin',password: 'wwww',id:'12'})).then(function (response) {
                //     console.log(response);
                // }).catch(function (error) {
                //     console.log(error);
                // });
            }

        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
        background-image: url("../../../static/img/login_bg.jpg");
        background-size: 100%;
    }
    .ms-title{
        width:100%;
        line-height: 50px;
        text-align: center;
        font-size:20px;
        color: #000000;
        border-bottom: 1px solid #ddd;
        background-color: #fff;
    }
    .ms-title:before{
        content: "";
        border-top: 1px solid #b5b5b5;
        display: block;
        position: absolute;
        width: 80px;
        top: 25px;
        left: 30px;
    }
    .ms-title:after{
        content: "";
        border-top: 1px solid #b5b5b5;
        display: block;
        position: absolute;
        width: 80px;
        top: 25px;
        right: 30px;
    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:350px;
        margin:-190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255,255,255, 0.3);
        overflow: hidden;
    }
    .ms-content{
        padding: 30px 30px;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
        margin-bottom: 10px;
    }
    .forgot-tips{
        font-size:12px;
        line-height:30px;
        color:#000000;
        text-align: right;
    }
    .login-tips{
        font-size:12px;
        line-height:30px;
        color:#fff;
    }


    .ms-activat{
        position: absolute;
        left:50%;
        top:50%;
        width:350px;
        margin:-190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255,255,255, 0.3);
        overflow: hidden;
    }


    .ms-forgot{
        position: absolute;
        left:50%;
        top:50%;
        width:350px;
        margin:-190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255,255,255, 0.3);
        overflow: hidden;
    }

    .logo {
        margin-top: 20px;
        margin-left: 25%;
    }

</style>
