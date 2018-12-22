/*
 * Grade  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.entity;

import com.github.hibernate.helper.annotation.QueryConditionAnnotation;
import com.github.hibernate.helper.condition.SymbolConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 班级实体类
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
@ApiModel(description = "班级类")
@Entity
@Table(name = "sch_grade", schema = "example_db", catalog = "")
public class Grade implements Serializable {
    private static final long serialVersionUID = 3690120078338704917L;

    @ApiModelProperty(value = "班级编码", required = true)
    private String gradeId;
    @ApiModelProperty(value = "班级名称", required = true)
    @QueryConditionAnnotation(symbol = SymbolConstants.LIKE)
    private String gradeName;
    @ApiModelProperty(value = "班级状态", required = true)
    @QueryConditionAnnotation(symbol = SymbolConstants.EQ)
    private String gradeState;
    @ApiModelProperty(value = "班级类型", required = true)
    @QueryConditionAnnotation(symbol = SymbolConstants.EQ)
    private String gradeType;
    @ApiModelProperty(value = "班级备注", required = false)
    @QueryConditionAnnotation(symbol = SymbolConstants.LIKE)
    private String gradeRemark;
    @ApiModelProperty(value = "教师编码", required = false)
    @QueryConditionAnnotation(symbol = SymbolConstants.EQ)
    private String teacherId;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "grade_id")
    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    @Basic
    @Column(name = "grade_name")
    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Basic
    @Column(name = "grade_state")
    public String getGradeState() {
        return gradeState;
    }

    public void setGradeState(String gradeState) {
        this.gradeState = gradeState;
    }

    @Basic
    @Column(name = "grade_type")
    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    @Basic
    @Column(name = "grade_remark")
    public String getGradeRemark() {
        return gradeRemark;
    }

    public void setGradeRemark(String gradeRemark) {
        this.gradeRemark = gradeRemark;
    }

    @Basic
    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(gradeId, grade.gradeId) &&
                Objects.equals(gradeName, grade.gradeName) &&
                Objects.equals(gradeState, grade.gradeState) &&
                Objects.equals(gradeType, grade.gradeType) &&
                Objects.equals(gradeRemark, grade.gradeRemark) &&
                Objects.equals(teacherId, grade.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeId, gradeName, gradeState, gradeType, gradeRemark, teacherId);
    }
}
