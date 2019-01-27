<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>授信项目详情</span>
                    </div>

                    <!-- 申请信息 -->
                    <div class="info-box">
                        <div class="info-title">申请信息</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn">展开</div>
                            <div class="info-content">
                                <div class="credit-info">
                                    <div class="credit-info-left">
                                        <span>刘长海</span>
                                        <p><span>申请额度500万</span></p>
                                    </div>
                                    <div class="credit-info-right">
                                        <div class="credit-info-span">
                                            <div class="credit-info-span-li1" style="width: 200px;">申请日期</div>
                                            <div class="credit-info-span-li1" style="width: 200px;">申请日期</div>
                                            <div class="credit-info-span-li1" style="width: 200px;">申请编号</div>
                                            <div class="credit-info-span-li1" style="width: 10%;">申请日期</div>
                                            <div class="credit-info-span-li1" style="width: 10%;">项目名称</div>
                                            <div class="credit-info-span-li1" style="width: 10%;">申请日期</div>
                                            <div class="credit-info-span-li1" style="width: 10%;">合同编号</div>
                                            <div class="credit-info-span-li1" style="width: 10%; border: none">申请日期</div>
                                        </div>
                                        <div class="credit-info-span" style="border-top: 1px solid #ccc">
                                            <div class="credit-info-span-li2" style="width: 200px;">申请事由</div>
                                            <div class="credit-info-span-li1" style="height: 100%; text-align: left; border: none">申00000000000000请日期</div>
                                        </div>
                                        <div class="credit-info-span" style="border-top: 1px solid #ccc">
                                            <div class="credit-info-span-li2" style="width: 200px; height: 100px;">个人近12个月银行流水</div>
                                            <div class="credit-info-span-li1" style=" border: none; height: 100px; height: 100%; text-align: left">申请日000000期</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 项目信息 -->
                    <div class="info-box">
                        <div class="info-title">项目信息</div>
                        <div class="info-btn-box">
                            <div class="is-show-btn">展开</div>
                            <div class="info-content">
                                <!-- 个人身份信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">项目基本信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="甲方名称：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="项目地址：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="合同金额：" style="margin-bottom: 0"> 深圳市</el-form-item>
                                        <el-form-item label="预计结算金额：" style="margin-bottom: 0">13534074557</el-form-item>
                                        <el-form-item label="保函要求：" style="margin-bottom: 0">6013822000982256896</el-form-item>
                                        <el-form-item label="施工期限：" style="margin-bottom: 0">2019-01-01 zhi 2021-01-01</el-form-item>
                                        <el-form-item label="项目施工进度：" style="margin-bottom: 0">13534074557</el-form-item>
                                        <el-form-item label="项目结算方式：" style="margin-bottom: 0">6013822000982256896</el-form-item>
                                        <el-form-item label="项目付款方式：" style="margin-bottom: 0">2019-01-01 zhi 2021-01-01</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>手持身份证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>户口本个人页</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 婚姻信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">项目成本信息</div>
                                    <!--<div class="content-lable2">已婚</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="项目利润率：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>工程进度回款台账</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>项目产值表</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>项目成本分析表</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>项目成本台账</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 社保信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">项目款项信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="社保电脑号：" style="margin-bottom: 0">张三</el-form-item>
                                        <el-form-item label="当前购买社保公司：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                        <el-form-item label="近一年缴纳社保清单：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/fyd.png" class="image">
                                                    <!--<span>社保清单</span>-->
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <div class="content-title">
                                    <div class="content-lable1">其它信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <!-- 房产信息 -->
                                <div class="content-info-box">
                                    <el-form>
                                        <el-form-item label="是否购买工商意外险" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="保险到期日：" style="margin-bottom: 0">2019-12-31</el-form-item>
                                        <el-form-item label="保单凭证：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/fyd.png" class="image">
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
                                                    <!--<img src="../../../static/img/fyd.png" class="image">-->
                                                    <span>车辆行驶证</span>
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            action="https://jsonplaceholder.typicode.com/posts/"
                                                            :show-file-list="false">
                                                        <img v-if="name === null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="车辆说明：" style="margin-bottom: 0">430426198502134569</el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
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
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>手持身份证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
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
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>配偶身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>配偶身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>结婚证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>配偶户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
                                                    <span>配偶户口本个人页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" hidden class="card-file">
                                                    <img src="../../../static/img/gcd.png" class="image">
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
                                                    <img src="../../../static/img/fyd.png" class="image">
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
                                                    <img src="../../../static/img/fyd.png" class="image">
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
                                                    <img src="../../../static/img/fyd.png" class="image">
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
                            <!-- 平台审批 -->
                            <div class="info-content">
                                <div class="approve-title-box">
                                    <div class="approve-title-left">平台审批结果</div>
                                    <div class="approve-title-right">评估人：习总   评估时间：2019-01-01 19:30:26</div>
                                </div>
                                <!-- 审批意见 -->
                                <div class="approve-content-box" style="height: 160px">
                                    <div class="approve-item">
                                        <div class="approve-item-left">是否可授信</div>
                                        <div class="approve-item-right">是</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">补充意见</div>
                                        <div class="approve-item-right">经核对，资料均符合要求，同意授信。</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">拒接原因</div>
                                        <div class="approve-item-right">2019-01-01 至 2020-12-31</div>
                                    </div>
                                    <div class="approve-item">
                                        <div class="approve-item-left">退回原因</div>
                                        <div class="approve-item-right">0000000是</div>
                                    </div>
                                </div>
                            </div>
                            <!-- 资金方审批 -->
                            <div class="info-content">
                                <div class="approve-title-box">
                                    <div class="approve-title-left">资金方审批结果</div>
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
                    <!-- 附件上传 -->
                    <div class="info-content">

                    </div>
                    <div class="info-content">
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">批复</div>

                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'credit-info-dateils',
        data() {
            return {
                name: localStorage.getItem('ms_username'),


            }
        },
        components: {
        },
        computed: {
        },
        created(){
        },
        activated(){
        },
        deactivated(){
        },
        methods: {



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

    .credit-info{
        border: 1px solid #ccc;
        height: 200px;
    }
    .credit-info-left{
        display: block;
        float: left;
        border: 1px solid #ccc;
        font-size: 18px;
        line-height: 100px;
        width: 150px;
        text-align: center;
        background:rgba(92,185,246,1);
        box-shadow:1px 1px 4px 0px rgba(0,0,0,0.5);
        font-weight:bold;
        color:rgba(255,255,255,1);
    }
    .credit-info-right{
        text-align: center;
        height: 100%;
    }
    .credit-info-span{
        width: 100%;
        height: 48px;
    }
    .credit-info-span-li1{
         float: left;
         width: 200px;
         line-height: 48px;
         border-right:  1px solid #ccc;
    }
    .credit-info-span-li2{
        float: left;
        width: 200px;
        line-height: 48px;
        border-right:  1px solid #ccc;

    }

    /*上传*/
    .el-upload--text{
        /*margin: 10px 10px;*/
        width: 120px;
        height: 120px;
    }
    .avatar-uploader{
        height: 120px;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 120px;
        height: 120px;
        line-height: 120px;
        text-align: center;
    }
    .avatar {
        width: 120px;
        height: 120px;
        display: block;
    }


</style>
