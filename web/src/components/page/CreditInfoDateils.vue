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
                                    <div class="credit-info-center">
                                        <div class="credit-info-center-span">申请日期</div>
                                        <div class="credit-info-center-span" style="border: 1px solid #ccc">申请事由</div>
                                        <div class="credit-info-center-span" style="height: 100px">个人近12个月银行流水</div>
                                    </div>
                                    <div class="credit-info-right">
                                        <div class="credit-info-right-span">
                                            <div class="ciedit-info-item" style="width: auto">2019-01-01</div>
                                            <div class="ciedit-info-item" style="width: auto">申请编号</div>
                                            <div class="ciedit-info-item" style="width: auto">2019-01-01</div>
                                            <div class="ciedit-info-item" style="width: auto">项目名称</div>
                                            <div class="ciedit-info-item" style="width: auto">2019-01-01</div>
                                            <div class="ciedit-info-item" style="width: auto">合同编号</div>
                                            <div class="ciedit-info-item" style="float: right">2019-01-01</div>
                                        </div>
                                        <div class="credit-info-right-span">20111111</div>
                                        <div class="credit-info-right-span" style="height: 100px"><img src="" height="100px" width="100px"></div>
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
                            <div class="info-content"></div>
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
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>手持身份证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
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
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>配偶身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>配偶身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>结婚证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>配偶户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
                                                    <span>配偶户口本个人页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" hidden class="card-file">
                                                    <img src="../../assets/img/gcd.png" class="image">
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
                                                    <img src="../../assets/img/fyd.png" class="image">
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
                                                    <img src="../../assets/img/fyd.png" class="image">
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
                                                    <img src="../../assets/img/fyd.png" class="image">
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

                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">批复</div>

                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img src="../../assets/img/img.jpg" class="user-avator" alt="">
                        <div class="user-info-cont">
                            <div class="user-info-name">{{name}}</div>
                            <div>{{role}}</div>
                        </div>
                    </div>
                    <div class="user-info-list">上次登录时间：<span>2018-01-01</span></div>
                    <div class="user-info-list">上次登录地点：<span>东莞</span></div>
                </el-card>
                <el-card shadow="hover" style="height:252px;">
                    <div slot="header" class="clearfix">
                        <span>语言详情</span>
                    </div>
                    Vue
                    <el-progress :percentage="71.3" color="#42b983"></el-progress>
                    JavaScript
                    <el-progress :percentage="24.1" color="#f1e05a"></el-progress>
                    CSS
                    <el-progress :percentage="3.7"></el-progress>
                    HTML
                    <el-progress :percentage="0.9" color="#f56c6c"></el-progress>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-lx-people grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">1234</div>
                                    <div>用户访问量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-lx-notice grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">321</div>
                                    <div>系统消息</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-lx-goods grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">5000</div>
                                    <div>数量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <el-card shadow="hover" style="height:403px;">
                    <div slot="header" class="clearfix">
                        <span>待办事项</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                    <el-table :data="todoList" :show-header="false" height="304" style="width: 100%;font-size:14px;">
                        <el-table-column width="40">
                            <template slot-scope="scope">
                                <el-checkbox v-model="scope.row.status"></el-checkbox>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column width="60">
                            <template slot-scope="scope">
                                <i class="el-icon-edit"></i>
                                <i class="el-icon-delete"></i>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="12">
                <el-card shadow="hover">
                    <schart ref="bar" class="schart" canvasId="bar" :data="data" type="bar" :options="options"></schart>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover">
                    <schart ref="line" class="schart" canvasId="line" :data="data" type="line" :options="options2"></schart>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import Schart from 'vue-schart';
    import bus from '../common/bus';
    export default {
        name: 'credit-info-dateils',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                todoList: [{
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要写100行代码加几个bug吧',
                        status: false,
                    }, {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: true,
                    },
                    {
                        title: '今天要写100行代码加几个bug吧',
                        status: true,
                    }
                ],
                data: [{
                        name: '2018/09/04',
                        value: 1083
                    },
                    {
                        name: '2018/09/05',
                        value: 941
                    },
                    {
                        name: '2018/09/06',
                        value: 1139
                    },
                    {
                        name: '2018/09/07',
                        value: 816
                    },
                    {
                        name: '2018/09/08',
                        value: 327
                    },
                    {
                        name: '2018/09/09',
                        value: 228
                    },
                    {
                        name: '2018/09/10',
                        value: 1065
                    }
                ],
                options: {
                    title: '最近七天每天的用户访问量',
                    showValue: false,
                    fillColor: 'rgb(45, 140, 240)',
                    bottomPadding: 30,
                    topPadding: 30
                },
                options2: {
                    title: '最近七天用户访问趋势',
                    fillColor: '#FC6FA1',
                    axisColor: '#008ACD',
                    contentColor: '#EEEEEE',
                    bgColor: '#F5F8FD',
                    bottomPadding: 30,
                    topPadding: 30
                }
            }
        },
        components: {
            Schart
        },
        computed: {
            role() {
                return this.name === 'admin' ? '超级管理员' : '普通用户';
            }
        },
        created(){
            this.handleListener();
            this.changeDate();
        },
        activated(){
            this.handleListener();
        },
        deactivated(){
            window.removeEventListener('resize', this.renderChart);
            bus.$off('collapse', this.handleBus);
        },
        methods: {
            changeDate(){
                const now = new Date().getTime();
                this.data.forEach((item, index) => {
                    const date = new Date(now - (6 - index) * 86400000);
                    item.name = `${date.getFullYear()}/${date.getMonth()+1}/${date.getDate()}`
                })
            },
            handleListener(){
                bus.$on('collapse', this.handleBus);
                // 调用renderChart方法对图表进行重新渲染
                window.addEventListener('resize', this.renderChart)
            },
            handleBus(msg){
                setTimeout(() => {
                    this.renderChart()
                }, 300);
            },
            renderChart(){
                this.$refs.bar.renderChart();
                this.$refs.line.renderChart();
            }
        }
    }

</script>


<style scoped>
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
        width: 70%;
        padding-left: 20px;
        display: block;
        float: left;
        line-height: 40px;
        text-align: left;
    }


    .info-bottom-box{
        width: 260px;
        margin: 0 auto;
        text-align: center;
        padding: 30px 0 40px 0;

    }
    .info-bottom-btn1{
        float: left;
        width:120px;
        height:30px;
        line-height: 30px;
        background:rgba(255,130,8,1);
        border-radius:5px;
        color: rgba(255,255,255,1);
    }
    .info-bottom-btn2{
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
    .credit-info-center{
        display: block;
        float: left;
        width: 150px;
        height: 100%;
        border: 1px solid #ccc;
        text-align: center;
    }
    .credit-info-center-span{
        display: block;
        line-height: 48px;
        text-align: center;
        font-size:12px;
        border: 1px solid #ccc;
    }
    .credit-info-right{
        margin: 0 auto;
        display: block;
        height: 200px;
        border: 1px solid #ccc;
    }

    .credit-info-right-span{
        display: block;
        line-height: 48px;
        border: 1px solid #ccc;
        width: 100%;
    }

    .ciedit-info-item{
        display: block;
        float: left;
        line-height: 48px;
        text-align: center;
        border-right: 1px solid #ccc;
        padding: 0px 10px;
    }



    .el-row {
        margin-bottom: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    .todo-item {
        font-size: 14px;
    }

    .todo-item-del {
        text-decoration: line-through;
        color: #999;
    }

    .schart {
        width: 100%;
        height: 300px;
    }

</style>
