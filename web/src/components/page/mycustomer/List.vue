<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>我的客户列表</span>
                    </div>
                    <div class="top-btn-box">
                        <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                        <el-button type="primary" icon="el-icon-plus" @click="add">查询</el-button>
                    </div>
                    <div class="customer-list-box">
                        <el-table :data="data" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                            <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
                            <el-table-column prop="name" label="姓名">
                            </el-table-column>
                            <el-table-column prop="name" label="性别">
                            </el-table-column>
                            <el-table-column prop="name" label="手机号">
                            </el-table-column>
                            <el-table-column prop="date" label="出生年月">
                            </el-table-column>
                            <el-table-column prop="name" label="婚姻状况">
                            </el-table-column>
                            <el-table-column prop="name" label="工程贷授信总额度">
                            </el-table-column>
                            <el-table-column prop="name" label="工程贷已放款总金额">
                            </el-table-column>
                            <el-table-column prop="name" label="工程贷在贷总金额">
                            </el-table-column>
                            <el-table-column prop="name" label="违约次数">
                            </el-table-column>
                            <!--<el-table-column label="操作" width="150" align="center">-->
                            <!--<template slot-scope="scope">-->
                            <!--<el-tag type="warning" >编辑</el-tag>-->
                            <!--<el-tag type="warning" >删除</el-tag>-->
                            <!--&lt;!&ndash;<el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>&ndash;&gt;-->
                            <!--&lt;!&ndash;<el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>&ndash;&gt;-->
                            <!--</template>-->
                            <!--</el-table-column>-->
                        </el-table>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'MyCustomer',
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
            this.getData();
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
            // 获取 easy-mock 的模拟数据
            getData() {
                // 开发环境使用 easy-mock 数据，正式环境使用 json 文件
                if (process.env.NODE_ENV === 'development') {
                    this.url = '/ms/table/list';
                };
                this.$axios.post(this.url, {
                    page: this.cur_page
                }).then((res) => {
                    this.tableData = res.data.list;
                })
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
            // 添加分公司
            add(companyId){
                this.$router.push({path: '/add-child-com?id=' + companyId});
            }
        }
    }

</script>

<style scoped>

    .table {
        background-color: #ffffff;
    }

    .crumbs {
        padding-left: 20px;
    }

    .breadcrumb {
        height: 30px;
        padding-left: 20px;
        border-left: 4px solid #ff8208;
    }

    .breadcrumb-item {
        line-height: 30px;
        font-size: 18px;
    }

    .handle-box {
        margin-bottom: 20px;
        padding-right: 50px;
        float: right;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
    .red{
        color: #ff0000;
    }
</style>

