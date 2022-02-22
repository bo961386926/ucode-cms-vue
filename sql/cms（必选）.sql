/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : ucode-cms

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 18/01/2022 10:20:58
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `config_id`    int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `config_name`  varchar(100) DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
    `config_type`  char(1)      DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_by`    varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`  datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`  datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`       varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config`
VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2021-11-22 09:57:56', '', NULL,
        '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config`
VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2021-11-22 09:57:56', '', NULL,
        '初始化密码 123456');
INSERT INTO `sys_config`
VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2021-11-22 09:57:56', '', NULL,
        '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config`
VALUES (4, '账号自助-验证码开关', 'sys.account.captchaOnOff', 'true', 'Y', 'admin', '2021-11-22 09:57:56', '', NULL,
        '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config`
VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2021-11-22 09:57:56', '', NULL,
        '是否开启注册用户功能（true开启，false关闭）');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `dept_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `parent_id`   bigint(20) DEFAULT '0' COMMENT '父部门id',
    `ancestors`   varchar(50) DEFAULT '' COMMENT '祖级列表',
    `dept_name`   varchar(30) DEFAULT '' COMMENT '部门名称',
    `order_num`   int(4) DEFAULT '0' COMMENT '显示顺序',
    `leader`      varchar(20) DEFAULT NULL COMMENT '负责人',
    `phone`       varchar(11) DEFAULT NULL COMMENT '联系电话',
    `email`       varchar(50) DEFAULT NULL COMMENT '邮箱',
    `status`      char(1)     DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
    `del_flag`    char(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept`
VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54', '', NULL);
INSERT INTO `sys_dept`
VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54', '',
        NULL);
INSERT INTO `sys_dept`
VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54', '',
        NULL);
INSERT INTO `sys_dept`
VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `dict_code`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_sort`   int(4) DEFAULT '0' COMMENT '字典排序',
    `dict_label`  varchar(100) DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100) DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) DEFAULT '' COMMENT '字典类型',
    `css_class`   varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class`  varchar(100) DEFAULT NULL COMMENT '表格回显样式',
    `is_default`  char(1)      DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status`      char(1)      DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data`
VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '性别男');
INSERT INTO `sys_dict_data`
VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '性别女');
INSERT INTO `sys_dict_data`
VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '性别未知');
INSERT INTO `sys_dict_data`
VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data`
VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data`
VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2021-11-22 09:57:56', '', NULL,
        '正常状态');
INSERT INTO `sys_dict_data`
VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL,
        '停用状态');
INSERT INTO `sys_dict_data`
VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '正常状态');
INSERT INTO `sys_dict_data`
VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '停用状态');
INSERT INTO `sys_dict_data`
VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '默认分组');
INSERT INTO `sys_dict_data`
VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '系统分组');
INSERT INTO `sys_dict_data`
VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data`
VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data`
VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '通知');
INSERT INTO `sys_dict_data`
VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '公告');
INSERT INTO `sys_dict_data`
VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2021-11-22 09:57:56', '', NULL,
        '正常状态');
INSERT INTO `sys_dict_data`
VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL,
        '关闭状态');
INSERT INTO `sys_dict_data`
VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '新增操作');
INSERT INTO `sys_dict_data`
VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '修改操作');
INSERT INTO `sys_dict_data`
VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '删除操作');
INSERT INTO `sys_dict_data`
VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '授权操作');
INSERT INTO `sys_dict_data`
VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '导出操作');
INSERT INTO `sys_dict_data`
VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '导入操作');
INSERT INTO `sys_dict_data`
VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '强退操作');
INSERT INTO `sys_dict_data`
VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '生成操作');
INSERT INTO `sys_dict_data`
VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '清空操作');
INSERT INTO `sys_dict_data`
VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL,
        '正常状态');
INSERT INTO `sys_dict_data`
VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2021-11-22 09:57:56', '', NULL,
        '停用状态');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `dict_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name`   varchar(100) DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) DEFAULT '' COMMENT '字典类型',
    `status`      char(1)      DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_id`),
    UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type`
VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type`
VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type`
VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type`
VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type`
VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type`
VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type`
VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type`
VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type`
VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type`
VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2021-11-22 09:57:56', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type`
VALUES (11, 'DD', 'DD', '0', 'admin', '2021-12-28 16:52:27', '', NULL, 'DD');
COMMIT;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`
(
    `info_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `user_name`      varchar(50)  DEFAULT '' COMMENT '用户账号',
    `ipaddr`         varchar(128) DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50)  DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50)  DEFAULT '' COMMENT '操作系统',
    `status`         char(1)      DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255) DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime     DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
BEGIN;
INSERT INTO `sys_logininfor`
VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-11-22 10:54:31');
INSERT INTO `sys_logininfor`
VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-11-22 10:54:35');
INSERT INTO `sys_logininfor`
VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-11-22 10:54:42');
INSERT INTO `sys_logininfor`
VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-11-22 13:41:32');
INSERT INTO `sys_logininfor`
VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-11-22 14:46:55');
INSERT INTO `sys_logininfor`
VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-11-22 14:47:02');
INSERT INTO `sys_logininfor`
VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-22 14:02:28');
INSERT INTO `sys_logininfor`
VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-22 15:13:41');
INSERT INTO `sys_logininfor`
VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-22 16:18:19');
INSERT INTO `sys_logininfor`
VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-12-22 16:43:14');
INSERT INTO `sys_logininfor`
VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-22 16:43:21');
INSERT INTO `sys_logininfor`
VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-12-22 16:43:25');
INSERT INTO `sys_logininfor`
VALUES (112, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-22 16:43:29');
INSERT INTO `sys_logininfor`
VALUES (113, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-22 16:43:36');
INSERT INTO `sys_logininfor`
VALUES (114, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-23 15:42:07');
INSERT INTO `sys_logininfor`
VALUES (115, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-23 16:43:58');
INSERT INTO `sys_logininfor`
VALUES (116, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-23 18:59:22');
INSERT INTO `sys_logininfor`
VALUES (117, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-23 18:59:27');
INSERT INTO `sys_logininfor`
VALUES (118, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-24 11:00:19');
INSERT INTO `sys_logininfor`
VALUES (119, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-24 11:00:28');
INSERT INTO `sys_logininfor`
VALUES (120, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-24 13:39:34');
INSERT INTO `sys_logininfor`
VALUES (121, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2021-12-24 18:03:21');
INSERT INTO `sys_logininfor`
VALUES (122, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-24 18:03:26');
INSERT INTO `sys_logininfor`
VALUES (123, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2021-12-26 14:04:20');
INSERT INTO `sys_logininfor`
VALUES (124, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2021-12-26 14:04:22');
INSERT INTO `sys_logininfor`
VALUES (125, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-26 14:04:26');
INSERT INTO `sys_logininfor`
VALUES (126, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-26 14:37:24');
INSERT INTO `sys_logininfor`
VALUES (127, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-26 15:36:25');
INSERT INTO `sys_logininfor`
VALUES (128, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-26 22:50:57');
INSERT INTO `sys_logininfor`
VALUES (129, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-26 22:51:05');
INSERT INTO `sys_logininfor`
VALUES (130, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-27 10:06:56');
INSERT INTO `sys_logininfor`
VALUES (131, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '1', '验证码错误', '2021-12-27 10:37:49');
INSERT INTO `sys_logininfor`
VALUES (132, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '1', '验证码错误', '2021-12-27 10:37:50');
INSERT INTO `sys_logininfor`
VALUES (133, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '1', '验证码错误', '2021-12-27 10:37:50');
INSERT INTO `sys_logininfor`
VALUES (134, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '1', '验证码错误', '2021-12-27 10:37:50');
INSERT INTO `sys_logininfor`
VALUES (135, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '1', '验证码错误', '2021-12-27 10:37:50');
INSERT INTO `sys_logininfor`
VALUES (136, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '1', '验证码错误', '2021-12-27 10:37:51');
INSERT INTO `sys_logininfor`
VALUES (137, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '1', '验证码错误', '2021-12-27 10:37:51');
INSERT INTO `sys_logininfor`
VALUES (138, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '1', '验证码错误', '2021-12-27 10:37:51');
INSERT INTO `sys_logininfor`
VALUES (139, 'admin', '192.168.181.100', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2021-12-27 10:37:51');
INSERT INTO `sys_logininfor`
VALUES (140, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-27 11:00:49');
INSERT INTO `sys_logininfor`
VALUES (141, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-27 11:00:49');
INSERT INTO `sys_logininfor`
VALUES (142, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-27 11:00:49');
INSERT INTO `sys_logininfor`
VALUES (143, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-27 13:39:46');
INSERT INTO `sys_logininfor`
VALUES (144, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-27 13:39:46');
INSERT INTO `sys_logininfor`
VALUES (145, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-27 15:13:18');
INSERT INTO `sys_logininfor`
VALUES (146, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-27 15:13:18');
INSERT INTO `sys_logininfor`
VALUES (147, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-27 15:13:18');
INSERT INTO `sys_logininfor`
VALUES (148, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-27 15:13:18');
INSERT INTO `sys_logininfor`
VALUES (149, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 10:05:32');
INSERT INTO `sys_logininfor`
VALUES (150, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 10:05:32');
INSERT INTO `sys_logininfor`
VALUES (151, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 10:05:33');
INSERT INTO `sys_logininfor`
VALUES (152, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 10:05:33');
INSERT INTO `sys_logininfor`
VALUES (153, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 10:05:33');
INSERT INTO `sys_logininfor`
VALUES (154, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-28 10:05:34');
INSERT INTO `sys_logininfor`
VALUES (155, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-28 11:21:31');
INSERT INTO `sys_logininfor`
VALUES (156, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 13:06:28');
INSERT INTO `sys_logininfor`
VALUES (157, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 13:06:28');
INSERT INTO `sys_logininfor`
VALUES (158, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 13:06:28');
INSERT INTO `sys_logininfor`
VALUES (159, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 13:06:28');
INSERT INTO `sys_logininfor`
VALUES (160, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 13:06:28');
INSERT INTO `sys_logininfor`
VALUES (161, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-12-28 13:06:29');
INSERT INTO `sys_logininfor`
VALUES (162, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-12-28 13:06:29');
INSERT INTO `sys_logininfor`
VALUES (163, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (164, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (165, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (166, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (167, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (168, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (169, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (170, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (171, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (172, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (173, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (174, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (175, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (176, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (177, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (178, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (179, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (180, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (181, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (182, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (183, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (184, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (185, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (186, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (187, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (188, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (189, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (190, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (191, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (192, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (193, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (194, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (195, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (196, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (197, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (198, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (199, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (200, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (201, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (202, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (203, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (204, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (205, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (206, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (207, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (208, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (209, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (210, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (211, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (212, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (213, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (214, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (215, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (216, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (217, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (218, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (219, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (220, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (221, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (222, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (223, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (224, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (225, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (226, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (227, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (228, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (229, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (230, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (231, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (232, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (233, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (234, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (235, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (236, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (237, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (238, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (239, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (240, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (241, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (242, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (243, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', NULL);
INSERT INTO `sys_logininfor`
VALUES (244, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', NULL);
INSERT INTO `sys_logininfor`
VALUES (245, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', NULL);
INSERT INTO `sys_logininfor`
VALUES (246, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', NULL);
INSERT INTO `sys_logininfor`
VALUES (247, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', NULL);
INSERT INTO `sys_logininfor`
VALUES (248, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', NULL);
INSERT INTO `sys_logininfor`
VALUES (249, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (250, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (251, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (252, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (253, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', NULL);
INSERT INTO `sys_logininfor`
VALUES (254, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (255, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (256, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', NULL);
INSERT INTO `sys_logininfor`
VALUES (257, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (258, 'admin', '192.168.181.161', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (259, 'admin', '192.168.181.161', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (260, 'admin', '192.168.181.162', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (261, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (262, 'admin', '192.168.181.162', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (263, 'admin', '192.168.181.162', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (264, 'admin', '192.168.181.162', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (265, 'admin', '192.168.181.162', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (266, 'admin', '192.168.181.162', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (267, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (268, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (269, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (270, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (271, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (272, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (273, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (274, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (275, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (276, 'admin', '192.168.181.164', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (277, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (278, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (279, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor`
VALUES (280, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name`   varchar(50) NOT NULL COMMENT '菜单名称',
    `parent_id`   bigint(20) DEFAULT '0' COMMENT '父菜单ID',
    `order_num`   int(4) DEFAULT '0' COMMENT '显示顺序',
    `path`        varchar(200) DEFAULT '' COMMENT '路由地址',
    `component`   varchar(255) DEFAULT NULL COMMENT '组件路径',
    `query`       varchar(255) DEFAULT NULL COMMENT '路由参数',
    `is_frame`    int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
    `is_cache`    int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type`   char(1)      DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible`     char(1)      DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `status`      char(1)      DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(100) DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) DEFAULT '#' COMMENT '菜单图标',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu`
VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2021-11-22 09:57:54', '',
        NULL, '系统管理目录');
INSERT INTO `sys_menu`
VALUES (2, '系统监控', 0, 6, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2021-11-22 09:57:54',
        'admin', '2022-01-05 15:40:59', '系统监控目录');
INSERT INTO `sys_menu`
VALUES (3, '系统工具', 0, 7, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2021-11-22 09:57:54', 'admin',
        '2022-01-05 15:41:05', '系统工具目录');
INSERT INTO `sys_menu`
VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin',
        '2021-11-22 09:57:54', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu`
VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin',
        '2021-11-22 09:57:54', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu`
VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table',
        'admin', '2021-11-22 09:57:54', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu`
VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin',
        '2021-11-22 09:57:54', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu`
VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin',
        '2021-11-22 09:57:54', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu`
VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin',
        '2021-11-22 09:57:54', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu`
VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit',
        'admin', '2021-11-22 09:57:54', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu`
VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message',
        'admin', '2021-11-22 09:57:54', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu`
VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '1', '0', '', 'log', 'admin', '2021-11-22 09:57:54', 'admin',
        '2021-12-28 13:21:19', '日志管理菜单');
INSERT INTO `sys_menu`
VALUES (109, '在线用户', 1, 11, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online',
        'admin', '2021-11-22 09:57:54', 'admin', '2021-12-28 13:22:30', '在线用户菜单');
INSERT INTO `sys_menu`
VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin',
        '2021-11-22 09:57:54', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu`
VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid',
        'admin', '2021-11-22 09:57:54', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu`
VALUES (112, '服务监控', 1, 12, 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server',
        'admin', '2021-11-22 09:57:54', 'admin', '2021-12-28 13:23:01', '服务监控菜单');
INSERT INTO `sys_menu`
VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis',
        'admin', '2021-11-22 09:57:54', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu`
VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin',
        '2021-11-22 09:57:54', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu`
VALUES (115, '代码生成', 0, 3, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin',
        '2021-11-22 09:57:54', 'admin', '2022-01-05 15:40:35', '代码生成菜单');
INSERT INTO `sys_menu`
VALUES (116, '系统接口', 0, 4, 'http://192.168.181.162:8080/doc.html', 'tool/swagger/index', '', 0, 1, 'C', '0', '0',
        'tool:swagger:list', 'swagger', 'admin', '2021-11-22 09:57:54', 'admin', '2022-01-05 15:42:00', '系统接口菜单');
INSERT INTO `sys_menu`
VALUES (500, '操作日志', 1, 9, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form',
        'admin', '2021-11-22 09:57:54', 'admin', '2021-12-28 13:20:26', '操作日志菜单');
INSERT INTO `sys_menu`
VALUES (501, '登录日志', 1, 10, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0',
        'monitor:logininfor:list', 'logininfor', 'admin', '2021-11-22 09:57:54', 'admin', '2021-12-28 13:21:10',
        '登录日志菜单');
COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `notice_id`      int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `notice_title`   varchar(50) NOT NULL COMMENT '公告标题',
    `notice_type`    char(1)     NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` longblob COMMENT '公告内容',
    `status`         char(1)      DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_by`      varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`      varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`    datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`         varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
BEGIN;
INSERT INTO `sys_notice`
VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2021-11-22 09:57:56', '',
        NULL, '管理员');
INSERT INTO `sys_notice`
VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2021-11-22 09:57:56', '', NULL,
        '管理员');
INSERT INTO `sys_notice`
VALUES (3, '123123123', '1',
        0x3C68313E333132333132333C2F68313E3C68323E333231333132333132333231333C2F68323E3C68353E33313233313331333C2F68353E3C703E3C62723E3C2F703E3C703E3C62723E3C2F703E3C703E3C62723E3C2F703E3C703E333132333132333132646173E5A4A7E4B889E5A4A7E59B9BE79A843C2F703E,
        '0', 'admin', '2021-12-28 10:06:29', '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `oper_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`          varchar(50)   DEFAULT '' COMMENT '模块标题',
    `business_type`  int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100)  DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10)   DEFAULT '' COMMENT '请求方式',
    `operator_type`  int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50)   DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50)   DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255)  DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128)  DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255)  DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) DEFAULT '' COMMENT '返回参数',
    `status`         int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime      DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_oper_log`
VALUES (1, '参数管理', 1, 'xin.altitude.cms.system.controller.SysConfigController.add()', 'POST', 1, 'admin', NULL,
        '/system/config', '127.0.0.1', '内网IP',
        '{\"configName\":\"2133\",\"configKey\":\"321321\",\"createBy\":\"admin\",\"remark\":\"312312\",\"configType\":\"Y\",\"configValue\":\"312321312\",\"params\":{}}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-12-23 18:59:42');
INSERT INTO `sys_oper_log`
VALUES (2, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/6', '127.0.0.1', '内网IP', '{configIds=6}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:01:17');
INSERT INTO `sys_oper_log`
VALUES (3, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/7', '127.0.0.1', '内网IP', '{configIds=7}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:01:31');
INSERT INTO `sys_oper_log`
VALUES (4, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/7', '127.0.0.1', '内网IP', '{configIds=7}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:10:45');
INSERT INTO `sys_oper_log`
VALUES (5, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/7', '127.0.0.1', '内网IP', '{configIds=7}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:11:59');
INSERT INTO `sys_oper_log`
VALUES (6, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/6', '127.0.0.1', '内网IP', '{configIds=6}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:13:57');
INSERT INTO `sys_oper_log`
VALUES (7, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/6', '127.0.0.1', '内网IP', '{configIds=6}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:16:30');
INSERT INTO `sys_oper_log`
VALUES (8, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/6', '127.0.0.1', '内网IP', '{configIds=6}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:22:30');
INSERT INTO `sys_oper_log`
VALUES (10, '参数管理', 1, 'xin.altitude.cms.system.controller.SysConfigController.add()', 'POST', 1, 'admin', NULL,
        '/system/config', '127.0.0.1', '内网IP',
        '{\"configName\":\"43214123421342\",\"configKey\":\"42314123412341234\",\"createBy\":\"admin\",\"configType\":\"Y\",\"configValue\":\"42314234123412341234\",\"params\":{}}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-12-23 19:30:53');
INSERT INTO `sys_oper_log`
VALUES (11, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/8', '127.0.0.1', '内网IP', '{configIds=8}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:30:58');
INSERT INTO `sys_oper_log`
VALUES (12, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/8', '127.0.0.1', '内网IP', '{configIds=8}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-23 19:32:44');
INSERT INTO `sys_oper_log`
VALUES (13, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/8', '127.0.0.1', '内网IP', '{configIds=8}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-24 11:00:38');
INSERT INTO `sys_oper_log`
VALUES (14, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/8', '127.0.0.1', '内网IP', '{configIds=8}', NULL, 1,
        'Invalid bound statement (not found): xin.altitude.cms.system.mapper.SysConfigMapper.selectById',
        '2021-12-24 11:01:38');
INSERT INTO `sys_oper_log`
VALUES (16, '参数管理', 2, 'xin.altitude.cms.system.controller.SysConfigController.edit()', 'PUT', 1, 'admin', NULL,
        '/system/config', '127.0.0.1', '内网IP',
        '{\"configName\":\"43214123421342\",\"configKey\":\"42314123412341234\",\"createBy\":\"admin\",\"updateBy\":\"admin\",\"configId\":8,\"configType\":\"N\",\"configValue\":\"42314234123412341234\",\"params\":{}}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-12-24 11:07:00');
INSERT INTO `sys_oper_log`
VALUES (17, '参数管理', 3, 'xin.altitude.cms.system.controller.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL,
        '/system/config/8', '127.0.0.1', '内网IP', '{configIds=8}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL,
        '2021-12-24 11:07:05');
INSERT INTO `sys_oper_log`
VALUES (18, '操作日志', 3, 'xin.altitude.cms.monitor.SysOperlogController.remove()', 'DELETE', 1, 'admin', '',
        '/monitor/operlog/9', '127.0.0.1', '内网IP', '{operIds=9}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (19, '操作日志', 3, 'xin.altitude.cms.monitor.SysOperlogController.remove()', 'DELETE', 1, 'admin', '',
        '/monitor/operlog/15', '127.0.0.1', '内网IP', '{operIds=15}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (20, '操作日志', 5, 'xin.altitude.cms.monitor.SysOperlogController.export()', 'POST', 1, 'admin', '',
        '/monitor/operlog/export', '127.0.0.1', '内网IP', '{\"params\":{}}', '', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (21, '岗位管理', 1, 'xin.altitude.cms.system.controller.SysPostController.add()', 'POST', 1, 'admin', '',
        '/system/post', '127.0.0.1', '内网IP',
        '{\"postSort\":\"4\",\"flag\":false,\"remark\":\"12312312\",\"postId\":5,\"params\":{},\"createBy\":\"admin\",\"postName\":\"13212312312\",\"postCode\":\"311231231231231\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (22, '岗位管理', 2, 'xin.altitude.cms.system.controller.SysPostController.edit()', 'PUT', 1, 'admin', '',
        '/system/post', '127.0.0.1', '内网IP',
        '{\"postSort\":\"4\",\"flag\":false,\"remark\":\"12312312312312312312\",\"postId\":5,\"params\":{},\"createBy\":\"admin\",\"updateBy\":\"admin\",\"postName\":\"13212312312\",\"postCode\":\"311231231231231\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (23, '岗位管理', 3, 'xin.altitude.cms.system.controller.SysPostController.remove()', 'DELETE', 1, 'admin', '',
        '/system/post/5', '127.0.0.1', '内网IP', '{postIds=5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (24, '角色管理', 2, 'xin.altitude.cms.common.SysRoleController.edit()', 'PUT', 1, 'admin', '', '/system/role',
        '127.0.0.1', '内网IP',
        '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1637546274000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1057,1058,1059,1060,116],\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (25, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}',
        '', 1, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (26, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}',
        '', 1, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (27, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (28, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":108,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (29, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"deptId\":109,\"avatar\":\"\",\"dept\":{\"deptName\":\"市场部门\",\"leader\":\"若依\",\"deptId\":108,\"orderNum\":\"1\",\"params\":{},\"parentId\":102,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (30, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1637546274000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"0\",\"avatar\":\"\",\"dept\":{\"deptName\":\"财务部门\",\"leader\":\"若依\",\"deptId\":109,\"orderNum\":\"2\",\"params\":{},\"parentId\":102,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1637546274000,\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (31, '用户管理', 1, 'xin.altitude.cms.system.controller.SysUserController.add()', 'POST', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"admin\":false,\"password\":\"$2a$10$/NiiCfVRKTr0ZY8gBRDa8OmeqlQpOnQKDT/18NbkMpbfZ3ACBm3VW\",\"postIds\":[],\"nickName\":\"321\",\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[],\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (32, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"321\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"\",\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[],\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (33, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[1],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"321\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"\",\"dept\":{\"deptName\":\"若依科技\",\"leader\":\"若依\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"phone\":\"15888888888\",\"updateBy\":\"\",\"ancestors\":\"0\",\"email\":\"ry@qq.com\",\"status\":\"0\"},\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[2],\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (34, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"phonenumber\":\"18511112222\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[1],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"321\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"\",\"dept\":{\"deptName\":\"若依科技\",\"leader\":\"若依\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"phone\":\"15888888888\",\"updateBy\":\"\",\"ancestors\":\"0\",\"email\":\"ry@qq.com\",\"status\":\"0\"},\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[2],\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (35, '菜单管理', 1, 'xin.altitude.cms.system.controller.SysMenuController.add()', 'POST', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"orderNum\":\"6\",\"menuName\":\"AAAAA\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"/aaaaa\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (36, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"6\",\"menuName\":\"AAAAA\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"/aaaaa\",\"children\":[],\"createTime\":1640589239000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (37, '菜单管理', 3, 'xin.altitude.cms.system.controller.SysMenuController.remove()', 'DELETE', 1, 'admin', '',
        '/system/menu/1061', '127.0.0.1', '内网IP', '{menuId=1061}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (38, '通知公告', 1, 'xin.altitude.cms.system.controller.SysNoticeController.add()', 'POST', 1, 'admin', '',
        '/system/notice', '127.0.0.1', '内网IP',
        '{\"noticeContent\":\"<h1>3123123</h1><h2>3213123123213</h2><h5>31231313</h5><p><br></p><p><br></p><p><br></p><p>312312312das大三大四的</p>\",\"createBy\":\"admin\",\"noticeType\":\"1\",\"params\":{},\"noticeId\":3,\"noticeTitle\":\"123123123\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (39, '角色管理', 1, 'xin.altitude.cms.common.SysRoleController.add()', 'POST', 1, 'admin', '', '/system/role',
        '127.0.0.1', '内网IP',
        '{\"flag\":false,\"roleId\":3,\"admin\":false,\"params\":{},\"roleSort\":\"3\",\"deptCheckStrictly\":true,\"createBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"31231231313123131231312\",\"roleName\":\"32112312312312\",\"deptIds\":[],\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045],\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (40, '角色管理', 4, 'xin.altitude.cms.common.SysRoleController.selectAuthUserAll()', 'PUT', 1, 'admin', '',
        '/system/role/authUser/selectAll', '127.0.0.1', '内网IP', '3 [3]', '{\"msg\":\"操作成功\",\"code\":200}', 0, '',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (41, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserController.edit()', 'PUT', 1, 'admin', '',
        '/system/user', '127.0.0.1', '内网IP',
        '{\"phonenumber\":\"18511112222\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[1],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"321\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"\",\"dept\":{\"deptName\":\"若依科技\",\"leader\":\"若依\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"phone\":\"15888888888\",\"updateBy\":\"\",\"ancestors\":\"0\",\"email\":\"ry@qq.com\",\"status\":\"0\"},\"params\":{},\"userName\":\"32131233\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[3],\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (42, '字典类型', 9, 'xin.altitude.cms.system.controller.SysDictTypeController.refreshCache()', 'DELETE', 1, 'admin',
        '', '/system/dict/type/refreshCache', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (43, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}',
        '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (44, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}',
        '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (45, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}',
        '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (46, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}',
        '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (47, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}',
        '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?\n### The error may exist in xin/altitude/cms/system/mapper/SysMenuMapper.java (best guess)\n### The error may involve xin.altitude.cms.system.mapper.SysMenuMapper.updateById\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu  SET menu_name=?,\nparent_id=?,\norder_num=?,\npath=?,\ncomponent=?,\nquery=?,\nis_frame=?,\nis_cache=?,\nmenu_type=?,\nvisible=?,\nstatus=?,\nperms=?,\nicon=?,\ncreate_by=?,\ncreate_time=?,\nupdate_by=?,\n\nremark=?  WHERE menu_id=?',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (48, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"form\",\"orderNum\":\"9\",\"menuName\":\"操作日志\",\"remark\":\"操作日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"operlog\",\"component\":\"monitor/operlog/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":500,\"menuType\":\"C\",\"perms\":\"monitor:operlog:list\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (49, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"logininfor\",\"orderNum\":\"10\",\"menuName\":\"登录日志\",\"remark\":\"登录日志菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"logininfor\",\"component\":\"monitor/logininfor/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":501,\"menuType\":\"C\",\"perms\":\"monitor:logininfor:list\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (50, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"1\",\"query\":\"\",\"icon\":\"log\",\"orderNum\":\"9\",\"menuName\":\"日志管理\",\"remark\":\"日志管理菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"log\",\"component\":\"\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":108,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (51, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"online\",\"orderNum\":\"11\",\"menuName\":\"在线用户\",\"remark\":\"在线用户菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"online\",\"component\":\"monitor/online/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":109,\"menuType\":\"C\",\"perms\":\"monitor:online:list\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (52, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuController.edit()', 'PUT', 1, 'admin', '',
        '/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"server\",\"orderNum\":\"12\",\"menuName\":\"服务监控\",\"remark\":\"服务监控菜单\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"server\",\"component\":\"monitor/server/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":112,\"menuType\":\"C\",\"perms\":\"monitor:server:list\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (53, '字典类型', 5, 'xin.altitude.cms.system.controller.SysDictTypeController.export()', 'POST', 1, 'admin', '',
        '/system/dict/type/export', '127.0.0.1', '内网IP', '{\"params\":{}}', '', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (54, '字典类型', 1, 'xin.altitude.cms.system.controller.SysDictTypeController.add()', 'POST', 1, 'admin', '',
        '/system/dict/type', '127.0.0.1', '内网IP',
        '{\"createBy\":\"admin\",\"dictName\":\"DD\",\"remark\":\"DD\",\"dictId\":11,\"params\":{},\"dictType\":\"DD\",\"status\":\"0\"}',
        '{\"msg\":\"操作成功\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (55, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"code\",\"orderNum\":\"3\",\"menuName\":\"代码生成\",\"remark\":\"代码生成菜单\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"gen\",\"component\":\"tool/gen/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":115,\"menuType\":\"C\",\"perms\":\"tool:gen:list\",\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (56, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"monitor\",\"orderNum\":\"6\",\"menuName\":\"系统监控\",\"remark\":\"系统监控目录\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"monitor\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (57, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"tool\",\"orderNum\":\"7\",\"menuName\":\"系统工具\",\"remark\":\"系统工具目录\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"tool\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (58, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"swagger\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (59, '菜单管理', 3, 'xin.altitude.cms.system.controller.SysMenuProController.remove()', 'DELETE', 1, 'admin', '',
        '/cms-api/system/menu/1055', '192.168.181.162', '内网IP', '{menuId=1055}',
        '{\"code\":500,\"msg\":\"菜单已分配,不允许删除\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (60, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (61, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (62, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (63, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (64, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (65, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (66, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '192.168.181.162', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"http://192.168.181.162:8080/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (67, '角色管理', 5, 'xin.altitude.cms.system.controller.SysRoleProController.export()', 'POST', 1, 'admin', '',
        '/cms-api/system/role/export', '127.0.0.1', '内网IP',
        '{\"flag\":false,\"admin\":false,\"params\":{\"dataScope\":\"\"}}', '', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (68, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserProController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/system/user/changeStatus', '127.0.0.1', '内网IP',
        '{\"admin\":true,\"params\":{},\"userId\":1,\"status\":\"1\"}', '', 1, '不允许操作超级管理员用户', NULL);
INSERT INTO `sys_oper_log`
VALUES (69, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserProController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/system/user/changeStatus', '127.0.0.1', '内网IP',
        '{\"admin\":false,\"updateBy\":\"admin\",\"params\":{},\"userId\":2,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (70, '用户管理', 2, 'xin.altitude.cms.system.controller.SysUserProController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/system/user/changeStatus', '127.0.0.1', '内网IP',
        '{\"admin\":false,\"updateBy\":\"admin\",\"params\":{},\"userId\":2,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (71, '定时任务', 1, 'xin.altitude.cms.job.controller.SysJobController.add()', 'POST', 1, 'admin', '',
        '/cms-api/monitor/job', '127.0.0.1', '内网IP',
        '{\"cronExpression\":\"* * * * * ?\",\"jobName\":\"每日更新数据\",\"jobId\":4,\"nextValidTime\":1641971442000,\"invokeTarget\":\"ryTask.ryParams(\'ry\')\",\"concurrent\":\"1\",\"misfirePolicy\":\"3\",\"jobGroup\":\"DEFAULT\",\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (72, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"0\"}', '', 1,
        '\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1\n### The error may exist in xin/altitude/cms/job/mapper/SysJobMapper.java (best guess)\n### The error may involve xin.altitude.cms.job.mapper.SysJobMapper.updateById-Inline\n### The error occurred while setting parameters\n### SQL: UPDATE sys_job  SET job_name=?, job_group=?, invoke_target=?, cron_expression=?, misfire_policy=?, concurrent=?, status=?, create_time=?,  deleted=?  WHERE job_id=?\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1\n; Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (73, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"0\"}', '', 1,
        '\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1\n### The error may exist in xin/altitude/cms/job/mapper/SysJobMapper.java (best guess)\n### The error may involve xin.altitude.cms.job.mapper.SysJobMapper.updateById-Inline\n### The error occurred while setting parameters\n### SQL: UPDATE sys_job  SET job_name=?, job_group=?, invoke_target=?, cron_expression=?, misfire_policy=?, concurrent=?, status=?, create_time=?,  deleted=?  WHERE job_id=?\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1\n; Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (74, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (75, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (76, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job', '127.0.0.1', '内网IP',
        '{\"cronExpression\":\"* * * * * ?\",\"jobName\":\"系统默认（无参）\",\"jobId\":1,\"nextValidTime\":1641972207000,\"deleted\":false,\"createTime\":\"09:57:56\",\"invokeTarget\":\"ryTask.ryNoParams\",\"concurrent\":\"1\",\"misfirePolicy\":\"3\",\"updateTime\":\"15:18:25\",\"jobGroup\":\"DEFAULT\",\"status\":\"1\"}',
        '', 1,
        '\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1\n### The error may exist in xin/altitude/cms/job/mapper/SysJobMapper.java (best guess)\n### The error may involve xin.altitude.cms.job.mapper.SysJobMapper.updateById-Inline\n### The error occurred while setting parameters\n### SQL: UPDATE sys_job  SET job_name=?, job_group=?, invoke_target=?, cron_expression=?, misfire_policy=?, concurrent=?, status=?, create_time=?, update_time=?, deleted=?  WHERE job_id=?\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1\n; Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: \'09:57:56\' for column \'create_time\' at row 1',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (77, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job', '127.0.0.1', '内网IP',
        '{\"cronExpression\":\"* * * * * ?\",\"jobName\":\"系统默认（无参）\",\"jobId\":1,\"nextValidTime\":1641973032000,\"deleted\":false,\"createTime\":\"2021-11-22T09:57:56\",\"invokeTarget\":\"ryTask.ryNoParams\",\"concurrent\":\"1\",\"misfirePolicy\":\"3\",\"updateTime\":\"2022-01-12T15:18:25\",\"jobGroup\":\"DEFAULT\",\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (78, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (79, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (80, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (81, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (82, '调度日志', 9, 'xin.altitude.cms.job.controller.SysJobLogController.clean()', 'DELETE', 1, 'admin', '',
        '/cms-api/monitor/jobLog/clean', '127.0.0.1', '内网IP', '{}', '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of full table deletion\n### The error may exist in xin/altitude/cms/job/mapper/SysJobLogMapper.java (best guess)\n### The error may involve xin.altitude.cms.job.mapper.SysJobLogMapper.delete\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of full table deletion',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (83, '调度日志', 9, 'xin.altitude.cms.job.controller.SysJobLogController.clean()', 'DELETE', 1, 'admin', '',
        '/cms-api/monitor/jobLog/clean', '127.0.0.1', '内网IP', '{}', '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: DELETE FROM sys_job_log \n \n WHERE (limit 100)\n### The error may exist in xin/altitude/cms/job/mapper/SysJobLogMapper.java (best guess)\n### The error may involve xin.altitude.cms.job.mapper.SysJobLogMapper.delete\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: DELETE FROM sys_job_log \n \n WHERE (limit 100)',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (84, '调度日志', 9, 'xin.altitude.cms.job.controller.SysJobLogController.clean()', 'DELETE', 1, 'admin', '',
        '/cms-api/monitor/jobLog/clean', '127.0.0.1', '内网IP', '{}', '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: DELETE FROM sys_job_log \n \n WHERE (job_log_id > ? AND limit 100)\n### The error may exist in xin/altitude/cms/job/mapper/SysJobLogMapper.java (best guess)\n### The error may involve xin.altitude.cms.job.mapper.SysJobLogMapper.delete\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: DELETE FROM sys_job_log \n \n WHERE (job_log_id > ? AND limit 100)',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (85, '调度日志', 9, 'xin.altitude.cms.job.controller.SysJobLogController.clean()', 'DELETE', 1, 'admin', '',
        '/cms-api/monitor/jobLog/clean', '127.0.0.1', '内网IP', '{}', '', 1,
        'nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of full table deletion\n### The error may exist in xin/altitude/cms/job/mapper/SysJobLogMapper.java (best guess)\n### The error may involve xin.altitude.cms.job.mapper.SysJobLogMapper.delete\n### The error occurred while executing an update\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of full table deletion',
        NULL);
INSERT INTO `sys_oper_log`
VALUES (86, '调度日志', 9, 'xin.altitude.cms.job.controller.SysJobLogController.clean()', 'DELETE', 1, 'admin', '',
        '/cms-api/monitor/jobLog/clean', '127.0.0.1', '内网IP', '{}', '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (87, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (88, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (89, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (90, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":1,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (91, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job', '127.0.0.1', '内网IP',
        '{\"cronExpression\":\"* * * * * ?\",\"jobName\":\"每日更新数据\",\"jobId\":4,\"nextValidTime\":1641976065000,\"deleted\":false,\"createTime\":\"2022-01-12T15:10:41\",\"invokeTarget\":\"cmsTask.oneParams(\'ry\')\",\"concurrent\":\"1\",\"misfirePolicy\":\"3\",\"updateTime\":\"2022-01-12T16:23:28\",\"jobGroup\":\"DEFAULT\",\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (92, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job', '127.0.0.1', '内网IP',
        '{\"cronExpression\":\"* * * * * ?\",\"jobName\":\"每秒更新数据\",\"jobId\":4,\"nextValidTime\":1641976091000,\"deleted\":false,\"createTime\":\"2022-01-12T15:10:41\",\"invokeTarget\":\"cmsTask.oneParams(\'ry\')\",\"concurrent\":\"1\",\"misfirePolicy\":\"3\",\"updateTime\":\"2022-01-12T16:23:28\",\"jobGroup\":\"DEFAULT\",\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (93, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job', '127.0.0.1', '内网IP',
        '{\"cronExpression\":\"* * * * * ?\",\"jobName\":\"每秒更新数据\",\"jobId\":4,\"nextValidTime\":1641976102000,\"deleted\":false,\"createTime\":\"2022-01-12T15:10:41\",\"invokeTarget\":\"cmsTask.oneParams(\'ry\')\",\"concurrent\":\"1\",\"misfirePolicy\":\"3\",\"updateTime\":\"2022-01-12T16:23:28\",\"jobGroup\":\"DEFAULT\",\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (94, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (95, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (96, '定时任务调度日志', 3, 'xin.altitude.cms.job.controller.SysJobLogController.remove()', 'DELETE', 1, 'admin', '',
        '/cms-api/monitor/jobLog/126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173',
        '127.0.0.1', '内网IP',
        '{jobLogIds=126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (97, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (98, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (99, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (100, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (101, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job', '127.0.0.1', '内网IP',
        '{\"cronExpression\":\"* * * * * ?\",\"jobName\":\"每分钟更新数据\",\"jobId\":4,\"nextValidTime\":1641978636000,\"deleted\":false,\"createTime\":\"2022-01-12T15:10:41\",\"invokeTarget\":\"cmsTask.oneParams(\'ry\')\",\"concurrent\":\"1\",\"misfirePolicy\":\"3\",\"updateTime\":\"2022-01-12T17:09:39\",\"jobGroup\":\"DEFAULT\",\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (102, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (103, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (104, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (105, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (106, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (107, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (108, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (109, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (110, '菜单管理', 2, 'xin.altitude.cms.system.controller.SysMenuProController.edit()', 'PUT', 1, 'admin', '',
        '/cms-api/system/menu', '127.0.0.1', '内网IP',
        '{\"visible\":\"0\",\"query\":\"\",\"icon\":\"swagger\",\"orderNum\":\"4\",\"menuName\":\"系统接口\",\"remark\":\"系统接口菜单\",\"updateTime\":1641368520000,\"params\":{},\"parentId\":0,\"isCache\":\"1\",\"path\":\"/doc.html\",\"component\":\"tool/swagger/index\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1637546274000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":116,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\",\"status\":\"0\"}',
        '{\"code\":500,\"msg\":\"修改菜单\'系统接口\'失败，地址必须以http(s)://开头\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (111, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (112, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (113, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (114, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (115, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (116, '定时任务调度日志', 3, 'xin.altitude.cms.job.controller.SysJobLogController.remove()', 'DELETE', 1, 'admin', '',
        '/cms-api/monitor/jobLog/174,175', '127.0.0.1', '内网IP', '{jobLogIds=174,175}',
        '{\"code\":200,\"msg\":\"操作成功\",\"data\":true}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (117, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\"}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (118, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"0\"}',
        '{\"code\":200,\"msg\":\"操作成功\",\"data\":true}', 0, '', NULL);
INSERT INTO `sys_oper_log`
VALUES (119, '定时任务', 2, 'xin.altitude.cms.job.controller.SysJobController.changeStatus()', 'PUT', 1, 'admin', '',
        '/cms-api/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":4,\"status\":\"1\"}',
        '{\"code\":200,\"msg\":\"操作成功\",\"data\":true}', 0, '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`
(
    `post_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `post_code`   varchar(64) NOT NULL COMMENT '岗位编码',
    `post_name`   varchar(50) NOT NULL COMMENT '岗位名称',
    `post_sort`   int(4) NOT NULL COMMENT '显示顺序',
    `status`      char(1)     NOT NULL COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_post`
VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2021-11-22 09:57:54', '', NULL, '');
INSERT INTO `sys_post`
VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2021-11-22 09:57:54', '', NULL, '');
INSERT INTO `sys_post`
VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2021-11-22 09:57:54', '', NULL, '');
INSERT INTO `sys_post`
VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2021-11-22 09:57:54', '', NULL, '');
COMMIT;

-- ----------------------------
-- Table structure for sys_qrtz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_job`;
CREATE TABLE `sys_qrtz_job`
(
    `job_id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '任务ID',
    `job_name`        varchar(64)  NOT NULL DEFAULT '' COMMENT '任务名称',
    `job_group`       varchar(64)  NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
    `invoke_target`   varchar(500) NOT NULL COMMENT '调用目标字符串',
    `cron_expression` varchar(255)          DEFAULT '' COMMENT 'cron执行表达式',
    `misfire_policy`  varchar(20)           DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    `concurrent`      char(1)               DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
    `status`          char(1)               DEFAULT '0' COMMENT '状态（0正常 1暂停）',
    `create_by`       varchar(64)           DEFAULT '' COMMENT '创建者',
    `create_time`     datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`       varchar(64)           DEFAULT '' COMMENT '更新者',
    `update_time`     datetime              DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`          varchar(500)          DEFAULT '' COMMENT '备注信息',
    `deleted`         int(11) DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_qrtz_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_qrtz_job`
VALUES (1, '系统默认（无参）', 'DEFAULT', 'cmsTask.noParams', '* * * * * ?', '3', '1', '1', 'admin', '2021-11-22 09:57:56', '',
        '2022-01-12 16:27:29', '', 0);
INSERT INTO `sys_qrtz_job`
VALUES (2, '系统默认（有参）', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin',
        '2021-11-22 09:57:56', '', '2022-01-12 16:23:21', '', 0);
INSERT INTO `sys_qrtz_job`
VALUES (3, '系统默认（多参）', 'DEFAULT', 'cmsTask.mutiParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1',
        '1', 'admin', '2021-11-22 09:57:56', '', '2022-01-12 16:23:09', '', 0);
INSERT INTO `sys_qrtz_job`
VALUES (4, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '*/2 * * * * ?', '3', '1', '1', '', '2022-01-12 15:10:41',
        '', '2022-01-18 10:14:05', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_qrtz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_job_log`;
CREATE TABLE `sys_qrtz_job_log`
(
    `job_log_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
    `job_name`       varchar(64)  NOT NULL COMMENT '任务名称',
    `job_group`      varchar(64)  NOT NULL COMMENT '任务组名',
    `invoke_target`  varchar(500) NOT NULL COMMENT '调用目标字符串',
    `job_message`    varchar(500)  DEFAULT NULL COMMENT '日志信息',
    `status`         char(1)       DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
    `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
    `start_time`     datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    `stop_time`      datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
    `create_time`    datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime      DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        int(11) DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1020 DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_qrtz_job_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_qrtz_job_log`
VALUES (176, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:11',
        '2022-01-12 16:30:11', '2022-01-12 16:30:11', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (177, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:11',
        '2022-01-12 16:30:11', '2022-01-12 16:30:11', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (178, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:12',
        '2022-01-12 16:30:12', '2022-01-12 16:30:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (179, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:13',
        '2022-01-12 16:30:13', '2022-01-12 16:30:13', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (180, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:14',
        '2022-01-12 16:30:14', '2022-01-12 16:30:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (181, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:15',
        '2022-01-12 16:30:15', '2022-01-12 16:30:15', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (182, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：1毫秒', '0', '', '2022-01-12 16:30:16',
        '2022-01-12 16:30:16', '2022-01-12 16:30:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (183, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:17',
        '2022-01-12 16:30:17', '2022-01-12 16:30:17', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (184, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:18',
        '2022-01-12 16:30:18', '2022-01-12 16:30:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (185, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:19',
        '2022-01-12 16:30:19', '2022-01-12 16:30:19', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (186, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:20',
        '2022-01-12 16:30:20', '2022-01-12 16:30:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (187, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:21',
        '2022-01-12 16:30:21', '2022-01-12 16:30:21', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (188, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:22',
        '2022-01-12 16:30:22', '2022-01-12 16:30:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (189, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:23',
        '2022-01-12 16:30:23', '2022-01-12 16:30:23', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (190, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:24',
        '2022-01-12 16:30:24', '2022-01-12 16:30:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (191, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:25',
        '2022-01-12 16:30:25', '2022-01-12 16:30:25', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (192, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：1毫秒', '0', '', '2022-01-12 16:30:26',
        '2022-01-12 16:30:26', '2022-01-12 16:30:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (193, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:27',
        '2022-01-12 16:30:27', '2022-01-12 16:30:27', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (194, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:30:28',
        '2022-01-12 16:30:28', '2022-01-12 16:30:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (195, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：2毫秒', '0', '', '2022-01-12 16:49:49',
        '2022-01-12 16:49:49', '2022-01-12 16:49:49', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (196, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:50',
        '2022-01-12 16:49:50', '2022-01-12 16:49:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (197, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:51',
        '2022-01-12 16:49:51', '2022-01-12 16:49:51', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (198, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:52',
        '2022-01-12 16:49:52', '2022-01-12 16:49:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (199, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:53',
        '2022-01-12 16:49:53', '2022-01-12 16:49:53', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (200, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：1毫秒', '0', '', '2022-01-12 16:49:54',
        '2022-01-12 16:49:54', '2022-01-12 16:49:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (201, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:55',
        '2022-01-12 16:49:55', '2022-01-12 16:49:55', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (202, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:56',
        '2022-01-12 16:49:56', '2022-01-12 16:49:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (203, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:57',
        '2022-01-12 16:49:57', '2022-01-12 16:49:57', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (204, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:58',
        '2022-01-12 16:49:58', '2022-01-12 16:49:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (205, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:49:59',
        '2022-01-12 16:49:59', '2022-01-12 16:49:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (206, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:50:00',
        '2022-01-12 16:50:00', '2022-01-12 16:50:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (207, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 16:50:01',
        '2022-01-12 16:50:01', '2022-01-12 16:50:01', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (208, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：1毫秒', '0', '', '2022-01-12 17:09:24',
        '2022-01-12 17:09:24', '2022-01-12 17:09:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (209, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：1毫秒', '0', '', '2022-01-12 17:09:25',
        '2022-01-12 17:09:25', '2022-01-12 17:09:25', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (210, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:26',
        '2022-01-12 17:09:26', '2022-01-12 17:09:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (211, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:27',
        '2022-01-12 17:09:27', '2022-01-12 17:09:27', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (212, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：1毫秒', '0', '', '2022-01-12 17:09:28',
        '2022-01-12 17:09:28', '2022-01-12 17:09:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (213, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:29',
        '2022-01-12 17:09:29', '2022-01-12 17:09:29', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (214, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:30',
        '2022-01-12 17:09:30', '2022-01-12 17:09:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (215, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:31',
        '2022-01-12 17:09:31', '2022-01-12 17:09:31', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (216, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:32',
        '2022-01-12 17:09:32', '2022-01-12 17:09:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (217, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:33',
        '2022-01-12 17:09:33', '2022-01-12 17:09:33', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (218, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:34',
        '2022-01-12 17:09:34', '2022-01-12 17:09:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (219, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:35',
        '2022-01-12 17:09:35', '2022-01-12 17:09:35', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (220, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:36',
        '2022-01-12 17:09:36', '2022-01-12 17:09:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (221, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:37',
        '2022-01-12 17:09:37', '2022-01-12 17:09:37', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (222, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：0毫秒', '0', '', '2022-01-12 17:09:38',
        '2022-01-12 17:09:38', '2022-01-12 17:09:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (223, '每秒更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每秒更新数据 总共耗时：1毫秒', '0', '', '2022-01-12 17:09:39',
        '2022-01-12 17:09:39', '2022-01-12 17:09:39', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (224, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:22',
        '2022-01-17 12:42:22', '2022-01-17 12:42:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (225, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:23',
        '2022-01-17 12:42:23', '2022-01-17 12:42:23', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (226, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:24',
        '2022-01-17 12:42:24', '2022-01-17 12:42:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (227, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:25',
        '2022-01-17 12:42:25', '2022-01-17 12:42:25', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (228, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:26',
        '2022-01-17 12:42:26', '2022-01-17 12:42:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (229, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-17 12:42:27',
        '2022-01-17 12:42:27', '2022-01-17 12:42:27', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (230, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:28',
        '2022-01-17 12:42:28', '2022-01-17 12:42:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (231, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:29',
        '2022-01-17 12:42:29', '2022-01-17 12:42:29', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (232, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:30',
        '2022-01-17 12:42:30', '2022-01-17 12:42:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (233, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:31',
        '2022-01-17 12:42:31', '2022-01-17 12:42:31', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (234, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:32',
        '2022-01-17 12:42:32', '2022-01-17 12:42:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (235, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:33',
        '2022-01-17 12:42:33', '2022-01-17 12:42:33', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (236, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:34',
        '2022-01-17 12:42:34', '2022-01-17 12:42:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (237, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:35',
        '2022-01-17 12:42:35', '2022-01-17 12:42:35', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (238, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:36',
        '2022-01-17 12:42:36', '2022-01-17 12:42:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (239, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:37',
        '2022-01-17 12:42:37', '2022-01-17 12:42:37', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (240, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:38',
        '2022-01-17 12:42:38', '2022-01-17 12:42:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (241, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:39',
        '2022-01-17 12:42:39', '2022-01-17 12:42:39', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (242, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:40',
        '2022-01-17 12:42:40', '2022-01-17 12:42:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (243, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:41',
        '2022-01-17 12:42:41', '2022-01-17 12:42:41', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (244, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-17 12:42:42',
        '2022-01-17 12:42:42', '2022-01-17 12:42:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (245, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:43',
        '2022-01-17 12:42:43', '2022-01-17 12:42:43', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (246, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-17 12:42:44',
        '2022-01-17 12:42:44', '2022-01-17 12:42:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (247, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-17 12:42:45',
        '2022-01-17 12:42:45', '2022-01-17 12:42:45', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (248, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:50:27',
        '2022-01-18 08:50:27', '2022-01-18 08:50:27', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (249, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:28',
        '2022-01-18 08:50:28', '2022-01-18 08:50:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (250, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:29',
        '2022-01-18 08:50:29', '2022-01-18 08:50:29', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (251, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:30',
        '2022-01-18 08:50:30', '2022-01-18 08:50:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (252, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:31',
        '2022-01-18 08:50:31', '2022-01-18 08:50:31', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (253, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:50:32',
        '2022-01-18 08:50:32', '2022-01-18 08:50:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (254, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:50:33',
        '2022-01-18 08:50:33', '2022-01-18 08:50:33', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (255, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:34',
        '2022-01-18 08:50:34', '2022-01-18 08:50:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (256, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:35',
        '2022-01-18 08:50:35', '2022-01-18 08:50:35', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (257, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:36',
        '2022-01-18 08:50:36', '2022-01-18 08:50:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (258, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:37',
        '2022-01-18 08:50:37', '2022-01-18 08:50:37', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (259, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:38',
        '2022-01-18 08:50:38', '2022-01-18 08:50:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (260, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:39',
        '2022-01-18 08:50:39', '2022-01-18 08:50:39', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (261, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:40',
        '2022-01-18 08:50:40', '2022-01-18 08:50:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (262, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:41',
        '2022-01-18 08:50:41', '2022-01-18 08:50:41', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (263, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:50:42',
        '2022-01-18 08:50:42', '2022-01-18 08:50:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (264, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:43',
        '2022-01-18 08:50:43', '2022-01-18 08:50:43', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (265, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:44',
        '2022-01-18 08:50:44', '2022-01-18 08:50:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (266, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:45',
        '2022-01-18 08:50:45', '2022-01-18 08:50:45', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (267, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:50:46',
        '2022-01-18 08:50:46', '2022-01-18 08:50:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (268, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:47',
        '2022-01-18 08:50:47', '2022-01-18 08:50:47', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (269, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:50:48',
        '2022-01-18 08:50:48', '2022-01-18 08:50:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (270, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:50:49',
        '2022-01-18 08:50:49', '2022-01-18 08:50:49', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (271, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:50',
        '2022-01-18 08:50:50', '2022-01-18 08:50:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (272, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:51',
        '2022-01-18 08:50:51', '2022-01-18 08:50:51', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (273, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:52',
        '2022-01-18 08:50:52', '2022-01-18 08:50:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (274, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:53',
        '2022-01-18 08:50:53', '2022-01-18 08:50:53', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (275, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:50:54',
        '2022-01-18 08:50:54', '2022-01-18 08:50:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (276, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:55',
        '2022-01-18 08:50:55', '2022-01-18 08:50:55', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (277, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:56',
        '2022-01-18 08:50:56', '2022-01-18 08:50:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (278, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:57',
        '2022-01-18 08:50:57', '2022-01-18 08:50:57', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (279, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:58',
        '2022-01-18 08:50:58', '2022-01-18 08:50:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (280, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:50:59',
        '2022-01-18 08:50:59', '2022-01-18 08:50:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (281, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:00',
        '2022-01-18 08:51:00', '2022-01-18 08:51:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (282, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:01',
        '2022-01-18 08:51:01', '2022-01-18 08:51:01', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (283, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:51:02',
        '2022-01-18 08:51:02', '2022-01-18 08:51:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (284, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:03',
        '2022-01-18 08:51:03', '2022-01-18 08:51:03', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (285, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:04',
        '2022-01-18 08:51:04', '2022-01-18 08:51:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (286, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:05',
        '2022-01-18 08:51:05', '2022-01-18 08:51:05', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (287, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:06',
        '2022-01-18 08:51:06', '2022-01-18 08:51:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (288, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:07',
        '2022-01-18 08:51:07', '2022-01-18 08:51:07', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (289, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:08',
        '2022-01-18 08:51:08', '2022-01-18 08:51:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (290, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:09',
        '2022-01-18 08:51:09', '2022-01-18 08:51:09', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (291, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:10',
        '2022-01-18 08:51:10', '2022-01-18 08:51:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (292, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:11',
        '2022-01-18 08:51:11', '2022-01-18 08:51:11', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (293, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:12',
        '2022-01-18 08:51:12', '2022-01-18 08:51:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (294, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:13',
        '2022-01-18 08:51:13', '2022-01-18 08:51:13', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (295, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:51:14',
        '2022-01-18 08:51:14', '2022-01-18 08:51:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (296, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:15',
        '2022-01-18 08:51:15', '2022-01-18 08:51:15', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (297, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:51:16',
        '2022-01-18 08:51:16', '2022-01-18 08:51:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (298, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:51:17',
        '2022-01-18 08:51:17', '2022-01-18 08:51:17', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (299, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：2毫秒', '0', '', '2022-01-18 08:54:59',
        '2022-01-18 08:54:59', '2022-01-18 08:54:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (300, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:00',
        '2022-01-18 08:55:00', '2022-01-18 08:54:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (301, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:00',
        '2022-01-18 08:55:00', '2022-01-18 08:54:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (302, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:00',
        '2022-01-18 08:55:00', '2022-01-18 08:54:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (303, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:00',
        '2022-01-18 08:55:00', '2022-01-18 08:55:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (304, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:01',
        '2022-01-18 08:55:01', '2022-01-18 08:55:01', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (305, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:02',
        '2022-01-18 08:55:02', '2022-01-18 08:55:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (306, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:03',
        '2022-01-18 08:55:03', '2022-01-18 08:55:03', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (307, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:04',
        '2022-01-18 08:55:04', '2022-01-18 08:55:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (308, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:05',
        '2022-01-18 08:55:05', '2022-01-18 08:55:05', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (309, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:06',
        '2022-01-18 08:55:06', '2022-01-18 08:55:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (310, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:07',
        '2022-01-18 08:55:07', '2022-01-18 08:55:07', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (311, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:08',
        '2022-01-18 08:55:08', '2022-01-18 08:55:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (312, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:09',
        '2022-01-18 08:55:09', '2022-01-18 08:55:09', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (313, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:10',
        '2022-01-18 08:55:10', '2022-01-18 08:55:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (314, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:11',
        '2022-01-18 08:55:11', '2022-01-18 08:55:11', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (315, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:12',
        '2022-01-18 08:55:12', '2022-01-18 08:55:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (316, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:13',
        '2022-01-18 08:55:13', '2022-01-18 08:55:13', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (317, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:14',
        '2022-01-18 08:55:14', '2022-01-18 08:55:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (318, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:15',
        '2022-01-18 08:55:15', '2022-01-18 08:55:15', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (319, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:16',
        '2022-01-18 08:55:16', '2022-01-18 08:55:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (320, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:17',
        '2022-01-18 08:55:17', '2022-01-18 08:55:17', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (321, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:18',
        '2022-01-18 08:55:18', '2022-01-18 08:55:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (322, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:19',
        '2022-01-18 08:55:19', '2022-01-18 08:55:19', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (323, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:20',
        '2022-01-18 08:55:20', '2022-01-18 08:55:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (324, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:21',
        '2022-01-18 08:55:21', '2022-01-18 08:55:21', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (325, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:22',
        '2022-01-18 08:55:22', '2022-01-18 08:55:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (326, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:23',
        '2022-01-18 08:55:23', '2022-01-18 08:55:23', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (327, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:24',
        '2022-01-18 08:55:24', '2022-01-18 08:55:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (328, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:25',
        '2022-01-18 08:55:25', '2022-01-18 08:55:25', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (329, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:26',
        '2022-01-18 08:55:26', '2022-01-18 08:55:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (330, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:27',
        '2022-01-18 08:55:27', '2022-01-18 08:55:27', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (331, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:28',
        '2022-01-18 08:55:28', '2022-01-18 08:55:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (332, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:29',
        '2022-01-18 08:55:29', '2022-01-18 08:55:29', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (333, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:30',
        '2022-01-18 08:55:30', '2022-01-18 08:55:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (334, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:31',
        '2022-01-18 08:55:31', '2022-01-18 08:55:31', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (335, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:32',
        '2022-01-18 08:55:32', '2022-01-18 08:55:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (336, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:33',
        '2022-01-18 08:55:33', '2022-01-18 08:55:33', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (337, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:34',
        '2022-01-18 08:55:34', '2022-01-18 08:55:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (338, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:35',
        '2022-01-18 08:55:35', '2022-01-18 08:55:35', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (339, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:36',
        '2022-01-18 08:55:36', '2022-01-18 08:55:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (340, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:37',
        '2022-01-18 08:55:37', '2022-01-18 08:55:37', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (341, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:38',
        '2022-01-18 08:55:38', '2022-01-18 08:55:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (342, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:39',
        '2022-01-18 08:55:39', '2022-01-18 08:55:39', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (343, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:55:40',
        '2022-01-18 08:55:40', '2022-01-18 08:55:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (344, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:41',
        '2022-01-18 08:55:41', '2022-01-18 08:55:41', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (345, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:55:42',
        '2022-01-18 08:55:42', '2022-01-18 08:55:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (346, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：2毫秒', '0', '', '2022-01-18 08:59:35',
        '2022-01-18 08:59:35', '2022-01-18 08:59:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (347, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:35',
        '2022-01-18 08:59:35', '2022-01-18 08:59:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (348, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:59:35',
        '2022-01-18 08:59:35', '2022-01-18 08:59:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (349, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:35',
        '2022-01-18 08:59:35', '2022-01-18 08:59:35', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (350, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:36',
        '2022-01-18 08:59:36', '2022-01-18 08:59:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (351, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:37',
        '2022-01-18 08:59:37', '2022-01-18 08:59:37', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (352, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:38',
        '2022-01-18 08:59:38', '2022-01-18 08:59:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (353, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:39',
        '2022-01-18 08:59:39', '2022-01-18 08:59:39', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (354, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:40',
        '2022-01-18 08:59:40', '2022-01-18 08:59:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (355, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:41',
        '2022-01-18 08:59:41', '2022-01-18 08:59:41', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (356, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:42',
        '2022-01-18 08:59:42', '2022-01-18 08:59:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (357, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:43',
        '2022-01-18 08:59:43', '2022-01-18 08:59:43', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (358, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:59:44',
        '2022-01-18 08:59:44', '2022-01-18 08:59:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (359, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:45',
        '2022-01-18 08:59:45', '2022-01-18 08:59:45', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (360, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:46',
        '2022-01-18 08:59:46', '2022-01-18 08:59:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (361, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:47',
        '2022-01-18 08:59:47', '2022-01-18 08:59:47', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (362, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:48',
        '2022-01-18 08:59:48', '2022-01-18 08:59:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (363, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:49',
        '2022-01-18 08:59:49', '2022-01-18 08:59:49', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (364, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:50',
        '2022-01-18 08:59:50', '2022-01-18 08:59:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (365, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:51',
        '2022-01-18 08:59:51', '2022-01-18 08:59:51', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (366, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:52',
        '2022-01-18 08:59:52', '2022-01-18 08:59:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (367, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:53',
        '2022-01-18 08:59:53', '2022-01-18 08:59:53', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (368, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:54',
        '2022-01-18 08:59:54', '2022-01-18 08:59:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (369, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:55',
        '2022-01-18 08:59:55', '2022-01-18 08:59:55', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (370, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:56',
        '2022-01-18 08:59:56', '2022-01-18 08:59:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (371, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:57',
        '2022-01-18 08:59:57', '2022-01-18 08:59:57', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (372, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 08:59:58',
        '2022-01-18 08:59:58', '2022-01-18 08:59:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (373, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 08:59:59',
        '2022-01-18 08:59:59', '2022-01-18 08:59:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (374, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:00:00',
        '2022-01-18 09:00:00', '2022-01-18 09:00:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (375, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:00:01',
        '2022-01-18 09:00:01', '2022-01-18 09:00:01', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (376, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:00:02',
        '2022-01-18 09:00:02', '2022-01-18 09:00:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (377, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:00:03',
        '2022-01-18 09:00:03', '2022-01-18 09:00:03', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (378, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:00:04',
        '2022-01-18 09:00:04', '2022-01-18 09:00:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (379, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:00:05',
        '2022-01-18 09:00:05', '2022-01-18 09:00:05', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (380, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:00:06',
        '2022-01-18 09:00:06', '2022-01-18 09:00:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (381, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：2毫秒', '0', '', '2022-01-18 09:02:51',
        '2022-01-18 09:02:51', '2022-01-18 09:02:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (382, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:02:51',
        '2022-01-18 09:02:51', '2022-01-18 09:02:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (383, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:51',
        '2022-01-18 09:02:51', '2022-01-18 09:02:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (384, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:51',
        '2022-01-18 09:02:51', '2022-01-18 09:02:51', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (385, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:52',
        '2022-01-18 09:02:52', '2022-01-18 09:02:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (386, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:53',
        '2022-01-18 09:02:53', '2022-01-18 09:02:53', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (387, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:54',
        '2022-01-18 09:02:54', '2022-01-18 09:02:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (388, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:55',
        '2022-01-18 09:02:55', '2022-01-18 09:02:55', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (389, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:56',
        '2022-01-18 09:02:56', '2022-01-18 09:02:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (390, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:02:57',
        '2022-01-18 09:02:57', '2022-01-18 09:02:57', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (391, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:58',
        '2022-01-18 09:02:58', '2022-01-18 09:02:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (392, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:02:59',
        '2022-01-18 09:02:59', '2022-01-18 09:02:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (393, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:00',
        '2022-01-18 09:03:00', '2022-01-18 09:03:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (394, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:01',
        '2022-01-18 09:03:01', '2022-01-18 09:03:01', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (395, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:02',
        '2022-01-18 09:03:02', '2022-01-18 09:03:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (396, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:03',
        '2022-01-18 09:03:03', '2022-01-18 09:03:03', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (397, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:03:04',
        '2022-01-18 09:03:04', '2022-01-18 09:03:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (398, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:05',
        '2022-01-18 09:03:05', '2022-01-18 09:03:05', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (399, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:06',
        '2022-01-18 09:03:06', '2022-01-18 09:03:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (400, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:07',
        '2022-01-18 09:03:07', '2022-01-18 09:03:07', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (401, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:08',
        '2022-01-18 09:03:08', '2022-01-18 09:03:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (402, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:09',
        '2022-01-18 09:03:09', '2022-01-18 09:03:09', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (403, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:10',
        '2022-01-18 09:03:10', '2022-01-18 09:03:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (404, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:11',
        '2022-01-18 09:03:11', '2022-01-18 09:03:11', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (405, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:03:12',
        '2022-01-18 09:03:12', '2022-01-18 09:03:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (406, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：2毫秒', '0', '', '2022-01-18 09:34:15',
        '2022-01-18 09:34:15', '2022-01-18 09:34:15', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (407, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:16',
        '2022-01-18 09:34:16', '2022-01-18 09:34:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (408, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:17',
        '2022-01-18 09:34:17', '2022-01-18 09:34:17', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (409, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:18',
        '2022-01-18 09:34:18', '2022-01-18 09:34:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (410, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:19',
        '2022-01-18 09:34:19', '2022-01-18 09:34:19', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (411, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:20',
        '2022-01-18 09:34:20', '2022-01-18 09:34:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (412, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:34:21',
        '2022-01-18 09:34:21', '2022-01-18 09:34:21', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (413, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:22',
        '2022-01-18 09:34:22', '2022-01-18 09:34:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (414, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:34:23',
        '2022-01-18 09:34:23', '2022-01-18 09:34:23', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (415, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:24',
        '2022-01-18 09:34:24', '2022-01-18 09:34:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (416, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:25',
        '2022-01-18 09:34:25', '2022-01-18 09:34:25', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (417, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:26',
        '2022-01-18 09:34:26', '2022-01-18 09:34:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (418, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:27',
        '2022-01-18 09:34:27', '2022-01-18 09:34:27', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (419, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:28',
        '2022-01-18 09:34:28', '2022-01-18 09:34:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (420, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:29',
        '2022-01-18 09:34:29', '2022-01-18 09:34:29', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (421, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:30',
        '2022-01-18 09:34:30', '2022-01-18 09:34:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (422, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:31',
        '2022-01-18 09:34:31', '2022-01-18 09:34:31', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (423, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:32',
        '2022-01-18 09:34:32', '2022-01-18 09:34:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (424, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:33',
        '2022-01-18 09:34:33', '2022-01-18 09:34:33', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (425, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:34',
        '2022-01-18 09:34:34', '2022-01-18 09:34:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (426, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:35',
        '2022-01-18 09:34:35', '2022-01-18 09:34:35', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (427, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:36',
        '2022-01-18 09:34:36', '2022-01-18 09:34:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (428, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:37',
        '2022-01-18 09:34:37', '2022-01-18 09:34:37', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (429, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:34:38',
        '2022-01-18 09:34:38', '2022-01-18 09:34:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (430, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:39',
        '2022-01-18 09:34:39', '2022-01-18 09:34:39', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (431, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:40',
        '2022-01-18 09:34:40', '2022-01-18 09:34:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (432, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:41',
        '2022-01-18 09:34:41', '2022-01-18 09:34:41', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (433, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:42',
        '2022-01-18 09:34:42', '2022-01-18 09:34:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (434, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:43',
        '2022-01-18 09:34:43', '2022-01-18 09:34:43', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (435, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:44',
        '2022-01-18 09:34:44', '2022-01-18 09:34:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (436, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:45',
        '2022-01-18 09:34:45', '2022-01-18 09:34:45', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (437, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:46',
        '2022-01-18 09:34:46', '2022-01-18 09:34:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (438, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:47',
        '2022-01-18 09:34:47', '2022-01-18 09:34:47', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (439, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:48',
        '2022-01-18 09:34:48', '2022-01-18 09:34:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (440, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:49',
        '2022-01-18 09:34:49', '2022-01-18 09:34:49', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (441, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:50',
        '2022-01-18 09:34:50', '2022-01-18 09:34:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (442, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:34:51',
        '2022-01-18 09:34:51', '2022-01-18 09:34:51', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (443, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:52',
        '2022-01-18 09:34:52', '2022-01-18 09:34:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (444, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:53',
        '2022-01-18 09:34:53', '2022-01-18 09:34:53', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (445, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:54',
        '2022-01-18 09:34:54', '2022-01-18 09:34:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (446, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:55',
        '2022-01-18 09:34:55', '2022-01-18 09:34:55', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (447, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:56',
        '2022-01-18 09:34:56', '2022-01-18 09:34:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (448, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:57',
        '2022-01-18 09:34:57', '2022-01-18 09:34:57', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (449, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:58',
        '2022-01-18 09:34:58', '2022-01-18 09:34:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (450, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:34:59',
        '2022-01-18 09:34:59', '2022-01-18 09:34:59', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (451, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:00',
        '2022-01-18 09:35:00', '2022-01-18 09:35:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (452, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:01',
        '2022-01-18 09:35:01', '2022-01-18 09:35:01', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (453, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:02',
        '2022-01-18 09:35:02', '2022-01-18 09:35:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (454, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:35:03',
        '2022-01-18 09:35:03', '2022-01-18 09:35:03', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (455, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:35:04',
        '2022-01-18 09:35:04', '2022-01-18 09:35:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (456, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:05',
        '2022-01-18 09:35:05', '2022-01-18 09:35:05', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (457, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:06',
        '2022-01-18 09:35:06', '2022-01-18 09:35:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (458, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:07',
        '2022-01-18 09:35:07', '2022-01-18 09:35:07', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (459, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:08',
        '2022-01-18 09:35:08', '2022-01-18 09:35:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (460, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:09',
        '2022-01-18 09:35:09', '2022-01-18 09:35:09', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (461, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:10',
        '2022-01-18 09:35:10', '2022-01-18 09:35:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (462, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:11',
        '2022-01-18 09:35:11', '2022-01-18 09:35:11', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (463, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:12',
        '2022-01-18 09:35:12', '2022-01-18 09:35:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (464, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:13',
        '2022-01-18 09:35:13', '2022-01-18 09:35:13', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (465, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:14',
        '2022-01-18 09:35:14', '2022-01-18 09:35:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (466, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:14',
        '2022-01-18 09:35:14', '2022-01-18 09:35:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (467, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:16',
        '2022-01-18 09:35:16', '2022-01-18 09:35:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (468, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:18',
        '2022-01-18 09:35:18', '2022-01-18 09:35:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (469, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:20',
        '2022-01-18 09:35:20', '2022-01-18 09:35:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (470, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:22',
        '2022-01-18 09:35:22', '2022-01-18 09:35:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (471, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:24',
        '2022-01-18 09:35:24', '2022-01-18 09:35:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (472, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:26',
        '2022-01-18 09:35:26', '2022-01-18 09:35:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (473, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:28',
        '2022-01-18 09:35:28', '2022-01-18 09:35:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (474, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:30',
        '2022-01-18 09:35:30', '2022-01-18 09:35:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (475, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:32',
        '2022-01-18 09:35:32', '2022-01-18 09:35:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (476, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:34',
        '2022-01-18 09:35:34', '2022-01-18 09:35:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (477, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:36',
        '2022-01-18 09:35:36', '2022-01-18 09:35:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (478, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:38',
        '2022-01-18 09:35:38', '2022-01-18 09:35:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (479, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:35:40',
        '2022-01-18 09:35:40', '2022-01-18 09:35:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (480, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:42',
        '2022-01-18 09:35:42', '2022-01-18 09:35:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (481, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:44',
        '2022-01-18 09:35:44', '2022-01-18 09:35:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (482, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:46',
        '2022-01-18 09:35:46', '2022-01-18 09:35:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (483, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:48',
        '2022-01-18 09:35:48', '2022-01-18 09:35:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (484, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:50',
        '2022-01-18 09:35:50', '2022-01-18 09:35:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (485, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:52',
        '2022-01-18 09:35:52', '2022-01-18 09:35:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (486, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:54',
        '2022-01-18 09:35:54', '2022-01-18 09:35:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (487, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:56',
        '2022-01-18 09:35:56', '2022-01-18 09:35:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (488, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:35:58',
        '2022-01-18 09:35:58', '2022-01-18 09:35:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (489, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:00',
        '2022-01-18 09:36:00', '2022-01-18 09:36:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (490, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:02',
        '2022-01-18 09:36:02', '2022-01-18 09:36:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (491, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:04',
        '2022-01-18 09:36:04', '2022-01-18 09:36:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (492, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:06',
        '2022-01-18 09:36:06', '2022-01-18 09:36:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (493, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:08',
        '2022-01-18 09:36:08', '2022-01-18 09:36:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (494, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:10',
        '2022-01-18 09:36:10', '2022-01-18 09:36:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (495, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:12',
        '2022-01-18 09:36:12', '2022-01-18 09:36:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (496, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:14',
        '2022-01-18 09:36:14', '2022-01-18 09:36:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (497, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:16',
        '2022-01-18 09:36:16', '2022-01-18 09:36:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (498, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:18',
        '2022-01-18 09:36:18', '2022-01-18 09:36:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (499, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:20',
        '2022-01-18 09:36:20', '2022-01-18 09:36:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (500, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:22',
        '2022-01-18 09:36:22', '2022-01-18 09:36:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (501, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:24',
        '2022-01-18 09:36:24', '2022-01-18 09:36:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (502, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:26',
        '2022-01-18 09:36:26', '2022-01-18 09:36:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (503, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:28',
        '2022-01-18 09:36:28', '2022-01-18 09:36:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (504, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:30',
        '2022-01-18 09:36:30', '2022-01-18 09:36:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (505, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:32',
        '2022-01-18 09:36:32', '2022-01-18 09:36:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (506, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:34',
        '2022-01-18 09:36:34', '2022-01-18 09:36:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (507, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:36',
        '2022-01-18 09:36:36', '2022-01-18 09:36:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (508, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:38',
        '2022-01-18 09:36:38', '2022-01-18 09:36:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (509, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:40',
        '2022-01-18 09:36:40', '2022-01-18 09:36:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (510, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:42',
        '2022-01-18 09:36:42', '2022-01-18 09:36:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (511, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:44',
        '2022-01-18 09:36:44', '2022-01-18 09:36:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (512, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:46',
        '2022-01-18 09:36:46', '2022-01-18 09:36:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (513, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:48',
        '2022-01-18 09:36:48', '2022-01-18 09:36:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (514, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:50',
        '2022-01-18 09:36:50', '2022-01-18 09:36:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (515, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:52',
        '2022-01-18 09:36:52', '2022-01-18 09:36:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (516, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:54',
        '2022-01-18 09:36:54', '2022-01-18 09:36:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (517, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:56',
        '2022-01-18 09:36:56', '2022-01-18 09:36:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (518, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:36:58',
        '2022-01-18 09:36:58', '2022-01-18 09:36:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (519, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:00',
        '2022-01-18 09:37:00', '2022-01-18 09:37:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (520, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:02',
        '2022-01-18 09:37:02', '2022-01-18 09:37:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (521, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:04',
        '2022-01-18 09:37:04', '2022-01-18 09:37:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (522, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:06',
        '2022-01-18 09:37:06', '2022-01-18 09:37:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (523, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:08',
        '2022-01-18 09:37:08', '2022-01-18 09:37:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (524, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:10',
        '2022-01-18 09:37:10', '2022-01-18 09:37:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (525, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:12',
        '2022-01-18 09:37:12', '2022-01-18 09:37:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (526, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:14',
        '2022-01-18 09:37:14', '2022-01-18 09:37:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (527, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:16',
        '2022-01-18 09:37:16', '2022-01-18 09:37:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (528, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:18',
        '2022-01-18 09:37:18', '2022-01-18 09:37:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (529, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:20',
        '2022-01-18 09:37:20', '2022-01-18 09:37:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (530, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:22',
        '2022-01-18 09:37:22', '2022-01-18 09:37:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (531, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:24',
        '2022-01-18 09:37:24', '2022-01-18 09:37:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (532, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:26',
        '2022-01-18 09:37:26', '2022-01-18 09:37:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (533, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:28',
        '2022-01-18 09:37:28', '2022-01-18 09:37:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (534, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:30',
        '2022-01-18 09:37:30', '2022-01-18 09:37:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (535, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:32',
        '2022-01-18 09:37:32', '2022-01-18 09:37:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (536, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:34',
        '2022-01-18 09:37:34', '2022-01-18 09:37:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (537, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:36',
        '2022-01-18 09:37:36', '2022-01-18 09:37:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (538, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:38',
        '2022-01-18 09:37:38', '2022-01-18 09:37:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (539, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:40',
        '2022-01-18 09:37:40', '2022-01-18 09:37:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (540, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:42',
        '2022-01-18 09:37:42', '2022-01-18 09:37:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (541, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:44',
        '2022-01-18 09:37:44', '2022-01-18 09:37:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (542, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:46',
        '2022-01-18 09:37:46', '2022-01-18 09:37:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (543, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:48',
        '2022-01-18 09:37:48', '2022-01-18 09:37:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (544, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:50',
        '2022-01-18 09:37:50', '2022-01-18 09:37:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (545, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:52',
        '2022-01-18 09:37:52', '2022-01-18 09:37:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (546, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:54',
        '2022-01-18 09:37:54', '2022-01-18 09:37:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (547, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:56',
        '2022-01-18 09:37:56', '2022-01-18 09:37:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (548, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:37:58',
        '2022-01-18 09:37:58', '2022-01-18 09:37:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (549, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:00',
        '2022-01-18 09:38:00', '2022-01-18 09:38:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (550, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:02',
        '2022-01-18 09:38:02', '2022-01-18 09:38:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (551, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:04',
        '2022-01-18 09:38:04', '2022-01-18 09:38:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (552, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:06',
        '2022-01-18 09:38:06', '2022-01-18 09:38:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (553, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:08',
        '2022-01-18 09:38:08', '2022-01-18 09:38:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (554, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:10',
        '2022-01-18 09:38:10', '2022-01-18 09:38:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (555, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:12',
        '2022-01-18 09:38:12', '2022-01-18 09:38:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (556, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:14',
        '2022-01-18 09:38:14', '2022-01-18 09:38:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (557, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:16',
        '2022-01-18 09:38:16', '2022-01-18 09:38:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (558, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:18',
        '2022-01-18 09:38:18', '2022-01-18 09:38:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (559, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:20',
        '2022-01-18 09:38:20', '2022-01-18 09:38:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (560, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:22',
        '2022-01-18 09:38:22', '2022-01-18 09:38:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (561, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:24',
        '2022-01-18 09:38:24', '2022-01-18 09:38:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (562, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:26',
        '2022-01-18 09:38:26', '2022-01-18 09:38:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (563, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:28',
        '2022-01-18 09:38:28', '2022-01-18 09:38:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (564, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:30',
        '2022-01-18 09:38:30', '2022-01-18 09:38:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (565, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:32',
        '2022-01-18 09:38:32', '2022-01-18 09:38:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (566, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:34',
        '2022-01-18 09:38:34', '2022-01-18 09:38:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (567, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:36',
        '2022-01-18 09:38:36', '2022-01-18 09:38:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (568, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:38',
        '2022-01-18 09:38:38', '2022-01-18 09:38:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (569, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:40',
        '2022-01-18 09:38:40', '2022-01-18 09:38:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (570, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:42',
        '2022-01-18 09:38:42', '2022-01-18 09:38:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (571, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:44',
        '2022-01-18 09:38:44', '2022-01-18 09:38:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (572, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:38:46',
        '2022-01-18 09:38:46', '2022-01-18 09:38:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (573, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:48',
        '2022-01-18 09:38:48', '2022-01-18 09:38:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (574, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:50',
        '2022-01-18 09:38:50', '2022-01-18 09:38:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (575, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:52',
        '2022-01-18 09:38:52', '2022-01-18 09:38:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (576, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:54',
        '2022-01-18 09:38:54', '2022-01-18 09:38:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (577, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:56',
        '2022-01-18 09:38:56', '2022-01-18 09:38:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (578, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:38:58',
        '2022-01-18 09:38:58', '2022-01-18 09:38:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (579, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:00',
        '2022-01-18 09:39:00', '2022-01-18 09:39:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (580, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:02',
        '2022-01-18 09:39:02', '2022-01-18 09:39:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (581, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:04',
        '2022-01-18 09:39:04', '2022-01-18 09:39:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (582, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:06',
        '2022-01-18 09:39:06', '2022-01-18 09:39:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (583, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:08',
        '2022-01-18 09:39:08', '2022-01-18 09:39:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (584, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:39:10',
        '2022-01-18 09:39:10', '2022-01-18 09:39:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (585, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:12',
        '2022-01-18 09:39:12', '2022-01-18 09:39:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (586, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:14',
        '2022-01-18 09:39:14', '2022-01-18 09:39:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (587, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:16',
        '2022-01-18 09:39:16', '2022-01-18 09:39:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (588, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:18',
        '2022-01-18 09:39:18', '2022-01-18 09:39:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (589, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:20',
        '2022-01-18 09:39:20', '2022-01-18 09:39:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (590, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:22',
        '2022-01-18 09:39:22', '2022-01-18 09:39:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (591, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:24',
        '2022-01-18 09:39:24', '2022-01-18 09:39:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (592, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:26',
        '2022-01-18 09:39:26', '2022-01-18 09:39:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (593, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:28',
        '2022-01-18 09:39:28', '2022-01-18 09:39:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (594, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:30',
        '2022-01-18 09:39:30', '2022-01-18 09:39:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (595, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:32',
        '2022-01-18 09:39:32', '2022-01-18 09:39:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (596, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:34',
        '2022-01-18 09:39:34', '2022-01-18 09:39:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (597, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:36',
        '2022-01-18 09:39:36', '2022-01-18 09:39:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (598, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:38',
        '2022-01-18 09:39:38', '2022-01-18 09:39:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (599, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:40',
        '2022-01-18 09:39:40', '2022-01-18 09:39:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (600, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:42',
        '2022-01-18 09:39:42', '2022-01-18 09:39:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (601, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:44',
        '2022-01-18 09:39:44', '2022-01-18 09:39:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (602, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:46',
        '2022-01-18 09:39:46', '2022-01-18 09:39:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (603, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:48',
        '2022-01-18 09:39:48', '2022-01-18 09:39:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (604, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:50',
        '2022-01-18 09:39:50', '2022-01-18 09:39:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (605, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:52',
        '2022-01-18 09:39:52', '2022-01-18 09:39:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (606, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:54',
        '2022-01-18 09:39:54', '2022-01-18 09:39:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (607, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:39:56',
        '2022-01-18 09:39:56', '2022-01-18 09:39:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (608, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:39:58',
        '2022-01-18 09:39:58', '2022-01-18 09:39:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (609, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:00',
        '2022-01-18 09:40:00', '2022-01-18 09:40:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (610, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:02',
        '2022-01-18 09:40:02', '2022-01-18 09:40:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (611, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:04',
        '2022-01-18 09:40:04', '2022-01-18 09:40:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (612, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:06',
        '2022-01-18 09:40:06', '2022-01-18 09:40:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (613, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:08',
        '2022-01-18 09:40:08', '2022-01-18 09:40:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (614, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:10',
        '2022-01-18 09:40:10', '2022-01-18 09:40:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (615, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:12',
        '2022-01-18 09:40:12', '2022-01-18 09:40:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (616, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:14',
        '2022-01-18 09:40:14', '2022-01-18 09:40:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (617, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:16',
        '2022-01-18 09:40:16', '2022-01-18 09:40:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (618, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:18',
        '2022-01-18 09:40:18', '2022-01-18 09:40:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (619, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:20',
        '2022-01-18 09:40:20', '2022-01-18 09:40:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (620, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:22',
        '2022-01-18 09:40:22', '2022-01-18 09:40:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (621, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:24',
        '2022-01-18 09:40:24', '2022-01-18 09:40:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (622, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:26',
        '2022-01-18 09:40:26', '2022-01-18 09:40:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (623, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:28',
        '2022-01-18 09:40:28', '2022-01-18 09:40:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (624, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:30',
        '2022-01-18 09:40:30', '2022-01-18 09:40:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (625, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:32',
        '2022-01-18 09:40:32', '2022-01-18 09:40:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (626, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:34',
        '2022-01-18 09:40:34', '2022-01-18 09:40:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (627, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:36',
        '2022-01-18 09:40:36', '2022-01-18 09:40:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (628, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:38',
        '2022-01-18 09:40:38', '2022-01-18 09:40:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (629, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:40',
        '2022-01-18 09:40:40', '2022-01-18 09:40:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (630, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:42',
        '2022-01-18 09:40:42', '2022-01-18 09:40:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (631, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:44',
        '2022-01-18 09:40:44', '2022-01-18 09:40:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (632, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:46',
        '2022-01-18 09:40:46', '2022-01-18 09:40:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (633, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:48',
        '2022-01-18 09:40:48', '2022-01-18 09:40:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (634, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:50',
        '2022-01-18 09:40:50', '2022-01-18 09:40:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (635, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:52',
        '2022-01-18 09:40:52', '2022-01-18 09:40:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (636, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:54',
        '2022-01-18 09:40:54', '2022-01-18 09:40:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (637, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:56',
        '2022-01-18 09:40:56', '2022-01-18 09:40:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (638, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:40:58',
        '2022-01-18 09:40:58', '2022-01-18 09:40:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (639, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:00',
        '2022-01-18 09:41:00', '2022-01-18 09:41:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (640, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:41:02',
        '2022-01-18 09:41:02', '2022-01-18 09:41:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (641, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:04',
        '2022-01-18 09:41:04', '2022-01-18 09:41:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (642, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:06',
        '2022-01-18 09:41:06', '2022-01-18 09:41:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (643, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:08',
        '2022-01-18 09:41:08', '2022-01-18 09:41:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (644, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:10',
        '2022-01-18 09:41:10', '2022-01-18 09:41:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (645, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:12',
        '2022-01-18 09:41:12', '2022-01-18 09:41:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (646, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:41:14',
        '2022-01-18 09:41:14', '2022-01-18 09:41:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (647, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:16',
        '2022-01-18 09:41:16', '2022-01-18 09:41:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (648, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:18',
        '2022-01-18 09:41:18', '2022-01-18 09:41:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (649, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:20',
        '2022-01-18 09:41:20', '2022-01-18 09:41:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (650, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:22',
        '2022-01-18 09:41:22', '2022-01-18 09:41:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (651, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:24',
        '2022-01-18 09:41:24', '2022-01-18 09:41:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (652, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:26',
        '2022-01-18 09:41:26', '2022-01-18 09:41:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (653, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:28',
        '2022-01-18 09:41:28', '2022-01-18 09:41:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (654, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:30',
        '2022-01-18 09:41:30', '2022-01-18 09:41:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (655, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:32',
        '2022-01-18 09:41:32', '2022-01-18 09:41:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (656, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:34',
        '2022-01-18 09:41:34', '2022-01-18 09:41:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (657, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:36',
        '2022-01-18 09:41:36', '2022-01-18 09:41:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (658, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:38',
        '2022-01-18 09:41:38', '2022-01-18 09:41:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (659, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:40',
        '2022-01-18 09:41:40', '2022-01-18 09:41:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (660, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:42',
        '2022-01-18 09:41:42', '2022-01-18 09:41:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (661, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:44',
        '2022-01-18 09:41:44', '2022-01-18 09:41:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (662, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:46',
        '2022-01-18 09:41:46', '2022-01-18 09:41:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (663, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:48',
        '2022-01-18 09:41:48', '2022-01-18 09:41:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (664, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:50',
        '2022-01-18 09:41:50', '2022-01-18 09:41:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (665, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:52',
        '2022-01-18 09:41:52', '2022-01-18 09:41:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (666, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:54',
        '2022-01-18 09:41:54', '2022-01-18 09:41:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (667, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:56',
        '2022-01-18 09:41:56', '2022-01-18 09:41:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (668, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:41:58',
        '2022-01-18 09:41:58', '2022-01-18 09:41:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (669, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:00',
        '2022-01-18 09:42:00', '2022-01-18 09:42:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (670, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:02',
        '2022-01-18 09:42:02', '2022-01-18 09:42:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (671, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:04',
        '2022-01-18 09:42:04', '2022-01-18 09:42:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (672, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:06',
        '2022-01-18 09:42:06', '2022-01-18 09:42:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (673, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:08',
        '2022-01-18 09:42:08', '2022-01-18 09:42:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (674, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:10',
        '2022-01-18 09:42:10', '2022-01-18 09:42:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (675, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:12',
        '2022-01-18 09:42:12', '2022-01-18 09:42:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (676, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:14',
        '2022-01-18 09:42:14', '2022-01-18 09:42:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (677, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:16',
        '2022-01-18 09:42:16', '2022-01-18 09:42:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (678, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:18',
        '2022-01-18 09:42:18', '2022-01-18 09:42:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (679, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:20',
        '2022-01-18 09:42:20', '2022-01-18 09:42:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (680, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:22',
        '2022-01-18 09:42:22', '2022-01-18 09:42:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (681, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:24',
        '2022-01-18 09:42:24', '2022-01-18 09:42:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (682, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:26',
        '2022-01-18 09:42:26', '2022-01-18 09:42:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (683, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:28',
        '2022-01-18 09:42:28', '2022-01-18 09:42:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (684, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:30',
        '2022-01-18 09:42:30', '2022-01-18 09:42:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (685, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:42:32',
        '2022-01-18 09:42:32', '2022-01-18 09:42:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (686, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:34',
        '2022-01-18 09:42:34', '2022-01-18 09:42:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (687, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:36',
        '2022-01-18 09:42:36', '2022-01-18 09:42:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (688, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:42:38',
        '2022-01-18 09:42:38', '2022-01-18 09:42:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (689, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:40',
        '2022-01-18 09:42:40', '2022-01-18 09:42:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (690, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:42:42',
        '2022-01-18 09:42:42', '2022-01-18 09:42:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (691, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:44',
        '2022-01-18 09:42:44', '2022-01-18 09:42:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (692, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:46',
        '2022-01-18 09:42:46', '2022-01-18 09:42:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (693, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:42:48',
        '2022-01-18 09:42:48', '2022-01-18 09:42:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (694, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:50',
        '2022-01-18 09:42:50', '2022-01-18 09:42:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (695, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:52',
        '2022-01-18 09:42:52', '2022-01-18 09:42:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (696, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:54',
        '2022-01-18 09:42:54', '2022-01-18 09:42:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (697, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:42:56',
        '2022-01-18 09:42:56', '2022-01-18 09:42:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (698, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:42:58',
        '2022-01-18 09:42:58', '2022-01-18 09:42:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (699, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:00',
        '2022-01-18 09:43:00', '2022-01-18 09:43:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (700, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:02',
        '2022-01-18 09:43:02', '2022-01-18 09:43:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (701, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:04',
        '2022-01-18 09:43:04', '2022-01-18 09:43:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (702, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:43:06',
        '2022-01-18 09:43:06', '2022-01-18 09:43:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (703, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:08',
        '2022-01-18 09:43:08', '2022-01-18 09:43:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (704, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:43:10',
        '2022-01-18 09:43:10', '2022-01-18 09:43:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (705, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:43:12',
        '2022-01-18 09:43:12', '2022-01-18 09:43:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (706, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:14',
        '2022-01-18 09:43:14', '2022-01-18 09:43:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (707, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:43:16',
        '2022-01-18 09:43:16', '2022-01-18 09:43:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (708, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:18',
        '2022-01-18 09:43:18', '2022-01-18 09:43:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (709, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:20',
        '2022-01-18 09:43:20', '2022-01-18 09:43:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (710, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:43:22',
        '2022-01-18 09:43:22', '2022-01-18 09:43:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (711, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:24',
        '2022-01-18 09:43:24', '2022-01-18 09:43:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (712, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:43:26',
        '2022-01-18 09:43:26', '2022-01-18 09:43:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (713, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:28',
        '2022-01-18 09:43:28', '2022-01-18 09:43:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (714, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:30',
        '2022-01-18 09:43:30', '2022-01-18 09:43:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (715, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:32',
        '2022-01-18 09:43:32', '2022-01-18 09:43:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (716, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:34',
        '2022-01-18 09:43:34', '2022-01-18 09:43:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (717, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:36',
        '2022-01-18 09:43:36', '2022-01-18 09:43:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (718, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:38',
        '2022-01-18 09:43:38', '2022-01-18 09:43:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (719, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:43:40',
        '2022-01-18 09:43:40', '2022-01-18 09:43:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (720, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:42',
        '2022-01-18 09:43:42', '2022-01-18 09:43:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (721, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:44',
        '2022-01-18 09:43:44', '2022-01-18 09:43:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (722, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:46',
        '2022-01-18 09:43:46', '2022-01-18 09:43:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (723, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:48',
        '2022-01-18 09:43:48', '2022-01-18 09:43:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (724, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:50',
        '2022-01-18 09:43:50', '2022-01-18 09:43:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (725, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:43:52',
        '2022-01-18 09:43:52', '2022-01-18 09:43:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (726, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:54',
        '2022-01-18 09:43:54', '2022-01-18 09:43:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (727, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:56',
        '2022-01-18 09:43:56', '2022-01-18 09:43:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (728, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:43:58',
        '2022-01-18 09:43:58', '2022-01-18 09:43:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (729, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:00',
        '2022-01-18 09:44:00', '2022-01-18 09:44:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (730, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:02',
        '2022-01-18 09:44:02', '2022-01-18 09:44:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (731, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:04',
        '2022-01-18 09:44:04', '2022-01-18 09:44:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (732, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:06',
        '2022-01-18 09:44:06', '2022-01-18 09:44:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (733, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:08',
        '2022-01-18 09:44:08', '2022-01-18 09:44:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (734, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:10',
        '2022-01-18 09:44:10', '2022-01-18 09:44:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (735, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:12',
        '2022-01-18 09:44:12', '2022-01-18 09:44:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (736, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:14',
        '2022-01-18 09:44:14', '2022-01-18 09:44:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (737, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:16',
        '2022-01-18 09:44:16', '2022-01-18 09:44:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (738, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:18',
        '2022-01-18 09:44:18', '2022-01-18 09:44:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (739, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:20',
        '2022-01-18 09:44:20', '2022-01-18 09:44:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (740, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:22',
        '2022-01-18 09:44:22', '2022-01-18 09:44:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (741, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:24',
        '2022-01-18 09:44:24', '2022-01-18 09:44:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (742, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:26',
        '2022-01-18 09:44:26', '2022-01-18 09:44:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (743, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:28',
        '2022-01-18 09:44:28', '2022-01-18 09:44:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (744, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:30',
        '2022-01-18 09:44:30', '2022-01-18 09:44:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (745, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:32',
        '2022-01-18 09:44:32', '2022-01-18 09:44:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (746, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:34',
        '2022-01-18 09:44:34', '2022-01-18 09:44:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (747, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:36',
        '2022-01-18 09:44:36', '2022-01-18 09:44:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (748, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:38',
        '2022-01-18 09:44:38', '2022-01-18 09:44:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (749, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:40',
        '2022-01-18 09:44:40', '2022-01-18 09:44:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (750, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:42',
        '2022-01-18 09:44:42', '2022-01-18 09:44:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (751, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:44',
        '2022-01-18 09:44:44', '2022-01-18 09:44:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (752, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:46',
        '2022-01-18 09:44:46', '2022-01-18 09:44:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (753, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:48',
        '2022-01-18 09:44:48', '2022-01-18 09:44:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (754, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:50',
        '2022-01-18 09:44:50', '2022-01-18 09:44:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (755, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:52',
        '2022-01-18 09:44:52', '2022-01-18 09:44:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (756, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:54',
        '2022-01-18 09:44:54', '2022-01-18 09:44:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (757, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:56',
        '2022-01-18 09:44:56', '2022-01-18 09:44:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (758, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:44:58',
        '2022-01-18 09:44:58', '2022-01-18 09:44:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (759, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:00',
        '2022-01-18 09:45:00', '2022-01-18 09:45:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (760, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:02',
        '2022-01-18 09:45:02', '2022-01-18 09:45:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (761, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:45:04',
        '2022-01-18 09:45:04', '2022-01-18 09:45:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (762, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:06',
        '2022-01-18 09:45:06', '2022-01-18 09:45:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (763, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:08',
        '2022-01-18 09:45:08', '2022-01-18 09:45:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (764, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:10',
        '2022-01-18 09:45:10', '2022-01-18 09:45:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (765, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:12',
        '2022-01-18 09:45:12', '2022-01-18 09:45:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (766, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:14',
        '2022-01-18 09:45:14', '2022-01-18 09:45:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (767, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:16',
        '2022-01-18 09:45:16', '2022-01-18 09:45:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (768, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:18',
        '2022-01-18 09:45:18', '2022-01-18 09:45:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (769, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:20',
        '2022-01-18 09:45:20', '2022-01-18 09:45:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (770, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:22',
        '2022-01-18 09:45:22', '2022-01-18 09:45:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (771, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:24',
        '2022-01-18 09:45:24', '2022-01-18 09:45:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (772, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:26',
        '2022-01-18 09:45:26', '2022-01-18 09:45:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (773, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:28',
        '2022-01-18 09:45:28', '2022-01-18 09:45:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (774, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:30',
        '2022-01-18 09:45:30', '2022-01-18 09:45:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (775, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:32',
        '2022-01-18 09:45:32', '2022-01-18 09:45:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (776, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:45:34',
        '2022-01-18 09:45:34', '2022-01-18 09:45:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (777, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:36',
        '2022-01-18 09:45:36', '2022-01-18 09:45:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (778, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:38',
        '2022-01-18 09:45:38', '2022-01-18 09:45:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (779, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:40',
        '2022-01-18 09:45:40', '2022-01-18 09:45:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (780, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:45:42',
        '2022-01-18 09:45:42', '2022-01-18 09:45:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (781, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:44',
        '2022-01-18 09:45:44', '2022-01-18 09:45:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (782, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:46',
        '2022-01-18 09:45:46', '2022-01-18 09:45:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (783, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:48',
        '2022-01-18 09:45:48', '2022-01-18 09:45:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (784, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:50',
        '2022-01-18 09:45:50', '2022-01-18 09:45:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (785, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:52',
        '2022-01-18 09:45:52', '2022-01-18 09:45:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (786, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:54',
        '2022-01-18 09:45:54', '2022-01-18 09:45:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (787, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:56',
        '2022-01-18 09:45:56', '2022-01-18 09:45:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (788, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:45:58',
        '2022-01-18 09:45:58', '2022-01-18 09:45:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (789, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:00',
        '2022-01-18 09:46:00', '2022-01-18 09:46:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (790, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:02',
        '2022-01-18 09:46:02', '2022-01-18 09:46:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (791, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:04',
        '2022-01-18 09:46:04', '2022-01-18 09:46:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (792, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:06',
        '2022-01-18 09:46:06', '2022-01-18 09:46:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (793, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:08',
        '2022-01-18 09:46:08', '2022-01-18 09:46:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (794, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:10',
        '2022-01-18 09:46:10', '2022-01-18 09:46:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (795, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:12',
        '2022-01-18 09:46:12', '2022-01-18 09:46:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (796, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:14',
        '2022-01-18 09:46:14', '2022-01-18 09:46:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (797, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:16',
        '2022-01-18 09:46:16', '2022-01-18 09:46:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (798, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:18',
        '2022-01-18 09:46:18', '2022-01-18 09:46:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (799, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:20',
        '2022-01-18 09:46:20', '2022-01-18 09:46:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (800, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:22',
        '2022-01-18 09:46:22', '2022-01-18 09:46:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (801, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:24',
        '2022-01-18 09:46:24', '2022-01-18 09:46:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (802, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:26',
        '2022-01-18 09:46:26', '2022-01-18 09:46:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (803, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:28',
        '2022-01-18 09:46:28', '2022-01-18 09:46:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (804, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:30',
        '2022-01-18 09:46:30', '2022-01-18 09:46:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (805, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:32',
        '2022-01-18 09:46:32', '2022-01-18 09:46:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (806, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:34',
        '2022-01-18 09:46:34', '2022-01-18 09:46:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (807, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:36',
        '2022-01-18 09:46:36', '2022-01-18 09:46:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (808, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:38',
        '2022-01-18 09:46:38', '2022-01-18 09:46:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (809, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:40',
        '2022-01-18 09:46:40', '2022-01-18 09:46:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (810, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:42',
        '2022-01-18 09:46:42', '2022-01-18 09:46:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (811, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:44',
        '2022-01-18 09:46:44', '2022-01-18 09:46:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (812, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:46',
        '2022-01-18 09:46:46', '2022-01-18 09:46:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (813, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:48',
        '2022-01-18 09:46:48', '2022-01-18 09:46:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (814, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:50',
        '2022-01-18 09:46:50', '2022-01-18 09:46:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (815, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:52',
        '2022-01-18 09:46:52', '2022-01-18 09:46:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (816, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:54',
        '2022-01-18 09:46:54', '2022-01-18 09:46:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (817, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:56',
        '2022-01-18 09:46:56', '2022-01-18 09:46:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (818, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:46:58',
        '2022-01-18 09:46:58', '2022-01-18 09:46:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (819, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:00',
        '2022-01-18 09:47:00', '2022-01-18 09:47:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (820, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:02',
        '2022-01-18 09:47:02', '2022-01-18 09:47:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (821, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:04',
        '2022-01-18 09:47:04', '2022-01-18 09:47:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (822, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:06',
        '2022-01-18 09:47:06', '2022-01-18 09:47:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (823, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:08',
        '2022-01-18 09:47:08', '2022-01-18 09:47:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (824, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:10',
        '2022-01-18 09:47:10', '2022-01-18 09:47:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (825, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:12',
        '2022-01-18 09:47:12', '2022-01-18 09:47:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (826, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:14',
        '2022-01-18 09:47:14', '2022-01-18 09:47:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (827, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:16',
        '2022-01-18 09:47:16', '2022-01-18 09:47:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (828, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:18',
        '2022-01-18 09:47:18', '2022-01-18 09:47:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (829, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:47:20',
        '2022-01-18 09:47:20', '2022-01-18 09:47:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (830, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:22',
        '2022-01-18 09:47:22', '2022-01-18 09:47:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (831, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:24',
        '2022-01-18 09:47:24', '2022-01-18 09:47:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (832, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:26',
        '2022-01-18 09:47:26', '2022-01-18 09:47:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (833, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:28',
        '2022-01-18 09:47:28', '2022-01-18 09:47:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (834, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:30',
        '2022-01-18 09:47:30', '2022-01-18 09:47:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (835, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:47:32',
        '2022-01-18 09:47:32', '2022-01-18 09:47:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (836, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:34',
        '2022-01-18 09:47:34', '2022-01-18 09:47:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (837, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:36',
        '2022-01-18 09:47:36', '2022-01-18 09:47:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (838, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:47:38',
        '2022-01-18 09:47:38', '2022-01-18 09:47:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (839, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:40',
        '2022-01-18 09:47:40', '2022-01-18 09:47:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (840, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:42',
        '2022-01-18 09:47:42', '2022-01-18 09:47:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (841, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:44',
        '2022-01-18 09:47:44', '2022-01-18 09:47:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (842, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:46',
        '2022-01-18 09:47:46', '2022-01-18 09:47:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (843, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:48',
        '2022-01-18 09:47:48', '2022-01-18 09:47:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (844, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:50',
        '2022-01-18 09:47:50', '2022-01-18 09:47:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (845, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:52',
        '2022-01-18 09:47:52', '2022-01-18 09:47:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (846, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:54',
        '2022-01-18 09:47:54', '2022-01-18 09:47:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (847, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:56',
        '2022-01-18 09:47:56', '2022-01-18 09:47:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (848, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:47:58',
        '2022-01-18 09:47:58', '2022-01-18 09:47:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (849, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:00',
        '2022-01-18 09:48:00', '2022-01-18 09:48:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (850, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:02',
        '2022-01-18 09:48:02', '2022-01-18 09:48:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (851, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:04',
        '2022-01-18 09:48:04', '2022-01-18 09:48:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (852, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:06',
        '2022-01-18 09:48:06', '2022-01-18 09:48:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (853, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:08',
        '2022-01-18 09:48:08', '2022-01-18 09:48:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (854, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:10',
        '2022-01-18 09:48:10', '2022-01-18 09:48:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (855, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:12',
        '2022-01-18 09:48:12', '2022-01-18 09:48:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (856, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:14',
        '2022-01-18 09:48:14', '2022-01-18 09:48:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (857, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:16',
        '2022-01-18 09:48:16', '2022-01-18 09:48:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (858, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:18',
        '2022-01-18 09:48:18', '2022-01-18 09:48:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (859, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:20',
        '2022-01-18 09:48:20', '2022-01-18 09:48:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (860, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:22',
        '2022-01-18 09:48:22', '2022-01-18 09:48:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (861, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:24',
        '2022-01-18 09:48:24', '2022-01-18 09:48:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (862, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:26',
        '2022-01-18 09:48:26', '2022-01-18 09:48:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (863, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:28',
        '2022-01-18 09:48:28', '2022-01-18 09:48:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (864, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:30',
        '2022-01-18 09:48:30', '2022-01-18 09:48:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (865, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:32',
        '2022-01-18 09:48:32', '2022-01-18 09:48:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (866, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:34',
        '2022-01-18 09:48:34', '2022-01-18 09:48:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (867, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:36',
        '2022-01-18 09:48:36', '2022-01-18 09:48:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (868, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:38',
        '2022-01-18 09:48:38', '2022-01-18 09:48:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (869, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:40',
        '2022-01-18 09:48:40', '2022-01-18 09:48:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (870, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:42',
        '2022-01-18 09:48:42', '2022-01-18 09:48:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (871, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:44',
        '2022-01-18 09:48:44', '2022-01-18 09:48:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (872, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:46',
        '2022-01-18 09:48:46', '2022-01-18 09:48:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (873, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:48',
        '2022-01-18 09:48:48', '2022-01-18 09:48:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (874, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:50',
        '2022-01-18 09:48:50', '2022-01-18 09:48:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (875, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:52',
        '2022-01-18 09:48:52', '2022-01-18 09:48:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (876, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:54',
        '2022-01-18 09:48:54', '2022-01-18 09:48:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (877, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:56',
        '2022-01-18 09:48:56', '2022-01-18 09:48:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (878, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:48:58',
        '2022-01-18 09:48:58', '2022-01-18 09:48:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (879, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:00',
        '2022-01-18 09:49:00', '2022-01-18 09:49:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (880, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:02',
        '2022-01-18 09:49:02', '2022-01-18 09:49:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (881, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:04',
        '2022-01-18 09:49:04', '2022-01-18 09:49:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (882, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:06',
        '2022-01-18 09:49:06', '2022-01-18 09:49:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (883, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:49:08',
        '2022-01-18 09:49:08', '2022-01-18 09:49:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (884, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:10',
        '2022-01-18 09:49:10', '2022-01-18 09:49:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (885, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:12',
        '2022-01-18 09:49:12', '2022-01-18 09:49:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (886, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:14',
        '2022-01-18 09:49:14', '2022-01-18 09:49:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (887, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:49:16',
        '2022-01-18 09:49:16', '2022-01-18 09:49:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (888, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:18',
        '2022-01-18 09:49:18', '2022-01-18 09:49:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (889, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:20',
        '2022-01-18 09:49:20', '2022-01-18 09:49:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (890, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:22',
        '2022-01-18 09:49:22', '2022-01-18 09:49:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (891, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:24',
        '2022-01-18 09:49:24', '2022-01-18 09:49:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (892, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:26',
        '2022-01-18 09:49:26', '2022-01-18 09:49:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (893, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:28',
        '2022-01-18 09:49:28', '2022-01-18 09:49:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (894, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:30',
        '2022-01-18 09:49:30', '2022-01-18 09:49:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (895, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:32',
        '2022-01-18 09:49:32', '2022-01-18 09:49:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (896, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:34',
        '2022-01-18 09:49:34', '2022-01-18 09:49:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (897, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:36',
        '2022-01-18 09:49:36', '2022-01-18 09:49:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (898, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:38',
        '2022-01-18 09:49:38', '2022-01-18 09:49:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (899, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:40',
        '2022-01-18 09:49:40', '2022-01-18 09:49:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (900, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:42',
        '2022-01-18 09:49:42', '2022-01-18 09:49:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (901, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:44',
        '2022-01-18 09:49:44', '2022-01-18 09:49:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (902, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:46',
        '2022-01-18 09:49:46', '2022-01-18 09:49:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (903, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:48',
        '2022-01-18 09:49:48', '2022-01-18 09:49:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (904, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:50',
        '2022-01-18 09:49:50', '2022-01-18 09:49:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (905, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:52',
        '2022-01-18 09:49:52', '2022-01-18 09:49:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (906, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:54',
        '2022-01-18 09:49:54', '2022-01-18 09:49:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (907, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:56',
        '2022-01-18 09:49:56', '2022-01-18 09:49:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (908, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:49:58',
        '2022-01-18 09:49:58', '2022-01-18 09:49:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (909, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：2毫秒', '0', '', '2022-01-18 09:51:32',
        '2022-01-18 09:51:32', '2022-01-18 09:51:31', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (910, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:51:32',
        '2022-01-18 09:51:32', '2022-01-18 09:51:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (911, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:51:34',
        '2022-01-18 09:51:34', '2022-01-18 09:51:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (912, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:51:36',
        '2022-01-18 09:51:36', '2022-01-18 09:51:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (913, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:51:38',
        '2022-01-18 09:51:38', '2022-01-18 09:51:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (914, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:51:40',
        '2022-01-18 09:51:40', '2022-01-18 09:51:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (915, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:51:42',
        '2022-01-18 09:51:42', '2022-01-18 09:51:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (916, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:51:44',
        '2022-01-18 09:51:44', '2022-01-18 09:51:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (917, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:22',
        '2022-01-18 09:53:22', '2022-01-18 09:53:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (918, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:53:24',
        '2022-01-18 09:53:24', '2022-01-18 09:53:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (919, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:26',
        '2022-01-18 09:53:26', '2022-01-18 09:53:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (920, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:28',
        '2022-01-18 09:53:28', '2022-01-18 09:53:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (921, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:30',
        '2022-01-18 09:53:30', '2022-01-18 09:53:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (922, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:53:32',
        '2022-01-18 09:53:32', '2022-01-18 09:53:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (923, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:34',
        '2022-01-18 09:53:34', '2022-01-18 09:53:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (924, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:36',
        '2022-01-18 09:53:36', '2022-01-18 09:53:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (925, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:38',
        '2022-01-18 09:53:38', '2022-01-18 09:53:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (926, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:40',
        '2022-01-18 09:53:40', '2022-01-18 09:53:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (927, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:42',
        '2022-01-18 09:53:42', '2022-01-18 09:53:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (928, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:44',
        '2022-01-18 09:53:44', '2022-01-18 09:53:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (929, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:46',
        '2022-01-18 09:53:46', '2022-01-18 09:53:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (930, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:48',
        '2022-01-18 09:53:48', '2022-01-18 09:53:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (931, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:50',
        '2022-01-18 09:53:50', '2022-01-18 09:53:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (932, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:52',
        '2022-01-18 09:53:52', '2022-01-18 09:53:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (933, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:54',
        '2022-01-18 09:53:54', '2022-01-18 09:53:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (934, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:56',
        '2022-01-18 09:53:56', '2022-01-18 09:53:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (935, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:53:58',
        '2022-01-18 09:53:58', '2022-01-18 09:53:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (936, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:00',
        '2022-01-18 09:54:00', '2022-01-18 09:54:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (937, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:02',
        '2022-01-18 09:54:02', '2022-01-18 09:54:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (938, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:04',
        '2022-01-18 09:54:04', '2022-01-18 09:54:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (939, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:06',
        '2022-01-18 09:54:06', '2022-01-18 09:54:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (940, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:08',
        '2022-01-18 09:54:08', '2022-01-18 09:54:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (941, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:10',
        '2022-01-18 09:54:10', '2022-01-18 09:54:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (942, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:12',
        '2022-01-18 09:54:12', '2022-01-18 09:54:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (943, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:14',
        '2022-01-18 09:54:14', '2022-01-18 09:54:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (944, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:16',
        '2022-01-18 09:54:16', '2022-01-18 09:54:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (945, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:18',
        '2022-01-18 09:54:18', '2022-01-18 09:54:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (946, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:54:20',
        '2022-01-18 09:54:20', '2022-01-18 09:54:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (947, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:22',
        '2022-01-18 09:54:22', '2022-01-18 09:54:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (948, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:24',
        '2022-01-18 09:54:24', '2022-01-18 09:54:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (949, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:26',
        '2022-01-18 09:54:26', '2022-01-18 09:54:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (950, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:28',
        '2022-01-18 09:54:28', '2022-01-18 09:54:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (951, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:30',
        '2022-01-18 09:54:30', '2022-01-18 09:54:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (952, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:32',
        '2022-01-18 09:54:32', '2022-01-18 09:54:32', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (953, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:34',
        '2022-01-18 09:54:34', '2022-01-18 09:54:34', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (954, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:36',
        '2022-01-18 09:54:36', '2022-01-18 09:54:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (955, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:38',
        '2022-01-18 09:54:38', '2022-01-18 09:54:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (956, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:40',
        '2022-01-18 09:54:40', '2022-01-18 09:54:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (957, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:42',
        '2022-01-18 09:54:42', '2022-01-18 09:54:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (958, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:44',
        '2022-01-18 09:54:44', '2022-01-18 09:54:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (959, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:46',
        '2022-01-18 09:54:46', '2022-01-18 09:54:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (960, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:48',
        '2022-01-18 09:54:48', '2022-01-18 09:54:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (961, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:54:50',
        '2022-01-18 09:54:50', '2022-01-18 09:54:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (962, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:52',
        '2022-01-18 09:54:52', '2022-01-18 09:54:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (963, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:54',
        '2022-01-18 09:54:54', '2022-01-18 09:54:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (964, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:56',
        '2022-01-18 09:54:56', '2022-01-18 09:54:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (965, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:54:58',
        '2022-01-18 09:54:58', '2022-01-18 09:54:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (966, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:55:00',
        '2022-01-18 09:55:00', '2022-01-18 09:55:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (967, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：2毫秒', '0', '', '2022-01-18 09:56:45',
        '2022-01-18 09:56:45', '2022-01-18 09:56:45', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (968, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:56:45',
        '2022-01-18 09:56:45', '2022-01-18 09:56:45', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (969, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:56:46',
        '2022-01-18 09:56:46', '2022-01-18 09:56:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (970, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:56:48',
        '2022-01-18 09:56:48', '2022-01-18 09:56:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (971, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:56:50',
        '2022-01-18 09:56:50', '2022-01-18 09:56:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (972, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:56:52',
        '2022-01-18 09:56:52', '2022-01-18 09:56:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (973, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:56:54',
        '2022-01-18 09:56:54', '2022-01-18 09:56:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (974, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:56:56',
        '2022-01-18 09:56:56', '2022-01-18 09:56:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (975, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:56:58',
        '2022-01-18 09:56:58', '2022-01-18 09:56:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (976, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:00',
        '2022-01-18 09:57:00', '2022-01-18 09:57:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (977, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:02',
        '2022-01-18 09:57:02', '2022-01-18 09:57:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (978, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:04',
        '2022-01-18 09:57:04', '2022-01-18 09:57:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (979, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:57:06',
        '2022-01-18 09:57:06', '2022-01-18 09:57:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (980, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:08',
        '2022-01-18 09:57:08', '2022-01-18 09:57:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (981, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:10',
        '2022-01-18 09:57:10', '2022-01-18 09:57:10', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (982, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:12',
        '2022-01-18 09:57:12', '2022-01-18 09:57:12', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (983, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:14',
        '2022-01-18 09:57:14', '2022-01-18 09:57:14', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (984, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:57:16',
        '2022-01-18 09:57:16', '2022-01-18 09:57:16', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (985, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:18',
        '2022-01-18 09:57:18', '2022-01-18 09:57:18', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (986, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:20',
        '2022-01-18 09:57:20', '2022-01-18 09:57:20', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (987, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:57:22',
        '2022-01-18 09:57:22', '2022-01-18 09:57:22', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (988, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:57:24',
        '2022-01-18 09:57:24', '2022-01-18 09:57:24', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (989, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:26',
        '2022-01-18 09:57:26', '2022-01-18 09:57:26', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (990, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:28',
        '2022-01-18 09:57:28', '2022-01-18 09:57:28', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (991, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:30',
        '2022-01-18 09:57:30', '2022-01-18 09:57:30', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (992, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：2毫秒', '0', '', '2022-01-18 09:57:47',
        '2022-01-18 09:57:47', '2022-01-18 09:57:47', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (993, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:47',
        '2022-01-18 09:57:47', '2022-01-18 09:57:47', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (994, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:48',
        '2022-01-18 09:57:48', '2022-01-18 09:57:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (995, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:50',
        '2022-01-18 09:57:50', '2022-01-18 09:57:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (996, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:57:52',
        '2022-01-18 09:57:52', '2022-01-18 09:57:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (997, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:57:54',
        '2022-01-18 09:57:54', '2022-01-18 09:57:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (998, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:56',
        '2022-01-18 09:57:56', '2022-01-18 09:57:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (999, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:57:58',
        '2022-01-18 09:57:58', '2022-01-18 09:57:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1000, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:58:00',
        '2022-01-18 09:58:00', '2022-01-18 09:58:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1001, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 09:58:02',
        '2022-01-18 09:58:02', '2022-01-18 09:58:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1002, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:58:04',
        '2022-01-18 09:58:04', '2022-01-18 09:58:04', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1003, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:58:06',
        '2022-01-18 09:58:06', '2022-01-18 09:58:06', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1004, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 09:58:08',
        '2022-01-18 09:58:08', '2022-01-18 09:58:08', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1005, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 10:13:36',
        '2022-01-18 10:13:36', '2022-01-18 10:13:36', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1006, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:13:38',
        '2022-01-18 10:13:38', '2022-01-18 10:13:38', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1007, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:13:40',
        '2022-01-18 10:13:40', '2022-01-18 10:13:40', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1008, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 10:13:42',
        '2022-01-18 10:13:42', '2022-01-18 10:13:42', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1009, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 10:13:44',
        '2022-01-18 10:13:44', '2022-01-18 10:13:44', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1010, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:13:46',
        '2022-01-18 10:13:46', '2022-01-18 10:13:46', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1011, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:13:48',
        '2022-01-18 10:13:48', '2022-01-18 10:13:48', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1012, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:13:50',
        '2022-01-18 10:13:50', '2022-01-18 10:13:50', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1013, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:13:52',
        '2022-01-18 10:13:52', '2022-01-18 10:13:52', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1014, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:13:54',
        '2022-01-18 10:13:54', '2022-01-18 10:13:54', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1015, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:13:56',
        '2022-01-18 10:13:56', '2022-01-18 10:13:56', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1016, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：1毫秒', '0', '', '2022-01-18 10:13:58',
        '2022-01-18 10:13:58', '2022-01-18 10:13:58', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1017, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:14:00',
        '2022-01-18 10:14:00', '2022-01-18 10:14:00', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1018, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:14:02',
        '2022-01-18 10:14:02', '2022-01-18 10:14:02', NULL, 0);
INSERT INTO `sys_qrtz_job_log`
VALUES (1019, '每分钟更新数据', 'DEFAULT', 'cmsTask.oneParams(\'ry\')', '每分钟更新数据 总共耗时：0毫秒', '0', '', '2022-01-18 10:14:04',
        '2022-01-18 10:14:04', '2022-01-18 10:14:04', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`           varchar(30)  NOT NULL COMMENT '角色名称',
    `role_key`            varchar(100) NOT NULL COMMENT '角色权限字符串',
    `role_sort`           int(4) NOT NULL COMMENT '显示顺序',
    `data_scope`          char(1)      DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
    `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
    `status`              char(1)      NOT NULL COMMENT '角色状态（0正常 1停用）',
    `del_flag`            char(1)      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`           varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`         datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`           varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`         datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`              varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2021-11-22 09:57:54', '', NULL, '超级管理员');
INSERT INTO `sys_role`
VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2021-11-22 09:57:54', 'admin', '2021-12-26 16:13:12',
        '普通角色');
INSERT INTO `sys_role`
VALUES (3, '32112312312312', '31231231313123131231312', 3, '1', 1, 1, '0', '0', 'admin', '2021-12-28 11:22:33', '',
        NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`
(
    `id`      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `role_id` (`role_id`,`dept_id`),
    KEY       `dept_id` (`dept_id`),
    CONSTRAINT `sys_role_dept_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `sys_role_dept_ibfk_2` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept`
VALUES (1, 2, 100);
INSERT INTO `sys_role_dept`
VALUES (2, 2, 101);
INSERT INTO `sys_role_dept`
VALUES (3, 2, 105);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`      bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `role_id` (`role_id`,`menu_id`),
    KEY       `menu_id` (`menu_id`),
    CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu`
VALUES (85, 2, 2);
INSERT INTO `sys_role_menu`
VALUES (100, 2, 3);
INSERT INTO `sys_role_menu`
VALUES (86, 2, 109);
INSERT INTO `sys_role_menu`
VALUES (90, 2, 110);
INSERT INTO `sys_role_menu`
VALUES (97, 2, 111);
INSERT INTO `sys_role_menu`
VALUES (98, 2, 112);
INSERT INTO `sys_role_menu`
VALUES (99, 2, 113);
INSERT INTO `sys_role_menu`
VALUES (101, 2, 114);
INSERT INTO `sys_role_menu`
VALUES (102, 2, 115);
INSERT INTO `sys_role_menu`
VALUES (109, 2, 116);
INSERT INTO `sys_role_menu`
VALUES (110, 3, 1);
INSERT INTO `sys_role_menu`
VALUES (111, 3, 100);
INSERT INTO `sys_role_menu`
VALUES (119, 3, 101);
INSERT INTO `sys_role_menu`
VALUES (125, 3, 102);
INSERT INTO `sys_role_menu`
VALUES (130, 3, 103);
INSERT INTO `sys_role_menu`
VALUES (135, 3, 104);
INSERT INTO `sys_role_menu`
VALUES (141, 3, 105);
INSERT INTO `sys_role_menu`
VALUES (147, 3, 106);
INSERT INTO `sys_role_menu`
VALUES (153, 3, 107);
INSERT INTO `sys_role_menu`
VALUES (158, 3, 108);
INSERT INTO `sys_role_menu`
VALUES (159, 3, 500);
INSERT INTO `sys_role_menu`
VALUES (163, 3, 501);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `dept_id`     bigint(20) DEFAULT NULL COMMENT '部门ID',
    `user_name`   varchar(30) NOT NULL COMMENT '用户账号',
    `nick_name`   varchar(30) NOT NULL COMMENT '用户昵称',
    `user_type`   varchar(2)   DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `email`       varchar(50)  DEFAULT '' COMMENT '用户邮箱',
    `phonenumber` varchar(11)  DEFAULT '' COMMENT '手机号码',
    `sex`         char(1)      DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar`      varchar(100) DEFAULT '' COMMENT '头像地址',
    `password`    varchar(100) DEFAULT '' COMMENT '密码',
    `status`      char(1)      DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag`    char(1)      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`    varchar(128) DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime     DEFAULT NULL COMMENT '最后登录时间',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`),
    KEY           `dept_id` (`dept_id`),
    CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user`
VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2022-01-18 09:53:02',
        'admin', '2021-11-22 09:57:54', '', '2022-01-18 09:53:01', '管理员');
INSERT INTO `sys_user`
VALUES (2, 109, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '0', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2021-11-22 09:57:54',
        'admin', '2021-11-22 09:57:54', 'admin', '2022-01-12 13:36:20', '测试员');
INSERT INTO `sys_user`
VALUES (3, 100, '32131233', '321', '00', '', '18511112222', '0', '', '', '0', '0', '', NULL, 'admin', NULL, 'admin',
        NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`
(
    `id`      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`,`post_id`),
    KEY       `post_id` (`post_id`),
    CONSTRAINT `sys_user_post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `sys_user_post_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `sys_post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_post`
VALUES (1, 1, 1);
INSERT INTO `sys_user_post`
VALUES (6, 2, 2);
INSERT INTO `sys_user_post`
VALUES (9, 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`,`role_id`),
    KEY       `role_id` (`role_id`),
    CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role`
VALUES (1, 1, 1);
INSERT INTO `sys_user_role`
VALUES (6, 2, 2);
INSERT INTO `sys_user_role`
VALUES (10, 3, 3);
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
