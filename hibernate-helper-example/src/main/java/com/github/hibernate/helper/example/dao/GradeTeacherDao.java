/*
 * GradeDao  1.0  2018-12-03
 */
package com.github.hibernate.helper.example.dao;

import com.github.hibernate.helper.HibernateHelper;
import com.github.hibernate.helper.SQLHelper;
import com.github.hibernate.helper.condition.*;
import com.github.hibernate.helper.example.dto.GradeTeacherDTO;
import com.github.hibernate.helper.example.dto.condition.GradeTeacherQuery;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 班级DAO
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-12-03 zhuyan 初版
 */
@Repository
public class GradeTeacherDao {

    @Autowired
    private HibernateHelper helper;

    /**
     * 通过教师删除班级信息
     *
     * @param teacherId 教师
     * @return 删除数量
     */
    public int deleteGradeByTeacherId(String teacherId) {
        Map<String, Object> entity = ImmutableMap.<String, Object>builder()
                .put("teacherId", teacherId)
                .build();
        return helper.executeSQL("DELETE FROM sch_grade WHERE teacher_id= :teacherId", entity);
    }

    public List<GradeTeacherDTO> findGradeTeacherDTO(GradeTeacherQuery findGradeTeacher) {
        QueryCondition queryCondition = SQLHelper.getQueryCondition(findGradeTeacher, "grade");

        String sql = "SELECT " + SQLHelper.getSQLFields(GradeTeacherDTO.class, "grade") + " "
                + "     FROM sch_grade AS grade"
                + "         INNER JOIN sch_teacher AS teacher ON grade.teacher_id = teacher.teacher_id"
                + queryCondition.getQueryString()
                + queryCondition.getOrderString();

        return helper.findBySQL(
                sql,
                GradeTeacherDTO.class,
                queryCondition.getQueryParams());
    }

    public Page<GradeTeacherDTO> findPageByGradeTeacherDTO(GradeTeacherQuery findGradeTeacher, int page, int size) {
        QueryCondition queryCondition = SQLHelper.getQueryCondition(findGradeTeacher, "grade");

        String sql = "SELECT " + SQLHelper.getSQLFields(GradeTeacherDTO.class, "grade")
                + "     FROM sch_grade AS grade"
                + "         INNER JOIN sch_teacher AS teacher ON grade.teacher_id = teacher.teacher_id"
                + queryCondition.getQueryString();

        String countSql = "SELECT COUNT(grade.grade_id) "
                + "     FROM sch_grade AS grade"
                + "         INNER JOIN sch_teacher AS teacher ON grade.teacher_id = teacher.teacher_id"
                + queryCondition.getQueryString();

        return helper.findPageBySQL(
                sql,
                countSql,
                GradeTeacherDTO.class,
                queryCondition.getQueryParams(),
                page,
                size);
    }

}
