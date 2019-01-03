/*
 * GradeDao  1.0  2018-12-03
 */
package com.github.hibernate.helper.example.dao;

import com.github.hibernate.helper.HibernateHelper;
import com.github.hibernate.helper.SQLHelper;
import com.github.hibernate.helper.condition.*;
import com.github.hibernate.helper.example.dto.GradeTeacherDTO;
import com.github.hibernate.helper.example.dto.condition.GradeTeacherQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<GradeTeacherDTO> findGradeTeacherDTO(GradeTeacherQuery findGradeTeacher) {
        //String sqlFields = SQLHelper.getSQLFields(GradeTeacherDTO.class, "grade");
        QueryCondition queryCondition = SQLHelper.getQueryCondition(findGradeTeacher, "grade");

        String sql = "SELECT " + SQLHelper.getSQLFields(GradeTeacherDTO.class, "grade") + " "
                + "     FROM sch_grade AS grade"
                + "         INNER JOIN sch_teacher AS teacher ON grade.teacher_id = teacher.teacher_id"
                + queryCondition.getQueryString()
                + queryCondition.getOrderString();

        return helper.findBySQL(sql, GradeTeacherDTO.class, queryCondition.getQueryParams());
    }

    public Page<GradeTeacherDTO> findPageByGradeTeacherDTO(GradeTeacherQuery findGradeTeacher, int page, int size) {
        String sqlFields = SQLHelper.getSQLFields(GradeTeacherDTO.class, "grade");
        QueryCondition queryCondition = SQLHelper.getQueryCondition(findGradeTeacher, "grade");

        String sql = "SELECT " + sqlFields + " "
                + "     FROM sch_grade AS grade"
                + "         INNER JOIN sch_teacher AS teacher ON grade.teacher_id = teacher.teacher_id"
                + queryCondition.getQueryString()
                + queryCondition.getOrderString();

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
