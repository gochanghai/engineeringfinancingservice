<template>
    <div class="">
        <div class="crumbs">
            <!--<el-breadcrumb separator="/">-->
                <!--<el-breadcrumb-item><i class="el-icon-lx-copy"></i> tab选项卡</el-breadcrumb-item>-->
            <!--</el-breadcrumb>-->
        </div>
        <div class="container">
            <div style="width: 100%; border-left: 3px solid #ff8208; padding: 5px 20px; margin-bottom: 50px">添加分公司</div>
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="150px">
                    <el-form-item label="分公司名称">
                        <el-input v-model="form.companyName" style="width: 400px"></el-input>
                    </el-form-item>
                    <el-form-item label="分公司地址">
                        <el-input v-model="form.companyAddress" style="width: 400px"></el-input>
                    </el-form-item>
                    <el-form-item label="联系人">
                        <el-input v-model="form.contactPerson" style="width: 200px"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话">
                        <el-input v-model="form.phone" style="width: 200px"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="save">保存</el-button>
                        <el-button style="margin-left: 100px">取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'AddChildCompany',
        data: function(){
            return {
                form: {
                    companyName: null,
                    companyAddress: null,
                    contactPerson: null,
                    phone: null,
                    pcId: null,
                }
            }
        },
        created() {
            this.form.pcId = localStorage.getItem('userInfoId');
        },
        methods: {
            onSubmit() {
                this.$message.success('提交成功！');
            },
            save(){
                // 产品状态
                this.form.status = 0;
                let _than = this;
                this.$message.success('保存成功！');
                this.$axios.post('cc/save',
                    this.qs.stringify(
                        {
                            companyName: this.form.companyName,
                            companyAddress: this.form.companyAddress,
                            contactPerson: this.form.contactPerson,
                            phone: this.form.phone,
                            pId: this.form.pId,
                        }
                    )).then(function (response) {
                    console.log(response);
                    _than.$router.push("child-com-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
        }
    }

</script>

<style>
    .message-title{
        cursor: pointer;
    }
    .handle-row{
        margin-top: 30px;
    }
    .form-box{
        padding-left: 200px;
    }
</style>

