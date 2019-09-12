/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : ballclub

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-11-16 09:44:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` varchar(50) NOT NULL DEFAULT '',
  `pid` varchar(50) DEFAULT NULL,
  `oid` varchar(50) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('aee3cc79-bb0c-4deb-9d5a-f08bac85dd62', '14', '90c1fef7-2dea-42f2-9bdd-b42d4495e31b', '1', '1799');
