/*
 * Teacher  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 教师实体类
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
@Entity
@Table(name = "sch_teacher", schema = "example_db", catalog = "")
public class Teacher implements Serializable {
    private static final long serialVersionUID = -4278504166573369751L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String teacherId;
    private String teacherCode;
    private String teacherName;
    private String teacherState;
    private String teacherSex;
    private Date teacherBirthday;
    private String teacherPhone;
    private String teacherDesc;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "teacher_code")
    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    @Basic
    @Column(name = "teacher_name")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "teacher_state")
    public String getTeacherState() {
        return teacherState;
    }

    public void setTeacherState(String teacherState) {
        this.teacherState = teacherState;
    }

    @Basic
    @Column(name = "teacher_sex")
    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    @Basic
    @Column(name = "teacher_birthday")
    @Temporal(TemporalType.DATE)
    public Date getTeacherBirthday() {
        return teacherBirthday;
    }

    public void setTeacherBirthday(Date teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }

    @Basic
    @Column(name = "teacher_phone")
    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    @Basic
    @Column(name = "teacher_desc")
    public String getTeacherDesc() {
        return teacherDesc;
    }

    public void setTeacherDesc(String teacherDesc) {
        this.teacherDesc = teacherDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) &&
                Objects.equals(teacherCode, teacher.teacherCode) &&
                Objects.equals(teacherName, teacher.teacherName) &&
                Objects.equals(teacherState, teacher.teacherState) &&
                Objects.equals(teacherSex, teacher.teacherSex) &&
                Objects.equals(teacherBirthday, teacher.teacherBirthday) &&
                Objects.equals(teacherPhone, teacher.teacherPhone) &&
                Objects.equals(teacherDesc, teacher.teacherDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherCode, teacherName, teacherState, teacherSex, teacherBirthday, teacherPhone, teacherDesc);
    }
}
