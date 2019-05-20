<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>授信列表</span>
                    </div>
                    <div class="top-btn-box">
                        <el-button type="warning" @click="add">新增</el-button>
                        <!--<el-button type="warning" @click="add" style="margin-left: 50px">导入</el-button>-->
                    </div>
                    <div class="project-list-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" >
                            <!--<el-table-column type="selection" width="55" align="center"></el-table-column>-->
                            <el-table-column type="index" label="序号" width="100" align="center"/>
                            <el-table-column prop="applyNo" label="申请编号"/>
                            <el-table-column prop="applyDate" label="申请日期" width="150" align="center"/>
                            <el-table-column prop="name" label="申请人" align="center">
                            </el-table-column>
                            <el-table-column prop="projectName" label="项目名称" align="center"/>
                            <el-table-column label="项目进度" width="100" align="center">
                                <template slot-scope="scope">
                                    <div @click="findProgress(scope.row.id)">
                                    <el-progress :text-inside="true" :stroke-width="18" :percentage="scope.row.projectProgress" color="#ff8208"></el-progress>
                                    </div>
                                </template>
                            </el-table-column>
                            <el-table-column label="申请授信金额(万元)" width="150" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.applyAmount}}万
                                </template>
                            </el-table-column>
                            <el-table-column label="审批进度" width="100" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.step + "-"+ scope.row.status | statusToText}}
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" width="100">
                                <template slot-scope="scope">
                                    <!--<el-button type="text" @click="findProgress(scope.row.id)"><el-tag type="warning">进度详情</el-tag></el-button>-->
                                    <el-button type="text" @click="detailsInfo(scope.row.id)">详情信息</el-button>
                                    <el-button type="warning" v-show="scope.row.step === 6" v-if="scope.row.status === 0 || scope.row.status === 2" @click="agreementBut(scope.row.id)">签署协议</el-button>
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

        <!-- 签署协议弹窗 -->
        <el-dialog :title="contrTitle" :visible.sync="agreementVisible" width="700px" center="">
            <div v-if="signIndex === 1">
                一，甲方承诺：
                1，向乙房申请购房贷款银行或贷款银行认可的机构提供符合要求的房屋资料以备查核.
                2，保证对出售的房屋拥有独立产权.如果该房屋为共有房屋，则必须取得其他所有共有人的同意出售书面文件.
                3，保证该出售房屋未予出租.因出租所产生的任何问题由甲方承担并负责解决.
                4，自签订本协议起，保证将该房屋按约定价格售给乙方，期间不得反悔或将房屋出售给第三人.
                5，按照前述业务的需要，及时签订各项合同文件和办理各种手续.
                6，在办理产权过户时，应依要求将房屋产权资料交付贷款银行或贷款银行认可的机构持有.
                二，乙方承诺：
                1，向贷款银行或贷款银行认可的机构提供符合要求的资料以备查核，并依规定支付费用.
                2，保证按原约定价格向甲方购买前述房屋，并及时将贷款所得支付甲方之售房款.
                3，将所购房屋向贷款银行申请抵押贷款.
                4，按照前述业务需要，及时签订各项合同文件和办理各种手续，并承担各项费用.
                5，在办理房屋过户时，应依要求将房屋产权资料交付贷款银行或其认可的机构持有.
                三，本协议以乙方向贷款银行申请购房抵押贷款获得批准为正式生效条件.如果贷款银行认为乙方的借款申请不符合条件而不予批准，则甲，乙双方可以解除本协议.甲方若向乙方收取定金,应如数退还给乙方.
            </div>
            <div v-if="signIndex === 2">
                一，甲方承诺：
                1，向乙房申请购房贷款银行或贷款银行认可的机构提供符合要求的房屋资料以备查核.
                2，保证对出售的房屋拥有独立产权.如果该房屋为共有房屋，则必须取得其他所有共有人的同意出售书面文件.
                3，保证该出售房屋未予出租.因出租所产生的任何问题由甲方承担并负责解决.
                4，自签订本协议起，保证将该房屋按约定价格售给乙方，期间不得反悔或将房屋出售给第三人.
                5，按照前述业务的需要，及时签订各项合同文件和办理各种手续.
                6，在办理产权过户时，应依要求将房屋产权资料交付贷款银行或贷款银行认可的机构持有.
                二，乙方承诺：
                1，向贷款银行或贷款银行认可的机构提供符合要求的资料以备查核，并依规定支付费用.
                2，保证按原约定价格向甲方购买前述房屋，并及时将贷款所得支付甲方之售房款.
                3，将所购房屋向贷款银行申请抵押贷款.
                4，按照前述业务需要，及时签订各项合同文件和办理各种手续，并承担各项费用.
                5，在办理房屋过户时，应依要求将房屋产权资料交付贷款银行或其认可的机构持有.
                三，本协议以乙方向贷款银行申请购房抵押贷款获得批准为正式生效条件.如果贷款银行认为乙方的借款申请不符合条件而不予批准，则甲，乙双方可以解除本协议.甲方若向乙方收取定金,应如数退还给乙方.
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="info" @click="signCancel">取 消</el-button>
                <!-- <el-button type="warning" v-if="signIndex === 2" @click="signAgreement">上一步</el-button> -->
                <el-button type="warning" v-if="sign2 != true" @click="signAgreement">同意并签署</el-button>
                <el-button type="warning" @click="saveAgreement">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'my-project',
        data() {
            return {
                userId: localStorage.getItem('userId'),
                loading: true,
                tableData: [],
                cur_page: 1,
                form: {
                    name: '',
                    date: '',
                    address: ''
                },
                idx: -1,
                agreementVisible: false,
                creditId: '',
                contrTitle: "借款合同",
                butName: "",
                sign1: false,
                sign2: false,
                signIndex: 1,
            }
        },
        created() {
            // this.getData();
            // 获取Table数据
            this.getDataList();
        },
        computed: {
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            getDataList(){
                let _than = this;
                this.$axios.get('api/credit/b',{params:{
                        id: this.userId
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading= false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 查看项目进度进度
            findProgress(projectId){
                //
                console.log(projectId);
                this.$router.push({
                    path:'priject-progress-details?id=' + projectId
                })
            },
            // 查看详情信息
            detailsInfo(id){
                this.$router.push({
                    path:'credit-engloan-info?id=' + id
                })
            },
            // 添加授信
            add(){
                this.$router.push({
                    path:'engineeringloan-credit-add'
                })
            },
            // 签署协议按钮
            agreementBut(id){
                this.creditId = id;
                this.agreementVisible = true;
            },
            signCancel(){
                this.contrTitle = "借款合同";
                this.signIndex = 1;
                this.sign1 = false;
                this.sign2 = false;
                this.agreementVisible = false;
            },
            // 签署协议
            signAgreement(){
                if(this.signIndex === 2){
                    this.sign2 = true;
                    return;
                }
                if(this.signIndex === 1){
                    this.contrTitle = "保证合同";
                    this.signIndex = 2;
                    this.sign1 = true;
                    return;
                }
                
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
                                userId:this.userId,
                                name: localStorage.getItem('ms_username'),
                                step: 6,
                                status: 1,
                            }
                        )).then(res => {
                        this.$message({type: 'success', message: '提交成功!'});
                        this.getDataList();
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
                        return '未处理';
                        break;
                    case '6-0':
                        return '待我签署协议';
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
    .top-btn-box{
        padding-bottom: 10px;
    }

</style>
