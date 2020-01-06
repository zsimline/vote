-- Luming Vote System

CREATE DATABASE `vote`
USE `vote`

-- 用户信息表
CREATE TABLE `user`
(
  `id` INT UNSIGNED AUTO_INCREMENT,     -- 用户ID
  `email` VARCHAR(255) NOT NULL,        -- 电子邮件(作账号)
  `password` CHAR(32) NOT NULL,         -- 用户密码  
  `organization` varchar(25) NOT NULL,  -- 所属组织
  `is_staff` TINYINT(1)  DEFAULT 0,     -- 是否为管理人员
  `is_active` TINYINT(1) DEFAULT 0,     -- 账号是否可用
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4;

-- 活动信息表
CREATE TABLE `activitie`
(
  `id` CHAR(32) NOT NULL,                       -- 活动ID
  `name` VARCHAR(40) NOT NULL,                  -- 活动名称
  `description` TEXT NOT NULL,                  -- 活动描述
  `time_start` TIMESTAMP NOT NULL,              -- 开始时间
  `time_end` TIMESTAMP NOT NULL,                -- 截止时间
  `maxium` SMALLINT UNSIGNED NOT NULL,          -- 最多选择
  `sum_vote` INT UNSIGNED DEFAULT 0,            -- 投票总数
  `sum_contestant` SMALLINT	UNSIGNED DEFAULT 0, -- 选手总数
  `img_addr` CHAR(40) NOT NULL,                 -- 图片地址
  `link` VARCHAR(50) NOT NULL,                  -- 活动链接
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `contestant`
(
  `id` INT UNSIGNED AUTO_INCREMENT,     -- 选手ID
  `aid` CHAR(32) NOT NULL,              -- 活动ID
  `summary` VARCHAR(140) NOT NULL,      -- 选手描述            
  `acquisition` INT DEFAULT 0,          -- 取得投票数
  `img_addr` CHAR(40) DEFAULT NULL,     -- 图片地址
)

