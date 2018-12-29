/*
 * GradeService  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.service;

import com.github.hibernate.helper.example.dto.GradeTeacherDTO;
import com.github.hibernate.helper.example.dto.condition.GradeQuery;
import com.github.hibernate.helper.example.dto.condition.GradeTeacherQuery;
import com.github.hibernate.helper.example.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 班级
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
public interface GradeService {

    /**
     * 保存
     *
     * @param grade
     * @return
     */
    Grade addGrade(Grade grade);

    /**
     * 删除
     *
     * @param gradeId
     */
    void deleteById(String gradeId);

    /**
     * 删除班级
     *
     * @param teacherId
     */
    void deleteByTeacherId(String teacherId);

    /**
     * 指定字段修改(应用于指定字段修改,使用@Query、@Modifying)
     *
     * @param gradeName
     * @param gradeId
     */
    void updateQuerySql(String gradeName, String gradeId);

    /**
     * 指定字段修改（Null的字段不修改）
     *
     * @param updateGrade
     */
    void updatePatch(Grade updateGrade);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Grade findById(String id);

    /**
     * 查询
     *
     * @return
     */
    List<Grade> find();

    /**
     * 实体类条件查询
     *
     * @param grade
     * @return
     */
    List<Grade> findCondition(Grade grade);

    /**
     * 指定条件查询
     *
     * @param grade
     * @return
     */
    List<Grade> findByGrade(GradeQuery grade);

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    Page<Grade> findPage(int page, int size);

    /**
     * 自定义SQL查询
     *
     * @param findGradeTeacher
     * @return
     */
    List<GradeTeacherDTO> findGradeTeacherDTO(GradeTeacherQuery findGradeTeacher);

    /**
     * 自定义SQL分页并指定条件查询
     *
     * @param findGradeTeacher
     * @param page             当前页
     * @param size             记录数
     * @return
     */
    Page<GradeTeacherDTO> findPageByGradeTeacherDTO(GradeTeacherQuery findGradeTeacher, int page, int size);
}
