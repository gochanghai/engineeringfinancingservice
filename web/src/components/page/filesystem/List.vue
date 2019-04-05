<template>
    <div>
        <el-row :gutter="20">
            <el-col>
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>文件列表</span>
                    </div>
                    <el-table :data="tableData" border class="table" v-loading="loading" ref="multipleTable">
                        <el-table-column type="index" label="序号" width="50" align="center"/>
                        <el-table-column prop="fileName" label="文件名称" width="200" align="left"/>
                        <el-table-column prop="fileType" label="文件类型" width="100" align="center"/>
                        <el-table-column label="文件大小" width="120" align="center">
                            <template slot-scope="scope">
                                {{scope.row.fileSize}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="filePath" label="文件路径" align="left" />
                        <el-table-column prop="createDate" label="上传时间" width="200" align="center" />
                        <el-table-column label="文件预览" width="200" align="center">
                            <template slot-scope="scope">
                                <div>
                                    <img v-if="scope.row.fileType === 'pdf'" src="../../../../static/img/pdf.jpg" width="23px" height="23px" @click="filePDFPreview(scope.row.filePath)"/>
                                    <img v-else :src="filesysip + scope.row.filePath" width="23px" height="23px" @click="filePreview(scope.row.filePath)"/>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="150" align="center">
                            <template slot-scope="scope">
                                <a :href="filesysip + scope.row.filePath" :download="scope.row.fileName"><el-button size="mini" round>下载</el-button></a>
                                <!-- <el-button size="mini" round @click="downloadFile(scope.row)">下载</el-button> -->
                                <el-button size="mini" round @click="deleteFile(scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-col>
        </el-row>
        <!-- 图片放大 -->
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
        <!-- pef预览 -->
        <el-dialog :visible.sync="pdfDialogVisible" width="800px" top="20px">
            <div style="width: 100%; height: 900px">
            <embed :src="pdfPath"
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
        name: "file-list",
        data() {
            return {
                filesysip: localStorage.getItem("fileBasePath"),
                loading: true,
                dialogVisible: false,
                pdfDialogVisible: false,
                dialogImageUrl: null,
                pdfPath: null,
                tableData: [],
            }
        },
        computed: {
            role() {
                return this.name === 'admin' ? '超级管理员' : '普通用户';
            }
        },
        created(){
            this.getFileList();
        },
        methods: {

            /**
             * 获取文件列表处理函数
             */
            getFileList() {
                let _than = this;
                this.$axios.get('filesystem/all').then(function (res) {
                    console.log(res);
                    _than.tableData = res.data.extend.list;
                    _than.loading = false;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            /**
             * 图片预览处理函数
             */
            filePreview(filePath) {
                this.dialogImageUrl =  this.filesysip + filePath;
                this.dialogVisible = true;
            },

            /**
             * pdf附件预览处理函数
             */
            filePDFPreview(filePath) {
                this.pdfPath =  this.filesysip + filePath;
                this.pdfDialogVisible = true;
            },

            /**
             * 下载文件处理函数
             */
            downloadFile(fileInfo){
                let url = this.filesysip + fileInfo.filePath;
                // window.location.href = url;

                if (this.myBrowser() === "IE" || this.myBrowser() === "Edge") {
                    this.saveAs5(url);
                } else {
                    this.download(url);
                }
    
            },

            /**
             * 删除文件处理函数
             */
            deleteFile(fileInfo){
                this.$axios.delete('filesystem/delete',{
                params:{
                        id: fileInfo.id,
                        fileId: fileInfo.filePath
                }}).then(function (res) {
                    console.log(res);
                }).catch(function (error) {
                    console.log(error);
                });
            },

            /**
             * 判断浏览器类型
             */
            myBrowser() {
                // 取得浏览器的userAgent字符串
                var userAgent = navigator.userAgent; 
                var isOpera = userAgent.indexOf("Opera") > -1;
                // 判断是否Opera浏览器
                if (isOpera) {
                    return "Opera"
                }; 
                // 判断是否Firefox浏览器
                if (userAgent.indexOf("Firefox") > -1) {
                    return "FF";
                }
                //判断是否Chrome浏览器 
                if (userAgent.indexOf("Chrome") > -1) {
                    return "Chrome";
                }
                // 判断是否Safari浏览器
                if (userAgent.indexOf("Safari") > -1) {
                    return "Safari";
                }
                // 判断是否IE浏览器
                if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
                    return "IE";
                };
                // 判断是否Edge浏览器
                if (userAgent.indexOf("Trident") > -1) {
                    return "Edge";
                }
            },

            /**
             * 文件下载类型一
             */
            download(url){
                var iframe = document.createElement("iframe")
                iframe.style.display = "none";
                iframe.src = url;
                document.body.appendChild(iframe);
            },
            /**
             * 另存为
             */
            saveAs5(url) {
                var oPop = window.open(imgURL, "", "width=1, height=1, top=5000, left=5000");
                for (; oPop.document.readyState != "complete";) {
                    if (oPop.document.readyState == "complete") break;
                }
                oPop.document.execCommand("SaveAs");
                oPop.close();
            },

        }
    }

</script>


<style scoped>
    .el-row {
        margin-bottom: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    .todo-item {
        font-size: 14px;
    }

    .todo-item-del {
        text-decoration: line-through;
        color: #999;
    }

    .schart {
        width: 100%;
        height: 300px;
    }

</style>
