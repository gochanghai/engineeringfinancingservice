<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>商务经理列表</span>
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
                            <el-table-column prop="name" label="姓名" width="200">
                            </el-table-column>
                            <el-table-column prop="" label="性别" width="100" align="center">
                                <template slot-scope="scope">
                                    <el-tag type="warning">{{scope.row.gender | genderToText}}</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column prop="phone" label="手机号" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="jobYearNumber" label="从业年限" width="200" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.jobYearNumber}}年
                                </template>
                            </el-table-column>
                            <el-table-column prop="cooperateYearNumber" label="合作年限" width="100" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.cooperateYearNumber}}年
                                </template>
                            </el-table-column>
                            <el-table-column prop="childCompanyName" label="所属分公司"/>
                            <el-table-column prop="name" label="状态" width="100" align="center">
                                <template slot-scope="scope">
                                    <el-tag type="warning">{{scope.row.status | statusToText}}</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="200" align="center">
                                <template slot-scope="scope">
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
            statusToText(intValue) {
                if(intValue === 1){
                    return '已提交'
                }
                return '未提交';
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            getDataList(){
                let _than = this;
                this.$axios.get('financier/list',{params:{
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
            detailsInfo(projectId){
                //
                console.log(projectId);
                this.$router.push({
                    path:'businessmanager-info-details?id=' + projectId
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
