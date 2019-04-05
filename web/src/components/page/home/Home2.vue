<template>
    <div>
        <el-row :gutter="20">
            <el-col>
                <el-card shadow="hover" class="product-box">
                    <div class="product-header">
                        <span>金融产品</span>
                    </div>
                    <div class="product-list">
                        <div class="product-list-item">
                            <div class="product-card">
                                <img src="../../../assets/img/gcd.png"/>
                            </div>
                            <!--<div class="product-name"></div>-->
                            <div class="product-btn-box">
                                <div class="product-btn1" @click="applyCredit">申请授信</div>
                                <div class="product-btn2" @click="applyLoan">申请放款</div>
                            </div>
                        </div>

                        <div class="product-list-item">
                            <div class="product-card">
                                <img src="../../../assets/img/fyd.png"/>
                            </div>
                            <div class="product-name">房易贷</div>
                            <!--<div class="product-btn-box">-->
                                <!--<div class="product-btn1">申请授信</div>-->
                                <!--<div class="product-btn2">申请放款</div>-->
                            <!--</div>-->
                        </div>

                        <div class="product-list-item">
                            <div class="product-card">
                                <img src="../../../assets/img/tbd.png"/>
                            </div>
                            <div class="product-name">投标贷</div>
                            <!--<div class="product-btn-box">-->
                                <!--<div class="product-btn1">申请授信</div>-->
                                <!--<div class="product-btn2">申请放款</div>-->
                            <!--</div>-->
                        </div>

                        <div class="product-list-item">
                            <div class="product-card">
                                <img src="../../../assets/img/qt.png"/>
                            </div>
                            <div class="product-name">其他产品</div>
                            <!--<div class="product-btn-box">-->
                                <!--<div class="product-btn1">申请授信</div>-->
                                <!--<div class="product-btn2">申请放款</div>-->
                            <!--</div>-->
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import Schart from 'vue-schart';
    import bus from '../../common/bus';
    export default {
        name: 'dashboard',
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
        methods: {
            /**
             * 工程贷 申请授信
             */
            applyCredit(){
                this.$router.push({
                    path:'engineeringloan-credit-add'
                })
            },
            /**
             * 工程贷 申请放款
             */
            applyLoan(){
                this.$router.push({
                    path:'engineeringloan-loan-add'
                })
            }
        }
    }

</script>


<style scoped>

    .product-box{
        padding-bottom: 20px;
    }
    .product-header{
        font-size:14px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
    }
    .product-list{
        margin-top: 30px;
        /*border: 1px solid #ccc;*/
    }
    .product-list-item{
        float: left;
        width: 260px;
        /*border: 1px solid #ccc;*/
        padding: 10px 21px;
    }
    .product-card{
        width: 100%;
        /*border: 1px solid #ccc;*/
        text-align: center;
    }
    .product-card img{
        width: 128px;
        height: 145px;
        padding: auto auto;
    }
    .product-name {
        padding-top: 20px;
        width: 100%;
        height: 30px;
        line-height: 30px;
        text-align: center;
    }
    .product-btn-box{
        width: 100%;
        padding-top: 20px;
    }
    .product-btn1{
        float: left;
        display: block;
        width:118px;
        height:28px;
        border-radius:5px;
        border:1px solid rgba(255,113,28,1);
        margin-right: 20px;
        line-height: 30px;
        text-align: center;
    }
    .product-btn2{
        float: left;
        display: block;
        width:118px;
        height:28px;
        line-height: 30px;
        border-radius:5px;
        border:1px solid rgba(255,113,28,1);
        text-align: center;
    }
    .product-btn1:hover{
        background-color: rgba(255,113,28,1);
        color: #ffffff;
    }
    .product-btn2:hover{
        background-color: rgba(255,113,28,1);
        color: #ffffff;
    }

</style>
