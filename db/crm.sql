/*
Navicat MySQL Data Transfer

Source Server         : 新建连接
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2020-06-24 15:33:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_auth_group
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_group`;
CREATE TABLE `t_auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `auths` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_group
-- ----------------------------
INSERT INTO `t_auth_group` VALUES ('1', '总经理', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,29', '2020-04-11 18:45:32');
INSERT INTO `t_auth_group` VALUES ('2', '副总经理', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24', '2020-04-12 18:45:37');
INSERT INTO `t_auth_group` VALUES ('3', '销售', '1,2,3,4,5,6,7,8,10,11,12,13,14,15', '2020-04-13 13:45:41');
INSERT INTO `t_auth_group` VALUES ('4', '人事主管', '1,2,3,4,5,6,7,22,23,24,29', '2020-04-08 18:45:49');
INSERT INTO `t_auth_group` VALUES ('5', '财务', '1,2,3,4,5,6,7,20,21', '2020-04-18 18:45:55');
INSERT INTO `t_auth_group` VALUES ('6', '前台', '7,11,14,19,21,24,26,27,29,', '2020-04-17 18:46:01');

-- ----------------------------
-- Table structure for t_auth_group_access
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_group_access`;
CREATE TABLE `t_auth_group_access` (
  `sid` int(11) NOT NULL,
  `groupId` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  CONSTRAINT `FK97kei1j27tvuvsfddkyspbyk7` FOREIGN KEY (`sid`) REFERENCES `t_staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_group_access
-- ----------------------------
INSERT INTO `t_auth_group_access` VALUES ('1', '1');
INSERT INTO `t_auth_group_access` VALUES ('2', '2');
INSERT INTO `t_auth_group_access` VALUES ('3', '5');
INSERT INTO `t_auth_group_access` VALUES ('25', '3');
INSERT INTO `t_auth_group_access` VALUES ('31', '6');
INSERT INTO `t_auth_group_access` VALUES ('32', '6');
INSERT INTO `t_auth_group_access` VALUES ('34', '4');

-- ----------------------------
-- Table structure for t_client
-- ----------------------------
DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` char(30) DEFAULT NULL,
  `name` char(20) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `source` char(20) DEFAULT NULL,
  `enter` char(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_client
-- ----------------------------
INSERT INTO `t_client` VALUES ('1', '网易科技', '丁磊', '12312312321', '广告营销', 'admin', '2020-04-08 16:21:22');
INSERT INTO `t_client` VALUES ('2', '阿里巴巴', '马云', '98797898798', '电话营销', 'admin', '2020-04-08 16:22:33');
INSERT INTO `t_client` VALUES ('3', '腾讯科技', '马化腾', '45454534535', '主动联系', 'admin', '2020-04-08 16:23:34');
INSERT INTO `t_client` VALUES ('4', '盛大集团', '陈天桥', '28738371212', '主动联系', 'admin', '2020-04-10 13:44:52');
INSERT INTO `t_client` VALUES ('5', '测试客户', '小明', '17766662277', '电话营销', 'admin', '2020-04-19 13:33:44');
INSERT INTO `t_client` VALUES ('6', '测试客户2', '小红', '12376777711', '广告营销', 'admin', '2020-04-27 17:04:23');
INSERT INTO `t_client` VALUES ('7', '1111', '1111', '11111111111', '广告营销', 'admin', '2020-05-30 09:58:55');

-- ----------------------------
-- Table structure for t_documentary
-- ----------------------------
DROP TABLE IF EXISTS `t_documentary`;
CREATE TABLE `t_documentary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` char(14) DEFAULT NULL,
  `title` char(30) DEFAULT NULL,
  `company` char(30) DEFAULT NULL,
  `way` char(10) DEFAULT NULL,
  `evolve` char(10) DEFAULT NULL,
  `staff_name` char(20) DEFAULT NULL,
  `remark` char(20) DEFAULT NULL,
  `enter` char(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_documentary
-- ----------------------------
INSERT INTO `t_documentary` VALUES ('1', '202004082259', '关于腾讯科技办公用品采购方案', '腾讯科技', '电话沟通', '未成交', '王大锤', '努力拿下', 'admin', '2020-04-08 23:01:22');
INSERT INTO `t_documentary` VALUES ('2', '202004082326', '关于网易科技办公用品采购方案', '网易科技', '网络咨询', '已成交', '赵晓丽', '加油', 'admin', '2020-04-08 23:27:06');
INSERT INTO `t_documentary` VALUES ('6', '202004101345', '关于盛大集团办公用品的采购方案', '盛大集团', '上门拜访', '已成交', '池子', '暂无', 'admin', '2020-04-10 13:45:26');
INSERT INTO `t_documentary` VALUES ('7', '202004122123', '关于盛大集团游戏服务器的采购案', '盛大集团', '上门拜访', '已成交', '大司马', '暂无', 'admin', '2020-04-12 21:23:43');
INSERT INTO `t_documentary` VALUES ('9', '202004182201', '新建跟单', '腾讯科技', '上门拜访', '已成交', '测试3', '', 'admin', '2020-04-18 22:01:14');
INSERT INTO `t_documentary` VALUES ('10', '202004221535', '网易科技二次采购', '网易科技', '电话沟通', '已成交', '赵晓丽', '暂无', 'admin', '2020-04-22 15:35:57');
INSERT INTO `t_documentary` VALUES ('11', '202004221543', '111', '网易科技', '', '已成交', '王大锤', '', 'admin', '2020-04-22 15:43:26');
INSERT INTO `t_documentary` VALUES ('12', '202004221635', '222', '网易科技', '', '已成交', '李白', '', 'admin', '2020-04-22 16:35:12');
INSERT INTO `t_documentary` VALUES ('13', '202004271705', '关于测试客户2公司的采购方案', '测试客户2', '上门拜访', '已成交', '李白', '暂无', 'admin', '2020-04-27 17:05:11');
INSERT INTO `t_documentary` VALUES ('14', '202005300959', '网易科技跟单', '网易科技', '电话沟通', '已成交', '赵晓丽', '1111', 'admin', '2020-05-30 09:59:28');

-- ----------------------------
-- Table structure for t_extend
-- ----------------------------
DROP TABLE IF EXISTS `t_extend`;
CREATE TABLE `t_extend` (
  `staff_id` int(11) NOT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `heath` varchar(255) DEFAULT NULL,
  `residence` varchar(255) DEFAULT NULL,
  `graduation_time` datetime DEFAULT NULL,
  `registered_permanent_residence` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `details` longtext,
  PRIMARY KEY (`staff_id`),
  CONSTRAINT `FKolcjc9n1lyna8f7utgtahi12q` FOREIGN KEY (`staff_id`) REFERENCES `t_staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_extend
-- ----------------------------
INSERT INTO `t_extend` VALUES ('1', '我是一个菜鸡', '菜鸡', '良好', '农村', '2020-04-04 00:00:00', '芜湖', '野鸡大学', '你好？');
INSERT INTO `t_extend` VALUES ('2', '你好', '营销', '健康', '城镇', '2000-04-19 16:17:30', '北京', '北京三道口职业技术学院', '暂无');
INSERT INTO `t_extend` VALUES ('3', '你好，', '表演', '健康', '城镇', '2005-06-08 16:19:09', '北京', '北京电影学院', '暂无');
INSERT INTO `t_extend` VALUES ('25', '诗仙', '文学', '健康', '城镇', '1988-04-07 00:00:00', '不知', '长安学府', '<img alt=\"\" src=\"/CRM/attached/image/20200403/20200403223239_774.jpg\" /><br />');
INSERT INTO `t_extend` VALUES ('31', null, null, null, null, null, null, null, null);
INSERT INTO `t_extend` VALUES ('32', null, null, null, null, null, null, null, null);
INSERT INTO `t_extend` VALUES ('34', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_inform
-- ----------------------------
DROP TABLE IF EXISTS `t_inform`;
CREATE TABLE `t_inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `details` text,
  `staff_id` int(11) DEFAULT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_inform
-- ----------------------------
INSERT INTO `t_inform` VALUES ('1', '发布一则通知', '测试内容2222', '1', '王大锤', '2020-04-19 13:31:36');
INSERT INTO `t_inform` VALUES ('2', '发布再一则通知', '测试数据~~~', '1', '王大锤', '2020-04-27 17:02:29');
INSERT INTO `t_inform` VALUES ('3', '发布通知测试', '111111', '1', '王大锤', '2020-05-30 09:58:01');

-- ----------------------------
-- Table structure for t_inlib
-- ----------------------------
DROP TABLE IF EXISTS `t_inlib`;
CREATE TABLE `t_inlib` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  `staff_name` char(20) DEFAULT NULL,
  `mode` char(2) DEFAULT NULL,
  `mode_explain` char(20) DEFAULT NULL,
  `enter` char(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp2aby8ji3k3hqg4tvy4i2mvkr` (`product_id`),
  CONSTRAINT `FKp2aby8ji3k3hqg4tvy4i2mvkr` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_inlib
-- ----------------------------
INSERT INTO `t_inlib` VALUES ('4', '100', '大司马', '采购', '从超市采购', 'admin', '2020-04-07 20:22:30', '5');
INSERT INTO `t_inlib` VALUES ('5', '2', '池子', '采购', '从天猫上秒杀抢购', 'admin', '2020-04-07 22:02:11', '6');
INSERT INTO `t_inlib` VALUES ('6', '100', '阿松大', '退货', '因质量不好被退货', 'admin', '2020-04-07 22:36:23', '8');
INSERT INTO `t_inlib` VALUES ('7', '200', '七七', '退货', '退货了', 'admin', '2020-04-08 14:40:21', '8');
INSERT INTO `t_inlib` VALUES ('8', '50', '卡姆', '采购', '从工厂采购', 'admin', '2020-04-12 21:05:27', '7');
INSERT INTO `t_inlib` VALUES ('9', '10', '李白', '采购', '', 'admin', '2020-04-18 22:24:37', '9');
INSERT INTO `t_inlib` VALUES ('10', '50', '赵晓丽', '采购', '', 'admin', '2020-04-22 15:37:33', '6');
INSERT INTO `t_inlib` VALUES ('11', '5', '李白', '', '', 'admin', '2020-04-22 15:38:10', '9');
INSERT INTO `t_inlib` VALUES ('12', '5', '王大锤', '', '', 'admin', '2020-04-22 15:42:49', '8');
INSERT INTO `t_inlib` VALUES ('13', '10', '卡姆', '采购', '从京东采购', 'admin', '2020-04-27 17:08:05', '9');

-- ----------------------------
-- Table structure for t_letter
-- ----------------------------
DROP TABLE IF EXISTS `t_letter`;
CREATE TABLE `t_letter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(225) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `send_id` int(11) DEFAULT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `send_name` varchar(20) DEFAULT NULL,
  `isRead` char(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_letter
-- ----------------------------
INSERT INTO `t_letter` VALUES ('1', '请把新员工的档案整理后发过来', '2', '1', '赵晓丽', '王大锤', '未读', '2020-04-17 17:58:03');
INSERT INTO `t_letter` VALUES ('4', '测试发送私信！！！', '2', '1', '赵晓丽', '王大锤', '已读', '2020-04-17 21:14:38');
INSERT INTO `t_letter` VALUES ('5', '测试生成日志', '2', '1', '赵晓丽', '王大锤', '未读', '2020-04-18 21:37:28');
INSERT INTO `t_letter` VALUES ('6', '测试发送私信', '1', '2', '王大锤', '赵晓丽', '已读', '2020-04-27 17:03:18');
INSERT INTO `t_letter` VALUES ('7', '123123123123', '3', '1', '卡姆', '王大锤', '未读', '2020-05-30 09:58:17');
INSERT INTO `t_letter` VALUES ('8', '私信测试', '2', '1', '赵晓丽', '王大锤', '未读', '2020-05-30 10:10:14');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` char(20) DEFAULT NULL,
  `method` char(20) DEFAULT NULL,
  `module` char(20) DEFAULT NULL,
  `ip` char(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('7', 'admin(王大锤)', '新增工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-18 21:07:19');
INSERT INTO `t_log` VALUES ('8', 'admin(王大锤)', '更新工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-18 21:07:42');
INSERT INTO `t_log` VALUES ('9', 'admin(王大锤)', '完成工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-18 21:12:17');
INSERT INTO `t_log` VALUES ('10', 'admin(王大锤)', '作废工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-18 21:17:16');
INSERT INTO `t_log` VALUES ('11', 'admin(王大锤)', '新增通知', '办公管理>>通知管理', '127.0.0.1', '2020-04-18 21:26:30');
INSERT INTO `t_log` VALUES ('12', 'admin(王大锤)', '修改通知', '办公管理>>通知管理', '127.0.0.1', '2020-04-18 21:32:05');
INSERT INTO `t_log` VALUES ('13', 'admin(王大锤)', '删除通知', '办公管理>>通知管理', '127.0.0.1', '2020-04-18 21:32:26');
INSERT INTO `t_log` VALUES ('14', 'admin(王大锤)', '新增私信', '办公管理>>私信收发', '127.0.0.1', '2020-04-18 21:37:28');
INSERT INTO `t_log` VALUES ('15', 'admin(王大锤)', '删除私信', '办公管理>>私信收发', '127.0.0.1', '2020-04-18 21:37:35');
INSERT INTO `t_log` VALUES ('16', 'admin(王大锤)', '新增客户信息', '客户管理>>客户信息', '127.0.0.1', '2020-04-18 21:44:13');
INSERT INTO `t_log` VALUES ('17', 'admin(王大锤)', '修改客户信息', '客户管理>>客户信息', '127.0.0.1', '2020-04-18 21:44:42');
INSERT INTO `t_log` VALUES ('18', 'admin(王大锤)', '删除客户信息', '客户管理>>客户信息', '127.0.0.1', '2020-04-18 21:44:53');
INSERT INTO `t_log` VALUES ('19', 'admin(王大锤)', '新增跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-18 21:53:10');
INSERT INTO `t_log` VALUES ('20', 'admin(王大锤)', '修改跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-18 21:54:28');
INSERT INTO `t_log` VALUES ('21', 'admin(王大锤)', '删除跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-18 21:54:47');
INSERT INTO `t_log` VALUES ('22', 'admin(王大锤)', '新增跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-18 22:01:14');
INSERT INTO `t_log` VALUES ('23', 'admin(王大锤)', '新增销售订单', '客户管理>>销售订单', '127.0.0.1', '2020-04-18 22:03:18');
INSERT INTO `t_log` VALUES ('24', 'admin(王大锤)', '修改销售订单', '客户管理>>销售订单', '127.0.0.1', '2020-04-18 22:03:53');
INSERT INTO `t_log` VALUES ('25', 'admin(王大锤)', '新增产品信息', '仓库管理>>产品信息', '127.0.0.1', '2020-04-18 22:10:50');
INSERT INTO `t_log` VALUES ('26', 'admin(王大锤)', '修改产品信息', '仓库管理>>产品信息', '127.0.0.1', '2020-04-18 22:16:03');
INSERT INTO `t_log` VALUES ('27', 'admin(王大锤)', '删除产品信息', '仓库管理>>产品信息', '127.0.0.1', '2020-04-18 22:16:22');
INSERT INTO `t_log` VALUES ('28', 'admin(王大锤)', '新增入库记录', '仓库管理>>入库记录', '127.0.0.1', '2020-04-18 22:24:37');
INSERT INTO `t_log` VALUES ('29', 'admin(王大锤)', '新增出库记录', '仓库管理>>出库记录', '127.0.0.1', '2020-04-18 22:25:15');
INSERT INTO `t_log` VALUES ('30', 'admin(王大锤)', '新增收款记录', '财务管理>>收款记录', '127.0.0.1', '2020-04-18 22:30:35');
INSERT INTO `t_log` VALUES ('38', 'admin(王大锤)', '删除账号', '人事管理>>登录账号', '127.0.0.1', '2020-04-18 23:07:37');
INSERT INTO `t_log` VALUES ('39', 'admin(王大锤)', '新增员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-18 23:08:39');
INSERT INTO `t_log` VALUES ('40', 'admin(王大锤)', '修改员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-18 23:08:57');
INSERT INTO `t_log` VALUES ('41', 'admin(王大锤)', '删除员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-18 23:09:18');
INSERT INTO `t_log` VALUES ('42', 'admin(王大锤)', '新增职位部门', '人事管理>>职位部门', '127.0.0.1', '2020-04-18 23:34:49');
INSERT INTO `t_log` VALUES ('43', 'admin(王大锤)', '修改职位部门', '人事管理>>职位部门', '127.0.0.1', '2020-04-18 23:35:00');
INSERT INTO `t_log` VALUES ('44', 'admin(王大锤)', '删除职位部门', '人事管理>>职位部门', '127.0.0.1', '2020-04-18 23:35:10');
INSERT INTO `t_log` VALUES ('45', 'admin(王大锤)', '新增工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-19 13:25:06');
INSERT INTO `t_log` VALUES ('46', 'admin(王大锤)', '更新工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-19 13:25:18');
INSERT INTO `t_log` VALUES ('47', 'admin(王大锤)', '完成工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-19 13:25:23');
INSERT INTO `t_log` VALUES ('48', 'admin(王大锤)', '作废工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-19 13:25:37');
INSERT INTO `t_log` VALUES ('49', 'admin(王大锤)', '新增工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-19 13:29:28');
INSERT INTO `t_log` VALUES ('50', 'admin(王大锤)', '更新工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-19 13:29:39');
INSERT INTO `t_log` VALUES ('51', 'admin(王大锤)', '完成工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-19 13:29:46');
INSERT INTO `t_log` VALUES ('52', 'admin(王大锤)', '作废工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-19 13:29:57');
INSERT INTO `t_log` VALUES ('53', 'admin(王大锤)', '新增通知', '办公管理>>通知管理', '127.0.0.1', '2020-04-19 13:31:36');
INSERT INTO `t_log` VALUES ('54', 'admin(王大锤)', '修改通知', '办公管理>>通知管理', '127.0.0.1', '2020-04-19 13:31:43');
INSERT INTO `t_log` VALUES ('55', 'Zhao(赵晓丽)', '新增私信', '办公管理>>私信收发', '127.0.0.1', '2020-04-19 13:32:50');
INSERT INTO `t_log` VALUES ('56', 'admin(王大锤)', '删除私信', '办公管理>>私信收发', '127.0.0.1', '2020-04-19 13:33:12');
INSERT INTO `t_log` VALUES ('57', 'admin(王大锤)', '新增客户信息', '客户管理>>客户信息', '127.0.0.1', '2020-04-19 13:33:44');
INSERT INTO `t_log` VALUES ('58', 'admin(王大锤)', '修改账号状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 14:09:49');
INSERT INTO `t_log` VALUES ('59', 'admin(王大锤)', '修改账号状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 14:10:04');
INSERT INTO `t_log` VALUES ('60', 'admin(王大锤)', '修改账号状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 14:13:01');
INSERT INTO `t_log` VALUES ('61', 'admin(王大锤)', '修改账号状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 14:18:29');
INSERT INTO `t_log` VALUES ('62', 'admin(王大锤)', '修改账号状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 14:20:02');
INSERT INTO `t_log` VALUES ('63', 'admin(王大锤)', '修改账号状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 14:20:05');
INSERT INTO `t_log` VALUES ('64', 'Zhao(赵晓丽)', '修改账号密码与状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 16:05:35');
INSERT INTO `t_log` VALUES ('65', 'admin(王大锤)', '修改账号密码', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 16:49:18');
INSERT INTO `t_log` VALUES ('66', 'admin(王大锤)', '修改账号密码', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 16:49:39');
INSERT INTO `t_log` VALUES ('67', 'admin(王大锤)', '新增员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-19 17:20:34');
INSERT INTO `t_log` VALUES ('68', 'admin(王大锤)', '修改员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-19 17:23:35');
INSERT INTO `t_log` VALUES ('69', 'admin(王大锤)', '修改员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-19 17:29:35');
INSERT INTO `t_log` VALUES ('70', 'admin(王大锤)', '修改员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-19 17:40:51');
INSERT INTO `t_log` VALUES ('71', 'admin(王大锤)', '修改员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-19 17:40:56');
INSERT INTO `t_log` VALUES ('72', 'admin(王大锤)', '修改员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-19 17:41:47');
INSERT INTO `t_log` VALUES ('73', 'admin(王大锤)', '删除员工档案', '人事管理>>员工档案', '127.0.0.1', '2020-04-19 17:43:48');
INSERT INTO `t_log` VALUES ('74', 'admin(王大锤)', '新增账号', '人事管理>>登录账号', '127.0.0.1', '2020-04-19 21:31:54');
INSERT INTO `t_log` VALUES ('75', 'admin(王大锤)', '新增跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-22 15:35:57');
INSERT INTO `t_log` VALUES ('76', 'admin(王大锤)', '新增入库记录', '仓库管理>>入库记录', '127.0.0.1', '2020-04-22 15:37:33');
INSERT INTO `t_log` VALUES ('77', 'admin(王大锤)', '新增入库记录', '仓库管理>>入库记录', '127.0.0.1', '2020-04-22 15:38:10');
INSERT INTO `t_log` VALUES ('78', 'admin(王大锤)', '新增入库记录', '仓库管理>>入库记录', '127.0.0.1', '2020-04-22 15:42:49');
INSERT INTO `t_log` VALUES ('79', 'admin(王大锤)', '新增跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-22 15:43:26');
INSERT INTO `t_log` VALUES ('80', 'admin(王大锤)', '新增销售订单', '客户管理>>销售订单', '127.0.0.1', '2020-04-22 15:52:08');
INSERT INTO `t_log` VALUES ('81', 'admin(王大锤)', '修改跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-22 16:24:39');
INSERT INTO `t_log` VALUES ('82', 'admin(王大锤)', '新增销售订单', '客户管理>>销售订单', '127.0.0.1', '2020-04-22 16:34:14');
INSERT INTO `t_log` VALUES ('83', 'admin(王大锤)', '新增跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-22 16:35:12');
INSERT INTO `t_log` VALUES ('84', 'admin(王大锤)', '新增销售订单', '客户管理>>销售订单', '127.0.0.1', '2020-04-22 16:35:37');
INSERT INTO `t_log` VALUES ('85', 'admin(王大锤)', '新增工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-27 16:54:22');
INSERT INTO `t_log` VALUES ('86', 'admin(王大锤)', '更新工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-27 16:54:39');
INSERT INTO `t_log` VALUES ('87', 'admin(王大锤)', '完成工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-27 16:54:51');
INSERT INTO `t_log` VALUES ('88', 'admin(王大锤)', '新增工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-27 16:59:34');
INSERT INTO `t_log` VALUES ('89', 'admin(王大锤)', '更新工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-27 16:59:47');
INSERT INTO `t_log` VALUES ('90', 'admin(王大锤)', '完成工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-27 16:59:52');
INSERT INTO `t_log` VALUES ('91', 'admin(王大锤)', '作废工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-04-27 17:00:56');
INSERT INTO `t_log` VALUES ('92', 'admin(王大锤)', '新增通知', '办公管理>>通知管理', '127.0.0.1', '2020-04-27 17:02:29');
INSERT INTO `t_log` VALUES ('93', 'Zhao(赵晓丽)', '新增私信', '办公管理>>私信收发', '127.0.0.1', '2020-04-27 17:03:18');
INSERT INTO `t_log` VALUES ('94', 'admin(王大锤)', '新增客户信息', '客户管理>>客户信息', '127.0.0.1', '2020-04-27 17:04:23');
INSERT INTO `t_log` VALUES ('95', 'admin(王大锤)', '新增跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-04-27 17:05:11');
INSERT INTO `t_log` VALUES ('96', 'admin(王大锤)', '新增销售订单', '客户管理>>销售订单', '127.0.0.1', '2020-04-27 17:06:02');
INSERT INTO `t_log` VALUES ('97', 'admin(王大锤)', '新增收款记录', '财务管理>>收款记录', '127.0.0.1', '2020-04-27 17:06:49');
INSERT INTO `t_log` VALUES ('98', 'admin(王大锤)', '新增出库记录', '仓库管理>>出库记录', '127.0.0.1', '2020-04-27 17:07:24');
INSERT INTO `t_log` VALUES ('99', 'admin(王大锤)', '新增入库记录', '仓库管理>>入库记录', '127.0.0.1', '2020-04-27 17:08:05');
INSERT INTO `t_log` VALUES ('100', 'admin(王大锤)', '新增账号', '人事管理>>登录账号', '127.0.0.1', '2020-04-27 17:08:54');
INSERT INTO `t_log` VALUES ('101', 'admin(王大锤)', '修改账号密码与状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-27 17:09:43');
INSERT INTO `t_log` VALUES ('102', 'admin(王大锤)', '修改账号密码与状态', '人事管理>>登录账号', '127.0.0.1', '2020-04-27 17:09:47');
INSERT INTO `t_log` VALUES ('103', 'admin(王大锤)', '新增工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-05-30 09:55:25');
INSERT INTO `t_log` VALUES ('104', 'admin(王大锤)', '更新工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-05-30 09:55:54');
INSERT INTO `t_log` VALUES ('105', 'admin(王大锤)', '完成工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-05-30 09:55:55');
INSERT INTO `t_log` VALUES ('106', 'admin(王大锤)', '作废工作计划', '办公管理>>工作计划', '127.0.0.1', '2020-05-30 09:56:35');
INSERT INTO `t_log` VALUES ('107', 'admin(王大锤)', '新增通知', '办公管理>>通知管理', '127.0.0.1', '2020-05-30 09:58:01');
INSERT INTO `t_log` VALUES ('108', 'admin(王大锤)', '新增私信', '办公管理>>私信收发', '127.0.0.1', '2020-05-30 09:58:17');
INSERT INTO `t_log` VALUES ('109', 'admin(王大锤)', '新增客户信息', '客户管理>>客户信息', '127.0.0.1', '2020-05-30 09:58:55');
INSERT INTO `t_log` VALUES ('110', 'admin(王大锤)', '新增跟单记录', '客户管理>>跟单记录', '127.0.0.1', '2020-05-30 09:59:28');
INSERT INTO `t_log` VALUES ('111', 'admin(王大锤)', '新增销售订单', '客户管理>>销售订单', '127.0.0.1', '2020-05-30 10:00:18');
INSERT INTO `t_log` VALUES ('112', 'admin(王大锤)', '新增产品信息', '仓库管理>>产品信息', '127.0.0.1', '2020-05-30 10:00:46');
INSERT INTO `t_log` VALUES ('113', 'admin(王大锤)', '新增收款记录', '财务管理>>收款记录', '127.0.0.1', '2020-05-30 10:02:03');
INSERT INTO `t_log` VALUES ('114', 'admin(王大锤)', '新增出库记录', '仓库管理>>出库记录', '127.0.0.1', '2020-05-30 10:02:18');
INSERT INTO `t_log` VALUES ('115', 'admin(王大锤)', '新增私信', '办公管理>>私信收发', '127.0.0.1', '2020-05-30 10:10:14');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '办公管理', 'icon-system', '0', null);
INSERT INTO `t_menu` VALUES ('2', '客户管理', 'icon-system', '0', null);
INSERT INTO `t_menu` VALUES ('3', '仓库管理', 'icon-system', '0', null);
INSERT INTO `t_menu` VALUES ('4', '财务管理', 'icon-system', '0', null);
INSERT INTO `t_menu` VALUES ('5', '人事管理', 'icon-system', '0', null);
INSERT INTO `t_menu` VALUES ('6', '系统管理', 'icon-system', '0', null);
INSERT INTO `t_menu` VALUES ('7', '数据统计', 'icon-system', '0', null);
INSERT INTO `t_menu` VALUES ('8', '工作计划', 'icon-book', '1', 'work.jsp');
INSERT INTO `t_menu` VALUES ('9', '分配任务', 'icon-book', '1', 'allo.jsp');
INSERT INTO `t_menu` VALUES ('10', '通知管理', 'icon-book', '1', 'inform.jsp');
INSERT INTO `t_menu` VALUES ('11', '私信收发', 'icon-book', '1', 'letter.jsp');
INSERT INTO `t_menu` VALUES ('12', '客户信息', 'icon-book', '2', 'client.jsp');
INSERT INTO `t_menu` VALUES ('13', '跟单记录', 'icon-book', '2', 'documentary.jsp');
INSERT INTO `t_menu` VALUES ('14', '销售订单', 'icon-book', '2', 'order.jsp');
INSERT INTO `t_menu` VALUES ('15', '产品信息', 'icon-book', '3', 'product.jsp');
INSERT INTO `t_menu` VALUES ('16', '入库记录', 'icon-book', '3', 'inlib.jsp');
INSERT INTO `t_menu` VALUES ('17', '出库记录', 'icon-book', '3', 'outlib.jsp');
INSERT INTO `t_menu` VALUES ('18', '库存警报', 'icon-book', '3', 'alarm.jsp');
INSERT INTO `t_menu` VALUES ('19', '采购记录', 'icon-book', '3', 'procure.jsp');
INSERT INTO `t_menu` VALUES ('20', '收款记录', 'icon-book', '4', 'receipt.jsp');
INSERT INTO `t_menu` VALUES ('21', '支出记录', 'icon-book', '4', 'payment.jsp');
INSERT INTO `t_menu` VALUES ('22', '登录账号', 'icon-book', '5', 'user.jsp');
INSERT INTO `t_menu` VALUES ('23', '员工档案', 'icon-book', '5', 'staff.jsp');
INSERT INTO `t_menu` VALUES ('24', '职位部门', 'icon-book', '5', 'post.jsp');
INSERT INTO `t_menu` VALUES ('25', '权限管理', 'icon-book', '6', 'auth.jsp');
INSERT INTO `t_menu` VALUES ('26', '操作日志', 'icon-book', '6', 'log.jsp');
INSERT INTO `t_menu` VALUES ('27', '产品销量', 'icon-book', '7', 'product_number.jsp');
INSERT INTO `t_menu` VALUES ('29', '客户分析', 'icon-book', '7', 'client_analytics.jsp');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` char(14) DEFAULT NULL,
  `title` char(30) DEFAULT NULL,
  `original` decimal(10,2) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `pay_state` char(10) DEFAULT NULL,
  `enter` char(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `documentary_id` int(11) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_91h5yafmrqagme8v2c6i55flx` (`documentary_id`),
  CONSTRAINT `FKct70fim3qw0ptuhsupm1exiu7` FOREIGN KEY (`documentary_id`) REFERENCES `t_documentary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('2', '202004121859', '关于盛大集团办公用品的采购订单', '2080.01', '2050.01', '已支付', 'admin', '2020-04-12 18:59:43', '6', '修改后的备注。。。');
INSERT INTO `t_order` VALUES ('3', '202004122124', '关于盛大集团游戏服务器的采购订单', '100.00', '90.01', '未支付', 'admin', '2020-04-12 21:24:54', '7', '服务器已经卖给别人了，剩下一点胶带卖给盛大集团');
INSERT INTO `t_order` VALUES ('4', '202004132343', '关于网易科技办公用品采购订单', '3750.00', '3600.00', '已支付', 'admin', '2020-04-13 23:43:22', '2', '暂无');
INSERT INTO `t_order` VALUES ('5', '202004182203', '新建的销售订单', '6300.00', '6300.10', '已支付', 'admin', '2020-04-18 22:03:18', '9', '修改后的备注');
INSERT INTO `t_order` VALUES ('6', '202004221552', '网易科技二次采购的订单', '12400.30', '12000.01', '未支付', 'admin', '2020-04-22 15:52:08', '10', '');
INSERT INTO `t_order` VALUES ('7', '202004221634', '111订单', '1110.00', '1111.00', '未支付', 'admin', '2020-04-22 16:34:14', '11', '');
INSERT INTO `t_order` VALUES ('8', '202004221635', '222订单', '3950.10', '4000.00', '已支付', 'admin', '2020-04-22 16:35:37', '12', '');
INSERT INTO `t_order` VALUES ('9', '202004271706', '关于测试客户2公司的采购方案订单', '350.00', '349.11', '已支付', 'admin', '2020-04-27 17:06:02', '13', '打个折');
INSERT INTO `t_order` VALUES ('10', '202005301000', '网易科技跟单订单', '180.01', '170.00', '未支付', 'admin', '2020-05-30 10:00:18', '14', '');

-- ----------------------------
-- Table structure for t_outlib
-- ----------------------------
DROP TABLE IF EXISTS `t_outlib`;
CREATE TABLE `t_outlib` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `product_sn` char(5) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `sell_price` decimal(10,2) DEFAULT NULL,
  `order_sn` char(14) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `state` char(5) DEFAULT NULL,
  `clerk` char(20) DEFAULT NULL,
  `enter` char(20) DEFAULT NULL,
  `dispose_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_outlib
-- ----------------------------
INSERT INTO `t_outlib` VALUES ('1', '5', '10003', '胶带', '10.00', '202004121859', '50', '已出库', 'admin', 'admin', '2020-04-18 22:25:15', '2020-04-12 18:59:43');
INSERT INTO `t_outlib` VALUES ('2', '6', '10004', '金士顿16GU盘', '80.01', '202004121859', '1', '已出库', 'admin', 'admin', '2020-04-27 17:07:24', '2020-04-12 18:59:43');
INSERT INTO `t_outlib` VALUES ('3', '8', '10002', '三聚中性笔', '10.00', '202004121859', '150', '已出库', 'admin', 'admin', '2020-04-13 23:20:33', '2020-04-12 18:59:43');
INSERT INTO `t_outlib` VALUES ('4', '5', '10003', '胶带', '10.00', '202004122124', '10', '未处理', null, 'admin', null, '2020-04-12 21:24:54');
INSERT INTO `t_outlib` VALUES ('5', '5', '10003', '胶带', '10.00', '202004132343', '10', '已出库', 'admin', 'admin', '2020-04-13 23:47:40', '2020-04-13 23:43:22');
INSERT INTO `t_outlib` VALUES ('6', '7', '10001', '三星500g移动硬盘', '315.00', '202004132343', '10', '已出库', 'admin', 'admin', '2020-04-13 23:47:40', '2020-04-13 23:43:22');
INSERT INTO `t_outlib` VALUES ('7', '8', '10002', '三聚中性笔', '10.00', '202004132343', '50', '已出库', 'admin', 'admin', '2020-04-13 23:47:40', '2020-04-13 23:43:22');
INSERT INTO `t_outlib` VALUES ('8', '7', '10001', '三星500g移动硬盘', '315.00', '202004182203', '20', '已支付', null, 'admin', null, '2020-04-18 22:03:18');
INSERT INTO `t_outlib` VALUES ('9', '6', '10004', '金士顿16GU盘', '80.01', '202004221552', '30', '未处理', null, 'admin', null, '2020-04-22 15:52:08');
INSERT INTO `t_outlib` VALUES ('10', '9', '10005', '金士顿64gU盘', '1000.00', '202004221552', '10', '未处理', null, 'admin', null, '2020-04-22 15:52:08');
INSERT INTO `t_outlib` VALUES ('11', '5', '10003', '胶带', '10.00', '202004221634', '11', '未处理', null, 'admin', null, '2020-04-22 16:34:14');
INSERT INTO `t_outlib` VALUES ('12', '9', '10005', '金士顿64gU盘', '1000.00', '202004221634', '1', '未处理', null, 'admin', null, '2020-04-22 16:34:14');
INSERT INTO `t_outlib` VALUES ('13', '6', '10004', '金士顿16GU盘', '80.01', '202004221635', '10', '已支付', null, 'admin', null, '2020-04-22 16:35:37');
INSERT INTO `t_outlib` VALUES ('14', '7', '10001', '三星500g移动硬盘', '315.00', '202004221635', '10', '已支付', null, 'admin', null, '2020-04-22 16:35:37');
INSERT INTO `t_outlib` VALUES ('15', '5', '10003', '胶带', '10.00', '202004271706', '5', '已出库', 'admin', 'admin', '2020-05-30 10:02:18', '2020-04-27 17:06:02');
INSERT INTO `t_outlib` VALUES ('16', '8', '10002', '三聚中性笔', '10.00', '202004271706', '30', '已出库', 'admin', 'admin', '2020-05-30 10:02:18', '2020-04-27 17:06:02');
INSERT INTO `t_outlib` VALUES ('17', '5', '10003', '胶带', '10.00', '202005301000', '10', '未处理', null, 'admin', null, '2020-05-30 10:00:18');
INSERT INTO `t_outlib` VALUES ('18', '6', '10004', '金士顿16GU盘', '80.01', '202005301000', '1', '未处理', null, 'admin', null, '2020-05-30 10:00:18');

-- ----------------------------
-- Table structure for t_post
-- ----------------------------
DROP TABLE IF EXISTS `t_post`;
CREATE TABLE `t_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_post
-- ----------------------------
INSERT INTO `t_post` VALUES ('1', '总经理', '2020-03-24 23:43:15');
INSERT INTO `t_post` VALUES ('2', '销售', '2020-03-25 23:44:45');
INSERT INTO `t_post` VALUES ('3', '财务', '2020-03-26 23:45:09');
INSERT INTO `t_post` VALUES ('4', '副总经理', '2020-03-28 16:28:53');
INSERT INTO `t_post` VALUES ('5', '前台', '2020-03-28 16:36:22');
INSERT INTO `t_post` VALUES ('7', '人事主管', '2020-03-28 16:39:18');

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` char(5) DEFAULT NULL,
  `name` char(20) DEFAULT NULL,
  `type` char(10) DEFAULT NULL,
  `pro_price` decimal(10,2) DEFAULT NULL,
  `sell_price` decimal(10,2) DEFAULT NULL,
  `unit` char(5) DEFAULT NULL,
  `inventory` int(11) DEFAULT '0',
  `inventory_in` int(11) DEFAULT '0',
  `inventory_out` int(11) DEFAULT '0',
  `inventory_alarm` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('5', '10003', '胶带', '办公耗材', '5.13', '10.00', '卷', '4', '100', '96', '10', '2020-04-06 17:43:37');
INSERT INTO `t_product` VALUES ('6', '10004', '金士顿16GU盘', '数码产品', '60.01', '80.01', '个', '10', '52', '42', '10', '2020-04-06 18:08:26');
INSERT INTO `t_product` VALUES ('7', '10001', '三星500g移动硬盘', '数码产品', '289.00', '315.00', '个', '10', '50', '40', '10', '2020-04-06 20:55:12');
INSERT INTO `t_product` VALUES ('8', '10002', '三聚中性笔', '办公耗材', '5.00', '10.00', '根', '75', '305', '230', '10', '2020-04-06 20:55:46');
INSERT INTO `t_product` VALUES ('9', '10005', '金士顿64gU盘', '数码产品', '300.00', '1000.00', '个', '14', '25', '11', '10', '2020-04-07 20:11:41');
INSERT INTO `t_product` VALUES ('10', '11111', '产品测试', '办公耗材', null, null, '', '0', null, null, null, '2020-05-30 10:00:46');

-- ----------------------------
-- Table structure for t_product_extend
-- ----------------------------
DROP TABLE IF EXISTS `t_product_extend`;
CREATE TABLE `t_product_extend` (
  `product_id` int(11) NOT NULL,
  `details` longtext,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `FKpu5ryw8da6blmmur50jt40qb` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_extend
-- ----------------------------
INSERT INTO `t_product_extend` VALUES ('5', '拿来粘东西d d d&nbsp;');
INSERT INTO `t_product_extend` VALUES ('6', '暂无');
INSERT INTO `t_product_extend` VALUES ('7', '暂无');
INSERT INTO `t_product_extend` VALUES ('8', '暂无');
INSERT INTO `t_product_extend` VALUES ('9', '暂无');
INSERT INTO `t_product_extend` VALUES ('10', null);

-- ----------------------------
-- Table structure for t_receipt
-- ----------------------------
DROP TABLE IF EXISTS `t_receipt`;
CREATE TABLE `t_receipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` char(30) DEFAULT NULL,
  `order_sn` char(12) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `enter` char(20) DEFAULT NULL,
  `remark` char(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_receipt
-- ----------------------------
INSERT INTO `t_receipt` VALUES ('1', '关于盛大集团办公用品的采购订单收款', '202004121859', '2050.01', 'admin', '收款齐全！', '2020-04-13 21:18:55');
INSERT INTO `t_receipt` VALUES ('2', '关于网易科技办公用品采购收款', '202004132343', '3600.00', 'admin', '货款收齐', '2020-04-13 23:47:06');
INSERT INTO `t_receipt` VALUES ('3', '测试生成日志的收款', '202004182203', '6300.00', 'admin', '', '2020-04-18 22:30:35');
INSERT INTO `t_receipt` VALUES ('4', '关于测试客户2公司的采购方案收款', '202004271706', '349.11', 'admin', '全款已收', '2020-04-27 17:06:49');
INSERT INTO `t_receipt` VALUES ('5', '1111', '202004221635', '400.00', 'admin', '已受到', '2020-05-30 10:02:03');

-- ----------------------------
-- Table structure for t_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `number` varchar(11) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `post` char(20) DEFAULT NULL,
  `type` char(4) DEFAULT NULL,
  `id_card` char(18) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `nation` char(5) DEFAULT NULL,
  `marital_status` char(2) DEFAULT NULL,
  `entry_status` char(2) DEFAULT NULL,
  `entry_date` datetime DEFAULT NULL,
  `dimission_date` datetime DEFAULT NULL,
  `politics_status` char(2) DEFAULT NULL,
  `education` char(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_inq4nwxr7j9od3vrxp9tjpafo` (`user_id`),
  CONSTRAINT `FKlbxihux3m3cnci1cj4mja6t2o` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_staff
-- ----------------------------
INSERT INTO `t_staff` VALUES ('1', '王大锤', '1001', '男', '总经理', '临时工', '445266177666661111', '99999999999', '满族', '丧偶', '暂休', '2020-04-04 00:00:00', '2020-04-04 00:00:00', '群众', '专科', '2020-04-01 15:59:29', '1');
INSERT INTO `t_staff` VALUES ('2', '赵晓丽', '1002', '女', '副总经理', '临时工', '445266177666660000', '32132132131', '汉族', '未婚', '离职', '2020-03-31 17:07:44', '2020-04-01 20:55:05', '团员', '带专', '2020-04-01 16:00:44', '45');
INSERT INTO `t_staff` VALUES ('3', '卡姆', '1003', '男', '财务', '合同工', '445266177666661001', '13231231231', '维族', '未婚', '离职', '2020-02-05 21:12:43', '2020-04-01 21:13:26', '党员', '本科', '2020-04-01 21:13:03', '41');
INSERT INTO `t_staff` VALUES ('25', '李白', '1009', '男', '销售', '正式员工', '446322188749389222', '18877728873', '汉族', '离异', '在职', '2020-04-01 00:00:00', '2020-04-03 00:00:00', '群众', '本科', '2020-04-03 22:32:42', '44');
INSERT INTO `t_staff` VALUES ('31', '测试生成日志', '1010', '男', '前台', '', '445266177666660012', '', '', '', '', null, null, '', '', '2020-04-18 19:58:53', '46');
INSERT INTO `t_staff` VALUES ('32', '生成日志测试', '1011', '男', '前台', '', '446322188749389233', '', '', '', '', null, null, '', '', '2020-04-18 20:01:58', null);
INSERT INTO `t_staff` VALUES ('34', '测试', '1111', '女', '人事主管', '', '227388199738278811', '', '', '', '', null, null, '', '', '2020-04-18 23:08:39', '47');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accounts` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(255) DEFAULT '',
  `login_count` int(11) unsigned NOT NULL DEFAULT '0',
  `state` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `staff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e8ih1covpb12k07945u0witmi` (`staff_id`),
  CONSTRAINT `FK3hjl4rha38k04p4t6ol0apr1s` FOREIGN KEY (`staff_id`) REFERENCES `t_staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2020-05-30 09:54:59', '127.0.0.1', '145', '正常', '2020-03-30 16:11:10', '1');
INSERT INTO `t_user` VALUES ('41', '黑客', '5445f79d79ad73c2cd0099cb5e4e9117', null, null, '0', '正常', '2020-04-05 12:30:12', '3');
INSERT INTO `t_user` VALUES ('44', '白客', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-19 16:45:06', '127.0.0.1', '2', '正常', '2020-04-17 11:39:38', '25');
INSERT INTO `t_user` VALUES ('45', 'Zhao', 'e10adc3949ba59abbe56e057f20f883e', '2020-05-02 16:10:24', '127.0.0.1', '11', '正常', '2020-04-17 14:24:02', '2');
INSERT INTO `t_user` VALUES ('46', 'QianTai', '96e79218965eb72c92a549dd5a330112', '2020-04-27 17:10:06', '127.0.0.1', '2', '正常', '2020-04-19 21:31:54', '31');
INSERT INTO `t_user` VALUES ('47', 'CeShi', 'e10adc3949ba59abbe56e057f20f883e', null, null, '0', '正常', '2020-04-27 17:08:54', '34');

-- ----------------------------
-- Table structure for t_work
-- ----------------------------
DROP TABLE IF EXISTS `t_work`;
CREATE TABLE `t_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` char(20) DEFAULT NULL,
  `type` char(5) DEFAULT NULL,
  `stage` char(20) DEFAULT NULL,
  `state` char(10) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `staff_name` char(20) DEFAULT NULL,
  `enter` char(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `enter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_work
-- ----------------------------
INSERT INTO `t_work` VALUES ('5', '外出拓展业务', '业务', '工作计划完成', '已完成', '1', '王大锤', '王大锤', '2020-04-16 17:41:33', '2020-04-16', '2020-04-18', '1');
INSERT INTO `t_work` VALUES ('6', '外出扩展业务', '业务', '工作计划完成', '已作废', '1', '王大锤', '王大锤', '2020-04-16 18:48:05', '2020-04-21', '2020-04-23', '1');
INSERT INTO `t_work` VALUES ('7', '再次外出扩展业务', '业务', '创建工作任务', '进行中', '1', '王大锤', '王大锤', '2020-04-16 23:50:10', '2020-04-16', '2020-04-18', '1');
INSERT INTO `t_work` VALUES ('8', '外出扩展业务（测试）', '业务', '创建工作任务', '进行中', '2', '赵晓丽', '王大锤', '2020-04-17 13:45:35', '2020-04-17', '2020-04-20', '1');
INSERT INTO `t_work` VALUES ('9', '测试生成日志', '业务', '创建工作任务', '已作废', '1', '王大锤', '王大锤', '2020-04-18 21:00:28', '2020-04-18', '2020-04-20', '1');
INSERT INTO `t_work` VALUES ('10', '测试生成日志2', '内勤', '工作计划完成', '已作废', '1', '王大锤', '王大锤', '2020-04-18 21:07:19', '2020-04-18', '2020-04-20', '1');
INSERT INTO `t_work` VALUES ('11', '测试3', '业务', '工作计划完成', '已作废', '1', '王大锤', '王大锤', '2020-04-19 13:25:06', '2020-04-19', '2020-04-21', '1');
INSERT INTO `t_work` VALUES ('12', '测试4', '业务', '创建工作任务', '进行中', '25', '李白', '王大锤', '2020-04-19 13:26:47', '2020-04-19', '2020-04-21', '1');
INSERT INTO `t_work` VALUES ('13', '测试4', '内勤', '工作计划完成', '已作废', '1', '王大锤', '王大锤', '2020-04-19 13:29:28', '2020-04-19', '2020-04-21', '1');
INSERT INTO `t_work` VALUES ('14', '外出扩展业务2', '业务', '工作计划完成', '已作废', '1', '王大锤', '王大锤', '2020-04-27 16:54:22', '2020-04-28', '2020-04-30', '1');
INSERT INTO `t_work` VALUES ('15', '外出扩展业务3', '业务', '工作计划完成', '已完成', '1', '王大锤', '王大锤', '2020-04-27 16:59:34', '2020-04-28', '2020-04-30', '1');
INSERT INTO `t_work` VALUES ('16', '测试5', '业务', '创建工作任务', '进行中', '2', '赵晓丽', '王大锤', '2020-04-27 17:01:36', '2020-04-28', '2020-04-29', '1');
INSERT INTO `t_work` VALUES ('17', '外出扩展业务', '业务', '工作计划完成', '已完成', '1', '王大锤', '王大锤', '2020-05-30 09:55:25', '2020-05-21', '2020-05-26', '1');
INSERT INTO `t_work` VALUES ('18', '工作计划分配', '业务', '创建工作任务', '进行中', '2', '赵晓丽', '王大锤', '2020-05-30 09:57:21', '2020-05-12', '2020-05-31', '1');

-- ----------------------------
-- Table structure for t_workstage
-- ----------------------------
DROP TABLE IF EXISTS `t_workstage`;
CREATE TABLE `t_workstage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` char(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `work_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8vbpusih23jmvexydrms12vtm` (`work_id`),
  CONSTRAINT `FK8vbpusih23jmvexydrms12vtm` FOREIGN KEY (`work_id`) REFERENCES `t_work` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_workstage
-- ----------------------------
INSERT INTO `t_workstage` VALUES ('6', '创建工作任务', '2020-04-16 17:41:33', '5');
INSERT INTO `t_workstage` VALUES ('7', '跟单进行中', '2020-04-16 17:41:40', '5');
INSERT INTO `t_workstage` VALUES ('8', '达成订单', '2020-04-16 18:08:24', '5');
INSERT INTO `t_workstage` VALUES ('9', '订单已付款', '2020-04-16 18:14:48', '5');
INSERT INTO `t_workstage` VALUES ('10', '申请采购资金', '2020-04-16 18:16:03', '5');
INSERT INTO `t_workstage` VALUES ('11', '工作计划完成', '2020-04-16 18:46:02', '5');
INSERT INTO `t_workstage` VALUES ('12', '创建工作任务', '2020-04-16 18:48:05', '6');
INSERT INTO `t_workstage` VALUES ('13', '跟单进行中', '2020-04-16 18:48:18', '6');
INSERT INTO `t_workstage` VALUES ('14', '达成订单', '2020-04-16 18:49:20', '6');
INSERT INTO `t_workstage` VALUES ('15', '订单已付款', '2020-04-16 18:49:26', '6');
INSERT INTO `t_workstage` VALUES ('16', '工作计划完成', '2020-04-16 18:50:40', '6');
INSERT INTO `t_workstage` VALUES ('17', '创建工作任务', '2020-04-16 23:50:10', '7');
INSERT INTO `t_workstage` VALUES ('18', '创建工作任务', '2020-04-17 13:45:35', '8');
INSERT INTO `t_workstage` VALUES ('19', '创建工作任务', '2020-04-18 21:00:28', '9');
INSERT INTO `t_workstage` VALUES ('20', '创建工作任务', '2020-04-18 21:07:19', '10');
INSERT INTO `t_workstage` VALUES ('21', '跟单进行中', '2020-04-18 21:07:42', '10');
INSERT INTO `t_workstage` VALUES ('22', '工作计划完成', '2020-04-18 21:12:17', '10');
INSERT INTO `t_workstage` VALUES ('23', '创建工作任务', '2020-04-19 13:25:06', '11');
INSERT INTO `t_workstage` VALUES ('24', '跟单进行中', '2020-04-19 13:25:18', '11');
INSERT INTO `t_workstage` VALUES ('25', '工作计划完成', '2020-04-19 13:25:23', '11');
INSERT INTO `t_workstage` VALUES ('26', '创建工作任务', '2020-04-19 13:26:47', '12');
INSERT INTO `t_workstage` VALUES ('27', '创建工作任务', '2020-04-19 13:29:28', '13');
INSERT INTO `t_workstage` VALUES ('28', '跟单进行中', '2020-04-19 13:29:39', '13');
INSERT INTO `t_workstage` VALUES ('29', '工作计划完成', '2020-04-19 13:29:46', '13');
INSERT INTO `t_workstage` VALUES ('30', '创建工作任务', '2020-04-27 16:54:22', '14');
INSERT INTO `t_workstage` VALUES ('31', '跟单进行中', '2020-04-27 16:54:39', '14');
INSERT INTO `t_workstage` VALUES ('32', '工作计划完成', '2020-04-27 16:54:51', '14');
INSERT INTO `t_workstage` VALUES ('33', '创建工作任务', '2020-04-27 16:59:34', '15');
INSERT INTO `t_workstage` VALUES ('34', '跟单进行中', '2020-04-27 16:59:47', '15');
INSERT INTO `t_workstage` VALUES ('35', '工作计划完成', '2020-04-27 16:59:52', '15');
INSERT INTO `t_workstage` VALUES ('36', '创建工作任务', '2020-04-27 17:01:36', '16');
INSERT INTO `t_workstage` VALUES ('37', '创建工作任务', '2020-05-30 09:55:25', '17');
INSERT INTO `t_workstage` VALUES ('38', '达成订单', '2020-05-30 09:55:54', '17');
INSERT INTO `t_workstage` VALUES ('39', '工作计划完成', '2020-05-30 09:55:55', '17');
INSERT INTO `t_workstage` VALUES ('40', '创建工作任务', '2020-05-30 09:57:21', '18');
