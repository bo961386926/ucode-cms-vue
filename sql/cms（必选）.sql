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
VALUES (100, 0, '0', '依依科技', 0, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54', '', NULL);
INSERT INTO `sys_dept`
VALUES (101, 100, '0,100', '深圳总公司', 1, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54', '',
        NULL);
INSERT INTO `sys_dept`
VALUES (102, 100, '0,100', '长沙分公司', 2, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54', '',
        NULL);
INSERT INTO `sys_dept`
VALUES (103, 101, '0,100,101', '研发部门', 1, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (104, 101, '0,100,101', '市场部门', 2, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (105, 101, '0,100,101', '测试部门', 3, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (106, 101, '0,100,101', '财务部门', 4, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (107, 101, '0,100,101', '运维部门', 5, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (108, 102, '0,100,102', '市场部门', 1, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
        '', NULL);
INSERT INTO `sys_dept`
VALUES (109, 102, '0,100,102', '财务部门', 2, '依依', '15888888888', 'yy@qq.com', '0', '0', 'admin', '2021-11-22 09:57:54',
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
VALUES (1, '温馨提醒：2018-07-01 依依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2021-11-22 09:57:56', '',
        NULL, '管理员');
INSERT INTO `sys_notice`
VALUES (2, '维护通知：2018-07-01 依依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2021-11-22 09:57:56', '', NULL,
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
VALUES (1, 103, 'admin', '依依', '00', 'yy@163.com', '15888888888', '1', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2022-01-18 09:53:02',
        'admin', '2021-11-22 09:57:54', '', '2022-01-18 09:53:01', '管理员');
INSERT INTO `sys_user`
VALUES (2, 109, 'ry', '依依', '00', 'yy@qq.com', '15666666666', '0', '',
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
