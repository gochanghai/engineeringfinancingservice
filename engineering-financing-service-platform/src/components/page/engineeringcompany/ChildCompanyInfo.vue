<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>分公司信息</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" :rules="rules" label-width="150px" class="child-company-box">
                                        <el-form-item label="分公司名称：" prop="companyName">
                                            <el-input v-model="form.companyName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="分公司地址：" prop="address">
                                            <el-input v-model="form.address" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="分公司负责人：" prop="contactPerson">
                                            <el-input v-model="form.contactPerson" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="联系电话：" prop="phone">
                                            <el-input v-model="form.phone" style="width: 400px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1" @click="goBack">返回</div>
                        <!-- <div class="info-bottom-btn2" @click="save">保存</div> -->
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'branch-company-info',
        data() {
            var checkPhone = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('手机号不能为空'));
                }
                // 手机号码正则表达式校验
                var reg_phone=  /^1[34578]\d{9}$/;
                // 电话号码正则表达式校验
                var reg_tel=  /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
                    if (!reg_phone.test(value) || !reg_tel.test(value)) {
                        callback(new Error('请输入正确的电话或手机号码'));
                    } else {
                        callback();
                    }
            };
            return {
                form: {
                    companyName: null,
                    address: null,
                    contactPerson: null,
                    phone: null,
                },
                // 检验
                rules: {
                    companyName: [
                        { required: true, message: '请输入分公司名称', trigger: 'blur' },
                    ],
                    address: [
                        { required: true, message: '请输入分公司地址', trigger: 'blur' },
                    ],
                    contactPerson: [
                        { required: true, message: '请输入分公司负责人', trigger: 'blur' },
                    ],
                    phone: [
                        { required: true, message: '请输入联系电话', trigger: 'blur' },
                        { validator:  checkPhone, trigger: 'blur' }
                    ],
                },

            }
        },
        components: {
        },
        computed: {
        },
        created(){
            this.getData();
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            /**
             * 返回处理函数
             */
            goBack(){
                this.$router.go(-1);
            },

            getData(){
                this.$axios.get('api/company/branch',{params:{
                        id: this.$route.query.id
                    }}).then( res => {
                    console.log(res);
                    let data = res.data.extend.branchCompany;
                    if(data != null){
                        this.form = data;
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },

            /**
             * 保存处理函数
             */
            save(){
                this.$axios.post('api/company/branch',
                    this.qs.stringify(
                        {
                            companyName: this.form.companyName,
                            address: this.form.address,
                            contactPerson: this.form.contactPerson,
                            phone: this.form.phone,
                            pId: this.form.pId,
                        }
                    )).then( res => {
                    console.log(res);
                    this.$router.go(-1);
                }).catch(function (error) {
                    console.log(error);
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
