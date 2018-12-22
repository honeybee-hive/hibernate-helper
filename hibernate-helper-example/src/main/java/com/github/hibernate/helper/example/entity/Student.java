/*
 * Student  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 学生实体类
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
@Entity
@Table(name = "sch_student", schema = "example_db", catalog = "")
public class Student implements Serializable {
    private static final long serialVersionUID = 5666010592477962738L;
    private String studentId;
    private String studentCode;
    private String studentName;
    private String studentState;
    private String studentSex;
    private Date studentBirthday;
    private Date studentStartSchool;
    private Date studentFinishSchool;
    private String gradeId;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "student_id")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_code")
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    @Basic
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_state")
    public String getStudentState() {
        return studentState;
    }

    public void setStudentState(String studentState) {
        this.studentState = studentState;
    }

    @Basic
    @Column(name = "student_sex")
    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    @Basic
    @Column(name = "student_birthday")
    @Temporal(TemporalType.DATE)
    public Date getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(Date studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    @Basic
    @Column(name = "student_start_school")
    @Temporal(TemporalType.DATE)
    public Date getStudentStartSchool() {
        return studentStartSchool;
    }

    public void setStudentStartSchool(Date studentStartSchool) {
        this.studentStartSchool = studentStartSchool;
    }

    @Basic
    @Column(name = "student_finish_school")
    @Temporal(TemporalType.DATE)
    public Date getStudentFinishSchool() {
        return studentFinishSchool;
    }

    public void setStudentFinishSchool(Date studentFinishSchool) {
        this.studentFinishSchool = studentFinishSchool;
    }

    @Basic
    @Column(name = "grade_id")
    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) &&
                Objects.equals(studentCode, student.studentCode) &&
                Objects.equals(studentName, student.studentName) &&
                Objects.equals(studentState, student.studentState) &&
                Objects.equals(studentSex, student.studentSex) &&
                Objects.equals(studentBirthday, student.studentBirthday) &&
                Objects.equals(studentStartSchool, student.studentStartSchool) &&
                Objects.equals(studentFinishSchool, student.studentFinishSchool) &&
                Objects.equals(gradeId, student.gradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentCode, studentName, studentState, studentSex, studentBirthday, studentStartSchool, studentFinishSchool, gradeId);
    }
}
