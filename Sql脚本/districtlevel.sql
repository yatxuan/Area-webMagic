/*
Navicat MySQL Data Transfer

Source Server         : YatXuan
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : webmagic

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-10-03 10:11:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for districtlevel
-- ----------------------------
DROP TABLE IF EXISTS `districtlevel`;
CREATE TABLE `districtlevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区县表',
  `districtLevelName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '区级名称',
  `toCityId` int(10) DEFAULT NULL COMMENT '对应的市级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1087 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
