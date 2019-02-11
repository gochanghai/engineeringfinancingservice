<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>我的项目</span>
                    </div>
                    <!--<div class="top-btn-box">-->
                        <!--<el-button type="warning" @click="add">新增</el-button>-->
                        <!--<el-button type="warning" @click="add" style="margin-left: 50px">导入</el-button>-->
                    <!--</div>-->
                    <div class="project-list-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" >
                            <!--<el-table-column type="selection" width="55" align="center"></el-table-column>-->
                            <el-table-column type="index" label="序号" width="100" align="center">
                            </el-table-column>
                            <el-table-column prop="projectName" label="项目合同名称" align="left">
                            </el-table-column>
                            <el-table-column prop="contractNo" label="合同编号" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="contractAmount" label="合同金额(万元)" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="companyName" label="所属分公司" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="name" label="商务经理" width="100" align="center">
                            </el-table-column>
                            <el-table-column label="预授信金额(万元)" width="150" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.shouldCreditAmount}}万
                                </template>
                            </el-table-column>
                            <el-table-column label="项目进度" width="100" align="center">
                                <template slot-scope="scope">
                                    <el-progress :text-inside="true" :stroke-width="18" :percentage="scope.row.projectProgress" color="#ff8208"></el-progress>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="100" align="center">
                                <template slot-scope="scope">
                                    <el-button type="text" @click="findProgress(scope.row.id)"><el-tag type="warning">查看进度</el-tag></el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="pagination">
                            <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">
                            </el-pagination>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'my-project-list',
        data() {
            return {
                userId: localStorage.getItem('userInfoId'),
                loading: true,
                tableData: [],
                cur_page: 1,
                form: {
                    name: '',
                    date: '',
                    address: ''
                },
                idx: -1
            }
        },
        created() {
            // this.getData();
            // 获取Table数据
            this.getDataList();
        },
        computed: {
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            getDataList(){
                let _than = this;
                this.$axios.get('api/project/list',{params:{
                        id: this.userId
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading= false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 查看项目进度进度
            findProgress(projectId){
                //
                console.log(projectId);
                this.$router.push({
                    path:'priject-progress-details?id=' + projectId
                })
            }
        }
    }

</script>

<style scoped>
    .top-btn-box{
        padding-bottom: 20px;
    }

</style>
