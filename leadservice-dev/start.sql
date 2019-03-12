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


DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `dept` varchar(20) DEFAULT NULL COMMENT '部门',
  `post` varchar(20) DEFAULT NULL COMMENT '职务',
  `no` varchar(20) DEFAULT NULL COMMENT '编号',
  `CreatBy` varchar(20) DEFAULT NULL COMMENT '创建人',
  `LastEditBy` varchar(20) DEFAULT NULL COMMENT '职务',
  `LastEditDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最好修改时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LoginName` varchar(255) NOT NULL COMMENT '登录名',
  `LoginPWD` varchar(255) NOT NULL COMMENT '登录密码',
  `CreateUID` int(11) NOT NULL COMMENT '创建人ID 外键#sys_user#',
  `CreateDate` datetime NOT NULL COMMENT '创建时间',
  `UpdateDate` datetime DEFAULT NULL COMMENT '更新时间',
  `LoginIP` varchar(255) DEFAULT NULL COMMENT '登录IP',
  `LoginDate` datetime DEFAULT NULL COMMENT '登录时间',
  `LastLoginIP` varchar(255) DEFAULT NULL COMMENT '最后登陆IP',
  `LastLoginDate` datetime DEFAULT NULL COMMENT '最后登录时间',
  `UserType` int(255) DEFAULT NULL COMMENT '默认用户类型',
  `UserName` varchar(255) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';



DROP TABLE IF EXISTS `msg_group_user`;
CREATE TABLE `msg_group_user` (
   `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '组ID',
  `GUID` char(36) DEFAULT NULL,
  `GroupName` varchar(255) DEFAULT NULL COMMENT '组名称',
  `CreateUID` int(11) DEFAULT NULL COMMENT '创建人ID 外键#sys_user#',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  `UpdateUID` int(11) DEFAULT NULL COMMENT '修改人 外键#sys_user#',
  `UpdateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `DeleteUID` int(11) DEFAULT NULL COMMENT '删除人ID 外键#sys_user#',
  `DeleteDate` datetime DEFAULT NULL COMMENT '删除时间',
  `IsDelete` int(11) NOT NULL COMMENT '是否删除，数据字典#0003#',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';



CREATE TABLE `msg_user_relation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL COMMENT '用户ID 外键#sys_user#',
  `GroupID` int(11) NOT NULL COMMENT '组ID 外键#msg_group_user#',
  PRIMARY KEY (`ID`)
)



CREATE TABLE `msg_user_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL COMMENT '用户ID',
  `IsShowSystemMessage` int(11) DEFAULT NULL COMMENT '是否显示系统消息',
  PRIMARY KEY (`ID`)
)


CREATE TABLE `msg_mail_box` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `GUID` char(36) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL COMMENT '标题',
  `Content` text COMMENT '正文',
  `IsMsgRemind` int(1) NOT NULL COMMENT '是否短信提醒，数据字典#0003#',
  `MailTiming` datetime DEFAULT NULL COMMENT '定时发送',
  `IsSend` int(11) DEFAULT NULL COMMENT '是否发送，数据字典#0003#',
  `SendDate` datetime DEFAULT NULL COMMENT '消息发送时间',
  `CreateUID` int(11) DEFAULT NULL COMMENT '创建人ID 外键#sys_user#',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  `UpdateUID` int(11) DEFAULT NULL COMMENT '修改人ID 外键#sys_user#',
  `UpdateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `DeleteUID` int(11) DEFAULT NULL COMMENT '删除人ID 外键#sys_user#',
  `DeleteDate` datetime DEFAULT NULL COMMENT '删除时间',
  `IsDelete` int(11) NOT NULL COMMENT '是否删除，数据字典#0003#',
  `IsCompel` int(11) DEFAULT NULL COMMENT '是否是强制消息',
  PRIMARY KEY (`ID`),
  KEY `CreateUID` (`CreateUID`),
  KEY `IsSend` (`IsSend`),
  KEY `IsDelete` (`IsDelete`)
)


CREATE TABLE `msg_mail_relation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MailID` int(11) DEFAULT NULL COMMENT '消息ID 外键#msg_mail_box#',
  `UserID` int(11) DEFAULT NULL COMMENT '用户ID 外键#sys_user#',
  `IsRead` int(11) DEFAULT NULL COMMENT '是否阅读，数据字典#0003#',
  `ReadDate` datetime DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`ID`),
  KEY `MailID` (`MailID`),
  KEY `IsRead` (`IsRead`),
  KEY `UserID` (`UserID`)
)