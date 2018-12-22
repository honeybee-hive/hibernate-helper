/*
 * GradeService  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.service.impl;

import com.github.hibernate.helper.example.dao.GradeDao;
import com.github.hibernate.helper.example.dto.GradeTeacherDTO;
import com.github.hibernate.helper.example.dto.condition.GradeQuery;
import com.github.hibernate.helper.example.dto.condition.GradeTeacherQuery;
import com.github.hibernate.helper.example.entity.Grade;
import com.github.hibernate.helper.example.service.GradeService;
import com.github.hibernate.helper.example.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private GradeRepository gradeRepository;

    @Transactional(rollbackFor = Exception.class)
    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByTeacherId(String teacherId) {
        gradeRepository.deleteByTeacherId(teacherId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePatch(Grade updateGrade) {
        gradeDao.updatePatch(updateGrade);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateQuerySql(String gradeName, String gradeId) {
        gradeRepository.updateQuerySQL(gradeName, gradeId);
    }

    @Transactional(readOnly = true)
    public Grade findById(String id) {
        return gradeRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public Page<Grade> findGradeNameSQL(String gradeName, Pageable pageable) {
        return gradeRepository.findGradeNameSQL(gradeName, pageable);
    }

    @Transactional(readOnly = true)
    public List<Grade> find() {
        return gradeDao.find();
    }

    @Transactional(readOnly = true)
    public List<Grade> findCondition(Grade grade) {
        return gradeDao.findCondition(grade);
    }

    @Transactional(readOnly = true)
    public List<Grade> findByGrade(GradeQuery grade) {
        return gradeDao.findByGrade(grade);
    }


    @Transactional(readOnly = true)
    public Page<Grade> findPage(int page, int size) {
        return gradeDao.findPage(page, size);
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
