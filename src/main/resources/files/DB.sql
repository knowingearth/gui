CREATE DATABASE gui_guang CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

drop table if exists gui_area;
CREATE TABLE `gui_area` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code` bigint NOT NULL COMMENT '区划编码',
    `parent_code` bigint NOT NULL DEFAULT '0' COMMENT '父级编码',
    `name` varchar(50) NOT NULL COMMENT '名称',
    `short_name` varchar(50) NOT NULL COMMENT '简称',
    `longitude` float NOT NULL DEFAULT '0' COMMENT '经度',
    `latitude` float NOT NULL DEFAULT '0' COMMENT '纬度',
    `level` int NOT NULL COMMENT '等级(1省/直辖市,2地级市,3区县,4镇/街道,5居委会)',
    `ur_code` int DEFAULT NULL COMMENT '城乡分类代码：城乡分类代码以1开头的表示是城镇，以2开头表示是乡村。具体含义：111表示主城区，112表示城乡结合区，121表示镇中心区，122表示镇乡结合区，123表示特殊区域；210表示乡中心区，220表示村庄。',
    `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
    `status` int NOT NULL DEFAULT '1' COMMENT '状态(0禁用/1启用)',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uni_idx_code` (`code`) USING BTREE,
    UNIQUE KEY `uni_idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COMMENT='全国区划表';

drop table if exists gui_log;
CREATE TABLE `gui_log` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
   `level` varchar(10) NOT NULL COMMENT '日志级别',
   `target` varchar(500) NOT NULL COMMENT '日志源',
   `message` text NOT NULL COMMENT '日志内容',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   PRIMARY KEY (`id`) USING BTREE,
   KEY `idx_target` (`target`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COMMENT='日志表';