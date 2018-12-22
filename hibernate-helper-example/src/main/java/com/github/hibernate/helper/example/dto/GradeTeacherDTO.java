/*
 * TeacherDTO  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.dto;

import com.github.hibernate.helper.annotation.QueryFieldAnnotation;
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

    @QueryFieldAnnotation(aliasName = "teacher")
    private String teacherId;
    @QueryFieldAnnotation(aliasName = "teacher")
    private String teacherCode;
    @QueryFieldAnnotation(aliasName = "teacher")
    private String teacherName;
    @QueryFieldAnnotation(aliasName = "teacher")
    private Date teacherBirthday;
    @QueryFieldAnnotation(aliasName = "teacher")
    private String teacherPhone;

}
