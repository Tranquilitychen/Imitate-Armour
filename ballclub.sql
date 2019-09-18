/*
Navicat MySQL Data Transfer

Source Server         : kalic
Source Server Version : 80012
Source Host           : 127.0.0.1:3306
Source Database       : ballclub

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-09-18 08:49:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
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

-- ----------------------------
-- Table structure for orderitem
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

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` varchar(50) NOT NULL DEFAULT '',
  `pname` varchar(50) DEFAULT NULL,
  `pprice` int(11) DEFAULT NULL,
  `pdesc` varchar(200) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `pimg` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '小米 4c 标准版', '1299', '小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待', '1', 'products/1/c_0001.jpg');
INSERT INTO `product` VALUES ('10', '华为 Ascend Mate7', '2599', '华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！', '1', 'products/1/c_0010.jpg');
INSERT INTO `product` VALUES ('11', 'vivo X5Pro', '2298', '移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术', '1', 'products/1/c_0014.jpg');
INSERT INTO `product` VALUES ('12', '努比亚（nubia）My 布拉格', '1799', '努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！', '1', 'products/1/c_0013.jpg');
INSERT INTO `product` VALUES ('13', '华为 麦芒4', '2499', '华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖', '1', 'products/1/c_0012.jpg');
INSERT INTO `product` VALUES ('14', 'vivo X5M', '1799', 'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV', '2', 'products/1/c_0011.jpg');
INSERT INTO `product` VALUES ('15', 'Apple iPhone 6 (A1586)', '4288', 'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！', '2', 'products/1/c_0015.jpg');
INSERT INTO `product` VALUES ('16', '华为 HUAWEI Mate S 臻享版', '4087', '华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版', '2', 'products/1/c_0016.jpg');
INSERT INTO `product` VALUES ('17', '索尼(SONY) E6533 Z3+', '3999', '索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G', '2', 'products/1/c_0017.jpg');
INSERT INTO `product` VALUES ('18', 'HTC One M9+', '3499', 'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！', '3', 'products/1/c_0018.jpg');
INSERT INTO `product` VALUES ('19', 'HTC Desire 826d 32G 臻珠白', '1469', '后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！', '3', 'products/1/c_0020.jpg');
INSERT INTO `product` VALUES ('2', '中兴 AXON', '2699', '中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待', '3', 'products/1/c_0002.jpg');
INSERT INTO `product` VALUES ('20', '小米 红米2A 增强版 白色', '549', '新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！', '1', 'products/1/c_0019.jpg');
INSERT INTO `product` VALUES ('21', '魅族 魅蓝note2 16GB 白色', '999', '现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！', '4', 'products/1/c_0021.jpg');
INSERT INTO `product` VALUES ('22', '三星 Galaxy S5 (G9008W) 闪耀白', '1999', '5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素', '1', 'products/1/c_0022.jpg');
INSERT INTO `product` VALUES ('23', 'sonim XP7700 4G手机', '1699', '三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗', '1', 'products/1/c_0023.jpg');
INSERT INTO `product` VALUES ('24', '努比亚（nubia）Z9精英版 金色', '3888', '移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！', '3', 'products/1/c_0024.jpg');
INSERT INTO `product` VALUES ('27', '三星 Galaxy Note5（N9200）32G版', '5388', '旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！', '1', 'products/1/c_0027.jpg');
INSERT INTO `product` VALUES ('29', 'LG G4（H818）陶瓷白 国际版', '2978', '李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！', '5', 'products/1/c_0029.jpg');
INSERT INTO `product` VALUES ('3', '华为荣耀6', '1499', '荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机', '1', 'products/1/c_0003.jpg');
INSERT INTO `product` VALUES ('31', '宏碁（acer）ATC705-N50 台式电脑', '2299', '爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！', '3', 'products/1/c_0031.jpg');
INSERT INTO `product` VALUES ('34', '联想（Lenovo）小新V3000经典版', '4499', '14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！', '1', 'products/1/c_0034.jpg');
INSERT INTO `product` VALUES ('35', '华硕（ASUS）经典系列R557LI', '3699', '15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）', '4', 'products/1/c_0035.jpg');
INSERT INTO `product` VALUES ('36', '华硕（ASUS）X450J', '4399', '14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）', '1', 'products/1/c_0036.jpg');
INSERT INTO `product` VALUES ('37', '戴尔（DELL）灵越 飞匣3000系列', '3299', ' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑', '5', 'products/1/c_0037.jpg');
INSERT INTO `product` VALUES ('38', '惠普(HP)WASD 暗影精灵', '5499', '15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)', '1', 'products/1/c_0038.jpg');
INSERT INTO `product` VALUES ('39', 'Apple 配备 Retina 显示屏的 MacBook', '10288', 'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存', '4', 'products/1/c_0039.jpg');
INSERT INTO `product` VALUES ('4', '联想 P1', '1999', '联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！', '1', 'products/1/c_0004.jpg');
INSERT INTO `product` VALUES ('40', '机械革命（MECHREVO）MR X6S-M', '6599', '15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色', '1', 'products/1/c_0040.jpg');
INSERT INTO `product` VALUES ('41', '神舟（HASEE） 战神K660D-i7D2', '5499', '15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色', '3', 'products/1/c_0041.jpg');
INSERT INTO `product` VALUES ('42', '微星（MSI）GE62 2QC-264XCN', '5999', '15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色', '1', 'products/1/c_0042.jpg');
INSERT INTO `product` VALUES ('43', '雷神（ThundeRobot）G150S', '5499', '15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金', '1', 'products/1/c_0043.jpg');
INSERT INTO `product` VALUES ('44', '惠普（HP）轻薄系列 HP', '3099', '15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰', '1', 'products/1/c_0044.jpg');
INSERT INTO `product` VALUES ('45', '未来人类（Terrans Force）T5', '9899', '15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑', '3', 'products/1/c_0045.jpg');
INSERT INTO `product` VALUES ('46', '戴尔（DELL）Vostro 3800-R6308 台式电脑', '3199', '（i3-4170 4G 500G DVD 三年上门服务 Win7）黑', '2', 'products/1/c_0046.jpg');
INSERT INTO `product` VALUES ('47', '联想（Lenovo）H3050 台式电脑', '4899', '（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸', '1', 'products/1/c_0047.jpg');
INSERT INTO `product` VALUES ('48', 'Apple iPad mini 2 ME279CH/A', '1888', '（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）', '1', 'products/1/c_0048.jpg');
INSERT INTO `product` VALUES ('49', '小米（MI）7.9英寸平板', '1299', 'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色', '3', 'products/1/c_0049.jpg');
INSERT INTO `product` VALUES ('5', '摩托罗拉 moto x（x+1）', '1699', '摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！', '1', 'products/1/c_0005.jpg');
INSERT INTO `product` VALUES ('50', 'Apple iPad Air 2 MGLW2CH/A', '2299', '（9.7英寸 16G WLAN 机型 银色）', '1', 'products/1/c_0050.jpg');
INSERT INTO `product` VALUES ('6', '魅族 MX5 16GB 银黑色', '1799', '魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！', '1', 'products/1/c_0006.jpg');
INSERT INTO `product` VALUES ('7', '三星 Galaxy On7', '1398', '三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！', '2', 'products/1/c_0007.jpg');
INSERT INTO `product` VALUES ('8', 'NUU NU5', '1190', 'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机', '2', 'products/1/c_0008.jpg');
INSERT INTO `product` VALUES ('9', '乐视（Letv）乐1pro（X800）', '2299', '乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！', '1', 'products/1/c_0009.jpg');

-- ----------------------------
-- Table structure for type
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
INSERT INTO `type` VALUES ('2', '教练');
INSERT INTO `type` VALUES ('3', 'hot商品');
INSERT INTO `type` VALUES ('4', '管理平台');
INSERT INTO `type` VALUES ('5', '篮球训练');
INSERT INTO `type` VALUES ('6', '商品列表');

-- ----------------------------
-- Table structure for user
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
INSERT INTO `user` VALUES ('5b5474c9-8344-443e-8c54-89b9f2c6f5d9', '我我', '1234', '1', '1@qq.com', '2131', '1231231', '1', 'bd43802d-7ff3-4e33-b706-7e73284da7b5');
INSERT INTO `user` VALUES ('f9a4de9c-f6e7-46cb-a298-38992cdc482c', '1234', '1234', '1', '18@qq.com', '上海市崇明岛', '110', '0', '787361e8-736f-48c5-bde7-72c11d8ab4ed');
