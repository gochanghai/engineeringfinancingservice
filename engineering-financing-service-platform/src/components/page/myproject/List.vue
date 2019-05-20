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
                            <el-table-column type="index" label="序号" width="100" align="center"/>
                            <el-table-column prop="projectName" label="项目合同名称"/>
                            <el-table-column prop="contractNo" label="合同编号" width="200" align="center"/>
                            <el-table-column prop="contractAmount" label="合同金额(万元)" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="childCompany.companyName" label="所属分公司" align="center"/>
                            <el-table-column label="预授信金额(万元)" width="150" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.shouldCreditAmount}}万
                                </template>
                            </el-table-column>
                            <el-table-column label="项目进度" width="100" align="center">
                                <template slot-scope="scope">
                                    <div @click="findProgress(scope.row.id)">
                                    <el-progress :text-inside="true" :stroke-width="18" :percentage="scope.row.projectProgress" color="#ff8208"></el-progress>
                                    </div>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="200">
                                <template slot-scope="scope">
                                    <!-- <el-button type="text" @click="findProgress(scope.row.id)"><el-tag type="warning">进度详情</el-tag></el-button> -->
                                    <el-button type="text" @click="detailsInfo(scope.row.id)"><el-tag type="warning">详情信息</el-tag></el-button>
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
        name: 'my-project',
        data() {
            return {
                userId: localStorage.getItem('userId'),
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
                this.$axios.get('api/project/list/bm',{params:{
                        userId: this.userId
                    }}).then(function (res) {
                    console.log(res);
                    _than.tableData = res.data.extend.list;
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
                    path:'project-progress-details?id=' + projectId
                })
            },
            // 查看项目详情信息
            detailsInfo(projectId){
                //
                console.log(projectId);
                this.$router.push({
                    path:'project-details?id=' + projectId
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
