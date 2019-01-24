<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <!--<el-breadcrumb-item>首页</el-breadcrumb-item>-->
                <el-breadcrumb-item>商务经理列表新增</el-breadcrumb-item>
                <!--<el-breadcrumb-item>新增商务经理</el-breadcrumb-item>-->
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" :rules="rules" status-icon label-width="110px">
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="form.name" style="width: 215px"></el-input>
                    </el-form-item>
                    <!--<el-form-item label="性别">-->
                        <!--<el-select v-model="form.gender" placeholder="请选择">-->
                            <!--<el-option key="M" label="男" value="M"></el-option>-->
                            <!--<el-option key="F" label="女" value="F"></el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                    <el-form-item label="身份证号" prop="idCard">
                        <el-input v-model="idCard" placeholder="请输入二代身份证号码" style="width: 300px"></el-input>
                    </el-form-item>
                    <el-form-item label="出生年月">
                        {{ this.form.birthdate}}
                        <!--<el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="form.birthdate" style="width: 100%;"></el-date-picker>-->
                    </el-form-item>
                    <el-form-item label="手机号码" prop="phone">
                        <el-input v-model="form.phone" style="width: 300px"></el-input>
                    </el-form-item>
                    <el-form-item label="从业日期" prop="jobDate">
                        <el-col :span="11">
                            <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="jobDate" style="width: 100%;"></el-date-picker>
                        </el-col>
                        <el-col class="line" :span="6">从业年限</el-col>
                        <el-col :span="5">
                            <el-input v-model="form.jobYearNumber"></el-input>
                        </el-col>
                        <el-col class="line" :span="2">年</el-col>
                    </el-form-item>
                    <el-form-item label="与公司合作日期" prop="cooperateDate">
                        <el-col :span="11">
                            <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="cooperateDate" style="width: 100%;"></el-date-picker>
                        </el-col>
                        <el-col class="line" :span="6">合作年限</el-col>
                        <el-col :span="5">
                            <el-input v-model="form.cooperateYearNumber"></el-input>
                        </el-col>
                        <el-col class="line" :span="2">年</el-col>
                    </el-form-item>
                    <!--<el-form-item label="婚姻状况">-->
                        <!--<el-select v-model="form.marriageStatus" placeholder="请选择">-->
                            <!--<el-option key="1" label="已婚" value="1"></el-option>-->
                            <!--<el-option key="2" label="未婚" value="0"></el-option>-->
                            <!--<el-option key="3" label="离异" value="-1"></el-option>-->
                            <!--<el-option key="4" label="丧偶" value="2"></el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                    <el-form-item label="所属分公司">
                        <el-select v-model="form.childCompanyId" placeholder="请选择">
                            <el-option v-for=" item in companyList" :key="item.childCompanyId" :label="item.childCompanyName" :value="item.childCompanyId"></el-option>

                        </el-select>
                    </el-form-item>
                    <el-form-item label="评级">
                        <el-select v-model="form.rate" placeholder="请选择">
                            <el-option key="1" label="A" value="A"></el-option>
                            <el-option key="2" label="B" value="B"></el-option>
                            <el-option key="3" label="C" value="C"></el-option>
                            <el-option key="4" label="D" value="D"></el-option>
                            <el-option key="5" label="E" value="E"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="评级说明">
                        <el-input type="textarea" rows="5" v-model="form.rateDesc"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onReturn">返回</el-button>
                        <el-button type="primary" @click="save">保存</el-button>
                        <el-button type="primary" @click="saveAndSubmit">保存并提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: 'addbusinessManger',
        data: function(){
            var checkIdCard = (rule, value, callback) => {
                console.log("checkOutputValue: check");
                if (!value) {
                    return callback(new Error('身份证号不能为空'));
                }
                // 正则表达式校验
                var reg=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('身份证号不正确'));
                    } else {
                        this.$axios.get('api/checkIdCard',{params:{
                                idCard: value
                            }}).then(function (response) {
                            console.log(response);
                            if (response.data.extend.result == 1) {
                                callback(new Error(response.data.extend.message));
                            } else {
                                callback();
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                }, 1000);
            };
            var checkPhone = (rule, value, callback) => {
                console.log("checkOutputValue: check");
                if (!value) {
                    return callback(new Error('手机号不能为空'));
                }
                // 正则表达式校验
                var reg=/^1[34578]\d{9}$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的手机号'));
                    } else {
                        this.$axios.get('api/checkPhone',{params:{
                                phone: value
                            }}).then(function (response) {
                            console.log(response);
                            if (response.data.extend.result == 1) {
                                callback(new Error(response.data.extend.message));
                            } else {
                                callback();
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                }, 1000);
            };
            return {
                companyId: null,
                companyList: null,
                idCard: null,
                jobDate: null,
                cooperateDate: null,
                form: {
                    name: null,
                    gender: null ,
                    idCard: null,
                    birthdate: null,
                    phone: null,
                    jobDate: null,
                    jobYearNumber: null,
                    cooperateDate: null,
                    cooperateYearNumber: null,
                    marriageStatus: null,
                    childCompanyId: null,
                    rate: null,
                    rateDesc: null,
                    status: 0,
                },
                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '请输入手机号', trigger: 'blur' },
                        { validator:  checkPhone, trigger: 'blur' }
                    ],
                    idCard: [
                        { required: true, message: '请输入身份证号', trigger: 'blur' },
                        { validator: checkIdCard, trigger: 'blur' }
                    ],
                    jobDate: [
                        { required: true, message: '请输入从业日期', trigger: 'blur' },
                    ],
                    cooperateDate: [
                        { required: true, message: '请输入与公司合作日期', trigger: 'blur' },
                    ]
                }
            }
        },
        // 初始化获取数据
        created() {
            this.companyId = localStorage.getItem('userInfoId'),
            // this.getData();
            this.getCompangList();
        },
        // 监听器
        watch: {
            idCard:function() {
                let id_card = this.idCard;
                this.form.idCard = id_card;
                if (this.idCard.length === 18){
                    this.form.birthdate = id_card.substr(6, 4) + "-" + id_card.substr(10, 2) + "-" + id_card.substr(12, 2);
                    if (parseInt(id_card.substr(14, 3)) % 2 === 0) {
                        this.form.gender = "F"
                    }else {
                        this.form.gender = "M"
                    }
                }

            },
            jobDate:function() {
                this.form.jobDate = this.jobDate;
                this.form.jobYearNumber = new Date().getFullYear() - this.jobDate.substr(0, 4);
                console.log(new Date().getFullYear());
            },
            cooperateDate:function() {
                this.form.cooperateDate = this.cooperateDate;
                this.form.cooperateYearNumber = new Date().getFullYear() - this.cooperateDate.substr(0, 4);
            },
        },
        methods: {
            // 获取数据
            getCompangList(){
                let _than = this;
                this.$axios.get('cc/list',{params:{
                        id: this.companyId
                    }}).then(function (response) {
                    console.log(response);
                    _than.companyList = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 返回
            onReturn() {
                this.$message.success('返回！');
                this.$router.push("business-manager-list")
            },
            // 保存
            save(){
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                });
                this.$axios.post('financier/save',
                    this.qs.stringify(
                        {
                            companyId:this.companyId,
                            name: this.form.name,
                            gender: this.form.gender,
                            idCard: this.form.idCard,
                            birthdate: this.form.birthdate,
                            phone: this.form.phone,
                            jobDate: this.form.jobDate,
                            jobYearNumber: this.form.jobYearNumber,
                            cooperateDate: this.form.cooperateDate,
                            cooperateYearNumber: this.form.cooperateYearNumber,
                            marriageStatus: this.form.marriageStatus,
                            childCompanyId: this.form.childCompanyId,
                            rate: this.form.rate,
                            rateDesc: this.form.rateDesc,
                            status: this.form.status,
                        }
                    )).then(function (response) {
                    console.log(response);
                    this.$router.push("business-manager-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
            // 保存并提交
            saveAndSubmit(){
                this.form.status = 1;
                this.save();
                this.$router.push("business-manager-list")
            }
        }
    }
</script>

<style scoped>

    .crumbs {
        background-color: #fff;
        padding-left: 50px;
        margin-bottom: 0;
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
