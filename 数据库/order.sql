/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : ballclub

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-11-16 09:44:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `oid` varchar(50) NOT NULL DEFAULT '',
  `uid` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('90c1fef7-2dea-42f2-9bdd-b42d4495e31b', 'f9a4de9c-f6e7-46cb-a298-38992cdc482c', null, '0', '1799');
