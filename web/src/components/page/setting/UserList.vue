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
                            <el-table-column prop="userName" label="用户名"/>
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
                                    <el-button type="text" @click="detailsInfo(scope.row.id)"><el-tag type="warning">修改密码</el-tag></el-button>
                                    <el-button type="text" @click="detailsInfo(scope.row.id)"><el-tag type="warning">停止</el-tag></el-button>
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
                let _than = this;
                this.$axios.get('user/list',{params:{
                        id: this.userId
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading= false;
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
        padding-bottom: 10px;
    }

</style>
