SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------
-- 创建数据库
-- -----------------------
CREATE DATABASE ConstructionTeamSAAS;

-- -----------------------
-- 使用数据库
-- -----------------------
USE ConstructionTeamSAAS;

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
-- 创建表 ggb_account
-- -----------------------
DROP TABLE IF EXISTS `ggb_account`;
CREATE TABLE `ggb_account` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '主键ID',
  `real_name` bigint(20) DEFAULT NULL COMMENT '姓名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `idCardSideFace` varchar(50) DEFAULT NULL COMMENT '身份证正面',
  `idCardSideBack` varchar(50) DEFAULT NULL COMMENT '身份证反面',
  `status` int(11) DEFAULT NULL COMMENT '实名状态',
  `auth_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '实名认证确认时间（yyyy-MM-dd）',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账户表';


-- --------------------------
-- 创建表 ggb_project_member
-- --------------------------
DROP TABLE IF EXISTS `ggb_project_member`;
CREATE TABLE `ggb_project_member` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `name` bigint(20) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `postion` varchar(50) DEFAULT NULL COMMENT '岗位（POS_1,POS_2）',
  `is_manager` int(2) DEFAULT NULL COMMENT '是否管理员',
  `is_creator` int(2) DEFAULT NULL COMMENT '是否创建者',
  `status` int(11) DEFAULT NULL COMMENT '成员状态',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目成员表';


-- --------------------------
-- 创建表 cts_project
-- --------------------------
DROP TABLE IF EXISTS `cts_project`;
CREATE TABLE `cts_project` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `code` varchar(50) DEFAULT NULL COMMENT '项目代码',
  `type` varchar(50) DEFAULT NULL COMMENT '项目类型',
  `project_amount` double DEFAULT NULL COMMENT '项目金额',
  `project_area` varchar(50) DEFAULT NULL COMMENT '项目区域(省市区)',
  `address` varchar(50) DEFAULT NULL COMMENT '项目地址',
  `start_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '项目开始时间',
  `end_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '项目结束时间',
  `status` int(11) DEFAULT NULL COMMENT '项目状态',
  `team_id` bigint(20) NOT NULL COMMENT '施工团队ID',
  `link_id` bigint(20) NOT NULL COMMENT 'ID',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '项目创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目信息表';


-- --------------------------
-- 创建表 cts_project_param
-- --------------------------
DROP TABLE IF EXISTS `cts_project_param`;
CREATE TABLE `cts_project_param` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `working` double DEFAULT NULL COMMENT '上班标准',
  `overtime` double DEFAULT NULL COMMENT '加班标准',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目参数表';


-- --------------------------
-- 创建表 cts_project_statistic
-- --------------------------
DROP TABLE IF EXISTS `cts_project_statistic`;
CREATE TABLE `cts_project_statistic` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `group_number` int(11) DEFAULT NULL COMMENT '班组数',
  `worker_number` int(11) DEFAULT NULL COMMENT '工人数',
  `total_worktime` double DEFAULT NULL COMMENT '总工时',
  `total_salary` double DEFAULT NULL COMMENT '项目总工资',
  `workingtime` double DEFAULT NULL COMMENT '上班工时',
  `overtime` double DEFAULT NULL COMMENT '加班工时',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目统计信息表';


-- --------------------------
-- 创建表 ggb_worker_attendance
-- --------------------------
DROP TABLE IF EXISTS `ggb_worker_attendance`;
CREATE TABLE `ggb_worker_attendance` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `group_id` bigint(20) NOT NULL COMMENT '班组ID',
  `worker_id` bigint(20) NOT NULL COMMENT '工人ID',
  `check_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '考勤时间',
  `mode` varchar(50) DEFAULT NULL COMMENT '考勤方式',
  `member_id` bigint(20) NOT NULL COMMENT '考勤人ID',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='考勤明细表';


-- --------------------------
-- 创建表 ggb_worker_worktime
-- --------------------------
DROP TABLE IF EXISTS `ggb_worker_worktime`;
CREATE TABLE `ggb_worker_worktime` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `group_id` bigint(20) NOT NULL COMMENT '班组ID',
  `worker_id` bigint(20) NOT NULL COMMENT '工人ID',
  `work_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记工日期（yyyy-MM-dd）',
  `work_month` varchar(50) DEFAULT NULL COMMENT '记工月份（yyyy-MM）',
  `fornoon_worktime` double DEFAULT NULL COMMENT '上午工时（小时）',
  `afternoon_worktime` double DEFAULT NULL COMMENT '下午工时（小时）',
  `overtime_worktime` double DEFAULT NULL COMMENT '加班工时(小时)',
  `total_worktime` double DEFAULT NULL COMMENT '总工时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工时明细表';


-- --------------------------
-- 创建表 cts_project_group
-- --------------------------
DROP TABLE IF EXISTS `cts_project_group`;
CREATE TABLE `cts_project_group` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `name` varchar(50) DEFAULT NULL COMMENT '班组名称',
  `profession` varchar(50) DEFAULT NULL COMMENT '专业',
  `type` varchar(50) DEFAULT NULL COMMENT '班组类型',
  `leader_name` varchar(50) DEFAULT NULL COMMENT '领班名称',
  `enter_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '进场日期（yyyy-MM-dd）',
  `leader_id` bigint(20) NOT NULL COMMENT '领班ID',
  `link_id` bigint(20) NOT NULL COMMENT '项目方关联班组ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目班组信息表';


-- --------------------------
-- 创建表 cts_project_group_worker
-- --------------------------
DROP TABLE IF EXISTS `cts_project_group_worker`;
CREATE TABLE `cts_project_group_worker` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `group_id` bigint(20) NOT NULL COMMENT '班组ID',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `join_date` varchar(50) DEFAULT NULL COMMENT '入职日期',
  `join_time` varchar(50) DEFAULT NULL COMMENT '入职时间',
  `leave_date` varchar(50) DEFAULT NULL COMMENT '离职日期',
  `leave_time` varchar(50) DEFAULT NULL COMMENT '离职时间',
  `entry_status` double DEFAULT NULL COMMENT '进场状态',
  `position` varchar(50) DEFAULT NULL COMMENT '岗位（成员/领班）',
  `inser_status` varchar(50) DEFAULT NULL COMMENT '保险状态',
  `security_status` varchar(50) DEFAULT NULL COMMENT '安全教育状态',
  `disclosure_status` varchar(50) DEFAULT NULL COMMENT '技术交底状态',
  `link_id` bigint(20) NOT NULL COMMENT '关联ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班组工人信息表';


-- --------------------------
-- 创建表 cts_project_worker_statistic
-- --------------------------
DROP TABLE IF EXISTS `cts_project_worker_statistic`;
CREATE TABLE `cts_project_worker_statistic` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `worker_id` bigint(20) NOT NULL COMMENT '工人ID',
  `confirmed_worktime` double DEFAULT NULL COMMENT '确认工时',
  `unconfirmed_worktime` double DEFAULT NULL COMMENT '未确认工时',
  `working_worktime` double DEFAULT NULL COMMENT '上班总工时(小时)',
  `overtime_worktime` double DEFAULT NULL COMMENT '加班总工时(小时)',
  `total_worktime` double DEFAULT NULL COMMENT '总工时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目工人统计表';


-- --------------------------
-- 创建表 ggb_group_statistic
-- --------------------------
DROP TABLE IF EXISTS `ggb_group_statistic`;
CREATE TABLE `ggb_group_statistic` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `group_id` bigint(20) NOT NULL COMMENT '工人ID',
  `worker_count` int(11) DEFAULT NULL COMMENT '在岗人数',
  `total_salary` double DEFAULT NULL COMMENT '班组总工资',
  `confirmed_worktime` double DEFAULT NULL COMMENT '已确认总工时',
  `unconfirmed_worktime` double DEFAULT NULL COMMENT '未确认总工时',
  `total_worktime` double DEFAULT NULL COMMENT '总工时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班组统计表';


-- --------------------------
-- 创建表 ggb_group_param
-- --------------------------
DROP TABLE IF EXISTS `ggb_group_param`;
CREATE TABLE `ggb_group_param` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `group_id` bigint(20) NOT NULL COMMENT '班组ID',
  `working` double DEFAULT NULL COMMENT '上班标准',
  `overtime` double DEFAULT NULL COMMENT '加班标准',
  `check_type` varchar(50) DEFAULT NULL COMMENT '考勤方式',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班组参数信息表';


-- --------------------------
-- 创建表 cts_team_statistic
-- --------------------------
DROP TABLE IF EXISTS `cts_team_statistic`;
CREATE TABLE `cts_team_statistic` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `team_id` bigint(20) NOT NULL COMMENT '班组ID',
  `worker_count` int(11) DEFAULT NULL COMMENT '在岗人数',
  `group_count` int(11) DEFAULT NULL COMMENT '总班组数',
  `confirmed_worktime` double DEFAULT NULL COMMENT '已确认总工时',
  `unconfirmed_worktime` double DEFAULT NULL COMMENT '未确认总工时',
  `total_worktime` double DEFAULT NULL COMMENT '总工时',
  `total_salary` double DEFAULT NULL COMMENT '总工资',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团队统计表';


-- --------------------------
-- 创建表 cts_worker_statistic
-- --------------------------
DROP TABLE IF EXISTS `cts_worker_statistic`;
CREATE TABLE `cts_worker_statistic` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `worker_id` bigint(20) NOT NULL COMMENT '工人ID',
  `confirmed_worktime` double DEFAULT NULL COMMENT '已确认总工时',
  `unconfirmed_worktime` double DEFAULT NULL COMMENT '未确认总工时',
  `working_worktime` double DEFAULT NULL COMMENT '上班总工时(小时)',
  `overtime_worktime` double DEFAULT NULL COMMENT '加班总工时(小时)',
  `total_worktime` double DEFAULT NULL COMMENT '总工时',
  `total_salary` double DEFAULT NULL COMMENT '总工资',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工人信息汇总表';


-- --------------------------
-- 创建表 cts_worker
-- --------------------------
DROP TABLE IF EXISTS `cts_worker`;
CREATE TABLE `cts_worker` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `team_id` bigint(20) NOT NULL COMMENT '班组ID',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `join_date` varchar(50) DEFAULT NULL COMMENT '入职日期',
  `join_time` varchar(50) DEFAULT NULL COMMENT '入职时间',
  `leave_date` varchar(50) DEFAULT NULL COMMENT '离职日期',
  `leave_time` varchar(50) DEFAULT NULL COMMENT '离职时间',
  `salary_type` varchar(50) DEFAULT NULL COMMENT'薪资类型',
  `salary` double DEFAULT NULL COMMENT '工价',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `work_status` double DEFAULT NULL COMMENT '工作状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工人信息表';


-- --------------------------
-- 创建表 cts_worker_param
-- --------------------------
DROP TABLE IF EXISTS `cts_worker_param`;
CREATE TABLE `cts_worker_param` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `worker_id` bigint(20) NOT NULL COMMENT '班组ID',
  `current_project_id` bigint(20) NOT NULL COMMENT '施工项目ID',
  `current_group_id` bigint(20) NOT NULL COMMENT '施工班组ID',
  `current_worker_id` bigint(20) NOT NULL COMMENT '施工工人ID',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工人参数信息表';


-- -----------------------
-- 创建表 cts_worker_account
-- -----------------------
DROP TABLE IF EXISTS `cts_worker_account`;
CREATE TABLE `cts_worker_account` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `worker_id` bigint(20) NOT NULL COMMENT '工人ID',
  `real_name` bigint(20) DEFAULT NULL COMMENT '真是姓名',
  `status` int(11) DEFAULT NULL COMMENT '实名状态',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `birthday` varchar(50) DEFAULT NULL COMMENT '出生日期',
  `ethnic` varchar(50) DEFAULT NULL COMMENT '民族',
  `age` double DEFAULT NULL COMMENT '年龄',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `idCardSideFace` varchar(50) DEFAULT NULL COMMENT '身份证正面',
  `idCardSideBack` varchar(50) DEFAULT NULL COMMENT '身份证反面',
  `bank_card_no` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工人账户表';


-- -----------------------
-- 创建表 cts_team
-- -----------------------
DROP TABLE IF EXISTS `cts_team`;
CREATE TABLE `cts_team` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '团队名称',
  `type` varchar(50) DEFAULT NULL COMMENT '团队类型',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '团队创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团队信息表';
