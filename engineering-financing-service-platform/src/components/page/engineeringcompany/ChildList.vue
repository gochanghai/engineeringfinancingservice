<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>分公司列表</span>
                    </div>
                    <div class="top-btn-box">
                        <el-button type="warning" @click="add">新增</el-button>
                        <!--<el-button type="warning" @click="add" style="margin-left: 50px">导入</el-button>-->
                    </div>
                    <div class="project-list-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" >
                            <!--<el-table-column type="selection" width="55" align="center"></el-table-column>-->
                            <el-table-column type="index" label="序号" width="100" align="center"/>
                            <el-table-column prop="companyName" label="公司名称" width="200"/>
                            <el-table-column prop="contactPerson" label="联系人" align="center"/>
                            <el-table-column prop="phone" label="联系电话" align="center"/>
                            <el-table-column prop="address" label="公司地址"/>
                            <el-table-column label="操作" align="center">
                                <template slot-scope="scope">
                                    <el-button type="text" @click="detailsInfo(scope.row.id)">详情信息</el-button>
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
                companyId: localStorage.getItem('companyId'),
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
                this.$axios.get('api/company/branch/list',{params:{
                        companyId: localStorage.getItem('companyId'),
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading= false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 添加分公司
            add(){
                let id = this.userId;
                this.$router.push({
                    path:'childcompany-add?id=' + id
                })
            },

            // 添加分公司
            detailsInfo(id){
                this.$router.push({
                    path:'childcompany-info?id=' + id
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
