<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>放款批复列表</span>
                    </div>
                    <div class="top-btn-box">
                        状态
                        <el-select v-model="select_cate" placeholder="请选择" class="handle-select mr10">
                            <el-option key="1" label="待完善项目资料" value="1001"></el-option>
                            <el-option key="2" label="待提交" value="1002"></el-option>
                        </el-select>
                        <el-button type="primary" class="btn-queren" icon="search">查询</el-button>
                    </div>
                    <div class="loan-info-box">
                        <div class="list" v-for=" (item, index) in tableData" :key="index">
                            <div class="loan-info-box list loan-info">
                                <div class="item">申请日期: {{item.applyDate}}</div>
                                <div class="item">申请编号: {{item.applyNo}}</div>
                                <div class="item">申请人: {{item.name}}</div>
                                <div class="item">项目名称: {{item.projectName}}</div>
                                <div class="item">申请放款金额: {{item.applyAmount}}.00万</div>
                                <div class="btn-sz" v-show="isShowPurchase === index" @click="isShowPurchaseInfo(-1)">收起 ▲</div>
                                <div class="btn-sz" v-show="isShowPurchase !== index" @click="isShowPurchaseInfo(index)">展开 ▼</div>
                                <div class="tag-status" v-if="item.step === 3 && item.status != -1">待确认</div>
                                <div class="tag-status" v-if="item.step === 3 && item.status == -1">已拒绝</div>
                                <div class="btn-cancel" v-if="item.step === 3 && item.status != -1" @click="gotoSubmit(item.id,item.applyNo, -1)">拒绝</div>
                                <div class="btn-submit" v-if="item.step === 3 && item.status != -1" @click="gotoSubmit(item.id,item.applyNo, 1)">确认</div>
                            </div>
                            <div class="purchase-info" v-show="isShowPurchase === index">
                                <el-table border :data="item.purchaseOrders">
                                    <el-table-column prop="contractNo" label="采购合同编号" align="center">
                                    </el-table-column>
                                    <el-table-column prop="contractFile" label="采购合同附件" align="center">
                                        <template slot-scope="scope">
                                            <img :src="filesysip + scope.row.contractFile" @click="filePreview(scope.row.contractFile)" width="30" height="25"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="orderAmount" label="采购订单总金额" align="center">
                                        <template slot-scope="scope">
                                            {{ scope.row.orderAmount}} 万
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="orderFile" label="采购订单附件" align="center">
                                        <template slot-scope="scope">
                                            <img :src="filesysip + scope.row.orderFile" @click="filePreview(scope.row.orderFile)" width="30" height="25"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="invoiceAmount" label="采购发票总金额" align="center">
                                        <template slot-scope="scope">
                                            {{ scope.row.invoiceAmount}} 万
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="invoiceFile" label="采购发票附件" align="center">
                                        <template slot-scope="scope">
                                            <img :src="filesysip + scope.row.invoiceFile" @click="filePreview(scope.row.invoiceFile)" width="30" height="25"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="deliveryFile" label="送货单附件" align="center">
                                        <template slot-scope="scope">
                                            <img :src="filesysip + scope.row.deliveryFile" @click="filePreview(scope.row.deliveryBillFile)" width="30" height="25"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="accountName" label="供应商银行账户名" align="center">
                                    </el-table-column>
                                    <el-table-column prop="bankAccount" label="供应商银行账号" align="center">
                                    </el-table-column>
                                    <el-table-column prop="bank" label="开户行" align="center">
                                    </el-table-column>
                                </el-table>
                            </div>
                        </div>
                        <div class="pagination">
                            <!--<el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">-->
                            <!--</el-pagination>-->
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <!-- 批复弹出框 -->
        <el-dialog :visible.sync="submitVisible" :rules="rules" center width="450px">
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="是否同意放款">
                    <el-radio-group v-model="form.isLend">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="拒绝原因"  v-show="this.form.isLend === -1" prop="fDesc">
                    <el-input type="textarea" v-model="form.fDesc" style="width: 300px"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <div v-show=" form.isLend === 1">
                    <el-button type="primary" class="btn-queren" @click="addLoanInfo">立即登记放款信息</el-button>
                    <el-button  @click="submitVisible = false">再等等</el-button>
                </div>
                <div v-show=" form.isLend === -1">
                    <el-button @click="submitVisible = false">取 消</el-button>
                    <el-button type="primary" class="btn-queren" @click="save('form')">确 定</el-button>
                </div>
            </span>
        </el-dialog>

        <!-- 登记放款信息 -->
        <el-dialog title="登记放款信息" :visible.sync="addLoanInfoVisible" width="1000px">
            <el-form :inline="true" :model="form" class="demo-form-inline">
                <div style="width: 100%">
                    <el-form-item label="放款日期">
                        <!-- <el-input v-model="form.loanDate" placeholder="输入放款日期"></el-input> -->
                        <el-date-picker type="date" placeholder="输入放款日期" v-model="form.loanDate" value-format="yyyy-MM-dd" style="width: 200px;"/>
                    </el-form-item>
                    <el-form-item label="还款方式">
                        <el-select v-model="form.type" placeholder="还款方式">
                            <el-option label="到期还款分期付息" value="到期还款分期付息"></el-option>
                            <el-option label="到期还本付息" value="到期还本付息"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div style="width: 100%">
                    <el-form-item label="放款金额">
                        <el-input v-model="form.amount" placeholder="万元"></el-input>
                    </el-form-item>
                    <el-form-item label="账期">
                        <el-date-picker
                                v-model="apDate"
                                value-format="yyyy-MM-dd"
                                type="daterange"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                        </el-date-picker>
                        共 {{repaymentInfo.length}} 期
                    </el-form-item>
                </div>
                <div style="width: 100%; height: 30px">还款计划</div>
                <div>
                    <el-table :data="repaymentInfo" border class="table">
                        <el-table-column type="index" label="序号" width="50" align="center">
                        </el-table-column>
                        <el-table-column prop="loanNumber" label="期数" width="150" align="center">
                            <template slot-scope="scope">
                                第 {{scope.$index}} 期
                            </template>
                        </el-table-column>
                        <el-table-column prop="name" label="资金类型" width="150" align="center">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.type"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="projectName" label="计划收入金额" width="200" align="center">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.amount1"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column label="计划支出金额" width="150" align="center">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.amount2"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column label="计划到账日"  align="center">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.endDate"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.status"></el-input>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button class="btn-cancel" @click="addLoanInfoVisible = false">取 消</el-button>
                <el-button type="primary" @click="save('form')">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'loan-apply-list',
        data() {
            return {
                userId: localStorage.getItem('userId'),
                filesysip: localStorage.getItem("fileBasePath"),
                tableData: [],
                repaymentInfo: [
                    {num: 0, type: '', amount1: '', amount2: '',endDate: '',status:''},
                    {num: 1, type: '', amount1: '', amount2: '',endDate: '',status:''},
                    ],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                editVisible: false,
                delVisible: false,

                apDate: null,
                isShowPurchase: 0,
                submitVisible: false,
                addLoanInfoVisible: false,
                form: {
                    id: null,
                    isLend: 1,
                    fDesc: null,
                    loanDate:null,
                    amount: null,
                    repaymentType: null,
                    apStartDate: null,
                    apEndDate: null,
                    apNumber: null,
                    applyNo: '',
                    applyId: '',
                },
                rules: {
                    fDesc: [
                        {required: true, message: '请输入拒绝原因', trigger: 'blur'}
                    ],
                },
                idx: -1
            }
        },
        created() {
            this.getTableData();
        },
        // 监听器
        watch: {
            apDate:function(val) {
                this.form.apStartDate = this.apDate[0];
                this.form.apEndDate = this.apDate[1];
                this.getIntervalMonth(this.apDate[0],this.apDate[1]);
            },

            bankListFile:function() {
                let _than = this;
                if (this.bankListFile != null) {
                    _than.uploadDisabled = true;
                    console.log('imagelist true');
                    return
                }
                _than.uploadDisabled = false;
                console.log('imagelist'+ this.imagelist.length >0);
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
            getTableData(){
                this.$axios.post('la/fcompany/list',this.qs.stringify({id: this.userId})).then( res => {
                    this.tableData = res.data.extend.list;
                }).catch(function (error) {

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
            filePreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            // 是否展开
            isShowPurchaseInfo(index){
                this.isShowPurchase = index;
                console.log("index: " + index);
            },

            // 获取期数
            getIntervalMonth(startDate, endDate){
                console.log(startDate);
                console.log(endDate);
                // 拆分年月日
                let start = startDate.split('-');
                // 得到月数
                let star = parseInt(start[0]) * 12 + parseInt(start[1]);
                // 拆分年月日
                let end = endDate.split('-');
                // 得到月数
                let en = parseInt(end[0]) * 12 + parseInt(end[1]);
                var m = Math.abs(star - en);
                    console.log(m);
                let list = [];
                for(let i= 0 ;i<= m+1; i++){
                    let item = {
                        num: i,
                        type: '',
                        amount1: '',
                        amount2: '',
                        endDate: '',
                        status: '',
                    }
                    list.push(item);
                }
                this.repaymentInfo = list;
                return m;
            },

            // 放款批复
            gotoSubmit(id, no, result){
                this.form.applyId = id;
                this.form.applyNo = no,
                this.form.isLend = result;
                this.submitVisible = true;
            },
            // 保存审批信息
            save(formName){
                let _than = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('loan/appr/f/save',
                            this.qs.stringify(
                                {
                                    result: this.form.isLend,
                                    applyId: this.form.applyId,
                                    applyNo: this.form.applyNo,
                                    opinion: this.form.fDesc,
                                    amount: this.form.amount,
                                    loanDate: this.form.loanDate,
                                    type: this.form.type,
                                    startDate: this.form.apStartDate,
                                    endDate: this.form.apEndDate,
                                    num: this.repaymentInfo.length,
                                    name: localStorage.getItem('ms_username'),
                                }
                            )).then( res =>{
                            console.log(res);
                            this.submitVisible = false;
                            this.addLoanInfoVisible = false;
                            this.$message.success('保存成功！');
                            this.getTableData();
                        }).catch(function (error) {
                            console.log(error);
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },

            addLoanInfo(){
                this.submitVisible = false;
                this.addLoanInfoVisible = true;
            },

            // 保存登记放款信息
            saveLoanInfo(){
                this.addLoanInfoVisible = false;
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
        margin-right: 200px;
        text-align: left;
    }

    .handle-select {
        margin-left: 10px;
        margin-right: 20px;
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

    .loan-info-box{

    }

    .loan-info-box .list{
        /*padding-top: 37px;*/
        /*width: 100%;*/
        /*border-top: 1px dashed rgba(151,151,151,1);*/
        /*display:block;*/
    }

    .loan-info-box .list .loan-info{
        /*width: 100%;*/
        padding: 30px 0;
        margin: 30px 0;
        width: 100%;
        border-top: 1px dashed rgba(151,151,151,1);
    }

    .loan-info-box .list .item{
        float: left;
        height:16px;
        font-size:12px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
        line-height:16px;
        padding-right: 20px;
    }

    .btn-sz{
        float: right;
        font-size:12px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(255,130,8,1);
        line-height:16px;
        width:80px;
        height:30px;
        line-height: 30px;
        border-radius:2px;
        border:1px solid rgba(255,130,8,1);
        text-align: center;
    }

    .purchase-info{

    }

    .tag-status{
        float: right;
        width: 180px;
        height: 30px;
        background-color: #D8D8D8;
        font-size:14px;
        font-family:MicrosoftYaHei;
        color:rgba(34,34,34,1);
        line-height:30px;
        text-align: center;
        margin-right: 20px;
    }

    .btn-submit{
        float: right;
        width:80px;
        height:30px;
        background:rgba(255,130,8,1);
        border-radius:2px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        color:rgba(255,255,255,1);
        line-height:30px;
        text-align: center;
        margin-right: 20px;
    }

    .btn-cancel {
        float: right;
        width:80px;
        height:30px;
        background:rgba(255,178,103,1);
        border-radius:2px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        color:rgba(255,255,255,1);
        line-height:30px;
        text-align: center;
        margin-right: 20px;
    }

    .btn-queren{
         background:rgba(255,130,8,1);
         border-radius:2px;
         font-size:14px;
         font-family:MicrosoftYaHei;
         border:1px solid rgba(255,130,8,1);
    }

    .btn-cancel{
        background:rgba(255,178,103,1);
        border-radius:2px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        border:1px solid rgba(255,178,103,1);
    }

</style>
