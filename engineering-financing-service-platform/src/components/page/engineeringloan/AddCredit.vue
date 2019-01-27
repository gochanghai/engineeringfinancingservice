<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>项目授信申请新增</span>
                    </div>
                    <!-- 项目信息 -->
                    <div class="info-box">
                        <div class="info-title">配置参数</div>
                        <div class="info-btn-box">
                            <!--<div class="is-show-btn">展开</div>-->
                            <div class="info-content">
                                <!-- 基本信息 -->
                                <div class="content-title">
                                    <!--<div class="content-lable1">基本信息</div>-->
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <div class="form-box">
                                        <el-form label-width="100px">
                                            <el-form-item label="申请编号：" >
                                                <el-input v-model="name" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="申请日期：">
                                                <el-input v-model="name" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="申请人：">
                                                <el-input v-model="name" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="项目名称：" >
                                                <el-input v-model="name" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="合同编号：">
                                                <el-input v-model="name" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="申请额度：">
                                                <el-input v-model="name" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="申请事由：">
                                                <el-input type="textarea" rows="5"></el-input>
                                            </el-form-item>
                                            <el-form-item label="银行流水：">
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
                                            <el-form-item>
                                                <el-checkbox v-model="isAgree">我已阅读并接受</el-checkbox><a style="color: #ff8208">《信息提醒须知》</a>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">保存</div>
                        <div class="info-bottom-btn2">保存并提交</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'project-dateils',
        data: function(){
            return {
                name: localStorage.getItem('ms_username'),
                isAgree: false,
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
    .info-title{
        font-size:14px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
        width: 100%;
        line-height:19px;
        margin: 0 auto;
        text-align: center;
        border: 1px dashed #ccc;
        padding: 20px 0;
    }
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
    .is-show-btn{
        height:19px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        color:rgba(255,130,8,1);
        width: 80px;
        line-height:19px;
        text-align: center;
        border: 1px solid rgba(255,130,8,1);
        margin-left: auto;


    }

    .content-title{
        display: block;
        margin: 0 auto 0 0;
        padding: 10px 10px;
        /*解决高度塌陷问题*/
        zoom:1;
        overflow: hidden;
    }
    .content-lable1{
        display: block;
        float: left;
        height:20px;
        font-size:14px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
        line-height:20px;
        padding: 0 10px;
        /*width: 60px;*/
    }
    .content-lable2{
        display: block;
        float: left;
        width: 60px;
        height: 20px;
        background: #FFE699;
        font-size:12px;
        font-family:MicrosoftYaHei;
        color:rgba(187,114,0,1);
        line-height:20px;
        text-align: center;
    }
    .content-info-box{
        /*padding: 20px 20px 20px 150px;*/
        width: 400px;
        margin: 0 auto;
        /*解决高度塌陷问题*/
        zoom:1;
        overflow: hidden;
    }
    .form-box{
        display: block;
        /*position: relative;*/
        float: left;
        width: 400px;
        margin-right: 50px;
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
        width: 400px;
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

    .file-box{
        padding: 10px 0;
        text-align: center;
    }
    .card-file{
        float: left;
        width: 100px;
        /*height: 170px;*/
        margin-right: 20px;
    }
    /*上传*/
    .el-upload--text{
        /*margin: 10px 10px;*/
        width: 100px;
        height: 100px;
    }
    .avatar-uploader{
        height: 100px;
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
        width: 100px;
        height: 100px;
        line-height: 100px;
        text-align: center;
    }
    .avatar {
        width: 100px;
        height: 100px;
        display: block;
    }

</style>
