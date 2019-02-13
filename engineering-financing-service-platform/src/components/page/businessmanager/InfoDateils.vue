<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>授信项目详情</span>
                    </div>
                    <!-- 商务经理个人资信情况 -->
                    <div class="info-box">
                        <div class="info-title">商务经理个人资信情况</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn">展开</div>
                            <div class="info-content">
                                <!-- 个人身份信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">身份信息</div>
                                    <div class="content-lable2">已认证</div>
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="姓名：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="身份证号：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="户籍所在地：" style="margin-bottom: 0"> 深圳市</el-form-item>
                                        <el-form-item label="手机号码：" style="margin-bottom: 0">13534074557</el-form-item>
                                        <el-form-item label="银行卡号：" style="margin-bottom: 0">6013822000982256896</el-form-item>
                                        <el-form-item label="开户行：" style="margin-bottom: 0">中国银行</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>手持身份证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>户口本个人页</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 婚姻信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">婚姻状况</div>
                                    <div class="content-lable2">已婚</div>
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="配偶姓名：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="配偶身份证号：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="配偶户籍所在地：" style="margin-bottom: 0"> 深圳市</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>配偶身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>配偶身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>结婚证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>配偶户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>配偶户口本个人页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" hidden class="card-file">
                                                    <img src="../../../../static/img/gcd.png" class="image">
                                                    <span>离婚证</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 社保信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">社保信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="社保电脑号：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="当前购买社保公司：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="近一年缴纳社保清单：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <!--<span>社保清单</span>-->
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <div class="content-title">
                                    <div class="content-lable1">个人资产</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <!-- 房产信息 -->
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="房产 1" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="房产所在地：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="房产证明：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <!--<span>房产证明</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="房产说明：" style="margin-bottom: 0">房产说明</el-form-item>
                                    </el-form>
                                </div>
                                <!-- 车辆信息 -->
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="车辆 1" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="车牌号：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="车辆行驶证：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../../static/img/fyd.png" class="image">
                                                    <!--<span>车辆行驶证</span>-->
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="车辆说明：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 审批记录 -->
                    <div class="info-box">
                        <div class="info-title">审批记录</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn">展开</div>
                            <!-- 担保审批 -->
                            <div class="info-content">
                                <div class="approve-title-box">
                                    <div class="approve-title-left">担保评估结果</div>
                                    <div class="approve-title-right">评估人：习总   评估时间：2019-01-01 19:30:26</div>
                                </div>
                                <!-- 审批意见 -->
                                <div class="approve-content-box">
                                    <div class="approve-item">
                                        <div class="approve-item-left">是否可授信</div>
                                        <div class="approve-item-right">是</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">授信金额</div>
                                        <div class="approve-item-right">300万元</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">授信期限</div>
                                        <div class="approve-item-right">2019-01-01 至 2020-12-31</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">还款方式（建议）</div>
                                        <div class="approve-item-right">0000000是</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">内控要点</div>
                                        <div class="approve-item-right">是0000000000000</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">贷后管理</div>
                                        <div class="approve-item-right">是0000000000000</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">进入首页</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'business-info-dateils',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                // id: this.$route.query.id,
                businessInfo: null,

            }
        },
        components: {
        },
        computed: {
        },
        created(){
            this. getDataInfo();
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            // 获取信息
            getDataInfo(){
                let _than = this;
                this.$axios.get('api/authen/bm_info_details',{params:{
                        id: this.$route.query.id
                    }}).then(function (response) {
                    console.log(response);
                    _than.businessInfo = response.data.extend;
                }).catch(function (error) {
                    console.log(error);
                });
            },


        }
    }

</script>


<style>
    .info-title{
        font-size:14px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
        width: 100%;
        line-height:19px;
        margin: 0 auto;
        text-align: center;
        border: 1px dashed #ccc;
        padding: 20px 0;
    }
    .info-btn-box{
        width: 100%;
        padding-top: 20px;
        border-top: 1px dashed #ccc;
        border: 1px dashed #ccc;
    }
    .info-content{
        width: 100%;
        /*height: 300px;*/
    }
    .is-show-btn{
        height:19px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        color:rgba(255,130,8,1);
        width: 80px;
        line-height:19px;
        text-align: center;
        border: 1px solid rgba(255,130,8,1);
        margin-left: auto;


    }

    .content-title{
        display: block;
        margin: 0 auto 0 0;
        padding: 10px 10px;
    }
    .content-lable1{
        display: block;
        float: left;
        height:20px;
        font-size:14px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
        line-height:20px;
        padding: 0 10px;
        width: 60px;
    }
    .content-lable2{
        display: block;
        float: left;
        width: 60px;
        height: 20px;
        background: #FFE699;
        font-size:12px;
        font-family:MicrosoftYaHei;
        color:rgba(187,114,0,1);
        line-height:20px;
        text-align: center;
    }
    .content-info-box{
        padding: 20px 20px 20px 150px;
    }

    .file-box{
        padding: 10px 0;
        text-align: center;
    }
    .card-file{
        float: left;
        width: 120px;
        /*height: 170px;*/
        margin-left: 20px;
    }
    .image {
        width: 100%;
        height: 120px;
        display: block;
    }

    .approve-title-box{
        margin-top: 20px;
        width: 100%;
        height:40px;
        background:rgba(92,185,246,1);
        border:1px solid rgba(92,185,246,1);
        color: #ffffff;
    }
    .approve-title-left{
        line-height: 40px;
        float: left;
        padding-left: 20px;
    }
    .approve-title-right{
        padding-right: 20px;
        float: right;
        line-height: 40px;
    }
    .approve-content-box{
        border-right: 1px solid #ccc;
        border-left: 1px solid #ccc;
    }
    .approve-item {
        /*display: block;*/
        border-bottom: 1px solid #ccc;
        height: 40px;
    }
    .approve-item-left{
        display: block;
        float: left;
        width: 20%;
        border-right: 1px solid #ccc;
        line-height: 40px;
        text-align: center;
    }
    .approve-item-right{
        width: auto;
        padding-left: 20px;
        display: block;
        float: left;
        line-height: 40px;
        text-align: left;
    }


    .info-bottom-box{
        position: relative;
        width: 260px;
        margin: 0 auto;
        text-align: center;
        padding: 30px 0 40px 0;

    }
    .info-bottom-btn1{
        position: relative;
        float: left;
        width:120px;
        height:30px;
        line-height: 30px;
        background:rgba(255,130,8,1);
        border-radius:5px;
        color: rgba(255,255,255,1);
    }
    .info-bottom-btn2{
        position: relative;
        float: left;
        margin-left: 20px;
        width:120px;
        height:30px;
        line-height: 30px;
        background:rgba(255,130,8,1);
        border-radius:5px;
        color: rgba(255,255,255,1);
    }

    /*上传*/
</style>
