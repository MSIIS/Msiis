/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : apple

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2015-12-18 22:35:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_roll
-- ----------------------------
DROP TABLE IF EXISTS `m_roll`;
CREATE TABLE `m_roll` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BLUE` varchar(255) NOT NULL,
  `data_source_type` varchar(255) DEFAULT NULL,
  `ISSUE_NUM` varchar(255) NOT NULL,
  `RED` varchar(255) NOT NULL,
  `RED_SUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for soupe_organizatioin
-- ----------------------------
DROP TABLE IF EXISTS `soupe_organizatioin`;
CREATE TABLE `soupe_organizatioin` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `status` int(11) NOT NULL,
  `is_leaf` bit(1) NOT NULL,
  `ogr_code` varchar(255) NOT NULL,
  `org_name` varchar(255) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT 'parent id',
  `sort_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`org_id`),
  UNIQUE KEY `UK_rirfxvxd9135eqh0dgds8htw3` (`ogr_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for soupe_permission
-- ----------------------------
DROP TABLE IF EXISTS `soupe_permission`;
CREATE TABLE `soupe_permission` (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `status` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_leaf` bit(1) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `res_name` varchar(255) DEFAULT NULL,
  `res_path` varchar(255) NOT NULL,
  `sort_code` varchar(255) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for soupe_role
-- ----------------------------
DROP TABLE IF EXISTS `soupe_role`;
CREATE TABLE `soupe_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) NOT NULL,
  `is_system` bit(1) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `role_type` int(11) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_3glfa94qcqutjxvse58p4r8lo` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for soupe_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `soupe_role_permission`;
CREATE TABLE `soupe_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `res_id` int(11) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9f9oiimau9w4bv81n873he0p9` (`res_id`),
  KEY `FK_ahoop94cdq71qolhllxqoittt` (`role_id`),
  CONSTRAINT `FK_9f9oiimau9w4bv81n873he0p9` FOREIGN KEY (`res_id`) REFERENCES `soupe_permission` (`res_id`),
  CONSTRAINT `FK_ahoop94cdq71qolhllxqoittt` FOREIGN KEY (`role_id`) REFERENCES `soupe_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for soupe_user
-- ----------------------------
DROP TABLE IF EXISTS `soupe_user`;
CREATE TABLE `soupe_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `status` int(11) NOT NULL,
  `nick_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `real_name` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_nf5o768ogumrsxawo8onhkbn6` (`nick_name`),
  UNIQUE KEY `UK_5lf81bwjr5di4kbdqtrpmotqc` (`user_name`),
  KEY `FK_738p44lwk8n9kl4osdad53bly` (`org_id`),
  CONSTRAINT `FK_738p44lwk8n9kl4osdad53bly` FOREIGN KEY (`org_id`) REFERENCES `soupe_organizatioin` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for soupe_user_role_org
-- ----------------------------
DROP TABLE IF EXISTS `soupe_user_role_org`;
CREATE TABLE `soupe_user_role_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_j0ccik4x4hlexns7hp9wcc0is` (`role_id`),
  KEY `FK_gbtkc0vt1xkt3uwlxjlmhh68c` (`user_id`),
  CONSTRAINT `FK_gbtkc0vt1xkt3uwlxjlmhh68c` FOREIGN KEY (`user_id`) REFERENCES `soupe_user` (`user_id`),
  CONSTRAINT `FK_j0ccik4x4hlexns7hp9wcc0is` FOREIGN KEY (`role_id`) REFERENCES `soupe_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
