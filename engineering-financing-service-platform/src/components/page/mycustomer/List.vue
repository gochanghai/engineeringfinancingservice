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
                        <el-button type="primary" icon="el-icon-plus">查询</el-button>
                    </div>
                    <div class="customer-list-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable" >
                            <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
                            <el-table-column prop="name" label="姓名">
                            </el-table-column>
                            <el-table-column prop="gender" label="性别">
                                <template slot-scope="scope">
                                    {{ scope.row.gender | genderToText}}
                                </template> 
                            </el-table-column>
                            <el-table-column prop="phone" label="手机号">
                            </el-table-column>
                            <el-table-column prop="birthdate" label="出生年月">
                            </el-table-column>
                            <el-table-column prop="marriageStatus" label="婚姻状况">
                                <template slot-scope="scope">
                                    {{ scope.row.marriageStatus | marriageToText}}
                                </template> 
                            </el-table-column>
                            <el-table-column prop="amount1" label="工程贷授信总额度">
                                <template slot-scope="scope">
                                    {{ scope.row.amount1 | amountFormat}}
                                </template> 
                            </el-table-column>
                            <el-table-column prop="amount2" label="工程贷已放款总金额">
                                <template slot-scope="scope">
                                    {{ scope.row.amount2 | amountFormat}}
                                </template> 
                            </el-table-column>
                            <el-table-column prop="amount2" label="工程贷在贷总金额">
                                <template slot-scope="scope">
                                    {{ scope.row.amount3 | amountFormat}}
                                </template> 
                            </el-table-column>
                            <el-table-column prop="dnum" label="违约次数">
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
                select_word: '',
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
            getData(){
                this.$axios.post('api/finance/customer',this.qs.stringify({id: localStorage.getItem('userId')})).then(res => {
                    console.log(res);
                    this.tableData = res.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },
        },
        filters:{
            marriageToText(val) {
                if(1 === val){
                    return '已婚'
                }
                if(-1 === val){
                    return '离婚'
                }
                return '未婚';
            },
            genderToText(val) {
                if('M' === val){
                    return '男'
                }
                if('F' === val){
                    return '女'
                }
                return '未知';
            },
            amountFormat(val) {
                if (val === 0) {
                    return "0.00";
                }
                var str = parseInt(val).toFixed(2) + '';
                var intSum = str.substring(0, str.indexOf(".")).replace(/\B(?=(?:\d{3})+$)/g, ',');
                var dot = str.substring(str.length, str.indexOf("."));
                var ret = intSum + dot;
                return ret;
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

