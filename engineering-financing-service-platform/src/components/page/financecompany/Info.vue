<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>资金方信息</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <!-- 绑定银行卡 -->
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" label-width="200px">
                                        <el-form-item label="企业全称：" prop="companyName">
                                            <el-input v-model="form.companyName" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="联系人：" prop="contactPerson">
                                            <el-input v-model="form.contactPerson" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="联系电话：" prop="contactNumber">
                                            <el-input v-model="form.contactNumber" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="合作分行：" prop="coopBank">
                                            <el-input v-model="form.coopBank" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="分行地址：">
                                            <el-input v-model="form.address" style="width: 400px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1" @click="onReturn">返回</div>
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
            return {
                name: localStorage.getItem('ms_username'),
                labelPosition: "right",
                form: {
                    id: '',
                    companyName: '',
                    contactPerson: '',
                    contactNumber: '',
                    coopBank: '',
                    address: '',
                    username: '',
                    phone: '',
                    status: 0
                },

            }
        },
        components: {
        },
        computed: {
        },
        created(){
            let id = this.$route.query.id;
            this.getDataInfo(id);
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            onReturn() {
                // 返回上一步
                this.$router.go(-1);
            },
            getDataInfo(id){
                let _than = this;
                this.$axios.get('api/finance/info',{params:{
                        id: id
                    }}).then(res => {
                    console.log(res);
                    let data = res.data.extend.data;
                    if(null != data){
                        this.form = data;
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
