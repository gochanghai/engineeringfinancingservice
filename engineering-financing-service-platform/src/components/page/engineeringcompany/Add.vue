<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>新增工程公司</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <!-- 绑定银行卡 -->
                                <div class="content-info-box">
                                    <el-form ref="form" label-width="200px">
                                        <el-form-item label="公司名称：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="公司地址：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="企业法人：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="法人身份证号：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="社会统一信用代码证编号：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="上传附件：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>法人身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>法人身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传信用代码证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="对公账户名称：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="开户行：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="账号：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item>系统登录账户</el-form-item>
                                        <el-form-item label="资料员用户名：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="资料员手机号：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="审核员用户名：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="审核员手机号：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="企业用户授权书：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
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
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">保存</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'business-info-dateils',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                labelPosition: "right",

            }
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
