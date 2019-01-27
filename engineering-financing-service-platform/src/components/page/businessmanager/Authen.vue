<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>身份认证</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-title">
                            <div class="steps-box" style="width: 600px">
                                <!--<div class="triangle"></div>-->
                                <ul class="step">
                                    <li class="active">
                                        绑定银行卡
                                        <div class="triangle"></div>
                                    </li>
                                    <li class="active2">
                                        上传认证资料
                                        <div class="triangle"></div>
                                    </li>
                                    <li>
                                        完成
                                        <div class="triangle"></div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="info-btn-box">
                            <!--<div class="is-show-btn">展开</div>-->
                            <div class="info-content">
                                <!-- 绑定银行卡 -->
                                <div class="content-info-box">
                                    <el-form ref="form" label-width="100px">
                                        <el-form-item label="姓名：">张三</el-form-item>
                                        <el-form-item label="身份证号：">430426198502134569</el-form-item>
                                        <el-form-item label="银行卡号：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="开户行：">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="手机号：" style="width: 400px">
                                            <el-input v-model="name">
                                                <template slot="append" style="background-color: #fff">
                                                    <div>获取验证码</div>
                                                </template>
                                            </el-input>
                                        </el-form-item>
                                        <el-form-item label="验证码：">
                                            <el-input v-model="name" style="width: 200px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 个人身份信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">身份信息</div>
                                    <div class="content-lable2">查看示例</div>
                                </div>
                                <div class="content-info-box">
                                    <el-form label-width="100px">
                                        <el-form-item label="户籍所在地：" style="margin-bottom: 0">
                                            <el-input v-model="name" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传手持身份证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本个人页</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 婚姻信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">婚姻状况</div>
                                    <div class="content-lable2">已婚</div>
                                </div>
                                <div class="content-info-box">
                                    <el-form label-width="130px">
                                        <el-form-item label="配偶姓名：" >
                                            <el-input v-model="name" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="配偶身份证号：" >
                                            <el-input v-model="name" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="配偶户籍所在地：" >
                                            <el-input v-model="name" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="配偶附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传结婚证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本个人页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" hidden class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传离婚证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 社保信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">社保信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form label-width="140px">
                                        <el-form-item label="社保电脑号：" >
                                            <el-input v-model="name" style="width: 400px" />
                                        </el-form-item>
                                        <el-form-item label="当前购买社保公司：" >
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="最近缴纳社保清单：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传社保清单</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <div class="content-title">
                                    <div class="content-lable1">个人资产</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <!-- 房产信息 -->
                                <div class="content-info-box">
                                    <el-form label-width="100px">
                                        <el-form-item label="房产 1" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="房产所在地：" >
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="房产证明：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传房产证明</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="房产说明：" style="margin-bottom: 0">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 车辆信息 -->
                                <div class="content-info-box">
                                    <el-form label-width="100px">
                                        <el-form-item label="车辆 1" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="车牌号：" >
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="车辆行驶证：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传车辆行驶证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="车辆说明：" style="margin-bottom: 0">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">上一步</div>
                        <div class="info-bottom-btn2">下一步</div>
                        <div class="info-bottom-btn2">进入首页</div>
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
    /* 进度条 */
    /*credit-step*/
    .steps-box {
        /*width: 600px;*/
        /*height: 100px;*/
        margin: 0 auto;
    }
    ul,li {
        list-style: none;
    }
    .step {
        overflow: hidden;
    }
    .step li {
        position: relative;
        float: left;
        padding: 0 20px;
        line-height: 30px;
        color: #999;
        background: #e3e3e3;
        width: 152px;
    }
    .triangle {
        content: "";
        position: relative;
        position: absolute;
        top: 0;
        left: 100%;
        z-index: 1;
        border-left: 15px solid #fff;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
    }
    .triangle:after {
        content: "";
        position: absolute;
        left: -16px;
        top: -15px;
        border-left: 15px solid #e3e3e3;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
    }
    .step li.active {
        color: #fff;
        background: #ff8308;
        width: 152px;
    }
    .step li.active .triangle:after {
        border-left-color: #ff8308;
    }
    .step li.active2 {
        color: #fff;
        background: #FFB267;
        width: 152px;
    }
    .step li.active2 .triangle:after {
        border-left-color: #FFB267;
    }


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
        width: 60px;
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
        width: 540px;
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
