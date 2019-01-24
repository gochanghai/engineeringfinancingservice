<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb class="breadcrumb" separator="/">
                <el-breadcrumb-item class="breadcrumb-item">
                    <!--<i class="el-icon-lx-cascades"></i> -->
                    客户管理列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container" >
            <div class="handle-box">
                <el-radio-group v-model="labelPosition" size="small">
                    <el-radio-button label="financier">融资人</el-radio-button>
                    <el-radio-button label="fundCompany">资金方</el-radio-button>
                    <el-radio-button label="enginneringCompany">工程公司</el-radio-button>
                </el-radio-group>
                <!--<el-input v-model="select_word" placeholder="项目名称/姓名" class="handle-input mr10"></el-input>-->
                <!--<el-button type="primary" icon="search" @click="add">新增</el-button>-->
                <!--<el-button type="primary" icon="search" @click="add" style="margin-left: 300px">新增</el-button>-->
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="index" label="序号" width="100" align="center">
                </el-table-column>
                <el-table-column prop="companyName" label="公司名称" sortable width="200" align="center">
                </el-table-column>
                <el-table-column prop="enterpriseLegalPerson" label="企业法人" width="200">
                </el-table-column>
                <el-table-column prop="socialUnifiedCreditCodeNumber" label="社会统一信用代码证" width="150">
                </el-table-column>
                <el-table-column prop="companyName" label="授信资方">
                </el-table-column>
                <el-table-column prop="enterpriseLegalPerson" label="担保授信额（万元）" width="200">
                </el-table-column>
                <!--<el-table-column prop="name" label="状态" width="200">-->
                <!--</el-table-column>-->
                <el-table-column label="操作" width="200" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" @click="handleEdit(scope.$index, scope.row)">公司信息</el-button>
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">删除</el-button>
                        <!--<el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>-->
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="50px">
                <el-form-item label="日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date" value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
        </el-dialog>
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
                labelPosition: "financier",
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
            // 获取Table数据
            getDataList(){
                console.log("123list");
                this.$axios.get('http://192.168.1.98:8088/ec/list').then((response) => {
                    console.log(response.data.extend);
                    this.tableData = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },

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
            // 新增
            add(){
                // 设置路由页面跳转
                this.$router.push('addengineeringcompany');
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

    .container {
        padding-left: 30px;
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }

    .handle-box {
        color: #606266;
        margin-bottom: 20px;
        margin-right: 100px;
        text-align: center;
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
</style>
