/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : tax

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-01-10 14:06:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for complain
-- ----------------------------
DROP TABLE IF EXISTS `complain`;
CREATE TABLE `complain` (
  `comp_id` varchar(32) NOT NULL,
  `comp_company` varchar(100) DEFAULT NULL,
  `comp_name` varchar(20) DEFAULT NULL,
  `comp_mobile` varchar(20) DEFAULT NULL,
  `is_NM` tinyint(1) DEFAULT NULL,
  `comp_time` datetime DEFAULT NULL,
  `comp_title` varchar(200) NOT NULL,
  `to_comp_name` varchar(20) DEFAULT NULL,
  `to_comp_dept` varchar(100) DEFAULT NULL,
  `comp_content` text,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complain
-- ----------------------------
INSERT INTO `complain` VALUES ('1', '部门B', '用户', '13888888888', '1', '2016-10-05 13:54:52', '投诉标题1', '管理员', '部门A', '投诉内容', '1');
INSERT INTO `complain` VALUES ('2', '部门A', '用户', '13888888888', '0', '2016-02-23 13:57:40', '投诉标题2', '管理员', '部门A', '<p>投诉</p>', '0');
INSERT INTO `complain` VALUES ('40289181579f5b9101579f65fa580000', '部门A', '管理员', '12345678911', '0', '2016-10-07 21:47:22', '投诉标题3333', '用户', '部门B', '<p><img src=\"http://localhost:8080/Tax/upload/ueditor/image/20161007/1475848041479020292.png\" title=\"1475848041479020292.png\" alt=\"50.png\"/></p>', '1');
INSERT INTO `complain` VALUES ('40289181579f5b9101579f6863030001', '部门A', '管理员', '12345678911', '0', '2016-03-24 21:50:00', '投诉标题444', '管理员', '部门A', '<p>toushushushss<br/></p>', '0');
INSERT INTO `complain` VALUES ('40289181579f6c7b01579f6cc5db0000', '部门A', '管理员', '12345678911', '0', '2016-04-04 21:54:45', '投诉标题5555', '用户', '部门B', '<p>ddddd<br/></p>', '0');
INSERT INTO `complain` VALUES ('40289181579f6c7b01579f70e1a60001', '部门A', '管理员', '12345678911', '0', '2016-01-20 21:59:17', '投诉标题6666', '用户', '部门B', '<p>666666666666666<br/></p>', '0');
INSERT INTO `complain` VALUES ('40289181579f6c7b01579f71a1460002', '部门A', '管理员', '12345678911', '0', '2016-10-07 22:00:06', '投诉标题777', '管理员', '部门A', '<p>7777777777777777777<br/></p>', '0');
INSERT INTO `complain` VALUES ('40289181579f6c7b01579f73e5c60003', '部门A', '管理员', '12345678911', '1', '2016-09-13 22:02:35', '投诉标题565656', '管理员', '部门A', '<p>565656565</p><p>222 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;555<br/></p>', '2');
INSERT INTO `complain` VALUES ('4028918157ae56e50157ae5c12000000', '部门A', '部门A', '12345678911', '0', '2016-10-10 19:30:52', '投诉标题', '用户', '部门B', '<p>sssss<br/></p>', '0');
INSERT INTO `complain` VALUES ('4028918157ae56e50157ae5eea9f0001', '部门A', '部门A', '12345678911', '1', '2016-10-10 19:33:58', '投诉标题', '用户', '部门B', '<p>sssss<br/></p>', '1');
INSERT INTO `complain` VALUES ('4028918157ae56e50157ae60d0f40002', '部门A', '部门A', '12345678911', '1', '2016-10-10 19:36:03', '投诉标题投诉标题', '用户', '部门B', '<p>12123121231231321231<br/></p>', '0');
INSERT INTO `complain` VALUES ('4028918157ae7e1e0157ae8102120000', '部门A', '管理员', '12345678911', '1', '2016-10-10 20:11:12', '投诉标题12312121', '用户', '部门B', '<p>dddd<br/></p>', '0');
INSERT INTO `complain` VALUES ('4028918157ae7e1e0157ae817bb60001', '部门A', '管理员', '12345678911', '1', '2016-10-10 20:11:43', '投诉标题753963', '管理员', '部门A', '<p>ssssssssssss<br/></p>', '0');
INSERT INTO `complain` VALUES ('4028918157ae7e1e0157ae8241160002', '部门A', '管理员', '12345678911', '1', '2016-10-10 20:12:34', '投诉标题ssss', '用户', '部门B', '<p>ssssssssssssssssssssssssssssss21213</p><p><br/></p><p>s1</p><p>1&nbsp;&nbsp; 12dfd</p><p>s <br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p>', '0');
INSERT INTO `complain` VALUES ('4028918157aed70e0157aedfee8c0000', '部门B', '用户', '12345678910', '0', '2016-10-10 21:54:53', '投诉标题', '管理员', '部门A', '<p>不给管理员权限<br/></p>', '1');

-- ----------------------------
-- Table structure for complain_reply
-- ----------------------------
DROP TABLE IF EXISTS `complain_reply`;
CREATE TABLE `complain_reply` (
  `reply_id` varchar(32) NOT NULL,
  `comp_id` varchar(32) NOT NULL,
  `replyer` varchar(20) DEFAULT NULL,
  `reply_dept` varchar(100) DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  `reply_content` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `FK_comp_reply` (`comp_id`),
  CONSTRAINT `FK_comp_reply` FOREIGN KEY (`comp_id`) REFERENCES `complain` (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complain_reply
-- ----------------------------
INSERT INTO `complain_reply` VALUES ('4028918157988932015798bef99d0002', '1', '管理员', '部门A', '2016-10-06 14:47:15', '123');
INSERT INTO `complain_reply` VALUES ('4028918157988932015798bf0ea10003', '1', '管理员', '部门A', '2016-10-06 14:47:20', '456');
INSERT INTO `complain_reply` VALUES ('4028918157988932015798bfb2260004', '1', '管理员', '部门A', '2016-10-06 14:48:02', '789');
INSERT INTO `complain_reply` VALUES ('4028918157988932015798c5b0ac0005', '1', '管理员', '部门A', '2016-10-06 14:54:35', '159');
INSERT INTO `complain_reply` VALUES ('40289181579f86de01579f87bd150000', '1', '管理员', '部门A', '2016-10-07 22:24:15', '753');
INSERT INTO `complain_reply` VALUES ('40289181579f86de01579f89ebf20001', '1', '管理员', '部门A', '2016-10-07 22:26:38', '7897797979');
INSERT INTO `complain_reply` VALUES ('40289181579f86de01579f8a9ba00002', '40289181579f5b9101579f65fa580000', '管理员', '部门A', '2016-10-07 22:27:23', 'ok');
INSERT INTO `complain_reply` VALUES ('4028918157ae56e50157ae6396d80003', '4028918157ae56e50157ae5eea9f0001', '管理员', '部门A', '2016-10-10 19:39:04', '收到');
INSERT INTO `complain_reply` VALUES ('4028918157aed70e0157aee1d17c0001', '4028918157aed70e0157aedfee8c0000', '管理员', '部门A', '2016-10-10 21:56:57', '你给管理员权限，，，，，，你这下满意了。。。。\r\n                   记得给好评！！！！！！！！！！！！！！！！！');

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `info_id` varchar(32) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `content` longtext,
  `memo` varchar(200) DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('402891815770fdf5015771414d060001', 'tzgg', '百度', '标题', '<p>信息发布管理：\r\n &nbsp; &nbsp;根据信息标题、信息类型进行信息查询；可以在页面中点击“新增”发布信息，点击“删除”进行批量删除信息。列表数据包括信息标题、信息分类、申请人、申请时间、状态、操作；其中操作栏的内容为停用/发布、编辑、删除。当信息的状态为停用时，在操作栏显示发布、编辑、删除，当信息的状态为启用时，操作栏显示停用、编辑、删除。</p>', '填写内容包括信息分类（通知公告、政策速递、纳税指导）、来源、信息标题、信息内容（需要编辑多种格式内容）、备注、申请人、申请时间。  ', '管理员', '2016-09-28 22:44:04', '1');
INSERT INTO `info` VALUES ('40289181577e9c7801577ea0dd040000', 'tzgg', '搜狐', '搜狐标题', '<p>this is 搜狐视频<br/></p>', '搜狐视频', '管理员', '2016-10-01 13:03:22', '1');
INSERT INTO `info` VALUES ('402891815780764b0157808d1b860000', 'tzgg', '', '​标题2', '<h1>标题2<br/></h1>', '', '管理员', '2016-10-01 22:01:13', '0');
INSERT INTO `info` VALUES ('4028918157aed70e0157aee35f510002', 'tzgg', '本地', '给管理员权限', '<p>需要管理员权限，请回复给我。。。。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限三天内。<br/></p>', '给管理员权限', '管理员', '2016-10-10 21:57:18', '1');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('40289181576568490157656c3a330002', '管理员', '1');
INSERT INTO `role` VALUES ('40289181576568490157656c7d8a0003', '一般用户', '1');

-- ----------------------------
-- Table structure for roleprivilege
-- ----------------------------
DROP TABLE IF EXISTS `roleprivilege`;
CREATE TABLE `roleprivilege` (
  `role_id` varchar(32) NOT NULL,
  `code` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`,`code`),
  KEY `FKD741BADB38214CB8` (`role_id`),
  CONSTRAINT `FKD741BADB38214CB8` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleprivilege
-- ----------------------------
INSERT INTO `roleprivilege` VALUES ('40289181576568490157656c3a330002', 'hqfw');
INSERT INTO `roleprivilege` VALUES ('40289181576568490157656c3a330002', 'nsfw');
INSERT INTO `roleprivilege` VALUES ('40289181576568490157656c3a330002', 'spaces');
INSERT INTO `roleprivilege` VALUES ('40289181576568490157656c3a330002', 'xzgl');
INSERT INTO `roleprivilege` VALUES ('40289181576568490157656c3a330002', 'zxxx');
INSERT INTO `roleprivilege` VALUES ('40289181576568490157656c7d8a0003', 'spaces');
INSERT INTO `roleprivilege` VALUES ('40289181576568490157656c7d8a0003', 'zxxx');

-- ----------------------------
-- Table structure for tmonth
-- ----------------------------
DROP TABLE IF EXISTS `tmonth`;
CREATE TABLE `tmonth` (
  `imonth` tinyint(4) NOT NULL,
  PRIMARY KEY (`imonth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tmonth
-- ----------------------------
INSERT INTO `tmonth` VALUES ('1');
INSERT INTO `tmonth` VALUES ('2');
INSERT INTO `tmonth` VALUES ('3');
INSERT INTO `tmonth` VALUES ('4');
INSERT INTO `tmonth` VALUES ('5');
INSERT INTO `tmonth` VALUES ('6');
INSERT INTO `tmonth` VALUES ('7');
INSERT INTO `tmonth` VALUES ('8');
INSERT INTO `tmonth` VALUES ('9');
INSERT INTO `tmonth` VALUES ('10');
INSERT INTO `tmonth` VALUES ('11');
INSERT INTO `tmonth` VALUES ('12');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `dept` varchar(20) NOT NULL,
  `userName` varchar(10) NOT NULL,
  `account` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `headImg` varchar(100) DEFAULT NULL,
  `sex` bit(1) DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('40289181576568490157656a84ee0000', '部门A', '管理员', 'admin', '123', 'user/9a15309e78074cf7acda2f5f71c5d1ed.gif', '\0', '1', '12345678911', 'admin@root.com', '2016-09-26 00:00:00', '');
INSERT INTO `user` VALUES ('40289181576568490157656b4adc0001', '部门B', '用户', 'user', '456', null, '', '1', '12345678910', 'user@root.com', '2016-09-27 00:00:00', '');
INSERT INTO `user` VALUES ('4028918157b12be80157b1318dc60000', '部门A', '123', '2123', '123', null, '', '1', '', '', null, '');
INSERT INTO `user` VALUES ('4028918157b12be80157b132896a0001', '部门A', '123', '456', '23', null, '', '1', '', '', null, '');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `role_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FKF01D02E138214CB8` (`role_id`),
  CONSTRAINT `FKF01D02E138214CB8` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES ('40289181576568490157656c3a330002', '40289181576568490157656a84ee0000');
INSERT INTO `userrole` VALUES ('40289181576568490157656c3a330002', '40289181576568490157656b4adc0001');
INSERT INTO `userrole` VALUES ('40289181576568490157656c7d8a0003', '40289181576568490157656a84ee0000');
INSERT INTO `userrole` VALUES ('40289181576568490157656c7d8a0003', '40289181576568490157656b4adc0001');
