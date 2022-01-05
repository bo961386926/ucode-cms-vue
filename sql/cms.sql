-- MySQL dump 10.13  Distrib 8.0.26, for macos11.3 (x86_64)
--
-- Host: localhost    Database: ucode-cms
-- ------------------------------------------------------
-- Server version	5.7.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2021-11-22 09:57:56','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2021-11-22 09:57:56','',NULL,'初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2021-11-22 09:57:56','',NULL,'深色主题theme-dark，浅色主题theme-light'),(4,'账号自助-验证码开关','sys.account.captchaOnOff','true','Y','admin','2021-11-22 09:57:56','',NULL,'是否开启验证码功能（true开启，false关闭）'),(5,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2021-11-22 09:57:56','',NULL,'是否开启注册用户功能（true开启，false关闭）');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','若依科技',0,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(101,100,'0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(102,100,'0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(103,101,'0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(104,101,'0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(106,101,'0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(107,101,'0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(108,102,'0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL),(109,102,'0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-11-22 09:57:54','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','Y','0','admin','2021-11-22 09:57:56','',NULL,'性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2021-11-22 09:57:56','',NULL,'性别女'),(3,3,'未知','2','sys_user_sex','','','N','0','admin','2021-11-22 09:57:56','',NULL,'性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2021-11-22 09:57:56','',NULL,'显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2021-11-22 09:57:56','',NULL,'正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2021-11-22 09:57:56','',NULL,'正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2021-11-22 09:57:56','',NULL,'默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2021-11-22 09:57:56','',NULL,'系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2021-11-22 09:57:56','',NULL,'系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2021-11-22 09:57:56','',NULL,'通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2021-11-22 09:57:56','',NULL,'公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2021-11-22 09:57:56','',NULL,'正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'关闭状态'),(18,1,'新增','1','sys_oper_type','','info','N','0','admin','2021-11-22 09:57:56','',NULL,'新增操作'),(19,2,'修改','2','sys_oper_type','','info','N','0','admin','2021-11-22 09:57:56','',NULL,'修改操作'),(20,3,'删除','3','sys_oper_type','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'删除操作'),(21,4,'授权','4','sys_oper_type','','primary','N','0','admin','2021-11-22 09:57:56','',NULL,'授权操作'),(22,5,'导出','5','sys_oper_type','','warning','N','0','admin','2021-11-22 09:57:56','',NULL,'导出操作'),(23,6,'导入','6','sys_oper_type','','warning','N','0','admin','2021-11-22 09:57:56','',NULL,'导入操作'),(24,7,'强退','7','sys_oper_type','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'强退操作'),(25,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2021-11-22 09:57:56','',NULL,'生成操作'),(26,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'清空操作'),(27,1,'成功','0','sys_common_status','','primary','N','0','admin','2021-11-22 09:57:56','',NULL,'正常状态'),(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2021-11-22 09:57:56','',NULL,'停用状态');
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2021-11-22 09:57:56','',NULL,'用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2021-11-22 09:57:56','',NULL,'菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2021-11-22 09:57:56','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2021-11-22 09:57:56','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2021-11-22 09:57:56','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2021-11-22 09:57:56','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2021-11-22 09:57:56','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2021-11-22 09:57:56','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2021-11-22 09:57:56','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2021-11-22 09:57:56','',NULL,'登录状态列表'),(11,'DD','DD','0','admin','2021-12-28 16:52:27','',NULL,'DD');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2021-11-22 09:57:56','',NULL,''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2021-11-22 09:57:56','',NULL,''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2021-11-22 09:57:56','',NULL,'');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (100,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-11-22 10:54:31'),(101,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-11-22 10:54:35'),(102,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-11-22 10:54:42'),(103,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-11-22 13:41:32'),(104,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-11-22 14:46:55'),(105,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-11-22 14:47:02'),(106,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-22 14:02:28'),(107,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-22 15:13:41'),(108,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-22 16:18:19'),(109,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功','2021-12-22 16:43:14'),(110,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-22 16:43:21'),(111,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功','2021-12-22 16:43:25'),(112,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-22 16:43:29'),(113,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-22 16:43:36'),(114,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-23 15:42:07'),(115,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-23 16:43:58'),(116,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-23 18:59:22'),(117,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-23 18:59:27'),(118,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-24 11:00:19'),(119,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-24 11:00:28'),(120,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-24 13:39:34'),(121,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效','2021-12-24 18:03:21'),(122,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-24 18:03:26'),(123,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效','2021-12-26 14:04:20'),(124,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效','2021-12-26 14:04:22'),(125,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-26 14:04:26'),(126,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-26 14:37:24'),(127,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-26 15:36:25'),(128,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-26 22:50:57'),(129,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-26 22:51:05'),(130,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-27 10:06:56'),(131,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','1','验证码错误','2021-12-27 10:37:49'),(132,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','1','验证码错误','2021-12-27 10:37:50'),(133,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','1','验证码错误','2021-12-27 10:37:50'),(134,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','1','验证码错误','2021-12-27 10:37:50'),(135,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','1','验证码错误','2021-12-27 10:37:50'),(136,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','1','验证码错误','2021-12-27 10:37:51'),(137,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','1','验证码错误','2021-12-27 10:37:51'),(138,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','1','验证码错误','2021-12-27 10:37:51'),(139,'admin','192.168.181.100','内网IP','Chrome 9','Mac OS X','0','登录成功','2021-12-27 10:37:51'),(140,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-27 11:00:49'),(141,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-27 11:00:49'),(142,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-27 11:00:49'),(143,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-27 13:39:46'),(144,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-27 13:39:46'),(145,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-27 15:13:18'),(146,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-27 15:13:18'),(147,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-27 15:13:18'),(148,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-27 15:13:18'),(149,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 10:05:32'),(150,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 10:05:32'),(151,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 10:05:33'),(152,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 10:05:33'),(153,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 10:05:33'),(154,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-28 10:05:34'),(155,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-28 11:21:31'),(156,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 13:06:28'),(157,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 13:06:28'),(158,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 13:06:28'),(159,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 13:06:28'),(160,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 13:06:28'),(161,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误','2021-12-28 13:06:29'),(162,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功','2021-12-28 13:06:29'),(163,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(164,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(165,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(166,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(167,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(168,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(169,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(170,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(171,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(172,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(173,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(174,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(175,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(176,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(177,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(178,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(179,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(180,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(181,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(182,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(183,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(184,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(185,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(186,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(187,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(188,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(189,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(190,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(191,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(192,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(193,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功',NULL),(194,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(195,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(196,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(197,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功',NULL),(198,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(199,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(200,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(201,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(202,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(203,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(204,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(205,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(206,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(207,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(208,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(209,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功',NULL),(210,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(211,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(212,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(213,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(214,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(215,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(216,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(217,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(218,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(219,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(220,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(221,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(222,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(223,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(224,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(225,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(226,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功',NULL),(227,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(228,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(229,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功',NULL),(230,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(231,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(232,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(233,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(234,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(235,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(236,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功',NULL),(237,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(238,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(239,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(240,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(241,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(242,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功',NULL),(243,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效',NULL),(244,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效',NULL),(245,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效',NULL),(246,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效',NULL),(247,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效',NULL),(248,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效',NULL),(249,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(250,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','退出成功',NULL),(251,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(252,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(253,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码已失效',NULL),(254,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(255,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(256,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','1','验证码错误',NULL),(257,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(258,'admin','192.168.181.161','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(259,'admin','192.168.181.161','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(260,'admin','192.168.181.162','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(261,'admin','127.0.0.1','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(262,'admin','192.168.181.162','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(263,'admin','192.168.181.162','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL),(264,'admin','192.168.181.162','内网IP','Chrome 8','Mac OS X','0','登录成功',NULL);
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1061 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,'',1,0,'M','0','0','','system','admin','2021-11-22 09:57:54','',NULL,'系统管理目录'),(2,'系统监控',0,6,'monitor',NULL,'',1,0,'M','0','0','','monitor','admin','2021-11-22 09:57:54','admin','2022-01-05 15:40:59','系统监控目录'),(3,'系统工具',0,7,'tool',NULL,'',1,0,'M','0','0','','tool','admin','2021-11-22 09:57:54','admin','2022-01-05 15:41:05','系统工具目录'),(100,'用户管理',1,1,'user','system/user/index','',1,0,'C','0','0','system:user:list','user','admin','2021-11-22 09:57:54','',NULL,'用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index','',1,0,'C','0','0','system:role:list','peoples','admin','2021-11-22 09:57:54','',NULL,'角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','',1,0,'C','0','0','system:menu:list','tree-table','admin','2021-11-22 09:57:54','',NULL,'菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','',1,0,'C','0','0','system:dept:list','tree','admin','2021-11-22 09:57:54','',NULL,'部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','',1,0,'C','0','0','system:post:list','post','admin','2021-11-22 09:57:54','',NULL,'岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','',1,0,'C','0','0','system:dict:list','dict','admin','2021-11-22 09:57:54','',NULL,'字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','',1,0,'C','0','0','system:config:list','edit','admin','2021-11-22 09:57:54','',NULL,'参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','',1,0,'C','0','0','system:notice:list','message','admin','2021-11-22 09:57:54','',NULL,'通知公告菜单'),(108,'日志管理',1,9,'log','','',1,0,'M','1','0','','log','admin','2021-11-22 09:57:54','admin','2021-12-28 13:21:19','日志管理菜单'),(109,'在线用户',1,11,'online','monitor/online/index','',1,0,'C','0','0','monitor:online:list','online','admin','2021-11-22 09:57:54','admin','2021-12-28 13:22:30','在线用户菜单'),(110,'定时任务',2,2,'job','monitor/job/index','',1,0,'C','0','0','monitor:job:list','job','admin','2021-11-22 09:57:54','',NULL,'定时任务菜单'),(111,'数据监控',2,3,'druid','monitor/druid/index','',1,0,'C','0','0','monitor:druid:list','druid','admin','2021-11-22 09:57:54','',NULL,'数据监控菜单'),(112,'服务监控',1,12,'server','monitor/server/index','',1,0,'C','0','0','monitor:server:list','server','admin','2021-11-22 09:57:54','admin','2021-12-28 13:23:01','服务监控菜单'),(113,'缓存监控',2,5,'cache','monitor/cache/index','',1,0,'C','0','0','monitor:cache:list','redis','admin','2021-11-22 09:57:54','',NULL,'缓存监控菜单'),(114,'表单构建',3,1,'build','tool/build/index','',1,0,'C','0','0','tool:build:list','build','admin','2021-11-22 09:57:54','',NULL,'表单构建菜单'),(115,'代码生成',0,3,'gen','tool/gen/index','',1,0,'C','0','0','tool:gen:list','code','admin','2021-11-22 09:57:54','admin','2022-01-05 15:40:35','代码生成菜单'),(116,'系统接口',0,4,'http://192.168.181.162:8080/doc.html','tool/swagger/index','',0,1,'C','0','0','tool:swagger:list','swagger','admin','2021-11-22 09:57:54','admin','2022-01-05 15:42:00','系统接口菜单'),(500,'操作日志',1,9,'operlog','monitor/operlog/index','',1,0,'C','0','0','monitor:operlog:list','form','admin','2021-11-22 09:57:54','admin','2021-12-28 13:20:26','操作日志菜单'),(501,'登录日志',1,10,'logininfor','monitor/logininfor/index','',1,0,'C','0','0','monitor:logininfor:list','logininfor','admin','2021-11-22 09:57:54','admin','2021-12-28 13:21:10','登录日志菜单'),(1001,'用户查询',100,1,'','','',1,0,'F','0','0','system:user:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1002,'用户新增',100,2,'','','',1,0,'F','0','0','system:user:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1003,'用户修改',100,3,'','','',1,0,'F','0','0','system:user:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1004,'用户删除',100,4,'','','',1,0,'F','0','0','system:user:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1005,'用户导出',100,5,'','','',1,0,'F','0','0','system:user:export','#','admin','2021-11-22 09:57:54','',NULL,''),(1006,'用户导入',100,6,'','','',1,0,'F','0','0','system:user:import','#','admin','2021-11-22 09:57:54','',NULL,''),(1007,'重置密码',100,7,'','','',1,0,'F','0','0','system:user:resetPwd','#','admin','2021-11-22 09:57:54','',NULL,''),(1008,'角色查询',101,1,'','','',1,0,'F','0','0','system:role:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1009,'角色新增',101,2,'','','',1,0,'F','0','0','system:role:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1010,'角色修改',101,3,'','','',1,0,'F','0','0','system:role:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1011,'角色删除',101,4,'','','',1,0,'F','0','0','system:role:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1012,'角色导出',101,5,'','','',1,0,'F','0','0','system:role:export','#','admin','2021-11-22 09:57:54','',NULL,''),(1013,'菜单查询',102,1,'','','',1,0,'F','0','0','system:menu:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1014,'菜单新增',102,2,'','','',1,0,'F','0','0','system:menu:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1015,'菜单修改',102,3,'','','',1,0,'F','0','0','system:menu:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1016,'菜单删除',102,4,'','','',1,0,'F','0','0','system:menu:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1017,'部门查询',103,1,'','','',1,0,'F','0','0','system:dept:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1018,'部门新增',103,2,'','','',1,0,'F','0','0','system:dept:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1019,'部门修改',103,3,'','','',1,0,'F','0','0','system:dept:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1020,'部门删除',103,4,'','','',1,0,'F','0','0','system:dept:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1021,'岗位查询',104,1,'','','',1,0,'F','0','0','system:post:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1022,'岗位新增',104,2,'','','',1,0,'F','0','0','system:post:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1023,'岗位修改',104,3,'','','',1,0,'F','0','0','system:post:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1024,'岗位删除',104,4,'','','',1,0,'F','0','0','system:post:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1025,'岗位导出',104,5,'','','',1,0,'F','0','0','system:post:export','#','admin','2021-11-22 09:57:54','',NULL,''),(1026,'字典查询',105,1,'#','','',1,0,'F','0','0','system:dict:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1027,'字典新增',105,2,'#','','',1,0,'F','0','0','system:dict:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1028,'字典修改',105,3,'#','','',1,0,'F','0','0','system:dict:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1029,'字典删除',105,4,'#','','',1,0,'F','0','0','system:dict:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1030,'字典导出',105,5,'#','','',1,0,'F','0','0','system:dict:export','#','admin','2021-11-22 09:57:54','',NULL,''),(1031,'参数查询',106,1,'#','','',1,0,'F','0','0','system:config:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1032,'参数新增',106,2,'#','','',1,0,'F','0','0','system:config:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1033,'参数修改',106,3,'#','','',1,0,'F','0','0','system:config:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1034,'参数删除',106,4,'#','','',1,0,'F','0','0','system:config:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1035,'参数导出',106,5,'#','','',1,0,'F','0','0','system:config:export','#','admin','2021-11-22 09:57:54','',NULL,''),(1036,'公告查询',107,1,'#','','',1,0,'F','0','0','system:notice:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1037,'公告新增',107,2,'#','','',1,0,'F','0','0','system:notice:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1038,'公告修改',107,3,'#','','',1,0,'F','0','0','system:notice:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1039,'公告删除',107,4,'#','','',1,0,'F','0','0','system:notice:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1040,'操作查询',500,1,'#','','',1,0,'F','0','0','monitor:operlog:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1041,'操作删除',500,2,'#','','',1,0,'F','0','0','monitor:operlog:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1042,'日志导出',500,4,'#','','',1,0,'F','0','0','monitor:operlog:export','#','admin','2021-11-22 09:57:54','',NULL,''),(1043,'登录查询',501,1,'#','','',1,0,'F','0','0','monitor:logininfor:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1044,'登录删除',501,2,'#','','',1,0,'F','0','0','monitor:logininfor:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1045,'日志导出',501,3,'#','','',1,0,'F','0','0','monitor:logininfor:export','#','admin','2021-11-22 09:57:54','',NULL,''),(1046,'在线查询',109,1,'#','','',1,0,'F','0','0','monitor:online:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1047,'批量强退',109,2,'#','','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2021-11-22 09:57:54','',NULL,''),(1048,'单条强退',109,3,'#','','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2021-11-22 09:57:54','',NULL,''),(1049,'任务查询',110,1,'#','','',1,0,'F','0','0','monitor:job:query','#','admin','2021-11-22 09:57:54','',NULL,''),(1050,'任务新增',110,2,'#','','',1,0,'F','0','0','monitor:job:add','#','admin','2021-11-22 09:57:54','',NULL,''),(1051,'任务修改',110,3,'#','','',1,0,'F','0','0','monitor:job:edit','#','admin','2021-11-22 09:57:54','',NULL,''),(1052,'任务删除',110,4,'#','','',1,0,'F','0','0','monitor:job:remove','#','admin','2021-11-22 09:57:54','',NULL,''),(1053,'状态修改',110,5,'#','','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2021-11-22 09:57:54','',NULL,''),(1054,'任务导出',110,7,'#','','',1,0,'F','0','0','monitor:job:export','#','admin','2021-11-22 09:57:54','',NULL,'');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'温馨提醒：2018-07-01 若依新版本发布啦','2',_binary '新版本内容','0','admin','2021-11-22 09:57:56','',NULL,'管理员'),(2,'维护通知：2018-07-01 若依系统凌晨维护','1',_binary '维护内容','0','admin','2021-11-22 09:57:56','',NULL,'管理员'),(3,'123123123','1',_binary '<h1>3123123</h1><h2>3213123123213</h2><h5>31231313</h5><p><br></p><p><br></p><p><br></p><p>312312312das大三大四的</p>','0','admin','2021-12-28 10:06:29','',NULL,NULL);
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (1,'参数管理',1,'xin.altitude.cms.system.controller.SysConfigController.add()','POST',1,'admin',NULL,'/system/config','127.0.0.1','内网IP','{\"configName\":\"2133\",\"configKey\":\"321321\",\"createBy\":\"admin\",\"remark\":\"312312\",\"configType\":\"Y\",\"configValue\":\"312321312\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-12-23 18:59:42'),(2,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/6','127.0.0.1','内网IP','{configIds=6}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:01:17'),(3,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/7','127.0.0.1','内网IP','{configIds=7}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:01:31'),(4,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/7','127.0.0.1','内网IP','{configIds=7}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:10:45'),(5,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/7','127.0.0.1','内网IP','{configIds=7}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:11:59'),(6,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/6','127.0.0.1','内网IP','{configIds=6}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:13:57'),(7,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/6','127.0.0.1','内网IP','{configIds=6}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:16:30'),(8,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/6','127.0.0.1','内网IP','{configIds=6}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:22:30'),(10,'参数管理',1,'xin.altitude.cms.system.controller.SysConfigController.add()','POST',1,'admin',NULL,'/system/config','127.0.0.1','内网IP','{\"configName\":\"43214123421342\",\"configKey\":\"42314123412341234\",\"createBy\":\"admin\",\"configType\":\"Y\",\"configValue\":\"42314234123412341234\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-12-23 19:30:53'),(11,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/8','127.0.0.1','内网IP','{configIds=8}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:30:58'),(12,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/8','127.0.0.1','内网IP','{configIds=8}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-23 19:32:44'),(13,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/8','127.0.0.1','内网IP','{configIds=8}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-24 11:00:38'),(14,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/8','127.0.0.1','内网IP','{configIds=8}',NULL,1,'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById','2021-12-24 11:01:38'),(16,'参数管理',2,'xin.altitude.cms.system.controller.SysConfigController.edit()','PUT',1,'admin',NULL,'/system/config','127.0.0.1','内网IP','{\"configName\":\"43214123421342\",\"configKey\":\"42314123412341234\",\"createBy\":\"admin\",\"updateBy\":\"admin\",\"configId\":8,\"configType\":\"N\",\"configValue\":\"42314234123412341234\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-12-24 11:07:00'),(17,'参数管理',3,'xin.altitude.cms.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/system/config/8','127.0.0.1','内网IP','{configIds=8}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-12-24 11:07:05'),(18,'操作日志',3,'xin.altitude.cms.monitor.SysOperlogController.remove()','DELETE',1,'admin','','/monitor/operlog/9','127.0.0.1','内网IP','{operIds=9}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(19,'操作日志',3,'xin.altitude.cms.monitor.SysOperlogController.remove()','DELETE',1,'admin','','/monitor/operlog/15','127.0.0.1','内网IP','{operIds=15}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(20,'操作日志',5,'xin.altitude.cms.monitor.SysOperlogController.export()','POST',1,'admin','','/monitor/operlog/export','127.0.0.1','内网IP','{\"params\":{}}','',0,'',NULL),(21,'岗位管理',1,'xin.altitude.cms.system.controller.SysPostController.add()','POST',1,'admin','','/system/post','127.0.0.1','内网IP','{\"postSort\":\"4\",\"flag\":false,\"remark\":\"12312312\",\"postId\":5,\"params\":{},\"createBy\":\"admin\",\"postName\":\"13212312312\",\"postCode\":\"311231231231231\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(22,'岗位管理',2,'xin.altitude.cms.system.controller.SysPostController.edit()','PUT',1,'admin','','/system/post','127.0.0.1','内网IP','{\"postSort\":\"4\",\"flag\":false,\"remark\":\"12312312312312312312\",\"postId\":5,\"params\":{},\"createBy\":\"admin\",\"updateBy\":\"admin\",\"postName\":\"13212312312\",\"postCode\":\"311231231231231\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(23,'岗位管理',3,'xin.altitude.cms.system.controller.SysPostController.remove()','DELETE',1,'admin','','/system/post/5','127.0.0.1','内网IP','{postIds=5}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(24,'角色管理',2,'xin.altitude.cms.common.SysRoleController.edit()','PUT',1,'admin','','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1637546274000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1057,1058,1059,1060,116],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(25,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}','',1,'',NULL),(26,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}','',1,'',NULL),(27,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(28,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":108,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(29,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":109,\"avatar\":\"\",\"dept\":{\"deptName\":\"市场部门\",\"leader\":\"若依\",\"deptId\":108,\"orderNum\":\"1\",\"params\":{},\"parentId\":102,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(30,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"avatar\":\"\",\"dept\":{\"deptName\":\"财务部门\",\"leader\":\"若依\",\"deptId\":109,\"orderNum\":\"2\",\"params\":{},\"parentId\":102,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(31,'用户管理',1,'xin.altitude.cms.system.controller.SysUserController.add()','POST',1,'admin','','/system/user','127.0.0.1','内网IP','{\"admin\":false,\"password\":\"$2a$10$/NiiCfVRKTr0ZY8gBRDa8OmeqlQpOnQKDT/18NbkMpbfZ3ACBm3VW\",\"postIds\":[],\"nickName\":\"321\",\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(32,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"321\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"\",\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(33,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[1],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"321\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"\",\"dept\":{\"deptName\":\"若依科技\",\"leader\":\"若依\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"phone\":\"15888888888\",\"updateBy\":\"\",\"ancestors\":\"0\",\"email\":\"ry@qq.com\",\"status\":\"0\"},\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[2],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(34,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"phonenumber\":\"18511112222\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[1],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"321\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"\",\"dept\":{\"deptName\":\"若依科技\",\"leader\":\"若依\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"phone\":\"15888888888\",\"updateBy\":\"\",\"ancestors\":\"0\",\"email\":\"ry@qq.com\",\"status\":\"0\"},\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[2],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(35,'菜单管理',1,'xin.altitude.cms.system.controller.SysMenuController.add()','POST',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"orderNum\":\"6\",\"menuName\":\"AAAAA\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"/aaaaa\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(36,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"6\",\"menuName\":\"AAAAA\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"/aaaaa\",\"children\":[],\"createTime\":1640589239000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(37,'菜单管理',3,'xin.altitude.cms.system.controller.SysMenuController.remove()','DELETE',1,'admin','','/system/menu/1061','127.0.0.1','内网IP','{menuId=1061}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(38,'通知公告',1,'xin.altitude.cms.system.controller.SysNoticeController.add()','POST',1,'admin','','/system/notice','127.0.0.1','内网IP','{\"noticeContent\":\"<h1>3123123</h1><h2>3213123123213</h2><h5>31231313</h5><p><br></p><p><br></p><p><br></p><p>312312312das大三大四的</p>\",\"createBy\":\"admin\",\"noticeType\":\"1\",\"params\":{},\"noticeId\":3,\"noticeTitle\":\"123123123\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(39,'角色管理',1,'xin.altitude.cms.common.SysRoleController.add()','POST',1,'admin','','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":3,\"admin\":false,\"params\":{},\"roleSort\":\"3\",\"deptCheckStrictly\":true,\"createBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"31231231313123131231312\",\"roleName\":\"32112312312312\",\"deptIds\":[],\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(40,'角色管理',4,'xin.altitude.cms.common.SysRoleController.selectAuthUserAll()','PUT',1,'admin','','/system/role/authUser/selectAll','127.0.0.1','内网IP','3 [3]','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(41,'用户管理',2,'xin.altitude.cms.system.controller.SysUserController.edit()','PUT',1,'admin','','/system/user','127.0.0.1','内网IP','{\"phonenumber\":\"18511112222\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[1],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"321\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"\",\"dept\":{\"deptName\":\"若依科技\",\"leader\":\"若依\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"phone\":\"15888888888\",\"updateBy\":\"\",\"ancestors\":\"0\",\"email\":\"ry@qq.com\",\"status\":\"0\"},\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[3],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(42,'字典类型',9,'xin.altitude.cms.system.controller.SysDictTypeController.refreshCache()','DELETE',1,'admin','','/system/dict/type/refreshCache','127.0.0.1','内网IP','{}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(43,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}','',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',NULL),(44,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}','',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',NULL),(45,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}','',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',NULL),(46,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}','',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',NULL),(47,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}','',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',NULL),(48,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(49,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"logininfor\",\"orderNum\":\"10\",\"menuName\":\"登录日志\",\"remark\":\"登录日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"logininfor\",\"component\":\"monitor/logininfor/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":501,\"menuType\":\"C\",\"perms\":\"monitor:logininfor:list\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(50,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"1\",\"query\":\"\",\"icon\":\"log\",\"orderNum\":\"9\",\"menuName\":\"日志管理\",\"remark\":\"日志管理菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"log\",\"component\":\"\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":108,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(51,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"online\",\"orderNum\":\"11\",\"menuName\":\"在线用户\",\"remark\":\"在线用户菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"online\",\"component\":\"monitor/online/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":109,\"menuType\":\"C\",\"perms\":\"monitor:online:list\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(52,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuController.edit()','PUT',1,'admin','','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"server\",\"orderNum\":\"12\",\"menuName\":\"服务监控\",\"remark\":\"服务监控菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"server\",\"component\":\"monitor/server/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":112,\"menuType\":\"C\",\"perms\":\"monitor:server:list\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(53,'字典类型',5,'xin.altitude.cms.system.controller.SysDictTypeController.export()','POST',1,'admin','','/system/dict/type/export','127.0.0.1','内网IP','{\"params\":{}}','',0,'',NULL),(54,'字典类型',1,'xin.altitude.cms.system.controller.SysDictTypeController.add()','POST',1,'admin','','/system/dict/type','127.0.0.1','内网IP','{\"createBy\":\"admin\",\"dictName\":\"DD\",\"remark\":\"DD\",\"dictId\":11,\"params\":{},\"dictType\":\"DD\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,'',NULL),(55,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"code\",\"orderNum\":\"3\",\"menuName\":\"代码生成\",\"remark\":\"代码生成菜单\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"gen\",\"component\":\"tool/gen/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":115,\"menuType\":\"C\",\"perms\":\"tool:gen:list\",\"status\":\"0\"}','{\"code\":200,\"msg\":\"操作成功\"}',0,'',NULL),(56,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"monitor\",\"orderNum\":\"6\",\"menuName\":\"系统监控\",\"remark\":\"系统监控目录\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"monitor\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"code\":200,\"msg\":\"操作成功\"}',0,'',NULL),(57,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"tool\",\"orderNum\":\"7\",\"menuName\":\"系统工具\",\"remark\":\"系统工具目录\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"tool\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"code\":200,\"msg\":\"操作成功\"}',0,'',NULL),(58,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"swagger\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}','{\"code\":200,\"msg\":\"操作成功\"}',0,'',NULL),(59,'菜单管理',3,'xin.altitude.cms.system.controller.SysMenuProController.remove()','DELETE',1,'admin','','/cms-api/system/menu/1055','192.168.181.162','内网IP','{menuId=1055}','{\"code\":500,\"msg\":\"菜单已分配,不允许删除\"}',0,'',NULL),(60,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}','{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}',0,'',NULL),(61,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}','{\"code\":200,\"msg\":\"操作成功\"}',0,'',NULL),(62,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}','{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}',0,'',NULL),(63,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}','{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}',0,'',NULL),(64,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}','{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}',0,'',NULL),(65,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}','{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}',0,'',NULL),(66,'菜单管理',2,'xin.altitude.cms.system.controller.SysMenuProController.edit()','PUT',1,'admin','','/cms-api/system/menu','192.168.181.162','内网IP','{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"http://192.168.181.162:8080/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}','{\"code\":200,\"msg\":\"操作成功\"}',0,'',NULL);
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'ceo','董事长',1,'0','admin','2021-11-22 09:57:54','',NULL,''),(2,'se','项目经理',2,'0','admin','2021-11-22 09:57:54','',NULL,''),(3,'hr','人力资源',3,'0','admin','2021-11-22 09:57:54','',NULL,''),(4,'user','普通员工',4,'0','admin','2021-11-22 09:57:54','',NULL,'');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2021-11-22 09:57:54','',NULL,'超级管理员'),(2,'普通角色','common',2,'2',1,1,'0','0','admin','2021-11-22 09:57:54','admin','2021-12-26 16:13:12','普通角色'),(3,'32112312312312','31231231313123131231312',3,'1',1,1,'0','0','admin','2021-12-28 11:22:33','',NULL,NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`dept_id`),
  KEY `dept_id` (`dept_id`),
  CONSTRAINT `sys_role_dept_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_dept_ibfk_2` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (1,2,100),(2,2,101),(3,2,105);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`menu_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (85,2,2),(100,2,3),(86,2,109),(90,2,110),(97,2,111),(98,2,112),(99,2,113),(101,2,114),(102,2,115),(109,2,116),(87,2,1046),(88,2,1047),(89,2,1048),(91,2,1049),(92,2,1050),(93,2,1051),(94,2,1052),(95,2,1053),(96,2,1054),(110,3,1),(111,3,100),(119,3,101),(125,3,102),(130,3,103),(135,3,104),(141,3,105),(147,3,106),(153,3,107),(158,3,108),(159,3,500),(163,3,501),(112,3,1001),(113,3,1002),(114,3,1003),(115,3,1004),(116,3,1005),(117,3,1006),(118,3,1007),(120,3,1008),(121,3,1009),(122,3,1010),(123,3,1011),(124,3,1012),(126,3,1013),(127,3,1014),(128,3,1015),(129,3,1016),(131,3,1017),(132,3,1018),(133,3,1019),(134,3,1020),(136,3,1021),(137,3,1022),(138,3,1023),(139,3,1024),(140,3,1025),(142,3,1026),(143,3,1027),(144,3,1028),(145,3,1029),(146,3,1030),(148,3,1031),(149,3,1032),(150,3,1033),(151,3,1034),(152,3,1035),(154,3,1036),(155,3,1037),(156,3,1038),(157,3,1039),(160,3,1040),(161,3,1041),(162,3,1042),(164,3,1043),(165,3,1044),(166,3,1045);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`),
  KEY `dept_id` (`dept_id`),
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,103,'admin','若依','00','ry@163.com','15888888888','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','192.168.181.162','2022-01-05 14:51:53','admin','2021-11-22 09:57:54','','2022-01-05 14:51:53','管理员'),(2,109,'ry','若依','00','ry@qq.com','15666666666','0','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2021-11-22 09:57:54','admin','2021-11-22 09:57:54','admin','2021-12-26 23:35:50','测试员'),(3,100,'32131233','321','00','','18511112222','0','','','0','0','',NULL,'admin',NULL,'admin',NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`post_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `sys_user_post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_post_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `sys_post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1,1),(6,2,2),(9,3,1);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1,1),(6,2,2),(10,3,3);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-05 16:16:21
