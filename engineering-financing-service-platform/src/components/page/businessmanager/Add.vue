<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>新增商务经理</span>
                    </div>
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <!-- 添加商务经理基本参数 -->
                                <div class="content-info-box">
                                    <el-form ref="form" label-width="200px" status-icon :model="form" :rules="rules">
                                        <el-form-item label="姓名：" prop="name">
                                            <el-input v-model="form.name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="身份证号：" prop="idCard">
                                            <el-input v-model="idCard" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="手机号码：" prop="phone">
                                            <el-input v-model="form.phone" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="从业日期：" prop="jobDate">
                                            <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="jobDate" style="width: 230px;"></el-date-picker>
                                            从业年限 <el-input v-model="form.jobYearNumber" style="width: 100px" placeholder="0.00年"/>
                                        </el-form-item>
                                        <el-form-item label="与公司合作日期：" prop="cooperateDate">
                                            <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="cooperateDate" style="width: 230px;"></el-date-picker>
                                            合作年限 <el-input v-model="form.cooperateYearNumber" style="width: 100px" placeholder="0.00年"/>
                                        </el-form-item>
                                        <el-form-item label="所属分公司：">
                                            <!--<el-input v-model="form.childCompanyId" style="width: 400px"/>-->
                                            <el-select v-model="form.childCompanyId" placeholder="请选择" style="width: 400px">
                                                <el-option v-for="(company,index) in companyList" :key="index" :label="company.companyName" :value="company.id"></el-option>
                                            </el-select>
                                        </el-form-item>
                                        <el-form-item label="评级：">
                                            <el-select v-model="form.rate" placeholder="请选择" style="width: 400px">
                                                <el-option v-for="(rate,index) in rates" :key="index" :label="rate.name" :value="rate.name"></el-option>
                                            </el-select>
                                        </el-form-item>
                                        <el-form-item label="评级说明：">
                                            <el-input type="textarea" rows="5" v-model="form.rateDesc" style="width: 400px"></el-input>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1" @click="onReturn">返回</div>
                        <div class="info-bottom-btn2" @click="save">保存</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'business-add',
        data() {
            var checkIdCard = (rule, value, callback) => {
                console.log("checkOutputValue: check");
                if (!value) {
                    return callback(new Error('身份证号不能为空'));
                }
                // 正则表达式校验
                var reg=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('身份证号不正确'));
                    } else {
                        this.$axios.get('api/checkIdCard',{params:{
                                idCard: value
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
                console.log("checkOutputValue: check");
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
                rates:[
                    {id:0,name:'A'},
                    {id:1,name:'B'},
                    {id:2,name:'C'},
                    {id:3,name:'D'},
                    {id:4,name:'E'}
                ],
                companyId: localStorage.getItem('userInfoId'),
                companyList: null,
                idCard: null,
                jobDate: null,
                cooperateDate: null,
                form: {
                    comId: this.$route.query.id,
                    name: null,
                    gender: null ,
                    idCard: null,
                    birthdate: null,
                    phone: null,
                    jobDate: null,
                    jobYearNumber: null,
                    cooperateDate: null,
                    cooperateYearNumber: null,
                    marriageStatus: null,
                    childCompanyId: null,
                    rate: null,
                    rateDesc: null,
                    status: 0,
                },
                // 检验
                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '请输入手机号', trigger: 'blur' },
                        { validator:  checkPhone, trigger: 'blur' }
                    ],
                    idCard: [
                        { required: true, message: '请输入身份证号', trigger: 'blur' },
                        { validator: checkIdCard, trigger: 'blur' }
                    ],
                    jobDate: [
                        { required: true, message: '请输入从业日期', trigger: 'blur' },
                    ],
                    cooperateDate: [
                        { required: true, message: '请输入与公司合作日期', trigger: 'blur' },
                    ]
                },
            }

        },
        // 监听器
        watch: {
            idCard:function() {
                let id_card = this.idCard;
                this.form.idCard = id_card;
                if (this.idCard.length === 18){
                    this.form.birthdate = id_card.substr(6, 4) + "-" + id_card.substr(10, 2) + "-" + id_card.substr(12, 2);
                    if (parseInt(id_card.substr(14, 3)) % 2 === 0) {
                        this.form.gender = "F"
                    }else {
                        this.form.gender = "M"
                    }
                }
            },
            jobDate:function() {
                this.form.jobDate = this.jobDate;
                this.form.jobYearNumber = new Date().getFullYear() - this.jobDate.substr(0, 4);
                console.log(new Date().getFullYear());
            },
            cooperateDate:function() {
                this.form.cooperateDate = this.cooperateDate;
                this.form.cooperateYearNumber = new Date().getFullYear() - this.cooperateDate.substr(0, 4);
            },
        },
        components: {
        },
        computed: {
        },
        created(){
            this.getCompangList();
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            //分公司列表数据
            getCompangList(){
                let _than = this;
                this.$axios.get('api/company/branch/list',{params:{
                        companyId: localStorage.getItem('companyId'),
                    }}).then(function (res) {
                    console.log(res);
                    _than.companyList = res.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 返回
            onReturn() {
                this.$router.go(-1);
            },
            // 保存
            save(){
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                });
                this.$axios.post('api/business',
                    this.qs.stringify(
                        {
                            companyId: localStorage.getItem('companyId'),
                            name: this.form.name,
                            gender: this.form.gender,
                            idCard: this.form.idCard,
                            birthdate: this.form.birthdate,
                            phone: this.form.phone,
                            jobDate: this.form.jobDate,
                            jobYearNumber: this.form.jobYearNumber,
                            coopDate: this.form.cooperateDate,
                            coopYearNumber: this.form.cooperateYearNumber,
                            branchId: this.form.childCompanyId,
                            rate: this.form.rate,
                            rateDesc: this.form.rateDesc,
                            status: this.form.status,
                        }
                    )).then(res => {
                    console.log(res);
                    this.$router.push("business-manager-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);
            },
            // 保存并提交
            saveAndSubmit(){
                this.form.status = 1;
                this.save();
                this.$router.push("business-manager-list")
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
        background:rgba(255,178,103,1);
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
