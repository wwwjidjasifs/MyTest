/*
 Navicat Premium Data Transfer

 Source Server         : wxiao
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : myoa

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 21/06/2023 17:11:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for communicate
-- ----------------------------
DROP TABLE IF EXISTS `communicate`;
CREATE TABLE `communicate`  (
  `comid` int NOT NULL AUTO_INCREMENT COMMENT '会议ID',
  `roomid` int NULL DEFAULT NULL COMMENT '会议室ID',
  `userid` int NULL DEFAULT NULL COMMENT '申请人ID',
  `title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '会议主题',
  `starttime` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `state` int NULL DEFAULT 0 COMMENT '会议审核',
  PRIMARY KEY (`comid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of communicate
-- ----------------------------
INSERT INTO `communicate` VALUES (1, 1, 1, '带动经济发展', '2021-06-18 01:03:03', '2021-06-18 02:03:05', 1);
INSERT INTO `communicate` VALUES (2, 2, 1, 'oa办公管理系统上线', '2021-06-20 08:10:00', '2021-06-20 09:00:00', 1);
INSERT INTO `communicate` VALUES (3, 3, 2, 'WWDC发布会', '2021-06-21 08:00:00', '2021-06-22 20:59:59', 1);
INSERT INTO `communicate` VALUES (4, 4, 2, '高等数学强化班', '2021-10-22', '2021-10-23', 1);
INSERT INTO `communicate` VALUES (5, 5, 1, '线性代数强化课程', '2021-06-01', '2021-06-02', 1);
INSERT INTO `communicate` VALUES (6, 6, 1, '概率论基础知识过关', '2021-06-22 05:00:00', '2021-06-22 08:00:00', 1);
INSERT INTO `communicate` VALUES (7, 7, 1, '好好好1', '2023-06-15 00:00:00', '2023-07-16 00:00:00', 1);
INSERT INTO `communicate` VALUES (8, 7, 1, 'dddd', '2023-06-15 00:00:00', '2023-07-16 00:00:00', 1);
INSERT INTO `communicate` VALUES (9, 9, 1, 'ddd', '2023-05-08 00:00:00', '2023-07-16 00:00:00', 1);
INSERT INTO `communicate` VALUES (10, 8, 1, '1', '2023-06-15 00:00:00', '2023-07-16 00:00:00', 0);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `departmentid` int NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `departmentname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门名字',
  `departmentphone` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门电话',
  `departmentnumber` int NULL DEFAULT NULL COMMENT '部门人数',
  PRIMARY KEY (`departmentid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '董事部', '010-66411161', 10);
INSERT INTO `department` VALUES (2, '人事部', '010-65551114', 10);
INSERT INTO `department` VALUES (3, '财政部', '010-66096114', 10);
INSERT INTO `department` VALUES (4, '行政部', '010-64193366', 10);
INSERT INTO `department` VALUES (5, '研发部', '010-84201114', 20);
INSERT INTO `department` VALUES (6, '售货管理部', '010-58160600', 35);
INSERT INTO `department` VALUES (7, '渠道管理部', '010-63202114', 20);
INSERT INTO `department` VALUES (8, '营销部', '010-65194114', 25);
INSERT INTO `department` VALUES (12, '传销部', '010-88888888', 10);
INSERT INTO `department` VALUES (13, '运营部', '010-11111111', 100);

-- ----------------------------
-- Table structure for holiday
-- ----------------------------
DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `userid` int NULL DEFAULT NULL COMMENT '用户ID',
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `departmentid` int NULL DEFAULT NULL COMMENT '部门',
  `type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '请假类型',
  `reason` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '请假原因',
  `starttime` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `totaltime` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '请假总时长',
  `state` int NULL DEFAULT 0 COMMENT '批准状态',
  `isApply` int NULL DEFAULT NULL COMMENT '二级审核',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of holiday
-- ----------------------------
INSERT INTO `holiday` VALUES (1, 1, 'admin', 1, '病假', '老婆怀孕', '2020-10-10 09:00:00', '2020-10-11 18:00:00', '0个月1天9小时0分钟0秒', 2, 2);
INSERT INTO `holiday` VALUES (2, 3, 'aaa', 2, '病假', '老婆怀孕', '2021-06-21 02:00:00', '2022-07-22 20:59:59', '1年1个月1天18小时59分钟59秒', 1, 2);
INSERT INTO `holiday` VALUES (3, 3, 'aaa', 2, '病假', '老婆怀孕', '2021-06-21 09:00:00', '2021-06-21 10:00:00', '0个月0天1小时0分钟0秒', 1, 2);
INSERT INTO `holiday` VALUES (4, 1, 'admin', 1, '病假', '弟弟买车', '2021-07-06 00:00:00', '2021-08-10 00:00:00', '1个月', 2, 1);
INSERT INTO `holiday` VALUES (5, 2, '123456', 2, '事假', '老婆怀孕', '2021-06-08', '2021-06-08', '1', 1, 2);
INSERT INTO `holiday` VALUES (6, 1, 'admin', 1, '病假', '老婆怀孕了', '2023-06-14 00:00:00', '2023-07-14 23:59:59', '1个月', 2, 0);
INSERT INTO `holiday` VALUES (7, 1, 'admin', 1, '事假', '老婆怀孕', '2021-06-02', '2021-06-03', '1', 2, 0);
INSERT INTO `holiday` VALUES (8, 1, 'admin', 1, '事假', '老婆怀孕', '2021-06-11', '2021-06-12', '1', 1, 2);
INSERT INTO `holiday` VALUES (9, 1, 'admin', 1, '事假', '老婆怀孕', '2021-06-03', '2021-06-05', '2', 0, 0);
INSERT INTO `holiday` VALUES (10, 1, 'admin', 1, '事假', '弟弟结婚', '2021-06-20', '2021-06-21', '1', 0, 0);
INSERT INTO `holiday` VALUES (11, 1, 'admin', 1, '病假', '买新房子', '2021-06-21 00:00:00', '2021-07-21 23:59:59', '1个月0天', 0, 0);
INSERT INTO `holiday` VALUES (12, 16, 'demo', 3, '病假', 'test', '2021-06-21 09:00:00', '2021-08-06 23:59:59', '1个月16天14小时59分钟59秒', 0, 0);
INSERT INTO `holiday` VALUES (13, 1, 'admin', 3, '事假', 'test', '2021-06-21 09:00:00', '2021-08-06 23:59:59', '1个月16天14小时59分钟59秒', 1, 2);
INSERT INTO `holiday` VALUES (14, 1, 'admin', 3, '病假', '病假6666', '2023-06-14 00:00:00', '2023-07-16 00:00:00', '2天', 0, NULL);
INSERT INTO `holiday` VALUES (15, 28, 'caizhengbu2', 3, '病假', '12313', '2023-06-13 00:00:00', '2023-07-14 00:00:00', '1', 0, NULL);
INSERT INTO `holiday` VALUES (16, 27, 'caizhengbu1', 3, '病假', '好好好', '2023-06-12 00:00:00', '2023-07-14 00:00:00', '11', 1, NULL);
INSERT INTO `holiday` VALUES (17, 27, 'caizhengbu1', 3, '病假', '111', '2023-06-14 00:00:00', '2023-07-15 00:00:00', '1', 1, 1);
INSERT INTO `holiday` VALUES (18, 27, 'caizhengbu1', 3, '病假', 'sad', '2023-06-14 00:00:00', '2023-07-15 00:00:00', '111', 1, 1);
INSERT INTO `holiday` VALUES (19, 29, 'renshibu1', 2, '病假', 'dsada', '2023-06-14 00:00:00', '2023-07-15 00:00:00', '111', 0, 1);
INSERT INTO `holiday` VALUES (20, 29, 'renshibu1', 2, '病假', 'ddddd', '2023-06-15 00:00:00', '2023-07-15 00:00:00', '1', 0, 1);
INSERT INTO `holiday` VALUES (21, 29, 'renshibu1', 2, '病假', '1111222', '2023-06-14 00:00:00', '2023-07-15 00:00:00', '11', 1, 1);
INSERT INTO `holiday` VALUES (22, 29, 'renshibu1', 2, '病假', '888', '2023-06-14 00:00:00', '2023-07-15 00:00:00', '22', 1, 1);
INSERT INTO `holiday` VALUES (23, 27, 'caizhengbu1', 3, '病假', '1337', '2023-06-21 00:00:00', '2023-07-22 00:00:00', '2', 1, 1);
INSERT INTO `holiday` VALUES (24, 27, 'caizhengbu1', 3, '病假', 'asdsad', '2023-06-14 00:00:00', '2023-07-21 00:00:00', '1', 1, NULL);

-- ----------------------------
-- Table structure for menuinfo
-- ----------------------------
DROP TABLE IF EXISTS `menuinfo`;
CREATE TABLE `menuinfo`  (
  `menuid` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单ID',
  `title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `state` int NULL DEFAULT 1 COMMENT '状态',
  `url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `pid` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单上级ID',
  `iconCls` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单图标'
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menuinfo
-- ----------------------------
INSERT INTO `menuinfo` VALUES ('Y01', '员工管理', 1, 'null', 'root', 'null');
INSERT INTO `menuinfo` VALUES ('Y0101', '员工信息', 1, 'http://localhost:8080/MyOA/user/userlist', 'Y01', 'null');
INSERT INTO `menuinfo` VALUES ('Y02', '部门管理', 1, 'null', 'root', 'null');
INSERT INTO `menuinfo` VALUES ('Y0201', '部门信息', 1, 'http://localhost:8080/MyOA/department/departmentlist', 'Y02', 'null');
INSERT INTO `menuinfo` VALUES ('Y03', '财政管理', 1, 'null', 'root', 'null');
INSERT INTO `menuinfo` VALUES ('Y0301', '工资信息', 1, 'null', 'Y03', 'null');
INSERT INTO `menuinfo` VALUES ('Y0302', '公司收入盈利', 1, 'null', 'Y03', 'null');
INSERT INTO `menuinfo` VALUES ('Y0303', '公司收费情况', 1, 'null', 'Y03', 'null');
INSERT INTO `menuinfo` VALUES ('Y04', '超级权限管理', 1, 'null', 'root', 'nulll');
INSERT INTO `menuinfo` VALUES ('Y0401', '用户列表', 1, 'http://localhost:8080/MyOA/user/userlistadmin', 'Y04', 'nulll');
INSERT INTO `menuinfo` VALUES ('Y0402', '菜单列表', 1, 'http://localhost:8080/MyOA/menu/menulist', 'Y04', 'nulll');
INSERT INTO `menuinfo` VALUES ('Y0403', '角色列表', 1, 'http://localhost:8080/MyOA/role/rolelist', 'Y04', 'nulll');
INSERT INTO `menuinfo` VALUES ('Y05', '请假管理', 1, 'null', 'root', 'null');
INSERT INTO `menuinfo` VALUES ('Y0501', '请假申请', 1, 'http://localhost:8080/MyOA/hoilday/applyLeave', 'Y05', 'null');
INSERT INTO `menuinfo` VALUES ('Y0502', '请假审批', 1, 'http://localhost:8080/MyOA/hoilday/approveApply', 'Y05', 'null');
INSERT INTO `menuinfo` VALUES ('Y06', '会议管理', 1, 'null', 'root', 'null');
INSERT INTO `menuinfo` VALUES ('Y0601', '会议申请', 1, 'http://localhost:8080/MyOA/communicate/roomApply', 'Y06', 'null');
INSERT INTO `menuinfo` VALUES ('Y0602', '会议审批', 1, 'http://localhost:8080/MyOA/communicate/roomApprove', 'Y06', 'null');
INSERT INTO `menuinfo` VALUES ('Y0603', '会议室管理', 1, 'http://localhost:8080/MyOA/room/roomList', 'Y06', 'null');
INSERT INTO `menuinfo` VALUES ('Y07', '开发者日志', 1, 'null', 'root', 'null');
INSERT INTO `menuinfo` VALUES ('Y0701', '日志', 1, 'http://localhost:8080/MyOA/log.jsp', 'Y07', 'null');
INSERT INTO `menuinfo` VALUES ('YY01', '功能管理', 1, 'null', 'root', 'null');
INSERT INTO `menuinfo` VALUES ('YY0101', '功能信息', 1, 'null', 'YY01', 'null');
INSERT INTO `menuinfo` VALUES ('Y08', '财务管理', 1, 'null', 'root', 'null');

-- ----------------------------
-- Table structure for roleinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo`  (
  `roleid` int NULL DEFAULT NULL COMMENT '角色ID',
  `rolename` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  INDEX `roleid`(`roleid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES (1, '董事长');
INSERT INTO `roleinfo` VALUES (2, '人事部经理');
INSERT INTO `roleinfo` VALUES (3, '财政部经理');
INSERT INTO `roleinfo` VALUES (4, '行政部经理');
INSERT INTO `roleinfo` VALUES (5, '普通员工');
INSERT INTO `roleinfo` VALUES (6, 'hhhh');

-- ----------------------------
-- Table structure for rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `rolemenu`;
CREATE TABLE `rolemenu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `roleid` int NULL DEFAULT NULL COMMENT '角色ID',
  `menuid` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2026 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rolemenu
-- ----------------------------
INSERT INTO `rolemenu` VALUES (1122, 3, 'Y03');
INSERT INTO `rolemenu` VALUES (1123, 3, 'Y0301');
INSERT INTO `rolemenu` VALUES (1124, 3, 'Y0302');
INSERT INTO `rolemenu` VALUES (1125, 3, 'Y0303');
INSERT INTO `rolemenu` VALUES (1126, 3, 'Y03');
INSERT INTO `rolemenu` VALUES (1127, 3, 'Y0301');
INSERT INTO `rolemenu` VALUES (1128, 3, 'Y0302');
INSERT INTO `rolemenu` VALUES (1129, 3, 'Y0303');
INSERT INTO `rolemenu` VALUES (1130, 3, 'Y05');
INSERT INTO `rolemenu` VALUES (1131, 3, 'Y0501');
INSERT INTO `rolemenu` VALUES (1132, 3, 'Y0502');
INSERT INTO `rolemenu` VALUES (1854, 5, 'Y01');
INSERT INTO `rolemenu` VALUES (1855, 5, 'Y0101');
INSERT INTO `rolemenu` VALUES (1856, 5, 'Y01');
INSERT INTO `rolemenu` VALUES (1857, 5, 'Y0101');
INSERT INTO `rolemenu` VALUES (1858, 5, 'Y05');
INSERT INTO `rolemenu` VALUES (1859, 5, 'Y0501');
INSERT INTO `rolemenu` VALUES (1945, 6, 'Y01');
INSERT INTO `rolemenu` VALUES (1946, 6, 'Y01');
INSERT INTO `rolemenu` VALUES (1947, 6, 'Y02');
INSERT INTO `rolemenu` VALUES (1948, 6, 'Y01');
INSERT INTO `rolemenu` VALUES (1949, 6, 'Y02');
INSERT INTO `rolemenu` VALUES (1950, 6, 'Y03');
INSERT INTO `rolemenu` VALUES (1951, 6, 'Y0302');
INSERT INTO `rolemenu` VALUES (1952, 6, 'Y0303');
INSERT INTO `rolemenu` VALUES (1989, 2, 'Y02');
INSERT INTO `rolemenu` VALUES (1990, 2, 'Y0201');
INSERT INTO `rolemenu` VALUES (1991, 2, 'Y04');
INSERT INTO `rolemenu` VALUES (1992, 2, 'Y0401');
INSERT INTO `rolemenu` VALUES (1993, 2, 'Y05');
INSERT INTO `rolemenu` VALUES (1994, 2, 'Y0501');
INSERT INTO `rolemenu` VALUES (1995, 2, 'Y0502');
INSERT INTO `rolemenu` VALUES (1996, 4, 'Y02');
INSERT INTO `rolemenu` VALUES (1997, 4, 'Y0201');
INSERT INTO `rolemenu` VALUES (1998, 4, 'Y06');
INSERT INTO `rolemenu` VALUES (1999, 4, 'Y0601');
INSERT INTO `rolemenu` VALUES (2000, 4, 'Y0602');
INSERT INTO `rolemenu` VALUES (2001, 4, 'Y0603');
INSERT INTO `rolemenu` VALUES (2002, 1, 'Y01');
INSERT INTO `rolemenu` VALUES (2003, 1, 'Y0101');
INSERT INTO `rolemenu` VALUES (2004, 1, 'Y02');
INSERT INTO `rolemenu` VALUES (2005, 1, 'Y0201');
INSERT INTO `rolemenu` VALUES (2006, 1, 'Y03');
INSERT INTO `rolemenu` VALUES (2007, 1, 'Y0301');
INSERT INTO `rolemenu` VALUES (2008, 1, 'Y0302');
INSERT INTO `rolemenu` VALUES (2009, 1, 'Y0303');
INSERT INTO `rolemenu` VALUES (2010, 1, 'Y04');
INSERT INTO `rolemenu` VALUES (2011, 1, 'Y0401');
INSERT INTO `rolemenu` VALUES (2012, 1, 'Y0402');
INSERT INTO `rolemenu` VALUES (2013, 1, 'Y0403');
INSERT INTO `rolemenu` VALUES (2014, 1, 'Y05');
INSERT INTO `rolemenu` VALUES (2015, 1, 'Y0501');
INSERT INTO `rolemenu` VALUES (2016, 1, 'Y0502');
INSERT INTO `rolemenu` VALUES (2017, 1, 'Y06');
INSERT INTO `rolemenu` VALUES (2018, 1, 'Y0601');
INSERT INTO `rolemenu` VALUES (2019, 1, 'Y0602');
INSERT INTO `rolemenu` VALUES (2020, 1, 'Y0603');
INSERT INTO `rolemenu` VALUES (2021, 1, 'Y07');
INSERT INTO `rolemenu` VALUES (2022, 1, 'Y0701');
INSERT INTO `rolemenu` VALUES (2023, 1, 'YY01');
INSERT INTO `rolemenu` VALUES (2024, 1, 'YY0101');
INSERT INTO `rolemenu` VALUES (2025, 1, 'Y08');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomid` int NOT NULL AUTO_INCREMENT COMMENT '会议室ID',
  `roomname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '会议室名称',
  `peoplenum` int NULL DEFAULT NULL COMMENT '容纳人数',
  `roomaddress` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '会议地点',
  `state` int NULL DEFAULT 0 COMMENT '会议室状态',
  `isapply` int NULL DEFAULT 0 COMMENT '是否被申请',
  PRIMARY KEY (`roomid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '桃花源', 10, 'A栋1层101', 1, 1);
INSERT INTO `room` VALUES (2, '梅花谷', 10, 'A栋2层201', 1, 1);
INSERT INTO `room` VALUES (3, '雅兰阁', 20, 'B栋1层101', 1, 1);
INSERT INTO `room` VALUES (4, '水云间', 20, 'B栋2层201', 1, 1);
INSERT INTO `room` VALUES (5, '紫水亭', 30, 'C栋1层101', 1, 1);
INSERT INTO `room` VALUES (6, '苍云座', 30, 'C栋2层201', 1, 1);
INSERT INTO `room` VALUES (7, '清波园', 10, 'D栋1层101', 1, 1);
INSERT INTO `room` VALUES (8, '翠玉阁', 10, 'D栋2层201', 1, 0);
INSERT INTO `room` VALUES (9, '碧玉间', 20, 'E栋1层101', 1, 1);
INSERT INTO `room` VALUES (10, '清溪阁', 15, 'E栋2层201', 1, 0);
INSERT INTO `room` VALUES (14, 'ddd', 1, '1', 1, 0);
INSERT INTO `room` VALUES (15, 'dddss', 12, '11', 1, 0);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `userid` int NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `userpass` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工密码',
  `sex` varchar(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '出生年月',
  `departmentid` int NULL DEFAULT NULL COMMENT '部门ID',
  `telephone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地点',
  `email` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `entertime` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '入职时间',
  `salary` int NULL DEFAULT NULL COMMENT '工资',
  `roleid` int NULL DEFAULT 5 COMMENT '角色ID',
  `state` int NULL DEFAULT 1 COMMENT '入职状态',
  `imageurl` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`userid`) USING BTREE,
  INDEX `roleid`(`roleid` ASC) USING BTREE,
  CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `roleinfo` (`roleid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 1, '13870096882', '江西省', '287@qq.com', '2019-7-9', 18000, 1, 1, '/MyOA/layui/upload/jiaoyou.jpg');
INSERT INTO `userinfo` VALUES (2, 'zhangsan', 'E10ADC3949BA59ABBE56E057F20F883E', '女', '2000-07-01', 2, '13967325579', '江西省南昌市青山湖区', '25769532279@qq.com', '2020-3-2 ', 15000, 2, 1, 'layui/upload/1.png');
INSERT INTO `userinfo` VALUES (3, '章青', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 3, '18277633573', '江西省南昌市青山湖区', '23177632513@qq.com', '2019-1-27', 14000, 3, 1, NULL);
INSERT INTO `userinfo` VALUES (4, '董小眼', 'E10ADC3949BA59ABBE56E057F20F883E', '女', '2000-07-01', 4, '19967235541', '江西省南昌市青山湖区', '1109951287@qq.com', '2018-10-15', 13000, 6, 1, NULL);
INSERT INTO `userinfo` VALUES (5, '刘红', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 2, '17366545572', '江西省南昌市青山湖区', '1109951287@qq.com', '2020-10-10', 12000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (6, '赵峰', '219B3A4E846E3D0019A876C74F5AF373', '女', '2000-07-01', 4, '18332556722', '江西省南昌市青山湖区', '1109951287@qq.com', '2019-3-7', 9000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (7, '孙周', 'E556421F2F543737E9040256024604FF', '男', '2000-07-01', 3, '19976312572', '江西省南昌市青山湖区', '1109951287@qq.com', '2019-5-9 ', 7000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (8, '刘琦', '4CB40714B5049BA56428F7DD1AE297E9', '男', '2000-07-01', 2, '18677325531', '江西省南昌市青山湖区', '1109951287@qq.com', '2021-3-3 ', 8000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (9, '夏雨荷', 'E10ADC3949BA59ABBE56E057F20F883E', '女', '2000-07-01', 4, '13376557731', '江西省南昌市青山湖区', '1109951287@qq.com', '2021-3-4', 8000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (10, '周芷若', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 3, '13886557731', '江西省南昌市青山湖区', '1109951287@qq.com', '2020-10-10', 6000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (11, '王杨子君', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 3, '18332555674', '江西省南昌市青山湖区', '1109951287@qq.com', '2019-6-17', 6000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (13, '胡淑颖', 'E2FC714C4727EE9395F324CD2E7F331F', '男', '2021-06-03', 4, '13970968505', '江西省南昌市青山湖区', '1109951287@qq.com', '2021-06-03', 6000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (14, '郑宇', '845D5F1153C27BEED29F479640445148', '女', '2000-03-11', 3, '15080802802', '江西省南昌市青山湖区', '1109951287@qq.com', '2021-03-11', 5000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (15, '周可', 'FE01CE2A7FBAC8FAFAED7C982A04E229', '女', '2000-03-11', 3, '13870096994', '江西省南昌市青山湖区', '1109951287@qq.com', '2021-03-18', 5000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (16, '谭世一', 'B247DEAFA97A5122EEF246B489074C5D', '女', '2000-03-11', 1, '13879942357', '江西省南昌市青山湖区', '1109951287@qq.com', '2019-03-11', 5000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (21, 'demo', '7C8227725C19E48E6D6288C94BC6D67B', '男', '2000-03-03', 1, '13860097095', '江西省南昌市青山湖区', '1109951287@qq.com', '2021-07-02', 9000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (26, 'lisi1', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2023-06-06', 4, '15878897131', '广西桂林', '22@qq.com', '2023-06-14', 123131, 1, 1, NULL);
INSERT INTO `userinfo` VALUES (27, 'caizhengbu1', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2023-06-14', 3, '15878897131', '广西桂林', '2425@qq.com', '2023-06-14', 21313, 5, 1, '/MyOA/layui/upload/jiaoyou.jpg');
INSERT INTO `userinfo` VALUES (28, 'caizhengbu2', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2023-06-14', 3, '15878897131', '广西桂林', '222@qq.com', '2023-06-14', 12313, 3, 1, '/MyOA/layui/upload/bg.jpg');
INSERT INTO `userinfo` VALUES (29, 'renshibu1', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2023-06-14', 2, '15878897131', '广西桂林', '12313@qq.com', '2023-06-14', 12313, 2, 1, '/MyOA/layui/upload/bg.jpg');
INSERT INTO `userinfo` VALUES (30, '66cc', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2023-06-13', 7, '15878897131', '广西桂林', '1231@qq.com', '2023-06-13', 123123, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (36, 'www', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 1, '13870096882', '江西省', '287@qq.com', '2019-07-09', 18000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (37, 'www123', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 1, '13870096882', '江西省', '287@qq.com', '2019-07-09', 18000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (38, 'www111', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 1, '13870096882', '江西省', '287@qq.com', '2019-07-09', 18000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (39, 'www22', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 1, '13870096882', '江西省', '287@qq.com', '2019-07-09', 18000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (40, 'aaa123', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 1, '13870096882', '江西省', '287@qq.com', '2019-07-09', 18000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (41, 'aaa1231', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 1, '13870096882', '江西省', '287@qq.com', '2019-07-09', 18000, 5, 1, NULL);
INSERT INTO `userinfo` VALUES (42, 'aaa12322', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2000-07-01', 1, '13870096882', '江西省', '287@qq.com', '2019-07-09', 18000, 5, 1, NULL);

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `roleid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES (1, 1, 1);
INSERT INTO `userrole` VALUES (2, 2, 2);
INSERT INTO `userrole` VALUES (3, 3, 3);
INSERT INTO `userrole` VALUES (4, 4, 4);
INSERT INTO `userrole` VALUES (5, 5, 5);
INSERT INTO `userrole` VALUES (6, 6, 5);
INSERT INTO `userrole` VALUES (7, 7, 5);
INSERT INTO `userrole` VALUES (8, 17, 5);
INSERT INTO `userrole` VALUES (9, 16, 3);
INSERT INTO `userrole` VALUES (10, 21, 5);
INSERT INTO `userrole` VALUES (11, 22, 4);
INSERT INTO `userrole` VALUES (12, 23, 6);
INSERT INTO `userrole` VALUES (13, 24, 6);
INSERT INTO `userrole` VALUES (14, 25, 6);
INSERT INTO `userrole` VALUES (15, 26, 2);
INSERT INTO `userrole` VALUES (16, 27, 5);
INSERT INTO `userrole` VALUES (17, 29, 2);
INSERT INTO `userrole` VALUES (18, 28, 3);
INSERT INTO `userrole` VALUES (19, 39, 5);

SET FOREIGN_KEY_CHECKS = 1;
