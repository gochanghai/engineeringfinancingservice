<template>
    <div class="authen-page">
        <div class="crumbs">
            <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item>身份认证</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <!-- stept-1 -->
            <div class="form-box" v-show="isStept1">
                <el-row style="color: #606266">个人信息填写</el-row>
                <div class="form-box1">
                    <el-form ref="form" :model="form" :rules="rules1" label-width="110px">
                        <el-form-item label="姓名">
                            <el-input v-model="form.name" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="身份证号">
                            <el-input v-model="form.idCard" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="银行卡号" prop="bankCardNo">
                            <el-input v-model="cardNo"></el-input>
                        </el-form-item>
                        <el-form-item label="开户行" prop="bank" style="text-align: left">
                            <el-input v-model="form.bank"></el-input>
                            <!-- bank logo -->
                            <img v-show="bankCode != null" :src="'https://apimg.alipay.com/combo.png?d=cashier&t=' + bankCode"/>
                        </el-form-item>
                        <el-form-item label="手机号码" prop="bankCardPhone">
                            <el-input v-model="form.bankCardPhone"></el-input>
                        </el-form-item>
                        <el-form-item label="验证码">
                            <el-input v-model="form.code"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onCancel">取消</el-button>
                            <el-button type="primary" @click="stepToSecond">下一步</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <!-- stept-2 -->
            <div class="form-box2" v-show="isStept2">
                <el-row style="color: #606266">上传附件</el-row>
                <div class="file-image-box">
                    <el-form ref="form" :model="form" label-width="110px">
                        <el-form-item label="户籍所在地">
                            <el-input v-model="form.domicile" style="width: 300px"></el-input>
                        </el-form-item>
                        <el-row :gutter="20" style="padding: 0 20px; color: #606266">
                            <el-col :span="4" style="padding-left: 30px">身份证正面
                                <el-upload
                                    :action="uploadPath"
                                    list-type="picture-card"
                                    :class="{disabled: isShowUploadIdBtn0}"
                                    name="file"
                                    :on-success="handleSuccess0"
                                    :on-preview="handlePictureCardPreview"
                                    :on-remove="handleRemove">
                                    <i class="el-icon-plus"></i>
                                </el-upload>
                            </el-col>
                            <el-col :span="4">身份证反面
                                <el-upload
                                    :action="uploadPath"
                                    list-type="picture-card"
                                    :class="{disabled: isShowUploadIdBtn1}"
                                    name="file"
                                    :on-success="handleSuccess1"
                                    :on-preview="handlePictureCardPreview"
                                    :on-remove="handleRemove">
                                    <i class="el-icon-plus"></i>
                                </el-upload>
                            </el-col>
                            <el-col :span="4">手持身份证
                                <el-upload
                                    :action="uploadPath"
                                    list-type="picture-card"
                                    :class="{disabled: isShowUploadIdBtn2}"
                                    name="file"
                                    :on-success="handleSuccess2"
                                    :on-preview="handlePictureCardPreview"
                                    :on-remove="handleRemove">
                                    <i class="el-icon-plus"></i>
                                </el-upload>
                            </el-col>
                            <el-col :span="4">户口本户主页
                                <el-upload
                                    :action="uploadPath"
                                    list-type="picture-card"
                                    :class="{disabled: isShowUploadIdBtn3}"
                                    name="file"
                                    :on-success="handleSuccess3"
                                    :on-preview="handlePictureCardPreview"
                                    :on-remove="handleRemove">
                                    <i class="el-icon-plus"></i>
                                </el-upload>
                            </el-col>
                            <el-col :span="4">户口本个人页
                                <el-upload
                                    :action="uploadPath"
                                    list-type="picture-card"
                                    :class="{disabled: isShowUploadIdBtn4}"
                                    name="file"
                                    :on-success="handleSuccess4"
                                    :on-preview="handlePictureCardPreview"
                                    :on-remove="handleRemove">
                                    <i class="el-icon-plus"></i>
                                </el-upload>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>
                <el-row style="color: #606266; margin: 30px 0;">婚姻状况</el-row>
                <el-row style="color: #606266; margin: 30px 0;">
                    <template>
                        <el-radio-group v-model="form.marriageStatus">
                            <el-radio :label="1">已婚</el-radio>
                            <el-radio :label="0">未婚</el-radio>
                            <el-radio :label="-1">离异</el-radio>
                        </el-radio-group>
                    </template>
                </el-row>
                <div v-show="form.marriageStatus === 1">
                    <el-row>
                        <el-form :inline="true" :model="form" class="demo-form-inline" label-width="110px">
                            <el-form-item label="配偶姓名">
                                <el-input v-model="form.spouseName"></el-input>
                            </el-form-item>
                            <el-form-item label="配偶身份证号">
                                <el-input v-model="form.spouseIdCard" style="width: 300px"></el-input>
                            </el-form-item>
                            <el-form-item label="配偶户籍所在地">
                                <el-select v-model="form.spouseDomicile" placeholder="活动区域">
                                    <el-option label="广东深圳" value="广东深圳"></el-option>
                                    <el-option label="广东广州" value="广东广州"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-row>
                    <el-row :gutter="20" style="padding: 0 20px; color: #606266">
                        <el-col :span="4" style="padding-left: 30px">配偶身份证正面
                            <el-upload
                                action="http://192.168.1.98:8088/filesystem/upload/"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadIdBtn5}"
                                name="file"
                                :on-success="handleSuccess5"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                        </el-col>
                        <el-col :span="4">配偶身份证反面
                            <el-upload
                                action="http://192.168.1.98:8088/filesystem/upload/"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadIdBtn6}"
                                name="file"
                                :on-success="handleSuccess6"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                        </el-col>
                        <el-col :span="4">结婚证附件
                            <el-upload
                                action="http://192.168.1.98:8088/filesystem/upload/"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadIdBtn7}"
                                name="file"
                                :on-success="handleSuccess7"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                        </el-col>
                        <el-col :span="4">配偶户口本户主页
                            <el-upload
                                action="http://192.168.1.98:8088/filesystem/upload/"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadIdBtn8}"
                                name="file"
                                :on-success="handleSuccess8"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                        </el-col>
                        <el-col :span="4">配偶户口本个人页
                            <el-upload
                                action="http://192.168.1.98:8088/filesystem/upload/"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadIdBtn9}"
                                name="file"
                                :on-success="handleSuccess9"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                        </el-col>
                    </el-row>
                </div>
                <div v-show="form.marriageStatus === -1">
                    <el-row :gutter="20" style="padding: 0 20px; color: #606266">
                        <el-col :span="4" style="padding-left: 30px">离婚证
                            <el-upload
                                :action="uploadPath"
                                list-type="picture-card"
                                :class="{disabled: isShowUploadIdCard1Btn}"
                                name="file"
                                :on-success="handleSuccess72"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                        </el-col>
                    </el-row>
                </div>
                <el-row style="margin: 50px 250px">
                    <el-button type="primary" @click="stepToOne">上一步</el-button>
                    <el-button type="primary" @click="stepToThree">下一步</el-button>
                    <el-button type="primary" @click="onHomePage">进入首页</el-button>
                </el-row>
            </div>
            <!-- stept-3 -->
            <div class="form-box2" v-show="isStept3">
                <el-row style="color: #606266; margin: 30px 0;">企业信息</el-row>
                <el-row style="color: #606266; margin: 30px 50px;">
                    <el-form>
                        <el-form-item label="名下是否有注册公司：">
                            <el-radio-group v-model="form.isCompany">
                                <el-radio :label="1">是</el-radio>
                                <el-radio :label="0">否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-form>
                </el-row>
                <div style="width: 100%; height: 270px">
                    <div v-show="form.isCompany === 1">
                        <el-form ref="form" :model="form" label-width="170px" style="width: 100%">
                            <el-form-item label="公司全称">
                                <el-input v-model="form.companyName" style="width: 400px"></el-input>
                            </el-form-item>
                            <el-form-item label="统一社会信用代码证">
                                <el-input v-model="form.creditCode" style="width: 400px"></el-input>
                            </el-form-item>
                            <el-row style="color: #606266">
                                <el-col :span="5" style="padding-left: 100px">公司营业执照
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
                                </el-col>
                                <el-col :span="5">公司章程
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
                                </el-col>
                                <el-col :span="5">办公场所租赁合同
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
                                </el-col>
                            </el-row>
                        </el-form>
                    </div>
                </div>
                <el-row style="color: #606266; margin: 30px 0;">社保信息</el-row>
                <el-row style="color: #606266; margin: 30px 50px;">
                    <el-form>
                        <el-form-item label="是否购买深圳社保：">
                            <el-radio-group v-model="form.isSocialecurity">
                                <el-radio :label="1">是</el-radio>
                                <el-radio :label="0">否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-form>
                </el-row>
                <div style="width: 700px; height: 260px">
                <div v-show="form.isSocialecurity === 1">
                    <el-form ref="form" :model="form" label-width="150px">
                        <el-form-item label="社保电脑号">
                            <el-input v-model="form.socialInsurCardNo"></el-input>
                        </el-form-item>
                        <el-form-item label="当前购买社保公司">
                            <el-input v-model="form.buySocialInsurCompany"></el-input>
                        </el-form-item>
                        <el-form-item label="近一年缴纳社保清单">
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
                </div>
                <el-form>
                    <el-form-item>
                        <div style="margin-left: 200px">
                        <el-button type="primary" @click="stepToSecond">上一步</el-button>
                        <el-button type="primary" @click="stepToFour">下一步</el-button>
                        <el-button type="primary" @click="onHomePage">进入首页</el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </div>
            <!-- stept-4 -->
            <div class="form-box" v-show="isStept4">
                <el-row style="color: #606266; margin: 30px 0;">个人资产</el-row>
                <!-- House -->
                <el-row style="color: #606266; margin: 30px 0;">
                    <el-form>
                        <el-form-item label="名下是否有房产">
                            <el-radio-group v-model="form.isHouse">
                                <el-radio :label="1">是</el-radio>
                                <el-radio :label="0">否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-form>
                    <div class="house_boxs" v-bind:class="{house_box:!form.isHouse}">
                        <div v-show="form.isHouse === 1">
                            <el-form ref="form" :model="form" label-width="150px">
                                <div v-for="(house, index) in form.houses">
                                    <el-row>房产{{index + 1}}</el-row>
                                    <el-form-item label="房产所在地">
                                        <el-input v-model="house.houseAddress"></el-input>
                                    </el-form-item>
                                    <el-form-item label="不动产证或购房合同">
                                        <div>
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn14}"
                                            name="file"
                                            :on-success="handleSuccess14"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload></div>
                                    </el-form-item>
                                    <el-form-item label="房产说明">
                                        <el-input v-model="house.houseDesc"></el-input>
                                    </el-form-item>
                                </div>
                                <el-form-item>
                                    <el-button type="warning" plain @click="addHouses">+ 添加更多房产信息</el-button>
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                </el-row>
                <!-- Car -->
                <el-row style="color: #606266; margin: 30px 0;">
                    <el-form>
                        <el-form-item label="名下是否有车">
                            <el-radio-group v-model="form.isCar">
                                <el-radio :label="1">是</el-radio>
                                <el-radio :label="0">否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-form>
                    <div class="car_boxs" v-bind:class="{car_box:!form.isCar}">
                        <div v-show="form.isCar === 1">
                            <el-form ref="form" :model="form" label-width="150px">
                                <div v-for="(car, index) in form.cars">
                                    <el-row>车辆{{index + 1}}</el-row>
                                    <el-form-item label="车牌号">
                                        <el-input v-model="car.carNumber" style="width: 150px"></el-input>
                                    </el-form-item>
                                    <el-form-item label="车辆行驶证附件">
                                        <el-upload
                                            :action="uploadPath"
                                            list-type="picture-card"
                                            :class="{disabled: isShowUploadIdBtn15}"
                                            name="file"
                                            :on-success="handleSuccess15"
                                            :on-preview="handlePictureCardPreview"
                                            :on-remove="handleRemove">
                                            <i class="el-icon-plus"></i>
                                        </el-upload>
                                    </el-form-item>
                                    <el-form-item label="车辆说明">
                                        <el-input v-model="car.carDesc"></el-input>
                                    </el-form-item>
                                </div>
                                <el-form-item>
                                    <el-button type="warning" plain @click="addCars">+ 添加更多车辆信息</el-button>
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                </el-row>
                <el-form>
                    <el-form-item>
                        <div style="margin-left: 200px">
                        <el-button type="primary" @click="saveCar">上一步</el-button>
                        <el-button type="primary" @click="save">提交</el-button>
                        <el-button type="primary" @click="onHomePage">进入首页</el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'authenInfo',
        data: function(){

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
                userInfo: null,
                uploadPath: 'http://192.168.1.98:8088/filesystem/upload/',
                bankCheck: null,
                bankCode: null, // 银行代码
                cardNo: '', // 银行卡号
                cardType: '',
                isShowUploadIdBtn0: false,
                isShowUploadIdBtn1: false,
                isShowUploadIdBtn2: false,
                isShowUploadIdBtn3: false,
                isShowUploadIdBtn4: false,
                isShowUploadIdBtn5: false,
                isShowUploadIdBtn6: false,
                isShowUploadIdBtn7: false,
                isShowUploadIdBtn8: false,
                isShowUploadIdBtn9: false,
                isShowUploadIdBtn10: false,
                isShowUploadIdBtn11: false,
                isShowUploadIdBtn12: false,
                isShowUploadIdBtn13: false,
                isShowUploadIdBtn14: false,
                isShowUploadIdBtn15: false,
                isShowUploadIdBtn16: false,

                now_upload_houseFile: null,

                isStept1: true,
                isStept2: false,
                isStept3: false,
                isStept4: false,
                dialogImageUrl: '',
                dialogVisible: false,
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
                rules2: {
                    bankIdCard: [
                        { required: true, message: '银行卡号不能为空', trigger: 'blur' },
                    ],
                    bankBindPhone: [
                        { required: true, message: '银行卡号不能为空', trigger: 'blur' },
                    ]
                },
                rules3: {
                    // pass: [
                    //     { validator: validatePass, trigger: 'blur' }
                    // ],
                    bankIdCard: [
                        { required: true, message: '银行卡号不能为空', trigger: 'blur' },
                    ],
                    bankBindPhone: [
                        { validator: checkPhone, trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            this.getUserInfo();
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
        methods: {
            // 获取用户信息
            getUserInfo(){
                let _than = this;
                this.$axios.get('financier/get',{params:{
                        id: this.form.id
                    }}).then(function (response) {
                    console.log(response);
                    _than.form.name = response.data.extend.userInfo.name;
                    _than.form.idCard = response.data.extend.userInfo.idCard;
                }).catch(function (error) {

                });
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

            handleSuccess0(response,file,files){
                this.form.idCardSideFace = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn0 = true;
                console.log("response::: " + response);
            },
            handleSuccess1(response,file,files){
                this.form.idCardSideBack = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn1 = true;
                console.log("response::: " + response);
            },
            handleSuccess2(response,file,files){
                this.form.idCardHand = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn2 = true;
                console.log("response::: " + response);
            },
            handleSuccess3(response,file,files){
                this.form.householdRegHome = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn3 = true;
                console.log("response::: " + response);
            },
            handleSuccess4(response,file,files){
                this.form.householdRegPersonal = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn4 = true;
                console.log("response::: " + response);
            },
            handleSuccess5(response,file,files){
                this.form.spouseIdCardSideFace = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn5 = true;
                console.log("response::: " + response);
            },
            handleSuccess6(response,file,files){
                this.form.spouseIdCardSideBack = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn6 = true;
                console.log("response::: " + response);
            },
            handleSuccess7(response,file,files){
                this.form.marriageCertificate = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn7 = true;
                console.log("response::: " + response);
            },
            handleSuccess72(response,file,files){
                this.form.divorceCertificate = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn7 = true;
                console.log("response::: " + response);
            },
            handleSuccess8(response,file,files){
                this.form.sHouseholdRegHome = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn8 = true;
                console.log("response::: " + response);
            },
            handleSuccess9(response,file,files){
                this.form.sHouseholdRegPersonal = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn9 = true;
                console.log("response::: " + response);
            },
            handleSuccess10(response,file,files){
                this.form.companyBusinessLicense = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn10 = true;
                console.log("response::: " + response);
            },
            handleSuccess11(response,file,files){
                this.form.articlesAssoCompany = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn11 = true;
                console.log("response::: " + response);
            },
            handleSuccess12(response,file,files){
                this.form.officeSpaceLeaseContract = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn12 = true;
                console.log("response::: " + response);
            },
            handleSuccess13(response,file,files){
                this.form.paySocialInsurDetails = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn13 = true;
                console.log("response::: " + response);
            },
            handleSuccess14(response,file,files){
                this.form.houses[0].premisesPermit = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn14 = true;
                console.log("response::: " + response);
            },
            handleSuccess15(response,file,files){
                this.form.cars[0].carDriveLicense = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn15 = true;
                console.log("response::: " + response);
            },
            handleSuccess16(response,file,files){
                this.form.cars[0].carDriveLicense = response.extend.fileSystem.filePath;
                this.isShowUploadIdBtn16 = true;
                console.log("response::: " + response);
            },

            houseNo(number){
                console.log("housePNumber: " + number);
                this.now_upload_houseFile = number;
            },


            onCancel() {
                this.$router.push("financier-home")
            },
            stepToSecond() {
                this.isStept1 = false;
                this.isStept2 = true;
                this.isStept3 = false;
                this.isStept4 = false;
            },
            stepToOne() {
                this.isStept1 = true;
                this.isStept2 = false;
                this.isStept3 = false;
                this.isStept4 = false;
            },
            stepToThree() {
                this.isStept1 = false;
                this.isStept2 = false;
                this.isStept3 = true;
                this.isStept4 = false;
            },
            stepToFour() {
                this.isStept1 = false;
                this.isStept2 = false;
                this.isStept3 = false;
                this.isStept4 = true;
            },
            addHouses() {
                this.form.houses.push({houseAddress: '', premisesPermit: '', houseDesc: ''});
            },
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

            onSubmit(){
                this.$message("提交成功！ ")
            },
            onHomePage(){
                this.$router.push('financier-home')
            },
            handleRemove(file, fileList, value) {
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            }
        }
    }
</script>

<style>

    .disabled .el-upload--picture-card {
        display: none;
    }
    .authen-page {
        height: 800px;
    }
    .crumbs {
        height: 50px;
        background-color: #fff;
        padding-left: 50px;
        margin-bottom: 0;
    }

    .breadcrumb {
        padding-top: 5px;
        line-height: 30px;
        padding-left: 20px;
        border-left: 4px solid #ff8208;
    }

    .breadcrumb el-breadcrumb-item {
        font-size: 40px;
    }

    .container {
        padding-left: 100px;
        border: none;
        border-top: 1px solid #cccccc;
        border-radius: 0;
    }
    .form-box1 {
        margin: 50px 100px 100px 50px;
        width: 600px;
        text-align: center;
    }
    .form-box2 {
        width: 100%;
    }
    .file-image-box {
        margin-top: 20px;
        width: 100%;
        height: 200px;
        background-color: #fff;
        margin-bottom: 50px;
    }

    .house_box {
       height: 330px;
    }
    .house_boxs {
        width: 700px;
    }
    .car_box {
        height: 330px;
    }
    .car_boxs {
        width: 700px;
    }



</style>
