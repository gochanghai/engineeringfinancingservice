<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item>新增可授信项目</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <!--<div style="width: 100%; border-left: 3px solid #ff8208; padding: 5px 20px; margin-bottom: 50px">新增可授信项目</div>-->
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="150px">
                    <el-form-item label="项目合同名称">
                        <el-input v-model="form.projectName" style="width: 215px"></el-input>
                    </el-form-item>
                    <el-form-item label="合同编号">
                        <el-input v-model="form.contractNo" style="width: 215px"></el-input>
                    </el-form-item>
                    <el-form-item label="合同金额(万元)">
                        <el-input v-model="form.contractAmount" style="width: 215px"></el-input>
                    </el-form-item>
                    <el-form-item label="所属分公司">
                        <el-select v-model="form.childComId" placeholder="请选择">
                            <el-option v-for=" item in companyList" :key="item.childCompanyId"
                                       :label="item.childCompanyName" :value="item.childCompanyId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商务经理">
                        <el-select v-model="form.fId" placeholder="请选择">
                            <el-option v-for="fop in financierList" :key="fop.id" :label="fop.name" :value="fop.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="预授信金额（万元）">
                        <el-input v-model="form.shouldCreditAmount" style="width: 215px"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onReturn">返回</el-button>
                        <el-button type="primary" @click="save">保存</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: 'AddProject',
        data: function(){
            return {
                comId: localStorage.getItem('userInfoId'),
                financierList: [],
                companyList: [],
                form: {
                    projectName: null,
                    contractNo: null,
                    contractAmount: null,
                    shouldCreditAmount: null,
                    projectProgress: null,
                    fId: null,
                    childComId: null,
                }
            }
        },
        created() {

            this.getCompanyList();
            this.getFinancierList();
        },
        methods: {
            // 获取分公司
            getCompanyList(){
                let _than = this;
                this.companyId = localStorage.getItem('userInfoId'),
                    console.log("companyId: " +  localStorage.getItem('userInfoId'));
                this.$axios.get('cc/list',{params:{
                        id: this.comId
                    }}).then(function (response) {
                    console.log(response);
                    _than.companyList = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 获取商务经理
            getFinancierList(){
                let _than = this;
                this.comId = localStorage.getItem('userInfoId'),
                    console.log("companyId: " +  localStorage.getItem('userInfoId'));
                this.$axios.get('financier/list',{params:{
                        id: this.comId
                    }}).then(function (response) {
                    console.log(response);
                    _than.financierList = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            onReturn() {
                this.$message.success('返回！');
                this.$router.push("may-credit-project-list")
            },
            save(){
                this.$message.success('保存成功！');
                this.$axios.post('api/project/save',
                    this.qs.stringify(
                        {
                            projectName: this.form.projectName,
                            contractNo: this.form.contractNo,
                            contractAmount: this.form.contractAmount,
                            shouldCreditAmount: this.form.shouldCreditAmount,
                            projectProgress: this.form.projectProgress,
                            fId: this.form.fId,
                            comId: this.comId,
                            childComId: this.form.childComId
                        }
                    )).then(function (response) {
                    console.log(response);

                }).catch(function (error) {
                    console.log(error);
                });

                this.$router.push("may-credit-project-list");

            },
        }
    }
</script>

<style scoped>

    .crumbs {
        margin-bottom: 0;
        background-color: #fff;
        padding-left: 50px;
    }

    .breadcrumb {
        line-height: 30px;
        padding-left: 20px;
        border-left: 4px solid #ff8208;
    }

    .breadcrumb el-breadcrumb-item {
        font-size: 18px;
    }

    .container {
        padding-left: 200px;
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }

    .line {
        text-align: right;
        color: #606266;
        padding-right: 10px;
    }
</style>
