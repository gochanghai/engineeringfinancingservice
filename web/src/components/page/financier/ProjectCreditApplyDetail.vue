<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item>项目授信申请详情</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="credit-state">
                <el-form>
                    <el-form-item>
                        <i class="el-icon-circle-close-outline" style="width: 50px;height: 50px; color: #ff8208"></i>
                    </el-form-item>
                    <el-form-item></el-form-item>
                </el-form>
            </div>
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="180px">
                    <el-form-item label="申请编号">
                        {{this.dataInfo.cai.creditNumber}}
                        <!--<el-input v-model="form.name" class="input-width"></el-input>-->
                    </el-form-item>
                    <el-form-item label="申请日期">
                        {{this.dataInfo.cai.date}}
                        <!--<el-date-picker type="date" placeholder="选择日期" v-model="form.date1" class="input-width"></el-date-picker>-->
                    </el-form-item>
                    <el-form-item label="融资人">
                        {{this.dataInfo.f.name}}
                        <!--<el-input v-model="form.name" class="input-width"></el-input>-->
                    </el-form-item>
                    <el-form-item label="项目名称">
                        {{this.dataInfo.pci.projectName}}
                        <!--<el-select v-model="form.region" placeholder="请选择">-->
                            <!--<el-option key="bbk" label="深圳分公司" value="bbk"></el-option>-->
                            <!--<el-option key="xtc" label="广州分公司" value="xtc"></el-option>-->
                            <!--<el-option key="imoo" label="上海分公司" value="imoo"></el-option>-->
                        <!--</el-select>-->
                    </el-form-item>
                    <el-form-item label="合同编号">
                        {{this.dataInfo.pci.projectNumber}}
                        <!--<el-input v-model="form.name" class="input-width"></el-input>-->
                    </el-form-item>
                    <el-form-item label="申请授信额">
                        {{this.dataInfo.cai.applyCreditAmount}} 万
                        <!--<el-input v-model="form.name" style="width: 210px;margin-right: 20px"></el-input>万元-->
                    </el-form-item>
                    <el-form-item label="申请事由">
                        {{this.dataInfo.cai.originIncident}}
                        <!--<el-input type="textarea" rows="5" v-model="form.desc"></el-input>-->
                    </el-form-item>
                    <el-form-item label="个人近12个月银行流水">
                        <div @click="handlePictureCardPreview(dataInfo.cai.bankListFile)">
                            <img :src="fileBasePath+ this.dataInfo.cai.bankListFile" style="width: 100px; height: 100px"/>
                        </div>
                        <!--<el-upload-->
                            <!--action="https://jsonplaceholder.typicode.com/posts/"-->
                            <!--list-type="picture-card"-->
                            <!--:on-preview="handlePictureCardPreview"-->
                            <!--:on-remove="handleRemove">-->
                            <!--<i class="el-icon-plus"></i>-->
                        <!--</el-upload>-->
                        <!--<el-dialog :visible.sync="dialogVisible">-->
                            <!--<img width="100%" :src="dialogImageUrl" alt="">-->
                        <!--</el-dialog>-->
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <el-button type="primary" @click="onReturn" class="btn-margin">返回</el-button>
                        <!--<el-button type="primary" @click="save" class="btn-margin">保存</el-button>-->
                        <!--<el-button type="info" class="btn-margin" v-show="isAgree === false" disabled>保存并提交</el-button>-->
                        <!--<el-button type="primary" @click="saveAndSubmit" class="btn-margin" v-show="isAgree === true">保存并提交</el-button>-->
                    </el-form-item>
                    <!--<el-form-item style="text-align: center">-->
                        <!--<el-checkbox v-model="isAgree">我已阅读并接受</el-checkbox><a style="color: #ff8208">《信息提醒须知》</a>-->
                    <!--</el-form-item>-->
                </el-form>
            </div>
        </div>
        <el-dialog :visible.sync="dialogTableVisible">
            <img style="width: 100%" :src="fileBasePath+ this.dataInfo.cai.bankListFile"/>
        </el-dialog>
        <el-dialog :visible.sync="pdfDialogVisible" width="800px" top="20px">
            <div style="width: 100%; height: 900px">
                <embed :src=" fileBasePath+ this.dataInfo.cai.bankListFile"
                       width="100%"
                       height="100%"
                       frameborder="no"
                       border="0"
                       marginwidth="0"
                       marginheight="0"
                       scrolling="no"
                       allowtransparency="no"/>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'projectCreditApplyDetail',
        data: function(){
            return {
                fileBasePath: localStorage.getItem("fileBasePath"),
                form:{},
                dialogTableVisible: false,
                projectId: this.$route.query.id,
                dataInfo: null,
                dialogImageUrl: null,
                dialogVisible: false,
                pdfDialogVisible: false,
                isAgree: false
            }
        },
        created() {
            // this.getData();
            // 获取Table数据
            this.getDataList();
        },
        methods: {

            // 获取项目进度数据
            getDataList(){
                let _than = this;
                this.$axios.get('credit/f/info',{params:{
                        id: this.projectId
                    }}).then(function (response) {
                    console.log(response);
                    _than.dataInfo = response.data.extend;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            handlePictureCardPreview(file) {
                let _that = this;
                this.dialogImageUrl = file.url;
                // 获取文件扩展名
                let extension = file.replace(/.+[\/\.]/g,'');
                console.log(extension);
                if (extension === "pdf"){
                    _that.pdfDialogVisible = true;
                }else {
                    _that.dialogTableVisible = true;
                }

            },
            onReturn() {
                // this.$message.success('返回！');
                this.$router.push("my-credit-project-list")
            },
            save(){
                // this.$message.success('保存成功！');
                this.$router.push("my-credit-project-list")
            },
            saveAndSubmit(){
                // this.$message.success('提交成功！');
                this.$router.push("my-credit-project-list")
            },


        }
    }
</script>

<style scoped>

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
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }
    .credit-state{
        margin: 0 50px 50px 50px;
        height: 100px;
        border-bottom: 1px dashed #cccccc;
        text-align: center;
        color: #cccccc;
    }
    .form-box{
        padding-left: 100px;
    }
    .input-width{
        width: 210px;
    }
    .btn-margin{
        margin: 40px 40px 50px 0;

    }
</style>
