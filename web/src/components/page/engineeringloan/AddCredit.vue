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
                                        <el-form label-width="100px" :model="form" :rules="rules" ref="form">
                                            <el-form-item label="申请编号：" >
                                                <el-input v-model="form.applyNo" style="width: 300px" disabled/>
                                            </el-form-item>
                                            <el-form-item label="申请日期：">
                                                <el-input v-model="form.applyDate" style="width: 300px" disabled/>
                                                <!--<el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="date" style="width: 200px"></el-date-picker>-->
                                            </el-form-item>
                                            <el-form-item label="申请人：">
                                                <el-input v-model="form.name" style="width: 300px" disabled/>
                                            </el-form-item>
                                            <el-form-item label="项目名称：" prop="projectId">
                                                <el-select v-model="form.projectId" placeholder="请选择" style="width: 300px">
                                                    <el-option v-for="item in projectList" :key="item.id" :label="item.projectName" :value="item.id"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="合同编号：" prop="projectNo">
                                                <el-input v-model="form.projectNo" style="width: 300px"/>
                                            </el-form-item>
                                            <el-form-item label="申请额度：" prop="applyAmount">
                                                <el-input v-model="form.applyAmount" style="width: 300px"/>
                                            </el-form-item>
                                            <el-form-item label="申请事由：" prop="reason">
                                                <el-input type="textarea" rows="5" v-model="form.reason"/>
                                            </el-form-item>
                                            <el-form-item label="银行流水：" prop="bankWater">
                                                <div class="file-box">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="bankListFile"
                                                                :show-file-list="false">
                                                            <img v-if="form.bankWater !== null" class="avatar" :src=" filesystem + form.bankWater">
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
                        <div class="info-bottom-btn1" @click="onReturn">返回</div>
                        <div class="info-bottom-btn2" @click="save">保存</div>
                        <div class="info-bottom-btn2" @click="saveAndSubmit">保存并提交</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import {formatDate}  from '../../common/util/date.js';
    export default {
        name: 'add-credit',
        data: function(){
            var checkCreditAmount = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('申请授信额度不能为空'));
                }
                // 正则表达式校验
                var reg=/^-?\d*\.?\d*$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的数字'));
                    } else {
                        if (value <= 0) {
                            callback(new Error('申请授信额度必须大于0'));
                        }else if(value > this.creditAmount){
                            callback(new Error('申请授信额度不能大于'+this.creditAmount));
                        }
                        callback();
                    }
                }, 1000);
            };
            return {
                uploadPath: localStorage.getItem("uploadPath"),
                filesystem: localStorage.getItem("fileBasePath"),               
                projectList: [],
                form:{
                    userId: localStorage.getItem('userId'),
                    applyNo: formatDate(new Date(),'yyyyMMdd') + new Date().getTime(),
                    applyDate: formatDate(new Date(),'yyyy-MM-dd'),
                    name: localStorage.getItem('user_name'),
                    projectId: null,
                    projectNo: '',
                    applyAmount: null,
                    reason: null,
                    bankWater: null,
                },
                rules: {
                    projectId: [
                        { required: true, message: '请选择项目', trigger: 'blur' },
                    ],
                    applyAmount: [
                        { required: true, message: '请输入申请授信额度', trigger: 'blur' },
                        { validator:  checkCreditAmount, trigger: 'blur' }
                    ],
                    reason: [
                        { required: true, message: '请输入申请事由', trigger: 'blur' },
                    ],
                    bankWater:[
                        { required: true, message: '请输上传银行流水', trigger: 'blur' },
                    ],
                },
                status: 0,
                isAgree: false,
            }
        },
        // 监听器
        watch: {
            'form.projectId':function(val) {
                let _than = this;
                for (let item in _than.projectList) {
                    console.log("foreach: " + item);
                    if (_than.projectList[item].id === _than.form.projectId) {
                        _than.form.projectNo = _than.projectList[item].contractNo;
                        _than.form.applyAmount = _than.projectList[item].shouldCreditAmount;
                    }
                }
            },
        },
        components: {
        },
        computed: {
        },
        created(){
            let userId = this.form.userId;
            this.getProjectList(userId);
            this.getNameAndIdCard(userId);
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            // 获取项目数据
            getProjectList(userId){
                let _than = this;
                this.$axios.get('api/project/list/bm',{params:{
                        userId: userId
                    }}).then(res=> {
                    console.log(res);
                    _than.projectList = res.data.extend.list;
                    _than.creditNo = res.data.extend.applyNo;
                }).catch(function (error) {

                });
            },

            /**
             * 获取用户信息
             */
            getNameAndIdCard(userId){
                let _than = this;
                this.$axios.get('api/business/user_id',{params:{
                        userId: userId
                    }}).then(function (res) {
                    console.log(res);
                    _than.form.name = res.data.extend.name;
                    _than.form.idCard = res.data.extend.idCard;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            // 银行流水上传成功回调函数
            bankListFile(res,file,files){
                this.form.bankWater = res.extend.fileSystem.filePath;
            },

            // 返回
            onReturn() {
                this.$router.push("my-credit-el")
            },
            // 保存
            save(){
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                });
                let _than = this;
                this.$axios.post('api/credit/apply',
                    this.qs.stringify(
                        {
                            applyDate: this.form.applyDate,
                            applyNo: this.form.applyNo,
                            userId: this.form.userId,
                            name: this.form.name,
                            applyAmount: this.form.applyAmount,
                            projectId: this.form.projectId,
                            projectNo: this.projectNo,
                            reason: this.form.reason,
                            bankWater: this.form.bankWater,
                            status: this.status,
                        }
                    )).then(function (res) {
                    _than.$router.push("my-credit-el");
                    console.log(res);
                }).catch(function (error) {
                    console.log(error);
                });

            },
            // 保存并提交
            saveAndSubmit(){
                this.status = 1;
                this.save();
            }


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
