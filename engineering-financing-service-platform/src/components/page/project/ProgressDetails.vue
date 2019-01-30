<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>放款批复列表</span>
                    </div>
                    <div class="timeline">
                        <div class="entry" v-for="item in dataList">
                            <div class="title">
                                <h3>{{item.date}}</h3>
                                <!--<p>Title, Company</p>-->
                            </div>
                            <div class="body">
                                <p>施工进度：{{item.constructionProgress}}%</p>
                                <p>进度描述：{{item.progressDesc}}</p>
                                <p>确定产值：{{item.outputValue}}万</p>
                                <p>施工现场照片或视频</p>
                                <img v-show="item.progressFile.length > 0" :src=" filesysip + item.progressFile" width="100px" height="100px"/>
                                <!--<ul>-->
                                <!--<li>Rerum sit libero possimus amet excepturi</li>-->
                                <!--<li>Exercitationem enim dolores sunt praesentium dolorum praesentium</li>-->
                                <!--<li>Modi aut dolores dignissimos sequi sit ut aliquid molestias deserunt illo</li>-->
                                <!--</ul>-->
                            </div>
                        </div>
                    </div>
                    <div class="btn-box">
                        <el-button type="warning" style="background-color: #ff8208" @click="addVisible = true">更新进度</el-button>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <!-- 更新进度弹出框 -->
        <el-dialog title="编辑" :visible.sync="addVisible" width="30%">
            <el-form ref="form" :model="form" :rules="rules2" status-icon label-width="200px" class="demo-ruleForm">
                <el-form-item label="日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date"
                                    value-format="yyyy-MM-dd" style="width: 150px;" ></el-date-picker>
                </el-form-item>
                <el-form-item label="施工进度" prop="constructionProgress">
                    <el-input v-model.number="form.constructionProgress" style="width: 100px"></el-input>%
                </el-form-item>
                <el-form-item label="进度描述">
                    <el-input type="textarea" v-model="form.progressDesc"></el-input>
                </el-form-item>
                <el-form-item label="确定产值" prop="outputValue">
                    <el-input v-model.number="form.outputValue" style="width: 100px"></el-input>万元
                </el-form-item>
                <el-form-item label="施工现场照片或视频">
                    <el-upload
                            action="http://192.168.1.98:8088/filesystem/upload/"
                            list-type="picture-card"
                            :class="{disabled: isShowUploadBtn}"
                            name="file"
                            :on-success="handleSuccess"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove">
                        <i class="el-icon-plus"></i>
                    </el-upload>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="save('form')">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 附件预览弹出框 -->
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "ProjectProgressDetail",
        data: function(){

            var checkConstructionProgress = (rule, value, callback) => {
                console.log("wwwww: check");
                if (!value) {
                    return callback(new Error('施工进度不能为空'));
                }
                // 正则表达式校验
                var reg=/^-?\d*\.?\d*$/;
                console.log("reg: " + reg.test(value) + Number.isInteger(value));

                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        if (value < 0 || value > 100) {
                            callback(new Error('施工进度只能0< 进度<=100'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };
            var checkOutputValue = (rule, value, callback) => {
                console.log("checkOutputValue: check");
                if (!value) {
                    return callback(new Error('产值不能为空'));
                }
                // 正则表达式校验
                var reg=/^-?\d*\.?\d*$/;
                console.log("reg: " + reg.test(value) + Number.isInteger(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        if (value < 0) {
                            callback(new Error('产值只能0<= 产值'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };

            return {
                filesysip: localStorage.getItem("fileBasePath"),
                dataList: null,
                isShowUploadBtn: true,
                addVisible: false,
                dialogImageUrl: '',
                dialogVisible: false,
                projectId: this.$route.query.id,
                form:{
                    date: '',
                    constructionProgress: null,
                    progressDesc: '',
                    outputValue: null,
                    progressFile:''
                },

                rules2: {
                    // pass: [
                    //     { validator: validatePass, trigger: 'blur' }
                    // ],
                    outputValue: [
                        { validator:  checkOutputValue, trigger: 'blur' }
                    ],
                    constructionProgress: [
                        { validator: checkConstructionProgress, trigger: 'blur' }
                    ]
                }

            }
        },
        created() {
            // 获取Table数据
            this.getDataList();
        },
        methods: {
            // 获取项目进度数据
            getDataList(){
                let _than = this;
                this.$axios.get('ppd/list',{params:{
                        id: this.projectId
                    }}).then(function (response) {
                    console.log(response);
                    _than.dataList = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            // 附件上传成功回调方法
            handleSuccess(response,file,files){
                this.form.progressFile = response.extend.fileSystem.filePath;
                this.isShowUploadBtn = true;
                console.log("response::: " + response);
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            save(formName){
                let _than = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        _than.$axios.post('ppd/save',
                            _than.qs.stringify(
                                {
                                    date: this.form.date,
                                    constructionProgress: this.form.constructionProgress,
                                    progressDesc: this.form.progressDesc,
                                    outputValue: this.form.outputValue,
                                    progressFile: this.form.progressFile,
                                    projectId: this.projectId
                                }
                            )).then(function (response) {
                            console.log(response);
                            _than.addVisible = false;
                            _than.getDataList();
                        }).catch(function (error) {
                            console.log(error);
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
                this.$message.success('保存成功！');

            },
        }
    }
</script>

<style scoped>
    /*body {*/
        /*background: linear-gradient(55deg, #4E75B9 30%, #5CBF98 90%);*/
        /*display: flex;*/
        /*align-items: center;*/
        /*justify-content: center;*/
        /*min-height: 100vh;*/
        /*width: 100vw;*/
        /*margin: 0;*/
        /*padding: 100px;*/
        /*font-family: 'Source Sans Pro', arial, sans-serif;*/
        /*font-weight: 300;*/
        /*color: #333;*/
        /*box-sizing: border-box;*/
    /*}*/
    body * {
        box-sizing: border-box;
    }

    .timeline {
        width: 100%;
        /*max-width: 800px;*/
        background: #fff;
        padding: 100px 50px 50px 50px;
        position: relative;
        /*box-shadow: 0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2);*/
        color: #606266;
        height: 100%;
    }
    .timeline:before {
        content: '';
        position: absolute;
        top: 0px;
        left: calc(33% + 15px);
        bottom: 0px;
        width: 4px;
        background: #ff8208;
    }
    .timeline:after {
        content: "";
        display: table;
        clear: both;
    }

    .entry {
        clear: both;
        text-align: left;
        position: relative;
    }
    .entry .title {
        margin-bottom: .5em;
        float: left;
        width: 33%;
        padding-right: 30px;
        text-align: right;
        position: relative;
    }
    .entry .title:before {
        content: '';
        position: absolute;
        width: 8px;
        height: 8px;
        /*border: 4px solid salmon;*/
        border: 4px solid #ff8208;
        background-color: #fff;
        border-radius: 100%;
        top: 15%;
        right: -8px;
        z-index: 99;
    }
    .entry .title h3 {
        margin: 0;
        font-size: 120%;
    }
    .entry .title p {
        margin: 0;
        font-size: 100%;
    }
    .entry .body {
        margin: 0 0 3em;
        float: right;
        width: 66%;
        padding-left: 30px;
    }
    .entry .body p {
        line-height: 1.4em;
    }
    .entry .body p:first-child {
        margin-top: 0;
        font-weight: 400;
    }
    .entry .body ul {
        color: #aaa;
        padding-left: 0;
        list-style-type: none;
    }
    .entry .body ul li:before {
        content: "–";
        margin-right: .5em;
    }
    .btn-box{
        padding: 20px 300px 50px;
        background-color: #ffffff;
        text-align: center;
    }

    .dialog-footer{
        width: 100%;
        text-align: center !important;
    }

    .disabled .el-upload--picture-card {
        display: none;
    }


</style>
