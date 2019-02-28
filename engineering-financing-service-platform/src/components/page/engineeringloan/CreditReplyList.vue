<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>授信批复列表</span>
                    </div>
                    <div class="top-btn-box">
                        <el-input v-model="select_word" placeholder="项目名称/姓名" class="search-input mr4"></el-input>
                        <el-button type="warning" icon="search" @click="search">查询</el-button>
                    </div>
                    <div class="project-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                            <el-table-column type="index" label="序号" width="50" align="center">
                            </el-table-column>
                            <el-table-column prop="name" label="借款人" sortable width="100" align="center">
                            </el-table-column>
                            <el-table-column prop="companyName" label="所属核心企业" align="center">
                            </el-table-column>
                            <el-table-column prop="phone" label="手机号" width="120" align="center">
                            </el-table-column>
                            <el-table-column prop="applyAmount" label="申请额度" width="100" align="center">
                            </el-table-column>
                            <el-table-column prop="projectName" label="申请项目名称" width="200" align="center">
                            </el-table-column>
                            <el-table-column prop="projectName" label="甲方名称" width="200" align="center">
                            </el-table-column>
                            <el-table-column label="审批进度" width="150" align="center">
                                <template slot-scope="scope">
                                    <el-tag type="warning" v-show="scope.row.step === 4 && scope.row.status === 0">待我批复</el-tag>
                                    <el-tag type="warning" v-show="scope.row.step === 5 && scope.row.status === 0">待我发起协议</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="200" align="center">
                                <template slot-scope="scope">
                                    <el-button type="primary" size="mini" round @click="gotoInfoDetails(scope.row.id)">详情</el-button>
                                    <el-button type="warning" size="mini" round v-show="scope.row.step === 4" @click="gotoReply(scope.row.id,scope.row.creditType)">去批复</el-button>
                                    <el-button type="warning" size="mini" round v-show="scope.row.step === 5" @click="gotoReply(scope.row.id,scope.row.creditType)">去发起协议</el-button>
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

        <!-- 批复弹出框 -->
        <el-dialog title="批复意见" :visible.sync="replyVisible" center width="30%">
            <el-form ref="form" :model="form" label-width="120px">
                <el-form-item label="是否可授信" v-show="form.creditType === 1">
                    <el-radio-group v-model="form.fResult">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                        <el-radio :label="3">特批(有条件同意)</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="是否可授信" v-show="form.creditType === 2">
                    <el-radio-group v-model="form.fResult">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="授信金额"  v-show="this.form.fResult === 1">
                    <el-input v-model="form.fAmount" style="width: 100px"></el-input> 万
                </el-form-item>
                <el-form-item label="授信期限"  v-show="this.form.fResult === 1" style="width: 250px">
                    <el-date-picker
                            v-model="sxDate"
                            value-format="yyyy-MM-dd"
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="还款方式"  v-show="this.form.fResult === 1">
                    <el-input v-model="form.fRepayType" style="width: 350px"></el-input>
                </el-form-item>
                <el-form-item label="批复文件"  v-show="this.form.fResult === 1">
                </el-form-item>
                <el-form-item label="拒绝原因"  v-show="this.form.fResult === -1">
                    <el-input type="textarea" v-model="form.fDesc" placeholder="请输入拒绝原因"  style="width: 350px"></el-input>
                </el-form-item>
                <el-form-item label="补充意见"  v-show="this.form.fResult === 2">
                    <el-input type="textarea" v-model="form.fSpecialDesc" placeholder="请输入补充资料" style="width: 350px"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="replyVisible = false">取 消</el-button>
                <el-button type="primary" class="btn-submit" @click="save">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'CreditReplyList',
        data() {
            return {
                userId: localStorage.getItem('userInfoId'),
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

                replyVisible: false,
                sxDate: null,
                form: {
                    fResult: 1,
                    fAmount: '',
                    fRepayType: '',
                    fCreditStartDate: '',
                    fCreditEndDate: '',
                    fReplyFile: '',
                    fSpecialDesc: '',
                    fDesc: '',
                    fPerson: null,
                    creditType: null,
                },
                idx: -1
            }
        },
        created() {
            this.getCreditDataList();
        },
        // 监听器
        watch: {
            sxDate:function() {
                this.form.fCreditStartDate = this.sxDate[0];
                this.form.fCreditEndDate = this.sxDate[1];
            },
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
            // 获取项目数据
            getCreditDataList() {
                let _than = this;
                this.$axios.get('api/credit/f_list', {
                    params: {
                        id: this.userId
                    }
                }).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
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

            gotoInfoDetails(id){
                this.$router.push("credit-info-details?id=" + id);
            },
            gotoReply(id, creditType){
                // this.form.creditId = id;
                // this.replyVisible = true;
                // this.form.creditType = creditType;
                this.$router.push("credit-info-details?id=" + id);
            },

            // 保存
            save(){
                // this.$refs['form'].validate((valid) => {
                //     if (!valid) {
                //         return;
                //     }
                // });
                this.$axios.post('credit-appr/ec/save',
                    this.qs.stringify(
                        {
                            creditId:this.form.creditId,
                            fResult: this.form.fResult,
                            fAmount: this.form.fAmount,
                            fRepayType: this.form.fRepayType,
                            fCreditStartDate: this.form.fCreditStartDate,
                            fCreditEndDate: this.form.fCreditEndDate,
                            fReplyFile: this.form.fReplyFile,
                            fSpecialDesc: this.form.fSpecialDesc,
                            fDesc: this.form.fDesc,
                            creditType: this.form.creditType,
                            fPerson: this.fPerson,
                        }
                    )).then(function (response) {
                    console.log(response);
                    this.$message.success('提交成功！');
                    this.$router.push("fund-credit-apply-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
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

    .top-btn-box-l {
        margin-bottom: 10px;
        /*padding-right: 10px;*/
        text-align: left;
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
