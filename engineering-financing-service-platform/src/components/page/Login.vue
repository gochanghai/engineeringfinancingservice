<template>
    <div class="login-wrap">
        <!-- logo -->
        <div class="logo"><img src="../../../static/img/login_logo.png"/></div>
        <!-- login -->
        <div class="ms-login">
            <div class="ms-title">用户登录</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="请输入用户名/手机号">
                        <!--<el-button slot="prepend" icon="el-icon-lx-people"></el-button>-->
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="请输入密码" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')">
                        <!--<el-button slot="prepend" icon="el-icon-lx-lock"></el-button>-->
                    </el-input>
                    <!--<p class="forgot-tips">忘记密码？</p>-->
                </el-form-item>
                <p class="forgot-tips">忘记密码？</p>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                    <!--<el-button type="primary" @click="submit">登录</el-button>-->
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
                    // username: 'admin',
                    // password: '123123'
                    username: null,
                    password: null
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
                // this.$refs[formName].validate((valid) => {
                //     if (valid) {
                //         localStorage.setItem('ms_username',this.ruleForm.username);
                //         localStorage.setItem('userInfo',"20");
                //         localStorage.setItem('role',"00");
                //         this.$router.push('/');
                //     } else {
                //         console.log('error submit!!');
                //         return false;
                //     }
                    let _than = this;
                    this.$axios.post('api/login',
                        this.qs.stringify(
                            {
                                userName: this.ruleForm.username,
                                password: this.ruleForm.password,
                            }
                        )).then(function (res) {
                        console.log(res);
                        let resultDate = res.data.extend;
                        let code = res.data.code;
                        /**
                         * 判断用户是否登陆成功
                         */
                        if (code === 100) {
                            console.log('ssss');
                            localStorage.setItem("fileBasePath",resultDate.fileBasePath);
                            localStorage.setItem('ms_username',_than.ruleForm.username);
                            localStorage.setItem('userInfo', resultDate.userInfo);
                            console.log("roleId: " + resultDate.userInfo.roleId);
                            localStorage.setItem('role', resultDate.userInfo.roleId);
                            localStorage.setItem('userInfoId', resultDate.userInfo.userInfoId);
                            if(resultDate.userInfo.roleId === 3){
                                localStorage.setItem('user_name', resultDate.nickname);
                                localStorage.setItem('real_name', resultDate.realname);
                            }
                            // 设置附件上传地址
                            localStorage.setItem('uploadPath', 'http://192.168.1.98:8088/filesystem/upload/');
                            _than.$router.push("/");

                        }
                        /**
                         * 用户登录失败
                         */
                        if(code === 200){
                            _than.$message.success(res.data.extend);
                            console.log('wwwwwwwwwww');
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                    // localStorage.setItem('role', 0);
                    // _than.$router.push("/")
                // });
            },
            submit: function () {
                this.$router.push("/")
            },

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
        color: #66b1ff;
        /* 取消 */
        /*border-bottom: 1px solid #ddd;*/
        /* 修改 */
        font-family:MicrosoftYaHei;
        background-color: #ffffff;
    }
    .ms-title:before{
        content: "";
        border-top: 1px solid #66b1ff;
        display: block;
        position: absolute;
        width: 80px;
        top: 25px;
        left: 30px;
    }
    .ms-title:after{
        content: "";
        border-top: 1px solid #66b1ff;
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
        /* 修改 */
        background-color: #ffffff;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
        margin-bottom: 10px;
    }
    .login-tips{
        font-size:12px;
        line-height:30px;
        color: #888;
    }

    .logo {
        padding-top: 20px;
        padding-left: 20%;
        height: 168px;
    }
    .logo img{
        height: 50px;
    }

    .forgot-tips{
        /* 忘记密码 */
        font-size:12px;
        line-height:30px;
        color:#888;
        text-align: right;
    }
</style>
