<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>修改密码</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" :rules="rules" label-width="150px" class="child-company-box">
                                        <el-form-item label="原始密码：" prop="oldPassword">
                                            <el-input v-model="form.oldPassword" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="新密码：" prop="newPassword">
                                            <el-input v-model="form.newPassword" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="确认密码：" prop="newPassword2">
                                            <el-input v-model="form.newPassword2" style="width: 400px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2" @click="save">保存</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'change-password',
        data() {
            var newPassword = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('手机号不能为空'));
                }
                // 手机号码正则表达式校验
                var reg_phone=  /^1[34578]\d{9}$/;
                // 电话号码正则表达式校验
                var newPassword=  /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
                    if (!reg_phone.test(value) || !reg_tel.test(value)) {
                        callback(new Error('请输入正确的电话或手机号码'));
                    } else {
                        callback();
                    }
            };
            var newPassword2 = (rule, value, callback) => {

                if (value != this.form.newPassword) {
                    callback(new Error('新密码和确认密码不一样'));
                }
                callback();
            };
            return {
                name: localStorage.getItem('ms_username'),
                userId: localStorage.getItem('userId'),
                form:{
                    oldPassword: '',
                    newPassword: '',
                    newPassword2: '',
                },
                // 检验
                rules: {
                    oldPassword: [
                        { required: true, message: '请输入原始密码', trigger: 'blur' },
                    ],
                    newPassword: [
                        { required: true, message: '请输入新密码', trigger: 'blur' },
                    ],
                    newPassword2: [
                        { required: true, message: '请输入确认密码', trigger: 'blur' },
                        { validator:  newPassword2, trigger: 'blur' }
                    ],
                },

            }
        },
        // 监听器
        watch: {

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
            save(){
                this.$refs['form'].validate((valid) => {
                    if(!valid){
                        return;
                    }

                    let _than = this;
                    this.$axios.put('api/user/change_password',
                        this.qs.stringify(
                            {
                                id: this.userId,
                                newPassword: this.form.newPassword,
                                oldPassword: this.form.oldPassword,
                            }
                        )).then( res => {
                        console.log(res);
                        if(res.data.code === 100){
                            _than.$message.success("密码修改成功");
                            _than.$router.push("home2")
                            return;
                        }
                        _than.$message.success("密码修改失败");
                    }).catch(function (error) {
                        console.log(error);
                    });
                    console.log(this.form);
                }); 
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
    .child-company-box{
        width: 600px;
        margin: 0 auto;
    }
</style>
