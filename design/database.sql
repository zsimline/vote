-- Luming Remote Voting System

CREATE DATABASE `vote`;
USE vote;

-- 用户信息表
CREATE TABLE `user`
(
  `id` INT UNSIGNED AUTO_INCREMENT,     -- 用户ID
  `email` VARCHAR(191) UNIQUE NOT NULL, -- 电子邮件(作账号)
  `password` CHAR(32) NOT NULL,         -- 用户密码
  `organization` varchar(45) NOT NULL,  -- 所属组织
  `is_staff` TINYINT(1)  DEFAULT 0,     -- 是否为管理人员
  `is_active` TINYINT(1) DEFAULT 1,     -- 账号是否可用
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4;

-- 活动信息表
CREATE TABLE `activity`
(
  `id` CHAR(32) NOT NULL,     -- 活动ID
  `title` VARCHAR(40) NOT NULL,   -- 活动标题
  `publisher` INT UNSIGNED NOT NULL,    -- 发布者ID
  `suffix` CHAR(3) NOT NULL,    -- 条目称谓
  `quantifier` CHAR(1) NOT NULL,    -- 条目量词
  `summary` TEXT NOT NULL,    -- 活动简介（富文本）
  `vote_time_start` DATETIME NOT NULL,    -- 投票开始时间
  `vote_time_end` DATETIME NOT NULL,    -- 投票截止时间
  `apply_time_start` DATETIME NOT NULL,   -- 报名开始时间
  `apply_time_end` DATETIME NOT NULL,   -- 报名截止时间
  `maximum` TINYINT UNSIGNED NOT NULL,    -- 单次最多选择
  `sum_entry` SMALLINT	UNSIGNED DEFAULT 0,   -- 条目总数
  `sum_voted` INT UNSIGNED DEFAULT 0,   -- 投票总数
  `sum_visited` INT UNSIGNED DEFAULT 0,   -- 访问总数
  `img_main` CHAR(56) NOT NULL,   -- 宣传图片地址
  `options` VARCHAR(300) NOT NULL,    -- 报名选项
  `external_apply` TINYINT(1) NOT NULL,   -- 是否允许外部报名
  `reason_length` TINYINT UNSIGNED NOT NULL,    --  理由最少字数
  `have_prize` TINYINT(1) NOT NULL,   -- 是否添加礼物
  `destroyed` TINYINT(1) DEFAULT 0,   -- 是否销毁
  PRIMARY KEY(`id`),
  CHECK(`maxium` < 101),
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 条目信息表
CREATE TABLE `entry`
(
  `id` INT UNSIGNED AUTO_INCREMENT,   -- 条目ID
  `aid` CHAR(32) NOT NULL,    -- 活动ID
  `number` SMALLINT UNSIGNED NOT NULL,    -- 参赛编号
  `title` VARCHAR(25) NOT NULL,   -- 条目标题
  `introduction` TEXT DEFAULT NULL,   -- 详细介绍
  `acquisition` INT UNSIGNED DEFAULT 0,   -- 取得投票数
  `img_entry` CHAR(56) DEFAULT NULL,    -- 参赛图片地址
  `is_freeze` TINYINT(1) DEFAULT 0,   -- 条目是否冻结
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- 报名信息表
CREATE TABLE `apply`
(
  `id` INT UNSIGNED AUTO_INCREMENT,   -- 报名ID
  `aid` CHAR(32) NOT NULL,    -- 活动ID
  `status` CHAR(1) DEFAULT 'w',    -- 审核状态
  `title` VARCHAR(15) NOT NULL,   -- 条目标题
  `introduction` TEXT DEFAULT NULL,   -- 详细介绍（富文本）
  `img_entry` CHAR(56) DEFAULT NULL,    -- 参赛图片地址
  `name` VARCHAR(15) DEFAULT NULL,    -- 真实姓名
  `sex` CHAR(1) DEFAULT NULL,   -- 真实性别
  `age` TINYINT(3) DEFAULT NULL,    -- 真实年龄
  `telephone` CHAR(11) DEFAULT NULL,    -- 手机号码
  `email` VARCHAR(191) DEFAULT NULL,  -- 电子邮件
  `school` VARCHAR(16) DEFAULT NULL,    -- 学校名称
  `company` VARCHAR(26) DEFAULT NULL,   -- 公司名称
  `address` VARCHAR(40) DEFAULT NULL,   -- 收货地址
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- 微信用户表
CREATE TABLE `wechat` (
  `openid` CHAR(28) NOT NULL,                          -- 微信用户openid
  `nickname` VARCHAR(32) DEFAULT NULL,      -- 微信用户昵称
  `sex` INT(1) DEFAULT NULL,                               -- 微信用户性别
  `country` VARCHAR(12) DEFAULT NULL,         -- 微信用户所属国家
  `province` VARCHAR(14) DEFAULT NULL,        -- 微信用户所属省份
  `city` VARCHAR(16) DEFAULT NULL,                -- 微信用户所属城市
  `headimgurl` VARCHAR(255) DEFAULT NULL, -- 微信用户头像地址
  `token` CHAR(32) DEFAULT NULL,                    -- 微信用户投票令牌
  PRIMARY KEY (`openid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- 投票信息表
--CREATE TABLE `ticket`
--(
--  `id` INT UNSIGNED AUTO_INCREMENT,   -- 投票ID
--  `openid` CHAR(28) NOT NULL,                    -- 投票者OpenID
--  `whom` INT UNSIGNED NOT NULL,           -- 投向条目的ID
--  `timestamp` DATE NOT NULL,                     -- 投票时间
--  `ip` CHAR(15) NOT NULL,                             -- IP地址
-- `reason` VARCHAR(127) DEFAULT NULL,   -- 投票理由
--  PRIMARY KEY (`id`)
--)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- 创建投票表
DELIMITER $$
CREATE PROCEDURE create_ticket_table(IN table_name VARCHAR(32))
BEGIN
SET @sql_create_table = concat(
"CREATE TABLE IF NOT EXISTS ", table_name,
"(
  `id` INT UNSIGNED AUTO_INCREMENT,
  `openid` CHAR(28) NOT NULL,
  `whom` INT UNSIGNED NOT NULL,
  `timestamp` DATE NOT NULL,
  `ip` CHAR(15) NOT NULL,
  `reason` VARCHAR(127) DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;");
PREPARE sql_create_table FROM @sql_create_table;
EXECUTE sql_create_table; 
END
$$
DELIMITER ;

-- 删除投票表
DELIMITER $$
CREATE PROCEDURE drop_ticket_table(IN table_name VARCHAR(32))
BEGIN
SET @sql_drop_table = concat("DROP TABLE IF EXISTS ", table_name);
PREPARE sql_drop_table FROM @sql_drop_table;
EXECUTE sql_drop_table; 
END
$$
DELIMITER ;

-- 开启实时日志
DELIMITER $$
CREATE PROCEDURE open_realtime_log()
BEGIN
	SET GLOBAL general_log = 'ON';
	SET GLOBAL general_log_file = '/var/lib/mysql/general.log';
END
$$
DELIMITER ;

-- 关闭实时日志
DELIMITER $$
CREATE PROCEDURE close_realtime_log()
BEGIN
	SET GLOBAL general_log = 'OFF';
END
$$
DELIMITER ;
