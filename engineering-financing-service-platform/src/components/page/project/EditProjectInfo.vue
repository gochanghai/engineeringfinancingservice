<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span :class="newTitle != 1 ? 'project-title-nave': 'project-title-nave nave2'" @click="newTitle=1">项目基本信息</span>
                        <span :class="newTitle != 2 ? 'project-title-nave': 'project-title-nave nave2'" @click="newTitle=2">项目成本信息</span>
                        <span :class="newTitle != 3 ? 'project-title-nave': 'project-title-nave nave2'" @click="newTitle=3">项目款项信息</span>
                        <span :class="newTitle != 4 ? 'project-title-nave': 'project-title-nave nave2'" @click="newTitle=4">项目其它信息</span>
                        <span :class="newTitle != 5 ? 'project-title-nave': 'project-title-nave nave2'" @click="newTitle=5">项目重要文件</span>
                    </div>
                    <!-- 项目信息 -->
                    <div class="info-box">
                        <!--<div class="info-title">项目资料</div>-->
                        <div class="info-btn-box">
                            <!--<div class="is-show-btn">展开</div>-->
                            <div class="info-content">
                                <!-- 个人身份信息 -->
                                <!--<div class="content-title">-->
                                    <!--<div class="content-lable1">项目基本信息</div>-->
                                    <!--<div class="content-lable2">已认证</div>-->
                                <!--</div>-->
                                <div class="content-info-box" v-show="newTitle === 1">
                                    <el-form>
                                        <el-form-item label="甲方名称：" style="margin-bottom: 0">
                                            <el-input v-model="form.partyAName" style="width: 300px"/>
                                        </el-form-item>
                                        <el-form-item label="项目地址：" style="margin-bottom: 0">
                                            <el-input v-model="form.adddress" style="width: 300px"/>
                                        </el-form-item>
                                        <el-form-item label="合同金额：" style="margin-bottom: 0">
                                            {{form.contractAmount}}万
                                            <!--<el-input v-model="form.contractAmount" style="width: 300px"/>-->
                                        </el-form-item>
                                        <el-form-item label="预计结算金额：" style="margin-bottom: 0">
                                            <el-input v-model="form.shouldClearAmount" style="width: 300px"/>
                                        </el-form-item>
                                        <el-form-item label="保函要求：" style="margin-bottom: 0">
                                            <el-input v-model="form.guaranteeClaim" style="width: 300px"/>
                                        </el-form-item>
                                        <el-form-item label="施工期限：" style="margin-bottom: 0">
                                            <el-date-picker
                                                    v-model="startEndDate"
                                                    value-format="yyyy-MM-dd"
                                                    type="daterange"
                                                    range-separator="至"
                                                    start-placeholder="开始日期"
                                                    end-placeholder="结束日期">
                                            </el-date-picker>
                                        </el-form-item>
                                        <el-form-item label="项目施工进度：" style="margin-bottom: 0">
                                            {{form.projectProgress}}%
                                            <!--<el-input v-model="form.projectProgress" style="width: 300px"/>-->
                                        </el-form-item>
                                        <el-form-item label="项目结算方式：" style="margin-bottom: 0">
                                            <el-input v-model="form.clearType" style="width: 300px"/>
                                        </el-form-item>
                                        <el-form-item label="项目付款方式" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item style="width: 900px; margin: 0 30px">
                                            <!-- 第一列 -->
                                            <el-form style="float: left; width: 250px">
                                                <el-form-item label="预付款：" style="margin-bottom: 0">
                                                    <el-input v-model="form.shouldPaymentAmount" style="width: 100px"/>万
                                                </el-form-item>
                                                <el-form-item label="进度款：" style="margin-bottom: 0">
                                                    <el-select v-model="form.progressPaymentType" placeholder="请选择" style="width: 100px">
                                                        <el-option label="月度支付" value="月度支付"></el-option>
                                                        <el-option label="季度支付" value="季度支付"></el-option>
                                                        <el-option label="一次性支付" value="一次性支付"></el-option>
                                                        <el-option label="其它" value="其它"></el-option>
                                                    </el-select>
                                                </el-form-item>
                                                <el-form-item label="验收款付款比例至：" style="margin-bottom: 0">
                                                    <el-input v-model="form.checkPaymentRatio" style="width: 80px"/>%
                                                </el-form-item>
                                                <el-form-item label="结算款付款比例至：" style="margin-bottom: 0">
                                                    <el-input v-model="form.clearPaymentRatio" style="width: 80px"/>%
                                                </el-form-item>
                                                <el-form-item label="质保金质保金比例：" style="margin-bottom: 0">
                                                    <el-input v-model="form.warrantyMoneyPaymentRatio" style="width: 80px"/>%
                                                </el-form-item>
                                            </el-form>
                                            <!-- 第二列 -->
                                            <el-form style="float: left; width: 300px">
                                                <el-form-item label="预付款支付日期：" style="margin-bottom: 0">
                                                    <el-date-picker v-model="form.shouldPaymentDate"
                                                                    type="date"
                                                                    value-format="yyyy-MM-dd"
                                                                    placeholder="选择日期"
                                                    style="width: 120px"/>
                                                </el-form-item>
                                                <el-form-item label="月结算日：" style="margin-bottom: 0">
                                                    <el-input v-model="form.monthClearDay" style="width: 80px"/>
                                                </el-form-item>
                                                <el-form-item label="验收款备注：" style="margin-bottom: 0">
                                                    <el-input v-model="form.checkPaymentDesc" style="width: 100px"/>
                                                </el-form-item>
                                                <el-form-item label="结算款备注：" style="margin-bottom: 0">
                                                    <el-input v-model="form.clearDesc" style="width: 100px"/>
                                                </el-form-item>
                                                <el-form-item label="质保期：" style="margin-bottom: 0">
                                                    <el-input v-model="form.warranty" style="width: 80px"/>
                                                </el-form-item>
                                            </el-form>
                                            <!-- 第三列 -->
                                            <el-form style="float: left; width: 300px">
                                                <el-form-item label="质保期支付方式：" style="margin-bottom: 0">
                                                    <el-input v-model="form.warrantyPayType" style="width: 100px"/>
                                                </el-form-item>
                                                <el-form-item label="进度付款比例至：" style="margin-bottom: 0">
                                                    <el-input v-model="form.progressPaymentRatio" style="width: 80px"/>%
                                                </el-form-item>
                                            </el-form>
                                        </el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <div @mouseenter="beforUpload(1)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.contract.contractFile" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="contractFile !== null" class="avatar" :src=" filesystem + contractFile">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>项目合同</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(2)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.contract.contractPaymentFile" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="contractPaymentFile !== null" class="avatar" :src=" filesystem + contractPaymentFile">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>合同付款方式页</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(3)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.contract.inContractLiabilityBook" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="inContractLiabilityBook !== null" class="avatar" :src=" filesystem + inContractLiabilityBook">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>经营承包责任书</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(4)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.contract.constructionSitePhoto" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="constructionSitePhoto !== null" class="avatar" :src=" filesystem + constructionSitePhoto">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>施工现场照片</span>
                                                    </el-card>
                                                </div>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 项目成本信息 -->
                                <!--<div class="content-title">-->
                                    <!--<div class="content-lable1">项目成本信息</div>-->
                                    <!--<div class="content-lable2">已婚</div>-->
                                <!--</div>-->
                                <div class="content-info-box" v-show="newTitle === 2">
                                    <el-form>
                                        <el-form-item label="项目利润率：" style="margin-bottom: 0">
                                            <el-input v-model="form.profitMargin" style="width: 100px"/>%
                                        </el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <div @mouseenter="beforUpload(5)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.cost.progressReturnAccount" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="progressReturnAccount !== null" class="avatar" :src=" filesystem + progressReturnAccount">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>工程进度回款台账</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(6)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.cost.outputValueTable" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="outputValueTable !== null" class="avatar" :src=" filesystem + outputValueTable">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>项目产值表</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(7)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.cost.costAnalysisTable" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="costAnalysisTable !== null" class="avatar" :src=" filesystem + costAnalysisTable">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>项目成本分析表</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(8)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.cost.costAccount" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="costAccount !== null" class="avatar" :src=" filesystem + costAccount">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>项目成本台账</span>
                                                    </el-card>
                                                </div>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 项目款项信息 -->
                                <!--<div class="content-title">-->
                                    <!--<div class="content-lable1">项目款项信息</div>-->
                                    <!--<div class="content-lable2">已认证</div>-->
                                <!--</div>-->
                                <div class="content-info-box" v-show="newTitle === 3">
                                    <el-form>
                                        <el-form-item label="前期累计开票额：" style="margin-bottom: 0">
                                            <el-input v-model="form.totalBillAmount" style="width: 100px"></el-input>万
                                        </el-form-item>
                                        <el-form-item>
                                            <div class="file-box" @mouseenter="beforUpload(9)">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <!--<img :src="filesystem + projectInfo.payment.invoiceFile" class="image">-->
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="uploadFileSuccess"
                                                            :show-file-list="false">
                                                        <img v-if="invoiceFile !== null" class="avatar" :src=" filesystem + invoiceFile">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>发票凭证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="前期累计回款额：" style="margin-bottom: 0">
                                            <el-input v-model="form.totalPaybackAmount" style="width: 100px"/>万
                                        </el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <div @mouseenter="beforUpload(10)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.payment.transferFile" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="transferFile !== null" class="avatar" :src=" filesystem + transferFile">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>转账凭证</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(11)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.payment.transferToInvoiceFile" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="transferToInvoiceFile !== null" class="avatar" :src=" filesystem + transferToInvoiceFile">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>对应发票凭证</span>
                                                    </el-card>
                                                </div>

                                            </div>
                                        </el-form-item>
                                        <el-form-item label="前期累计付款额：" style="margin-bottom: 0">
                                            <el-input v-model="form.totalPaymentAmount" style="width: 80px"/>万
                                        </el-form-item>
                                        <el-form-item>
                                            <div class="file-box">
                                                <div @mouseenter="beforUpload(12)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.payment.buyContractFile" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="buyContractFile !== null" class="avatar" :src=" filesystem + buyContractFile">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>采购合同</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(13)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.payment.buyInvoiceFile" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="buyInvoiceFile !== null" class="avatar" :src=" filesystem + buyInvoiceFile">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>采购发票</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(14)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.payment.deliveryNote" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="deliveryNote !== null" class="avatar" :src=" filesystem + deliveryNote">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>送货单</span>
                                                    </el-card>
                                                </div>
                                                <div @mouseenter="beforUpload(15)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.payment.banckTransfer" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="banckTransfer !== null" class="avatar" :src=" filesystem + banckTransfer">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>银行转账凭证</span>
                                                    </el-card>
                                                </div>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 其它信息 -->
                                <!--<div class="content-title">-->
                                    <!--<div class="content-lable1">其它信息</div>-->
                                    <!--<div class="content-lable2">已认证</div>-->
                                <!--</div>-->
                                <div class="content-info-box" v-show="newTitle === 4">
                                    <el-form>
                                        <el-form-item label="是否购买意外险：" style="margin-bottom: 0">
                                            <el-radio-group v-model="form.isInsur">
                                                <el-radio label="1">是</el-radio>
                                                <el-radio label="0">否</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                        <div v-show="form.isInsur === '1'">
                                            <el-form-item label="" style="margin-bottom: 0">
                                                <el-form inline style="border-bottom: 0">
                                                    <el-form-item label="保险到期日" style="margin-bottom: 0">
                                                        <el-date-picker type="date" placeholder="选择日期"
                                                                        value-format="yyyy-MM-dd"
                                                                        v-model="form.insurEndDate"
                                                                        style="width: 200px;"/>
                                                    </el-form-item>
                                                    <el-form-item label="保额" style="margin-bottom: 0">
                                                        <el-input v-model="form.insurAmount" style="width: 100px"/>万
                                                    </el-form-item>
                                                    <el-form-item label="参保人数" style="margin-bottom: 0">
                                                        <el-input v-model="form.insurPersonNumber" style="width: 80px"/>人
                                                    </el-form-item>
                                                </el-form>
                                            </el-form-item>
                                            <el-form-item label="保单凭证：">
                                                <div class="file-box" @mouseenter="beforUpload(16)">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <!--<img :src="filesystem + projectInfo.other.insurFile" class="image">-->
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="uploadFileSuccess"
                                                                :show-file-list="false">
                                                            <img v-if="insurFile !== null" class="avatar" :src=" filesystem + insurFile">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <!--<span>房产证明</span>-->
                                                    </el-card>
                                                </div>
                                            </el-form-item>
                                            <el-form-item label="特殊事项（选填）：" style="margin-bottom: 0">
                                                <el-input type="textarea" v-model="form.unusualDesc" style="width: 400px"/>
                                            </el-form-item>
                                        </div>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1" @click="goBack">返回</div>
                        <div class="info-bottom-btn2" @click="save">保存</div>
                        <!--<div class="info-bottom-btn2" @click="sumbit">提交</div>-->
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'edit-project-info',
        data() {
            return {
                newTitle: 1,
                name: localStorage.getItem('ms_username'),
                uploadPath: localStorage.getItem("uploadPath"),
                filesystem: localStorage.getItem("fileBasePath"),
                projectId: this.$route.query.id,
                projectInfo: null,
                startEndDate:'',
                thisUploadType: 1,
                form:{
                    name: '123',
                    id: 1,
                    contractAmount: 0.00,
                    projectProgress: 0.00,
                    partyAName:null,
                    adddress: null,
                    shouldClearAmount: null,
                    guaranteeClaim: null,
                    startDate: null,
                    endDate: null,
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

                    totalBillAmount: null,
                    totalPaybackAmount: null,
                    totalPaymentAmount: null,

                    isInsur: '1',
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
            }
        },
        /**
         * 监听器
         */
        watch:{
            newTitle(value){
                if(value === 1){
                    this.getProjectContract();
                    return;
                }
                if(value === 2){
                    this.getProjectCost();
                    return;
                }
                if(value === 3){
                    this.getProjectPayment();
                    return;
                }
                if(value === 4){
                    this.getProjectOther();
                }
            },
            startEndDate(value){
                this.form.startDate = value[0];
                this.form.endDate = value[1];
            }
        },
        components: {
        },
        computed: {
        },
        created(){
            this.getProjectContract();
        },
        activated(){
        },
        deactivated(){
        },
        methods: {

            // 附件上传前
            beforUpload(file_type){
                console.log("fileType " + file_type);
                this.thisUploadType = file_type;
            },
            // 附件上传成功时
            uploadFileSuccess(response,file,files){
                // console.log(response);
                let file_path = response.extend.fileSystem.filePath;
                switch (this.thisUploadType) {
                    case 1:
                        this.contractFile = file_path;
                        break;
                    case 2:
                        this.contractPaymentFile = file_path;
                        break;
                    case 3:
                        this.inContractLiabilityBook = file_path;
                        break;
                    case 4:
                        this.constructionSitePhoto = file_path;
                        break;
                    case 5:
                        this.progressReturnAccount = file_path;
                        break;
                    case 6:
                        this.outputValueTable = file_path;
                        break;
                    case 7:
                        this.costAnalysisTable = file_path;
                        break;
                    case 8:
                        this.costAccount = file_path;
                        break;
                    case 9:
                        this.invoiceFile = file_path;
                        break;
                    case 10:
                        this.transferFile = file_path;
                        break;
                    case 11:
                        this.transferToInvoiceFile = file_path;
                        break;
                    case 12:
                        this.buyContractFile = file_path;
                        break;
                    case 13:
                        this.buyInvoiceFile = file_path;
                        break;
                    case 14:
                        this.deliveryNote = file_path;
                        break;
                    case 15:
                        this.banckTransfer = file_path;
                        break;
                    case 16:
                        this.insurFile = file_path;
                        break;
                    default:
                        this.thisUploadType = 0;
                        break;
                }
            },

            /**
             * 返回按钮
             */
            goBack(){
                // 返回上一步
                this.$router.go(-1);
            },

            // 保存
            save1(){
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
            // 提交
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

            /**
             * 获取合同信息
             */
            getProjectContract(){
                let _than = this;
                this.$axios.get('api/project/contract',{params:{
                        projectId: this.projectId
                    }}).then(function (res) {
                    console.log(res);
                    var contract = res.data.extend.contract;
                    _than.startEndDate = [contract.startDate,contract.endDate]
                    _than.form = contract;

                }).catch(function (error) {
                    console.log(error);
                });
            },
            /**
             * 获取成本信息
             */
            getProjectCost(){
                let _than = this;
                this.$axios.get('api/project/cost',{params:{
                        projectId: this.projectId
                    }}).then(function (res) {
                    console.log(res);
                    var cost = res.data.extend.cost;
                    _than.form = cost;

                }).catch(function (error) {
                    console.log(error);
                });
            },
            /**
             * 获取款项信息
             */
            getProjectPayment(){
                let _than = this;
                this.$axios.get('api/project/payment',{params:{
                        projectId: this.projectId
                    }}).then(function (res) {
                    console.log(res);
                    var payment = res.data.extend.payment;
                    _than.form = payment;

                }).catch(function (error) {
                    console.log(error);
                });
            },
            /**
             * 获取其它信息
             */
            getProjectOther(){
                let _than = this;
                this.$axios.get('api/project/other',{params:{
                        projectId: this.projectId
                    }}).then(function (res) {
                    console.log(res);
                    var other = res.data.extend.other;
                    _than.form = other;

                }).catch(function (error) {
                    console.log(error);
                });
            },

            /**
             * 保存按钮处理函数
             */
            save(){
                var tag = this.newTitle;
                // 保存合同信息
                if(tag === 1){
                    this.saveContractInfo();
                }
                // 保存成信息
                if(tag === 2){
                    this.saveCostInfo();
                }
                // 保存款项信息
                if(tag === 3){
                    this.savePaymentInfo();
                }
                // 保存其它信息
                if(tag === 4){
                    this.saveOtherInfo();
                }
            },

            /**
             * 保存合同信息
             */
            saveContractInfo(){
                this.$axios.put('api/project/contract',
                    this.qs.stringify(
                        {   id:this.form.id,
                            projectId:this.projectId,
                            partyAName: this.form.partyAName,
                            address: this.form.address,
                            shouldClearAmount: this.form.shouldClearAmount,
                            guaranteeClaim: this.form.guaranteeClaim,
                            startDate: this.form.startDate,
                            endDate: this.form.endDate,
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
                        }
                    )).then(function (res){
                    console.log(res);
                    _that.$message.success('提交成功！');
                    _that.pingguVisible = false;

                }).catch(function (error) {
                    console.log(error);
                });
            },

            /**
             * 保存成本信息
             */
            saveCostInfo(){
                this.$axios.post('api/project/cost',
                    this.qs.stringify(
                        {   id:this.id,
                            projectId:this.projectId,
                            profitMargin: this.form.profitMargin,
                            progressReturnAccount: this.progressReturnAccount,
                            outputValueTable: this.outputValueTable,
                            costAnalysisTable: this.costAnalysisTable,
                            costAccount: this.costAccount,
                        }
                    )).then(function (res){
                    console.log(res);
                    _that.$message.success('提交成功！');
                    _that.pingguVisible = false;

                }).catch(function (error) {
                    console.log(error);
                });
            },

            /**
             * 保存款项信息
             */
            savePaymentInfo(){
                this.$axios.post('api/project/payment',
                    this.qs.stringify(
                        {   id:this.form.id,
                            projectId:this.projectId,
                            totalBillAmount: this.form.totalBillAmount,
                            totalPaybackAmount: this.form.totalPaybackAmount,
                            totalPaymentAmount: this.form.totalPaymentAmount,
                            invoice: this.invoiceFile,
                            transfer: this.transferFile,
                            transferInvoice: this.transferToInvoiceFile,
                            purchaseContract: this.buyContractFile,
                            purchaseInvoice: this.buyInvoiceFile,
                            deliveryNote: this.deliveryNote,
                            banckTransfer: this.banckTransfer,
                        }
                    )).then(function (res){
                    console.log(res);
                    _that.$message.success('提交成功！');
                    _that.pingguVisible = false;

                }).catch(function (error) {
                    console.log(error);
                });
            },

            /**
             * 保存其它信息
             */
            saveOtherInfo(){
                this.$axios.post('api/project/other',
                    this.qs.stringify(
                        {   id:this.form.id,
                            projectId:this.projectId,
                            isInsur: this.form.isBuyInsur,
                            insurEndDate: this.form.insurEndDate,
                            insurAmount: this.form.insurAmount,
                            insurPersonNumber: this.form.insurPersonNumber,
                            insurFile: this.insurFile,
                            unusualDesc: this.form.unusualDesc,
                        }
                    )).then(function (res){
                    console.log(res);
                    _that.$message.success('提交成功！');
                    _that.pingguVisible = false;

                }).catch(function (error) {
                    console.log(error);
                });
            }
        }
    }

</script>

<style>
    .project-title-nave {
        height:19px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        /*color:rgba(255,130,8,1);*/
        width: 80px;
        line-height:19px;
        text-align: center;
        /*border: 1px solid rgba(255,130,8,1);*/
        padding: 18px 10px;
        /*margin: 0 10px;*/
    }

    .project-title-nave:hover {
        color:rgba(255,130,8,1);
        font-weight:bold;
        border-bottom: 2px solid rgba(255,130,8,1);
    }

    .nave2 {
        /*padding: 18px 10px;*/
        /*margin: 0 10px;*/
        color:rgba(255,130,8,1);
        font-weight:bold;
        border-bottom: 2px solid rgba(255,130,8,1);
    }
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
        /*width: 60px;*/
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
        margin-right: 20px;
    }
    .image {
        width: 100%;
        height: 120px;
        display: block;
    }


    .info-bottom-box{
        position: relative;
        /*width: 260px;*/
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
