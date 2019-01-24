<template>
    <div>
        <!--<div class="crumbs">-->
            <!--<el-breadcrumb separator="/" class="breadcrumb">-->
                <!--&lt;!&ndash;<el-breadcrumb-item>首页</el-breadcrumb-item>&ndash;&gt;-->
                <!--&lt;!&ndash;<el-breadcrumb-item>我的融资列表</el-breadcrumb-item>&ndash;&gt;-->
                <!--<el-breadcrumb-item>添加项目信息</el-breadcrumb-item>-->
            <!--</el-breadcrumb>-->
        <!--</div>-->
        <div class="container">
            <div style="width: 100%; color: #000000; border-left: 3px solid #ff8208; padding: 5px 20px; margin-bottom: 20px">添加项目信息</div>
            <div class="info-title">申请信息</div>
            <div class="apply-info-box">
                <div style="width: 200px;height: 100%; line-height: 100%; text-align: left; background-color: #5CB9F6; float: left; color: #ffffff">
                    <h3 style="padding: 30px 10px">{{dataInfo.financier.name}}</h3>
                    <h3 style="padding: 20px 10px">申请额度：{{dataInfo.creditApply.applyAmount}}.00万</h3>
                </div>
                <div style="width: 100%; text-align: center">
                    <div style="width: 100%; height:30px; line-height: 30px; text-align: center">
                        <div style="width: 200px; height: 100%; float: left">申请日期</div>
                        <div style="width: 120px; border-left: 1px solid #ccc; height: 100%; float: left">{{this.dataInfo.creditApply.date}}</div>
                        <div style="width: 100px; border-left: 1px solid #ccc; height: 100%; float: left">申请编号</div>
                        <div style="width: 12%; border-left: 1px solid #ccc; height: 100%; float: left">{{this.dataInfo.creditApply.creditNo}}</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">项目名称</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">{{this.dataInfo.project.projectName}}</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">项目合同编号</div>
                        <div style="width: 10%; border-left: 1px solid #ccc; height: 100%; float: left">{{this.dataInfo.creditApply.contractNo}}</div>
                    </div>
                    <div style="width: 100%; height: 30px; line-height: 30px; text-align: center; border-top: 1px solid #ccc; border-bottom: 1px solid #ccc">
                        <div style="width: 200px; float: left; height: 30px; border-right: 1px solid #ccc;">申请事由</div>
                        <div style=" height: 30px; text-align: left">{{this.dataInfo.creditApply.originIncident}}</div>
                    </div>
                    <div style="width: 100%;  height: 100%;line-height: 116px; text-align: center">
                        <div style="width: 200px;  height: 100%; float: left; border-right: 1px solid #ccc;">个人近12个月银行流水</div>
                        <div style="width: 100%; height: 100%; text-align: left" @click="findFile(dataInfo.creditApply.bankListFile)">
                            <img :src="'http://192.168.1.89/' + dataInfo.creditApply.bankListFile" style="width: 100px"/></div>
                    </div>

                </div>
            </div>
            <!-- 第一步 -->
            <div v-show="isStep1 === true">
                <div class="info-title">添加项目信息</div>
                <div class="project-info">
                    <el-row>项目基本信息</el-row>
                    <div class="form-box">
                        <el-form ref="form" :model="form" label-width="180px">
                            <el-form-item label="甲方名称">
                                <el-input v-model="form.partyAName" style="width: 400px"></el-input>
                            </el-form-item>
                            <el-form-item label="项目地址">
                                <el-input v-model="form.projectAdress" style=" width: 400px"></el-input>
                            </el-form-item>
                            <el-form-item label="合同金额">
                                <el-input v-model="this.dataInfo.project.contractAmount" style="width: 100px" disabled></el-input>万
                            </el-form-item>
                            <el-form-item label="预计结算金额">
                                <el-input v-model="form.shouldClearAmount" class="input-width"></el-input>
                            </el-form-item>
                            <el-form-item label="保函要求">
                                <el-input v-model="form.guaranteeClaim" class="input-width"></el-input>
                            </el-form-item>
                            <el-form-item label="施工期限">
                                <el-date-picker
                                    v-model="constructionDate"
                                    value-format="yyyy-MM-dd"
                                    type="daterange"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="项目施工进度">
                                <el-input v-model="this.dataInfo.project.projectProgress" style="width: 50px"></el-input>%
                            </el-form-item>
                            <el-form-item label="项目结算方式">
                                <el-input v-model="form.clearType" class="input-width"></el-input>
                            </el-form-item>

                            <el-form-item label="项目付款方式">
                                <el-form :inline="true">
                                    <el-row>
                                        <el-form-item label="预付款">
                                            <el-input v-model="form.shouldPaymentAmount" placeholder="万元"></el-input>
                                        </el-form-item>
                                        <el-form-item label="预付款支付日期">
                                            <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="form.shouldPaymentDate" style="width: 200px;"></el-date-picker>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="进度款">
                                            <el-select v-model="form.progressPaymentType" placeholder="请选择" style="width: 200px">
                                                <el-option label="月度支付" value="月度支付"></el-option>
                                                <el-option label="季度支付" value="季度支付"></el-option>
                                                <el-option label="一次性支付" value="一次性支付"></el-option>
                                                <el-option label="其它" value="其它"></el-option>
                                            </el-select>
                                        </el-form-item>
                                        <el-form-item label="月结算日">
                                            <el-input v-model="form.monthClearDay" placeholder="日"></el-input>
                                        </el-form-item>
                                        <el-form-item label="进度付款比例至">
                                            <el-input v-model="form.progressPaymentRatio" placeholder="%"></el-input>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="验收款：付款比例至">
                                            <el-input v-model="form.checkPaymentRatio" placeholder="%"></el-input>
                                        </el-form-item>
                                        <el-form-item label="验收款备注">
                                            <el-input v-model="form.checkPaymentDesc" placeholder="备注"></el-input>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="结算款：付款比例至">
                                            <el-input v-model="form.clearPaymentRatio" placeholder="%"></el-input>
                                        </el-form-item>
                                        <el-form-item label="结算款备注">
                                            <el-input v-model="form.clearDesc" placeholder="备注"></el-input>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="质保金：质保金比例">
                                            <el-input v-model="form.warrantyMoneyPaymentRatio" placeholder="%"></el-input>
                                        </el-form-item>
                                        <el-form-item label="质保期">
                                            <el-input v-model="form.warranty" placeholder="年" style="width: 100px"></el-input>年
                                        </el-form-item>
                                        <el-form-item label="质保期支付方式">
                                            <el-input v-model="form.warrantyPayType" placeholder=""></el-input>
                                        </el-form-item>
                                    </el-row>

                                </el-form>
                            </el-form-item>
                            <div style="text-align: left; padding-left: 180px">
                                <el-form :inline="true">
                                    <el-form-item label="">
                                        项目合同
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn10}"
                                            name="file"
                                            :on-success="handleSuccess10"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                    <el-form-item label="">
                                        合同付款方式页附件凭证
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn11}"
                                            name="file"
                                            :on-success="handleSuccess11"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                    <el-form-item label="">
                                        内部经营承包责任书附件
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn12}"
                                            name="file"
                                            :on-success="handleSuccess12"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                    <el-form-item label="">
                                        施工现场图片
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn13}"
                                            name="file"
                                            :on-success="handleSuccess13"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                </el-form>
                            </div>
                        </el-form>
                    </div>
                </div>
            </div>
            <!-- 第二步 -->
            <div v-show="isStep2 === true">
                <div class="info-title">添加项目信息</div>
                <div class="project-cost-info">
                    <el-row>项目成本信息</el-row>
                    <div class="form-box">
                        <el-form ref="form" :model="form" label-width="180px">
                            <el-form-item label="项目利润率">
                                <el-input v-model="form.profitMargin" style="width: 100px"></el-input>%
                            </el-form-item>
                            <div style="padding-left: 100px;">
                                <el-form :inline="true">
                                    <el-form-item label="">
                                        工程进度回款台账
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn20}"
                                            name="file"
                                            :on-success="handleSuccess20"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                    <el-form-item label="">
                                        项目产值表
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn21}"
                                            name="file"
                                            :on-success="handleSuccess21"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                    <el-form-item label="">
                                        项目成本分析表
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn22}"
                                            name="file"
                                            :on-success="handleSuccess22"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                    <el-form-item label="">
                                        项目成本台账
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn23}"
                                            name="file"
                                            :on-success="handleSuccess23"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                </el-form>
                            </div>
                        </el-form>
                    </div>
                </div>
            </div>
            <!-- 第三步 -->
            <div v-show="isStep3 === true">
                <div class="info-title">添加项目信息</div>
                <div class="project-cost-info">
                    <el-row>项目款项信息</el-row>
                    <div class="form-box">
                        <el-form :inline="true" label-width="150px">
                            <el-row>
                                <el-form-item label="前期累计开票额">
                                    <el-input v-model="form.sumBillAmount" style="width: 100px"></el-input>万元
                                </el-form-item>
                                <el-form-item label="">
                                    发票凭证
                                    <el-upload
                                        :action="uploadPath"
                                        list-type="picture-card"
                                        :class="{disabled: isShowUploadIdBtn30}"
                                        name="file"
                                        :on-success="handleSuccess30"
                                        :on-preview="handlePictureCardPreview"
                                        :on-remove="handleRemove">
                                        <i class="el-icon-plus"></i>
                                    </el-upload>
                                </el-form-item>
                            </el-row>
                            <el-row>
                                <el-form-item label="前期累计回款额">
                                    <el-input v-model="form.sumPaybackAmount" style="width: 100px"></el-input>万元
                                </el-form-item>
                                <el-form-item label="">
                                    转账凭证
                                    <el-upload
                                        :action="uploadPath"
                                        list-type="picture-card"
                                        :class="{disabled: isShowUploadIdBtn31}"
                                        name="file"
                                        :on-success="handleSuccess31"
                                        :on-preview="handlePictureCardPreview"
                                        :on-remove="handleRemove">
                                        <i class="el-icon-plus"></i>
                                    </el-upload>
                                </el-form-item>
                                <el-form-item label="">
                                    对应发票凭证
                                    <el-upload
                                        :action="uploadPath"
                                        list-type="picture-card"
                                        :class="{disabled: isShowUploadIdBtn32}"
                                        name="file"
                                        :on-success="handleSuccess32"
                                        :on-preview="handlePictureCardPreview"
                                        :on-remove="handleRemove">
                                        <i class="el-icon-plus"></i>
                                    </el-upload>
                                </el-form-item>
                            </el-row>
                            <el-row>
                                <el-form-item label="前期累计付款额">
                                    <el-input v-model="form.sumPaymentAmount" style="width: 100px"></el-input>万元
                                </el-form-item>
                                <el-form-item label="">
                                    采购合同
                                    <el-upload
                                        :action="uploadPath"
                                        list-type="picture-card"
                                        :class="{disabled: isShowUploadIdBtn33}"
                                        name="file"
                                        :on-success="handleSuccess33"
                                        :on-preview="handlePictureCardPreview"
                                        :on-remove="handleRemove">
                                        <i class="el-icon-plus"></i>
                                    </el-upload>
                                </el-form-item>
                                <el-form-item label="">
                                    采购发票
                                    <el-upload
                                        :action="uploadPath"
                                        list-type="picture-card"
                                        :class="{disabled: isShowUploadIdBtn34}"
                                        name="file"
                                        :on-success="handleSuccess34"
                                        :on-preview="handlePictureCardPreview"
                                        :on-remove="handleRemove">
                                        <i class="el-icon-plus"></i>
                                    </el-upload>
                                </el-form-item>
                                <el-form-item label="">
                                    送货单
                                    <el-upload
                                        :action="uploadPath"
                                        list-type="picture-card"
                                        :class="{disabled: isShowUploadIdBtn35}"
                                        name="file"
                                        :on-success="handleSuccess35"
                                        :on-preview="handlePictureCardPreview"
                                        :on-remove="handleRemove">
                                        <i class="el-icon-plus"></i>
                                    </el-upload>
                                </el-form-item>
                                <el-form-item label="">
                                    银行转账凭证
                                    <el-upload
                                        :action="uploadPath"
                                        list-type="picture-card"
                                        :class="{disabled: isShowUploadIdBtn36}"
                                        name="file"
                                        :on-success="handleSuccess36"
                                        :on-preview="handlePictureCardPreview"
                                        :on-remove="handleRemove">
                                        <i class="el-icon-plus"></i>
                                    </el-upload>
                                </el-form-item>
                            </el-row>
                        </el-form>
                    </div>
                </div>
            </div>
            <!-- 第四步 -->
            <div v-show="isStep4 === true">
                <div class="info-title">添加项目信息4</div>
                <div class="project-cost-info">
                    <el-row>其它信息</el-row>
                    <div class="form-box">
                        <el-form ref="form" :model="form" label-width="180px">
                            <el-form-item label="是否购买工商意外险">
                                <el-radio-group v-model="form.isBuyInsur">
                                    <el-radio :label= "1">是</el-radio>
                                    <el-radio :label= "0">否</el-radio>
                                </el-radio-group>
                            </el-form-item>
                            <div style="padding-left: 40px; height: 220px; text-align: left">
                                <div v-show="form.isBuyInsur === 1">
                                    <el-form :inline="true" class="demo-form-inline">
                                        <el-form-item label="保险到期日">
                                            <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="form.insurEndDate" style="width: 200px;"></el-date-picker>
                                        </el-form-item>
                                        <el-form-item label="保额">
                                            <el-input v-model="form.insurAmount" placeholder=""></el-input>
                                        </el-form-item>
                                        <el-form-item label="人数">
                                            <el-input v-model="form.insurPersonNumber" placeholder="个"></el-input>
                                        </el-form-item>
                                    </el-form>
                                    <el-form-item label="保单凭证">
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn40}"
                                            name="file"
                                            :on-success="handleSuccess40"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                        <!--<div @click="dialogFileVisible = true">-->
                                            <!--<iframe src="http://192.168.1.89/group1/M00/00/00/wKgBWVwY1SKAaoVfAAN9O0dIAx8286.pdf" width="100" height="100"></iframe>-->
                                        <!--</div>-->

                                    </el-form-item>
                                </div>
                            </div>
                            <el-form-item label="特殊事项（选填）">
                                <el-input type="textarea" v-model="form.unusualDesc" rows ="5" style="width: 800px"></el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
            </div>
            <el-form>
                <div>
                    <el-form-item style="padding-left: 300px" v-show="isStep1 === true">
                        <el-button type="primary" @click="onReturn" class="btn-margin">返回</el-button>
                        <el-button type="primary" @click="secondStep" class="btn-margin">下一步</el-button>
                    </el-form-item>
                    <el-form-item style="padding-left: 300px" v-show="isStep2 === true">
                        <el-button type="primary" @click="oneStep" class="btn-margin">上一步</el-button>
                        <el-button type="primary" @click="threeStep" class="btn-margin">下一步</el-button>
                    </el-form-item>
                    <el-form-item style="padding-left: 300px" v-show="isStep3 === true">
                        <el-button type="primary" @click="secondStep" class="btn-margin">上一步</el-button>
                        <el-button type="primary" @click="fourStep" class="btn-margin">下一步</el-button>
                    </el-form-item>
                    <el-form-item style="padding-left: 300px" v-show="isStep4 === true">
                        <el-button type="primary" @click="threeStep" class="btn-margin">上一步</el-button>
                        <el-button type="warning" class="btn-margin" @click="save">保存</el-button>
                        <el-button type="warning" class="btn-margin" @click="sumbit">保存并提交</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </div>
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
        <el-dialog :visible.sync="dialogFileVisible">
            <!--<img style="width: 100%" :src="'http://192.168.1.89/'+ this.dataInfo.cai.bankListFile"/>-->
            <!--<iframe src="http://192.168.1.89/group1/M00/00/00/wKgBWVwY1SKAaoVfAAN9O0dIAx8286.pdf" width="100%" height="100%"></iframe>-->
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'credit-apply-dtail',
        data: function(){
            return {
                uploadPath: 'http://192.168.1.98:8088/filesystem/upload/',
                creditId: this.$route.query.id,
                dataInfo: null,
                constructionDate: null,
                form: {
                    id: null,
                    partyAName:null,
                    projectAdress: null,
                    shouldClearAmount: null,
                    guaranteeClaim: null,
                    constructionStartDate: null,
                    constructionEndDate: null,
                    clearType: null,
                    shouldPaymentAmount: null,
                    shouldPaymentDate: null,
                    progressPaymentType: null,
                    monthClearDay: null,
                    progressPaymentRatio:null,
                    checkPaymentRatio: null,
                    checkPaymentDesc: null,
                    clearPaymentRatio: null,
                    clearDesc: null,
                    warrantyMoneyPaymentRatio: null,
                    warranty: null,
                    warrantyPayType: null,

                    profitMargin: null,

                    sumBillAmount: null,
                    sumPaybackAmount: null,
                    sumPaymentAmount: null,

                    isBuyInsur: 1,
                    insurEndDate: null,
                    insurAmount: null,
                    insurPersonNumber: null,

                    unusualDesc: null,
                },
                contractFile:null,
                contractPaymentFile:null,
                inContractLiabilityBook: null,
                constructionSitePhoto: null,

                progressReturnAccount: null,
                outputValueTable: null,
                costAnalysisTable: null,
                costAccount: null,

                invoiceFile: null,
                transferFile: null,
                transferToInvoiceFile: null,
                buyContractFile: null,
                buyInvoiceFile: null,
                deliveryNote: null,
                banckTransfer: null,
                insurFile: null,

                dialogFileVisible: false,
                dialogImageUrl: '',
                dialogVisible: false,
                isStep1: true,
                isStep2: false,
                isStep3: false,
                isStep4: false,

                isShowUploadIdBtn10: false,
                isShowUploadIdBtn11: false,
                isShowUploadIdBtn12: false,
                isShowUploadIdBtn13: false,

                isShowUploadIdBtn20: false,
                isShowUploadIdBtn21: false,
                isShowUploadIdBtn22: false,
                isShowUploadIdBtn23: false,

                isShowUploadIdBtn30: false,
                isShowUploadIdBtn31: false,
                isShowUploadIdBtn32: false,
                isShowUploadIdBtn33: false,
                isShowUploadIdBtn34: false,
                isShowUploadIdBtn35: false,
                isShowUploadIdBtn36: false,

                isShowUploadIdBtn40: false,
            }
        },
        created() {
            this.getCreditDataInfo();
        },
        // 监听器
        watch: {
            constructionDate:function(val) {
                this.form.constructionStartDate = this.constructionDate[0];
                this.form.constructionEndDate = this.constructionDate[1];
            },
            contractFile:function(val) {
                let _that = this;
                if(_that.contractFile !== null){
                    _that.isShowUploadIdBtn10 = true;
                    return;
                }
                _that.isShowUploadIdBtn10 = false;
            },
            contractPaymentFile:function(val) {
                let _that = this;
                if(_that.contractPaymentFile !== null){
                    _that.isShowUploadIdBtn11 = true;
                    return;
                }
                _that.isShowUploadIdBtn11 = false;
            },
            inContractLiabilityBook:function(val) {
                let _that = this;
                if(_that.inContractLiabilityBook !== null){
                    _that.isShowUploadIdBtn12 = true;
                    return;
                }
                _that.isShowUploadIdBtn12 = false;
            },
            constructionSitePhoto:function(val) {
                let _that = this;
                if(_that.constructionSitePhoto !== null){
                    _that.isShowUploadIdBtn13 = true;
                    return;
                }
                _that.isShowUploadIdBtn13 = false;
            },
            progressReturnAccount:function(val) {
                let _that = this;
                if(_that.progressReturnAccount !== null){
                    _that.isShowUploadIdBtn20 = true;
                    return;
                }
                _that.isShowUploadIdBtn20 = false;
            },
            outputValueTable:function(val) {
                let _that = this;
                if(_that.outputValueTable !== null){
                    _that.isShowUploadIdBtn21 = true;
                    return;
                }
                _that.isShowUploadIdBtn21 = false;
            },
            costAnalysisTable:function(val) {
                let _that = this;
                if(_that.costAnalysisTable !== null){
                    _that.isShowUploadIdBtn22 = true;
                    return;
                }
                _that.isShowUploadIdBtn22 = false;
            },
            costAccount:function(val) {
                let _that = this;
                if(_that.costAccount !== null){
                    _that.isShowUploadIdBtn23 = true;
                    return;
                }
                _that.isShowUploadIdBtn23 = false;
            },
            invoiceFile:function(val){
                let _that = this;
                if(_that.invoiceFile !== null){
                    _that.isShowUploadIdBtn30 = true;
                    return;
                }
                _that.isShowUploadIdBtn30 = false;

            },
            transferFile:function(val){
                let _that = this;
                if(_that.transferFile !== null){
                    _that.isShowUploadIdBtn31 = true;
                    return;
                }
                _that.isShowUploadIdBtn31 = false;

            },
            transferToInvoiceFile:function(val) {
                let _that = this;
                if(_that.transferToInvoiceFile !== null){
                    _that.isShowUploadIdBtn32 = true;
                    return;
                }
                _that.isShowUploadIdBtn32 = false;
            },
            buyContractFile:function(val) {
                let _that = this;
                if(_that.buyContractFile !== null){
                    _that.isShowUploadIdBtn33 = true;
                    return;
                }
                _that.isShowUploadIdBtn33 = false;
            },
            buyInvoiceFile:function(val) {
                let _that = this;
                if(_that.buyInvoiceFile !== null){
                    _that.isShowUploadIdBtn34 = true;
                    return;
                }
                _that.isShowUploadIdBtn34 = false;
            },
            deliveryNote:function(val) {
                let _that = this;
                if(_that.deliveryNote !== null){
                    _that.isShowUploadIdBtn35 = true;
                    return;
                }
                _that.isShowUploadIdBtn35 = false;
            },
            banckTransfer:function(val) {
                let _that = this;
                if(_that.banckTransfer !== null){
                    _that.isShowUploadIdBtn36 = true;
                    return;
                }
                _that.isShowUploadIdBtn36 = false;
            },

            insurFile:function(val) {
                let _that = this;
                if(_that.insurFile !== null){
                    _that.isShowUploadIdBtn40 = true;
                    return;
                }
                _that.isShowUploadIdBtn40 = false;
            },
        },
        methods: {
            getCreditDataInfo(){
                let _than = this;
                this.$axios.get('credit/f/info',{params:{
                        id: this.creditId
                    }}).then(function (response) {
                    console.log(response);
                    _than.dataInfo = response.data.extend;
                    _than.form.id = response.data.extend.creditApply.pId;
                }).catch(function (error) {
                    console.log(error);
                });
            },

            // 合同信息附件上传成功
            handleSuccess10(response,file, fileList) {
                this.contractFile = response.extend.fileSystem.filePath;
            },
            handleSuccess11(response,file, fileList) {
                this.contractPaymentFile = response.extend.fileSystem.filePath;
            },
            handleSuccess12(response,file, fileList) {
                this.inContractLiabilityBook = response.extend.fileSystem.filePath;
            },
            handleSuccess13(response,file, fileList) {
                this.constructionSitePhoto = response.extend.fileSystem.filePath;
            },
            // 成本附件上传成功回调函数
            handleSuccess20(response,file, fileList) {
                this.progressReturnAccount = response.extend.fileSystem.filePath;
            },
            handleSuccess21(response,file, fileList) {
                this.outputValueTable = response.extend.fileSystem.filePath;
            },
            handleSuccess22(response,file, fileList) {
                this.costAnalysisTable = response.extend.fileSystem.filePath;
            },
            handleSuccess23(response,file, fileList) {
                this.costAccount = response.extend.fileSystem.filePath;
            },
            // 款项附件上传成功回调函数
            handleSuccess30(response,file, fileList) {
                this.invoiceFile = response.extend.fileSystem.filePath;
            },
            handleSuccess31(response,file, fileList) {
                this.transferFile = response.extend.fileSystem.filePath;
            },
            handleSuccess32(response,file, fileList) {
                this.transferToInvoiceFile = response.extend.fileSystem.filePath;
            },
            handleSuccess33(response,file, fileList) {
                this.buyContractFile = response.extend.fileSystem.filePath;
            },
            handleSuccess34(response,file, fileList) {
                this.buyInvoiceFile = response.extend.fileSystem.filePath;
            },
            handleSuccess35(response,file, fileList) {
                this.deliveryNote = response.extend.fileSystem.filePath;
            },
            handleSuccess36(response,file, fileList) {
                this.banckTransfer = response.extend.fileSystem.filePath;
            },
            // 保单附件
            handleSuccess40(response,file, fileList) {
                this.insurFile = response.extend.fileSystem.filePath;
            },


            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            onReturn() {
                this.$router.push("ec-credit-apply-list")
            },
            // 上一步
            oneStep(){
               this.isStep1 = true;
               this.isStep2 = false;
               this.isStep3 = false;
               this.isStep4 = false;
            },
            // 下一步
            secondStep(){
                this.isStep1 = false;
                this.isStep2 = true;
                this.isStep3 = false;
                this.isStep4 = false;
            },
            // 下一步
            threeStep(){
                this.isStep1 = false;
                this.isStep2 = false;
                this.isStep3 = true;
                this.isStep4 = false;
            },
            // 下一步
            fourStep(){
                this.isStep1 = false;
                this.isStep2 = false;
                this.isStep3 = false;
                this.isStep4 = true;
            },
            save(){
                let _that = this;
                this.$axios.post('api/project/info/save',
                    this.qs.stringify(
                        {
                            id: this.form.id,
                            partyAName: this.form.partyAName,
                            projectAdress: this.form.projectAdress,
                            shouldClearAmount: this.form.shouldClearAmount,
                            guaranteeClaim: this.form.guaranteeClaim,
                            constructionStartDate: this.form.constructionStartDate,
                            constructionEndDate: this.form.constructionEndDate,
                            clearType: this.form.clearType,
                            shouldPaymentAmount: this.form.shouldPaymentAmount,
                            shouldPaymentDate: this.form.shouldPaymentDate,
                            progressPaymentType: this.form.progressPaymentType,
                            monthClearDay: this.form.monthClearDay,
                            progressPaymentRatio: this.form.progressPaymentRatio,
                            checkPaymentRatio: this.form.checkPaymentRatio,
                            checkPaymentDesc: this.form.checkPaymentDesc,
                            clearPaymentRatio: this.form.clearPaymentRatio,
                            clearDesc: this.form.clearDesc,
                            warrantyMoneyPaymentRatio: this.form.warrantyMoneyPaymentRatio,
                            warranty: this.form.warranty,
                            warrantyPayType: this.form.warrantyPayType,

                            contractFile: this.contractFile,
                            contractPaymentFile: this.contractPaymentFile,
                            inContractLiabilityBook: this.inContractLiabilityBook,
                            constructionSitePhoto: this.constructionSitePhoto,

                            profitMargin: this.form.profitMargin,
                            progressReturnAccount: this.progressReturnAccount,
                            outputValueTable: this.outputValueTable,
                            costAnalysisTable: this.costAnalysisTable,
                            costAccount: this.costAccount,

                            sumBillAmount: this.form.sumBillAmount,
                            sumPaybackAmount: this.form.sumPaybackAmount,
                            sumPaymentAmount: this.form.sumPaymentAmount,
                            invoiceFile: this.invoiceFile,
                            transferFile: this.transferFile,
                            transferToInvoiceFile: this.transferToInvoiceFile,
                            buyContractFile: this.buyContractFile,
                            buyInvoiceFile: this.buyInvoiceFile,
                            deliveryNote: this.deliveryNote,
                            banckTransfer: this.banckTransfer,

                            isBuyInsur: this.form.isBuyInsur,
                            insurEndDate: this.form.insurEndDate,
                            insurAmount: this.form.insurAmount,
                            insurPersonNumber: this.form.insurPersonNumber,
                            insurFile: this.insurFile,
                            unusualDesc: this.form.unusualDesc,
                        }
                    )).then(function (response) {
                    console.log(response);
                    _that.$router.push("ec-credit-apply-list")
                }).catch(function (error) {
                    console.log(error);
                });
                console.log(this.form);

            },
            sumbit(){
                this.$axios.put('credit/apply/update',
                    this.qs.stringify(
                        {   id:this.creditId,
                            creditId:this.creditId,
                            step: 2,
                            status: 0,
                        }
                    )).then(function (response) {
                    console.log(response);
                    _that.getCreditDataInfo();
                    _that.$message.success('提交成功！');
                    _that.pingguVisible = false;

                }).catch(function (error) {
                    console.log(error);
                });
            },
            findFile(bankListFile){

            }

        }
    }
</script>

<style>

    .disabled .el-upload--picture-card {
        display: none;
    }
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
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }

    .info-title{
        color: #cccccc;
        text-align: center;
        font-size: 18px;
        margin-bottom: 20px;
    }
    .apply-info-box {
        margin: 30px 0;
        height: 180px;
        border: 1px solid #ccc;
        color: #cccccc;
    }
    .project-info{
        padding-top: 20px;
        border-top: 1px dashed #cccccc;
    }
    .project-cost-info{
        padding-top: 20px;
        border-top: 1px dashed #cccccc;
    }
    .form-box{
        padding-left: 100px;
        width: 90%;
        color: #606266;
    }
    .input-width{
        width: 210px;
    }
    .btn-margin{
        margin: 40px 40px 50px 0;

    }
</style>
