<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>金融产品列表</span>
                    </div>
                    <div class="top-btn-box">
                        <el-input v-model="select_word" placeholder="产品名称" class="search-input mr4"></el-input>
                        <el-button type="warning" icon="search" @click="search">查询</el-button>
                        <el-button type="warning" icon="el-icon-plus" style="margin-left: 20px"@click="add">添加</el-button>
                    </div>
                    <div class="project-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                            <el-table-column type="index" label="序号" width="100" align="center">
                            </el-table-column>
                            <el-table-column prop="product_no" label="产品编号" sortable width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="product_name" label="产品名称" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="base_year_rate" label="年利率" width="150" align="center">
                                <template slot-scope="scope">
                                    <el-tag>{{scope.row.base_year_rate}}%</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column prop="cooperation_bank" label="资金渠道" align="left">
                                <template slot-scope="scope">
                                    {{scope.row.company_full_name + scope.row.cooperation_bank}}
                                </template>
                            </el-table-column>
                            <el-table-column label="状态" align="center" width="150">
                                <template slot-scope="scope">
                                    <el-tag type="warning" v-show="scope.row.status === 1">已上架</el-tag>
                                    <el-tag type="warning" v-show="scope.row.status === 0">未提交</el-tag>
                                    <el-tag type="warning" v-show="scope.row.status === -1">已下架</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="200" align="center">
                                <template slot-scope="scope">
                                    <el-button size="mini" round @click="handleEdit(scope.$index, scope.row.id)">产品信息</el-button>
                                    <el-button size="mini" round @click="handleEdit(scope.$index, scope.row.id)">删除</el-button>
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
        name: 'business-manager-list',
        data() {
            return {
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
                this.getDataList();
            },
            // 获取Table数据
            getDataList(){
                this.$axios.get('fp/list').then((response) => {
                    console.log(response.data.extend);
                    this.tableData = response.data.extend.list;
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
                this.$router.push('product-add');
            }
        }
    }

</script>

<style scoped>
    .table {
        background-color: #ffffff;
    }

    .el-table .warning-row {
        background: #ccc;
    }

    .top-btn-box {
        margin-bottom: 10px;
        /*padding-right: 10px;*/
        text-align: right;
    }

    .top-btn-box .search-input{
        width: 300px;
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
    .el-upload--text {
        width: 110px;
        height: 35px;
    }
</style>
