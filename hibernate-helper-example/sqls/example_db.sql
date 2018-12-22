SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sch_grade
-- ----------------------------
CREATE TABLE `sch_grade` (
  `grade_id` varchar(64) NOT NULL COMMENT '班级编号',
  `grade_name` varchar(100) NOT NULL COMMENT '班级名称',
  `grade_state` varchar(30) NOT NULL COMMENT '班级状态',
  `grade_type` varchar(30) DEFAULT NULL,
  `grade_remark` varchar(500) DEFAULT NULL COMMENT '班级信息',
  `teacher_id` varchar(64) NOT NULL COMMENT '班主任',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sch_grade
-- ----------------------------
INSERT INTO `sch_grade` VALUES ('402880ee6759516901675959b9fb0002', '三年一班', '1', '1', '111', '402880ee6759516901675952ace90001');
INSERT INTO `sch_grade` VALUES ('402880ee6759516901675959fd3d0003', '三年二班', '1', '1', '', '402880ee6759516901675951be460000');
INSERT INTO `sch_grade` VALUES ('402880ee675951690167595ad6a50006', '三年三班', '1', '1', '', '402880ee675951690167595a91cc0005');
INSERT INTO `sch_grade` VALUES ('402880ee675951690167595b3f1d0008', '三年四班', '2', '2', '不被修改的备注', '402880ee675951690167595b10a80007');
INSERT INTO `sch_grade` VALUES ('402880ee675951690167595b8975000a', '三年五班', '1', '1', '', '402880ee675951690167595b65ec0009');
INSERT INTO `sch_grade` VALUES ('402880ee675951690167595be597000c', '三年六班', '1', '1', '', '402880ee675951690167595bbe40000b');
INSERT INTO `sch_grade` VALUES ('402880ee675951690167595c3436000e', '三年七班', '1', '1', '', '402880ee675951690167595c0efe000d');
INSERT INTO `sch_grade` VALUES ('402880ee675951690167595c94f10010', '三年八班', '1', '1', '', '402880ee675951690167595c6bf8000f');
INSERT INTO `sch_grade` VALUES ('402880ee675951690167595cd9ca0012', '三年九班', '1', '1', '', '402880ee675951690167595cb70b0011');
INSERT INTO `sch_grade` VALUES ('402880ee67a5b1e40167a60301b00000', '四年一班', '1', '1', '', '402880ee6759516901675951be460000');
INSERT INTO `sch_grade` VALUES ('402880ee67a5b1e40167a607518c0004', '四年二班', '1', '1', '', '402880ee6759516901675951be460000');

-- ----------------------------
-- Table structure for sch_student
-- ----------------------------
CREATE TABLE `sch_student` (
  `student_id` varchar(64) NOT NULL COMMENT '主键',
  `student_code` varchar(20) NOT NULL COMMENT '学号',
  `student_name` varchar(255) NOT NULL COMMENT '学生姓名',
  `student_state` varchar(30) NOT NULL COMMENT '学生状态（1=正常，2=已毕业，3=退学，4=开除）',
  `student_sex` varchar(30) NOT NULL,
  `student_birthday` date DEFAULT NULL COMMENT '出生日期',
  `student_start_school` date NOT NULL COMMENT '入学日期',
  `student_finish_school` date DEFAULT NULL COMMENT '毕业日期',
  `grade_id` varchar(255) NOT NULL COMMENT '所属班级',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sch_student
-- ----------------------------

-- ----------------------------
-- Table structure for sch_teacher
-- ----------------------------
CREATE TABLE `sch_teacher` (
  `teacher_id` varchar(64) NOT NULL COMMENT '主键',
  `teacher_code` varchar(20) NOT NULL COMMENT '工号',
  `teacher_name` varchar(60) NOT NULL COMMENT '姓名',
  `teacher_state` varchar(30) DEFAULT NULL COMMENT '教师状态',
  `teacher_sex` varchar(30) NOT NULL COMMENT '性别',
  `teacher_birthday` date NOT NULL COMMENT '出生日期',
  `teacher_phone` varchar(22) NOT NULL COMMENT '手机号码',
  `teacher_desc` varchar(500) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sch_teacher
-- ----------------------------
INSERT INTO `sch_teacher` VALUES ('402880ee6759516901675951be460000', '10001', '张一', '1', 'male', '1984-05-19', '13100000001', '');
INSERT INTO `sch_teacher` VALUES ('402880ee6759516901675952ace90001', '10002', '张二', '1', 'male', '1981-08-10', '13100000002', '');
INSERT INTO `sch_teacher` VALUES ('402880ee675951690167595a91cc0005', '10003', '张三', '1', 'female', '1981-08-10', '13100000003', '');
INSERT INTO `sch_teacher` VALUES ('402880ee675951690167595b10a80007', '10004', '张四', '1', 'female', '1981-08-10', '13100000004', '');
INSERT INTO `sch_teacher` VALUES ('402880ee675951690167595b65ec0009', '10005', '张五', '1', 'female', '1981-08-10', '13100000005', '');
INSERT INTO `sch_teacher` VALUES ('402880ee675951690167595bbe40000b', '10006', '张六', '1', 'female', '1981-08-10', '13100000006', '');
INSERT INTO `sch_teacher` VALUES ('402880ee675951690167595c0efe000d', '10007', '张七', '1', 'female', '1981-08-10', '13100000007', '');
INSERT INTO `sch_teacher` VALUES ('402880ee675951690167595c6bf8000f', '10008', '张八', '1', 'female', '1981-08-10', '13100000008', '');
INSERT INTO `sch_teacher` VALUES ('402880ee675951690167595cb70b0011', '10009', '张九', '1', 'female', '1981-08-10', '13100000009', '');

-- ----------------------------
-- Table structure for sch_user
-- ----------------------------
CREATE TABLE `sch_user` (
  `user_id` varchar(64) NOT NULL,
  `user_code` varchar(20) NOT NULL,
  `user_password` varchar(64) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_phone` varchar(22) DEFAULT NULL,
  `user_sex` varchar(30) DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_icard` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_state` varchar(30) DEFAULT NULL,
  `user_desc` varchar(255) DEFAULT NULL,
  `user_remark` varchar(255) DEFAULT NULL,
  `create_user_id` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `flag` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sch_user
-- ----------------------------
INSERT INTO `sch_user` VALUES ('10000', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', '', 'male', '1900-12-22', null, null, null, null, null, '10000', '2018-11-29 10:57:56', '1', null, null);
INSERT INTO `sch_user` VALUES ('10001', 'wangxiaona', 'e10adc3949ba59abbe56e057f20f883e', '王晓娜', null, 'female', '1993-03-24', null, null, null, null, null, '10000', '2018-11-29 11:00:13', '1', null, null);
INSERT INTO `sch_user` VALUES ('10002', 'guanrui', 'e10adc3949ba59abbe56e057f20f883e', '关芮', null, 'female', null, null, null, null, null, null, '10000', '2018-11-29 11:00:42', '1', null, null);
INSERT INTO `sch_user` VALUES ('10003', 'zhaotianye', 'e10adc3949ba59abbe56e057f20f883e', '赵天野', null, 'male', '1985-06-04', null, null, null, null, null, '10000', '2018-11-29 11:01:19', '1', null, null);
INSERT INTO `sch_user` VALUES ('10004', 'wanghaixin', 'e10adc3949ba59abbe56e057f20f883e', '王海鑫', null, 'male', null, null, null, null, null, null, '10000', '2018-11-29 11:01:43', '1', null, null);
INSERT INTO `sch_user` VALUES ('10005', 'chenbo', 'e10adc3949ba59abbe56e057f20f883e', '陈博', null, 'female', '1979-07-29', null, null, null, null, null, '10000', '2018-11-29 11:02:38', '1', null, null);
INSERT INTO `sch_user` VALUES ('10006', 'yuxin', 'e10adc3949ba59abbe56e057f20f883e', '于昕', null, 'female', null, null, null, null, null, null, '10000', '2018-11-29 11:03:09', '1', null, null);
INSERT INTO `sch_user` VALUES ('10007', 'yanglinlin', 'e10adc3949ba59abbe56e057f20f883e', '杨琳琳', null, 'female', '1988-06-17', null, null, null, null, null, '10000', '2018-11-29 11:03:59', '1', null, null);
INSERT INTO `sch_user` VALUES ('10008', 'zhouxiushuang', 'e10adc3949ba59abbe56e057f20f883e', '周秀爽', null, 'female', null, null, null, null, null, null, '10000', '2018-11-29 11:04:26', '1', null, null);
