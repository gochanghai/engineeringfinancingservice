<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>放款审批列表</span>
                    </div>
                    <div class="top-btn-box">
                        状态
                        <el-select v-model="select_cate" placeholder="请选择" class="search-input mr4">
                            <el-option key="1" label="待完善项目资料" value="1001"></el-option>
                            <el-option key="2" label="待提交" value="1002"></el-option>
                        </el-select>
                        <!--<el-input v-model="select_word" placeholder="请输入融资人或项目名称或合同编号" class="handle-input mr10"></el-input>-->
                        <el-button type="primary" class="btn-search" icon="search" @click="search">查询</el-button>
                    </div>
                    <div class="loan-info-box" v-loading="loading">
                        <div class="list" v-for=" (item, index) in tableData" :key="index">
                            <div class="loan-info-box list loan-info">
                                <div class="item">申请日期: {{item.applyDate}}</div>
                                <div class="item">申请编号: {{item.applyNo}}</div>
                                <div class="item">申请人: {{item.name}}</div>
                                <div class="item">项目名称: {{item.projectName}}</div>
                                <div class="item">申请放款金额: {{item.applyAmount}} 万</div>

                                <div class="btn-sz" v-show="isShowPurchase === index" @click="isShowPurchaseInfo(-1)">收起 ▲</div>
                                <div class="btn-sz" v-show="isShowPurchase !== index" @click="isShowPurchaseInfo(index)">展开 ▼</div>
                                <div class="tag-status" v-if="item.step === 1 && item.status != -1">待确认</div>
                                <div class="tag-status" v-if="item.step === 1 && item.status === -1">已拒绝</div>
                                <div class="btn-cancel" v-if="item.step === 1 && item.status != -1" @click="gotoSubmit(item.id,item.applyNo, -1)">拒绝</div>
                                <div class="btn-submit" v-if="item.step === 1 && item.status != -1" @click="gotoSubmit(item.id,item.applyNo, 1)">确认</div>
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
                                            <img :src="filesysip + scope.row.deliveryFile" @click="filePreview(scope.row.deliveryFile)" width="30" height="25"/>
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

        <!-- 审批弹出框 -->
        <el-dialog :visible.sync="submitVisible" center width="550px">
            <el-form ref="form" :model="form" :rules="rules" label-width="170px">
                <el-form-item label="放款申请资料是否通过">
                    <el-radio-group v-model="form.result">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                    </el-radio-group>
                </el-form-item>
                <div  v-show="this.form.result === 1">
                    <el-form-item label="有效采购金额共计" prop="amount">
                        <el-input v-model="form.amount" style="width: 200px"></el-input> 万
                    </el-form-item>
                </div>
                <el-form-item label="请输入拒绝原因"  v-show="this.form.result === -1" prop="opinion">
                    <el-input type="textarea" v-model="form.opinion" style="width: 300px"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="submitVisible = false">取 消</el-button>
                <el-button type="primary" @click="save('form')">提 交</el-button>
            </span>
        </el-dialog>

        <!-- 发起手续费弹出框 -->
        <el-dialog :visible.sync="sendFreeVisible" center width="30%">
            <el-form ref="form" :model="form" label-width="150px">
                <el-form-item label="手续费共计">
                    <h1>101250.00元</h1>
                </el-form-item>
                <el-form-item label="手续费说明">
                    手续费年化5%，按放款周期计算。
                </el-form-item>
                <el-form-item label="收款通知书">
                    <el-upload
                            class="upload-demo"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :on-change="handleChange">
                        <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="submitVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">发 起</el-button>
            </span>
        </el-dialog>

        <!-- 确认服务费弹出框 -->
        <el-dialog :visible.sync="confirmFreeVisible" center width="30%">
            <el-form ref="form" :model="form" label-width="150px">
                <el-form-item label="完成支付费用">
                    <h1>101250.00元</h1>
                </el-form-item>
                <el-form-item label="支付凭证">
                    <img src="" height="50" width="50"/>
                </el-form-item>
                <el-form-item label="是否属实">
                    <el-radio-group v-model="form.pResult">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="-1">否</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item v-show="form.pResult === -1">
                    <el-input type="textarea" v-model="form.pDesc" placeholder="请输入否定原因" style="width: 300px"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="submitVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">提 交</el-button>
            </span>
        </el-dialog>

        <!-- 发起放款通知提示弹出框 -->
        <el-dialog :visible.sync="delVisible" width="30%" center>
            <!--<h2>将发送放款通知到资金方，是否已确认资料无误？</h2>-->
            <div class="del-dialog-cnt"><h3>将发送放款通知到资金方，是否已确认资料无误？</h3></div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">提 交</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'loan-apply-list',
        data() {
            return {
                filesysip: localStorage.getItem("fileBasePath"),
                loading: true,
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                editVisible: false,

                delVisible: false,

                isShowPurchase: 0,
                submitVisible: false,
                sendFreeVisible: false,
                confirmFreeVisible: false,
                form: {
                    id: '',
                    result: 1,
                    amount: '',
                    opinion: '',
                    no: ''
                },
                rules: {
                    amount: [
                        {required: true, message: '请输入有效采购金额', trigger: 'blur'}
                    ],
                    opinion: [
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
                this.$axios.get('la/list').then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading = false;
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

            handleChange() {

            },

            // 审批
            gotoSubmit(id, no, result){
                this.form.result = result;
                this.form.id = id;
                this.form.no = no;
                this.submitVisible = true;
            },
            // 保存审批信息
            save(formName){
                this.$refs[formName].validate((valid) => {
                    if (!valid) {
                        this.$axios.post('loan/appr/save',
                            this.qs.stringify(
                                {
                                    applyId: this.form.id,
                                    applyNo: this.form.no,
                                    name: localStorage.getItem('ms_username'),
                                    result: this.form.result,
                                    purchaseAmount: this.form.amount,
                                    opinion: this.form.opinion,
                                }
                            )).then(res => {
                            console.log(res);
                            this.submitVisible = false;
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
        /*border-top: 1px solid #cccccc;*/
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
