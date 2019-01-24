<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb class="breadcrumb" separator="/">
                <el-breadcrumb-item class="breadcrumb-item">
                    <!--<i class="el-icon-lx-cascades"></i> -->
                    授信项目列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container" >
            <div class="handle-box">
                <el-button type="warning" icon="search" @click="addCredit">新增授信</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable">
                <el-table-column type="index" label="序号" width="50" align="center">
                </el-table-column>
                <el-table-column prop="creditNo" label="申请编号" sortable width="200" align="center">
                </el-table-column>
                <el-table-column prop="date" label="申请日期" width="120" align="center">
                </el-table-column>
                <el-table-column prop="name" label="申请人" width="150" align="center">
                </el-table-column>
                <el-table-column prop="projectName" label="项目名称" align="center">
                </el-table-column>
                <el-table-column label="项目进度" width="150" align="center">
                    <template slot-scope="scope">
                        <el-progress :text-inside="true" :stroke-width="18" :percentage="scope.row.projectProgress" color="#ff8208"></el-progress>
                        <!--<el-progress type="circle" :percentage="scope.row.projectProgress" :width='30'></el-progress>-->
                    </template>
                </el-table-column>
                <el-table-column label="申请授信额" width="150" align="center">
                    <template slot-scope="scope">
                        {{scope.row.applyAmount}}.00万
                    </template>
                </el-table-column>
                <el-table-column label="审批进度" width="150" align="center">
                    <template slot-scope="scope">
                        <el-tag type="warning" v-show="scope.row.step > 0 ">审批中</el-tag>
                        <el-tag type="warning" v-show="scope.row.step === 0 ">未提交</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="200" align="left">
                    <template slot-scope="scope">
                        <el-button type="warning" size="mini" plain @click="goToDetail(scope.row.id)">详情</el-button>
                        <el-button type="warning" size="mini" @click="edit" v-show="scope.row.step === 0 ">编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--<div class="pagination">-->
                <!--<el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">-->
                <!--</el-pagination>-->
            <!--</div>-->
        </div>
    </div>
</template>

<script>
    export default {
        name: 'CreditProjectList',
        data() {
            return {
                userId: localStorage.getItem('userInfoId'),
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                editVisible: false,
                delVisible: false,
            }
        },
        created() {
            this.getProjectList();
        },
        computed: {
            data() {
                return this.tableData.filter((d) => {
                    let is_del = false;
                    for (let i = 0; i < this.del_list.length; i++) {
                        if (d.name === this.del_list[i].name) {
                            is_del = true;
                            break;
                        }
                    }
                    if (!is_del) {
                        if (d.address.indexOf(this.select_cate) > -1 &&
                            (d.name.indexOf(this.select_word) > -1 ||
                                d.address.indexOf(this.select_word) > -1)
                        ) {
                            return d;
                        }
                    }
                })
            }
        },
        methods: {
            // 获取项目数据
            getProjectList() {
                let _than = this;
                this.$axios.get('credit/f/list', {
                    params: {
                        id: this.userId
                    }
                }).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            // 新增授信
            addCredit() {
                // 设置路由页面跳转
                this.$router.push('add-project-credit-apply');
            },
            // 编辑
            edit() {
                // 设置路由页面跳转
                this.$router.push('add-project-credit-apply');
            },
            goToDetail(projectId) {
                this.$router.push({
                    path:'project-credit-apply-detail?id=' + projectId
                })
            }
        },
    }

</script>

<style scoped>

    .table {
        background-color: #ffffff;
    }

    .crumbs {
        padding-left: 20px;
    }

    .breadcrumb {
        height: 30px;
        padding-left: 20px;
        border-left: 4px solid #ff8208;
    }

    .breadcrumb-item {
        line-height: 30px;
        font-size: 18px;
    }

    .container {
        padding-left: 30px;
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }

    .handle-box {
        color: #606266;
        margin-bottom: 20px;
        margin-right: 20px;
        padding-right: 10px;
        text-align: right;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
</style>
