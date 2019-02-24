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
                                        <el-form label-width="100px" :model="form" :rules="rules">
                                            <el-form-item label="申请编号：" >
                                                <el-input v-model="creditNo" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="申请日期：">
                                                <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="date" style="width: 200px"></el-date-picker>
                                            </el-form-item>
                                            <el-form-item label="申请人：">
                                                <el-input v-model="name" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="项目名称：" prop="pId">
                                                <el-select v-model="pId" placeholder="请选择">
                                                    <el-option v-for="item in projectList" :key="item.id" :label="item.projectName" :value="item.id"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="合同编号：">
                                                <el-input v-model="contractNo" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="申请额度：" prop="applyAmount">
                                                <el-input v-model="form.applyAmount" style="width: 200px"/>
                                            </el-form-item>
                                            <el-form-item label="申请事由：">
                                                <el-input type="textarea" rows="5" v-model="form.originIncident"/>
                                            </el-form-item>
                                            <el-form-item label="银行流水：">
                                                <div class="file-box">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="bankListFile"
                                                                :show-file-list="false">
                                                            <img v-if="form.bankListFile !== null" class="avatar" :src=" filesystem + form.bankListFile">
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
                name: localStorage.getItem('ms_username'),
                uploadPath: localStorage.getItem("uploadPath"),
                filesystem: localStorage.getItem("fileBasePath"),
                userId: localStorage.getItem('userInfoId'),
                userName: localStorage.getItem('user_name'),
                creditNo: '',
                projectList: [],
                creditNo: null,
                creditAmount: 0,
                date: formatDate(new Date(),'yyyy-MM-dd'),
                pId: null,
                contractNo: null,
                applyAmount: null,
                originIncident: null,
                form:{
                    pId: null,
                    applyAmount: null,
                    originIncident: null,
                    bankListFile: null,
                },
                rules: {
                    pId: [
                        { required: true, message: '请选择项目', trigger: 'blur' },
                    ],
                    applyAmount: [
                        { required: true, message: '请输入申请授信额度', trigger: 'blur' },
                        { validator:  checkCreditAmount, trigger: 'blur' }
                    ],
                    originIncident: [
                        { required: true, message: '请输入申请事由', trigger: 'blur' },
                    ]
                },
                status: 0,
                isAgree: false,
            }
        },
        // 监听器
        watch: {
            pId:function(val) {
                let _than = this;
                for (let item in _than.projectList) {
                    console.log("foreach: " + item);
                    if (_than.projectList[item].id === _than.pId) {
                        _than.contractNo = _than.projectList[item].contractNo;
                        _than.form.applyAmount = _than.projectList[item].shouldCreditAmount;
                        _than.creditAmount = _than.projectList[item].shouldCreditAmount;
                        _than.form.pId = val;
                    }
                }
            },
        },
        components: {
        },
        computed: {
        },
        created(){
            this.getProjectList();
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            // 获取项目数据
            getProjectList(){
                let _than = this;
                this.$axios.get('credit/project/list',{params:{
                        id: _than.userId
                    }}).then((response)=> {
                    console.log(response);
                    _than.projectList = response.data.extend.list;
                    _than.creditNo = response.data.extend.applyNo;
                }).catch(function (error) {

                });
            },

            // 银行流水上传成功回调函数
            bankListFile(response,file,files){
                this.form.bankListFile = response.extend.fileSystem.filePath;
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
                this.$axios.post('credit/save',
                    this.qs.stringify(
                        {
                            date: this.date,
                            creditNo: this.creditNo,
                            fId: this.userId,
                            applyAmount: this.form.applyAmount,
                            pId: this.form.pId,
                            contractNo: this.contractNo,
                            originIncident: this.form.originIncident,
                            bankListFile: this.bankListFile,
                            status: this.status,
                        }
                    )).then(function (response) {
                    console.log(response);
                }).catch(function (error) {
                    console.log(error);
                });
                this.$router.push("my-credit-el")
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
