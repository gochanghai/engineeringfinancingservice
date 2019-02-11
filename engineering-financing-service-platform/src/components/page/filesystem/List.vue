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
                                    <img v-if="scope.row.fileType === '.pdf'" src="../../../../static/img/pdf.jpg" width="23px" height="23px" @click="filePDFPreview(scope.row.filePath)"/>
                                    <img v-else="scope.row.fileType === '.jpg'" :src="filesysip + scope.row.filePath" width="23px" height="23px" @click="filePreview(scope.row.filePath)"/>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="150" align="center">
                            <template slot-scope="scope">
                                <el-button size="mini" round @click="handleEdit(scope.$index, scope.row)">下载</el-button>
                                <el-button size="mini" round @click="handleEdit(scope.$index, scope.row)">删除</el-button>
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
            this.getCreditDataList();
        },
        methods: {

            // 获取文件列表数据
            getCreditDataList() {
                let _than = this;
                this.$axios.get('filesystem/all').then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading = false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            filePreview(filePath) {
                this.dialogImageUrl =  this.filesysip + filePath;
                this.dialogVisible = true;
            },
            filePDFPreview(filePath) {
                this.pdfPath =  this.filesysip + filePath;
                this.pdfDialogVisible = true;
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
