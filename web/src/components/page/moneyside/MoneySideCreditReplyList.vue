<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb class="breadcrumb" separator="/">
                <el-breadcrumb-item class="breadcrumb-item">
                    <!--<i class="el-icon-lx-cascades"></i> -->
                    授信批复列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container" >
            <div class="handle-box">
                <el-input v-model="select_word" placeholder="项目名称/姓名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">查询</el-button>
                <!--<el-button type="primary" icon="search" @click="add" style="margin-left: 300px">新增</el-button>-->
            </div>
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
                <el-button type="primary" @click="save">确 定</el-button>
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
                this.$axios.get('credit/fund/list', {
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

            },
            gotoReply(id, creditType){
                this.form.creditId = id;
                this.replyVisible = true;
                this.form.creditType = creditType;
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
