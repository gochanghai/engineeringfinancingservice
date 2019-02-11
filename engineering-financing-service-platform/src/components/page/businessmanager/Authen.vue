<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>身份认证</span>
                    </div>
                    <!-- 身份认证 -->
                    <div class="info-box">
                        <div class="info-title">
                            <div class="steps-box" style="width: 600px">
                                <!--<div class="triangle"></div>-->
                                <ul class="step">
                                    <li class="active">
                                        绑定银行卡
                                        <div class="triangle"></div>
                                    </li>
                                    <li class="active2">
                                        上传认证资料
                                        <div class="triangle"></div>
                                    </li>
                                    <li>
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
                                <div class="content-info-box">
                                    <el-form ref="form" label-width="100px">
                                        <el-form-item label="姓名：">张三</el-form-item>
                                        <el-form-item label="身份证号：">430426198502134569</el-form-item>
                                        <el-form-item label="银行卡号：">
                                            <el-input v-model="cardNo" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="开户行：">
                                            <el-input v-model="form.bank" style="width: 400px"/>
                                            <!-- bank logo -->
                                            <img v-show="bankCode != null" :src="'https://apimg.alipay.com/combo.png?d=cashier&t=' + bankCode"/>
                                        </el-form-item>
                                        <el-form-item label="手机号：" style="width: 400px">
                                            <el-input v-model="form.bankCardPhone">
                                                <template slot="append" style="background-color: #fff">
                                                    <div>获取验证码</div>
                                                </template>
                                            </el-input>
                                        </el-form-item>
                                        <el-form-item label="验证码：">
                                            <el-input v-model="form.code" style="width: 200px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 个人身份信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">身份信息</div>
                                    <div class="content-lable2">查看示例</div>
                                </div>
                                <div class="content-info-box">
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
                                                        <img v-if="form.idCardSideFace !== null" class="avatar">
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
                                                        <img v-if="form.idCardSideBack !== null" class="avatar">
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
                                                        <img v-if="form.idCardHand !== null" class="avatar">
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
                                                        <img v-if="form.householdRegHome !== null" class="avatar">
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
                                                        <img v-if="form.householdRegPersonal !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本个人页</span>
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
                                    <el-form label-width="130px">
                                        <el-form-item label="配偶姓名：" >
                                            <el-input v-model="form.spouseName" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="配偶身份证号：" >
                                            <el-input v-model="form.spouseIdCard" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="配偶户籍所在地：" >
                                            <el-input v-model="form.spouseDomicile" style="width: 400px"></el-input>
                                        </el-form-item>
                                        <el-form-item label="配偶附件：" style="margin-bottom: 0">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="spouseIdCardSideFace"
                                                            :show-file-list="false">
                                                        <img v-if="form.spouseIdCardSideFace !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证正面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="spouseIdCardSideBack"
                                                            :show-file-list="false">
                                                        <img v-if="form.spouseIdCardSideBack !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传身份证反面</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="marriageCertificate"
                                                            :show-file-list="false">
                                                        <img v-if="form.marriageCertificate !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传结婚证</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="sHouseholdRegHome"
                                                            :show-file-list="false">
                                                        <img v-if="form.sHouseholdRegHome !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本主页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="sHouseholdRegPersonal"
                                                            :show-file-list="false">
                                                        <img v-if="form.sHouseholdRegPersonal !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传户口本个人页</span>
                                                </el-card>
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" hidden class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="divorceCertificate"
                                                            :show-file-list="false">
                                                        <img v-if="form.divorceCertificate !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传离婚证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 企业信息 -->
                                <div class="content-title">
                                    <div class="content-lable1">企业信息</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <div class="content-info-box">
                                    <el-form label-width="140px">
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
                                                        <img v-if="form.companyBusinessLicense !== null" class="avatar">
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
                                                        <img v-if="form.articlesAssoCompany !== null" class="avatar">
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
                                                        <img v-if="form.officeSpaceLeaseContract !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>办公场所租赁合同</span>
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
                                    <el-form label-width="140px">
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
                                                        <img v-if="form.paySocialInsurDetails !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传社保清单</span>
                                                </el-card>
                                            </div>

                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 个人资产 -->
                                <div class="content-title">
                                    <div class="content-lable1">个人资产</div>
                                    <!--<div class="content-lable2">已认证</div>-->
                                </div>
                                <!-- 房产信息 -->
                                <div class="content-info-box">
                                    <el-form label-width="100px">
                                        <el-form-item label="房产 1" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="房产所在地：" >
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="房产证明：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="premisesPermit"
                                                            :show-file-list="false">
                                                        <img v-if="form.houses.premisesPermit !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传房产证明</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="房产说明：" style="margin-bottom: 0">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                                <!-- 车辆信息 -->
                                <div class="content-info-box">
                                    <el-form label-width="100px">
                                        <el-form-item label="车辆 1" style="margin-bottom: 0"></el-form-item>
                                        <el-form-item label="车牌号：" >
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                        <el-form-item label="车辆行驶证：">
                                            <div class="file-box">
                                                <el-card shadow="hover" :body-style="{ padding: '0px' }" class="card-file">
                                                    <el-upload
                                                            class="avatar-uploader"
                                                            name="file"
                                                            :action="uploadPath"
                                                            :on-success="carDriveLicense"
                                                            :show-file-list="false">
                                                        <img v-if="form.cars.carDriveLicense !== null" class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                    <span>上传车辆行驶证</span>
                                                </el-card>
                                            </div>
                                        </el-form-item>
                                        <el-form-item label="车辆说明：" style="margin-bottom: 0">
                                            <el-input v-model="name" style="width: 400px"/>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 底部按钮 -->
                    <div class="info-bottom-box">
                        <div class="info-bottom-btn1">返回</div>
                        <div class="info-bottom-btn2">上一步</div>
                        <div class="info-bottom-btn2">下一步</div>
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
                            '&cardNo='+this.form.bankCardNo+'&cardBinCheck=true').then(function (response) {
                            _than.bankCheck = response.data;
                            _than.bankCode = response.data.bank;
                            _than.form.bank = _than.getBankName(response.data.bank);
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
                name: localStorage.getItem('ms_username'),
                labelPosition: "right",
                userInfo: null,
                uploadPath: 'http://192.168.1.98:8088/filesystem/upload/',
                filesystem: localStorage.getItem("fileBasePath"),
                bankCheck: null,
                bankCode: null, // 银行代码
                cardNo: '', // 银行卡号
                cardType: '',
                form: {
                    id: localStorage.getItem('userInfoId'),
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
                    houses: [{houseAddress: '',premisesPermit: '', houseDesc: ''}],
                    cars: [{carNumber: '', carDriveLicense: '', carDesc: ''}],
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
        },
        activated(){
        },
        deactivated(){
        },
        methods: {
            // 身份证正面上传成功回调函数
            idCardSideFace(response,file,files){
                console.log(response);
                this.form.idCardSideFace = response.extend.fileSystem.filePath;
            },
            // 身份证反面上传成功回调函数
            idCardSideBack(response,file,files){
                this.form.idCardSideBack = response.extend.fileSystem.filePath;
            },
            // 手持身份证上传成功回调函数
            idCardHand(response,file,files){
                this.form.idCardHand = response.extend.fileSystem.filePath;
            },
            // 户口本主页上传成功回调函数
            householdRegHome(response,file,files){
                this.form.householdRegHome = response.extend.fileSystem.filePath;
            },
            // 户口本个人页上传成功回调函数
            householdRegPersonal(response,file,files){
                this.form.householdRegPersonal = response.extend.fileSystem.filePath;
            },
            // 配偶身份证正面上传成功回调函数
            spouseIdCardSideFace(response,file,files){
                this.form.spouseIdCardSideFace = response.extend.fileSystem.filePath;
            },
            // 配偶身份证反面上传成功回调函数
            spouseIdCardSideBack(response,file,files){
                this.form.spouseIdCardSideBack = response.extend.fileSystem.filePath;
            },
            // 结婚证上传成功回调函数
            marriageCertificate(response,file,files){
                this.form.marriageCertificate = response.extend.fileSystem.filePath;
            },
            // 配偶户口本主页上传成功回调函数
            sHouseholdRegHome(response,file,files){
                this.form.sHouseholdRegHome = response.extend.fileSystem.filePath;
            },
            // 配偶户口本个人页上传成功回调函数
            sHouseholdRegPersonal(response,file,files){
                this.form.sHouseholdRegPersonal = response.extend.fileSystem.filePath;
            },
            // 离婚证上传成功回调函数
            divorceCertificate(response,file,files){
                this.form.divorceCertificate = response.extend.fileSystem.filePath;
            },
            // 公司营业执照上传成功回调函数
            companyBusinessLicense(response,file,files){
                this.form.companyBusinessLicense = response.extend.fileSystem.filePath;
            },
            // 公司规章制度上传成功回调函数
            articlesAssoCompany(response,file,files){
                this.form.articlesAssoCompany = response.extend.fileSystem.filePath;
            },
            // 办公场所租赁合同上传成功回调函数
            officeSpaceLeaseContract(response,file,files){
                this.form.articlesAssoCompany = response.extend.fileSystem.filePath;
            },
            // 最近缴纳社保清单上传成功回调函数
            paySocialInsurDetails(response,file,files){
                this.form.paySocialInsurDetails = response.extend.fileSystem.filePath;
            },
            // 房产证明上传成功回调函数
            premisesPermit(response,file,files){
                this.form.premisesPermit = response.extend.fileSystem.filePath;
            },
            // 车辆行驶证上传成功回调函数
            carDriveLicense(response,file,files){
                this.form.carDriveLicense = response.extend.fileSystem.filePath;
            },


            // 添加房产信息
            addHouses() {
                this.form.houses.push({houseAddress: '', premisesPermit: '', houseDesc: ''});
            },
            // 添加车辆信息
            addCars() {
                this.form.cars.push({carNumber: '', carDriveLicense: '', carDesc: ''});
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
                                fId: _that.form.id,
                                carNumber: csrs[index].carNumber,
                                carDriveLicense: csrs[index].carDriveLicense,
                                carDesc: csrs[index].carDesc
                            })
                        ).then(function (response) {
                            console.log(response);
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
                                fId: _that.form.id,
                                houseAddress: houses[index].houseAddress,
                                premisesPermit: houses[index].premisesPermit,
                                houseDesc: houses[index].houseDesc
                            })
                        ).then(function (response) {
                            console.log(response);
                            // this.$router.push("business-manager-list")
                        }).catch(function (error) {
                            console.log(error);
                        })
                    };
                }


            },
            // 保存数据
            save(){
                this.$axios.post('api/authen/save',
                    this.qs.stringify({
                        id: this.form.id,
                        fid: this.form.id,
                        idCard: this.form.idCard,
                        bankCardNo: this.form.bankCardNo,
                        bank: this.form.bank,
                        bankCardPhone: this.form.bankCardPhone,
                        cardType: this.cardType,
                        bankCode: this.bankCode,
                        code: this.form.code,
                        number: this.form.id,
                        name: this.form.name,
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
                        spouseIdCardSideFace: this.form.spouseIdCardSideFace,
                        spouseIdCardSideBack: this.form.spouseIdCardSideBack,
                        marriageCertificate: this.form.marriageCertificate,
                        sHouseholdRegHome: this.form.sHouseholdRegHome,
                        sHouseholdRegPersonal: this.form.sHouseholdRegPersonal,
                        divorceCertificate: this.form.divorceCertificate,
                        companyName: this.form.companyName,
                        creditCode: this.form.creditCode,
                        companyBusinessLicense: this.form.companyBusinessLicense,
                        articlesAssoCompany: this.form.articlesAssoCompany,
                        officeSpaceLeaseContract: this.form.officeSpaceLeaseContract,
                        socialInsurCardNo: this.form.socialInsurCardNo,
                        buySocialInsurCompany: this.form.buySocialInsurCompany,
                        paySocialInsurDetails: this.form.paySocialInsurDetails,
                        isCar: this.form.isCar,
                        isHouse: this.form.isHouse,
                        isSocialecurity: this.form.isSocialecurity,

                    }),{indices: false }).then(function (response) {
                    console.log(response);
                    // this.$router.push("business-manager-list")
                }).catch(function (error) {
                    console.log(error);
                });

                // save houses
                this.saveHouse();

                // save cars
                this.saveCar();
            },

            // 获取银行名称
            getBankName(bank){
                let data = {
                    "SRCB": "深圳农村商业银行",
                    "BGB": "广西北部湾银行",
                    "SHRCB": "上海农村商业银行",
                    "BJBANK": "北京银行",
                    "WHCCB": "威海市商业银行",
                    "BOZK": "周口银行",
                    "KORLABANK": "库尔勒市商业银行",
                    "SPABANK": "平安银行",
                    "SDEB": "顺德农商银行",
                    "HURCB": "湖北省农村信用社",
                    "WRCB": "无锡农村商业银行",
                    "BOCY": "朝阳银行",
                    "CZBANK": "浙商银行",
                    "HDBANK": "邯郸银行",
                    "BOC": "中国银行",
                    "BOD": "东莞银行",
                    "CCB": "中国建设银行",
                    "ZYCBANK": "遵义市商业银行",
                    "SXCB": "绍兴银行",
                    "GZRCU": "贵州省农村信用社",
                    "ZJKCCB": "张家口市商业银行",
                    "BOJZ": "锦州银行",
                    "BOP": "平顶山银行",
                    "HKB": "汉口银行",
                    "SPDB": "上海浦东发展银行",
                    "NXRCU": "宁夏黄河农村商业银行",
                    "NYNB": "广东南粤银行",
                    "GRCB": "广州农商银行",
                    "BOSZ": "苏州银行",
                    "HZCB": "杭州银行",
                    "HSBK": "衡水银行",
                    "HBC": "湖北银行",
                    "JXBANK": "嘉兴银行",
                    "HRXJB": "华融湘江银行",
                    "BODD": "丹东银行",
                    "AYCB": "安阳银行",
                    "EGBANK": "恒丰银行",
                    "CDB": "国家开发银行",
                    "TCRCB": "江苏太仓农村商业银行",
                    "NJCB": "南京银行",
                    "ZZBANK": "郑州银行",
                    "DYCB": "德阳商业银行",
                    "YBCCB": "宜宾市商业银行",
                    "SCRCU": "四川省农村信用",
                    "KLB": "昆仑银行",
                    "LSBANK": "莱商银行",
                    "YDRCB": "尧都农商行",
                    "CCQTGB": "重庆三峡银行",
                    "FDB": "富滇银行",
                    "JSRCU": "江苏省农村信用联合社",
                    "JNBANK": "济宁银行",
                    "CMB": "招商银行",
                    "JINCHB": "晋城银行JCBANK",
                    "FXCB": "阜新银行",
                    "WHRCB": "武汉农村商业银行",
                    "HBYCBANK": "湖北银行宜昌分行",
                    "TZCB": "台州银行",
                    "TACCB": "泰安市商业银行",
                    "XCYH": "许昌银行",
                    "CEB": "中国光大银行",
                    "NXBANK": "宁夏银行",
                    "HSBANK": "徽商银行",
                    "JJBANK": "九江银行",
                    "NHQS": "农信银清算中心",
                    "MTBANK": "浙江民泰商业银行",
                    "LANGFB": "廊坊银行",
                    "ASCB": "鞍山银行",
                    "KSRB": "昆山农村商业银行",
                    "YXCCB": "玉溪市商业银行",
                    "DLB": "大连银行",
                    "DRCBCL": "东莞农村商业银行",
                    "GCB": "广州银行",
                    "NBBANK": "宁波银行",
                    "BOYK": "营口银行",
                    "SXRCCU": "陕西信合",
                    "GLBANK": "桂林银行",
                    "BOQH": "青海银行",
                    "CDRCB": "成都农商银行",
                    "QDCCB": "青岛银行",
                    "HKBEA": "东亚银行",
                    "HBHSBANK": "湖北银行黄石分行",
                    "WZCB": "温州银行",
                    "TRCB": "天津农商银行",
                    "QLBANK": "齐鲁银行",
                    "GDRCC": "广东省农村信用社联合社",
                    "ZJTLCB": "浙江泰隆商业银行",
                    "GZB": "赣州银行",
                    "GYCB": "贵阳市商业银行",
                    "CQBANK": "重庆银行",
                    "DAQINGB": "龙江银行",
                    "CGNB": "南充市商业银行",
                    "SCCB": "三门峡银行",
                    "CSRCB": "常熟农村商业银行",
                    "SHBANK": "上海银行",
                    "JLBANK": "吉林银行",
                    "CZRCB": "常州农村信用联社",
                    "BANKWF": "潍坊银行",
                    "ZRCBANK": "张家港农村商业银行",
                    "FJHXBC": "福建海峡银行",
                    "ZJNX": "浙江省农村信用社联合社",
                    "LZYH": "兰州银行",
                    "JSB": "晋商银行",
                    "BOHAIB": "渤海银行",
                    "CZCB": "浙江稠州商业银行",
                    "YQCCB": "阳泉银行",
                    "SJBANK": "盛京银行",
                    "XABANK": "西安银行",
                    "BSB": "包商银行",
                    "JSBANK": "江苏银行",
                    "FSCB": "抚顺银行",
                    "HNRCU": "河南省农村信用",
                    "COMM": "交通银行",
                    "XTB": "邢台银行",
                    "CITIC": "中信银行",
                    "HXBANK": "华夏银行",
                    "HNRCC": "湖南省农村信用社",
                    "DYCCB": "东营市商业银行",
                    "ORBANK": "鄂尔多斯银行",
                    "BJRCB": "北京农村商业银行",
                    "XYBANK": "信阳银行",
                    "ZGCCB": "自贡市商业银行",
                    "CDCB": "成都银行",
                    "HANABANK": "韩亚银行",
                    "CMBC": "中国民生银行",
                    "LYBANK": "洛阳银行",
                    "GDB": "广东发展银行",
                    "ZBCB": "齐商银行",
                    "CBKF": "开封市商业银行",
                    "H3CB": "内蒙古银行",
                    "CIB": "兴业银行",
                    "CRCBANK": "重庆农村商业银行",
                    "SZSBK": "石嘴山银行",
                    "DZBANK": "德州银行",
                    "SRBANK": "上饶银行",
                    "LSCCB": "乐山市商业银行",
                    "JXRCU": "江西省农村信用",
                    "ICBC": "中国工商银行",
                    "JZBANK": "晋中市商业银行",
                    "HZCCB": "湖州市商业银行",
                    "NHB": "南海农村信用联社",
                    "XXBANK": "新乡银行",
                    "JRCB": "江苏江阴农村商业银行",
                    "YNRCC": "云南省农村信用社",
                    "ABC": "中国农业银行",
                    "GXRCU": "广西省农村信用",
                    "PSBC": "中国邮政储蓄银行",
                    "BZMD": "驻马店银行",
                    "ARCU": "安徽省农村信用社",
                    "GSRCU": "甘肃省农村信用",
                    "LYCB": "辽阳市商业银行",
                    "JLRCU": "吉林农信",
                    "URMQCCB": "乌鲁木齐市商业银行",
                    "XLBANK": "中山小榄村镇银行",
                    "CSCB": "长沙银行",
                    "JHBANK": "金华银行",
                    "BHB": "河北银行",
                    "NBYZ": "鄞州银行",
                    "LSBC": "临商银行",
                    "BOCD": "承德银行",
                    "SDRCU": "山东农信",
                    "NCB": "南昌银行",
                    "TCCB": "天津银行",
                    "WJRCB": "吴江农商银行",
                    "CBBQS": "城市商业银行资金清算中心",
                    "HBRCU": "河北省农村信用社"
                }
                return data[bank];
            },
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
