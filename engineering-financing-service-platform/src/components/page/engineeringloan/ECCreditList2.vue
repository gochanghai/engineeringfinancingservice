<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>授信申请列表</span>
                    </div>
                    <div class="top-btn-box">
                        状态
                        <el-select v-model="select_cate" placeholder="请选择" class="status-input mr4">
                            <el-option key="1" label="待完善项目资料" value="1001"></el-option>
                            <el-option key="2" label="待提交" value="1002"></el-option>
                        </el-select>
                        <el-input v-model="select_word" placeholder="请输入融资人或项目名称或合同编号" class="search-input mr4"></el-input>
                        <el-button type="warning" icon="search" @click="search">查询</el-button>
                    </div>
                    <div class="project-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                            <el-table-column type="index" label="序号" width="50" align="center">
                            </el-table-column>
                            <el-table-column prop="applyNo" label="申请编号" sortable width="160" align="center">
                            </el-table-column>
                            <el-table-column prop="applyDate" label="申请日期" width="100" align="center">
                            </el-table-column>
                            <el-table-column prop="name" label="申请人" width="80" align="center">
                            </el-table-column>
                            <el-table-column prop="projectName" label="项目名称" align="center">
                            </el-table-column>
                            <el-table-column label="项目进度" width="100" align="center">
                                <template slot-scope="scope">
                                    <el-progress :text-inside="true" :stroke-width="18" :percentage="scope.row.projectProgress" color="#ff8208"></el-progress>
                                    <!--<el-progress type="circle" :percentage="scope.row.projectProgress" :width="40"></el-progress>-->
                                </template>
                            </el-table-column>
                            <el-table-column prop="applyAmount" label="申请授信额(万)" width="120" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.applyAmount}}.00万
                                </template>
                            </el-table-column>
                            <el-table-column prop="companyName" label="工程公司" width="150" align="center">
                            </el-table-column>
                            <el-table-column prop="companyName" label="担保企业" width="150" align="center">
                            </el-table-column>
                            <el-table-column prop="fcompanyName" label="资金渠道" width="150" align="center">
                            </el-table-column>
                            <el-table-column label="审批进度" width="80" align="center">
                                <template slot-scope="scope">
                                    <el-tag type="warning">
                                        {{scope.row.step +'-'+ scope.row.status | statusToText}}
                                    </el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="180" align="left">
                                <template slot-scope="scope">
                                    <el-button type="warning" size="mini" @click="gotoInfoDetails(scope.row.id)" >详情</el-button>
                                    <el-button type="warning" size="mini" v-show="scope.row.step === 2 && scope.row.status === 0" @click="gotoApprove(scope.row.id)">去审批</el-button>
                                    <el-button type="warning" v-show="scope.row.step === 6" v-if="scope.row.status === 0 || scope.row.status === 1" @click="agreemenBut(scope.row.id)">签署协议</el-button>
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

        <!-- 审批弹出框 -->
        <el-dialog :visible.sync="auditVisible" width="25%" center="">
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item>
                    <el-form-item>
                        <el-radio-group v-model="form.pResult">
                            <el-radio :label="1">是</el-radio>
                            <el-radio :label="-1">否</el-radio>
                            <el-radio :label="2">退回</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form-item>
                <el-form-item label="说明"  v-show="this.form.pResult === 1">
                    <el-input type="textarea" v-model="form.pDesc" placeholder="请输入通过说明" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="拒绝原因"  v-show="this.form.pResult === -1">
                    <el-input type="textarea" v-model="form.pDesc" placeholder="请输入拒绝原因" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="退回原因"  v-show="this.form.pResult === 2">
                    <el-input type="textarea" v-model="form.pDesc" placeholder="请输入退回原因" style="width: 300px"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="info" @click="auditVisible = false">取 消</el-button>
                <el-button type="warning" @click="save">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 签署协议弹窗 -->
        <el-dialog title="担保合同" :visible.sync="agreementVisible" width="700px" center="">
            <div>
                二，甲方承诺：
                1，向乙房申请购房贷款银行或贷款银行认可的机构提供符合要求的房屋资料以备查核.
                2，保证对出售的房屋拥有独立产权.如果该房屋为共有房屋，则必须取得其他所有共有人的同意出售书面文件.
                3，保证该出售房屋未予出租.因出租所产生的任何问题由甲方承担并负责解决.
                4，自签订本协议起，保证将该房屋按约定价格售给乙方，期间不得反悔或将房屋出售给第三人.
                5，按照前述业务的需要，及时签订各项合同文件和办理各种手续.
                6，在办理产权过户时，应依要求将房屋产权资料交付贷款银行或贷款银行认可的机构持有.
                三，乙方承诺：
                1，向贷款银行或贷款银行认可的机构提供符合要求的资料以备查核，并依规定支付费用.
                2，保证按原约定价格向甲方购买前述房屋，并及时将贷款所得支付甲方之售房款.
                3，将所购房屋向贷款银行申请抵押贷款.
                4，按照前述业务需要，及时签订各项合同文件和办理各种手续，并承担各项费用.
                5，在办理房屋过户时，应依要求将房屋产权资料交付贷款银行或其认可的机构持有.
                四，本协议以乙方向贷款银行申请购房抵押贷款获得批准为正式生效条件.如果贷款银行认为乙方的借款申请不符合条件而不予批准，则甲，乙双方可以解除本协议.甲方若向乙方收取定金,应如数退还给乙方.
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="info" @click="auditVisible = false">取 消</el-button>
                <el-button type="warning" v-if="sign == false" @click="sign = !sign">同意并签署</el-button>
                <el-button type="warning" v-if="sign == true" @click="saveAgreement">确认</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: 'CreditList',
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

                auditVisible: false,
                agreementVisible: false,
                creditId: '',
                form: {
                    pResult: 1,
                    pDesc: '',
                    pPerson: '',
                    creditId: ''
                },
                sign: false,
                idx: -1
            }
        },
        created() {
            this.getCreditDataList();
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
                this.$axios.get('api/credit/ec',{params:{
                        companyId: localStorage.getItem('companyId')
                    }}).then(function (res){
                    console.log(res);
                    _than.tableData = res.data.extend.list;
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
            // 去查看详情
            gotoInfoDetails(id){
                this.$router.push("/credit-info-details?id=" + id)
            },
            // 去审批
            gotoApprove(id){
                this.auditVisible = true;
                this.$router.push("/credit-info-details?id=" + id)
            },
            // 保存审批结果
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
                            pResult: this.form.pResult,
                            pDesc: this.form.pDesc,
                            pPerson: this.pPerson,
                        }
                    )).then(function (response) {
                    console.log(response);
                    this.$message.success('提交成功！');
                    this.$router.push("credit-apply-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
            agreemenBut(id){
                this.creditId = id;
                this.agreementVisible = true;
            },
            // 签署协议
            saveAgreement(){
                this.$confirm('您确定要提交签署协议吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    this.$axios.post('api/credit/appr/sign',
                        this.qs.stringify(
                            {   id: this.creditId,
                                userId: localStorage.getItem('companyId'),
                                name: localStorage.getItem('ms_username'),
                                step: 6,
                                status: 2,
                            }
                        )).then(res => {
                        this.$message({type: 'success', message: '提交成功!'});
                        this.getCreditDataList();
                        this.agreementVisible = false;
                    }).catch(function (error) {
                        console.log(error);
                    });                    
                }).catch(() => {
                    this.$message({type: 'info', message: '已取消'});
                });
                
            },
        },
        // 过滤器
        filters: {
            statusToText(oldValue) {
                switch (oldValue) {
                    case '0-0':
                        return '未提交';
                        break;
                    case '1-0':
                        return '待完善资料';
                        break;
                    case '2-0':
                        return '待担保审批';
                        break;
                    case '3-0':
                        return '待平台审批';
                        break;
                    case '4-0':
                        return '待资金方批复';
                        break;
                    case '5-0':
                        return '待发起协议';
                        break;
                    case '6-0':
                        return '待我签署协议';
                        break;
                    case '6-1':
                        return '待我签署协议';
                        break;
                    case '7-0':
                        return '待确认协议';
                        break;
                    case '8-0':
                        return '授信完成';
                        break;
                    default:
                        return '审批中';
                        break;
                }
            },
        },
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
        text-align: left;
    }

    .top-btn-box .status-input{
        width: 150px;
        margin-right: 20px;
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
