-- Luming Vote System

CREATE DATABASE `vote`
USE `vote`

-- 用户信息表
CREATE TABLE `user`
(
  `id` INT UNSIGNED AUTO_INCREMENT,     -- 用户ID
  `email` VARCHAR(255) NOT NULL,        -- 电子邮件(作账号)
  `password` CHAR(32) NOT NULL,         -- 用户密码
  `organization` varchar(45) NOT NULL,  -- 所属组织
  `is_staff` TINYINT(1)  DEFAULT 0,     -- 是否为管理人员
  `is_active` TINYINT(1) DEFAULT 0,     -- 账号是否可用
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4;

-- 活动信息表
CREATE TABLE `activity`
(
  `id` CHAR(32) NOT NULL,                       -- 活动ID
  `title` VARCHAR(40) NOT NULL,                 -- 活动标题
  `suffix` CHAR(3) NOT NULL,                    -- 条目称谓
  `description` VARCHAR(3000) NOT NULL,         -- 活动描述（富文本）
  `time_start` TIMESTAMP NOT NULL,              -- 开始时间（时间戳）
  `time_end` TIMESTAMP NOT NULL,                -- 截止时间（时间戳）
  `maxium` SMALLINT UNSIGNED NOT NULL,          -- 最多选择
  `sum_entry` SMALLINT	UNSIGNED DEFAULT 0,     -- 条目总数
  `sum_voted` INT UNSIGNED DEFAULT 0,           -- 投票总数
  `sum_visited` INT UNSIGNED NOT NULL,          -- 访问总数
  `img_addr` CHAR(40) NOT NULL,                 -- 宣传图片地址
  `options` VARCHAR(300) NOT NULL,              -- 其它必填项
  `destroyed` TINYINT(1) DEFAULT 0,             -- 是否销毁
  PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 条目信息表
CREATE TABLE `entry`
(
  `id` INT UNSIGNED AUTO_INCREMENT,     -- 条目ID
  `aid` CHAR(32) NOT NULL,              -- 活动ID
  `title` VARCHAR(15) NOT NULL,         -- 条目标题
  `description` VARCHAR(3000) NOT NULL, -- 条目描述
  `acquisition` INT UNSIGNED DEFAULT 0, -- 取得投票数
  `img_addr` CHAR(40) DEFAULT NULL,     -- 条目图片地址
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- 报名信息表
CREATE TABLE `signup` 
(
  `id` INT UNSIGNED AUTO_INCREMENT,      -- 报名ID
  `aid` CHAR(32) NOT NULL,               -- 活动ID
  `reviewed` TINYINT(1) DEFAULT 0,       -- 是否通过审核
  `title` VARCHAR(15) NOT NULL,          -- 条目标题
  `description` VARCHAR(3000) NOT NULL,  -- 报名描述（富文本）
  `img_addr` CHAR(40) DEFAULT NULL,      -- 图片地址
  `sex` TINYINT(1) DEFAULT NULL,         -- 人物性别
  `age` TINYINT(3) DEFAULT NULL,         -- 人物年龄
  `name` VARCHAR(15) DEFAULT NULL,       -- 真实姓名
  `telephone` CHAR(11) DEFAULT NULL,     -- 手机号码
  `email` VARCHAR(255) DEFAULT NULL,     -- 电子邮件
  `wechat` VARCHAR(44) DEFAULT NULL,     -- 微信号
  `school` VARCHAR(10) DEFAULT NULL,     -- 学校名
  `classdesc` VARCHAR(30) DEFAULT NULL,  -- 院系[,专业[,班级]描述
  `company` VARCHAR(26) DEFAULT NULL,    -- 公司名称
  `address` VARCHAR(40) DEFAULT NULL,    -- 收货地址
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- 投票信息表
CREATE TABLE `uuid`
(
  `id` INT UNSIGNED AUTO_INCREMENT,       -- 投票ID
  `openid` CHAR(28) NOT NULL,             -- 投票者OpenID
  `nickname` VARCHAR(32) DEFAULT NULL,    -- 投票者昵称
  `sex` TINYINT(1) DEFAULT NULL,          -- 投票者性别
  `country` VARCHAR(12) DEFAULT NULL,     -- 投票者所属国家
  `province` VARCHAR(10) DEFAULT NULL,    -- 投票者所属省份
  `city` VARCHAR(8) DEFAULT NULL,         -- 投票者所属城市
  `headimgurl` VARCHAR(255) DEFAULT NULL, -- 投票者头像地址
  `timestamp` TIMESTAMP NOT NULL,         -- 投票时间（时间戳）
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
