<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>企业信息</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <!-- 绑定银行卡 -->
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" label-width="200px">
                                        <el-form-item label="授信资方：">{{form.financeName}}</el-form-item>
                                        <el-form-item label="授信额度：">{{form.creditAmount}}</el-form-item>
                                        <el-form-item label="公司名称：">{{form.companyName}}</el-form-item>
                                        <el-form-item label="公司地址：">{{form.address}}</el-form-item>
                                        <el-form-item label="企业法人：">{{form.person}}</el-form-item>
                                        <el-form-item label="法人身份证号：">{{form.idCard}}</el-form-item>
                                        <el-form-item label="信用代码证编号：">{{form.creditCode}}</el-form-item>
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
                                        <el-form-item label="对公账户名称：">{{form.publicAccountName}}</el-form-item>
                                        <el-form-item label="开户行：">{{form.openAccountBank}}</el-form-item>
                                        <el-form-item label="账号：">{{form.bankAccount}}</el-form-item>
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
                        <div class="info-bottom-btn1" @click="$router.go(-1)">返回</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'enterprise-info',
        data() {
            return {
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

            // 获取信息
            getDataInfo(){
                this.$axios.get('api/engcom/company_info',{params:{
                        id: localStorage.getItem('companyId')
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
