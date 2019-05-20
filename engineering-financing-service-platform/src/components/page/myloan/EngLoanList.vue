<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>放款列表</span>
                    </div>
                    <div class="top-btn-box">
                        <el-button type="warning" @click="add">新增</el-button>
                        <!--<el-button type="warning" @click="add" style="margin-left: 50px">导入</el-button>-->
                    </div>
                    <div class="project-list-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" >
                            <!--<el-table-column type="selection" width="55" align="center"></el-table-column>-->
                            <el-table-column type="index" label="序号" width="100" align="center"/>
                            <el-table-column prop="applyNo" label="申请编号"/>
                            <el-table-column prop="applyDate" label="申请日期" align="center"/>
                            <el-table-column prop="name" label="申请人" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="projectName" label="项目名称" align="center"/>
                            <el-table-column label="本次申请放款金额(万元)" width="200" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.applyAmount}}万
                                </template>
                            </el-table-column>
                            <el-table-column label="状态" width="150" align="center">
                                <template slot-scope="scope">
                                    <div @click="findProgress(scope.row.id)">
                                    {{scope.row.status | statusToText}}
                                    </div>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="200">
                                <template slot-scope="scope">
                                    <!--<el-button type="text" @click="findProgress(scope.row.id)"><el-tag type="warning">进度详情</el-tag></el-button>-->
                                    <el-button type="text" @click="detailsInfo(scope.row.id)">详情信息</el-button>
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
                this.$axios.get('la/f/list',{params:{
                        id: this.userId
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading= false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 查看详情信息
            detailsInfo(id){
                this.$router.push({
                    path:'loan-apply-info?id=' + id
                })
            },
            add(){
                this.$router.push({
                    path:'engineeringloan-loan-add'
                })
            }
        },
        // 过滤器
        filters: {
            statusToText(oldValue) {
                switch (oldValue) {
                    case '-1':
                        return '未提交';
                        break;
                    case '5':
                        return '待放款';
                        break;
                    default:
                        return '审批中';
                        break;
                }
            },
        },
    }

</script>

<style scoped>
    .top-btn-box{
        padding-bottom: 10px;
    }

</style>
