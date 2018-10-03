/*
Navicat MySQL Data Transfer

Source Server         : YatXuan
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : webmagic

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-10-03 10:11:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tocity
-- ----------------------------
DROP TABLE IF EXISTS `tocity`;
CREATE TABLE `tocity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '市区表',
  `toCityName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '市区名称',
  `provinceId` int(10) DEFAULT NULL COMMENT '对应的主表（省级id）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
