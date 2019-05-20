/*
Navicat MySQL Data Transfer

Source Server         : 授课小程序
Source Server Version : 50642
Source Host           : 129.204.144.82:3306
Source Database       : teach

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-01-21 15:59:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `name` varchar(45) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `area` varchar(45) DEFAULT NULL COMMENT '所在地区(省市区)',
  `address` varchar(45) DEFAULT NULL COMMENT '详细地址',
  `defaulting` varchar(1) DEFAULT '0' COMMENT '是否为默认地址（0否，1是）',
  PRIMARY KEY (`id`),
  KEY `fk_address_student1_idx` (`student_id`) USING BTREE,
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='学生的收货地址表';

-- ----------------------------
-- Table structure for application_parameter
-- ----------------------------
DROP TABLE IF EXISTS `application_parameter`;
CREATE TABLE `application_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(45) DEFAULT NULL COMMENT '参数名',
  `value` varchar(45) DEFAULT NULL COMMENT '参数值',
  `remark` varchar(255) DEFAULT NULL COMMENT '参数说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统参数设置表';

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `name` varchar(45) DEFAULT NULL COMMENT '学生真实姓名',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名',
  `start_time` date DEFAULT NULL COMMENT '优惠券使用开始时间',
  `end_time` date DEFAULT NULL COMMENT '有效时间',
  `coupon_name` varchar(255) DEFAULT NULL COMMENT '优惠券名称',
  `satisfy` decimal(11,2) DEFAULT NULL COMMENT '满足条件金额',
  `discount` decimal(11,2) DEFAULT NULL COMMENT '折扣额度',
  `coupon_type` int(11) DEFAULT NULL COMMENT '优惠券类型，0答疑，1课程',
  `status` int(11) DEFAULT '0' COMMENT '0未使用1已使用',
  `identification` varchar(500) DEFAULT NULL COMMENT '优惠券标识，用于分组',
  PRIMARY KEY (`id`),
  KEY `fk_coupon_student1_idx` (`student_id`) USING BTREE,
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='优惠券';

-- ----------------------------
-- Table structure for coupon_group
-- ----------------------------
DROP TABLE IF EXISTS `coupon_group`;
CREATE TABLE `coupon_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `start_time` date DEFAULT NULL COMMENT '优惠券使用开始时间',
  `end_time` date DEFAULT NULL COMMENT '有效时间',
  `coupon_name` varchar(500) DEFAULT NULL COMMENT '优惠券名称',
  `satisfy` decimal(11,2) DEFAULT NULL,
  `discount` decimal(11,2) DEFAULT NULL,
  `coupon_type` int(1) DEFAULT NULL COMMENT '优惠券类型，0答疑，1课程',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='优惠券组';

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `teacher_id` int(11) NOT NULL COMMENT '老师id',
  `name` varchar(45) DEFAULT NULL COMMENT '课程名',
  `grade` varchar(45) DEFAULT NULL COMMENT '年级',
  `subject` varchar(45) DEFAULT NULL COMMENT '科目',
  `introduce` text COMMENT '简介',
  `status` int(11) DEFAULT '0' COMMENT '状态(0有效  1无效)',
  `course_type` varchar(50) DEFAULT NULL COMMENT '科目类型（小学 初中  高中）',
  `is_show` int(3) DEFAULT '0' COMMENT '是否显示（0显示  1不显示）',
  `live_status` int(11) DEFAULT '0' COMMENT '是否正在直播（0否，1是）',
  `is_finish` int(2) DEFAULT '0' COMMENT '老师上完课依据（0否，1是），课程所有类型及章节是否完成',
  `level` varchar(50) DEFAULT NULL COMMENT '级别（小学 初中  高中）',
  PRIMARY KEY (`id`),
  KEY `fk_course_teacher1_idx` (`teacher_id`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='课程表';

-- ----------------------------
-- Table structure for course_comment
-- ----------------------------
DROP TABLE IF EXISTS `course_comment`;
CREATE TABLE `course_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `picture` varchar(500) DEFAULT NULL COMMENT '学生头像',
  `name` varchar(45) DEFAULT NULL COMMENT '学生姓名',
  `type` int(11) DEFAULT NULL COMMENT '评论类型 （0好评1中评2差评）',
  `start` int(11) DEFAULT NULL COMMENT '几个星星',
  `comment` varchar(500) DEFAULT NULL COMMENT '评价内容',
  `order_course_id` int(11) DEFAULT NULL COMMENT '课程订单id',
  PRIMARY KEY (`id`),
  KEY `fk_course_comment_course1_idx` (`course_id`) USING BTREE,
  CONSTRAINT `course_comment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='课程评论表';

-- ----------------------------
-- Table structure for course_outline
-- ----------------------------
DROP TABLE IF EXISTS `course_outline`;
CREATE TABLE `course_outline` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `course_type_id` int(11) NOT NULL COMMENT '课程类型id',
  `start_time` datetime DEFAULT NULL COMMENT '上课开始时间',
  `title` varchar(45) DEFAULT NULL COMMENT '标题',
  `introduce` varchar(45) DEFAULT NULL COMMENT '介绍',
  `status` int(11) DEFAULT '0' COMMENT '是否已上，0未上课  1已上课 2上课中',
  `rank` int(11) DEFAULT NULL COMMENT '排序号',
  `video_url` varchar(1024) DEFAULT NULL COMMENT '回看视频地址',
  `channel_no` varchar(255) DEFAULT NULL COMMENT '频道号',
  PRIMARY KEY (`id`),
  KEY `fk_course_outline_course_type1_idx` (`course_type_id`) USING BTREE,
  CONSTRAINT `course_outline_ibfk_1` FOREIGN KEY (`course_type_id`) REFERENCES `course_type` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='课程大纲';

-- ----------------------------
-- Table structure for course_type
-- ----------------------------
DROP TABLE IF EXISTS `course_type`;
CREATE TABLE `course_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `course_type` int(11) DEFAULT '0' COMMENT '课程类型（0 一对一，1小班课，2大班课）',
  `orig` decimal(11,2) DEFAULT NULL COMMENT '原价，缩写',
  `discount` decimal(11,2) DEFAULT NULL COMMENT '优惠价',
  `periods` varchar(45) DEFAULT NULL COMMENT '课时',
  `duration` int(11) DEFAULT NULL COMMENT '课程时长，分钟',
  `start_time` datetime DEFAULT NULL COMMENT '开班时间',
  `course_time` varchar(45) DEFAULT NULL COMMENT '上课时间',
  `quantity` int(11) DEFAULT '10' COMMENT '可报名总人数（对于课程类型）',
  `enrolment` int(11) DEFAULT '0' COMMENT '已报名总人数',
  `class_frequency` varchar(255) DEFAULT NULL COMMENT '上课频率（每天：d；每周1,2,5：w_1,2,5）',
  `is_finish` int(1) DEFAULT '0' COMMENT '学生上完课依据（0否，1是），是否完成该课程类型下所有章节',
  `commission` decimal(10,2) DEFAULT '0.00' COMMENT '老师提成',
  PRIMARY KEY (`id`),
  KEY `fk_course_type_course1_idx` (`course_id`) USING BTREE,
  CONSTRAINT `course_type_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8 COMMENT='课程类型表，即大班课小班课一对一等课程';

-- ----------------------------
-- Table structure for finance
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `student_name` varchar(45) DEFAULT NULL COMMENT '学生姓名',
  `teacher_name` varchar(45) DEFAULT NULL COMMENT '老师姓名',
  `student_pay` decimal(11,2) DEFAULT NULL COMMENT '学生支付',
  `platform_income` decimal(11,2) DEFAULT NULL COMMENT '平台收益',
  `teacher_income` decimal(11,2) DEFAULT NULL COMMENT '老师收益',
  `teacher_bonus_ratio` decimal(11,2) DEFAULT NULL COMMENT '老师提成比例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8 COMMENT='系统的财务管理';

-- ----------------------------
-- Table structure for finance_student
-- ----------------------------
DROP TABLE IF EXISTS `finance_student`;
CREATE TABLE `finance_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `type` int(11) DEFAULT NULL COMMENT '0支出1充值',
  `name` varchar(45) DEFAULT NULL COMMENT '明细名称',
  `money` decimal(11,2) DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`id`),
  KEY `fk_finance_student_student1_idx` (`student_id`) USING BTREE,
  CONSTRAINT `finance_student_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1548052635 DEFAULT CHARSET=utf8 COMMENT='学生的账户费用明细';

-- ----------------------------
-- Table structure for finance_teacher
-- ----------------------------
DROP TABLE IF EXISTS `finance_teacher`;
CREATE TABLE `finance_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `teacher_id` int(11) NOT NULL COMMENT '老师id',
  `type` int(11) DEFAULT NULL COMMENT '0 收入 1提现',
  `name` varchar(45) DEFAULT NULL COMMENT '明细名称',
  `money` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `is_finish` varchar(50) DEFAULT NULL COMMENT '是否完成提现(0未完成1完成)',
  PRIMARY KEY (`id`),
  KEY `fk_finance_teacher_teacher1_idx` (`teacher_id`) USING BTREE,
  CONSTRAINT `finance_teacher_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8 COMMENT='老师的账户费用明细';

-- ----------------------------
-- Table structure for marketing_banner
-- ----------------------------
DROP TABLE IF EXISTS `marketing_banner`;
CREATE TABLE `marketing_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程名',
  `url` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '资料链接',
  `content` text CHARACTER SET utf8 COMMENT '内容',
  `status` int(11) DEFAULT '0' COMMENT '状态,0上架  1下架',
  `number` int(11) DEFAULT NULL COMMENT '观看人数',
  `cover_map` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '轮播图',
  `is_href` int(3) DEFAULT '0' COMMENT '是否是跳链接（0否  1是）',
  `material_type` int(10) DEFAULT NULL COMMENT '素材类型(0:轮播 1:视频)',
  `datum` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '视频地址',
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '发布人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COMMENT='营销素材-学生端首页';

-- ----------------------------
-- Table structure for marketing_course
-- ----------------------------
DROP TABLE IF EXISTS `marketing_course`;
CREATE TABLE `marketing_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程名',
  `type` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程类型，直接存语文数学等',
  `url` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '资料链接',
  `content` text CHARACTER SET utf8 COMMENT '内容',
  `status` int(11) DEFAULT NULL COMMENT '状态（0上架  1下架）',
  `number` int(11) DEFAULT NULL COMMENT '观看人数',
  `cover_map` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '封面图',
  `is_href` int(3) DEFAULT NULL COMMENT '是否是跳链接（0否  1是）',
  `datum` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '答疑资料地址',
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '发布人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='营销素材-课程';

-- ----------------------------
-- Table structure for marketing_question
-- ----------------------------
DROP TABLE IF EXISTS `marketing_question`;
CREATE TABLE `marketing_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程名',
  `type` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程类型，直接存语文数学等',
  `url` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '资料链接',
  `content` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '内容',
  `status` int(11) DEFAULT NULL COMMENT '状态  0上架 1下架',
  `number` int(11) DEFAULT NULL COMMENT '观看人数',
  `cover_map` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '封面图',
  `is_href` int(3) DEFAULT NULL COMMENT '是否是跳链接（0否  1是）',
  `datum` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '答疑资料地址',
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '发布人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='营销素材-难题';

-- ----------------------------
-- Table structure for message_student
-- ----------------------------
DROP TABLE IF EXISTS `message_student`;
CREATE TABLE `message_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `title` varchar(45) DEFAULT NULL COMMENT '标题',
  `comment` varchar(45) DEFAULT NULL COMMENT '消息内容',
  `status` int(11) DEFAULT '0' COMMENT '是否已读(0未读  1已读)',
  PRIMARY KEY (`id`),
  KEY `fk_message_student_student1_idx` (`student_id`) USING BTREE,
  CONSTRAINT `message_student_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=526 DEFAULT CHARSET=utf8 COMMENT='学生的消息';

-- ----------------------------
-- Table structure for message_teacher
-- ----------------------------
DROP TABLE IF EXISTS `message_teacher`;
CREATE TABLE `message_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `teacher_id` int(11) NOT NULL COMMENT '老师id',
  `title` varchar(45) DEFAULT NULL COMMENT '消息标题',
  `comment` varchar(45) DEFAULT NULL COMMENT '消息内容',
  `status` int(11) DEFAULT NULL COMMENT '是否已读（0否  1是）',
  PRIMARY KEY (`id`),
  KEY `fk_message_teacher_teacher1_idx` (`teacher_id`) USING BTREE,
  CONSTRAINT `message_teacher_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='老师的消息';

-- ----------------------------
-- Table structure for order_course
-- ----------------------------
DROP TABLE IF EXISTS `order_course`;
CREATE TABLE `order_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `student_name` varchar(45) DEFAULT NULL COMMENT '学生姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别（0男 1女）',
  `address` varchar(45) DEFAULT NULL COMMENT '地址',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `orig` decimal(11,2) DEFAULT NULL COMMENT '原价，缩写',
  `discount` decimal(11,2) DEFAULT NULL COMMENT '优惠价',
  `periods` varchar(45) DEFAULT NULL COMMENT '课时',
  `duration` int(11) DEFAULT NULL COMMENT '课程时长，分钟',
  `course_time` varchar(45) DEFAULT NULL COMMENT '上课时间',
  `teacher_name` varchar(45) DEFAULT NULL COMMENT '老师姓名',
  `is_coupon` int(11) DEFAULT '0' COMMENT '是否使用优惠券（0否，1是）',
  `coupon_price` decimal(11,2) DEFAULT NULL COMMENT '优惠券金额',
  `teacher_bonus` decimal(11,2) DEFAULT NULL COMMENT '教师提成',
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `teacher_id` int(11) DEFAULT NULL COMMENT '老师id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `course_type_id` int(11) DEFAULT NULL COMMENT '课程类型id',
  `apprised` varchar(1) DEFAULT '0' COMMENT '是否评价',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '支付金额',
  `status` int(11) DEFAULT '0' COMMENT '订单状态（0创建，1支付中，2支付成功，3支付失败，4超时关闭，5取消订单，6退款）',
  `course_title` varchar(255) DEFAULT NULL COMMENT '课程标题',
  `course_info` varchar(500) DEFAULT NULL COMMENT '课程信息',
  `course_start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开课时间',
  `course_class_frequency` varchar(255) DEFAULT NULL COMMENT '上课频率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000145 DEFAULT CHARSET=utf8 COMMENT='课程订单';

-- ----------------------------
-- Table structure for order_question
-- ----------------------------
DROP TABLE IF EXISTS `order_question`;
CREATE TABLE `order_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `teacher_id` int(11) NOT NULL COMMENT '老师id',
  `student_name` varchar(45) DEFAULT NULL COMMENT '学生姓名',
  `teacher_name` varchar(45) DEFAULT NULL COMMENT '老师姓名',
  `question_img` varchar(1024) DEFAULT NULL COMMENT '问题图片',
  `status` varchar(45) DEFAULT NULL COMMENT '审核状态(0待接受 1解疑中  2待支付 3已完成  4未通过 5已取消)',
  `price` decimal(11,2) DEFAULT NULL COMMENT '价格',
  `is_coupon` int(11) DEFAULT '0' COMMENT '是否使用优惠券（0否  1是）',
  `coupon_price` decimal(11,2) DEFAULT NULL COMMENT '优惠券金额',
  `is_pay` int(3) DEFAULT '0' COMMENT '是否支付(0否 1是）',
  `pay_type` int(3) DEFAULT NULL COMMENT '支付类型(0微信 1代付 2余额支付）',
  `phone` varchar(255) DEFAULT NULL COMMENT '收货手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '收货详细地址',
  `area` varchar(255) DEFAULT NULL COMMENT '收货区域  （省市区）',
  `answering_time` varchar(50) DEFAULT NULL COMMENT '答疑时长(分)',
  `apprised` varchar(1) DEFAULT '0' COMMENT '是否已评论（0否，1是）',
  `true_price` decimal(10,2) DEFAULT NULL COMMENT '真实价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50000380 DEFAULT CHARSET=utf8 COMMENT='疑难解答订单';

-- ----------------------------
-- Table structure for order_recharge
-- ----------------------------
DROP TABLE IF EXISTS `order_recharge`;
CREATE TABLE `order_recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `amount` decimal(11,2) NOT NULL COMMENT '充值金额',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '充值状态（0预支付，1支付中，2支付成功，3支付失败，4超时关闭，5取消订单，6退款）',
  `payment` varchar(1) CHARACTER SET latin1 NOT NULL COMMENT '支付方式（0自己支付，1好友代付）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90000036 DEFAULT CHARSET=utf8 COMMENT='学生充值订单';

-- ----------------------------
-- Table structure for order_student
-- ----------------------------
DROP TABLE IF EXISTS `order_student`;
CREATE TABLE `order_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间s',
  `student_name` varchar(45) DEFAULT NULL COMMENT '学生姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别（0男 1女  2保密）',
  `address` varchar(45) DEFAULT NULL COMMENT '地址',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COMMENT='学生订单';

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(190) DEFAULT NULL,
  `JOB_GROUP` varchar(190) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question_comment
-- ----------------------------
DROP TABLE IF EXISTS `question_comment`;
CREATE TABLE `question_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `teacher_id` int(11) NOT NULL COMMENT '老师id',
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `name` varchar(45) DEFAULT NULL COMMENT '学生姓名',
  `type` int(11) DEFAULT NULL COMMENT '0好评1中评2差评',
  `star` int(11) DEFAULT NULL COMMENT '几个星星',
  `comment` varchar(45) DEFAULT NULL COMMENT '评价内容',
  `picture` varchar(500) DEFAULT NULL COMMENT '学生头像',
  `order_id` int(11) DEFAULT NULL COMMENT '解疑订单id',
  `question_img` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_teacher_comment_teacher1_idx` (`teacher_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='解疑评论表';

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `question` varchar(45) DEFAULT NULL COMMENT '问题标题，固定八个题目',
  `is_show` varchar(10) DEFAULT '0' COMMENT '是否显示（0显示 1不显示）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='问卷题目表';

-- ----------------------------
-- Table structure for questionnaire_colletion
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire_colletion`;
CREATE TABLE `questionnaire_colletion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `account` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '账号',
  `satisfied` int(11) DEFAULT NULL COMMENT '满意度(0-3不满意，一般，满意，非常满意)',
  `question1` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '八个问题，八个回答',
  `answer1` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `question2` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '八个问题，八个回答',
  `answer2` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `question3` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '八个问题，八个回答',
  `answer3` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `question4` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '八个问题，八个回答',
  `answer4` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `question5` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '八个问题，八个回答',
  `answer5` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `question6` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '八个问题，八个回答',
  `answer6` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `question7` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '八个问题，八个回答',
  `answer7` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `question8` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '八个问题，八个回答',
  `answer8` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `advise` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '建议内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COMMENT='问卷情况汇总表';

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `menu_id` varchar(50) DEFAULT NULL COMMENT '菜单id',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_role_id` (`role_id`) USING BTREE,
  KEY `FK_menu_id` (`menu_id`) USING BTREE,
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`) ON DELETE CASCADE,
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`roleId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(45) DEFAULT NULL COMMENT '真实姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别（0男 1女  2保密）',
  `region` varchar(45) DEFAULT NULL COMMENT '地区（省市区）',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `grade` varchar(45) DEFAULT NULL COMMENT '年级，直接存一年级二年级',
  `picture` varchar(500) DEFAULT NULL COMMENT '头像路径',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名 ',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `balance` decimal(11,2) DEFAULT '0.00' COMMENT '余额',
  `status` int(11) DEFAULT '0' COMMENT '用户状态 （0启用 1禁用）',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证',
  `open_id` varchar(255) DEFAULT NULL COMMENT '微信用户id',
  `pin` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `hasPin` varchar(1) DEFAULT '0' COMMENT '是否设置支付密码（0否，1是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='学生表';

-- ----------------------------
-- Table structure for student_has_course_type
-- ----------------------------
DROP TABLE IF EXISTS `student_has_course_type`;
CREATE TABLE `student_has_course_type` (
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `course_type_id` int(11) NOT NULL COMMENT '课程类型id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`student_id`,`course_type_id`),
  KEY `fk_student_has_course_type_course_type1_idx` (`course_type_id`) USING BTREE,
  KEY `fk_student_has_course_type_student1_idx` (`student_id`) USING BTREE,
  CONSTRAINT `student_has_course_type_ibfk_1` FOREIGN KEY (`course_type_id`) REFERENCES `course_type` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `student_has_course_type_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生订阅课程表';

-- ----------------------------
-- Table structure for student_has_teacher
-- ----------------------------
DROP TABLE IF EXISTS `student_has_teacher`;
CREATE TABLE `student_has_teacher` (
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `teacher_id` int(11) NOT NULL COMMENT '老师id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`student_id`,`teacher_id`),
  KEY `fk_student_has_teacher_teacher1_idx` (`teacher_id`) USING BTREE,
  KEY `fk_student_has_teacher_student_idx` (`student_id`) USING BTREE,
  CONSTRAINT `student_has_teacher_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_has_teacher_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生关注老师表';

-- ----------------------------
-- Table structure for sys_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_data`;
CREATE TABLE `sys_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数名',
  `type` int(3) DEFAULT NULL COMMENT '类型（0 学历）',
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `logid` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operate_name` varchar(50) DEFAULT NULL COMMENT '操作名称',
  `operate_url` varchar(255) DEFAULT NULL COMMENT '操作地址',
  `token` varchar(5000) DEFAULT NULL COMMENT '操作人token',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `port` varchar(50) DEFAULT NULL COMMENT '端口',
  `customer_type` varchar(50) DEFAULT NULL COMMENT '客户端类型(0pc1android 2ios)',
  `type` varchar(50) DEFAULT '1' COMMENT '类型（1订单操作 2基础信息操作  3用户操作）',
  `create_time` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `lose_status` varchar(50) DEFAULT '0' COMMENT '过期状态（0有效1失效）',
  `op_type` varchar(50) DEFAULT '1' COMMENT '操作类型（1新增 2修改 3删除 4确认 5取消 6审核 7取消审核 8启用  9禁用）',
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `href` varchar(255) DEFAULT NULL COMMENT '访问地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `rank` varchar(50) DEFAULT NULL COMMENT '排序号',
  `type` varchar(50) DEFAULT '0' COMMENT '类型（0功能菜单1一级菜单2二级菜单）',
  `state` varchar(50) DEFAULT '0' COMMENT '有效状态（0无效1有效）',
  `pId` varchar(50) DEFAULT NULL COMMENT '父id',
  `crate_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能权限表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` varchar(50) NOT NULL COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `role_mark` varchar(50) DEFAULT NULL COMMENT '角色唯一标识',
  `state` varchar(50) DEFAULT '0' COMMENT '角色状态(0有效1无效)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(50) NOT NULL COMMENT '主键',
  `user_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `true_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(50) DEFAULT '0' COMMENT '性别(0男1女2保密)',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `state` varchar(50) DEFAULT '0' COMMENT '用户状态(0有效  1无效)',
  `type` varchar(50) DEFAULT '1' COMMENT '用户类型(0前台用户1后台用户)',
  `picture_path` varchar(500) DEFAULT NULL COMMENT '头像路径',
  `address` varchar(50) DEFAULT NULL COMMENT '所在地',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `age` varchar(45) DEFAULT NULL COMMENT '年龄',
  `teachercol` varchar(45) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL COMMENT '性别（0男 1女  2保密）',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `grade` varchar(45) DEFAULT NULL COMMENT '年级',
  `subject` varchar(45) DEFAULT NULL COMMENT '科目',
  `level` varchar(11) DEFAULT NULL COMMENT '级别',
  `experience` int(11) DEFAULT NULL COMMENT '授课年龄',
  `id_card` varchar(45) DEFAULT NULL COMMENT '身份证号',
  `picture` varchar(255) DEFAULT NULL COMMENT '头像',
  `card_img` varchar(1024) DEFAULT NULL COMMENT '教师资格证',
  `examine` int(11) DEFAULT NULL COMMENT '审核状态，0待完善资料 1待审核2通过3不通过',
  `reason` varchar(45) DEFAULT NULL COMMENT '不通过理由',
  `region` varchar(45) DEFAULT NULL COMMENT '所属区域（省市区）',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `star_count` decimal(10,0) DEFAULT '5' COMMENT '总得评分数，用于计算显示教师的总评分',
  `star_times` int(11) DEFAULT '1' COMMENT '总被评分次数，用于计算显示教师的总评分',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `balance` decimal(11,2) DEFAULT '0.00' COMMENT '余额',
  `status` int(3) DEFAULT '0' COMMENT '状态（0启用 1禁用）',
  `teaching_experience` varchar(500) DEFAULT NULL COMMENT '教学经历',
  `certificate_of_honor` varchar(500) DEFAULT NULL COMMENT '荣誉证书',
  `introduce` varchar(500) DEFAULT NULL COMMENT '个人介绍',
  `is_show` int(3) DEFAULT NULL COMMENT '是否显示（0显示  1不显示）',
  `charges` int(11) NOT NULL DEFAULT '0' COMMENT '收费百分比',
  `charge_settings` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '超时收费设置（元/分钟）',
  `open_id` varchar(255) DEFAULT NULL COMMENT '微信用户id',
  `live_room` varchar(255) DEFAULT NULL COMMENT '直播房间号',
  `pay_pwd` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `starting_price` decimal(10,2) DEFAULT '0.00' COMMENT '起步价',
  `is_online` varchar(255) DEFAULT '0' COMMENT '疑难是否在线(0否1解疑中2是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='老师表';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `FK_role_ids` (`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`roleId`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
SET FOREIGN_KEY_CHECKS=1;
