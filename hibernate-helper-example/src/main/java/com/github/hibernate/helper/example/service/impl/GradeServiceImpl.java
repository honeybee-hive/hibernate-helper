/*
 * GradeService  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.service.impl;

import com.github.hibernate.helper.example.dao.GradeTeacherDao;
import com.github.hibernate.helper.example.dto.GradeTeacherDTO;
import com.github.hibernate.helper.example.dto.condition.GradeTeacherQuery;
import com.github.hibernate.helper.example.entity.Grade;
import com.github.hibernate.helper.example.service.GradeService;
import com.github.hibernate.helper.service.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class GradeServiceImpl extends CrudServiceImpl<Grade, String> implements GradeService {

    @Autowired
    private GradeTeacherDao gradeDao;

    @Transactional(rollbackFor = Exception.class)
    public void deleteByTeacherId(String teacherId) {
        gradeDao.deleteGradeByTeacherId(teacherId);
    }

    @Transactional(readOnly = true)
    public List<GradeTeacherDTO> findGradeTeacherDTO(GradeTeacherQuery findGradeTeacher) {
        return gradeDao.findGradeTeacherDTO(findGradeTeacher);
    }

    @Transactional(readOnly = true)
    public Page<GradeTeacherDTO> findPageByGradeTeacherDTO(GradeTeacherQuery findGradeTeacher, int page, int size) {
        return gradeDao.findPageByGradeTeacherDTO(findGradeTeacher, page, size);
    }

}
