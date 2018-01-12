/*
Navicat MySQL Data Transfer

Source Server         : 172.31.247.197
Source Server Version : 50635
Source Host           : 172.31.247.197:3306
Source Database       : eip_vehicle2

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-01-12 18:04:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'ID',
  `name` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `leaf` int(11) DEFAULT NULL COMMENT '节点',
  `type` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `priority` int(11) DEFAULT NULL COMMENT '顺序',
  `permission` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限',
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '路径',
  `out_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '站外路径',
  `img_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片路径',
  `pid` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '父id',
  `res_field_1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `res_field_2` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `res_field_3` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `create_opr_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `upd_opr_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源列表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'ID',
  `name` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `state` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `pid` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '父id',
  `res_field_1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `res_field_2` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `res_field_3` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `create_opr_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `upd_opr_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'ID',
  `role_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '角色id',
  `resource_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色资源对照表';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'ID',
  `user_name` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `login_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登录ID',
  `org_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '部门ID',
  `salt` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '盐',
  `password` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `state` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `img_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片路径',
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `res_field_1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `res_field_2` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `res_field_3` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
  `create_opr_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `upd_opr_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('4b891f1b7dce487da49466657eb78a1e', 'admin', 'admin', null, '7f47a414bb10f1b000539e0535820ebf', 'eefcc0a9cf387611d430a7a5530cd9d9', '20', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'ID',
  `user_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色对照表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for user_t
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_t
-- ----------------------------
INSERT INTO `user_t` VALUES ('1', '测试', '1', '24');
SET FOREIGN_KEY_CHECKS=1;
