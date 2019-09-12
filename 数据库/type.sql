/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : ballclub

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-11-16 09:45:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` varchar(50) NOT NULL DEFAULT '0',
  `type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '男子装');
INSERT INTO `type` VALUES ('2', '杨教练');
INSERT INTO `type` VALUES ('3', 'hot商品');
INSERT INTO `type` VALUES ('4', '管理平台');
INSERT INTO `type` VALUES ('5', '篮球训练');
INSERT INTO `type` VALUES ('6', '商品列表');
