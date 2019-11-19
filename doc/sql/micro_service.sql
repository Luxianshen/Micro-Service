/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : micro_service

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2019-11-19 18:03:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `pid` bigint(19) DEFAULT '0',
  `label` varchar(30) NOT NULL COMMENT '标题',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、资源',
  `state` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态 1、正常 0、禁用',
  `seq` int(11) DEFAULT '0' COMMENT '排序',
  `path` varchar(100) DEFAULT NULL COMMENT '地址',
  `permission_code` varchar(100) NOT NULL COMMENT '权限编码',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  `permission_desc` varchar(80) DEFAULT NULL COMMENT '描述',
  `sys_id` bigint(19) DEFAULT NULL COMMENT '所属系统id',
  `component` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除 0:未删除,1:已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_id` (`sys_id`,`permission_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统权限信息';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1094068184018956289', null, '平台设置', '0', '1', '0', 'admin', 'menu:admin', 'component', null, '1', 'Layout', '2019-02-09 10:59:16', null, '0');
INSERT INTO `tb_menu` VALUES ('1094071492502704130', '1094068184018956289', '系统管理', '0', '1', '0', '/sys', 'menu:admin:system', 'component', null, '1', '', '2019-02-09 11:12:25', null, '0');
INSERT INTO `tb_menu` VALUES ('1094894629217681410', null, '平台功能', '2', '1', '0', null, 'function', 'component', null, '1', 'Layout', '2019-02-11 17:43:16', null, '0');
INSERT INTO `tb_menu` VALUES ('1094903963725176834', null, '平台菜单', '0', '1', '0', null, 'menu', 'component', null, '1', 'Layout', '2019-02-11 18:20:21', null, '0');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `role_desc` varchar(250) DEFAULT NULL COMMENT '描述',
  `sys_id` bigint(19) DEFAULT NULL COMMENT '所属系统id',
  `default_role` tinyint(1) DEFAULT '0' COMMENT '是否默认角色 0：否  1：是',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统角色信息';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '超级管理员', '拥有基础系统的所有权限', '1', '0', 'base:admin:super', '2019-01-30 16:17:47', null, '0');

-- ----------------------------
-- Table structure for tb_role_menu_rel
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu_rel`;
CREATE TABLE `tb_role_menu_rel` (
  `id` bigint(19) NOT NULL,
  `role_id` bigint(19) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(19) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除：0、未删除  1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构用户关联表';

-- ----------------------------
-- Records of tb_role_menu_rel
-- ----------------------------
INSERT INTO `tb_role_menu_rel` VALUES ('2', '1', '1094068184018956289', '2019-11-19 11:43:02', null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('4', '1', '1094071492502704130', '2019-11-19 09:13:28', null, '0');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `agent_id` varchar(100) DEFAULT NULL COMMENT '用户名',
  `agent_auth` blob COMMENT '密码',
  `salt` blob COMMENT '密码盐',
  `state` tinyint(2) DEFAULT '1' COMMENT '是否启用 0：禁用 1：启用',
  `name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(100) DEFAULT NULL COMMENT 'email',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别 0:未知  1：男  2：女',
  `phone_no` varchar(22) DEFAULT NULL COMMENT '电话号码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) DEFAULT NULL COMMENT '是否删除 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_user_email_uk` (`email`) USING BTREE,
  UNIQUE KEY `admin_user_phone_no_uk` (`phone_no`) USING BTREE,
  UNIQUE KEY `admin_user_username_uk` (`agent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统用户信息';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 0xA8EB7E3D6EB4BEABB641DD2107245C1D50220AA5, 0x1F027DA4DE09661F, '1', '超级管理员', 'hi@lujs.com', 'http://thirdwx.qlogo.cn/mmopen/vi_32/kAexWBkmJC2df40QKvfnHG5iaT8U8lznnUumZ31ZxIIttwaxvDrPEygwaEfPjo76bfR3SxAnmYROPb5670FycAQ/132', '1', null, '2019-01-30 01:12:26', null, '0');

-- ----------------------------
-- Table structure for tb_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role_rel`;
CREATE TABLE `tb_user_role_rel` (
  `id` bigint(19) NOT NULL,
  `role_id` bigint(19) DEFAULT NULL COMMENT '角色id',
  `user_id` bigint(19) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除：0、未删除  1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构用户关联表';

-- ----------------------------
-- Records of tb_user_role_rel
-- ----------------------------
INSERT INTO `tb_user_role_rel` VALUES ('1', '1', '1', '2019-11-19 09:13:28', null, '0');
