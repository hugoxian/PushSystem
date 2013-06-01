-- MySQL dump 10.13  Distrib 5.5.28, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: PS
-- ------------------------------------------------------
-- Server version	5.5.28-0ubuntu0.12.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CDR_META`
--

DROP TABLE IF EXISTS `CDR_META`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CDR_META` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `VERSION` varchar(10) NOT NULL COMMENT 'CDR版本',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `UA` varchar(200) DEFAULT NULL COMMENT 'UA信息',
  `CLIENT_IP` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `CLIENT_TYPE` int(2) DEFAULT NULL COMMENT '1、wap；2、3g；3、web；4、app；9、other',
  `ACTION_TYPE` int(5) DEFAULT NULL COMMENT '1、登入；2 、登出；3、在线人数，具体参考业务说明',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '不同的cdr记录该字段意义不一样',
  `EVENT_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'CDR发生时间',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '该条cdr记录时间',
  `PROCESS_TIME` int(11) DEFAULT NULL COMMENT '处理耗时，单位毫秒，系统做性能分析用',
  `DURATION` int(11) DEFAULT NULL COMMENT '登入登出类，记录回话时长及退出原因，时长，单位秒',
  `REASON` int(1) NOT NULL DEFAULT '0' COMMENT '退出原因：0：user 1：server',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CDR_META`
--

LOCK TABLES `CDR_META` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `PS_CLIENT`
--

DROP TABLE IF EXISTS `PS_CLIENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PS_CLIENT` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `appKey` varchar(32) NOT NULL COMMENT '软件唯一标示',
  `deviceId` varchar(16) NOT NULL COMMENT '设备ID',
  `userAgent` varchar(200) DEFAULT NULL COMMENT '客户端类型',
  `lastLatLon` varchar(50) DEFAULT NULL COMMENT '最新经纬度',
  `lastAccessTime` datetime NOT NULL COMMENT '创建时间',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0：正常；1：黑名单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PS_CLIENT`
--

LOCK TABLES `PS_CLIENT` WRITE;
/*!40000 ALTER TABLE `PS_CLIENT` DISABLE KEYS */;
INSERT INTO `PS_CLIENT` VALUES (6,'6bdc4fccfdcdc811a202d0278375f408','355302040541790','Android','lon,lat','2013-03-19 13:19:23','2013-03-18 16:17:33',0),(7,'6bdc4fccfdcdc811a202d0278375f408','868033013905189','Android','lon,lat','2013-03-20 18:40:25','2013-03-18 16:19:41',0);
/*!40000 ALTER TABLE `PS_CLIENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PS_NODE`
--

DROP TABLE IF EXISTS `PS_NODE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PS_NODE` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '父节点ID',
  `name` varchar(10) NOT NULL COMMENT '节点中文名称',
  `end` int(1) NOT NULL DEFAULT '0' COMMENT '0:非终极子节点，1：终极节点',
  `sequence` int(5) NOT NULL COMMENT '序号',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0：有效，1：无效',
  `uri` varchar(32) DEFAULT NULL COMMENT '点击跳转路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PS_NODE`
--

LOCK TABLES `PS_NODE` WRITE;
/*!40000 ALTER TABLE `PS_NODE` DISABLE KEYS */;
INSERT INTO `PS_NODE` VALUES (1,0,'服务器',0,1,0,NULL),(2,0,'系统管理',0,2,0,NULL),(3,0,'个人中心',0,3,0,NULL),(4,0,'客户端',0,4,0,NULL),(5,0,'用户/推送',0,5,0,NULL),(6,1,'服务器设置',1,1,0,'server/baseInfo.do'),(7,1,'数据库',1,2,0,'server/databaseInfo.do'),(8,1,'缓存服务器信息',1,3,0,'server/cache.do'),(9,1,'错误日志',1,4,0,'server/log.do'),(10,1,'在线会话',1,5,0,'server/session.do'),(11,1,'性能统计',1,6,0,'server/performance.do'),(12,2,'角色管理',1,1,0,'system/rolesInfo.do'),(13,2,'操作员管理',1,2,0,'system/operatersInfo.do'),(14,2,'节点管理',1,3,0,'system/nodesInfo.do'),(15,2,'操作记录',1,4,0,'system/opreateRecord.do'),(16,3,'我的资料',1,1,0,'personal/profile.do'),(17,3,'修改密码',1,2,0,'personal/changePassword.do'),(18,3,'登录记录',1,3,0,'personal/loginRecord.do'),(19,4,'在线终端',1,1,0,'client/iosession.do'),(20,5,'多个用户推送',1,1,0,'push/groupSend.do'),(21,5,'单个用户推送',1,2,0,'push/singleSend.do'),(22,5,'推送记录',1,3,0,'push/record.do'),(23,4,'所有终端',1,2,0,'client/all.do'),(24,4,'用户分析',1,3,0,'client/analyse.do');
/*!40000 ALTER TABLE `PS_NODE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PS_OPERATOR`
--

DROP TABLE IF EXISTS `PS_OPERATOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PS_OPERATOR` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) NOT NULL COMMENT '用户名称，用于显示',
  `account` varchar(16) NOT NULL COMMENT '登录名称',
  `password` varchar(32) NOT NULL COMMENT '登录密码，MD5加密,小写',
  `roleId` int(11) NOT NULL COMMENT '所属角色ID',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0：正常有效；1：无效，不能登录',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `lastLoginDate` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PS_OPERATOR`
--

LOCK TABLES `PS_OPERATOR` WRITE;
/*!40000 ALTER TABLE `PS_OPERATOR` DISABLE KEYS */;
INSERT INTO `PS_OPERATOR` VALUES (1,'冼志列','admin','21232f297a57a5a743894a0e4a801fc3',1,0,'2012-09-22 10:01:30','2013-03-19 11:56:29','hugo.xian@qq.com','15818559132'),(2,'4S店','auto4s','670b14728ad9902aecba32e22fa4f6bd',2,0,'2012-09-22 10:05:08','2013-03-21 09:47:12',NULL,NULL),(3,'智能公交','citybus','670b14728ad9902aecba32e22fa4f6bd',2,1,'2012-09-22 10:06:22','2012-09-23 22:21:02',NULL,NULL),(4,'E车天地','ecar','670b14728ad9902aecba32e22fa4f6bd',2,0,'2012-09-22 10:15:32','2012-10-06 12:11:25',NULL,NULL),(5,'管理员','root','63a9f0ea7bb98050796b649e85481845',3,0,'2012-09-22 10:15:32','2012-09-23 22:21:02',NULL,NULL),(6,'深圳消防','fire','cfcd208495d565ef66e7dff9f98764da',2,0,'2012-09-22 10:15:32','2013-03-19 12:02:31',NULL,NULL);
/*!40000 ALTER TABLE `PS_OPERATOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PS_PUSH_RECORD`
--

DROP TABLE IF EXISTS `PS_PUSH_RECORD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PS_PUSH_RECORD` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `taskId` int(11) NOT NULL COMMENT '任务ID',
  `deviceId` varchar(16) NOT NULL COMMENT '设备ID',
  `messageId` varchar(32) NOT NULL COMMENT '消息ID',
  `pushStatus` int(1) NOT NULL DEFAULT '0' COMMENT '0:未发送，1：发送中 9：发送成功',
  `pushTime` datetime NOT NULL COMMENT '推送时间',
  `finishTime` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PS_PUSH_RECORD`
--

LOCK TABLES `PS_PUSH_RECORD` WRITE;
/*!40000 ALTER TABLE `PS_PUSH_RECORD` DISABLE KEYS */;
INSERT INTO `PS_PUSH_RECORD` VALUES (1,1,'355302040541790','94666b81eec943e7bc6181f015bafcf8',0,'2013-03-21 10:05:21',NULL),(2,1,'868033013905189','c79be7c9333b4560a56581664f8306f7',0,'2013-03-21 10:05:21',NULL);
/*!40000 ALTER TABLE `PS_PUSH_RECORD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PS_PUSH_TASK`
--

DROP TABLE IF EXISTS `PS_PUSH_TASK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PS_PUSH_TASK` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '0:单个用户 1：全部在线用户，2：全部用户，3：某地区用户，4：某UA用户 9：其他',
  `operatorId` int(11) NOT NULL COMMENT '操作用户ID',
  `appKey` varchar(32) NOT NULL COMMENT '软件唯一标示',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `sendTime` datetime NOT NULL COMMENT '发送时间',
  `title` varchar(32) NOT NULL COMMENT '标题',
  `content` varchar(140) NOT NULL COMMENT '内容',
  `channel` int(1) NOT NULL DEFAULT '0' COMMENT '0:api 1:web 2:socket',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '发送总数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PS_PUSH_TASK`
--

LOCK TABLES `PS_PUSH_TASK` WRITE;
/*!40000 ALTER TABLE `PS_PUSH_TASK` DISABLE KEYS */;
INSERT INTO `PS_PUSH_TASK` VALUES (1,2,2,'6bdc4fccfdcdc811a202d0278375f408','2013-03-21 10:05:21','2013-03-21 10:05:21','你好啊','d',0,0);
/*!40000 ALTER TABLE `PS_PUSH_TASK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PS_ROLE`
--

DROP TABLE IF EXISTS `PS_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PS_ROLE` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(10) NOT NULL COMMENT '角色名称',
  `desc` varchar(140) NOT NULL COMMENT '角色描述',
  `permitIdsStr` varchar(200) NOT NULL COMMENT '有权限的节点id字符串，用","分隔（只对一级节点做权限）',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '角色状态，0：有效；1：无效',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PS_ROLE`
--

LOCK TABLES `PS_ROLE` WRITE;
/*!40000 ALTER TABLE `PS_ROLE` DISABLE KEYS */;
INSERT INTO `PS_ROLE` VALUES (1,'超级管理员','拥有最高权限','1,2,3,4,5',0,'2012-09-22 10:01:30'),(2,'程序注册者','只能查看自己程序相关的信息，只能向自己的程序推送消息','3,4,5',0,'2012-09-22 10:05:08'),(3,'运维人员','查看服务器信息','1,2',1,'2012-09-22 10:05:08');
/*!40000 ALTER TABLE `PS_ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PS_SOFTWARE`
--

DROP TABLE IF EXISTS `PS_SOFTWARE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PS_SOFTWARE` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `appKey` varchar(32) NOT NULL COMMENT '软件唯一标示',
  `name` varchar(16) NOT NULL COMMENT '软件名称',
  `nameEn` varchar(16) DEFAULT NULL COMMENT '软件英文名称',
  `packageName` varchar(50) DEFAULT NULL COMMENT '包名称，Android APP需要',
  `iconUrl` varchar(100) DEFAULT NULL COMMENT '图标地址',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0：正常有效；1：无效',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `operatorId` int(11) NOT NULL COMMENT '所属操作员ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PS_SOFTWARE`
--

LOCK TABLES `PS_SOFTWARE` WRITE;
/*!40000 ALTER TABLE `PS_SOFTWARE` DISABLE KEYS */;
INSERT INTO `PS_SOFTWARE` VALUES (1,'6bdc4fccfdcdc811a202d0278375f408','车辆守护','Car','com.zcwl.car',NULL,0,'2012-09-22 10:01:30',2),(2,'986539873c894ff39db55902bfb36145','深圳消防','Fire','com.zcwl.fire',NULL,0,'2012-09-22 10:01:30',6);
/*!40000 ALTER TABLE `PS_SOFTWARE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PS_TEST`
--

DROP TABLE IF EXISTS `PS_TEST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PS_TEST` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) NOT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PS_TEST`
--

LOCK TABLES `PS_TEST` WRITE;
/*!40000 ALTER TABLE `PS_TEST` DISABLE KEYS */;
INSERT INTO `PS_TEST` VALUES (1,'你好'),(2,'你好'),(3,'??');
/*!40000 ALTER TABLE `PS_TEST` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-03-21 10:06:50
