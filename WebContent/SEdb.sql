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
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.member 的資料：~6 rows (大約)
DELETE FROM `member`;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `project_id`, `user_id`, `role`, `username`) VALUES
	(8, 3, 2, 'PM', 'mike'),
	(9, 1, 1, 'Reviewer', 'henry'),
	(11, 3, 1, 'ST', 'henry'),
	(12, 4, 1, 'PM', 'henry'),
	(13, 6, 1, 'PM', 'henry'),
	(14, 23, 1, 'PM', 'henry');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- 導出  表 softwareengineering.project 結構
CREATE TABLE IF NOT EXISTS `project` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- 正在導出表  softwareengineering.project 的資料：~17 rows (大約)
DELETE FROM `project`;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`id`, `name`, `notes`) VALUES
	(1, 'ezScrum', NULL),
	(3, 'project02', 'project02'),
	(4, 'project03_update', 'project03_note_update'),
	(7, 'project07', 'project05_note'),
	(9, 'project08', 'project05_note'),
	(11, '123', '321'),
	(12, '1234', '4321'),
	(13, '', ''),
	(14, 'djvhslfh', 'loishdfalofh'),
	(15, 'testProject', 'testproject_post'),
	(16, 'dfasdfassg', 'agfdgfdgs'),
	(17, '124erfetfe', 'refsedfczsd'),
	(18, '4trq345g', '45t3g534g5'),
	(19, 'hbhbeg', 'efgvetgv'),
	(20, 'projectName', 'projectTestNote'),
	(21, 'qwe', 'qwe'),
	(22, 'qwew', 'qwe');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


-- 導出  表 softwareengineering.requirement 結構
CREATE TABLE IF NOT EXISTS `requirement` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hadfix` tinyint(1) NOT NULL DEFAULT '0',
  `star_time` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `projectid` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.requirement 的資料：~11 rows (大約)
DELETE FROM `requirement`;
/*!40000 ALTER TABLE `requirement` DISABLE KEYS */;
INSERT INTO `requirement` (`id`, `name`, `description`, `hadfix`, `star_time`, `comment`, `type`, `projectid`) VALUES
	(3, 'requirement3', 'r1d1', 1, 1451462174788, NULL, NULL, 3),
	(5, 'requirement5', 'r3d3', 1, 1451462197028, NULL, NULL, 3),
	(6, 'requirement6', 'test', 0, 1451480192181, 'change comment', 'done', 3),
	(7, 'requirement7', 'rest03description', 1, 1451484092627, NULL, NULL, 3),
	(8, 'requirement8', 'rest10description', 1, 1451484539430, NULL, NULL, 3),
	(9, 'requirement9', '321', 1, 1451485016738, NULL, NULL, 3),
	(10, 'requirement10', 'description', 1, 1451558097805, NULL, NULL, 3),
	(11, 'requirement11', 'description', 0, 1451998647439, 'improve', 'ready', 3),
	(12, 'requirement12', 'description', 0, 1452000048424, 'new', 'do', 3),
	(13, 'requirement13', 'description is success', 0, 1452000048424, 'good', 'do', 3),
	(14, 'dfa', 'dasfasf', 1, 1452008668477, NULL, NULL, 3),
	(15, 'asfdaf', 'rgffgsfdgsd', 1, 1452008714674, NULL, NULL, 1),
	(16, 'lkhlkn', 'kihglihblihb', 1, 1452045358796, NULL, NULL, 1);
/*!40000 ALTER TABLE `requirement` ENABLE KEYS */;


-- 導出  表 softwareengineering.requirement_relation 結構
CREATE TABLE IF NOT EXISTS `requirement_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rid` int(11) unsigned NOT NULL,
  `relation_id` int(11) unsigned DEFAULT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 正在導出表  softwareengineering.test 的資料：~11 rows (大約)
DELETE FROM `test`;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`id`, `name`, `description`, `requirementid`, `projectid`) VALUES
	(2, 'project02test02', 'test02description', 3, 3),
	(3, 'project02test03', 'test03description', 3, 3),
	(4, 'project02test04', 'test04description', 12, 3),
	(5, 'project02test05', 'test05description', 7, 3),
	(6, 'project02test06', 'test06description', 8, 3),
	(7, 'project02test07', 'test07description', 10, 3),
	(8, 'project02test08', 'test08description', 13, 3),
	(9, 'project02test09', 'test09description', 5, 3),
	(10, 'project02test10', 'test10description', 11, 3),
	(11, 'project02test11', 'test11description', 6, 3),
	(12, 'project02test12', 'test12description', 9, 3);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;


-- 導出  表 softwareengineering.user 結構
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=4067 DEFAULT CHARSET=utf8;

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
	(3821, 'MEOW', '5566', 'test@gmail.com'),
	(4066, 'wwswss', '', 'ssss');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
