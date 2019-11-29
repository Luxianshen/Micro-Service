/*
Source Database       : micro_service

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-11-29 17:26:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_api
-- ----------------------------
DROP TABLE IF EXISTS `tb_api`;
CREATE TABLE `tb_api` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `pid` bigint(19) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `api_key` varchar(255) NOT NULL COMMENT '请求地址',
  `real_url` varchar(255) NOT NULL COMMENT '真实请求地址',
  `req_type` tinyint(2) DEFAULT NULL COMMENT '请求类型',
  `state` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态 1、正常 0、禁用',
  `sys_id` bigint(19) DEFAULT NULL COMMENT '所属系统id',
  `seq` int(11) DEFAULT '0' COMMENT '排序',
  `permission_code` varchar(100) NOT NULL COMMENT '权限编码',
  `api_desc` varchar(80) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除 0:未删除,1:已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_id` (`sys_id`,`permission_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统权限信息';

-- ----------------------------
-- Records of tb_api
-- ----------------------------
INSERT INTO `tb_api` VALUES ('1', null, '测试post接口', 'TESTPOST', 'http://192.168.4.79:8079/transmit/test2', '1', '1', '0', '1', 'test:postt', '2', '2019-11-26 14:10:04', '2019-11-26 14:10:08', '0');
INSERT INTO `tb_api` VALUES ('1199244318487085057', null, '测试get接口', 'TESTGET', 'http://localhost:8079/transmit/test1', '0', '1', '0', '10', 'test:get', '1', '2019-11-26 14:10:04', '2019-11-26 14:10:08', '0');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `pid` bigint(19) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `label` varchar(30) NOT NULL COMMENT '标题',
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
  `redirect` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_id` (`sys_id`,`permission_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统权限信息';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1094068184018956289', null, '系统管理', '系统管理', '1', '0', '/admin', 'admin', 'component', null, '0', 'Layout', '2019-02-09 10:59:16', null, '0', null);
INSERT INTO `tb_menu` VALUES ('1094071492502704131', '1094068184018956289', '角色管理', '角色管理', '1', '0', 'role', 'admin:role', 'component', null, '0', 'views/admin/role/index', '2019-02-09 11:12:25', null, '0', null);
INSERT INTO `tb_menu` VALUES ('1094071492502704132', '1094068184018956289', '菜单管理', '菜单管理', '1', '0', 'permission', 'admin:permission', 'component', null, '0', 'views/admin/permission/index', '2019-02-09 11:12:25', null, '0', null);
INSERT INTO `tb_menu` VALUES ('1094071492502704133', '1094068184018956289', '授权管理', '授权管理', '1', '0', 'role/auth/:id', 'admin:role:auth', 'component', null, '0', 'views/admin/role/auth', '2019-02-09 11:12:25', null, '0', null);
INSERT INTO `tb_menu` VALUES ('1094071492502704134', '1094068184018956289', '用户管理', '用户管理', '1', '0', 'user', 'admin:user', 'component', null, '0', 'views/admin/user/index', '2019-02-09 11:12:25', null, '0', null);
INSERT INTO `tb_menu` VALUES ('1199167935111045121', '1094068184018956289', '接口管理', '接口管理', '1', '0', 'transmit', 'admin:transmit', 'component', null, '0', 'views/admin/transmit/index', '2019-11-26 03:28:10', null, '0', null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `role_desc` varchar(250) DEFAULT NULL COMMENT '描述',
  `sys_id` bigint(19) DEFAULT NULL COMMENT '所属系统id',
  `default_role` tinyint(1) DEFAULT '0' COMMENT '是否系统角色 0：否  1：是',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统角色信息';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '超级管理员', '拥有基础系统的所有权限', '0', '1', 'base:admin:super', '2019-01-30 16:17:47', null, '0');
INSERT INTO `tb_role` VALUES ('1198850730259488770', '测试自由分配', '测试自由分配', '0', '0', 'test_role', '2019-11-25 06:27:42', null, '0');

-- ----------------------------
-- Table structure for tb_role_api_rel
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_api_rel`;
CREATE TABLE `tb_role_api_rel` (
  `id` bigint(19) NOT NULL,
  `role_id` bigint(19) DEFAULT NULL COMMENT '角色id',
  `api_id` bigint(19) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除：0、未删除  1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构用户关联表';

-- ----------------------------
-- Records of tb_role_api_rel
-- ----------------------------
INSERT INTO `tb_role_api_rel` VALUES ('1200256782695723010', '1', '1', null, null, '0');
INSERT INTO `tb_role_api_rel` VALUES ('1200311811423195137', '1', '1199244318487085057', null, null, '0');

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
INSERT INTO `tb_role_menu_rel` VALUES ('1197810014984970241', '1', '1094071492502704131', null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1197810156307849217', '1', '1094071492502704132', null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1197811098419830785', '1', '1094071492502704134', null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1198809261263937538', '1', null, null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1198809520597753858', '1', null, null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1198812140125102082', '1', '1094071492502704133', null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1198864889353142274', '1198850730259488770', '1094071492502704131', null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1198864898689662977', '1198850730259488770', '1094068184018956289', null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1199506543005249537', '1', '1199167935111045121', null, null, '0');
INSERT INTO `tb_role_menu_rel` VALUES ('1200311966905995265', '1198850730259488770', '1094071492502704133', null, null, '0');

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
INSERT INTO `tb_user` VALUES ('1', 'admin', 0xA8EB7E3D6EB4BEABB641DD2107245C1D50220AA5, 0x1F027DA4DE09661F, '1', '超级管理员', 'hi@lujs.com', 'http://thirdwx.qlogo.cn/mmopen/vi_32/kAexWBkmJC2df40QKvfnHG5iaT8U8lznnUumZ31ZxIIttwaxvDrPEygwaEfPjo76bfR3SxAnmYROPb5670FycAQ/132', '1', '13555555555', '2019-01-30 01:12:26', null, '0');
INSERT INTO `tb_user` VALUES ('1197714343959310337', 'test', 0x4DB67A3CD93E19AE694523DB0FE67830FABCEF97, 0xE075B79CD3D4158C, '1', 'test', null, null, '0', '13555555556', '2019-11-22 03:12:07', null, '0');

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
INSERT INTO `tb_user_role_rel` VALUES ('2', '1198850730259488770', '1197714343959310337', '2019-11-19 09:13:28', null, '0');
