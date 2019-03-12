SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_bank_card`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bank_card`;
CREATE TABLE `tb_bank_card` (
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
--  Table structure for `tb_project_credit`
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_credit`;
CREATE TABLE `tb_project_credit` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `product_id` bigint(20) DEFAULT NULL COMMENT '金融产品ID',
  `f_id` bigint(20) DEFAULT NULL COMMENT '资金方ID',
  `credit_no` varchar(50) DEFAULT NULL COMMENT '授信编号',
  `credit_amount` double DEFAULT NULL COMMENT '授信金额',
  `bank` varchar(50) DEFAULT NULL COMMENT '银行',
  `credit_type` varchar(20) DEFAULT NULL COMMENT '授信类型',
  `credit_date` date DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目授信表';


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