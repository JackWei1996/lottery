/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : lottery

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 29/11/2019 09:35:27
*/

set character set utf8;
drop database if exists `lottery`
--    
-- 数据库: `lottery`    
--    
CREATE DATABASE `lottery` DEFAULT CHARACTER SET utf8; 
USE `lottery`;  


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dlt_old
-- ----------------------------
DROP TABLE IF EXISTS `dlt_old`;
CREATE TABLE `dlt_old`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `periods` int(5) NOT NULL,
  `no1` int(5) NOT NULL,
  `no2` int(5) NOT NULL,
  `no3` int(5) NOT NULL,
  `no4` int(5) NOT NULL,
  `no5` int(5) NOT NULL,
  `no6` int(5) NOT NULL,
  `no7` int(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1900 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dlt_seer
-- ----------------------------
DROP TABLE IF EXISTS `dlt_seer`;
CREATE TABLE `dlt_seer`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `periods` int(5) DEFAULT NULL,
  `no1` int(5) NOT NULL,
  `no2` int(5) NOT NULL,
  `no3` int(5) NOT NULL,
  `no4` int(5) NOT NULL,
  `no5` int(5) NOT NULL,
  `no6` int(5) NOT NULL,
  `no7` int(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12397476 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
