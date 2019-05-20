<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>公司详情</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <!-- 绑定银行卡 -->
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" :rules="rules" label-width="200px">
                                        <el-form-item label="授信资方：">
                                            <el-input v-model="form.financeName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="授信额度：">
                                            <el-input v-model="form.creditAmount" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="公司名称：">
                                            <el-input v-model="form.companyName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="公司地址：">
                                            <el-input v-model="form.address" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="企业法人：">
                                            <el-input v-model="form.person" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="法人身份证号：">
                                            <el-input v-model="form.idCard" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="信用代码证编号：">
                                            <el-input v-model="form.creditCode" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="上传附件：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img class="avatar" :src=" filesystem + form.idCardSideFace">
                                                    <span>法人身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img class="avatar" :src=" filesystem + form.idCardSideBack">
                                                    <span>法人身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img class="avatar" :src=" filesystem + form.creditCodeCert">
                                                    <span>信用代码证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="对公账户名称：">
                                            <el-input v-model="form.publicAccountName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="开户行：">
                                            <el-input v-model="form.openAccountBank" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="账号：">
                                            <el-input v-model="form.bankAccount" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="企业用户授权书：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                   <img class="avatar" :src=" filesystem + form.authFile">
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
                        <div class="info-bottom-btn1" @click="goBack">返回</div>
                        <div class="info-bottom-btn2" v-if="form.creditAmount == '' || form.creditAmount == null" @click="creditVisible = true">立即授信</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <!-- 授信弹框 -->
        <el-dialog :visible.sync="creditVisible" center width="30%">
            <el-form ref="form" :model="creditForm" label-width="100px">
                <el-form-item label="资金方:">
                    <!--<el-input v-model="form.childCompanyId" style="width: 400px"/>-->
                    <el-select v-model="creditForm.fcompanyId" placeholder="请选择授信资方" style="width: 300px">
                        <el-option v-for="(item,index) in financeList" :key="index" :label="item.companyName" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="授信金额:">
                    <el-input v-model="creditForm.amount" placeholder="请输入授信金额" style="width: 300px"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="creditVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveCredit">确定</el-button>
            </span>
        </el-dialog>
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
                    companyName: '',
                    address: '',
                    person: '',
                    idCard: '',
                    idCardSideFace: '',
                    idCardSideBack: '',
                    creditCode: '',
                    creditCodeCert: '',
                    publicAccountName: '',
                    openAccountBank: '',
                    bankAccount: '',
                    authFile: '',
                    creditAmount: '',
                    financeName: ''
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
                creditVisible: false,
                creditForm:{
                    amount: '',
                    fcompanyId: '',
                },
                financeList: []               

            }
        },
        watch:{
            creditVisible: function(val){
                if(val){
                    this.getFinanceList();
                }
            }
        },
        components: {
        },
        computed: {
        },
        created() {
            this.getDataInfo();
        },
        methods: {
            /**
             * 表单重置
             */
            resetForm() {
                this.$refs['form'].resetFields();
            },

            // 获取信息
            getDataInfo(){
                this.$axios.get('api/engcom/company_info',{params:{
                        id: this.$route.query.id
                    }}).then( res => {
                    console.log(res);
                    let data = res.data.extend;
                    if(null != data.company){
                        this.form = data.company;
                    }                    
                }).catch(function (error) {
                    console.log(error);
                });
            },

            // 获取信息
            getFinanceList(){
                this.$axios.get('api/finance/list').then( res => {
                    console.log(res);
                    this.financeList = res.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            // 法人身份证正面上传成功回调函数
            idCardSideFace(res,file,files){
                console.log(res);
                this.form.idCardSideFace = res.extend.fileSystem.filePath;
            },
            goBack(){
                this.$router.go(-1);
            },
            // 保存数据
            saveCredit(){
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                });                
                this.$axios.post('api/engcom/credit',
                    this.qs.stringify(
                        {
                            fcompanyId: this.creditForm.fcompanyId,
                            companyId: this.$route.query.id,
                            amount: this.creditForm.amount,
                        }
                    )).then(res => {
                    console.log(res);
                    if(100 == res.data.code){
                        this.$message.success('授信成功！');
                        this.resetForm();
                        this.$router.go(-1);                        
                    }else{
                        this.$message.error('授信不失败');
                    }
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
