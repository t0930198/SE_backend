-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 服務器版本:                        5.6.26-log - MySQL Community Server (GPL)
-- 服務器操作系統:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 導出 softwareengineering 的資料庫結構
CREATE DATABASE IF NOT EXISTS `softwareengineering` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `softwareengineering`;


-- 導出  表 softwareengineering.member 結構
CREATE TABLE IF NOT EXISTS `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_member_project` (`project_id`),
  KEY `FK_member_user` (`user_id`),
  KEY `FK_member_role` (`role`),
  CONSTRAINT `FK_member_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_member_role` FOREIGN KEY (`role`) REFERENCES `role` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_member_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.member 的資料：~0 rows (大約)
DELETE FROM `member`;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `project_id`, `user_id`, `role`) VALUES
	(2, 1, 1, 'admin');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- 導出  表 softwareengineering.project 結構
CREATE TABLE IF NOT EXISTS `project` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- 正在導出表  softwareengineering.project 的資料：~6 rows (大約)
DELETE FROM `project`;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`id`, `name`, `notes`) VALUES
	(1, 'ezScrum', NULL),
	(3, 'project02', 'project02'),
	(4, 'project03_update', 'project03_note_update'),
	(6, '252', '123'),
	(7, 'project07', 'project05_note'),
	(9, 'project08', 'project05_note');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


-- 導出  表 softwareengineering.requirement 結構
CREATE TABLE IF NOT EXISTS `requirement` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hadfix` tinyint(1) NOT NULL DEFAULT '0',
  `star_time` bigint(20) NOT NULL,
  `command` varchar(255) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `projectid` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.requirement 的資料：~0 rows (大約)
DELETE FROM `requirement`;
/*!40000 ALTER TABLE `requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirement` ENABLE KEYS */;


-- 導出  表 softwareengineering.requirement_relation 結構
CREATE TABLE IF NOT EXISTS `requirement_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rid` int(11) unsigned NOT NULL,
  `relation_id` int(11) unsigned DEFAULT NULL,
  `relation_test` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_requirement_relation_requirement` (`rid`),
  CONSTRAINT `FK_requirement_relation_requirement` FOREIGN KEY (`rid`) REFERENCES `requirement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.requirement_relation 的資料：~0 rows (大約)
DELETE FROM `requirement_relation`;
/*!40000 ALTER TABLE `requirement_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirement_relation` ENABLE KEYS */;


-- 導出  表 softwareengineering.role 結構
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.role 的資料：~4 rows (大約)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`) VALUES
	(1, 'admin'),
	(2, 'project_manager'),
	(3, 'reviewer'),
	(4, 'team_mamber');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- 導出  表 softwareengineering.test 結構
CREATE TABLE IF NOT EXISTS `test` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `requirementid` int(11) unsigned NOT NULL,
  `projectid` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__requirement` (`requirementid`),
  CONSTRAINT `FK__requirement` FOREIGN KEY (`requirementid`) REFERENCES `requirement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.test 的資料：~0 rows (大約)
DELETE FROM `test`;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;


-- 導出  表 softwareengineering.user 結構
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=4066 DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.user 的資料：~12 rows (大約)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `account`, `password`, `email`) VALUES
	(1, 'henry', '134', 'ibmboy19@gmail.com'),
	(2, 'mike', '1234', 'mike@gmail.com'),
	(3, 'YC', '2266', 'mike02@gmail.com'),
	(4, 'YC Chen', '1', 'mike02@gmail.com'),
	(5, '111 Chen', '1', 'mike02@gmail.com'),
	(6, '123', '1234', 'test@gmail.com'),
	(7, '1233', '124', 'test@gmail.com'),
	(8, 'test', '1234231', 'test@gmail.com'),
	(9, 'dddddddd', '1234', 'test@gmail.com'),
	(10, '555555', '1234', 'test@gmail.com'),
	(11, 'CutecoolIsBad', '1234', 'cutecool@gmail.com'),
	(3821, 'MEOW', '5566', 'test@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
