/*
Navicat MySQL Data Transfer

Source Server         : YatXuan
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : webmagic

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-10-03 10:11:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '省份表',
  `provinceName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '省份名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
