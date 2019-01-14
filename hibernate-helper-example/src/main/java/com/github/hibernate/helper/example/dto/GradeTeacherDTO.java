/*
 * TeacherDTO  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.dto;

import com.github.hibernate.helper.annotation.SelectColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级及教师信息
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
@Data
public class GradeTeacherDTO implements Serializable {
    private static final long serialVersionUID = -4752759310731060916L;

    private String gradeName;
    private String gradeState;
    private String gradeType;
    private String gradeRemark;

    @SelectColumn(aliasName = "teacher")
    private String teacherId;
    @SelectColumn(aliasName = "teacher")
    private String teacherCode;
    @SelectColumn(aliasName = "teacher")
    private String teacherName;
    @SelectColumn(aliasName = "teacher")
    private Date teacherBirthday;
    @SelectColumn(aliasName = "teacher")
    private String teacherPhone;

}
