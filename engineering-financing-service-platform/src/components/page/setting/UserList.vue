<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>用户管理</span>
                    </div>
                    <!--<div class="top-btn-box">-->
                        <!--<el-button type="warning" @click="add">新增</el-button>-->
                        <!--<el-button type="warning" @click="add" style="margin-left: 50px">导入</el-button>-->
                    <!--</div>-->
                    <div class="project-list-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" >
                            <!--<el-table-column type="selection" width="55" align="center"></el-table-column>-->
                            <el-table-column type="index" label="序号" width="100" align="center"/>
                            <el-table-column prop="username" label="用户名"/>
                            <el-table-column prop="phone" label="手机号" align="center"/>
                            <el-table-column prop="" label="类别" align="center">
                                <template slot-scope="scope">
                                    <el-tag type="warning">{{scope.row.userType | classifyToText}}</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column prop="name" label="状态" align="center">
                                <template slot-scope="scope">
                                    <el-tag type="warning">{{scope.row.status | statusToText}}</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" align="center">
                                <template slot-scope="scope">
                                    <el-button type="text" @click="detailsInfo(scope.row.id)">修改密码</el-button>
                                    <el-button type="text" @click="detailsInfo(scope.row.id)">停止</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <!--<div class="pagination">-->
                            <!--<el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">-->
                            <!--</el-pagination>-->
                        <!--</div>-->
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'user-list',
        data() {
            return {
                userId: localStorage.getItem('companyId'),
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
            // 获取Table数据
            this.getDataList();
        },
        computed: {
        },
        // 过滤器
        filters: {
            classifyToText(oldValue) {
                if(oldValue === 1){
                    return '一类'
                }
                if(oldValue === 2){
                    return '二类'
                }
                return '未知';
            },
            statusToText(intValue) {
                if(intValue === 1){
                    return '正常'
                }
                return '停止';
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            getDataList(){
                this.$axios.get('api/user/list',{params:{
                        id: this.userId
                    }}).then(res => {
                    console.log(res);
                    this.tableData = res.data.extend.list;
                    this.loading= false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 添加商务经理
            add(id){
                this.$router.push({
                    path:'businessmanager-add?id=' + id
                })
            },

            // 查看详情信息
            detailsInfo(projectId){
                this.$router.push({
                    path:'businessmanager-info-details?id=' + projectId
                })
            }
        }
    }

</script>

<style scoped>
    .top-btn-box{
        padding-bottom: 10px;
    }

</style>
