<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>添加项目</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" :rules="rules" label-width="150px" class="child-company-box">
                                        <el-form-item label="项目合同名称：" prop="projectName">
                                            <el-input v-model="form.projectName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="合同编号：" prop="contractNo">
                                            <el-input v-model="form.contractNo" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="合同金额：" prop="contractAmount">
                                            <el-input v-model="form.contractAmount" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="所属分公司：" prop="phone">
                                            <!--<el-input v-model="form.comId" style="width: 400px"/>-->
                                            <el-select v-model="form.branchId" placeholder="请选择所属分公司" style="width: 400px">
                                                <el-option v-for="item in companyList" :key="item.id" :label="item.companyName" :value="item.id"/>
                                            </el-select>
                                        </el-form-item>
                                        <el-form-item label="商务经理：" prop="phone">
                                            <!--<el-input v-model="form.fId" style="width: 400px"/>-->
                                            <el-select v-model="form.userId" placeholder="请选择所属商务经理" style="width: 400px">
                                                <el-option v-for="type in this.bmList" :key="type.id" :label="type.name" :value="type.userId"/>
                                            </el-select>
                                        </el-form-item>
                                        <el-form-item label="预授信金额：" prop="shouldCreditAmount">
                                            <el-input v-model="form.shouldCreditAmount" style="width: 400px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1" @click="gotoReturn">返回</div>
                        <div class="info-bottom-btn2" @click="save">保存</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'add-project',
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
                name: localStorage.getItem('ms_username'),
                labelPosition: "right",
                companyId: localStorage.getItem('companyId'),
                companyList: [],
                bmList: [],
                form: {
                    projectName: '',
                    contractNo: '',
                    contractAmount: '0.00',
                    branchId: '',
                    shouldCreditAmount: '',
                    userId:'',
                },
                // 检验
                rules: {
                    projectName: [
                        { required: true, message: '请输入项目名称', trigger: 'blur' },
                    ],
                    contractNo: [
                        { required: true, message: '请输入合同编号', trigger: 'blur' },
                    ],
                    contractAmount: [
                        { required: true, message: '请输入合同金额', trigger: 'blur' },
                    ],
                    shouldCreditAmount: [
                        { required: true, message: '请输入授信金额', trigger: 'blur' },
                        // { validator:  checkPhone, trigger: 'blur' }
                    ],
                },

            }
        },
        components: {
        },
        computed: {
        },
        created(){
            this.getCompanyList();
            this.getBusinessManagerList()
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            getCompanyList(){
                let _than = this;
                this.$axios.get('api/company/branch/list',{params:{
                        companyId: this.companyId
                    }}).then(function (response) {
                    console.log(response);
                    _than.companyList = response.data.extend.list;
                    _than.loading= false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            getBusinessManagerList(){
                let _than = this;
                this.$axios.get('api/business/list',{params:{
                        companyId: this.companyId
                    }}).then(function (response) {
                    console.log(response);
                    _than.bmList = response.data.extend.list;
                    _than.loading= false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            save(){
                let _than = this;
                this.$axios.post('api/project',
                    this.qs.stringify(
                        {
                            projectName: this.form.projectName,
                            contractNo: this.form.contractNo,
                            contractAmount: this.form.contractAmount,
                            branchId: this.form.branchId,
                            creditAmount: this.form.shouldCreditAmount,
                            userId: this.form.userId,
                            companyId: this.companyId,
                        }
                    )).then((response)=> {
                    console.log(response);
                    _than.$router.push("project-list");
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
            gotoReturn(){
                this.$router.push("project-list");
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
