<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>授信列表</span>
                    </div>
                    <!--<div class="top-btn-box">-->
                        <!--<el-button type="warning" @click="add">新增</el-button>-->
                        <!--<el-button type="warning" @click="add" style="margin-left: 50px">导入</el-button>-->
                    <!--</div>-->
                    <div class="project-list-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" >
                            <!--<el-table-column type="selection" width="55" align="center"></el-table-column>-->
                            <el-table-column type="index" label="序号" width="100" align="center"/>
                            <el-table-column prop="creditNo" label="申请编号"/>
                            <el-table-column prop="date" label="申请日期" width="200" align="center"/>
                            <el-table-column prop="name" label="申请人" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="projectName" label="项目名称" align="center"/>
                            <el-table-column label="项目进度" width="100" align="center">
                                <template slot-scope="scope">
                                    <div @click="findProgress(scope.row.id)">
                                    <el-progress :text-inside="true" :stroke-width="18" :percentage="scope.row.projectProgress" color="#ff8208"></el-progress>
                                    </div>
                                </template>
                            </el-table-column>
                            <el-table-column label="申请授信金额(万元)" width="150" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.applyAmount}}万
                                </template>
                            </el-table-column>
                            <el-table-column label="审批进度" width="150" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.step + scope.row.status}}
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="200">
                                <template slot-scope="scope">
                                    <!--<el-button type="text" @click="findProgress(scope.row.id)"><el-tag type="warning">进度详情</el-tag></el-button>-->
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
                this.$axios.get('credit/f/list',{params:{
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
            },
            // 查看详情信息
            detailsInfo(id){
                this.$router.push({
                    path:'credit-engloan-info?id=' + id
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
