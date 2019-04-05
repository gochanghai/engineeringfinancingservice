<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>身份认证</span>
                    </div>
                    <!-- 认证步骤 -->
                    <div class="info-box">
                        <div class="info-title">
                            <div class="steps-box" style="width: 600px">
                                <!--<div class="triangle"></div>-->
                                <ul class="step">
                                    <li class="active">
                                        绑定银行卡
                                        <div class="triangle"></div>
                                    </li>
                                    <li :class="{active: step>= 2 }">
                                        上传认证资料
                                        <div class="triangle"></div>
                                    </li>
                                    <li :class="{active: step === 5}">
                                        完成
                                        <div class="triangle"></div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="info-btn-box">
                            <!--<div class="is-show-btn">展开</div>-->
                            <div class="info-content">
                                <!-- 绑定银行卡 -->
                                <div class="content-title" v-show="step === 1 || step === 5">
                                    <div class="content-lable1">绑定银行卡</div>
                                    <!--<div class="content-lable2">查看示例</div>-->
                                </div>
                                <div class="content-info-box" v-show="step === 1 || step === 5">
                                    <el-form ref="form" :model="form" :rules="rules1" label-width="100px">
                                        <el-form-item label="姓名：">
                                            <el-input v-model="form.name" disabled style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="身份证号：">
                                            <el-input v-model="form.idCard" disabled style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="银行卡号：" prop="bankCardNo">
                                            <el-input v-model="cardNo" placeholder="请输入银行卡号" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="开户行：">
                                            <!-- bank logo -->
                                            <div style="width: 400px; height: 32px">
                                                <img v-if="bankCode != null" :src="'https://apimg.alipay.com/combo.png?d=cashier&t=' + bankCode"/>
                                                <el-input v-else v-model="form.bank"/>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="手机号：" prop="bankCardPhone">
                                            <el-input v-model="form.bankCardPhone" placeholder="请输入银行预留的手机号" style="width: 400px">
                                                <!-- <template slot="append">
                                                    <div style="background-color: #fff" @click="sendMessage">{{btnText}}</div>
                                                </template> -->
                                            </el-input>
                                        </el-form-item>
                                        <!-- <el-form-item label="验证码：">
                                            <el-input v-model="form.code" style="width: 200px"/>
                                        </el-form-item> -->
                                    </el-form>
                                </div>
                                <!-- 个人身份信息 -->
                                <div class="content-title" v-show="step === 2 || step === 5">
                                    <div class="content-lable1">身份信息</div>
                                    <div class="content-lable2">查看示例</div>
                                </div>
                                <div class="content-info-box" v-show="step === 2 || step === 5">
                                    <el-form label-width="100px">
                                        <el-form-item label="户籍所在地：" style="margin-bottom: 0">
                                            <el-input v-model="form.domicile" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="idCardSideFace"
                                                            :show-file-list="false">
                                                        <img v-if="form.idCardSideFace !== null" class="avatar" :src=" filesystem + form.idCardSideFace">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="idCardSideBack"
                                                            :show-file-list="false">
                                                        <img v-if="form.idCardSideBack !== null" class="avatar" :src=" filesystem + form.idCardSideBack">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="idCardHand"
                                                            :show-file-list="false">
                                                        <img v-if="form.idCardHand !== null" class="avatar" :src=" filesystem + form.idCardHand">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传手持身份证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="householdRegHome"
                                                            :show-file-list="false">
                                                        <img v-if="form.householdRegHome !== null" class="avatar" :src=" filesystem + form.householdRegHome">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="householdRegPersonal"
                                                            :show-file-list="false">
                                                        <img v-if="form.householdRegPersonal !== null" class="avatar" :src=" filesystem + form.householdRegPersonal">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本个人页</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 婚姻信息 -->
                                <div class="content-title" v-show="step === 2 || step === 5">
                                    <div class="content-lable1">婚姻状况</div>
                                    <!--<div class="content-lable2">已婚</div>-->
                                </div>
                                <div class="content-info-box" v-show="step === 2 || step === 5">
                                    <el-form label-width="130px">
                                        <el-form-item label="婚姻状况：">
                                            <el-radio-group v-model="form.marriageStatus">
                                                <el-radio :label="1">已婚</el-radio>
                                                <el-radio :label="0">未婚</el-radio>
                                                <el-radio :label="-1">离异</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                        <el-form-item label="配偶姓名：" v-show="form.marriageStatus === 1">
                                            <el-input v-model="form.spouseName" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="配偶身份证号：" v-show="form.marriageStatus === 1">
                                            <el-input v-model="form.spouseIdCard" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="配偶户籍所在地：" v-show="form.marriageStatus === 1">
                                            <el-input v-model="form.spouseDomicile" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="上传附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file" v-show="form.marriageStatus === 1">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="spouseIdCardSideFace"
                                                            :show-file-list="false">
                                                        <img v-if="form.spouseIdCardSideFace !== null" class="avatar" :src=" filesystem + form.spouseIdCardSideFace">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file" v-show="form.marriageStatus === 1">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="spouseIdCardSideBack"
                                                            :show-file-list="false">
                                                        <img v-if="form.spouseIdCardSideBack !== null" class="avatar" :src=" filesystem + form.spouseIdCardSideBack">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file" v-show="form.marriageStatus === 1">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="marriageCertificate"
                                                            :show-file-list="false">
                                                        <img v-if="form.marriageCertificate !== null" class="avatar" :src=" filesystem + form.marriageCertificate">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传结婚证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file" v-show="form.marriageStatus === 1">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="sHouseholdRegHome"
                                                            :show-file-list="false">
                                                        <img v-if="form.sHouseholdRegHome !== null" class="avatar" :src=" filesystem + form.sHouseholdRegHome">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file" v-show="form.marriageStatus === 1">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="sHouseholdRegPersonal"
                                                            :show-file-list="false">
                                                        <img v-if="form.sHouseholdRegPersonal !== null" class="avatar" :src=" filesystem + form.sHouseholdRegPersonal">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本个人页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file" v-show="form.marriageStatus === -1">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="divorceCertificate"
                                                            :show-file-list="false">
                                                        <img v-if="form.divorceCertificate !== null" class="avatar" :src=" filesystem + form.divorceCertificate">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传离婚证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 企业信息 -->
                                <div class="content-title" v-show="step === 3 || step === 5">
                                    <div class="content-lable1">企业信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box" v-show="step === 3 || step === 5">
                                    <el-form label-width="140px">
                                        <el-form-item label="名下是否有公司：">
                                            <el-radio-group v-model="form.isCompany">
                                                <el-radio :label="1">是</el-radio>
                                                <el-radio :label="0">否</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                        <div v-show="form.isCompany === 1">
                                            <el-form-item label="公司全称：" >
                                                <el-input v-model="form.companyName" style="width: 400px" />
                                            </el-form-item>
                                            <el-form-item label="信用代码证编号：" >
                                                <el-input v-model="form.creditCode" style="width: 400px"/>
                                            </el-form-item>
                                            <el-form-item label="上传附件：" style="margin-bottom: 0">
                                                <div class="file-box">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="companyBusinessLicense"
                                                                :show-file-list="false">
                                                            <img v-if="form.companyBusinessLicense !== null" class="avatar" :src=" filesystem + form.companyBusinessLicense">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>公司营业执照</span>
                                                    </el-card>
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="articlesAssoCompany"
                                                                :show-file-list="false">
                                                            <img v-if="form.articlesAssoCompany !== null" class="avatar" :src=" filesystem + form.articlesAssoCompany">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>公司章程</span>
                                                    </el-card>
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="officeSpaceLeaseContract"
                                                                :show-file-list="false">
                                                            <img v-if="form.officeSpaceLeaseContract !== null" class="avatar" :src=" filesystem + form.officeSpaceLeaseContract">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>办公场所租赁合同</span>
                                                    </el-card>
                                                </div>
                                            </el-form-item>
                                        </div>
                                    </el-form>
                                </div>
                                <!-- 社保信息 -->
                                <div class="content-title" v-show="step === 3 || step === 5">
                                    <div class="content-lable1">社保信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box" v-show="step === 3 || step === 5">
                                    <el-form label-width="140px">
                                        <el-form-item label="是否购买社保：">
                                            <el-radio-group v-model="form.isSocialecurity">
                                                <el-radio :label="1">是</el-radio>
                                                <el-radio :label="0">否</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                        <div v-show="form.isSocialecurity === 1">
                                            <el-form-item label="社保电脑号：" >
                                                <el-input v-model="form.socialInsurCardNo" style="width: 400px" />
                                            </el-form-item>
                                            <el-form-item label="当前购买社保公司：" >
                                                <el-input v-model="form.buySocialInsurCompany" style="width: 400px"/>
                                            </el-form-item>
                                            <el-form-item label="最近缴纳社保清单：" style="margin-bottom: 0">
                                                <div class="file-box">
                                                    <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                        <el-upload
                                                                class="avatar-uploader"
                                                                name="file"
                                                                :action="uploadPath"
                                                                :on-success="paySocialInsurDetails"
                                                                :show-file-list="false">
                                                            <img v-if="form.paySocialInsurDetails !== null" class="avatar" :src=" filesystem + form.paySocialInsurDetails">
                                                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                        </el-upload>
                                                        <span>上传社保清单</span>
                                                    </el-card>
                                                </div>
                                            </el-form-item>
                                        </div>
                                    </el-form>
                                </div>
                                <!-- 个人资产 -->
                                <div class="content-title" v-show="step === 4 || step === 5">
                                    <div class="content-lable1">个人资产</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <!-- 房产信息 -->
                                <div class="content-info-box" v-show="step === 4 || step === 5">
                                    <el-form label-width="100px">
                                        <el-form-item label="是否有房产">
                                            <el-radio-group v-model="form.isHouse">
                                                <el-radio :label="1">是</el-radio>
                                                <el-radio :label="0">否</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                        <div v-show="form.isHouse === 1">
                                            <div v-for="(house,index) in form.houses" :key="index">
                                                <el-form-item :label="'房产' + (index + 1)" style="margin-bottom: 0">
                                                    <el-button type="info" size="mini" @click="removeHouse(index)">移除房产{{index+1}}</el-button>
                                                </el-form-item>
                                                <el-form-item label="房产所在地：" >
                                                    <el-input v-model="house.houseAddress" style="width: 400px"/>
                                                </el-form-item>
                                                <el-form-item label="房产证明：">
                                                    <div class="file-box" @mouseenter="houseIndex(index)">
                                                        <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                            <el-upload
                                                                    class="avatar-uploader"
                                                                    name="file"
                                                                    :action="uploadPath"
                                                                    :on-success="premisesPermit"
                                                                    :show-file-list="false">
                                                                <img v-if="house.premisesPermit !== null" class="avatar" :src=" filesystem + house.premisesPermit">
                                                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                            </el-upload>
                                                            <span>上传房产证明</span>
                                                        </el-card>
                                                    </div>
                                                </el-form-item>
                                                <el-form-item label="房产说明：" style="margin-bottom: 0">
                                                    <el-input v-model="house.houseDesc" style="width: 400px"/>
                                                </el-form-item>
                                            </div>
                                            <el-form-item>
                                                <el-button v-if="form.houses.length<2" type="warning" plain @click="addHouses">+ 添加更多房产信息</el-button>
                                            </el-form-item>
                                        </div>
                                    </el-form>
                                </div>
                                <!-- 车辆信息 -->
                                <div class="content-info-box" v-show="step === 4 || step === 5">
                                    <el-form label-width="100px">
                                        <el-form-item label="是否有车">
                                            <el-radio-group v-model="form.isCar">
                                                <el-radio :label="1">是</el-radio>
                                                <el-radio :label="0">否</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                        <div v-show="form.isCar">
                                            <div v-for="(car, index) in form.cars" :key="index">
                                                <el-form-item :label="'车辆'+ (index+1)" style="margin-bottom: 0">
                                                    <el-button type="info" size="mini" @click="removeCar(index)">移除车辆{{index+1}}</el-button>
                                                </el-form-item>
                                                <el-form-item label="车牌号：" >
                                                    <el-input v-model="car.carNumber" style="width: 200px"/>
                                                </el-form-item>
                                                <el-form-item label="车辆行驶证：">
                                                    <div class="file-box" @mouseenter="carIndex(index)">
                                                        <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                            <el-upload
                                                                    class="avatar-uploader"
                                                                    name="file"
                                                                    :action="uploadPath"
                                                                    :on-success="carDriveLicense"
                                                                    :show-file-list="false">
                                                                <img v-if="car.carDriveLicense !== null" class="avatar" :src=" filesystem + car.carDriveLicense">
                                                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                            </el-upload>
                                                            <span>上传车辆行驶证</span>
                                                        </el-card>
                                                    </div>
                                                </el-form-item>
                                                <el-form-item label="车辆说明：" style="margin-bottom: 0">
                                                    <el-input v-model="car.carDesc" style="width: 400px"/>
                                                </el-form-item>
                                            </div>
                                            <el-form-item>
                                                <el-button v-if="form.cars.length<2" type="warning" plain @click="addCars">+ 添加更多车辆信息</el-button>
                                            </el-form-item>
                                        </div>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1" v-show="step === 1">返回</div>
                        <div class="info-bottom-btn2" v-show="step > 2 && step < 5" @click="step -= 1">上一步</div>
                        <!-- <div class="info-bottom-btn2" v-show="step = 1" @click="saveBankCard">下一步</div> -->
                        <div class="info-bottom-btn2" v-show="step < 4" @click="step += 1">下一步</div>
                        <div class="info-bottom-btn2" v-show="step == 4" @click="save">完成</div>
                        <div class="info-bottom-btn2" v-show="step == 5" @click="goHome">进入首页</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    const Bank = require('../../common/util/bank.js');
    export default {
        name: 'business-manager-authen',
        data() {
            var checkBankCardNo = (rule, value, callback) => {
                console.log("checkOutputValue: check");
                if (!value) {
                    return callback(new Error('银行卡号不能为空'));
                }
                // 正则表达式校验
                var reg = /^(\d{16}|\d{19})$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的银行卡号'));
                    } else {
                        let _than = this;
                        // aliyun 银行卡号自动识别
                        this.$axios.get('https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8' +
                            '&cardNo='+this.form.bankCardNo+'&cardBinCheck=true').then( res => {
                            _than.bankCheck = res.data;
                            _than.bankCode = res.data.bank;
                            _than.form.bank = Bank.getBankName(res.data.bank);
                        }).catch(function (error) {
                            console.log(error);
                        });
                        callback();
                    }
                }, 1000);
            };
            var checkPhone = (rule, value, callback) => {
                console.log("checkOutputValue: check");
                if (!value) {
                    return callback(new Error('手机号不能为空'));
                }
                // 正则表达式校验
                var reg = /^1[3|4|5|7|8]\d{9}$/;
                console.log("reg: " + reg.test(value));
                setTimeout(() => {
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的手机号'));
                    } else {

                        if (value < 0) {
                            callback(new Error('产值只能0<= 产值'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };
            return {
                labelPosition: "right",
                userInfo: null,
                uploadPath: localStorage.getItem("uploadPath"),
                filesystem: localStorage.getItem("fileBasePath"),
                step: 1,
                bankCheck: null,
                bankCode: null, // 银行代码
                cardNo: '', // 银行卡号
                cardType: '',
                bankCardInfo: '',
                vercode:'',
                btnDisabled:false,
                btnText:'获取验证码',

                form: {
                    userId: localStorage.getItem('userId'),
                    name:'',
                    idCard: null,
                    bankCardNo: null,
                    bank: null,
                    bankCardPhone: null,
                    code: null,
                    domicile: null,
                    idCardSideFace: null,
                    idCardSideBack: null,
                    idCardHand: null,
                    householdRegHome: null,
                    householdRegPersonal: null,
                    marriageStatus: 1,
                    spouseName: null,
                    spouseIdCard: null,
                    spouseDomicile: null,
                    spouseIdCardSideFace: null,
                    spouseIdCardSideBack: null,
                    marriageCertificate: null,
                    sHouseholdRegHome: null,
                    sHouseholdRegPersonal: null,
                    divorceCertificate: null,
                    companyName: null,
                    creditCode: null,
                    companyBusinessLicense: null,
                    articlesAssoCompany: null,
                    officeSpaceLeaseContract: null,
                    socialInsurCardNo: null,
                    buySocialInsurCompany: null,
                    paySocialInsurDetails: null,
                    isSocialecurity: 1,
                    isCompany: 1,
                    isHouse: 1,
                    isCar: 1,
                    houses: [{houseAddress: '',premisesPermit: null, houseDesc: ''}],
                    cars: [{carNumber: '', carDriveLicense: null, carDesc: ''}],
                },
                house_index: 0,
                car_index: 0,
                rules1: {
                    bank:[{ required: true, message: '开户行不能为空', trigger: 'blur' }],
                    bankCardNo: [
                        { required: true, message: '银行卡号不能为空', trigger: 'blur' },
                        { validator:  checkBankCardNo, trigger: 'blur' }
                    ],
                    bankCardPhone: [
                        { required: true, message: '手机号不能为空', trigger: 'blur' },
                        { validator: checkPhone, trigger: 'blur' }
                    ]
                },
            }
        },
        // 监听器
        watch: {
            cardNo: function () {
                this.form.bankCardNo = this.cardNo.replace(/\s+/g,"");
                this.cardNo = this.cardNo.replace(/\s/g, '').replace(/(\d{4})(?=\d)/g, "$1 ");
            },
            bankCheck:function () {
                this.cardType = this.bankCheck.cardType;
            }
        },
        components: {
        },
        computed: {
        },
        created(){
            let userId = localStorage.getItem('userId');
            this.getNameAndIdCard(userId);
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            /**
             * 获取用户信息
             */
            getNameAndIdCard(userId){
                let _than = this;
                this.$axios.get('api/business/user_id',{params:{
                        userId: userId
                    }}).then(function (res) {
                    console.log(res);
                    _than.form.name = res.data.extend.name;
                    _than.form.idCard = res.data.extend.idCard;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 获取验证码
            sendMessage(){
                if(this.btnDisabled){
                    return;
                }
                this.getSecond(60);
            },
            //发送验证码
            getSecond(wait){
                let _this=this;
                let _wait = wait;
                if(wait == 0) {
                    this.btnDisabled=false;
                    this.btnText="获取验证码";                    
                    wait = _wait;
                } else {
                    this.btnDisabled=true;
                    this.btnText="验证码(" + wait + "s)"
                    wait--;
                    setTimeout(function() {
                        _this.getSecond(wait);
                    },
                    1000);
                }
            },

            // 身份证正面上传成功回调函数
            idCardSideFace(res,file,files){
                console.log(res);
                this.form.idCardSideFace = res.extend.fileSystem.filePath;
            },
            // 身份证反面上传成功回调函数
            idCardSideBack(res,file,files){
                this.form.idCardSideBack = res.extend.fileSystem.filePath;
            },
            // 手持身份证上传成功回调函数
            idCardHand(res,file,files){
                this.form.idCardHand = res.extend.fileSystem.filePath;
            },
            // 户口本主页上传成功回调函数
            householdRegHome(res,file,files){
                this.form.householdRegHome = res.extend.fileSystem.filePath;
            },
            // 户口本个人页上传成功回调函数
            householdRegPersonal(res,file,files){
                this.form.householdRegPersonal = res.extend.fileSystem.filePath;
            },
            // 配偶身份证正面上传成功回调函数
            spouseIdCardSideFace(res,file,files){
                this.form.spouseIdCardSideFace = res.extend.fileSystem.filePath;
            },
            // 配偶身份证反面上传成功回调函数
            spouseIdCardSideBack(res,file,files){
                this.form.spouseIdCardSideBack = res.extend.fileSystem.filePath;
            },
            // 结婚证上传成功回调函数
            marriageCertificate(res,file,files){
                this.form.marriageCertificate = res.extend.fileSystem.filePath;
            },
            // 配偶户口本主页上传成功回调函数
            sHouseholdRegHome(res,file,files){
                this.form.sHouseholdRegHome = res.extend.fileSystem.filePath;
            },
            // 配偶户口本个人页上传成功回调函数
            sHouseholdRegPersonal(res,file,files){
                this.form.sHouseholdRegPersonal = res.extend.fileSystem.filePath;
            },
            // 离婚证上传成功回调函数
            divorceCertificate(res,file,files){
                this.form.divorceCertificate = res.extend.fileSystem.filePath;
            },
            // 公司营业执照上传成功回调函数
            companyBusinessLicense(res,file,files){
                this.form.companyBusinessLicense = res.extend.fileSystem.filePath;
            },
            // 公司规章制度上传成功回调函数
            articlesAssoCompany(res,file,files){
                this.form.articlesAssoCompany = res.extend.fileSystem.filePath;
            },
            // 办公场所租赁合同上传成功回调函数
            officeSpaceLeaseContract(res,file,files){
                this.form.officeSpaceLeaseContract = res.extend.fileSystem.filePath;
            },
            // 最近缴纳社保清单上传成功回调函数
            paySocialInsurDetails(res,file,files){
                this.form.paySocialInsurDetails = res.extend.fileSystem.filePath;
            },
            // 房产证明上传成功回调函数
            premisesPermit(res,file,files){
                this.form.houses[this.house_index].premisesPermit = res.extend.fileSystem.filePath;
            },
            // 车辆行驶证上传成功回调函数
            carDriveLicense(res,file,files){
                this.form.cars[this.car_index].carDriveLicense = res.extend.fileSystem.filePath;
            },

            // 添加房产信息
            addHouses() {
                this.form.houses.push({houseAddress: '', premisesPermit: null, houseDesc: ''});
            },
            // 添加车辆信息
            addCars() {
                this.form.cars.push({carNumber: '', carDriveLicense: null, carDesc: ''});
            },
            //移除房产
            removeHouse(index){
                if (index !== -1) {
                    this.form.houses.splice(index, 1)
                }
                // 如果全部删除了，就始终添加一条新的
                if (this.form.houses.length === 0){
                    this.addHouses();
                }
            },
            //移除车辆
            removeCar(index){
                if (index !== -1) {
                    this.form.cars.splice(index, 1)
                }
                // 如果全部删除了，就始终添加一条新的
                if (this.form.cars.length === 0){
                    this.addCars();
                }
            },

            houseIndex(index){
                this.house_index = index;
            },
            carIndex(index){
                this.car_index = index;
            },
            // 保存车辆数据
            saveCar() {
                let _that = this;
                let csrs = _that.form.cars;
                console.log(csrs);
                if (this.form.isCar === 1) {
                    for( let index in csrs) {
                        console.log(index,csrs[index]);
                        _that.$axios.post('api/authen/save/car',
                            _that.qs.stringify({
                                userId: _that.form.userId,
                                number: csrs[index].carNumber,
                                driveLicense: csrs[index].carDriveLicense,
                                remarks: csrs[index].carDesc
                            })
                        ).then(function (res) {
                            console.log(res);
                            // this.$router.push("business-manager-list")
                        }).catch(function (error) {
                            console.log(error);
                        })
                    };
                }
            },
            // 保存房子数据
            saveHouse() {
                let _that = this;
                let houses = _that.form.houses;
                console.log(houses);
                if (this.form.isHouse === 1) {
                    for( let index in houses) {
                        console.log(index,houses[index]);
                        _that.$axios.post('api/authen/save/house',
                            _that.qs.stringify({
                                userId: _that.form.userId,
                                address: houses[index].houseAddress,
                                premisesPermit: houses[index].premisesPermit,
                                remarks: houses[index].houseDesc
                            })
                        ).then(function (res) {
                            console.log(res);
                            // this.$router.push("business-manager-list")
                        }).catch(function (error) {
                            console.log(error);
                        })
                    };
                }
            },
            // 保存数据
            save(){
                let _this = this;
                this.$axios.post('api/authen/save',
                    this.qs.stringify({
                        id: this.form.id,
                        userId: this.form.id,
                        name: this.form.name,
                        idCard: this.form.idCard,
                        bankCardNo: this.form.bankCardNo,
                        bank: this.form.bank,
                        bankCardPhone: this.form.bankCardPhone,
                        cardTypeCode: this.cardType,
                        bankCode: this.bankCode,
                        domicile: this.form.domicile,
                        idCardSideFace: this.form.idCardSideFace,
                        idCardSideBack: this.form.idCardSideBack,
                        idCardHand: this.form.idCardHand,
                        householdRegHome: this.form.householdRegHome,
                        householdRegPersonal: this.form.householdRegPersonal,
                        marriageStatus: this.form.marriageStatus,
                        spouseName: this.form.spouseName,
                        spouseIdCard: this.form.spouseIdCard,
                        spouseDomicile: this.form.spouseDomicile,
                        sidCardSideFace: this.form.spouseIdCardSideFace,
                        sidCardSideBack: this.form.spouseIdCardSideBack,
                        marriageCert: this.form.marriageCertificate,
                        shouseholdRegHome: this.form.sHouseholdRegHome,
                        shouseholdRegPersonal: this.form.sHouseholdRegPersonal,
                        divorceCert: this.form.divorceCertificate,
                        companyName: this.form.companyName,
                        creditCode: this.form.creditCode,
                        companyBusinessLicense: this.form.companyBusinessLicense,
                        articlesAssoCompany: this.form.articlesAssoCompany,
                        officeSpaceLeaseContract: this.form.officeSpaceLeaseContract,
                        socialInsurCardNo: this.form.socialInsurCardNo,
                        socialInsurCompany: this.form.buySocialInsurCompany,
                        socialInsurDetails: this.form.paySocialInsurDetails,
                        isCar: this.form.isCar,
                        isHouse: this.form.isHouse,
                        isSocialecurity: this.form.isSocialecurity,

                    }),{indices: false }).then(function (res) {
                    console.log(res);
                    _this.step += 1;
                }).catch(function (error) {
                    console.log(error);
                });

                // save houses
                this.saveHouse();

                // save cars
                this.saveCar();
            },

            /**
             * 保存银行卡信息
             */
            saveBankCard(){
                this.$refs['form'].validate((valid) => {
                    console.log(valid);
                    if(!valid){
                        return;
                    }
                    let _this = this;
                    this.$axios.post('api/authen/bankCard/save',
                        _this.qs.stringify({
                            userId: _this.form.id,
                            realName: _this.form.name,
                            idCard: _this.form.idCard,
                            accountNo: _this.form.bankCardNo,
                            phone: _this.form.bankCardPhone,
                            bankCode: _this.bankCode,
                            bank: _this.form.bank,
                            cardTypeCode: _this.cardType,
                        })
                    ).then(function (res) {
                        console.log(res);
                    }).catch(function (error) {
                        console.log(error);
                    }) 
                });                                           
            },

            /**
             * 进入首页
             */
            goHome(){
                this.$router.push("/home2");
                localStorage.setItem("authenStatus", 1)
            }
        }
    }

</script>


<style>
    /* 进度条 */
    /*credit-step*/
    .steps-box {
        /*width: 600px;*/
        /*height: 100px;*/
        margin: 0 auto;
    }
    ul,li {
        list-style: none;
    }
    .step {
        overflow: hidden;
    }
    .step li {
        position: relative;
        float: left;
        padding: 0 20px;
        line-height: 30px;
        color: #999;
        background: #e3e3e3;
        width: 152px;
    }
    .triangle {
        content: "";
        position: relative;
        position: absolute;
        top: 0;
        left: 100%;
        z-index: 1;
        border-left: 15px solid #fff;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
    }
    .triangle:after {
        content: "";
        position: absolute;
        left: -16px;
        top: -15px;
        border-left: 15px solid #e3e3e3;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
    }
    .step li.active {
        color: #fff;
        background: #ff8308;
        width: 152px;
    }
    .step li.active .triangle:after {
        border-left-color: #ff8308;
    }
    .step li.active2 {
        color: #fff;
        background: #FFB267;
        width: 152px;
    }
    .step li.active2 .triangle:after {
        border-left-color: #FFB267;
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
        width: 70px;
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
        width: 540px;
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
