<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb class="breadcrumb" separator="/">
                <el-breadcrumb-item class="breadcrumb-item">
                    <!--<i class="el-icon-lx-cascades"></i> -->
                    放款申请列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container" >
            <div class="handle-box">
                <!--<el-button type="primary" icon="delete" class="handle-del mr10" @click="delAll">批量删除</el-button>-->
                状态
                <el-select v-model="select_cate" placeholder="请选择" class="handle-select mr10">
                    <el-option key="1" label="待完善项目资料" value="1001"></el-option>
                    <el-option key="2" label="待提交" value="1002"></el-option>
                </el-select>
                <!--<el-input v-model="select_word" placeholder="请输入融资人或项目名称或合同编号" class="handle-input mr10"></el-input>-->
                <el-button type="primary" class="btn-search" icon="search" @click="search">查询</el-button>
                <!--<el-button type="primary" icon="search" @click="add" style="margin-left: 300px">新增</el-button>-->
            </div>
            <div class="loan-info-box" >
                <div class="list" v-for=" (item, index) in tableData">
                    <div class="loan-info-box list loan-info">
                        <div class="item">申请日期: {{item.loanDate}}</div>
                        <div class="item">申请编号: {{item.loanNo}}</div>
                        <div class="item">申请人: {{item.name}}</div>
                        <div class="item">项目名称: {{item.projectName}}</div>
                        <div class="item">申请放款金额: {{item.loanAmount}} 万</div>

                        <div class="btn-sz" v-show="isShowPurchase === index" @click="isShowPurchaseInfo(-1)">收起 ▲</div>
                        <div class="btn-sz" v-show="isShowPurchase !== index" @click="isShowPurchaseInfo(index)">展开 ▼</div>
                        <div class="tag-status">待确认</div>
                        <div class="btn-cancel" @click="gotoSubmit(item.id, -1)">拒绝</div>
                        <div class="btn-submit" @click="gotoSubmit(item.id, 1)">确认</div>
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
                            <el-table-column prop="orderSumAmount" label="采购订单总金额" align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.orderSumAmount}} 万
                                </template>
                            </el-table-column>
                            <el-table-column prop="orderFile" label="采购订单附件" align="center">
                                <template slot-scope="scope">
                                    <img :src="filesysip + scope.row.orderFile" @click="filePreview(scope.row.orderFile)" width="30" height="25"/>
                                </template>
                            </el-table-column>
                            <el-table-column prop="invoiceSumAmount" label="采购发票总金额" align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.invoiceSumAmount}} 万
                                </template>
                            </el-table-column>
                            <el-table-column prop="invoiceFile" label="采购发票附件" align="center">
                                <template slot-scope="scope">
                                    <img :src="filesysip + scope.row.invoiceFile" @click="filePreview(scope.row.invoiceFile)" width="30" height="25"/>
                                </template>
                            </el-table-column>
                            <el-table-column prop="deliveryBillFile" label="送货单附件" align="center">
                                <template slot-scope="scope">
                                    <img :src="filesysip + scope.row.deliveryBillFile" @click="filePreview(scope.row.deliveryBillFile)" width="30" height="25"/>
                                </template>
                            </el-table-column>
                            <el-table-column prop="bankCardNo" label="供应商银行账户名" align="center">
                            </el-table-column>
                            <el-table-column prop="bankAccountName" label="供应商银行账号" align="center">
                            </el-table-column>
                            <el-table-column prop="openAccountBank" label="开户行" align="center">
                            </el-table-column>
                        </el-table>
                    </div>
                </div>
            </div>
            <!--<el-table :data="tableData">-->
                <!--<el-table-column prop="loanDate" label="申请日期" width="100" align="center">-->
                <!--</el-table-column>-->
                <!--<el-table-column prop="loanNumber" label="申请编号" width="150" align="center">-->
                <!--</el-table-column>-->
                <!--<el-table-column prop="name" label="申请人" width="150" align="center">-->
                <!--</el-table-column>-->
                <!--<el-table-column prop="projectName" label="项目名称" width="200" align="center">-->
                <!--</el-table-column>-->
                <!--<el-table-column label="申请放款金额" width="150" align="center">-->
                    <!--<template slot-scope="scope">-->
                        <!--{{ scope.row.loanAmount }} 万-->
                    <!--</template>-->
                <!--</el-table-column>-->
                <!--<el-table-column label="操作"  align="center">-->
                    <!--<template slot-scope="scope">-->
                        <!--<el-button type="warning" plain @click="handleEdit(scope.$index, scope.row)">详情</el-button>-->
                        <!--<el-button type="warning" plain @click="handleEdit(scope.$index, scope.row)">去审批</el-button>-->
                    <!--</template>-->
                <!--</el-table-column>-->
            <!--</el-table>-->
            <div class="pagination">
                <!--<el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">-->
                <!--</el-pagination>-->
            </div>
        </div>

        <!-- 审批弹出框 -->
        <el-dialog :visible.sync="submitVisible" center width="30%">
            <el-form ref="form" :model="form" :rules="rules" label-width="170px">
                <el-form-item label="放款申请资料是否通过">
                    <el-radio-group v-model="form.ecResult">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="有效采购金额共计" v-show="this.form.ecResult === 1" prop="ecPurchaseAmount">
                    <el-input v-model="form.ecPurchaseAmount" style="width: 200px"></el-input> 万
                </el-form-item>
                <el-form-item label="请输入拒绝原因"  v-show="this.form.ecResult === -1" prop="ecDesc">
                    <el-input type="textarea" v-model="form.ecDesc"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="submitVisible = false">取 消</el-button>
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
                companyId: localStorage.getItem('userInfoId'),
                filesysip: localStorage.getItem("fileBasePath"),
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                submitVisible: false,
                editVisible: false,
                uploadFileVisible: false,
                isShowPurchase: 0,
                form: {
                    id: null,
                    ecResult: 1,
                    ecPurchaseAmount: null,
                    ecDesc: null
                },
                rules: {
                    ecPurchaseAmount: [
                        {required: true, message: '请输入有效采购金额', trigger: 'blur'}
                    ],
                    ecDesc: [
                        {required: true, message: '请输入拒绝原因', trigger: 'change'}
                    ],
                },
                idx: -1
            }
        },
        created() {
            this.getTableData();
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
            // 获取数据
            getTableData(){
                let _than = this;
                this.$axios.get('la/com/list',{params:{
                        id: this.companyId
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                }).catch(function (error) {

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
            filePreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            // 是否展开
            isShowPurchaseInfo(index){
                this.isShowPurchase = index;
                console.log("index: " + index);
            },

            //去审批
            gotoSubmit(id, result){
                this.submitVisible = true;
                this.form.engComResult = result;
                this.form.id = id;
            },
            // 保存审批信息
            save(formName){
                let _than = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        _than.$axios.post('loan_appr/ec_save',
                            _than.qs.stringify(
                                {
                                    id: this.form.id,
                                    ecResult: this.form.ecResult,
                                    ecPurchaseAmount: this.form.ecPurchaseAmount,
                                    ecDescc: this.form.ecDesc,
                                }
                            )).then(function (response) {
                            console.log(response);
                            _than.submitVisible = false;
                        }).catch(function (error) {
                            console.log(error);
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
                this.$message.success('保存成功！');

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

    .btn-search{
        width:80px;
        background:rgba(255,130,8,1);
        border-radius:2px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        border:1px solid rgba(255,130,8,1);
    }

</style>
