<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>可授信项目列表</span>
                    </div>
                    <div class="top-btn-box">
                        <el-button type="warning" @click="add">新增</el-button>
                        <el-button type="warning" @click="add" style="margin-left: 50px">导入</el-button>
                    </div>
                    <div class="project-list-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                            <!--<el-table-column type="selection" width="55" align="center"></el-table-column>-->
                            <el-table-column type="index" label="序号" width="100" align="center">
                            </el-table-column>
                            <el-table-column prop="projectName" label="项目合同名称" align="center">
                            </el-table-column>
                            <el-table-column prop="contractNo" label="合同编号" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="contractAmount" label="合同金额(万元)" align="center">
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
                            <el-table-column label="操作" width="200" align="center">
                                <template slot-scope="scope">
                                    <el-button type="text" @click="findProgress(scope.row.id)"><el-tag type="warning">查看进度</el-tag></el-button>
                                    <el-button size="mini" round @click="editProjectInfo(scope.row.id)">项目资料</el-button>
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
        name: 'ProjectList',
        data() {
            return {
                companyId: localStorage.getItem('userInfoId'),
                loading: true,

                url: './static/vuetable.json',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                editVisible: false,
                delVisible: false,
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
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            getDataList(){
                let _than = this;
                    console.log("companyId: " +  localStorage.getItem('userInfoId'));
                this.$axios.get('api/project/list',{params:{
                        id: this.companyId
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading= false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            search() {
                this.is_search = true;
            },
            formatter(row, column) {
                return row.address;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.idx = index;
                const item = this.tableData[index];
                this.form = {
                    name: item.name,
                    date: item.date,
                    address: item.address
                }
                this.editVisible = true;
            },
            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
            },
            delAll() {
                const length = this.multipleSelection.length;
                let str = '';
                this.del_list = this.del_list.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].name + ' ';
                }
                this.$message.error('删除了' + str);
                this.multipleSelection = [];
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 保存编辑
            saveEdit() {
                this.$set(this.tableData, this.idx, this.form);
                this.editVisible = false;
                this.$message.success(`修改第 ${this.idx+1} 行成功`);
            },
            // 确定删除
            deleteRow(){
                this.tableData.splice(this.idx, 1);
                this.$message.success('删除成功');
                this.delVisible = false;
            },
            // 新增
            add(){
                // 设置路由页面跳转
                this.$router.push('may-credit-project-add');
            },
            // 查看项目进度进度
            findProgress(projectId){
                //
                console.log(projectId);
                this.$router.push({
                    path:'priject-progress-details?id=' + projectId
                })
            },
            // 查看项目资料
            editProjectInfo(projectId){
                this.$router.push({
                    path:'edit-project-info?id=' + projectId
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
