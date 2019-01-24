<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb class="breadcrumb" separator="/">
                <el-breadcrumb-item class="breadcrumb-item">
                    <!--<i class="el-icon-lx-cascades"></i> -->
                    工程公司列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container" >
            <div class="handle-box">
                <!--<el-input v-model="select_word" placeholder="项目名称/姓名" class="handle-input mr10"></el-input>-->
                <el-button type="primary" icon="search" @click="add">新增</el-button>
                <!--<el-button type="primary" icon="search" @click="add" style="margin-left: 300px">新增</el-button>-->
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable">
                <el-table-column type="index" label="序号" width="100" align="center">
                </el-table-column>
                <el-table-column prop="companyName" label="公司名称" sortable align="center">
                </el-table-column>
                <el-table-column prop="person" label="企业法人" align="center" width="120">
                </el-table-column>
                <el-table-column prop="creditCodeNumber" label="社会统一信用代码证" align="center" width="200">
                </el-table-column>
                <el-table-column prop="companyFullName" label="授信资方" align="center" width="250">
                </el-table-column>
                <el-table-column prop="creditAmount" label="担保授信额（万元）" align="center" width="200">
                    <template slot-scope="scope">
                        {{ scope.row.creditAmount }}万
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="220" align="left">
                    <template slot-scope="scope">
                        <el-button type="primary" plain size="mini" @click="findEngComInfo(scope.row.id)">公司信息</el-button>
                        <el-button type="warning" plain size="mini" v-show="scope.row.creditAmount === null" @click="addCreditInfo(scope.row.id)">添加授信信息</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--分页条-->
            <!--<div class="pagination">-->
                <!--<el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">-->
                <!--</el-pagination>-->
            <!--</div>-->
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="添加授信信息" :visible.sync="is_addCredit" width="25%" center>
            <el-form ref="form" :model="form" label-width="150px">
                <el-form-item label="资金方">
                    <el-select v-model="creditForm.fComId" placeholder="请选择">
                        <el-option v-for=" item in options " :key="item.id" :label="item.companyFullName" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="担保授信额度">
                    <el-input v-model="creditForm.creditAmount" style="width: 220px"></el-input>万元
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="is_addCredit = false">取 消</el-button>
                <el-button type="primary" @click="saveCieditInfo">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                url: './static/vuetable.json',
                tableData: [],
                options: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                is_addCredit: false,
                editVisible: false,
                delVisible: false,
                creditForm:{
                    comId: null,
                    fComId: null,
                    creditAmount: null,
                },
                form: {
                    name: '',
                    date: '',
                    address: ''
                },
                idx: -1
            }
        },
        created() {
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
                this.$axios.get('api/engcom/list').then((response) => {
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
            // 添加授信信息
            addCreditInfo(companyId){
                this.is_addCredit = true;
                this.$axios.get('fc/options').then((response) => {
                    this.options = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
                this.creditForm.comId = companyId;
            },
            // 保存授信信息
            saveCieditInfo(){
                let _that
                this.is_addCredit = false;
                this.$axios.put('api/engcom//update',
                    this.qs.stringify(
                        {
                            comId: this.creditForm.comId,
                            fComId: this.creditForm.fComId,
                            amount: this.creditForm.creditAmount,
                        }
                    )).then(function (response) {
                    // console.log(response);
                    _that.getDataList();
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 新增
            add(){
                // 设置路由页面跳转
                this.$router.push('addengineeringcompany');
            },
            // 企业信息
            findEngComInfo(companyId){
                this.$router.push({path:'find-eng-com-info?id=' + companyId})
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
        text-align: right;
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
