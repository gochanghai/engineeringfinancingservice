<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <!--<el-breadcrumb-item>首页</el-breadcrumb-item>-->
                <!--<el-breadcrumb-item>我的融资列表</el-breadcrumb-item>-->
                <el-breadcrumb-item>项目授信申请新增</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" :rules="rules" label-width="180px">
                    <el-form-item label="申请编号">
                        <el-input v-model="creditNo" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="申请日期">
                        <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="date" class="input-width"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="融资人">
                        <el-input v-model="userName" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="项目名称" prop="pId">
                        <el-select v-model="pId" placeholder="请选择">
                            <el-option v-for="item in projectList" :key="item.id" :label="item.projectName" :value="item.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="合同编号">
                        <el-input v-model="contractNo" class="input-width"></el-input>
                    </el-form-item>
                    <el-form-item label="申请授信额" prop="applyAmount">
                        <el-input v-model="form.applyAmount" style="width: 100px;margin-right: 20px"></el-input>万元 （授信金额不能大于{{creditAmount}}.00万）
                    </el-form-item>
                    <el-form-item label="申请事由" prop="originIncident">
                        <el-input type="textarea" rows="5" v-model="form.originIncident"></el-input>
                    </el-form-item>
                    <el-form-item label="个人近12个月银行流水">
                        <el-upload
                            action="http://192.168.1.98:8088/filesystem/upload/"
                            :class="{disabled:uploadDisabled}"
                            list-type="picture-card"
                            :limit="1"
                            :file-list="imagelist"
                            :on-success="handleSuccess"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove">
                            <i class="el-icon-plus"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <el-checkbox v-model="isAgree">我已阅读并接受</el-checkbox><a style="color: #ff8208">《信息提醒须知》</a>
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <el-button type="primary" @click="onReturn" class="btn-margin">取消</el-button>
                        <el-button type="primary" @click="save" class="btn-margin">保存</el-button>
                        <el-button type="info" class="btn-margin" v-show="isAgree === false" disabled>保存并提交</el-button>
                        <el-button type="primary" @click="saveAndSubmit" class="btn-margin" v-show="isAgree === true">保存并提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'AddCreditApply',
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
                fileBasePath: localStorage.getItem("fileBasePath"),
                userId: localStorage.getItem('userInfoId'),
                userName: localStorage.getItem('user_name'),
                projectList: null,
                creditNo: null,
                creditAmount: 0,
                date: new Date(),
                pId: null,
                contractNo: null,
                applyAmount: null,
                originIncident: null,
                bankListFile: null,
                status: 0,
                form:{
                    pId: null,
                    applyAmount: null,
                    originIncident: null,
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
                uploadDisabled: false,
                imagelist: [],
                imageUrl: null,
                dialogImageUrl: '',
                dialogVisible: false,
                isAgree: false
            }
        },
        created() {
            this.getProjectList();
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

            bankListFile:function() {
                let _than = this;
                if (this.bankListFile != null) {
                    _than.uploadDisabled = true;
                    console.log('imagelist true');
                    return
                }
                _than.uploadDisabled = false;
                console.log('imagelist'+ this.imagelist.length >0);
            },
        },
        computed: {
            uploadDisabled:function() {
                console.log('imagelist'+ this.imagelist.length >0);
                return  this.imagelist.length >0;
            },

        },
        methods: {
            // 获取项目数据
            getProjectList(){
                let _than = this;
                this.$axios.get('credit/project/list',{params:{
                        id: this.userId
                    }}).then(function (response) {
                    console.log(response);
                    _than.projectList = response.data.extend.list;
                    _than.creditNo = response.data.extend.applyNo;
                }).catch(function (error) {

                });
            },
            handleSuccess(response,file,files){
                this.imageUrl = this.fileBasePath+  response.extend.fileSystem.filePath;
                this.bankListFile = response.extend.fileSystem.filePath;
                console.log("response::: " + response);
            },
            handleRemove(file, fileList) {
                this.bankListFile = null;
                console.log(file, fileList);

            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            // 返回
            onReturn() {
                this.$router.push("my-credit-project-list")
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
                this.$router.push("my-credit-project-list")
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

    .crumbs {
        background-color: #fff;
        padding-left: 50px;
        margin-bottom: 0;
    }

    .breadcrumb {
        line-height: 30px;
        padding-left: 20px;
        border-left: 4px solid #ff8208;
    }

    .breadcrumb el-breadcrumb-item {
        font-size: 18px;
    }

    .container {
        padding-left: 200px;
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }

    .input-width{
        width: 210px;
    }
    .btn-margin{
        margin: 40px 40px 50px 0;

    }

    /* upload css */
    .el-upload .el-upload--text{
        width: 100px;
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
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }

    .disabled .el-upload--picture-card {
        display: none;
    }

</style>

