<template>
    <div>        
        <div class="block" v-show="addVisible == false">
            <el-timeline>
                <el-timeline-item v-for="(item, index) in dataList" :timestamp="item.date" :key="index" :color="index == 0 ? '#0bbd87':'#ff8208'" placement="top">
                    <el-card>
                        <h4 class="h4">施工进度: {{item.progressRatio}}%</h4>
                        <h4 class="h4">确定产值: {{item.outputValue}}</h4>                        
                        <p>进度描述: {{item.progressDesc}}</p>
                        <p style="padding-top: 10px;">
                            <img v-show="item.file != null" :src=" filesysip + item.file" width="100px" height="100px"/>
                        </p>
                    </el-card>
                </el-timeline-item>
            </el-timeline>
        </div>
        <div class="update-btn" v-show="addVisible == false" @click="addVisible = true">更新进度</div>
        <!-- 更新进度弹出框 -->
        <el-row :gutter="20" v-show="addVisible">
            <el-col :span="24">
                <el-card shadow="hover">
                    <el-form ref="form" :model="form" :rules="rules2" status-icon label-width="200px" class="demo-ruleForm">
                        <el-form-item label="日期">
                            <el-date-picker type="date" placeholder="选择日期" v-model="form.date"
                                            value-format="yyyy-MM-dd" style="width: 200px;" />
                        </el-form-item>
                        <el-form-item label="施工进度" prop="progressRatio">
                            <el-input v-model.number="form.progressRatio" style="width: 180px"/>%
                        </el-form-item>
                        <el-form-item label="进度描述">
                            <el-input type="textarea" style="width: 400px" v-model="form.progressDesc"/>
                        </el-form-item>
                        <el-form-item label="确定产值" prop="outputValue">
                            <el-input v-model.number="form.outputValue" style="width: 180px"></el-input>万元
                        </el-form-item>
                        <el-form-item label="施工现场照片或视频">
                            <el-upload
                                    :action="uploadUrl"
                                    list-type="picture-card"
                                    :class="{disabled: isShowUploadBtn}"
                                    name="file"
                                    :on-success="handleSuccess"
                                    :on-preview="handlePictureCardPreview"
                                    :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                        </el-form-item>
                        <div class="info-bottom-box">
                            <div class="info-bottom-btn1" @click="addVisible = false">取 消</div>
                            <div class="info-bottom-btn2" @click="save('form')">确 定</div>
                        </div>
                    </el-form>
                </el-card>
            </el-col>
        </el-row>
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
                uploadUrl: localStorage.getItem("uploadPath"),
                filesysip: localStorage.getItem("fileBasePath"),
                dataList: null,
                isShowUploadBtn: true,
                addVisible: false,
                dialogImageUrl: '',
                dialogVisible: false,
                projectId: this.$route.query.id,
                form:{
                    date: null,
                    progressRatio: null,
                    progressDesc: null,
                    outputValue: null,
                    progressFile: null
                },

                rules2: {
                    outputValue: [
                        { validator:  checkOutputValue, trigger: 'blur' }
                    ],
                    progressRatio: [
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
                this.$axios.get('api/project/progress/list',{params:{
                        projectId: this.projectId
                    }}).then(function (res) {
                    console.log(res);
                    _than.dataList = res.data.extend.list;
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
                        _than.$axios.post('api/project/progress',
                            _than.qs.stringify(
                                {
                                    date: this.form.date,
                                    progressRatio: this.form.progressRatio,
                                    progressDesc: this.form.progressDesc,
                                    outputValue: this.form.outputValue,
                                    file: this.form.progressFile,
                                    projectId: this.projectId
                                }
                            )).then(function (res) {
                            console.log(res);
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
.h4{
    display: block;
    float: left;
    margin-right: 50px    
}
.update-btn{
    position:fixed; 
    top: 75px;
    right: 30px;
    width:120px;
    height:30px;
    line-height: 30px;
    background:rgba(255,178,103,1);
    border-radius:5px;
    color: rgba(255,255,255,1);
    text-align: center;
}
.update-btn:hover{
    background:rgba(255,130,8,1);
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
    background:rgba(255,178,103,1);
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
</style>


