/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : ballclub

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-11-16 09:45:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(20) DEFAULT NULL,
  `password` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telephone` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `active` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('5b5474c9-8344-443e-8c54-89b9f2c6f5d9', '我我', '1234', '1', '1836639284@qq.com', '2131', '1231231', '1', 'bd43802d-7ff3-4e33-b706-7e73284da7b5');
INSERT INTO `user` VALUES ('f9a4de9c-f6e7-46cb-a298-38992cdc482c', '1234', '1234', '1', '1836639284@qq.com', '四川省成都市都江堰市青城山镇成都东软学院', '110', '0', '787361e8-736f-48c5-bde7-72c11d8ab4ed');
