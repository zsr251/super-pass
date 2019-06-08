
# Dump of table tb_app_account
# ------------------------------------------------------------

CREATE TABLE `tb_app_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `app_name` varchar(50) DEFAULT NULL COMMENT '应用名',
  `show_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '账户名',
  `password` varchar(300) NOT NULL DEFAULT '' COMMENT '密码密文',
  `url` varchar(300) DEFAULT NULL COMMENT 'URL',
  `remark` varchar(800) DEFAULT NULL COMMENT '备注最多100字',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 -1 永久删除 0 废纸篓 1 有效',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用';



# Dump of table tb_card_account
# ------------------------------------------------------------

CREATE TABLE `tb_card_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `icon` varchar(50) DEFAULT '' COMMENT '图标',
  `card_name` varchar(50) DEFAULT NULL COMMENT '卡片名',
  `show_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `show_card_no` varchar(50) NOT NULL DEFAULT '' COMMENT '展示的卡号',
  `card_no` varchar(300) NOT NULL DEFAULT '' COMMENT '卡号密文',
  `password` varchar(300) DEFAULT NULL COMMENT '密码密文',
  `remark` varchar(800) DEFAULT NULL COMMENT '备注 最多100字',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 -1 永久删除 0 废纸篓 1 正常',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卡号';



# Dump of table tb_login_log
# ------------------------------------------------------------

CREATE TABLE `tb_login_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `login_type` int(11) DEFAULT NULL COMMENT '登录方式 1 密码 2 手机 3 邮箱 4 指纹',
  `platform` int(11) DEFAULT NULL COMMENT '平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面',
  `agent` varchar(500) DEFAULT NULL COMMENT '客户端数据',
  `ip` varchar(50) DEFAULT NULL COMMENT '登录IP',
  `token` varchar(100) DEFAULT '' COMMENT '登录token',
  `status` int(11) DEFAULT NULL COMMENT '0 登录失败 1 登录成功',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';


# Dump of table tb_memo
# ------------------------------------------------------------

CREATE TABLE `tb_memo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `show_title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` varchar(5000) DEFAULT NULL COMMENT '内容密文 800字符',
  `status` int(11) DEFAULT '1' COMMENT '状态 -1 永久删除 0 废纸篓 1 正常',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='备忘录';



# Dump of table tb_operate_log
# ------------------------------------------------------------

CREATE TABLE `tb_operate_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `platform` int(11) DEFAULT NULL COMMENT '平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面',
  `ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `operate_type` int(11) NOT NULL COMMENT '操作类型 1 查看 2 新增 3 修改 4 删除',
  `data_type` int(11) DEFAULT NULL COMMENT '数据类型 1 应用数据 2 卡片数据 3 备忘录',
  `data_id` int(11) DEFAULT NULL COMMENT '记录ID',
  `remark` varchar(500) DEFAULT '' COMMENT '操作备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_operate_type` (`operate_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table tb_show_type
# ------------------------------------------------------------

CREATE TABLE `tb_show_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_type` int(11) NOT NULL DEFAULT '1' COMMENT '1 账户类 2 卡类',
  `icon` varchar(50) NOT NULL DEFAULT '' COMMENT '图标标示',
  `default_name` varchar(50) NOT NULL DEFAULT '' COMMENT '应用名',
  `default_url` varchar(300) DEFAULT NULL COMMENT 'URL',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='默认图标类型';



# Dump of table tb_user
# ------------------------------------------------------------

CREATE TABLE `tb_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `super_pass` varchar(32) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0 账户锁定 1 正常 2 通过认证',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_user_name` (`user_name`),
  KEY `idx_phone` (`super_pass`),
  KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';