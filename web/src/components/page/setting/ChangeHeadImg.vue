<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>修改头像</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-btn-box">
                            <div class="info-content">
                                <div class="content-info-box">
                                    <el-form ref="form" :model="form" :rules="rules" label-width="150px" class="child-company-box">
                                        <el-upload
                                                class="avatar-uploader"
                                                :action="uploadAction"
                                                :show-file-list="false"
                                                :on-success="uploadSuccess"
                                                :before-upload="beforeAvatarUpload">
                                            <img v-if="imageUrl" :src="imageUrl" class="avatar">
                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                        </el-upload>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2" @click="save">保存</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'change-headimg',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                userId: localStorage.getItem('userId'),
                uploadAction: localStorage.getItem("uploadPath"),
                filesystem: localStorage.getItem("fileBasePath"),
                imageUrl:'',
                form:{
                    headimgurl:''
                },
                // 检验
                rules: { },

            }
        },
        methods: {
            /**
             * 上传前钩子函数
             */
            beforeAvatarUpload(file){
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            /**
             * 上传成功钩子函数
             */
            uploadSuccess(res, file, files){
                this.form.headimgurl = response.extend.fileSystem.filePath;
                this.imageUrl = response.extend.fileSystem.filePath;
            },

            save(){
                let _than = this;
                this.$axios.put('user/change_headimgurl',
                    this.qs.stringify(
                        {
                            id: this.userId,
                            headimgurl: this.form.headimgurl,
                        }
                    )).then( (res)=> {
                    console.log(res);
                    if(res.data.code === 100){
                        _than.$message.success("头像修改成功");
                        _than.$router.push("home2")
                        return;
                    }
                    _than.$message.success("头像修改失败");
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

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
        padding: 20px 20px;
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
    .child-company-box{
        width: 600px;
        margin: 0 auto;
    }

    /* 长传控件 */
    .el-upload--text{
        /*margin: 10px 10px;*/
        width: 178px;
        height: 178px;
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
</style>
