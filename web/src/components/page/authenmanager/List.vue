<template>
    <div>
        <el-row :gutter="20">
            <el-col>
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>认证管理列表</span>
                    </div>
                    <el-table :data="tableData" border class="table" v-loading="loading" ref="multipleTable">
                        <el-table-column type="index" label="序号" width="50" align="center"/>
                        <el-table-column prop="name" label="姓名" width="200" align="left"/>
                        <el-table-column prop="birthdate" label="出生日期" width="100" align="center"/>
                        <el-table-column label="性别" width="120" align="center">
                            <template slot-scope="scope">
                                {{scope.row.gender | genderToText}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="phone" label="手机号" align="left" width="200"/>
                        <el-table-column prop="companyName" label="所属公司" align="left"/>
                        <el-table-column prop="status" label="状态" align="center" width="100">
                            <template slot-scope="scope">
                                {{scope.row.status | statusToText}}
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="150" align="center">
                            <template slot-scope="scope">
                                <el-button size="mini" round @click="handleEdit(scope.$index, scope.row)">详情</el-button>
                                <!-- <el-button size="mini" round @click="handleEdit(scope.$index, scope.row)">删除</el-button> -->
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
        // 过滤器
        filters: {
            genderToText(oldValue) {
                if(oldValue === 'M'){
                    return '男'
                }
                if(oldValue === 'F'){
                    return '女'
                }
                return '未知';
            },
            statusToText(value) {
                if(1 === value){
                    return '待审核'
                }
                if(2 === value){
                    return '已认证'
                }
                return '未认证';
            }
        },
        created(){
            this.getCreditDataList();
        },
        methods: {

            // 获取列表数据
            getCreditDataList() {
                let _than = this;
                this.$axios.get('api/business/all').then(function (res) {
                    console.log(res);
                    _than.tableData = res.data.extend.list;
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
