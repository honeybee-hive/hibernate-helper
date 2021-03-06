/*
 * GradeService  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.service;

import com.github.hibernate.helper.example.dto.GradeTeacherDTO;
import com.github.hibernate.helper.example.dto.condition.GradeQuery;
import com.github.hibernate.helper.example.dto.condition.GradeTeacherQuery;
import com.github.hibernate.helper.example.entity.Grade;
import com.github.hibernate.helper.service.CrudService;
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
public interface GradeService extends CrudService<Grade, String> {

    /**
     * 删除班级
     *
     * @param teacherId
     */
    void deleteByTeacherId(String teacherId);

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
