SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------
-- 创建数据库
-- -----------------------
CREATE DATABASE efservice;

-- -----------------------
-- 使用数据库
-- -----------------------
USE efservice;

-- -----------------------
-- 创建表 sys_user
-- -----------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `headimgurl` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `wxopenid` varchar(50) DEFAULT NULL COMMENT '微信ID',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


-- -----------------------
-- 创建表 cust_engineeringcompany
-- -----------------------
DROP TABLE IF EXISTS `cust_engineeringcompany`;
CREATE TABLE `cust_engineeringcompany` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `company_name` varchar(20) DEFAULT NULL COMMENT '公司名称',
  `address` varchar(20) DEFAULT NULL COMMENT '公司地址',
  `person` varchar(50) DEFAULT NULL COMMENT '法人',
  `id_card` varchar(100) DEFAULT NULL COMMENT '法人身份证号',
  `id_card_side_face` varchar(50) DEFAULT NULL COMMENT '身份证正面',
  `id_card_side_back` varchar(50) DEFAULT NULL COMMENT '身份证背面',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '信用代码',
  `credit_code_file` varchar(50) DEFAULT NULL COMMENT '信用代码',
  `public_account_name` varchar(50) DEFAULT NULL COMMENT '对公账户名称',
  `open_account_bank` varchar(50) DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `username1` varchar(50) DEFAULT NULL COMMENT '用户1',
  `phone1` varchar(50) DEFAULT NULL COMMENT '手机号1',
  `username2` varchar(50) DEFAULT NULL COMMENT '用户名2',
  `phone2` varchar(50) DEFAULT NULL COMMENT '手机号2',
  `auth_file` varchar(50) DEFAULT NULL COMMENT '授权证书',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程公司表';


-- -----------------------
-- 创建表 financecompany
-- -----------------------
DROP TABLE IF EXISTS `financecompany`;
CREATE TABLE `financecompany` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `company_name` varchar(20) DEFAULT NULL COMMENT '企业名称',
  `contact_person` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `coop_bank` varchar(50) DEFAULT NULL COMMENT '合作行',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资金方表';


-- -----------------------
-- 创建表 businessmanager
-- -----------------------
DROP TABLE IF EXISTS `businessmanager`;
CREATE TABLE `businessmanager` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `company_id` bigint(20) DEFAULT NULL COMMENT '公司ID',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `idCard` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `birthdate` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `jobDate` date DEFAULT NULL COMMENT '从业日期',
  `coopDate` date DEFAULT NULL COMMENT '合作日期',
  `jobYearNumber` int(20) DEFAULT NULL COMMENT '从业年限',
  `coopYearNumber` int(20) DEFAULT NULL COMMENT '合作年限',
  `rate` varchar(10) DEFAULT NULL COMMENT '评级',
  `rateDesc` varchar(100) DEFAULT NULL COMMENT '评级说明',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商务经理表';



-- -----------------------
-- 创建表 businessmanager
-- -----------------------
DROP TABLE IF EXISTS `businessmanager_authen`;
CREATE TABLE `businessmanager_authen` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `bank_card_no` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `bank` varchar(100) DEFAULT NULL COMMENT '银行',
  `bank_card_phone` varchar(100) DEFAULT NULL COMMENT '银行卡手机号',
  `domicile` varchar(100) DEFAULT NULL COMMENT '户籍所在地',
  `id_card_side_face` varchar(100) DEFAULT NULL COMMENT '身份证正面',
  `id_card_side_back` varchar(100) DEFAULT NULL COMMENT '身份证正面',
  `id_card_hand` varchar(100) DEFAULT NULL COMMENT '手持身份证',
  `household_reg_home` varchar(100) DEFAULT NULL COMMENT '户口本主页',
  `household_reg_personal` varchar(100) DEFAULT NULL COMMENT '户口本个人页',
  `marriage_status` varchar(100) DEFAULT NULL COMMENT '婚姻状况',
  `spouse_name` varchar(100) DEFAULT NULL COMMENT '配偶姓名',
  `spouse_id_card` varchar(100) DEFAULT NULL COMMENT '配偶身份证号',
  `spouse_domicile` varchar(100) DEFAULT NULL COMMENT '配偶户籍所在地',
  `spouse_id_card_side_face` varchar(100) DEFAULT NULL COMMENT '配偶身份证正面',
  `spouse_id_card_side_back` varchar(100) DEFAULT NULL COMMENT '配偶身份证正面',
  `spouse_household_reg_home` varchar(100) DEFAULT NULL COMMENT '配偶户口本主页',
  `spouse_household_reg_personal` varchar(100) DEFAULT NULL COMMENT '配偶户口本个人页',
  `marriage_cert` varchar(100) DEFAULT NULL COMMENT '结婚证',
  `divorce_cert` varchar(100) DEFAULT NULL COMMENT '离婚证',
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `credit_code` varchar(100) DEFAULT NULL COMMENT '信用代码',
  `company_business_license` varchar(100) DEFAULT NULL COMMENT '公司营业执照',
  `articles_asso_company` varchar(100) DEFAULT NULL COMMENT '公司章程',
  `office_space_lease_contract` varchar(100) DEFAULT NULL COMMENT '办公场所租赁合同',
  `social_insur_card_no` varchar(100) DEFAULT NULL COMMENT '社保卡号',
  `pay_social_insur_details` varchar(100) DEFAULT NULL COMMENT '现在购买社保公司',
  `buy_social_insur_company` varchar(100) DEFAULT NULL COMMENT '最近缴纳社保清单',
  `is_socialecurity` int(11) DEFAULT NULL COMMENT '是否有社保',
  `is_house` int(11) DEFAULT NULL COMMENT '是否有房子',
  `is_car` int(11) DEFAULT NULL COMMENT '是否有车',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商务经理认证表';



-- -----------------------
-- 创建表 businessmanager_car
-- -----------------------
DROP TABLE IF EXISTS `businessmanager_car`;
CREATE TABLE `businessmanager_car` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `number` varchar(50) DEFAULT NULL COMMENT '车牌号',
  `drive_license` varchar(100) DEFAULT NULL COMMENT '车辆行驶证',
  `desc` varchar(200) DEFAULT NULL COMMENT '车辆说明',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车辆信息表';

-- -----------------------
-- 创建表 businessmanager_house
-- -----------------------
DROP TABLE IF EXISTS `businessmanager_house`;
CREATE TABLE `businessmanager_house` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `address` varchar(200) DEFAULT NULL COMMENT '房产所在地',
  `premisesPermit` varchar(100) DEFAULT NULL COMMENT '房产证',
  `desc` varchar(200) DEFAULT NULL COMMENT '房产说明',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房子信息表';





-- -----------------------
-- 创建表 project
-- -----------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `company_id` bigint(20) DEFAULT NULL COMMENT '公司ID',
  `project_name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `project_no` varchar(50) DEFAULT NULL COMMENT '项目编号',
  `contract_no` varchar(50) DEFAULT NULL COMMENT '合同编号',
  `contract_amount` double DEFAULT NULL COMMENT '合同金额',
  `credit_amount` double DEFAULT NULL COMMENT '授信金额',
  `project_progress` double DEFAULT NULL COMMENT '项目进度',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目表';


-- -----------------------
-- 创建表 engineeringcompany_branch
-- -----------------------
DROP TABLE IF EXISTS `engineeringcompany_branch`;
CREATE TABLE `engineeringcompany_branch` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `p_id` bigint(20) NOT NULL COMMENT '主公司ID',
  `company_name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `address` varchar(100) DEFAULT NULL COMMENT '项目编号',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分公司表';


-- -----------------------
-- 创建表 financecompany
-- -----------------------
DROP TABLE IF EXISTS `financecompany`;
CREATE TABLE `financecompany` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `coop_bank` varchar(100) DEFAULT NULL COMMENT '合作分行',
  `address` varchar(100) DEFAULT NULL COMMENT '分行地址',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资金公司表';



-- -----------------------
-- 创建表 project_contract
-- -----------------------
DROP TABLE IF EXISTS `project_contract`;
CREATE TABLE `project_contract` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `party_a_name` varchar(100) DEFAULT NULL COMMENT '甲方名称',
  `address` varchar(100) DEFAULT NULL COMMENT '项目地址',
  `contract_amount` varchar(50) DEFAULT NULL COMMENT '合同金额',
  `should_clear_amount` varchar(50) DEFAULT NULL COMMENT '预结算金额',
  `guarantee_claim` varchar(50) DEFAULT NULL COMMENT '保函要求',
  `start_date` varchar(50) DEFAULT NULL COMMENT '开始日期',
  `end_date` varchar(50) DEFAULT NULL COMMENT '结束日期',
  `clear_type` int(11) DEFAULT NULL COMMENT '结算方式',
  `should_payment_amount` varchar(100) DEFAULT NULL COMMENT '预付款',
  `should_payment_date` varchar(100) DEFAULT NULL COMMENT '预付款支付日期',
  `progress_payment_type` varchar(100) DEFAULT NULL COMMENT '进度付款方式',
  `month_clear_day` varchar(50) DEFAULT NULL COMMENT '月结算日',
  `progress_payment_ratio` varchar(50) DEFAULT NULL COMMENT '进度付款比例',
  `check_payment_ratio` varchar(50) DEFAULT NULL COMMENT '验收付款比例',
  `check_payment_desc` varchar(50) DEFAULT NULL COMMENT '验收款说明',
  `clear_payment_ratio` int(11) DEFAULT NULL COMMENT '结算付款比例',
  `clear_desc` varchar(100) DEFAULT NULL COMMENT '结算款备注',
  `warranty_money_payment_ratio` varchar(100) DEFAULT NULL COMMENT '质保金付款比例',
  `warranty` varchar(100) DEFAULT NULL COMMENT '保质期',
  `warranty_pay_type` varchar(50) DEFAULT NULL COMMENT '保质期支付方式',
  `contract_file` varchar(50) DEFAULT NULL COMMENT '项目合同文件',
  `contract_payment_file` varchar(50) DEFAULT NULL COMMENT '合同付款方式页附件凭证',
  `in_contract_liability_book` varchar(50) DEFAULT NULL COMMENT '内部经营承包责任书附件',
  `construction_site_photo` int(11) DEFAULT NULL COMMENT '施工现场照片',
  `is_buy_insur` int(11) DEFAULT NULL COMMENT '是否购买保险',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目合同表';



-- -----------------------
-- 创建表 project_payment
-- -----------------------
DROP TABLE IF EXISTS `project_payment`;
CREATE TABLE `project_payment` (
  `id` bigint(20) NOT  NULL COMMENT '项目ID',
  `sum_bill_amount` varchar(100) DEFAULT NULL COMMENT '前期累计开票额',
  `sum_payback_amount` varchar(100) DEFAULT NULL COMMENT '前期累计回款额',
  `sum_payment_amount` varchar(50) DEFAULT NULL COMMENT '前期累计付款额',
  `invoice` varchar(200) DEFAULT NULL COMMENT '发票凭证',
  `transfer` varchar(200) DEFAULT NULL COMMENT '转账凭证',
  `transfer_to_invoice` varchar(200) DEFAULT NULL COMMENT '对应发票凭证',
  `purchase_contract` varchar(200) DEFAULT NULL COMMENT '采购合同',
  `purchase_invoice` varchar(200) DEFAULT NULL COMMENT '采购发票',
  `delivery_note` varchar(200) DEFAULT NULL COMMENT '送货单',
  `banck_transfer` varchar(200) DEFAULT NULL COMMENT '银行转账凭证',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目款项信息表';


-- -----------------------
-- 创建表 project_cost
-- -----------------------
DROP TABLE IF EXISTS `project_cost`;
CREATE TABLE `project_cost` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `profit_margin` varchar(100) DEFAULT NULL COMMENT '项目利润率',
  `progress_return_account` varchar(200) DEFAULT NULL COMMENT '工程进度回款台帐',
  `output_value_table` varchar(50) DEFAULT NULL COMMENT '项目产值表',
  `cost_analysis_table` varchar(200) DEFAULT NULL COMMENT '项目成本分析表',
  `cost_account` varchar(200) DEFAULT NULL COMMENT '项目成本台帐',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目成本信息表';

-- -----------------------
-- 创建表 project_other
-- -----------------------
DROP TABLE IF EXISTS `project_other`;
CREATE TABLE `project_other` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `is_buy_insur` int(11) DEFAULT NULL COMMENT '是否购买保险',
  `insur_end_date` varchar(200) DEFAULT NULL COMMENT '保险到日期',
  `insur_amount` varchar(50) DEFAULT NULL COMMENT '保险金额',
  `insur_person_number` int(10) DEFAULT NULL COMMENT '保险人数',
  `insur_file` varchar(200) DEFAULT NULL COMMENT '保险文件',
  `unusual_desc` varchar(200) DEFAULT NULL COMMENT '特殊说明',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目其他信息表';

-- -----------------------
-- 创建表 project_progress_detail
-- -----------------------
DROP TABLE IF EXISTS `project_progress_detail`;
CREATE TABLE `project_progress_detail` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `date` date DEFAULT NULL COMMENT '施工时间',
  `progress_ratio` varchar(200) DEFAULT NULL COMMENT '施工进度',
  `describe` varchar(50) DEFAULT NULL COMMENT '进度说明',
  `output_value` double (10) DEFAULT NULL COMMENT '产值',
  `file` varchar(200) DEFAULT NULL COMMENT '现场文件',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目进度明细信息表';



-- ----------------------------
--  Table structure for `bank_card`
-- ----------------------------
DROP TABLE IF EXISTS `bank_card`;
CREATE TABLE `bank_card` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `card_no` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `card_type` varchar(50) DEFAULT NULL COMMENT '银行卡类型',
  `bank` varchar(50) DEFAULT NULL COMMENT '银行',
  `bank_code` varchar(50) DEFAULT NULL COMMENT '银行代码',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `number` varchar(50) DEFAULT NULL COMMENT '证件号',
  `type` varchar(50) DEFAULT NULL COMMENT '证件类型',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='银行卡表';


-- ----------------------------
--  Table structure for `tb_project_file`
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_file`;
CREATE TABLE `tb_project_file` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `classify` varchar(50) DEFAULT NULL COMMENT '文件分类',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `remarks` varchar(50) DEFAULT NULL COMMENT '备注',
  `type` varchar(20) DEFAULT NULL COMMENT '文件类型',
  `author` varchar(20) DEFAULT NULL COMMENT '上传人',
  `fileurl` varchar(20) DEFAULT NULL COMMENT '文件地址',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `upload_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目文件信息表';


-- ----------------------------
--  Table structure for `file_system`
-- ----------------------------
DROP TABLE IF EXISTS `file_system`;
CREATE TABLE `file_system` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `file_path` bigint(20) DEFAULT NULL COMMENT '文件地址',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件分类',
  `file_size` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `file_type` varchar(50) DEFAULT NULL COMMENT '备注',
  `file_url` varchar(20) DEFAULT NULL COMMENT '文件连接',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件存储信息表';


-- ----------------------------
--  Table structure for `message_system`
-- ----------------------------
DROP TABLE IF EXISTS `message_system`;
CREATE TABLE `message_system` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `type` varchar(50) DEFAULT NULL COMMENT '消息类型( 0: 系统公告 1: 消息通知)',
  `create_date` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `title` varchar(50) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(20) DEFAULT NULL COMMENT '消息内容',
  `status` int(11) DEFAULT NULL COMMENT '消息状态 0: 未读 1：已读',
  `read_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '阅读时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告消息信息表';

-- ----------------------------
--  Table structure for `company_user`
-- ----------------------------
DROP TABLE IF EXISTS `company_user`;
CREATE TABLE `company_user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `company_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_id` varchar(50) DEFAULT NULL COMMENT '公司ID',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司用户关系表';


-- ----------------------------
--  Table structure for `sms_code`
-- ----------------------------
DROP TABLE IF EXISTS `sms_code`;
CREATE TABLE `sms_code` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `phone_number` bigint(20) DEFAULT NULL COMMENT '手机号码',
  `check_code` varchar(50) DEFAULT NULL COMMENT '验证码',
  `valid_time` double DEFAULT NULL COMMENT '有效时间',
  `type` varchar(50) DEFAULT NULL COMMENT '短信类型',
  `template_code` varchar(50) DEFAULT NULL COMMENT '模板编号',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信验证码';


-- ----------------------------
--  Table structure for `project_credit_apply`
-- ----------------------------
DROP TABLE IF EXISTS `project_credit_apply`;
CREATE TABLE `project_credit_apply` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '申请人ID',
  `project_id` bigint(20) DEFAULT NULL COMMENT '申请授信项目ID',
  `apply_no` varchar(50) DEFAULT NULL COMMENT '申请编号',
  `apply_data` date DEFAULT NULL COMMENT '申请时间',
  `name` varchar(50) DEFAULT NULL COMMENT '申请人',
  `origin_incident` varchar(50) DEFAULT NULL COMMENT '申请金额',
  `apply_amount` varchar(50) DEFAULT NULL COMMENT '申请金额',
  `bank_water` varchar(50) DEFAULT NULL COMMENT '银行流水文件',
  `step` varchar(50) DEFAULT NULL COMMENT '申请进度',
  `status` varchar(50) DEFAULT NULL COMMENT '进度状态',
  `credit_type` varchar(50) DEFAULT NULL COMMENT '授信类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目授信申请';


-- ----------------------------
--  Table structure for `project_credit_apply`
-- ----------------------------
DROP TABLE IF EXISTS `project_loan_apply`;
CREATE TABLE `project_loan_apply` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '申请人ID',
  `project_id` bigint(20) DEFAULT NULL COMMENT '申请授信项目ID',
  `apply_no` varchar(50) DEFAULT NULL COMMENT '申请编号',
  `apply_data` date DEFAULT NULL COMMENT '申请时间',
  `name` varchar(50) DEFAULT NULL COMMENT '申请人',
  `apply_amount` varchar(50) DEFAULT NULL COMMENT '申请金额',
  `loan_cycle` varchar(50) DEFAULT NULL COMMENT '贷款周期',
  `status` varchar(50) DEFAULT NULL COMMENT '进度状态',
  `companyId` varchar(50) DEFAULT NULL COMMENT '工程公司ID',
  `fcompanyId` varchar(50) DEFAULT NULL COMMENT '资金方ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目贷款申请';



-- ----------------------------
--  Table structure for `finance_product`
-- ----------------------------
DROP TABLE IF EXISTS `finance_product`;
CREATE TABLE `finance_product` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `product_no` varchar(100) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(100) DEFAULT NULL COMMENT '产品名称',
  `product_type` date DEFAULT NULL COMMENT '产品类型',
  `product_img` date DEFAULT NULL COMMENT '产品图片',
  `year_rate` varchar(50) DEFAULT NULL COMMENT '年率利',
  `overdue_rate` varchar(50) DEFAULT NULL COMMENT '逾期罚息利率',
  `deadline_type` varchar(50) DEFAULT NULL COMMENT '期限类型',
  `deadline_min` varchar(50) DEFAULT NULL COMMENT '期限最小',
  `deadline_max` varchar(50) DEFAULT NULL COMMENT '期限最大',
  `fcompanyId` varchar(50) DEFAULT NULL COMMENT '资金方ID',
  `is_delay` varchar(50) DEFAULT NULL COMMENT '是否可延期',
  `servicefee_type` varchar(50) DEFAULT NULL COMMENT '服务费类型',
  `repayment_type` varchar(50) DEFAULT NULL COMMENT '还款类型',
  `servicefee_rate` varchar(50) DEFAULT NULL COMMENT '服务类率利',
  `is_assure` varchar(50) DEFAULT NULL COMMENT '是否担保',
  `assure_company_id` varchar(50) DEFAULT NULL COMMENT '担保公司ID',
  `mortgage_type` varchar(50) DEFAULT NULL COMMENT '抵押类型',
  `assurefee_type` varchar(50) DEFAULT NULL COMMENT '担保类型',
  `assure_fee_rate` varchar(50) DEFAULT NULL COMMENT '担保费率利',
  `status` varchar(50) DEFAULT NULL COMMENT '产品状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='金融产品';


-- ----------------------------
--  Table structure for `project_purchase_order`
-- ----------------------------
DROP TABLE IF EXISTS `project_purchase_order`;
CREATE TABLE `project_purchase_order` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `loan_no` varchar(100) DEFAULT NULL COMMENT '贷款编号',
  `contract_no` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `contract_file` varchar(100) DEFAULT NULL COMMENT '合同附件',
  `roder_amount` double DEFAULT NULL COMMENT '订单金额',
  `roder_file` varchar(200) DEFAULT NULL COMMENT '订单附件',
  `invoice_amount` double DEFAULT NULL COMMENT '发票金额',
  `invoice_file` varchar(200) DEFAULT NULL COMMENT '发票附件',
  `delivery_file` varchar(200) DEFAULT NULL COMMENT '送货单',
  `bank_account` varchar(100) DEFAULT NULL COMMENT '银行账号',
  `account_name` varchar(100) DEFAULT NULL COMMENT '银行账户名称',
  `bank` varchar(100) DEFAULT NULL COMMENT '开户行',
  `status` int (10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目采购订单信息';

-- ----------------------------
--  Table structure for `credit_company_product`
-- ----------------------------
DROP TABLE IF EXISTS `credit_company_product`;
CREATE TABLE `credit_company_product` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `company_id` bigint(20) DEFAULT NULL COMMENT '工程公司ID',
  `fcompany_id` bigint(20) DEFAULT NULL COMMENT '资金方ID',
  `product_id` bigint(20) DEFAULT NULL COMMENT '金融产品ID',
  `amount` double DEFAULT NULL COMMENT '授信额度',
  `status` int (10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程公司授信信息';

-- ----------------------------
--  Table structure for `credit_project_product`
-- ----------------------------
DROP TABLE IF EXISTS `credit_project_product`;
CREATE TABLE `credit_project_product` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `apply_no` varchar(100) DEFAULT NULL COMMENT '授信申请编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '商务经理ID',
  `project_id` bigint(20) DEFAULT NULL COMMENT '工程公司ID',
  `fcompany_id` bigint(20) DEFAULT NULL COMMENT '资金方ID',
  `product_id` bigint(20) DEFAULT NULL COMMENT '金融产品ID',
  `amount` double DEFAULT NULL COMMENT '授信额度',
  `status` int (10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目授信信息';


-- ----------------------------
--  Table structure for `project_credit_examineapprove`
-- ----------------------------
DROP TABLE IF EXISTS `project_credit_examineapprove`;
CREATE TABLE `project_credit_examineapprove` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `apply_id` bigint(20) DEFAULT NULL COMMENT '授信申请ID',
  `apply_no` varchar(100) DEFAULT NULL COMMENT '授信申请编号',
  `credit_type` varchar(250) DEFAULT NULL COMMENT '授信类型',
  `result` varchar(20) DEFAULT NULL COMMENT '审批结果',
  `desc` varchar(250) DEFAULT NULL COMMENT '审批意见',
  `repay_opinion` varchar(250) DEFAULT NULL COMMENT '还款说明',
  `credit_date` varchar(200) DEFAULT NULL COMMENT '授信期限',
  `start_time` varchar(200) DEFAULT NULL COMMENT '授信开始时间',
  `end_time` varchar(200) DEFAULT NULL COMMENT '授信结束时间',
  `amount` double DEFAULT NULL COMMENT '授信额度',
  `opinion` varchar(250) DEFAULT NULL COMMENT '特批说明',
  `incontrol` varchar(250) DEFAULT NULL COMMENT '内控要点',
  `after_loandesc` varchar(250) DEFAULT NULL COMMENT '贷后管理说明',
  `reply_file` varchar(250) DEFAULT NULL COMMENT '批复文件',
  `name` varchar(250) DEFAULT NULL COMMENT '审批人',
  `status` int (10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目授信审批信息';



-- ----------------------------
--  Table structure for `project_loan_examineapprove`
-- ----------------------------
DROP TABLE IF EXISTS `project_loan_examineapprove`;
CREATE TABLE `project_loan_examineapprove` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `apply_id` bigint(20) DEFAULT NULL COMMENT '贷款申请ID',
  `apply_no` varchar(100) DEFAULT NULL COMMENT '贷款申请编号',
  `result` varchar(20) DEFAULT NULL COMMENT '审批结果',
  `onsite_file` varchar(200) DEFAULT NULL COMMENT '现场文件',
  `creditrate_file` varchar(200) DEFAULT NULL COMMENT '资信评估文件',
  `purchase_amount` double DEFAULT NULL COMMENT '采购订单金额',
  `opinion` varchar(250) DEFAULT NULL COMMENT '审批说明',
  `name` varchar(250) DEFAULT NULL COMMENT '审批人',
  `status` int (10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目贷款审批信息';