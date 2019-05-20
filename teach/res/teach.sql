/*
Navicat MySQL Data Transfer

Source Server         : 授课小程序
Source Server Version : 50642
Source Host           : 129.204.144.82:3306
Source Database       : teach

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-01-21 15:59:14
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
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('20', '2019-01-18 11:27:40', null, '17', '发过火', '13568855455', '北京市/东城区', '废话姐姐', '1');
INSERT INTO `address` VALUES ('21', '2019-01-18 11:55:26', null, '19', 'a', '15170192080', '北京市/东城区', '1', '1');
INSERT INTO `address` VALUES ('23', '2019-01-18 15:21:07', null, '21', '你想让我', '17603072607', '北京市/东城区', '哦属于什么', '1');
INSERT INTO `address` VALUES ('24', '2019-01-18 15:32:35', null, '23', 'ghh', '17841138290', '北京市/东城区', 'ggv', '0');

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
-- Records of application_parameter
-- ----------------------------
INSERT INTO `application_parameter` VALUES ('1', '2018-12-19 11:46:03', null, 'reminderBeforeClass', '5', '上课提前提醒时间');
INSERT INTO `application_parameter` VALUES ('2', '2018-12-19 11:46:38', null, 'customerServicePhone', '070-234324-34', '客服电话');
INSERT INTO `application_parameter` VALUES ('3', '2018-12-19 11:48:15', null, 'startingTime', '20', '答疑开始计费时间');
INSERT INTO `application_parameter` VALUES ('4', '2018-12-19 11:49:41', null, 'answerSwitch', '开', '开关');
INSERT INTO `application_parameter` VALUES ('5', '2018-12-19 11:49:45', null, 'introduction', '难题解疑介绍123', '难题解疑介绍');

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
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('102', '2019-01-18 13:59:31', null, '17', '长孙', 'P_18270708551', '2019-01-17', '2019-01-31', '优惠券1', '100.00', '99.00', '0', '0', null);
INSERT INTO `coupon` VALUES ('104', '2019-01-18 13:59:31', null, '19', '小浣熊', 'P_15170192080', '2019-01-17', '2019-01-31', '优惠券1', '100.00', '99.00', '0', '0', null);
INSERT INTO `coupon` VALUES ('105', '2019-01-18 14:00:34', null, '17', '长孙', 'P_18270708551', '2019-01-17', '2019-01-31', '优惠券2', '10.00', '10.00', '1', '0', null);
INSERT INTO `coupon` VALUES ('107', '2019-01-18 14:00:34', null, '19', '小浣熊', 'P_15170192080', '2019-01-17', '2019-01-31', '优惠券2', '10.00', '10.00', '1', '0', null);
INSERT INTO `coupon` VALUES ('108', '2019-01-18 17:38:52', null, '17', '长孙', 'P_18270708551', '2019-01-12', '2019-02-06', '优惠券', '30.00', '20.00', '1', '0', null);
INSERT INTO `coupon` VALUES ('109', '2019-01-18 17:38:52', null, '19', '小浣熊', 'P_15170192080', '2019-01-12', '2019-02-06', '优惠券', '30.00', '20.00', '1', '0', null);
INSERT INTO `coupon` VALUES ('110', '2019-01-18 17:38:52', null, '21', '卢祖飞', 'P_17603072607', '2019-01-12', '2019-02-06', '优惠券', '30.00', '20.00', '1', '0', null);
INSERT INTO `coupon` VALUES ('112', '2019-01-18 17:38:52', null, '23', 'hwl', 'P_17841138290', '2019-01-12', '2019-02-06', '优惠券', '30.00', '20.00', '1', '1', null);
INSERT INTO `coupon` VALUES ('113', '2019-01-19 09:53:14', null, '17', '长孙', 'P_18270708551', '2019-01-10', '2019-02-20', '优惠券', '50.00', '30.00', '0', '0', null);
INSERT INTO `coupon` VALUES ('114', '2019-01-19 09:53:14', null, '19', '小浣熊', 'P_15170192080', '2019-01-10', '2019-02-20', '优惠券', '50.00', '30.00', '0', '0', null);
INSERT INTO `coupon` VALUES ('115', '2019-01-19 09:53:14', null, '21', '卢祖飞', 'P_17603072607', '2019-01-10', '2019-02-20', '优惠券', '50.00', '30.00', '0', '0', null);
INSERT INTO `coupon` VALUES ('117', '2019-01-19 09:53:14', null, '23', 'hwl', 'P_17841138290', '2019-01-10', '2019-02-20', '优惠券', '50.00', '30.00', '0', '0', null);
INSERT INTO `coupon` VALUES ('118', '2019-01-21 09:35:45', null, '17', '长孙', 'P_18270708551', '2019-01-26', '2019-02-28', '测试', '100.00', '10.00', '0', '0', '1');
INSERT INTO `coupon` VALUES ('119', '2019-01-21 09:35:45', null, '19', '小浣熊', 'P_15170192080', '2019-01-26', '2019-02-28', '测试', '100.00', '10.00', '0', '0', '1');
INSERT INTO `coupon` VALUES ('120', '2019-01-21 09:35:45', null, '21', '卢祖飞', 'P_17603072607', '2019-01-26', '2019-02-28', '测试', '100.00', '10.00', '0', '0', '1');
INSERT INTO `coupon` VALUES ('122', '2019-01-21 09:35:45', null, '23', 'hwl', 'P_17841138290', '2019-01-26', '2019-02-28', '测试', '100.00', '10.00', '0', '0', '1');
INSERT INTO `coupon` VALUES ('128', '2019-01-21 09:45:42', null, '17', '长孙', 'P_18270708551', '2019-01-26', '2019-02-26', 'SSS', '1.00', '1.00', '1', '0', '2');
INSERT INTO `coupon` VALUES ('129', '2019-01-21 09:45:42', null, '19', '小浣熊', 'P_15170192080', '2019-01-26', '2019-02-26', 'SSS', '1.00', '1.00', '1', '0', '2');
INSERT INTO `coupon` VALUES ('130', '2019-01-21 09:45:42', null, '21', '卢祖飞', 'P_17603072607', '2019-01-26', '2019-02-26', 'SSS', '1.00', '1.00', '1', '0', '2');
INSERT INTO `coupon` VALUES ('132', '2019-01-21 09:45:42', null, '23', 'hwl', 'P_17841138290', '2019-01-26', '2019-02-26', 'SSS', '1.00', '1.00', '1', '0', '2');

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
-- Records of coupon_group
-- ----------------------------
INSERT INTO `coupon_group` VALUES ('1', '2019-01-21 10:01:55', '2019-01-26', '2019-02-28', '测试', '100.00', '10.00', '0');
INSERT INTO `coupon_group` VALUES ('2', '2019-01-20 10:01:57', '2019-01-26', '2019-02-26', 'SSS', '1.00', '1.00', '1');

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
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('55', '2019-01-18 15:30:59', null, '11', '我的乐橙', '一年级', '语文', '我的课程设计', '0', null, '0', '0', '0', '小学');
INSERT INTO `course` VALUES ('57', '2019-01-18 17:59:28', null, '11', '会尽快', '一年级', '语文', '地方', '0', null, '0', '0', '1', '小学');
INSERT INTO `course` VALUES ('58', '2019-01-21 14:55:03', null, '11', '1', '一年级', '语文', '111', '0', null, '0', '0', '0', '小学');
INSERT INTO `course` VALUES ('59', '2019-01-21 15:05:58', null, '14', '课程一', '一年级', '语文', '课程介绍', '0', null, '0', '0', '0', '小学');

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
-- Records of course_comment
-- ----------------------------

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
-- Records of course_outline
-- ----------------------------
INSERT INTO `course_outline` VALUES ('126', '2019-01-18 15:30:59', null, '112', '2019-01-18 15:27:00', '第一节课', '我的第一节课', '1', '1', 'http://jdvodgoxegdqv.vod.126.net/jdvodgoxegdqv/0-50718218617385-0-mix.mp4', '50718218617385');
INSERT INTO `course_outline` VALUES ('127', '2019-01-18 15:30:59', null, '112', '2019-01-18 15:27:00', '第二节课', '我的第二节课', '2', '2', null, '50718344610189');
INSERT INTO `course_outline` VALUES ('128', '2019-01-18 15:30:59', null, '112', '2019-01-18 15:27:00', '第三节课', '我的第三节课', '0', '3', null, null);
INSERT INTO `course_outline` VALUES ('129', '2019-01-18 15:30:59', null, '112', '2019-01-18 15:27:00', '第四节课', '我的第四节课', '0', '4', null, null);
INSERT INTO `course_outline` VALUES ('130', '2019-01-18 15:30:59', null, '112', '2019-01-18 15:27:00', '第五节课', '第五节课', '0', '5', null, null);
INSERT INTO `course_outline` VALUES ('131', '2019-01-18 15:30:59', null, '112', '2019-01-18 15:27:00', '第六节课', '我的第六节课', '0', '6', null, null);
INSERT INTO `course_outline` VALUES ('133', '2019-01-18 17:59:29', null, '114', '2019-01-18 00:00:00', '34', '43', '1', null, null, '50720299909309');
INSERT INTO `course_outline` VALUES ('134', '2019-01-18 17:59:29', null, '114', '2019-01-18 00:00:00', '433', '4334', '1', null, null, '50720279331280');
INSERT INTO `course_outline` VALUES ('135', '2019-01-21 14:55:03', null, '115', '2019-01-21 14:50:00', '1', '1', '0', '1', null, null);
INSERT INTO `course_outline` VALUES ('136', '2019-01-21 14:55:03', null, '115', '2019-05-21 14:50:00', '5', '5', '0', '2', null, null);
INSERT INTO `course_outline` VALUES ('137', '2019-01-21 14:55:03', null, '115', '2019-06-21 14:50:00', '6', '6', '0', '3', null, null);
INSERT INTO `course_outline` VALUES ('138', '2019-01-21 14:55:03', null, '115', '2019-07-21 14:50:00', '7', '7', '0', '4', null, null);
INSERT INTO `course_outline` VALUES ('139', '2019-01-21 15:05:58', null, '116', '2019-01-21 17:00:00', '章一', '一', '0', '1', null, null);
INSERT INTO `course_outline` VALUES ('140', '2019-01-21 15:05:58', null, '116', '2019-01-21 18:00:00', '章二', '二', '0', '2', null, null);
INSERT INTO `course_outline` VALUES ('141', '2019-01-21 15:05:58', null, '116', '2019-01-21 19:00:00', '章三', '三', '0', '3', null, null);

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
-- Records of course_type
-- ----------------------------
INSERT INTO `course_type` VALUES ('112', '2019-01-18 15:30:59', '2019-01-18 16:37:31', '55', '1', '299.99', '299.12', '322', '32', '2019-01-18 15:27:00', '15:28', '10', '4', '每周一、三、五、六', '0', '837.52');
INSERT INTO `course_type` VALUES ('114', '2019-01-18 17:59:28', '2019-01-19 09:59:45', '57', '1', '122.00', '20.00', '23', '232', '2019-01-18 02:00:00', '00:02', '10', '2', '每天', '0', '28.00');
INSERT INTO `course_type` VALUES ('115', '2019-01-21 14:55:03', null, '58', '1', '112.00', '21.00', '21', '21', '2019-01-21 14:50:00', '09:01', '10', '0', '每周一', '0', '0.00');
INSERT INTO `course_type` VALUES ('116', '2019-01-21 15:05:58', null, '59', '1', '99.00', '69.00', '5', '40', '2019-01-09 00:00:00', '09:00', '10', '0', '每天', '0', '0.00');

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
-- Records of finance
-- ----------------------------
INSERT INTO `finance` VALUES ('130', '2019-01-18 11:28:51', null, '长孙', '钟旭春', '38.88', '11.66', '27.22', '0.70');
INSERT INTO `finance` VALUES ('132', '2019-01-18 14:21:56', null, '小浣熊', '钟旭春', '38.88', '11.66', '27.22', '0.70');
INSERT INTO `finance` VALUES ('133', '2019-01-18 15:32:27', null, '卢祖飞', '黄世杰', '299.12', '89.74', '209.38', '0.70');
INSERT INTO `finance` VALUES ('134', '2019-01-18 15:33:12', null, 'hwl', '黄世杰', '299.12', '89.74', '209.38', '0.70');
INSERT INTO `finance` VALUES ('135', '2019-01-18 15:39:32', null, 'ZXC', '黄世杰', '299.12', '89.74', '209.38', '0.70');
INSERT INTO `finance` VALUES ('136', '2019-01-18 16:37:31', null, '小浣熊', '黄世杰', '299.12', '89.74', '209.38', '0.70');
INSERT INTO `finance` VALUES ('137', '2019-01-18 17:41:34', null, 'hwl', '接近让妈妈', '10.00', '3.00', '7.00', '0.70');
INSERT INTO `finance` VALUES ('138', '2019-01-18 17:42:09', null, '卢祖飞', '接近让妈妈', '30.00', '9.00', '21.00', '0.70');
INSERT INTO `finance` VALUES ('139', '2019-01-19 09:50:21', null, '小浣熊', '黄世杰', '20.00', '6.00', '14.00', '0.70');
INSERT INTO `finance` VALUES ('140', '2019-01-19 09:59:45', null, '卢祖飞', '黄世杰', '20.00', '6.00', '14.00', '0.70');
INSERT INTO `finance` VALUES ('141', '2019-01-21 10:48:33', null, '钟旭春', null, '0.01', '0.00', null, null);
INSERT INTO `finance` VALUES ('142', '2019-01-21 10:49:52', null, '钟旭春', null, '0.01', '0.00', null, null);
INSERT INTO `finance` VALUES ('143', '2019-01-21 14:37:14', null, '小浣熊', null, '0.01', '0.00', null, null);

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
-- Records of finance_student
-- ----------------------------
INSERT INTO `finance_student` VALUES ('1547715238', '2019-01-18 14:21:56', null, '19', '0', '课程报名支出', '38.88');
INSERT INTO `finance_student` VALUES ('1547715239', '2019-01-18 15:32:27', null, '21', '0', '课程报名支出', '299.12');
INSERT INTO `finance_student` VALUES ('1547715240', '2019-01-18 15:33:12', null, '23', '0', '课程报名支出', '299.12');
INSERT INTO `finance_student` VALUES ('1547715242', '2019-01-18 16:37:31', null, '19', '0', '课程报名支出', '299.12');
INSERT INTO `finance_student` VALUES ('1547715243', '2019-01-18 17:41:34', null, '23', '0', '课程报名支出', '10.00');
INSERT INTO `finance_student` VALUES ('1547715244', '2019-01-18 17:42:09', null, '21', '0', '课程报名支出', '30.00');
INSERT INTO `finance_student` VALUES ('1547715245', '2019-01-19 09:50:21', null, '19', '0', '课程报名支出', '20.00');
INSERT INTO `finance_student` VALUES ('1547715246', '2019-01-19 09:59:45', null, '21', '0', '课程报名支出', '20.00');
INSERT INTO `finance_student` VALUES ('1548052634', '2019-01-21 14:37:14', null, '19', '1', '余额充值', '0.01');

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
-- Records of finance_teacher
-- ----------------------------
INSERT INTO `finance_teacher` VALUES ('235', '2019-01-18 15:14:39', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('236', '2019-01-18 15:16:47', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('237', '2019-01-18 15:18:41', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('238', '2019-01-18 15:19:49', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('239', '2019-01-18 15:21:16', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('240', '2019-01-18 15:21:51', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('241', '2019-01-18 15:22:55', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('242', '2019-01-18 15:32:27', null, '11', '0', '课程报名收入', '209.38', null);
INSERT INTO `finance_teacher` VALUES ('243', '2019-01-18 15:33:12', null, '11', '0', '课程报名收入', '209.38', null);
INSERT INTO `finance_teacher` VALUES ('244', '2019-01-18 15:39:32', null, '11', '0', '课程报名收入', '209.38', null);
INSERT INTO `finance_teacher` VALUES ('245', '2019-01-18 16:37:31', null, '11', '0', '课程报名收入', '209.38', null);
INSERT INTO `finance_teacher` VALUES ('246', '2019-01-18 17:17:14', null, '13', '0', '疑难收入', '0.00', null);
INSERT INTO `finance_teacher` VALUES ('247', '2019-01-18 17:20:35', null, '13', '0', '疑难收入', '0.00', null);
INSERT INTO `finance_teacher` VALUES ('248', '2019-01-18 17:41:34', null, '13', '0', '课程报名收入', '7.00', null);
INSERT INTO `finance_teacher` VALUES ('249', '2019-01-18 17:42:09', null, '13', '0', '课程报名收入', '21.00', null);
INSERT INTO `finance_teacher` VALUES ('250', '2019-01-18 18:20:32', null, '13', '1', '教师提现', '28.00', '0');
INSERT INTO `finance_teacher` VALUES ('251', '2019-01-19 09:43:05', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('252', '2019-01-19 09:44:47', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('253', '2019-01-19 09:47:27', null, '11', '0', '疑难收入', '8.40', null);
INSERT INTO `finance_teacher` VALUES ('254', '2019-01-19 09:50:21', null, '11', '0', '课程报名收入', '14.00', null);
INSERT INTO `finance_teacher` VALUES ('255', '2019-01-19 09:59:45', null, '11', '0', '课程报名收入', '14.00', null);
INSERT INTO `finance_teacher` VALUES ('256', '2019-01-19 11:09:46', null, '11', '1', '教师提现', '10.00', '0');
INSERT INTO `finance_teacher` VALUES ('257', '2019-01-19 11:15:53', null, '11', '1', '教师提现', '10.00', '0');

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
-- Records of marketing_banner
-- ----------------------------
INSERT INTO `marketing_banner` VALUES ('82', '2019-01-18 10:26:48', null, '轮播图1', null, '<p>集体上课<br/></p>', '0', '0', '/upload/file/23610bcb40774824999252471f03b78c.jpg', '0', '0', null, '真实姓名');
INSERT INTO `marketing_banner` VALUES ('84', '2019-01-18 10:27:45', null, '轮播图2', null, '<p>集体上课2<br/></p>', '0', '0', '/upload/file/ae647bb0ac1445dc9dd12e34f471d9a2.jpg', '0', '0', null, '真实姓名');
INSERT INTO `marketing_banner` VALUES ('86', '2019-01-18 10:28:44', null, '视频1', null, '<p>视频1<br/></p>', '0', '0', '/upload/file/aa2b5d38cee3439ba8133c34603dc8b2.png', '0', '1', '/upload/file/50d1b65737db4d09b02766551bf34787.mp4', '真实姓名');
INSERT INTO `marketing_banner` VALUES ('87', '2019-01-18 10:29:09', '2019-01-18 17:54:04', '视频2', null, '<p>视频2<br/></p>', '1', '0', '/upload/file/4cb8b4ffbb6b42edac4c957697a188c4.png', '0', '1', '/upload/file/4e7c3cfa3f374f26b7523a9db6996831.mp4', '真实姓名');
INSERT INTO `marketing_banner` VALUES ('90', '2019-01-18 17:52:14', null, '法国', null, '<p>就看见</p>', '0', '0', '/upload/file/51f9513c67e34678b9771a25b2e82722.jpg', '0', '0', null, '真实姓名');
INSERT INTO `marketing_banner` VALUES ('91', '2019-01-18 17:54:34', null, '会尽快', null, '<p>就回家看看</p>', '0', '0', '/upload/file/53d69f4c1b2b4f7ab16e0ef2cb8bab2b.png', '0', '1', '/upload/file/1986a3a4490b44bba142dbc85e3c8e6f.mp4', '真实姓名');

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
-- Records of marketing_course
-- ----------------------------
INSERT INTO `marketing_course` VALUES ('23', '2019-01-18 10:30:36', null, '约课1', null, null, '<p>约课1<br/></p>', '0', null, '/upload/file/9de52e21f9514d06bf38d3010b7c108f.jpg', null, null, '真实姓名');
INSERT INTO `marketing_course` VALUES ('24', '2019-01-18 10:30:52', null, '约课2', null, null, '<p>约课2<br/></p>', '0', null, '/upload/file/cc98279cf8bb48c8864a88a61380a24b.jpg', null, null, '真实姓名');
INSERT INTO `marketing_course` VALUES ('25', '2019-01-18 10:32:49', null, '约课3', null, null, '<p>约课3<br/></p>', '0', null, '/upload/file/359238aa66e84362acbe1b213c1eef37.jpg', null, null, '真实姓名');

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
-- Records of marketing_question
-- ----------------------------
INSERT INTO `marketing_question` VALUES ('18', '2019-01-18 10:33:32', null, '疑难1', null, null, '<p>疑难<br/></p>', '0', null, '/upload/file/553ca936536545dca11a7732fcf7d501.jpg', null, null, '真实姓名');
INSERT INTO `marketing_question` VALUES ('19', '2019-01-18 10:33:50', null, '疑难2', null, null, '<p>疑难2<br/></p>', '0', null, '/upload/file/f4620085852f480bae20a72060686630.jpg', null, null, '真实姓名');
INSERT INTO `marketing_question` VALUES ('20', '2019-01-18 10:34:09', null, '疑难3', null, null, '<p>疑难3<br/></p>', '0', null, '/upload/file/d68fc38a729d429ba99ba50f6cc81762.jpg', null, null, '真实姓名');

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
-- Records of message_student
-- ----------------------------
INSERT INTO `message_student` VALUES ('452', '2019-01-18 11:30:39', null, '17', '上课提醒', '您的《第一章》课程，将在0天2小时29分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('453', '2019-01-18 11:30:48', null, '17', '上课提醒', '您的《第一章》课程，将在0天2小时29分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('454', '2019-01-18 11:31:24', null, '17', '上课提醒', '您的《第一章》课程，将在0天2小时28分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('455', '2019-01-18 11:31:28', null, '17', '上课提醒', '您的《第一章》课程，将在0天2小时28分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('456', '2019-01-18 14:21:57', null, '19', '开课提醒', '您的课程一课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('457', '2019-01-18 15:32:27', null, '21', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('458', '2019-01-18 15:32:27', null, '21', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('459', '2019-01-18 15:32:27', null, '21', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('460', '2019-01-18 15:32:27', null, '21', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('461', '2019-01-18 15:32:27', null, '21', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('462', '2019-01-18 15:32:28', null, '21', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('463', '2019-01-18 15:33:12', null, '23', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('464', '2019-01-18 15:33:12', null, '23', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('465', '2019-01-18 15:33:12', null, '23', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('466', '2019-01-18 15:33:12', null, '23', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('467', '2019-01-18 15:33:12', null, '23', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('468', '2019-01-18 15:33:13', null, '23', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('475', '2019-01-18 15:41:13', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-14分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('476', '2019-01-18 15:41:13', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-14分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('477', '2019-01-18 15:41:13', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-14分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('478', '2019-01-18 15:41:44', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-14分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('479', '2019-01-18 15:41:44', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-14分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('480', '2019-01-18 15:41:44', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-14分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('481', '2019-01-18 15:42:52', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-15分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('482', '2019-01-18 15:42:52', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-15分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('483', '2019-01-18 15:42:52', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-15分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('484', '2019-01-18 15:43:58', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-16分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('485', '2019-01-18 15:43:58', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-16分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('486', '2019-01-18 15:43:58', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-16分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('487', '2019-01-18 15:44:17', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-17分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('488', '2019-01-18 15:44:17', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-17分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('489', '2019-01-18 15:44:17', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-17分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('490', '2019-01-18 15:45:57', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('491', '2019-01-18 15:45:57', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('492', '2019-01-18 15:45:57', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('493', '2019-01-18 15:46:25', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('494', '2019-01-18 15:46:25', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('495', '2019-01-18 15:46:25', null, '23', '上课提醒', '您的《第一节课》课程，将在0天0小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('496', '2019-01-18 16:37:32', null, '19', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('497', '2019-01-18 16:37:32', null, '19', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('498', '2019-01-18 16:37:32', null, '19', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('499', '2019-01-18 16:37:32', null, '19', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('500', '2019-01-18 16:37:32', null, '19', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('501', '2019-01-18 16:37:32', null, '19', '开课提醒', '您的我的乐橙课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('502', '2019-01-18 16:45:02', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('503', '2019-01-18 16:45:02', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('504', '2019-01-18 16:45:02', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('505', '2019-01-18 16:45:02', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('506', '2019-01-18 16:45:27', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('507', '2019-01-18 16:45:27', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('508', '2019-01-18 16:45:27', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('509', '2019-01-18 16:45:27', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-18分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('510', '2019-01-18 16:46:55', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('511', '2019-01-18 16:46:55', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('512', '2019-01-18 16:46:55', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('513', '2019-01-18 16:46:55', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('514', '2019-01-18 16:46:59', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('515', '2019-01-18 16:46:59', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('516', '2019-01-18 16:46:59', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('517', '2019-01-18 16:46:59', null, '23', '上课提醒', '您的《第二节课》课程，将在0天-1小时-19分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('518', '2019-01-18 17:41:34', null, '23', '开课提醒', '您的课程课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('519', '2019-01-18 17:42:10', null, '21', '开课提醒', '您的课程课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('520', '2019-01-18 17:42:18', null, '23', '上课提醒', '您的《痛》课程，将在0天0小时-8分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('521', '2019-01-18 17:42:18', null, '23', '上课提醒', '您的《痛》课程，将在0天0小时-8分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('522', '2019-01-19 09:50:22', null, '19', '开课提醒', '您的会尽快课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('523', '2019-01-19 09:50:22', null, '19', '开课提醒', '您的会尽快课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('524', '2019-01-19 09:59:45', null, '21', '开课提醒', '您的会尽快课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');
INSERT INTO `message_student` VALUES ('525', '2019-01-19 09:59:45', null, '21', '开课提醒', '您的会尽快课 将在1分钟后开课，建议您马上去课室开始上课哟~', '0');

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
-- Records of message_teacher
-- ----------------------------
INSERT INTO `message_teacher` VALUES ('1', '2019-01-18 14:34:13', null, '11', '疑难提醒', '13926513277同学遇到问题,呼唤您上线答疑了!', '0');
INSERT INTO `message_teacher` VALUES ('2', '2019-01-18 14:48:33', null, '11', '疑难提醒', '13926513277同学遇到问题,呼唤您上线答疑了!', '0');
INSERT INTO `message_teacher` VALUES ('3', '2019-01-18 15:05:15', null, '11', '疑难提醒', 'ZXC同学遇到问题,呼唤您上线答疑了!', '0');
INSERT INTO `message_teacher` VALUES ('4', '2019-01-18 15:15:37', null, '11', '疑难提醒', 'ZXC同学遇到问题,呼唤您上线答疑了!', '0');
INSERT INTO `message_teacher` VALUES ('5', '2019-01-18 17:13:45', null, '13', '疑难提醒', 'ZXC同学遇到问题,呼唤您上线答疑了!', '0');
INSERT INTO `message_teacher` VALUES ('6', '2019-01-18 17:18:54', null, '13', '疑难提醒', 'ZXC同学遇到问题,呼唤您上线答疑了!', '0');
INSERT INTO `message_teacher` VALUES ('7', '2019-01-19 09:34:16', null, '11', '疑难提醒', '卢祖飞同学遇到问题,呼唤您上线答疑了!', '0');

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
-- Records of order_course
-- ----------------------------
INSERT INTO `order_course` VALUES ('10000133', '2019-01-18 11:28:51', '2019-01-18 11:28:51', '发过火', '0', '北京市/东城区废话姐姐', '13568855455', '75.00', '38.88', '7', '40', '21:00', '钟旭春', '0', null, '38.88', '17', '12', '54', '111', '1', '38.88', '2', '课程一', '课程介绍', '2019-01-18 11:35:52', null);
INSERT INTO `order_course` VALUES ('10000135', '2019-01-18 14:21:56', '2019-01-18 14:21:59', 'a', null, '北京市/东城区1', '15170192080', '75.00', '38.88', '7', '40', '21:00', '钟旭春', '0', null, '38.88', '19', '12', '54', '111', '0', '38.88', '2', '课程一', '课程介绍', '2019-01-18 10:00:00', null);
INSERT INTO `order_course` VALUES ('10000136', '2019-01-18 15:31:29', null, '带个好', '0', '北京市/东城区一会回复', '13217131528', '299.99', '299.12', '322', '32', '15:28', '黄世杰', '0', null, '299.12', '22', '11', '55', '112', '0', '299.12', '4', '我的乐橙', '我的课程设计', '2019-01-18 15:27:00', null);
INSERT INTO `order_course` VALUES ('10000137', '2019-01-18 15:32:27', '2019-01-18 15:32:27', '你想让我', '0', '北京市/东城区哦属于什么', '17603072607', '299.99', '299.12', '322', '32', '15:28', '黄世杰', '0', null, '299.12', '21', '11', '55', '112', '0', '299.12', '2', '我的乐橙', '我的课程设计', '2019-01-18 15:27:00', null);
INSERT INTO `order_course` VALUES ('10000138', '2019-01-18 15:33:11', '2019-01-18 15:33:12', 'ghh', '0', '北京市/东城区ggv', '17841138290', '299.99', '299.12', '322', '32', '15:28', '黄世杰', '0', null, '299.12', '23', '11', '55', '112', '0', '299.12', '2', '我的乐橙', '我的课程设计', '2019-01-18 15:27:00', null);
INSERT INTO `order_course` VALUES ('10000139', '2019-01-18 15:39:32', '2019-01-18 15:39:32', '带个好', '0', '北京市/东城区一会回复', '13217131528', '299.99', '299.12', '322', '32', '15:28', '黄世杰', '0', null, '299.12', '22', '11', '55', '112', '0', '299.12', '2', '我的乐橙', '我的课程设计', '2019-01-18 15:27:00', null);
INSERT INTO `order_course` VALUES ('10000140', '2019-01-18 16:37:31', '2019-01-18 16:37:31', 'a', null, '北京市/东城区1', '15170192080', '299.99', '299.12', '322', '32', '15:28', '黄世杰', '0', null, '299.12', '19', '11', '55', '112', '0', '299.12', '2', '我的乐橙', '我的课程设计', '2019-01-18 15:27:00', null);
INSERT INTO `order_course` VALUES ('10000141', '2019-01-18 17:41:34', '2019-01-18 17:41:34', 'ghh', '0', '北京市/东城区ggv', '17841138290', '40.00', '40.00', '10', '40', '17:35', '接近让妈妈', '1', '20.00', '30.00', '23', '13', '56', '113', '0', '10.00', '2', '课程', '接受', '2019-01-18 18:15:33', null);
INSERT INTO `order_course` VALUES ('10000143', '2019-01-19 09:50:21', '2019-01-19 09:50:22', 'a', null, '北京市/东城区1', '15170192080', '122.00', '20.00', '23', '232', '00:02', '黄世杰', '0', null, '20.00', '19', '11', '57', '114', '0', '20.00', '2', '会尽快', '地方', '2019-01-18 02:00:00', '每天');
INSERT INTO `order_course` VALUES ('10000144', '2019-01-19 09:59:45', '2019-01-19 09:59:45', '你想让我', '0', '北京市/东城区哦属于什么', '17603072607', '122.00', '20.00', '23', '232', '00:02', '黄世杰', '0', null, '20.00', '21', '11', '57', '114', '0', '20.00', '2', '会尽快', '地方', '2019-01-18 02:00:00', null);

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
-- Records of order_question
-- ----------------------------
INSERT INTO `order_question` VALUES ('50000367', '2019-01-18 15:17:15', '2019-01-18 15:17:27', '22', '11', 'ZXC', '黄世杰', '/upload/file/8a78faa78a0c4e2f81fc041f9f3f7ee7.jpg', '5', '12.00', '0', null, '0', null, null, null, null, null, '0', null);
INSERT INTO `order_question` VALUES ('50000368', '2019-01-18 15:17:38', '2019-01-18 15:17:51', '22', '11', 'ZXC', '黄世杰', '/upload/file/70864ab06a544bbf85d827162c7004d3.jpg', '5', '12.00', '0', null, '0', null, null, null, null, null, '0', null);
INSERT INTO `order_question` VALUES ('50000369', '2019-01-18 15:18:02', null, '22', '11', 'ZXC', '黄世杰', '/upload/file/926c3073289b4642aec0e7b7f5e2689b.jpg', '3', '12.00', '0', '0.00', '1', '2', null, '一会回复', null, '1', '0', '12.00');
INSERT INTO `order_question` VALUES ('50000370', '2019-01-18 15:18:57', null, '22', '11', 'ZXC', '黄世杰', '/upload/file/f5044178394d468a9840c6a17f3533d3.jpg', '3', '12.00', '0', '0.00', '1', '2', null, '一会回复', null, '1', '0', '12.00');
INSERT INTO `order_question` VALUES ('50000372', '2019-01-18 15:20:53', null, '22', '11', 'ZXC', '黄世杰', '/upload/file/f12497a138424bcf84815066d3cf0b96.jpg', '3', '12.00', '0', '0.00', '1', '2', null, '一会回复', null, '1', '0', '12.00');
INSERT INTO `order_question` VALUES ('50000374', '2019-01-18 15:22:21', null, '22', '11', 'ZXC', '黄世杰', '/upload/file/489b7d0836584bc29ae3359c6cc5e163.jpg', '3', '12.00', '0', '0.00', '1', '2', null, '一会回复', null, '1', '0', '12.00');
INSERT INTO `order_question` VALUES ('50000375', '2019-01-18 17:14:36', null, '22', '13', 'ZXC', '接近让妈妈', '/upload/file/0c34bce0d77345c2bee5fbc58ff47732.jpg', '3', '0.00', '0', '0.00', '1', '2', null, '一会回复', null, '1', '0', '0.00');

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
-- Records of order_recharge
-- ----------------------------
INSERT INTO `order_recharge` VALUES ('90000020', '2019-01-18 15:42:23', null, '19', '0.01', '1', '0');
INSERT INTO `order_recharge` VALUES ('90000022', '2019-01-18 15:46:38', null, '19', '0.01', '4', '0');
INSERT INTO `order_recharge` VALUES ('90000023', '2019-01-18 15:49:17', null, '19', '0.01', '4', '0');
INSERT INTO `order_recharge` VALUES ('90000024', '2019-01-18 16:24:13', null, '19', '0.01', '1', '0');
INSERT INTO `order_recharge` VALUES ('90000025', '2019-01-18 16:26:22', null, '19', '0.01', '1', '0');
INSERT INTO `order_recharge` VALUES ('90000026', '2019-01-18 16:26:31', null, '19', '0.01', '1', '0');
INSERT INTO `order_recharge` VALUES ('90000027', '2019-01-18 16:26:43', null, '19', '0.01', '1', '0');
INSERT INTO `order_recharge` VALUES ('90000028', '2019-01-18 16:30:55', null, '19', '0.01', '1', '0');
INSERT INTO `order_recharge` VALUES ('90000031', '2019-01-21 12:26:15', null, '19', '0.01', '4', '0');
INSERT INTO `order_recharge` VALUES ('90000032', '2019-01-21 13:38:33', null, '19', '0.01', '4', '0');
INSERT INTO `order_recharge` VALUES ('90000033', '2019-01-21 13:39:33', null, '19', '0.01', '4', '0');
INSERT INTO `order_recharge` VALUES ('90000034', '2019-01-21 14:34:01', null, '19', '0.01', '4', '0');
INSERT INTO `order_recharge` VALUES ('90000035', '2019-01-21 14:35:27', '2019-01-21 14:37:13', '19', '0.01', '2', '0');

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
-- Records of order_student
-- ----------------------------
INSERT INTO `order_student` VALUES ('120', '2019-01-18 11:40:38', null, '长孙', '0', '废话姐姐', '18270708551', 'P_18270708551');
INSERT INTO `order_student` VALUES ('121', '2019-01-18 11:55:53', null, '小浣熊', null, '北京市/东城区1', '15170192080', 'P_15170192080');
INSERT INTO `order_student` VALUES ('122', '2019-01-18 14:21:56', null, '小浣熊', null, '北京市/东城区1', '15170192080', 'P_15170192080');
INSERT INTO `order_student` VALUES ('123', '2019-01-18 15:14:39', null, 'ZXC', '0', '一会回复', '18819015201', 'P_18819015201');
INSERT INTO `order_student` VALUES ('124', '2019-01-18 15:16:47', null, 'ZXC', '0', '一会回复', '18819015201', 'P_18819015201');
INSERT INTO `order_student` VALUES ('125', '2019-01-18 15:18:41', null, 'ZXC', '0', '一会回复', '18819015201', 'P_18819015201');
INSERT INTO `order_student` VALUES ('126', '2019-01-18 15:19:49', null, 'ZXC', '0', '一会回复', '18819015201', 'P_18819015201');
INSERT INTO `order_student` VALUES ('127', '2019-01-18 15:21:16', null, '卢祖飞', '0', '哦属于什么', '17603072607', 'P_17603072607');
INSERT INTO `order_student` VALUES ('128', '2019-01-18 15:21:51', null, 'ZXC', '0', '一会回复', '18819015201', 'P_18819015201');
INSERT INTO `order_student` VALUES ('129', '2019-01-18 15:22:55', null, 'ZXC', '0', '一会回复', '18819015201', 'P_18819015201');
INSERT INTO `order_student` VALUES ('130', '2019-01-18 15:32:27', null, '卢祖飞', '0', '北京市/东城区哦属于什么', '17603072607', 'P_17603072607');
INSERT INTO `order_student` VALUES ('131', '2019-01-18 15:33:12', null, 'hwl', '0', '北京市/东城区ggv', '17841138290', 'P_17841138290');
INSERT INTO `order_student` VALUES ('132', '2019-01-18 15:39:32', null, 'ZXC', '0', '北京市/东城区一会回复', '13217131528', 'P_18819015201');
INSERT INTO `order_student` VALUES ('133', '2019-01-18 16:37:31', null, '小浣熊', null, '北京市/东城区1', '15170192080', 'P_15170192080');
INSERT INTO `order_student` VALUES ('134', '2019-01-18 17:17:14', null, 'ZXC', '0', '一会回复', '18819015201', 'P_18819015201');
INSERT INTO `order_student` VALUES ('135', '2019-01-18 17:20:35', null, 'ZXC', '0', '一会回复', '18819015201', 'P_18819015201');
INSERT INTO `order_student` VALUES ('136', '2019-01-18 17:41:34', null, 'hwl', '0', '北京市/东城区ggv', '17841138290', 'P_17841138290');
INSERT INTO `order_student` VALUES ('137', '2019-01-18 17:42:09', null, '卢祖飞', '0', '北京市/东城区哦属于什么', '17603072607', 'P_17603072607');
INSERT INTO `order_student` VALUES ('138', '2019-01-19 09:43:05', null, '卢祖飞', '0', '哦属于什么', '17603072607', 'P_17603072607');
INSERT INTO `order_student` VALUES ('139', '2019-01-19 09:44:47', null, '卢祖飞', '0', '哦属于什么', '17603072607', 'P_17603072607');
INSERT INTO `order_student` VALUES ('140', '2019-01-19 09:47:27', null, '卢祖飞', '0', '哦属于什么', '17603072607', 'P_17603072607');
INSERT INTO `order_student` VALUES ('141', '2019-01-19 09:50:21', null, '小浣熊', null, '北京市/东城区1', '15170192080', 'P_15170192080');
INSERT INTO `order_student` VALUES ('142', '2019-01-19 09:59:45', null, '卢祖飞', '0', '北京市/东城区哦属于什么', '17603072607', 'P_17603072607');

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
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

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
-- Records of QRTZ_CALENDARS
-- ----------------------------

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
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('DefaultQuartzScheduler', 'JOBNAME_CLEAN_FILE', 'DEFAULT_TRIGGER_GROUP', '0 0 2 ? * MON', 'Asia/Shanghai');

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
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

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
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('DefaultQuartzScheduler', 'COURSE_OUTLINE_STUDENT__46ab221e56164286a2f26f23da771d17', 'DEFAULT_JOB_GROUP', null, 'cn.logicalthinking.common.quartz.ClassRemindJob', '1', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000067400076A6F624E616D65740038434F555253455F4F55544C494E455F53545544454E545F5F343661623232316535363136343238366132663236663233646137373164313774000973747564656E744964737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000001174000A636F757273654E616D65740009E8AFBEE7A88BE4B8807400097465616368657249647371007E000A0000000C74000570686F6E6574000B313832373037303835353174000A636F7572736554696D657371007E000A000000057800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('DefaultQuartzScheduler', 'JOBNAME_CLEAN_FILE', 'DEFAULT_JOB_GROUP', null, 'cn.logicalthinking.common.quartz.JobCleanFile', '1', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740004706174687400242F7573722F6C6F63616C2F746F6D6361742F776562617070732F74656163682D66696C657800);

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
-- Records of QRTZ_LOCKS
-- ----------------------------

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
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

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
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

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
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

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
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

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
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('DefaultQuartzScheduler', 'COURSE_OUTLINE_STUDENT__46ab221e56164286a2f26f23da771d17', 'DEFAULT_TRIGGER_GROUP', 'COURSE_OUTLINE_STUDENT__46ab221e56164286a2f26f23da771d17', 'DEFAULT_JOB_GROUP', null, '1579326900000', '1547790900000', '5', 'WAITING', 'CRON', '1547782131000', '0', null, '0', '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('DefaultQuartzScheduler', 'JOBNAME_CLEAN_FILE', 'DEFAULT_TRIGGER_GROUP', 'JOBNAME_CLEAN_FILE', 'DEFAULT_JOB_GROUP', null, '1548612000000', '1548034241364', '5', 'WAITING', 'CRON', '1547713574000', '0', null, '0', '');

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
-- Records of question_comment
-- ----------------------------
INSERT INTO `question_comment` VALUES ('25', '2019-01-18 17:21:29', null, '13', '22', 'ZXC', '2', '2', '223', '/upload/file/6ab49dbfa96045398d607c924d126b28.png', '50000376', '/upload/file/b43bcdae4ecb4826a08f8be4ca2d549c.jpg');

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
-- Records of questionnaire
-- ----------------------------
INSERT INTO `questionnaire` VALUES ('97', '2019-01-18 18:05:30', null, '1', '0');
INSERT INTO `questionnaire` VALUES ('98', '2019-01-18 18:05:30', null, '2', '0');
INSERT INTO `questionnaire` VALUES ('99', '2019-01-18 18:05:30', null, '3', '0');
INSERT INTO `questionnaire` VALUES ('100', '2019-01-18 18:05:30', null, '4', '0');
INSERT INTO `questionnaire` VALUES ('101', '2019-01-18 18:05:30', null, '5', '0');
INSERT INTO `questionnaire` VALUES ('102', '2019-01-18 18:05:30', null, '6', '0');
INSERT INTO `questionnaire` VALUES ('103', '2019-01-18 18:05:30', null, '7', '0');
INSERT INTO `questionnaire` VALUES ('104', '2019-01-18 18:05:30', null, '88888', '0');

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
-- Records of questionnaire_colletion
-- ----------------------------
INSERT INTO `questionnaire_colletion` VALUES ('33', '2019-01-18 18:06:50', null, '小浣熊', 'P_15170192080', '1', '1', '2', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '2', '88888', '2', '嗯呢');
INSERT INTO `questionnaire_colletion` VALUES ('34', '2019-01-21 14:57:14', null, '小浣熊', 'P_15170192080', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '88888', '0', '');
INSERT INTO `questionnaire_colletion` VALUES ('35', '2019-01-21 14:57:33', null, '小浣熊', 'P_15170192080', '1', '1', '2', '2', '2', '3', '1', '4', '1', '5', '0', '6', '1', '7', '1', '88888', '1', '');
INSERT INTO `questionnaire_colletion` VALUES ('36', '2019-01-21 14:59:26', null, '小浣熊', 'P_15170192080', '1', '1', '2', '2', '2', '3', '1', '4', '1', '5', '0', '6', '1', '7', '1', '88888', '1', '');
INSERT INTO `questionnaire_colletion` VALUES ('37', '2019-01-21 14:59:43', null, '小浣熊', 'P_15170192080', '1', '1', '2', '2', '1', '3', '1', '4', '1', '5', '2', '6', '2', '7', '2', '88888', '1', '');
INSERT INTO `questionnaire_colletion` VALUES ('38', '2019-01-21 14:59:53', null, '小浣熊', 'P_15170192080', '3', '1', '3', '2', '3', '3', '3', '4', '3', '5', '3', '6', '3', '7', '3', '88888', '3', '');
INSERT INTO `questionnaire_colletion` VALUES ('39', '2019-01-21 15:00:05', null, '小浣熊', 'P_15170192080', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '88888', '0', '');
INSERT INTO `questionnaire_colletion` VALUES ('40', '2019-01-21 15:00:16', null, '小浣熊', 'P_15170192080', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '88888', '2', '');
INSERT INTO `questionnaire_colletion` VALUES ('41', '2019-01-21 15:00:33', null, '小浣熊', 'P_15170192080', '3', '1', '3', '2', '3', '3', '3', '4', '3', '5', '3', '6', '3', '7', '3', '88888', '3', '');
INSERT INTO `questionnaire_colletion` VALUES ('42', '2019-01-21 15:02:37', null, '小浣熊', 'P_15170192080', '0', '1', '1', '2', '1', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '88888', '0', '');
INSERT INTO `questionnaire_colletion` VALUES ('43', '2019-01-21 15:14:43', null, '小浣熊', 'P_15170192080', '1', '1', '1', '2', '2', '3', '1', '4', '3', '5', '0', '6', '3', '7', '1', '88888', '0', '');
INSERT INTO `questionnaire_colletion` VALUES ('44', '2019-01-21 15:14:57', null, '小浣熊', 'P_15170192080', '1', '1', '3', '2', '3', '3', '2', '4', '2', '5', '1', '6', '0', '7', '0', '88888', '0', '');

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
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('009323a45c7e42f8a1a2238d1c70673b', '8', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('0559ff2347504ae78bbcf615ea84d755', '12', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('0646afc851454e24b5affec82800fc2c', '3', 'b49c3e77d31e419fa28c3f0e5c6e69e4', null);
INSERT INTO `role_menu` VALUES ('0d9dc5b077bb4ec79d5f4795ffbfed75', '11', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('10c81b1e7ede413886e5de5263ea482b', '17', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('1641babbfb4e4ef988db0d45e033e156', '29', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('1df492e125ad4fa5a45097f2503569d0', '14', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('26a0de392daa4fc791ae231b86e7b26f', '14', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('26edac56f5a543d89d8b7a7c421847d1', '2', 'b49c3e77d31e419fa28c3f0e5c6e69e4', null);
INSERT INTO `role_menu` VALUES ('2b50dafd9e3c429aadc081490abf1487', '10', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('38860728e9c94311aefb5fef1aad5867', '5', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('42d277a2554341bc8a27ac7d28798c89', '26', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('48b77c66132447c285d1ea5cd0edf98a', '16', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('4ff271a6b7a84a10acc45385b33a45b6', '4', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('558c17f8392148cc874a472eff087bff', '21', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('59f14037dff54031a89f923d458c2727', '12', 'b49c3e77d31e419fa28c3f0e5c6e69e4', null);
INSERT INTO `role_menu` VALUES ('65a33f65353f4691867fd641c8fc9942', '22', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('65b7d94ccff1427ca70c8a527eca794b', '13', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('66972945efa14bc6a8d2fd9c3c2f1e92', '20', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('68797ec27e804cb2b06e3c7c84a9d100', '27', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('6b773712418d4932a4da649458bfc803', '23', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('73312b9985f2484ab51c71345d16a5e7', '12', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('74d3824cb6814b2c920b5a3768c75daf', '5', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('79c3cf95c52045d396754a735ee07b48', '19', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('8c95202bd9d841a7bcf55d23bace5778', '28', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('954c9bd63e2a4a25b59000d7c4ce23d7', '5', 'b49c3e77d31e419fa28c3f0e5c6e69e4', null);
INSERT INTO `role_menu` VALUES ('964255d1455a4020a0eb4b90c869b3a2', '3', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('99a552b647c84ea9978fad5b121d4b84', '2', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('a32d66cad2ad4098b425e069ceb5a9c9', '6', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('a3ae719891c14601b91f841923ba7763', '4', 'b49c3e77d31e419fa28c3f0e5c6e69e4', null);
INSERT INTO `role_menu` VALUES ('a553407b5cd347b99ce5dad1ee921421', '9', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('a5620bc31af946ed8da4069515ed2318', '15', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('a799dbd373d9459a97232d8a641697bf', '2', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('ad2539ae9ef946f8971482c43475e9ab', '7', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('b09c31b1b4bd4e99ba658ec76b67bcf3', '3', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('b5247218f17c4b0499255228a54c3182', '4', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('be0d9e66334d4d76a293b3f54b785941', '25', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('bf56057e48dd479591b817fc375fe4c0', '29', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('c36ce2d906df4000987db9dd42d8d180', '13', 'b49c3e77d31e419fa28c3f0e5c6e69e4', null);
INSERT INTO `role_menu` VALUES ('c6fabaa91ffa42aeb1aa953bbe913e5f', '24', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('ca886488c29941138653194bbaf71466', '1', 'b49c3e77d31e419fa28c3f0e5c6e69e4', null);
INSERT INTO `role_menu` VALUES ('d040facdeed947939bce44600267f290', '16', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('e2a3742c907f4769a2017b89ed93d732', '17', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('e842c1e437ce4670b593392226bd4ce0', '15', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('ed5a1e1ca3214aa78c69a2b1c5ef6ddb', '1', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('f07eac7f502148aba30040821c38451e', '1', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('f31015700db54a079034c63abc03bc42', '18', 'ed138d6ef9944c85989eedebebb3c44d', null);
INSERT INTO `role_menu` VALUES ('f700404fb86c41a9a2d2d88af451d5a7', '18', 'c648861ebaeb4e189ffc0d3d0a518d4e', null);
INSERT INTO `role_menu` VALUES ('f786b60a75eb41ac88c808fa531d32cc', '13', 'ed138d6ef9944c85989eedebebb3c44d', null);

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
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('17', '2019-01-18 09:48:17', '2019-01-18 18:01:00', '长孙', '0', '山西省/太原市/小店区', '18270708551', '20', '一年级', '/upload/file/36d982df8f41480dbe27b2263e8fc3be.png', 'P_18270708551', '222', '49999941.12', '1', null, 'okLJZ5K096A9gZslEAvNFI_QZxqo', 'e10adc3949ba59abbe56e057f20f883e', '0');
INSERT INTO `student` VALUES ('19', '2019-01-18 11:54:40', '2019-01-21 14:37:14', '小浣熊', null, '', '15170192080', null, '一年级', '/image/wx_head_img.png', 'P_15170192080', null, '999602.13', '0', null, 'okLJZ5CS4xAFa1bo2TDtCkiDvQMU', 'f379eaf3c831b04de153469d1bec345e', '0');
INSERT INTO `student` VALUES ('21', '2019-01-18 14:59:40', '2019-01-19 09:59:45', '卢祖飞', '0', '江西省/赣州市/信丰县', '17603072607', '21', '高三', '/upload/file/63ac1a6ca181437eb2aa48bed50dd586.png', 'P_17603072607', null, '9999602.88', '0', null, 'okLJZ5Eva0_o1BigWQimDLkDYNt0', 'f379eaf3c831b04de153469d1bec345e', '0');
INSERT INTO `student` VALUES ('23', '2019-01-18 15:30:06', '2019-01-18 17:41:34', 'hwl', '0', '北京市东城区', '17841138290', null, '一年级', '/image/wx_head_img.png', 'P_17841138290', '223', '99690.88', '0', null, 'okLJZ5J830oKPOwWCM7Xk7E9IohM', 'e10adc3949ba59abbe56e057f20f883e', '0');
INSERT INTO `student` VALUES ('25', '2019-01-21 15:43:00', null, '啦啦啦', '0', '北京市/东城区', '13926513278', '12', '一年级', '/upload/file/fe2654a70b0648b0b5ace1d10f1c71f8.png', 'P_13926513278', null, '0.00', '0', null, 'okLJZ5IJulLT01kqDLsDc3FbC1VY', null, '0');

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
-- Records of student_has_course_type
-- ----------------------------

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
-- Records of student_has_teacher
-- ----------------------------

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
-- Records of sys_data
-- ----------------------------
INSERT INTO `sys_data` VALUES ('1', '小学', '1', '0');
INSERT INTO `sys_data` VALUES ('2', '一年级', '2', '1');
INSERT INTO `sys_data` VALUES ('3', '二年级', '2', '1');
INSERT INTO `sys_data` VALUES ('4', '三年级', '2', '1');
INSERT INTO `sys_data` VALUES ('5', '四年级', '2', '1');
INSERT INTO `sys_data` VALUES ('6', '五年级', '2', '1');
INSERT INTO `sys_data` VALUES ('7', '六年级', '2', '1');
INSERT INTO `sys_data` VALUES ('8', '初中', '1', '0');
INSERT INTO `sys_data` VALUES ('9', '初一', '2', '8');
INSERT INTO `sys_data` VALUES ('10', '初二', '2', '8');
INSERT INTO `sys_data` VALUES ('11', '初三', '2', '8');
INSERT INTO `sys_data` VALUES ('12', '高中', '1', '0');
INSERT INTO `sys_data` VALUES ('13', '高一', '2', '12');
INSERT INTO `sys_data` VALUES ('14', '高二', '2', '12');
INSERT INTO `sys_data` VALUES ('15', '高三', '2', '12');
INSERT INTO `sys_data` VALUES ('16', '语文', '3', '2');
INSERT INTO `sys_data` VALUES ('17', '数学', '3', '2');
INSERT INTO `sys_data` VALUES ('18', '英语', '3', '2');
INSERT INTO `sys_data` VALUES ('19', '品德与社会', '3', '2');
INSERT INTO `sys_data` VALUES ('20', '科学', '3', '2');
INSERT INTO `sys_data` VALUES ('21', '体育', '3', '2');
INSERT INTO `sys_data` VALUES ('22', '音乐', '3', '2');
INSERT INTO `sys_data` VALUES ('23', '美术', '3', '2');
INSERT INTO `sys_data` VALUES ('24', '健康', '3', '2');
INSERT INTO `sys_data` VALUES ('25', '法制', '3', '2');
INSERT INTO `sys_data` VALUES ('26', '信息技术', '3', '2');
INSERT INTO `sys_data` VALUES ('27', '综合实践', '3', '2');
INSERT INTO `sys_data` VALUES ('28', '语文', '3', '3');
INSERT INTO `sys_data` VALUES ('29', '数学', '3', '3');
INSERT INTO `sys_data` VALUES ('30', '英语', '3', '3');
INSERT INTO `sys_data` VALUES ('31', '品德与社会', '3', '3');
INSERT INTO `sys_data` VALUES ('32', '科学', '3', '3');
INSERT INTO `sys_data` VALUES ('33', '体育', '3', '3');
INSERT INTO `sys_data` VALUES ('34', '音乐', '3', '3');
INSERT INTO `sys_data` VALUES ('35', '美术', '3', '3');
INSERT INTO `sys_data` VALUES ('36', '健康', '3', '3');
INSERT INTO `sys_data` VALUES ('37', '法制', '3', '3');
INSERT INTO `sys_data` VALUES ('38', '信息技术', '3', '3');
INSERT INTO `sys_data` VALUES ('39', '综合实践', '3', '3');
INSERT INTO `sys_data` VALUES ('40', '语文', '3', '4');
INSERT INTO `sys_data` VALUES ('41', '数学', '3', '4');
INSERT INTO `sys_data` VALUES ('42', '英语', '3', '4');
INSERT INTO `sys_data` VALUES ('43', '品德与社会', '3', '4');
INSERT INTO `sys_data` VALUES ('44', '科学', '3', '4');
INSERT INTO `sys_data` VALUES ('45', '体育', '3', '4');
INSERT INTO `sys_data` VALUES ('46', '音乐', '3', '4');
INSERT INTO `sys_data` VALUES ('47', '美术', '3', '4');
INSERT INTO `sys_data` VALUES ('48', '健康', '3', '4');
INSERT INTO `sys_data` VALUES ('49', '法制', '3', '4');
INSERT INTO `sys_data` VALUES ('50', '信息技术', '3', '4');
INSERT INTO `sys_data` VALUES ('51', '综合实践', '3', '4');
INSERT INTO `sys_data` VALUES ('52', '语文', '3', '5');
INSERT INTO `sys_data` VALUES ('53', '数学', '3', '5');
INSERT INTO `sys_data` VALUES ('54', '英语', '3', '5');
INSERT INTO `sys_data` VALUES ('55', '品德与社会', '3', '5');
INSERT INTO `sys_data` VALUES ('56', '科学', '3', '5');
INSERT INTO `sys_data` VALUES ('57', '体育', '3', '5');
INSERT INTO `sys_data` VALUES ('58', '音乐', '3', '5');
INSERT INTO `sys_data` VALUES ('59', '美术', '3', '5');
INSERT INTO `sys_data` VALUES ('60', '健康', '3', '5');
INSERT INTO `sys_data` VALUES ('61', '法制', '3', '5');
INSERT INTO `sys_data` VALUES ('62', '信息技术', '3', '5');
INSERT INTO `sys_data` VALUES ('63', '综合实践', '3', '5');
INSERT INTO `sys_data` VALUES ('64', '语文', '3', '6');
INSERT INTO `sys_data` VALUES ('65', '数学', '3', '6');
INSERT INTO `sys_data` VALUES ('66', '英语', '3', '6');
INSERT INTO `sys_data` VALUES ('67', '品德与社会', '3', '6');
INSERT INTO `sys_data` VALUES ('68', '科学', '3', '6');
INSERT INTO `sys_data` VALUES ('69', '体育', '3', '6');
INSERT INTO `sys_data` VALUES ('70', '音乐', '3', '6');
INSERT INTO `sys_data` VALUES ('71', '美术', '3', '6');
INSERT INTO `sys_data` VALUES ('72', '健康', '3', '6');
INSERT INTO `sys_data` VALUES ('73', '法制', '3', '6');
INSERT INTO `sys_data` VALUES ('74', '信息技术', '3', '6');
INSERT INTO `sys_data` VALUES ('75', '综合实践', '3', '6');
INSERT INTO `sys_data` VALUES ('76', '语文', '3', '7');
INSERT INTO `sys_data` VALUES ('77', '数学', '3', '7');
INSERT INTO `sys_data` VALUES ('78', '英语', '3', '7');
INSERT INTO `sys_data` VALUES ('79', '品德与社会', '3', '7');
INSERT INTO `sys_data` VALUES ('80', '科学', '3', '7');
INSERT INTO `sys_data` VALUES ('81', '体育', '3', '7');
INSERT INTO `sys_data` VALUES ('82', '音乐', '3', '7');
INSERT INTO `sys_data` VALUES ('83', '美术', '3', '7');
INSERT INTO `sys_data` VALUES ('84', '健康', '3', '7');
INSERT INTO `sys_data` VALUES ('85', '法制', '3', '7');
INSERT INTO `sys_data` VALUES ('86', '信息技术', '3', '7');
INSERT INTO `sys_data` VALUES ('87', '综合实践', '3', '7');
INSERT INTO `sys_data` VALUES ('88', '语文', '3', '9');
INSERT INTO `sys_data` VALUES ('89', '数学', '3', '9');
INSERT INTO `sys_data` VALUES ('90', '英语', '3', '9');
INSERT INTO `sys_data` VALUES ('91', '政治', '3', '9');
INSERT INTO `sys_data` VALUES ('92', '历史', '3', '9');
INSERT INTO `sys_data` VALUES ('93', '体育', '3', '9');
INSERT INTO `sys_data` VALUES ('94', '音乐', '3', '9');
INSERT INTO `sys_data` VALUES ('95', '美术', '3', '9');
INSERT INTO `sys_data` VALUES ('96', '劳技', '3', '9');
INSERT INTO `sys_data` VALUES ('97', '生物', '3', '9');
INSERT INTO `sys_data` VALUES ('98', '地理', '3', '9');
INSERT INTO `sys_data` VALUES ('99', '语文', '3', '10');
INSERT INTO `sys_data` VALUES ('100', '数学', '3', '10');
INSERT INTO `sys_data` VALUES ('101', '英语', '3', '10');
INSERT INTO `sys_data` VALUES ('102', '物理', '3', '10');
INSERT INTO `sys_data` VALUES ('103', '政治', '3', '10');
INSERT INTO `sys_data` VALUES ('104', '历史', '3', '10');
INSERT INTO `sys_data` VALUES ('105', '体育', '3', '10');
INSERT INTO `sys_data` VALUES ('106', '音乐', '3', '10');
INSERT INTO `sys_data` VALUES ('107', '美术', '3', '10');
INSERT INTO `sys_data` VALUES ('108', '劳技', '3', '10');
INSERT INTO `sys_data` VALUES ('109', '生物', '3', '10');
INSERT INTO `sys_data` VALUES ('110', '地理', '3', '10');
INSERT INTO `sys_data` VALUES ('111', '语文', '3', '11');
INSERT INTO `sys_data` VALUES ('112', '数学', '3', '11');
INSERT INTO `sys_data` VALUES ('113', '英语', '3', '11');
INSERT INTO `sys_data` VALUES ('114', '物理', '3', '11');
INSERT INTO `sys_data` VALUES ('115', '化学', '3', '11');
INSERT INTO `sys_data` VALUES ('116', '政治', '3', '11');
INSERT INTO `sys_data` VALUES ('117', '历史', '3', '11');
INSERT INTO `sys_data` VALUES ('118', '体育', '3', '11');
INSERT INTO `sys_data` VALUES ('119', '音乐', '3', '11');
INSERT INTO `sys_data` VALUES ('120', '美术', '3', '11');
INSERT INTO `sys_data` VALUES ('121', '劳技', '3', '11');
INSERT INTO `sys_data` VALUES ('122', '生物', '3', '11');
INSERT INTO `sys_data` VALUES ('123', '地理', '3', '11');
INSERT INTO `sys_data` VALUES ('124', '语文', '3', '13');
INSERT INTO `sys_data` VALUES ('125', '数学', '3', '13');
INSERT INTO `sys_data` VALUES ('126', '英语', '3', '13');
INSERT INTO `sys_data` VALUES ('127', '物理', '3', '13');
INSERT INTO `sys_data` VALUES ('128', '化学', '3', '13');
INSERT INTO `sys_data` VALUES ('129', '政治', '3', '13');
INSERT INTO `sys_data` VALUES ('130', '历史', '3', '13');
INSERT INTO `sys_data` VALUES ('131', '体育', '3', '13');
INSERT INTO `sys_data` VALUES ('132', '音乐', '3', '13');
INSERT INTO `sys_data` VALUES ('133', '美术', '3', '13');
INSERT INTO `sys_data` VALUES ('134', '劳技', '3', '13');
INSERT INTO `sys_data` VALUES ('135', '生物', '3', '13');
INSERT INTO `sys_data` VALUES ('136', '地理', '3', '13');
INSERT INTO `sys_data` VALUES ('137', '语文', '3', '14');
INSERT INTO `sys_data` VALUES ('138', '数学', '3', '14');
INSERT INTO `sys_data` VALUES ('139', '英语', '3', '14');
INSERT INTO `sys_data` VALUES ('140', '物理', '3', '14');
INSERT INTO `sys_data` VALUES ('141', '化学', '3', '14');
INSERT INTO `sys_data` VALUES ('142', '政治', '3', '14');
INSERT INTO `sys_data` VALUES ('143', '历史', '3', '14');
INSERT INTO `sys_data` VALUES ('144', '体育', '3', '14');
INSERT INTO `sys_data` VALUES ('145', '音乐', '3', '14');
INSERT INTO `sys_data` VALUES ('146', '美术', '3', '14');
INSERT INTO `sys_data` VALUES ('147', '劳技', '3', '14');
INSERT INTO `sys_data` VALUES ('148', '生物', '3', '14');
INSERT INTO `sys_data` VALUES ('149', '地理', '3', '14');
INSERT INTO `sys_data` VALUES ('150', '语文', '3', '15');
INSERT INTO `sys_data` VALUES ('151', '数学', '3', '15');
INSERT INTO `sys_data` VALUES ('152', '英语', '3', '15');
INSERT INTO `sys_data` VALUES ('153', '物理', '3', '15');
INSERT INTO `sys_data` VALUES ('154', '化学', '3', '15');
INSERT INTO `sys_data` VALUES ('155', '政治', '3', '15');
INSERT INTO `sys_data` VALUES ('156', '历史', '3', '15');
INSERT INTO `sys_data` VALUES ('157', '体育', '3', '15');
INSERT INTO `sys_data` VALUES ('158', '音乐', '3', '15');
INSERT INTO `sys_data` VALUES ('159', '美术', '3', '15');
INSERT INTO `sys_data` VALUES ('160', '劳技', '3', '15');
INSERT INTO `sys_data` VALUES ('161', '生物', '3', '15');
INSERT INTO `sys_data` VALUES ('162', '地理', '3', '15');

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
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('00e9147474ab48f79c0ef89269008234', '1', 'admin', '后台用户启用禁用', 'admin/updateAdminState', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 12:07:55', '0', '启用/禁用');
INSERT INTO `sys_log` VALUES ('0129677b013d4594a2735724570032a3', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:08:59', '0', '修改');
INSERT INTO `sys_log` VALUES ('021aa6614a09466ba22ca09b886f8492', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:21:54', '0', '修改');
INSERT INTO `sys_log` VALUES ('029a1cb7d49544278f3039628b03f7cf', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:06:26', '0', '修改');
INSERT INTO `sys_log` VALUES ('0321311a17c240b0ba2aa9e3f4d1e792', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:56:32', '0', '修改');
INSERT INTO `sys_log` VALUES ('0396a45de2b040ceba36cdd568002add', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '183.15.181.42', '443', null, '2', '2019-01-19 09:51:26', '0', '修改');
INSERT INTO `sys_log` VALUES ('039e7a39c8c14232a4f74add7bf2b6db', '1', 'admin', '删除营销素材-学生端首页', '/manage/MarketingBanner/removeMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:52:31', '0', '删除');
INSERT INTO `sys_log` VALUES ('06085a1afdcc4dc8b2a4c1ce2dc0956c', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:56:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('0699418f78fc4fe2bea52c60e4e96941', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 14:19:09', '0', '修改');
INSERT INTO `sys_log` VALUES ('08963205042c444db8a66f75287b8014', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:17:23', '0', '修改');
INSERT INTO `sys_log` VALUES ('091aed93c1d543559afa74410264450d', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:23:57', '0', '修改');
INSERT INTO `sys_log` VALUES ('09f6159aeca64edcaafa80acf7603b18', '1', 'admin', '删除用户表', 'admin/removeSysUser', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:11:16', '0', '删除');
INSERT INTO `sys_log` VALUES ('0c873b2fbdc1440687112898ffe0a104', '1', 'admin', '后台用户启用禁用', 'admin/updateAdminState', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 11:59:06', '0', '启用/禁用');
INSERT INTO `sys_log` VALUES ('0d8d756187d945c89ba09fbb883c84dd', '1', 'admin', '隐藏或显示老师课程', 'manage/updateCourseType', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 14:16:14', '0', '修改');
INSERT INTO `sys_log` VALUES ('0da419501e83481baf4deba7016f8dcf', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:21:42', '0', '修改');
INSERT INTO `sys_log` VALUES ('0de67281dd974b04873c873d91877994', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:05:30', '0', '修改');
INSERT INTO `sys_log` VALUES ('0f27be699a4b4a4d99d5c6780bc32263', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:43:20', '0', '修改');
INSERT INTO `sys_log` VALUES ('119a11f6989444bdab37669418cc4327', '1', 'admin', '后台用户启用禁用', 'admin/updateAdminState', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 12:06:58', '0', '启用/禁用');
INSERT INTO `sys_log` VALUES ('11d628e4dee246bc876e75397ad38903', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 10:40:30', '0', '修改');
INSERT INTO `sys_log` VALUES ('1227036ff7bf4e21abe4f7418cd0fed9', '1', 'admin', '修改营销素材-学生端首页', '/manage/MarketingBanner/updateMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:51:42', '0', '修改');
INSERT INTO `sys_log` VALUES ('12335bfcdfb04175ae41c35537f7d811', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 14:48:54', '0', '修改');
INSERT INTO `sys_log` VALUES ('13476e380ee6414dbf5eb614699e9921', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:28:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('140f0e2a5a60455199bb37d2e2165d7c', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:05:54', '0', '修改');
INSERT INTO `sys_log` VALUES ('14db6f2358264d1d94e184b3a14299d5', '1', 'admin', '添加优惠券', '/manage/Coupon/addCoupon', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '192.168.1.142', '8080', null, '2', '2019-01-21 09:35:45', '0', '添加');
INSERT INTO `sys_log` VALUES ('14ffe0eab8e74ed083bfd405c00375c0', '1', 'admin', '删除老师表', '/manage/Teacher/removeTeacher', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '113.88.96.10', '443', null, '2', '2019-01-18 14:51:07', '0', '删除');
INSERT INTO `sys_log` VALUES ('1577e0e3b24443feb387301e70b82497', '14', '钟旭春', '修改个人信息', '/teacher/teacherInfo/updateTeacherInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 15:09:08', '0', '修改');
INSERT INTO `sys_log` VALUES ('16404e95631e4a0e81e31598056b428b', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:32:02', '0', '修改');
INSERT INTO `sys_log` VALUES ('16a5a2e83c7d41d1bd6b9ce5b1899a1f', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:43:58', '0', '添加');
INSERT INTO `sys_log` VALUES ('16d4fb7c0e7a4a6da23f521a9e3659ac', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.75', '443', null, '2', '2019-01-21 14:00:47', '0', '修改');
INSERT INTO `sys_log` VALUES ('17206efbd9b1423e99f84e188c42c2b3', '13', '接近让妈妈', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:34:44', '0', '修改');
INSERT INTO `sys_log` VALUES ('1777b2171f7b4380ba32e28d6f79828c', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:08:15', '0', '修改');
INSERT INTO `sys_log` VALUES ('189025b3c4734dd99d5218c867f20618', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:48:03', '0', '删除');
INSERT INTO `sys_log` VALUES ('19890eaeeefe4a21b75917d12996073c', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:35:58', '0', '修改');
INSERT INTO `sys_log` VALUES ('1af20b40211c47b4a9dace9f6c6d30b3', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:12:25', '0', '修改');
INSERT INTO `sys_log` VALUES ('1c4f222fbbf04267ae95983b8a123c51', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:06:58', '0', '修改');
INSERT INTO `sys_log` VALUES ('1c9bbaf0735b4be9a77239ea5d85cd85', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 14:45:32', '0', '修改');
INSERT INTO `sys_log` VALUES ('1ca54ca104b64a05aeb81c0764655d76', '1', 'admin', '删除营销素材-学生端首页', '/manage/MarketingBanner/removeMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:52:51', '0', '删除');
INSERT INTO `sys_log` VALUES ('1cb41b4b290247d5bb01226d67bfee11', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '117.136.40.216', '443', null, '2', '2019-01-21 14:39:01', '0', '修改');
INSERT INTO `sys_log` VALUES ('1d742eddbc4b47549697d36835bb3b9b', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:59:41', '0', '修改');
INSERT INTO `sys_log` VALUES ('1f174f9d32ce4075ab11a7213f7871d2', '11', '黄世杰', '保存返回的频道号', '/teacher/teacherCourses/setUpchannelNo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 16:44:05', '0', '修改');
INSERT INTO `sys_log` VALUES ('1f25c408f47643b4976750f8c9262bd8', '11', '黄世杰', '保存返回的频道号', '/teacher/teacherCourses/setUpchannelNo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:39:59', '0', '修改');
INSERT INTO `sys_log` VALUES ('1f62e371af294c2fbbb250f0b2e435fb', '13', '接近让妈妈', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:18:50', '0', '修改');
INSERT INTO `sys_log` VALUES ('1fa87afe393343e98ea1d2424655fc55', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 14:44:47', '0', '修改');
INSERT INTO `sys_log` VALUES ('22249ce2a43e44b7b00c97d80eec0b67', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:28:44', '0', '添加');
INSERT INTO `sys_log` VALUES ('226b903a8a134113a110a3ca1018e08d', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:46:25', '0', '添加');
INSERT INTO `sys_log` VALUES ('227d5f139a3d4ff595a28517ed39ef43', '1', 'admin', '删除营销素材-学生端首页', '/manage/MarketingBanner/removeMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:53:18', '0', '删除');
INSERT INTO `sys_log` VALUES ('229217fc35564f99ae8720ebec46cab8', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ4MDUwNzY1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NTU2NSwibmJmIjoxNTQ4MDUwNzY1fQ.m0ufOkTpqi13v8RBc0zwK8epyuZ8eTHvSk84YzVbNGY', '183.15.181.75', '443', null, '2', '2019-01-21 15:07:58', '0', '修改');
INSERT INTO `sys_log` VALUES ('22b9fe42736644d1a0cc4d0e853387c0', '5', '黄少杰', '完善审核信息', '/teacher/teacherLogin/perfectInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1Iiwic3ViIjoie1wicGhvbmVcIjpcIjEzOTI2NTEzMjg4XCIsXCJ1c2VyTmFtZVwiOlwi6buE5bCR5p2wXCIsXCJ1c2VySWRcIjo1fSIsImlhdCI6MTU0NzcwNjY3NiwiaXNzIjoiUDEzOTI2NTEzMjg4IiwiYXVkIjoiYXVkaWVuY2UiLCJleHAiOjE1NDgzMTE0NzYsIm5iZiI6MTU0NzcwNjY3Nn0.FqmGeTrESbQCgBrGHX_trZsV8knGE-M59mwCnrjJ754', '113.88.96.10', '443', null, '2', '2019-01-18 09:52:55', '0', '完善审核信息');
INSERT INTO `sys_log` VALUES ('23f0db58f9704381992d4396c6b9714a', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 14:44:48', '0', '修改');
INSERT INTO `sys_log` VALUES ('244585a0277148178c9dfce504afd41e', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:01:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('2642d8ffe74b4758b0d44a874d45c760', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:36:39', '0', '修改');
INSERT INTO `sys_log` VALUES ('270444989a724c4e9533f73c0bb7f7ff', '12', '钟旭春', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:31:24', '0', '添加');
INSERT INTO `sys_log` VALUES ('272d10da940e4fd68f64b5da6c628a71', '1', 'admin', '删除用户表', 'admin/removeSysUser', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:11:18', '0', '删除');
INSERT INTO `sys_log` VALUES ('28f6b699dbdb459a89e25e0a6c555e12', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 14:02:22', '0', '修改');
INSERT INTO `sys_log` VALUES ('29f776f40c1d49efa47f878588069fa0', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:10:48', '0', '修改');
INSERT INTO `sys_log` VALUES ('2ab417ee8c274515a37d0fdd8f72101a', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:24:03', '0', '修改');
INSERT INTO `sys_log` VALUES ('2ac35e68f57c4e81985d6d0489236af4', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:03:28', '0', '修改');
INSERT INTO `sys_log` VALUES ('2c341079bf124aa9982c55c2d0fb5f00', '1', 'admin', '添加营销素材-难题', '/manage/MarketingQuestion/addMarketingQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:34:09', '0', '添加');
INSERT INTO `sys_log` VALUES ('2c40558f73d2473c8d3d0a8676bc9b9c', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:43:21', '0', '删除');
INSERT INTO `sys_log` VALUES ('2edced9c82824efab225bd755467bab8', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:07:23', '0', '修改');
INSERT INTO `sys_log` VALUES ('2eeed71cb9e64514bf30f4f1caf97f88', '12', '钟旭春', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:39:09', '0', '修改');
INSERT INTO `sys_log` VALUES ('2f67fc1350cc46d293f4770cb81958fe', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 15:00:52', '0', '修改');
INSERT INTO `sys_log` VALUES ('3099926c5ab7466f9fb63e73f8e4f92c', '11', '黄世杰', '添加课程', '/teacher/teacherReleaseCourse/ReleaseCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ4MDUwNzY1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NTU2NSwibmJmIjoxNTQ4MDUwNzY1fQ.m0ufOkTpqi13v8RBc0zwK8epyuZ8eTHvSk84YzVbNGY', '183.15.181.75', '443', null, '2', '2019-01-21 14:55:03', '0', '添加');
INSERT INTO `sys_log` VALUES ('319fd6f2b16146f08199509dfd7f6ff3', '1', 'admin', '删除课程订单', 'admin/removeOrderCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 18:16:58', '0', '删除');
INSERT INTO `sys_log` VALUES ('3239ed8dea1b4f3199c5fe4a9a48007b', '13', '接近让妈妈', '保存返回的频道号', '/teacher/teacherCourses/setUpchannelNo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:42:06', '0', '修改');
INSERT INTO `sys_log` VALUES ('333f79d546504079957e02dc51e2f69a', '1', 'admin', '隐藏或显示老师课程', 'manage/updateCourseType', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:56:56', '0', '修改');
INSERT INTO `sys_log` VALUES ('345189f52c2e44a1806f5ff605a680e9', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:16:05', '0', '修改');
INSERT INTO `sys_log` VALUES ('34d43816c7364583a2dc8d9d82bd88b0', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:27:44', '0', '修改');
INSERT INTO `sys_log` VALUES ('35c5509d926c46aa94683155401a2935', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:00:00', '0', '修改');
INSERT INTO `sys_log` VALUES ('37073a593a494920a00196e03d8ddb56', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 14:48:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('38818e30873b4bbbb525c3db4ee30eda', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 10:34:35', '0', '修改');
INSERT INTO `sys_log` VALUES ('38890216909d42c7adf17f8fba389600', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 10:54:25', '0', '修改');
INSERT INTO `sys_log` VALUES ('389200db68494d36841c3a244b36747b', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:45:33', '0', '修改');
INSERT INTO `sys_log` VALUES ('39a366803b384075a571df1b2fb90a5b', '1', 'admin', '添加优惠券', '/manage/Coupon/addCoupon', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '192.168.1.142', '8080', null, '2', '2019-01-21 09:45:42', '0', '添加');
INSERT INTO `sys_log` VALUES ('39cac486cfee4c5fabffbdb7a20b70b1', '13', '接近让妈妈', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:42:18', '0', '添加');
INSERT INTO `sys_log` VALUES ('3a0942e8efc946e88946bfd114b5d48c', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 12:03:44', '0', '修改');
INSERT INTO `sys_log` VALUES ('3a797131d820458e8ac3eb3a8c8c15b2', '1', 'admin', '隐藏或显示老师课程', 'manage/updateCourseType', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:55:31', '0', '修改');
INSERT INTO `sys_log` VALUES ('3b1c1d9385434d78b3e0bf7adec2d553', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:17:25', '0', '修改');
INSERT INTO `sys_log` VALUES ('3b4a62adba3945bdb113e51dca91ed52', '1', 'admin', '添加营销素材-课程', '/manage/MarketingCourse/addMarketingCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:32:49', '0', '添加');
INSERT INTO `sys_log` VALUES ('3c976c735afc4771aad720e61f53169a', '1', 'admin', '添加用户表', 'admin/addSysUser', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 18:00:32', '0', '添加');
INSERT INTO `sys_log` VALUES ('3ca0673fe3a14267aaab4e1e89c51acd', '14', '钟旭春', '修改疑难收费', '/teacher/teacherProblemSolving/setProblemSolving', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:51:43', '0', '修改');
INSERT INTO `sys_log` VALUES ('3cbb3c988cd742bdae61592a32139f6a', '13', '接近让妈妈', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:19:06', '0', '修改');
INSERT INTO `sys_log` VALUES ('3f880ffb49ae44d6b0cc0dbf8359f006', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:46:53', '0', '修改');
INSERT INTO `sys_log` VALUES ('3f925a3524734710b488b2407e2741c5', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:33:48', '0', '删除');
INSERT INTO `sys_log` VALUES ('3faf675d1de54554a43fa14b1de740b1', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:33:53', '0', '删除');
INSERT INTO `sys_log` VALUES ('414cdcb31d45439387dcd6cd7621ed3b', '11', '黄世杰', '修改视频路径', '/teacher/teacherCourses/keepVideoUrl', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 09:21:11', '0', '修改');
INSERT INTO `sys_log` VALUES ('417d318f8d264231bfe64edf1a735381', '5', '黄少杰', '存在用户时登录,不存在用户时注册', '/teacher/teacherLogin/login', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1Iiwic3ViIjoie1wicGhvbmVcIjpcIjEzOTI2NTEzMjg4XCIsXCJ1c2VyTmFtZVwiOlwi6buE5bCR5p2wXCIsXCJ1c2VySWRcIjo1fSIsImlhdCI6MTU0NzcwNjY3NiwiaXNzIjoiUDEzOTI2NTEzMjg4IiwiYXVkIjoiYXVkaWVuY2UiLCJleHAiOjE1NDgzMTE0NzYsIm5iZiI6MTU0NzcwNjY3Nn0.FqmGeTrESbQCgBrGHX_trZsV8knGE-M59mwCnrjJ754', '113.88.96.10', '443', null, '2', '2019-01-18 09:51:30', '0', '登录/注册');
INSERT INTO `sys_log` VALUES ('428e13c36e5448298e74b7b82c813fc2', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:52:14', '0', '添加');
INSERT INTO `sys_log` VALUES ('430ce2a2bb4440679611037e240baeeb', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:20:59', '0', '修改');
INSERT INTO `sys_log` VALUES ('43eb09e187cf4d56ab72d517fcb366c5', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:41:05', '0', '修改');
INSERT INTO `sys_log` VALUES ('442933c322004375acd0bc966c1f80ae', '12', '钟旭春', '修改视频路径', '/teacher/teacherCourses/keepVideoUrl', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:32:36', '0', '修改');
INSERT INTO `sys_log` VALUES ('44958963adad438a8620606394b21526', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:08:44', '0', '修改');
INSERT INTO `sys_log` VALUES ('45be8a143ced412783a18270edd5756b', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:02:24', '0', '修改');
INSERT INTO `sys_log` VALUES ('45fbe3ba50714120b43de8d9f14896e0', '1', 'admin', '隐藏或显示老师课程', 'manage/updateCourseType', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 14:16:51', '0', '修改');
INSERT INTO `sys_log` VALUES ('45fed0c276ff4b1ba6b6873b0ffbae21', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:16:34', '0', '修改');
INSERT INTO `sys_log` VALUES ('460b2399cdd54641988c404f15b00c47', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:42:52', '0', '添加');
INSERT INTO `sys_log` VALUES ('46294580bf7246ce9938d2caa3b7a74a', '1', 'admin', '添加营销素材-课程', '/manage/MarketingCourse/addMarketingCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 13:51:31', '0', '添加');
INSERT INTO `sys_log` VALUES ('471f899f0dc8481da756a5205404cbbb', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:02:22', '0', '修改');
INSERT INTO `sys_log` VALUES ('474a7ba9808c4653a337f5b240e5d22d', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:45:45', '0', '删除');
INSERT INTO `sys_log` VALUES ('4896c5b0be0e4be8aeca1d67b6778bf4', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:18:08', '0', '修改');
INSERT INTO `sys_log` VALUES ('489ffc25a33c4d4aa7ab1d56555caa60', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '183.15.181.42', '443', null, '2', '2019-01-19 09:46:53', '0', '修改');
INSERT INTO `sys_log` VALUES ('48bf522012754940af72ed63eacf8131', '1', 'admin', '隐藏或显示老师课程', 'manage/updateCourseType', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 12:03:30', '0', '修改');
INSERT INTO `sys_log` VALUES ('49bb1a125cd944babf7c12e4ce6ebecd', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:24:10', '0', '修改');
INSERT INTO `sys_log` VALUES ('49ceab166a6048c796ea2af58d8d8f5d', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:21:14', '0', '修改');
INSERT INTO `sys_log` VALUES ('4b23e16e9ef944c88a87e2a6a88ef808', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:22:30', '0', '修改');
INSERT INTO `sys_log` VALUES ('4bd47947f0fc4f49afe1975e8f76cd02', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 16:45:02', '0', '添加');
INSERT INTO `sys_log` VALUES ('4c4c25fab5ba40938657dd04619f8c2f', '1', 'admin', '修改老师表', '/manage/Teacher/updateTeacher', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:57:43', '0', '修改');
INSERT INTO `sys_log` VALUES ('4ef3944b57b24bc58ec232f6555a212c', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:33:28', '0', '修改');
INSERT INTO `sys_log` VALUES ('4f735e75523a4639b4c34543883d8969', '12', '钟旭春', '存在用户时登录,不存在用户时注册', '/teacher/teacherLogin/login', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '183.15.181.75', '443', null, '2', '2019-01-21 13:47:48', '0', '登录/注册');
INSERT INTO `sys_log` VALUES ('5019b0b1a67442f1846f3c67a36809a2', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:09:29', '0', '修改');
INSERT INTO `sys_log` VALUES ('5058944c1aaf4ad6bdf88569dcaa8742', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:42:35', '0', '修改');
INSERT INTO `sys_log` VALUES ('50681c865ab24bd9ac1e2ff8de194ef4', '1', 'admin', '修改系统参数设置表', '/manage/ApplicationParameter/updateApplicationParameter', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '113.88.96.10', '443', null, '2', '2019-01-18 14:26:31', '0', '修改');
INSERT INTO `sys_log` VALUES ('50f5d7759a724d1dbd80d6b3bafa1ee3', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:31:03', '0', '修改');
INSERT INTO `sys_log` VALUES ('51f5d705641a4827855f4413c976d295', '1', 'admin', ' 审核教师', 'admin/examineUserInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:23:20', '0', '修改');
INSERT INTO `sys_log` VALUES ('52028464aa9f4543a42cf69ddf6b75ce', '5', '黄少杰', '存在用户时登录,不存在用户时注册', '/teacher/teacherLogin/login', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1Iiwic3ViIjoie1wicGhvbmVcIjpcIjEzOTI2NTEzMjg4XCIsXCJ1c2VyTmFtZVwiOlwi6buE5bCR5p2wXCIsXCJ1c2VySWRcIjo1fSIsImlhdCI6MTU0NzE5ODU0NywiaXNzIjoiUDEzOTI2NTEzMjg4IiwiYXVkIjoiYXVkaWVuY2UiLCJleHAiOjE1NDc4MDMzNDcsIm5iZiI6MTU0NzE5ODU0N30.bNc2zg_Np6OBGvrQvPOr3hbocKeAit5DYuiakuc3vi0', '117.136.40.204', '443', null, '2', '2019-01-18 14:27:25', '0', '登录/注册');
INSERT INTO `sys_log` VALUES ('53cfde54500e4431b631a84fde3a4865', '9', '钟旭春', '完善审核信息', '/teacher/teacherLogin/perfectInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5Iiwic3ViIjoie1wicGhvbmVcIjpcIjE4ODE5MDE1MjAxXCIsXCJ1c2VyTmFtZVwiOlwi6ZKf5pet5pilXCIsXCJ1c2VySWRcIjo5fSIsImlhdCI6MTU0NzY4ODU4NiwiaXNzIjoiUjE4ODE5MDE1MjAxIiwiYXVkIjoiYXVkaWVuY2UiLCJleHAiOjE1NDgyOTMzODYsIm5iZiI6MTU0NzY4ODU4Nn0.eXZYaMN8YYreREJZO2xwFh93uhsLIzNHhbwxrFa9-GY', '113.88.96.10', '443', null, '2', '2019-01-18 10:23:05', '0', '完善审核信息');
INSERT INTO `sys_log` VALUES ('5410206c7aee40c8b5f00cb4809b468d', '13', '接近让妈妈', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:13:59', '0', '修改');
INSERT INTO `sys_log` VALUES ('55063bd0197144fb9a93d64d65d5a892', '1', 'admin', '删除营销素材-课程', '/manage/MarketingCourse/removeMarketingCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:53:30', '0', '删除');
INSERT INTO `sys_log` VALUES ('555d8380a156416a827cb924508236e8', '12', '钟旭春', '修改个人信息', '/teacher/teacherInfo/updateTeacherInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 13:57:17', '0', '修改');
INSERT INTO `sys_log` VALUES ('556573b750474a308365778e1b239797', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 09:59:11', '0', '修改');
INSERT INTO `sys_log` VALUES ('564129daf35f4d378fadd55ddaa16c95', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 10:41:40', '0', '修改');
INSERT INTO `sys_log` VALUES ('5666f6399efa4bff979735a2593d6180', '12', '钟旭春', '保存返回的频道号', '/teacher/teacherCourses/setUpchannelNo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:30:31', '0', '修改');
INSERT INTO `sys_log` VALUES ('5777f2dad20648a7a101c0a95c31e8fc', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 17:04:16', '0', '修改');
INSERT INTO `sys_log` VALUES ('579ad981cc6a46e0a4137643624e96c5', '12', '钟旭春', '修改疑难收费', '/teacher/teacherProblemSolving/setProblemSolving', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 10:45:09', '0', '修改');
INSERT INTO `sys_log` VALUES ('57f33e35ccc943bb8c98e29fb7396264', '1', 'admin', '删除学生表', '/manage/Student/removeStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '183.15.181.75', '443', null, '2', '2019-01-21 13:46:08', '0', '删除');
INSERT INTO `sys_log` VALUES ('599b9963ffa64c559c98298b0fb3a034', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:14:33', '0', '修改');
INSERT INTO `sys_log` VALUES ('5a1743098ea44b04abb7e08070227c5c', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:36:09', '0', '修改');
INSERT INTO `sys_log` VALUES ('5b849a5ea08d4d23b54eb50e2930f278', '1', 'admin', '修改提现状态', '/manage/Teacher/updateTeacherWithdrawalStatus', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 18:22:40', '0', '修改');
INSERT INTO `sys_log` VALUES ('5b9025401cc34a2587e7c50c77857032', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:41:44', '0', '添加');
INSERT INTO `sys_log` VALUES ('5bf9ab11037e48808380703dd17aefdd', '1', 'admin', '隐藏或显示老师课程', 'manage/updateCourseType', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:57:17', '0', '修改');
INSERT INTO `sys_log` VALUES ('5c0338dd7f2b47e9a59f50e43a35a014', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:36:34', '0', '修改');
INSERT INTO `sys_log` VALUES ('5d0eb9b06a1d43fdb62d2d5e79a88399', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:29:02', '0', '修改');
INSERT INTO `sys_log` VALUES ('5d8d1defb7ba439c8a792b78976845ad', '1', 'admin', '删除用户表', 'admin/removeSysUser', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:11:20', '0', '删除');
INSERT INTO `sys_log` VALUES ('5e0b6b017d0c4e7cba5d72090731890c', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 17:04:30', '0', '修改');
INSERT INTO `sys_log` VALUES ('5fd63a3e94b0431cae9ad997f2db98e9', '14', '钟旭春', '修改疑难收费', '/teacher/teacherProblemSolving/setProblemSolving', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:52:12', '0', '修改');
INSERT INTO `sys_log` VALUES ('60d180d395f84504a1b55fb62164d3df', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 14:46:55', '0', '修改');
INSERT INTO `sys_log` VALUES ('61379284723b45e4b317fba88885c6a6', '1', 'admin', '删除营销素材-学生端首页', '/manage/MarketingBanner/removeMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:27:20', '0', '删除');
INSERT INTO `sys_log` VALUES ('61b9a88c56f141d5b946242f5b4efd49', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:14:17', '0', '修改');
INSERT INTO `sys_log` VALUES ('6293799e0ec844388ef35ff54c74b75f', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:44:36', '0', '修改');
INSERT INTO `sys_log` VALUES ('63157cecd2e84dd69302419042e112b4', '13', '接近让妈妈', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:15:22', '0', '修改');
INSERT INTO `sys_log` VALUES ('6421d4004b164e05afe38cc0312d7116', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 16:46:55', '0', '添加');
INSERT INTO `sys_log` VALUES ('646028dc99644a23a79ef095dd8ba3d6', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:06:48', '0', '修改');
INSERT INTO `sys_log` VALUES ('654b4b92f08249a0bd276af50aaa78b9', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:26:48', '0', '添加');
INSERT INTO `sys_log` VALUES ('655de70470874d3c853136cab7f4b0c5', '1', 'admin', '后台用户启用禁用', 'admin/updateAdminState', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 12:07:57', '0', '启用/禁用');
INSERT INTO `sys_log` VALUES ('65e76217a94b4d5bbc2bd259a2f4dc69', '1', 'admin', '删除角色表', 'admin/removeSysRole', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:37:29', '0', '删除');
INSERT INTO `sys_log` VALUES ('66e5b55179854ed59c826efa651e367b', '14', '钟旭春', '修改个人信息', '/teacher/teacherInfo/updateTeacherInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:50:57', '0', '修改');
INSERT INTO `sys_log` VALUES ('678ea8a32e44457581accb4a11216664', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 10:30:11', '0', '修改');
INSERT INTO `sys_log` VALUES ('67f9cc240e1a44e8a7ddfd57d82c42b7', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:25:15', '0', '修改');
INSERT INTO `sys_log` VALUES ('6843a69a1f86492ba7b64d64125d8307', '12', '钟旭春', '修改疑难收费', '/teacher/teacherProblemSolving/setProblemSolving', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:53:14', '0', '修改');
INSERT INTO `sys_log` VALUES ('68549e734df74abca91c391edf8a6e8a', '1', 'admin', '后台用户启用禁用', 'admin/updateAdminState', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 12:00:23', '0', '启用/禁用');
INSERT INTO `sys_log` VALUES ('6998452d5e6e4237879832752ed8c213', '14', '钟旭春', '修改疑难收费', '/teacher/teacherProblemSolving/setProblemSolving', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:52:03', '0', '修改');
INSERT INTO `sys_log` VALUES ('6cb96b62d2a4458d99479e716cb39779', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:17:46', '0', '修改');
INSERT INTO `sys_log` VALUES ('6ce4a8437751412aada7624c461b0dbd', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 14:42:25', '0', '修改');
INSERT INTO `sys_log` VALUES ('6d69ebf5b2e64e999caa568c908f7ac5', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 10:54:24', '0', '修改');
INSERT INTO `sys_log` VALUES ('6d7714d36b6a465fbeb7339e0b885c20', '12', '钟旭春', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:30:48', '0', '添加');
INSERT INTO `sys_log` VALUES ('6d9949ae6a5c4015a2e0a549f9a01c9e', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:43:24', '0', '删除');
INSERT INTO `sys_log` VALUES ('6e681b32881542bdb49323e861a87470', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:15:41', '0', '修改');
INSERT INTO `sys_log` VALUES ('6e6f1f0b1e5d4ff09785daf825756f4d', '1', 'admin', '添加优惠券', '/manage/Coupon/addCoupon', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:53:14', '0', '添加');
INSERT INTO `sys_log` VALUES ('6ee673e716e841b08bce931a232b4f6d', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:47:07', '0', '修改');
INSERT INTO `sys_log` VALUES ('6f86e529cc7f48b987268a094d64c24c', '1', 'admin', '删除课程', 'manage/removeCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:56:44', '0', '删除');
INSERT INTO `sys_log` VALUES ('6fe33c7bd8734497aea3f3eadfce0ebf', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:21:09', '0', '修改');
INSERT INTO `sys_log` VALUES ('70085b6703cc43a48513a1e90d39bef6', '13', '接近让妈妈', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 18:19:12', '0', '修改');
INSERT INTO `sys_log` VALUES ('701d1a8aeffe4bac96364cc639f1d0ce', '1', 'admin', '添加优惠券', '/manage/Coupon/addCoupon', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:38:52', '0', '添加');
INSERT INTO `sys_log` VALUES ('70f87634fae4422ca7930034a03feed3', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 10:40:30', '0', '修改');
INSERT INTO `sys_log` VALUES ('73a13eee3c7c422ba5dfc02719f76248', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:17:19', '0', '修改');
INSERT INTO `sys_log` VALUES ('74fc409be2864e6aa5d6e9bbabf88731', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:25:24', '0', '修改');
INSERT INTO `sys_log` VALUES ('76343f83cd804fab8d2ea6c5c9560ff8', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:17:24', '0', '修改');
INSERT INTO `sys_log` VALUES ('7649d3530b9d4111bb6e3eeef2bf529c', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:52:48', '0', '添加');
INSERT INTO `sys_log` VALUES ('769204f334a247b0a1010421cc553d92', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:08:45', '0', '修改');
INSERT INTO `sys_log` VALUES ('777922fe4eec4a89b35dbc147ee16403', '1', 'admin', '删除营销素材-学生端首页', '/manage/MarketingBanner/removeMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:10:41', '0', '删除');
INSERT INTO `sys_log` VALUES ('7972837a1f3845499d346846a071e046', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:20:58', '0', '修改');
INSERT INTO `sys_log` VALUES ('798b5277ec8f4eb2b3fcfceef0e254f8', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 14:11:56', '0', '修改');
INSERT INTO `sys_log` VALUES ('7a7fa675f50145b390e233d0236322a1', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:43:48', '0', '修改');
INSERT INTO `sys_log` VALUES ('7b586ea8b8d84e3e901707f80bda83b3', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 09:33:21', '0', '修改');
INSERT INTO `sys_log` VALUES ('7bd7c34c2bfc46989da19a2419ef1384', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:44:36', '0', '修改');
INSERT INTO `sys_log` VALUES ('7dae0c3e83a74daeb61b000b77987113', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:33:30', '0', '修改');
INSERT INTO `sys_log` VALUES ('7dc8bb3cc884471b8135d674ad71440c', '1', 'admin', '隐藏或显示老师课程', 'manage/updateCourseType', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:55:42', '0', '修改');
INSERT INTO `sys_log` VALUES ('7e0b42886fcb45a8b136f5730f453fe1', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:20:03', '0', '修改');
INSERT INTO `sys_log` VALUES ('7ee867cae8644d789de77f5e697f577a', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:45:53', '0', '删除');
INSERT INTO `sys_log` VALUES ('80ce52143d524cd89cbe7a3f8858d615', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:24:06', '0', '修改');
INSERT INTO `sys_log` VALUES ('80dea79151584e23b5cde69a1e7ce397', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:07:10', '0', '修改');
INSERT INTO `sys_log` VALUES ('81855c1f5a7b4125af3e6fcbbdda4e32', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 09:59:27', '0', '修改');
INSERT INTO `sys_log` VALUES ('825f8c72c58a4170b9ced8ec0783785f', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 14:11:56', '0', '修改');
INSERT INTO `sys_log` VALUES ('829d191d22144c008936dfa8cd94c6d7', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 14:36:02', '0', '修改');
INSERT INTO `sys_log` VALUES ('82a1add64cb2443a804953567e21fc2f', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:09:47', '0', '修改');
INSERT INTO `sys_log` VALUES ('854f2d4909bf41b3af2248e7f53b712e', '5', '黄少杰', '完善审核信息', '/teacher/teacherLogin/perfectInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1Iiwic3ViIjoie1wicGhvbmVcIjpcIjEzOTI2NTEzMjg4XCIsXCJ1c2VyTmFtZVwiOlwi6buE5bCR5p2wXCIsXCJ1c2VySWRcIjo1fSIsImlhdCI6MTU0NzcwNjY3NiwiaXNzIjoiUDEzOTI2NTEzMjg4IiwiYXVkIjoiYXVkaWVuY2UiLCJleHAiOjE1NDgzMTE0NzYsIm5iZiI6MTU0NzcwNjY3Nn0.FqmGeTrESbQCgBrGHX_trZsV8knGE-M59mwCnrjJ754', '113.88.96.10', '443', null, '2', '2019-01-18 09:52:37', '0', '完善审核信息');
INSERT INTO `sys_log` VALUES ('8579c3915fda420caf914f3e4adba21b', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:52:42', '0', '修改');
INSERT INTO `sys_log` VALUES ('88ed4b04ba94490b9a875771046017b7', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '183.15.181.42', '443', null, '2', '2019-01-19 09:34:21', '0', '修改');
INSERT INTO `sys_log` VALUES ('8955ce3869594bb9b13458fec2c8c517', '1', 'admin', '修改营销素材-学生端首页', '/manage/MarketingBanner/updateMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:54:04', '0', '修改');
INSERT INTO `sys_log` VALUES ('8aaa9a1477014fb9a943260f0dfd8aee', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 14:02:22', '0', '修改');
INSERT INTO `sys_log` VALUES ('8b70de5580254a52aa423f6d47f5749f', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:45:51', '0', '删除');
INSERT INTO `sys_log` VALUES ('8c990d2d808446249f8487d3ce4b6d5d', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:55:27', '0', '修改');
INSERT INTO `sys_log` VALUES ('8cc1deb31a944c4aafc7b4b1707c49ec', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:12:25', '0', '修改');
INSERT INTO `sys_log` VALUES ('8db096992e8643d99e878273c9751fc2', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:45:47', '0', '删除');
INSERT INTO `sys_log` VALUES ('8db5b4cf52ff4f2c9eb2098cc7e012fa', '1', 'admin', '修改提现状态', '/manage/Teacher/updateTeacherWithdrawalStatus', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 18:23:02', '0', '修改');
INSERT INTO `sys_log` VALUES ('8e267c4d3fc149aa86d0baf4304622c4', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:07:44', '0', '修改');
INSERT INTO `sys_log` VALUES ('8f1e0f29c1ff41aeaf493271ef58962c', '13', '接近让妈妈', '添加课程', '/teacher/teacherReleaseCourse/ReleaseCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:35:55', '0', '添加');
INSERT INTO `sys_log` VALUES ('8fd51c2e5cad41c88dc50771540ab021', '13', '接近让妈妈', '修改视频路径', '/teacher/teacherCourses/keepVideoUrl', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:44:23', '0', '修改');
INSERT INTO `sys_log` VALUES ('8fde828fb8c641e897d5d556477fb1b0', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:20:05', '0', '修改');
INSERT INTO `sys_log` VALUES ('913e810630134cde811f17a819387734', '1', 'admin', '删除学生订单', 'manage/removeOrderStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 18:13:28', '0', '删除');
INSERT INTO `sys_log` VALUES ('9157036903bc40fb9cfdcc88dc265d2f', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:54:01', '0', '修改');
INSERT INTO `sys_log` VALUES ('9190bc3b5e68432ca971cd6d83f78b4c', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:19:01', '0', '修改');
INSERT INTO `sys_log` VALUES ('93ff9a41bbd04426a3df4ec567db6ed9', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:07:25', '0', '修改');
INSERT INTO `sys_log` VALUES ('947bda0ff8e343918fc9fcb3a0e9e740', '14', '钟旭春', '修改疑难收费', '/teacher/teacherProblemSolving/setProblemSolving', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:52:18', '0', '修改');
INSERT INTO `sys_log` VALUES ('950ee58122d74553b5978b0361317938', '11', '黄世杰', '修改疑难收费', '/teacher/teacherProblemSolving/setProblemSolving', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:13:57', '0', '修改');
INSERT INTO `sys_log` VALUES ('968ce5495ac64fd79632d8688ee83404', '1', 'admin', '添加问卷题目表', '/manage/Questionnaire/addQuestionnaire', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 18:05:30', '0', '添加');
INSERT INTO `sys_log` VALUES ('991d2757457048a7b0990ba22365fb08', '1', 'admin', '添加优惠券', '/manage/Coupon/addCoupon', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '192.168.1.142', '8080', null, '2', '2019-01-21 09:40:47', '0', '添加');
INSERT INTO `sys_log` VALUES ('99e91237617f4ac1819f59edcaaba22c', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:27:39', '0', '修改');
INSERT INTO `sys_log` VALUES ('9afb8e6c3e7549dfaa4f58c94970b14c', '1', 'admin', ' 审核教师', 'admin/examineUserInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '183.15.181.75', '443', null, '2', '2019-01-21 13:48:41', '0', '修改');
INSERT INTO `sys_log` VALUES ('9c0237abd3684fff878e191c3585b4fb', '1', 'admin', '后台用户登录', 'admin/adminUserLogin', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzcxNjIyNywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODMyMTAyNywibmJmIjoxNTQ3NzE2MjI3fQ.8V41SvOG_LKG6-MampBNPeNrakb6yapJSYRFmKM6teM', '113.88.96.10', '443', null, '2', '2019-01-18 14:31:13', '0', '登录');
INSERT INTO `sys_log` VALUES ('9d147fa45e644474aed9937ce48a4567', '14', '钟旭春', '添加课程', '/teacher/teacherReleaseCourse/ReleaseCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 15:05:58', '0', '添加');
INSERT INTO `sys_log` VALUES ('9e5eaf58e2d74947a2df307436cd381e', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 14:13:06', '0', '修改');
INSERT INTO `sys_log` VALUES ('9f9090981de94ba28737f5e6f5fc59c5', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:39:05', '0', '修改');
INSERT INTO `sys_log` VALUES ('a08e84cc76304ad2a17d575350456fdb', '12', '钟旭春', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:30:39', '0', '添加');
INSERT INTO `sys_log` VALUES ('a165e128756f49deb938a6b875ac9a33', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:43:28', '0', '删除');
INSERT INTO `sys_log` VALUES ('a1660bbc91924216ae69c17682616a89', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 14:35:50', '0', '修改');
INSERT INTO `sys_log` VALUES ('a1868af0f02d4cbea14adef83b90c457', '12', '钟旭春', '完善审核信息', '/teacher/teacherLogin/perfectInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '183.15.181.75', '443', null, '2', '2019-01-21 13:48:29', '0', '完善审核信息');
INSERT INTO `sys_log` VALUES ('a2f91fd4d260486fad528c52ae8c3ab8', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:10:39', '0', '修改');
INSERT INTO `sys_log` VALUES ('a3ead76cb1cc4caf987cbb83b8a16223', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:12:02', '0', '修改');
INSERT INTO `sys_log` VALUES ('a45c39c57d774ea1be8049df1e195a21', '1', 'admin', '修改学生表', '/manage/Student/updateStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 18:01:00', '0', '修改');
INSERT INTO `sys_log` VALUES ('a4ba6f7ab98a42e9ad3c6a37e09d7e9d', '1', 'admin', '修改课程订单', 'admin/updateOrderCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 18:15:33', '0', '修改');
INSERT INTO `sys_log` VALUES ('a4eb23936f3249ab8b60297e93577cc5', '1', '黄世杰', '存在用户时登录,不存在用户时注册', '/teacher/teacherLogin/login', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjEzOTI2NTEzMjc3XCIsXCJ1c2VyTmFtZVwiOlwi6buE5LiW5p2wXCIsXCJ1c2VySWRcIjoxfSIsImlhdCI6MTU0NzY5Mzc5OSwiaXNzIjoiSzEzOTI2NTEzMjc3IiwiYXVkIjoiYXVkaWVuY2UiLCJleHAiOjE1NDgyOTg1OTksIm5iZiI6MTU0NzY5Mzc5OX0.H6aXY1vOXTz9zHybhAiG6rjmW2SVeQ96I-pIZ3qKxtI', '113.88.96.10', '443', null, '2', '2019-01-18 10:53:40', '0', '登录/注册');
INSERT INTO `sys_log` VALUES ('a5317c19b24149419c4b741b9653b34f', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 16:45:27', '0', '添加');
INSERT INTO `sys_log` VALUES ('a53464c3e2604725996cd3670a6d717a', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:02:26', '0', '修改');
INSERT INTO `sys_log` VALUES ('a5ee9fe04c784cea8d0685b198341841', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:48:38', '0', '修改');
INSERT INTO `sys_log` VALUES ('a681806ffaac4eb0aa1a066904f9bb1b', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:05:06', '0', '修改');
INSERT INTO `sys_log` VALUES ('a7f7ebaaf5004ca2b4e9bc3318f6af8e', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:23:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('a800d72883d14ec294d990448ae6b840', '1', 'admin', '删除用户表', 'admin/removeSysUser', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:11:12', '0', '删除');
INSERT INTO `sys_log` VALUES ('a8d23b39fa1c426886748d1456d2e70a', '1', 'admin', '修改系统参数设置表', '/manage/ApplicationParameter/updateApplicationParameter', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 11:48:48', '0', '修改');
INSERT INTO `sys_log` VALUES ('a8e843064e9d4b4595f1eb1d9d04e5b3', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:27:45', '0', '添加');
INSERT INTO `sys_log` VALUES ('aa69db547db54932a766d182326f6c59', '1', 'admin', '删除学生订单', 'manage/removeOrderStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '113.88.96.10', '443', null, '2', '2019-01-18 14:32:57', '0', '删除');
INSERT INTO `sys_log` VALUES ('aac8dd8ad6e3472895d7d35863fd959f', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:05:55', '0', '修改');
INSERT INTO `sys_log` VALUES ('ac5caada23ae460d99b838538d069332', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:09:48', '0', '修改');
INSERT INTO `sys_log` VALUES ('ace0083320764fd5be150e424166f6ec', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ4MDUwNzY1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NTU2NSwibmJmIjoxNTQ4MDUwNzY1fQ.m0ufOkTpqi13v8RBc0zwK8epyuZ8eTHvSk84YzVbNGY', '183.15.181.74', '443', null, '2', '2019-01-21 15:38:26', '0', '修改');
INSERT INTO `sys_log` VALUES ('aced2ea2f457442386c6d67d1058a349', '11', '黄世杰', '添加课程', '/teacher/teacherReleaseCourse/ReleaseCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:30:59', '0', '添加');
INSERT INTO `sys_log` VALUES ('ad54bcddf7ec4503b23c690d07d20a8a', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:09:16', '0', '修改');
INSERT INTO `sys_log` VALUES ('ae15615d60de4293bf05519813d3a5b2', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:58:14', '0', '修改');
INSERT INTO `sys_log` VALUES ('ae65863bb861437e9b6153feb36202ef', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 10:54:28', '0', '修改');
INSERT INTO `sys_log` VALUES ('b01495f855664c9a9a3d69fe0308ad53', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:06:51', '0', '修改');
INSERT INTO `sys_log` VALUES ('b28d26aa0af048be80b2f17b8a22a5de', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:02:08', '0', '修改');
INSERT INTO `sys_log` VALUES ('b309d1fdb9604ae68ab5effc7a5c9c46', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:00:20', '0', '修改');
INSERT INTO `sys_log` VALUES ('b35e07ebc6ae4b0795abaa91d4623551', '1', 'admin', '添加营销素材-课程', '/manage/MarketingCourse/addMarketingCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:30:52', '0', '添加');
INSERT INTO `sys_log` VALUES ('b3a81657512c49b99e41959ffbfa95dc', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:01:17', '0', '修改');
INSERT INTO `sys_log` VALUES ('b3c59af7f93041879afc17a3733886ed', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:45:57', '0', '添加');
INSERT INTO `sys_log` VALUES ('b437c772ce07416cb53f03779c9e2e10', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:07:16', '0', '修改');
INSERT INTO `sys_log` VALUES ('b4b79af8c37e493a96493ad1564b3e5e', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:23:56', '0', '修改');
INSERT INTO `sys_log` VALUES ('b53c5e0145a646ca94b8f8fbdb9cee55', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:28:15', '0', '添加');
INSERT INTO `sys_log` VALUES ('b5b3c94bb8dd41cdbfaf0d94856bbecd', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:52:43', '0', '修改');
INSERT INTO `sys_log` VALUES ('b64bbf6a3dea4d539511e159549de803', '1', 'admin', '添加问卷题目表', '/manage/Questionnaire/addQuestionnaire', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '113.88.96.10', '443', null, '2', '2019-01-18 14:26:00', '0', '添加');
INSERT INTO `sys_log` VALUES ('b66cff4649f9498fa21147d51e0b0ca9', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 10:25:06', '0', '修改');
INSERT INTO `sys_log` VALUES ('b7160cc633fa4e91b5e38e1e62ac98db', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInVzZXJOYW1lXCI6XCLpu4TkuJbmnbBcIixcInVzZXJJZFwiOjExfSIsImlhdCI6MTU0Nzc3NjM3NSwiYXVkIjoiYXVkaWVuY2UiLCJleHAiOjE1NDgzODExNzUsIm5iZiI6MTU0Nzc3NjM3NX0.U_d7flQ2N7edS0FhfmQvBCRAdflIQtP_iOBwa2GB8uE', '113.88.96.10', '443', null, '2', '2019-01-18 09:53:29', '0', '修改');
INSERT INTO `sys_log` VALUES ('b746fa7ca27e46c48dbf276a5055d2af', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:16:47', '0', '修改');
INSERT INTO `sys_log` VALUES ('b74c26294fab4966be41646da80328dc', '1', 'admin', '后台用户登录', 'admin/adminUserLogin', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 14:24:37', '0', '登录');
INSERT INTO `sys_log` VALUES ('b7663be7a20048bca9e9000f14af6e5b', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:04:38', '0', '修改');
INSERT INTO `sys_log` VALUES ('b79aacb60c05441bba4480ab28a4b8ad', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:27:07', '0', '添加');
INSERT INTO `sys_log` VALUES ('b7d514fe584c437ca2e7148fa841fba6', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:16:35', '0', '修改');
INSERT INTO `sys_log` VALUES ('b815568289714927b7db817a3d9292a6', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 09:24:23', '0', '修改');
INSERT INTO `sys_log` VALUES ('b8a1d37468664926ad71684960949417', '1', 'admin', '修改老师表', '/manage/Teacher/updateTeacher', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:57:38', '0', '修改');
INSERT INTO `sys_log` VALUES ('b8c646f71da94b30a56147179acc374d', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 14:03:46', '0', '修改');
INSERT INTO `sys_log` VALUES ('b9e830f18222462598a1b4357c25e156', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:56:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('ba19bfe003d6423f8028ccce0ce8dcc1', '1', 'admin', '删除营销素材-难题', '/manage/MarketingQuestion/removeMarketingQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:10:53', '0', '删除');
INSERT INTO `sys_log` VALUES ('bb05555aaaad49439bc95d04b068e642', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 17:04:09', '0', '修改');
INSERT INTO `sys_log` VALUES ('bbe018c628124b308c4a7265c0eb7c1d', '1', 'admin', '角色分配权限', 'admin/roleDistributionMenu', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:59:55', '0', '角色分配权限');
INSERT INTO `sys_log` VALUES ('bca23180269b47139e64d94b063c2035', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:29:09', '0', '添加');
INSERT INTO `sys_log` VALUES ('bccb4298668f4cbca44a877e5dad9240', '1', 'admin', '添加营销素材-难题', '/manage/MarketingQuestion/addMarketingQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:33:50', '0', '添加');
INSERT INTO `sys_log` VALUES ('bd4fb76ca9bb4842b4a70847b76be485', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:08:42', '0', '修改');
INSERT INTO `sys_log` VALUES ('bef9443080eb4cfb8af99d3931c37aaa', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:52:57', '0', '修改');
INSERT INTO `sys_log` VALUES ('c0e33f81cb744a22ac5b49c41247aa04', '1', 'admin', ' 审核教师', 'admin/examineUserInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzE4NjA4MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0Nzc5MDg4MSwibmJmIjoxNTQ3MTg2MDgxfQ.XjyQHu0gu5kHdeiD-8YYtL_u2GcCZxO4vHCLM99RFdk', '113.88.96.10', '443', null, '2', '2019-01-18 09:52:53', '0', '修改');
INSERT INTO `sys_log` VALUES ('c12eb8148f1949109c069c5574715fff', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 10:54:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('c15554c74c8b4991b15fff81bf23e857', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:19:57', '0', '修改');
INSERT INTO `sys_log` VALUES ('c2271841e2f846f786a5cdd0266ac507', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:43:36', '0', '修改');
INSERT INTO `sys_log` VALUES ('c254ecb32aca41828fad30c4fd11ee6d', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:45:49', '0', '删除');
INSERT INTO `sys_log` VALUES ('c2b1fa64557d42b685483e71dc5fd9f4', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:27:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('c34cf76765b8418c9d1f72b8053b196f', '1', 'admin', '后台用户启用禁用', 'admin/updateAdminState', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 12:07:30', '0', '启用/禁用');
INSERT INTO `sys_log` VALUES ('c35963cdd4204b639f0e08470f37056f', '12', '钟旭春', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:31:28', '0', '添加');
INSERT INTO `sys_log` VALUES ('c3bf446c649c4b34b40627a6e7247bcf', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:33:51', '0', '删除');
INSERT INTO `sys_log` VALUES ('c55f966143144d6a9b6803126e87d59e', '11', '黄世杰', '保存返回的频道号', '/teacher/teacherCourses/setUpchannelNo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 09:18:35', '0', '修改');
INSERT INTO `sys_log` VALUES ('c5cc25a792c94626925c70789c9c7f50', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:07:49', '0', '修改');
INSERT INTO `sys_log` VALUES ('c7d56c48c93a490daec46983c2a3ef40', '9', '钟旭春', '存在用户时登录,不存在用户时注册', '/teacher/teacherLogin/login', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5Iiwic3ViIjoie1wicGhvbmVcIjpcIjE4ODE5MDE1MjAxXCIsXCJ1c2VyTmFtZVwiOlwi6ZKf5pet5pilXCIsXCJ1c2VySWRcIjo5fSIsImlhdCI6MTU0NzY4ODU4NiwiaXNzIjoiUjE4ODE5MDE1MjAxIiwiYXVkIjoiYXVkaWVuY2UiLCJleHAiOjE1NDgyOTMzODYsIm5iZiI6MTU0NzY4ODU4Nn0.eXZYaMN8YYreREJZO2xwFh93uhsLIzNHhbwxrFa9-GY', '113.88.96.10', '443', null, '2', '2019-01-18 10:22:24', '0', '登录/注册');
INSERT INTO `sys_log` VALUES ('c95c0316403841c49db62cff2fb6e243', '1', 'admin', '添加课程', 'manage/addCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:59:29', '0', '添加');
INSERT INTO `sys_log` VALUES ('c9cffa7733244ad1acda3b9b69fd8d4b', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 13:42:07', '0', '修改');
INSERT INTO `sys_log` VALUES ('cc67f58a8239495b9b365be147119b09', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:07:42', '0', '修改');
INSERT INTO `sys_log` VALUES ('cc8c4e3dff524f5c8c1eb4fffe1ad6cc', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:12:01', '0', '修改');
INSERT INTO `sys_log` VALUES ('cd99608945b8499495aa16a68600333e', '11', '黄世杰', '修改视频路径', '/teacher/teacherCourses/keepVideoUrl', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 09:09:26', '0', '修改');
INSERT INTO `sys_log` VALUES ('cea6ec7de7ec48759a1b785d52e926be', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 14:47:46', '0', '修改');
INSERT INTO `sys_log` VALUES ('cf3b90af85c44434b90be7b243ce0b7a', '1', 'admin', '添加营销素材-课程', '/manage/MarketingCourse/addMarketingCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:30:36', '0', '添加');
INSERT INTO `sys_log` VALUES ('cf48811f900b4811a77d50c70f857097', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:52:15', '0', '添加');
INSERT INTO `sys_log` VALUES ('cf841480bcc742ef9d3e6f69153f062e', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:46:54', '0', '修改');
INSERT INTO `sys_log` VALUES ('d16e51f55ba940619688c1c714f16c2a', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '183.15.181.42', '443', null, '2', '2019-01-19 09:43:54', '0', '修改');
INSERT INTO `sys_log` VALUES ('d1745d40ee89435abf7bc93504a54bb2', '11', '黄世杰', '保存返回的频道号', '/teacher/teacherCourses/setUpchannelNo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 09:08:07', '0', '修改');
INSERT INTO `sys_log` VALUES ('d1792ec44e6641569312b595358e6650', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 14:36:33', '0', '修改');
INSERT INTO `sys_log` VALUES ('d2938a65ad514b4f8cd7583d01f7f017', '1', 'admin', '后台用户登录', 'admin/adminUserLogin', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 13:52:00', '0', '登录');
INSERT INTO `sys_log` VALUES ('d359275543fc42c8b7b837a20c96bb58', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:50:02', '0', '修改');
INSERT INTO `sys_log` VALUES ('d423c1b739834a6e9f9d0112eaf125c6', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:25:21', '0', '修改');
INSERT INTO `sys_log` VALUES ('d424f438a86f4ce59f6c4dddadba160d', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:27:27', '0', '修改');
INSERT INTO `sys_log` VALUES ('d4c211609754467eb463384224a6fc62', '1', 'admin', '后台用户启用禁用', 'admin/updateAdminState', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 12:00:32', '0', '启用/禁用');
INSERT INTO `sys_log` VALUES ('d4fc552d10ca46e9881a56aabc6208a9', '12', '钟旭春', '添加课程', '/teacher/teacherReleaseCourse/ReleaseCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 10:44:56', '0', '添加');
INSERT INTO `sys_log` VALUES ('d5141462f2764f4eb8cd0a29715d4c32', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:46:22', '0', '修改');
INSERT INTO `sys_log` VALUES ('d66a9e071699450b8653fdd471a4771f', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:56:48', '0', '修改');
INSERT INTO `sys_log` VALUES ('d8312b9939244c728f3c2775eaba1c7b', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:17:18', '0', '修改');
INSERT INTO `sys_log` VALUES ('d8d16f8f60374a48bb08de8e4bfef000', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 10:54:28', '0', '修改');
INSERT INTO `sys_log` VALUES ('da6efc9948ca47c784bd1142b63eb015', '1', 'admin', '添加优惠券', '/manage/Coupon/addCoupon', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 14:00:42', '0', '添加');
INSERT INTO `sys_log` VALUES ('db3e04e83bdf40079ce8a3d2efbaf9f6', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:27:38', '0', '修改');
INSERT INTO `sys_log` VALUES ('db6417c9ab5f429187ebeba7e8351792', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 14:34:16', '0', '修改');
INSERT INTO `sys_log` VALUES ('dc0fce514f534d3588356dd990d21dcc', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:23:45', '0', '修改');
INSERT INTO `sys_log` VALUES ('dc81db17f7a346f283579c35f4509f18', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:04:00', '0', '修改');
INSERT INTO `sys_log` VALUES ('dca6fe5d9deb456faf8bd2f464cf885a', '1', 'admin', '添加问卷题目表', '/manage/Questionnaire/addQuestionnaire', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '113.88.96.10', '443', null, '2', '2019-01-18 14:25:51', '0', '添加');
INSERT INTO `sys_log` VALUES ('dd105bba41624d7eab4cdd89973e04d3', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:56:44', '0', '修改');
INSERT INTO `sys_log` VALUES ('de52422c6c034c79a542d4a09f2111b3', '1', 'admin', '修改系统参数设置表', '/manage/ApplicationParameter/updateApplicationParameter', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '113.88.96.10', '443', null, '2', '2019-01-18 14:26:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('dfdba078f0bb47a5b0e29be51887166f', '11', '黄世杰', '修改视频路径', '/teacher/teacherCourses/keepVideoUrl', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:47:42', '0', '修改');
INSERT INTO `sys_log` VALUES ('e189e9a2bc7f4dbea23daf5f68b40012', '12', '钟旭春', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:37:20', '0', '修改');
INSERT INTO `sys_log` VALUES ('e18a54d223df43c5a9840bf5d061350d', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '183.15.181.42', '443', null, '2', '2019-01-19 09:34:45', '0', '修改');
INSERT INTO `sys_log` VALUES ('e22f4d7464724f9a90cc8bd9fc88b362', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:45:35', '0', '修改');
INSERT INTO `sys_log` VALUES ('e23c9fb623cd44f39321fabcaea87f94', '1', 'admin', '删除用户表', 'admin/removeSysUser', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:11:22', '0', '删除');
INSERT INTO `sys_log` VALUES ('e2401e8bdda74651be0c2aa1939675c8', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:37:01', '0', '修改');
INSERT INTO `sys_log` VALUES ('e28a6ea3723e422193c335334a03f634', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:35:59', '0', '修改');
INSERT INTO `sys_log` VALUES ('e2e9fa5e2f4d4124be5ef9c2544fdeb9', '1', 'admin', '添加优惠券', '/manage/Coupon/addCoupon', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MDcyMCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NTUyMCwibmJmIjoxNTQ3NzkwNzIwfQ.fcKfBTOvbbz_ojh0-341sRDTzqA_kNu1StpYPvpCGNU', '113.88.96.10', '443', null, '2', '2019-01-18 13:59:31', '0', '添加');
INSERT INTO `sys_log` VALUES ('e3893a7f37404a05a1cdccb07daa5dcb', '13', '接近让妈妈', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6IntcInBob25lXCI6XCIxNzY2NTMyNDUyNVwiLFwidXNlck5hbWVcIjpcIuaOpei_keiuqeWmiOWmiFwiLFwidXNlcklkXCI6MTN9IiwiaWF0IjoxNTQ3ODAyNzMyLCJpc3MiOiJEMTc2NjUzMjQ1MjUiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzUzMiwibmJmIjoxNTQ3ODAyNzMyfQ.qVDxlvPTX95xb3_YDIn6HsPG0jS7GPJzhdnvZ9rr1FM', '117.136.40.204', '443', null, '2', '2019-01-18 17:19:41', '0', '修改');
INSERT INTO `sys_log` VALUES ('e74dc22370974be39c581cfffdf7381d', '14', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 13:56:39', '0', '修改');
INSERT INTO `sys_log` VALUES ('e7a95eeba18d4fa8a2b6c6df1f6b3a27', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:33:43', '0', '修改');
INSERT INTO `sys_log` VALUES ('e836a46c67b6415abe42cedf25529fb1', '1', 'admin', '添加营销素材-学生端首页', '/manage/MarketingBanner/addMarketingBanner', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:54:34', '0', '添加');
INSERT INTO `sys_log` VALUES ('ea7a511868184213bc6b088a0378dd39', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:02:55', '0', '修改');
INSERT INTO `sys_log` VALUES ('eaaa80a8ddac45598a43d22b239a7ccd', '12', '钟旭春', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTJ9IiwiaWF0IjoxNTQ3Nzc4MjE0LCJpc3MiOiJCMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MzAxNCwibmJmIjoxNTQ3Nzc4MjE0fQ.1CG0XnD1ZCuQkKzaKHvOBTeRBpjBmq6tE59DF5iVxcc', '113.88.96.10', '443', null, '2', '2019-01-18 11:42:35', '0', '修改');
INSERT INTO `sys_log` VALUES ('eb8d204d1f9d45068b08f0a6aad6e0be', '1', 'admin', '删除疑难解答订单', '/manage/doubts/removeOrderQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzYyMzAzMywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODIyNzgzMywibmJmIjoxNTQ3NjIzMDMzfQ.wyJgXmGjQNYxarH5zPxpM30iy3wP1S7yk2wcut5w5dk', '183.15.181.42', '443', null, '2', '2019-01-19 09:48:02', '0', '删除');
INSERT INTO `sys_log` VALUES ('ec8c88d4f8f34272b551d3fd3f6f178f', '1', 'admin', '删除用户表', 'admin/removeSysUser', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:11:14', '0', '删除');
INSERT INTO `sys_log` VALUES ('ecb2e748ea284654b0525468c40c4d7a', '1', 'admin', ' 审核教师', 'admin/examineUserInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:11:37', '0', '修改');
INSERT INTO `sys_log` VALUES ('ecb4427c7f57450ebe37fd8838dd244a', '1', 'admin', '删除学生表', '/manage/Student/removeStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc5MjY3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzQ3NywibmJmIjoxNTQ3NzkyNjc3fQ.S2EHy58b6_nj7OyALwTZ6J9mSxe9Xubtda3rPnyE2HU', '183.15.181.75', '443', null, '2', '2019-01-21 10:44:47', '0', '删除');
INSERT INTO `sys_log` VALUES ('ed1c15e1394948f3859f73a9ac3f4ead', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:44:17', '0', '添加');
INSERT INTO `sys_log` VALUES ('ede915809466470caff773aaffce0ee7', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:21:42', '0', '修改');
INSERT INTO `sys_log` VALUES ('edeb0ad9420b4dfc801cb398b19e7413', '1', 'admin', '隐藏或显示老师课程', 'manage/updateCourseType', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0ODAzNDI3NywiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODYzOTA3NywibmJmIjoxNTQ4MDM0Mjc3fQ.KvQb4gju-YUxPTOgRDdcCmuIUIEnmWGiL9jMPfufhXo', '183.15.181.75', '443', null, '2', '2019-01-21 12:03:26', '0', '修改');
INSERT INTO `sys_log` VALUES ('ef6f2eb6a87745768bd1a9f030d7bdc4', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:05:11', '0', '修改');
INSERT INTO `sys_log` VALUES ('f0c0e71d794347939560ad4efd1e1aa8', '1', 'admin', '删除角色表', 'admin/removeSysRole', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:37:36', '0', '删除');
INSERT INTO `sys_log` VALUES ('f0ec72d3a64a43329577d55f425c4c75', '1', 'admin', '添加角色表', 'admin/addSysRole', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:59:46', '0', '添加');
INSERT INTO `sys_log` VALUES ('f1c88e83f62643799d89debf322f8401', '1', 'admin', '添加营销素材-难题', '/manage/MarketingQuestion/addMarketingQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:33:32', '0', '添加');
INSERT INTO `sys_log` VALUES ('f42c06a9df67472987e64d741475fad8', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 13:45:48', '0', '修改');
INSERT INTO `sys_log` VALUES ('f434eb03626b4cb0bb699bd357fb141c', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 11:01:19', '0', '修改');
INSERT INTO `sys_log` VALUES ('f58d7795a6b1426d974c6978f5fb2766', '14', '钟旭春', '修改个人信息', '/teacher/teacherInfo/updateTeacherInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNCIsInN1YiI6IntcInBob25lXCI6XCIxODgxOTAxNTIwMVwiLFwidXNlck5hbWVcIjpcIumSn-aXreaYpVwiLFwidXNlcklkXCI6MTR9IiwiaWF0IjoxNTQ4MDQ5NzI2LCJpc3MiOiJFMTg4MTkwMTUyMDEiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODY1NDUyNiwibmJmIjoxNTQ4MDQ5NzI2fQ.ig6XvaksD-sbdlUGEuWidl-zbe1BHWDrFECckCGo85w', '183.15.181.75', '443', null, '2', '2019-01-21 15:06:45', '0', '修改');
INSERT INTO `sys_log` VALUES ('f605e0e07b504292bfc2c647f55eedba', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:25:35', '0', '修改');
INSERT INTO `sys_log` VALUES ('f6453914dec043e78681cac646e73462', '11', '黄世杰', '修改订单状态', '/teacher/teacherProblemSolving/processingOrder', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3Nzc2NTc5LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4MTM3OSwibmJmIjoxNTQ3Nzc2NTc5fQ.pWPFgw5b9kovztZGVIMwiFdIYIPO-IeaLxheNw3pxlU', '113.88.96.10', '443', null, '2', '2019-01-18 10:27:54', '0', '修改');
INSERT INTO `sys_log` VALUES ('f76b9b5dcb7b4789a303e26d3a543e5d', '11', '黄世杰', '修改上下线', '/teacher/teacherProblemSolving/onLineQuestion', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzgwMDIwLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM4NDgyMCwibmJmIjoxNTQ3NzgwMDIwfQ.GkpjXfasRAtD3UuGgZ4GLhVyAFmGIs9IvUwIO1N8kjE', '113.88.96.10', '443', null, '2', '2019-01-18 14:08:44', '0', '修改');
INSERT INTO `sys_log` VALUES ('f88d212573944d4cb8809342895e29e2', '1', 'admin', '删除营销素材-课程', '/manage/MarketingCourse/removeMarketingCourse', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0Nzc3NDEzOCwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM3ODkzOCwibmJmIjoxNTQ3Nzc0MTM4fQ.UmZFUCmUt7A3z4IH7ulhy-f-tdOdn1JbxEgUmagHE_4', '113.88.96.10', '443', null, '2', '2019-01-18 10:10:48', '0', '删除');
INSERT INTO `sys_log` VALUES ('f901b6035eda48eaa3ed906d83c38a05', '1', 'admin', ' 审核教师', 'admin/examineUserInfo', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoie1wicGhvbmVcIjpcIjE4Njg0Njg0NTY4XCIsXCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwifSIsImlhdCI6MTU0NzgwMjQ1MSwiaXNzIjoiYWRtaW4iLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODQwNzI1MSwibmJmIjoxNTQ3ODAyNDUxfQ.yknJ0ZXthgaozdrlgNM_ZFJ1Ge6epWMZIorBtAYOIxM', '113.88.96.10', '443', null, '2', '2019-01-18 17:12:09', '0', '修改');
INSERT INTO `sys_log` VALUES ('f943378021a64e1db62164edecd61bee', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '113.88.96.10', '443', null, '2', '2019-01-18 16:46:59', '0', '添加');
INSERT INTO `sys_log` VALUES ('fd06b310639d4642939077321fa1051b', '11', '黄世杰', '添加消息', '/teacher/teacherCourses/getBuyCourseStudent', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkyODQ1LCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5NzY0NSwibmJmIjoxNTQ3NzkyODQ1fQ.BFJlcCR9A-G1sU_W4gzzukmruNd60zlmPKvxP3YwJ2U', '117.136.40.204', '443', null, '2', '2019-01-18 15:41:13', '0', '添加');
INSERT INTO `sys_log` VALUES ('ffe056c6d84f437ea1c15f18c2f29d6d', '11', '黄世杰', '修改密码', '/teacher/teacherEarnExpenses/changePassword', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsInN1YiI6IntcInBob25lXCI6XCIxMzkyNjUxMzI4MFwiLFwidXNlck5hbWVcIjpcIum7hOS4luadsFwiLFwidXNlcklkXCI6MTF9IiwiaWF0IjoxNTQ3NzkzODgxLCJpc3MiOiJXMTM5MjY1MTMyODAiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTU0ODM5ODY4MSwibmJmIjoxNTQ3NzkzODgxfQ.FUOGsu0TcWgizjwe9jvkklCOWA8u-TLhDqWMWu9abzY', '183.15.181.42', '443', null, '2', '2019-01-19 11:23:11', '0', '修改');

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
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '营销素材管理', '&#xe705;', '', null, '0', '1', '0', '0', '2018-12-20 17:05:20');
INSERT INTO `sys_menu` VALUES ('10', '疑难解疑订单', null, 'manage_solve_the_problem.html', null, '9', '2', '0', '8', '2018-12-20 17:11:38');
INSERT INTO `sys_menu` VALUES ('11', '学生订单', null, 'manage_student_order.html', null, '10', '2', '0', '8', '2018-12-20 17:12:03');
INSERT INTO `sys_menu` VALUES ('12', '课程管理', '&#xe63c;', null, null, '11', '1', '0', '0', '2018-12-20 17:12:32');
INSERT INTO `sys_menu` VALUES ('13', '课程页面', null, 'manage_item.html', null, '12', '2', '0', '12', '2018-12-20 17:13:15');
INSERT INTO `sys_menu` VALUES ('14', '权限管理', '&#xe6b2;', null, null, '13', '1', '0', '0', '2018-12-20 17:13:40');
INSERT INTO `sys_menu` VALUES ('15', '权限页面', null, 'manage_power.html', null, '14', '2', '0', '14', '2018-12-20 17:14:06');
INSERT INTO `sys_menu` VALUES ('16', '客户管理', '&#xe66f;', null, null, '15', '1', '0', '0', '2018-12-20 17:14:30');
INSERT INTO `sys_menu` VALUES ('17', '学生管理', null, 'manage_student.html', null, '16', '2', '0', '16', '2018-12-20 17:14:54');
INSERT INTO `sys_menu` VALUES ('18', '老师管理', null, 'manage_teacher.html', null, '17', '2', '0', '16', '2018-12-20 17:15:21');
INSERT INTO `sys_menu` VALUES ('19', '日志管理', '&#xe655;', null, null, '18', '1', '0', '0', '2018-12-20 17:15:55');
INSERT INTO `sys_menu` VALUES ('2', '学生首页轮播图', null, 'student_img.html', null, '1', '2', '0', '1', '2018-12-20 17:06:13');
INSERT INTO `sys_menu` VALUES ('20', '日志页面', null, 'log.html', null, '19', '2', '0', '19', '2018-12-20 17:16:15');
INSERT INTO `sys_menu` VALUES ('21', '优惠劵设置', '&#xe657;', null, null, '20', '1', '0', '0', '2018-12-20 17:16:53');
INSERT INTO `sys_menu` VALUES ('22', '优惠劵页面', '', 'set_coupon.html', null, '21', '2', '0', '21', '2018-12-20 17:17:46');
INSERT INTO `sys_menu` VALUES ('23', '财务管理', '&#xe620;', null, null, '22', '1', '0', '0', '2018-12-20 17:18:22');
INSERT INTO `sys_menu` VALUES ('24', '财务页面', '', 'manage_money.html', null, '23', '2', '0', '23', '2018-12-20 17:19:26');
INSERT INTO `sys_menu` VALUES ('25', '问卷管理', '&#xe620;', null, null, '24', '1', '0', '0', '2018-12-20 17:19:51');
INSERT INTO `sys_menu` VALUES ('26', '问卷页面', null, 'manage_quesition.html', null, '25', '2', '0', '25', '2018-12-20 17:20:20');
INSERT INTO `sys_menu` VALUES ('27', '设置管理', '&#xe620;', null, null, '26', '1', '0', '0', '2018-12-20 17:20:43');
INSERT INTO `sys_menu` VALUES ('28', '设置页面', null, 'manage_set.html', null, '27', '2', '0', '27', '2018-12-20 17:21:08');
INSERT INTO `sys_menu` VALUES ('29', '后台用户管理', '', 'manage_userbackg.html', null, '28', '2', '0', '14', '2018-12-20 18:33:13');
INSERT INTO `sys_menu` VALUES ('3', '学生端首页视频', null, 'student_video.html', null, '2', '2', '0', '1', '2018-12-20 17:06:37');
INSERT INTO `sys_menu` VALUES ('4', '约课', null, 'scheduled_class.html', null, '3', '2', '0', '1', '2018-12-20 17:07:03');
INSERT INTO `sys_menu` VALUES ('5', '难题解疑', null, 'difficult_broadcast.html', null, '4', '2', '0', '1', '2018-12-20 17:07:53');
INSERT INTO `sys_menu` VALUES ('6', '审核管理', '&#xe672;', null, null, '5', '1', '0', '0', '2018-12-20 17:09:38');
INSERT INTO `sys_menu` VALUES ('7', '审核页面', null, 'manage_examine.html', null, '6', '2', '0', '6', '2018-12-20 17:10:20');
INSERT INTO `sys_menu` VALUES ('8', '订单管理', '&#xe656;', null, null, '7', '1', '0', '0', '2018-12-20 17:10:50');
INSERT INTO `sys_menu` VALUES ('9', '课程订单', null, 'manage_item_order.html', null, '8', '2', '0', '8', '2018-12-20 17:11:16');

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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('b49c3e77d31e419fa28c3f0e5c6e69e4', '地方都是', null, '0', '2019-01-18 17:59:46');
INSERT INTO `sys_role` VALUES ('c648861ebaeb4e189ffc0d3d0a518d4e', '管理员', null, '0', '2018-09-30 17:16:23');
INSERT INTO `sys_role` VALUES ('ed138d6ef9944c85989eedebebb3c44d', '测试管理', null, '0', '2019-01-15 09:17:34');

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
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '真实姓名', '0', null, '18684684568', '0192023a7bbd73250516f069df18b500', null, '2019-01-21 11:31:36', '2019-01-21 11:27:14', '0', '0', '/image/8.jpg', null);
INSERT INTO `sys_user` VALUES ('b4ea1aa727994e8180b3b49ac0916b26', 'admin1', '111', '0', null, '13288888888', '20917c851c4a54f2a054390dac9085b7', null, null, null, '1', '0', '/image/8.jpg', null);

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
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('11', '2019-01-18 09:51:29', '2019-01-19 09:59:45', '黄世杰', '251', null, '0', '13926513280', '一年级', '语文', '小学', '12', '1245615455485', '/upload/file/ec188d0d15e84f4dba2041c6ae98f4f9.jpg', '/upload/file/a3a4c7f742224560929f8ce6c4bb5f50.jpg', '2', null, null, null, '5', '1', 'W13926513280', '929.52', '0', null, null, null, '0', '70', '0.00', null, '139265132801547860711956', 'a3f0bec59cebeb60553ec80bbfd5dfdf', '12.00', '0');
INSERT INTO `teacher` VALUES ('13', '2019-01-18 17:09:06', '2019-01-18 17:42:09', '接近让妈妈', '24', '', '0', '17665324525', '三年级', '品德与社会', '小学', '1', '5554613', '/upload/file/a4168b766686406f8f092c90b264364e.jpg', '/upload/file/70ebdf93be714812b53137700f297166.jpg', '2', 'sagvfasf', null, null, '7', '2', 'D17665324525', '120.10', '0', null, null, null, '0', '70', '0.00', null, '176653245251547804525816', '', '0.00', '0');
INSERT INTO `teacher` VALUES ('14', '2019-01-21 13:47:48', '2019-01-21 15:09:08', '钟旭', '25', null, '0', '18819015201', '一年级', '语文', '小学', '5', '441621199412131014', '/upload/file/6ddb0317edc5470594fcbad37313c7cf.jpg', '/upload/file/121bcd5773814e1da1ed0de0a1dcc813.jpg', '2', null, '广东省/深圳市/南山区', null, '5', '1', 'E18819015201', '0.00', '0', '五年', '高级教师', '好', '0', '70', '2.00', null, '298845', null, '10.00', '0');

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

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4463725fb919470e9ad3fdcc01fa2b17', 'b4ea1aa727994e8180b3b49ac0916b26', 'b49c3e77d31e419fa28c3f0e5c6e69e4');
INSERT INTO `user_role` VALUES ('a2a22a5b3a7c42c38eb1c72787ce45b6', '1', 'c648861ebaeb4e189ffc0d3d0a518d4e');
SET FOREIGN_KEY_CHECKS=1;
