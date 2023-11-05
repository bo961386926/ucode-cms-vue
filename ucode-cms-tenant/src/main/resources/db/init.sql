CREATE TABLE `tb_db_source`
(
    `tenant_id`         varchar(64) NOT NULL DEFAULT '' COMMENT '租户ID',
    `driver_class_name` varchar(128) CHARACTER DEFAULT NULL COMMENT '驱动名称',
    `url`               varchar(256) CHARACTER DEFAULT NULL COMMENT 'url',
    `username`          varchar(64) CHARACTER DEFAULT NULL COMMENT '数据库连接用户名',
    `password`          varchar(256) CHARACTER DEFAULT NULL COMMENT '数据库连接密码',
    PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB;
