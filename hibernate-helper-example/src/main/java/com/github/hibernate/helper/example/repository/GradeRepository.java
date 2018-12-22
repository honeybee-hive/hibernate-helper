/*
 * GradeRepository  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.repository;

import com.github.hibernate.helper.example.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 班级SQL
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
public interface GradeRepository extends JpaRepository<Grade, String> {

    @Modifying
    @Query(value = "UPDATE sch_grade SET grade_name=?1 WHERE grade_id=?2 ", nativeQuery = true)
    public void updateQuerySQL(String gradeName, String gradeId);

    @Modifying
    @Query(value = "DELETE FROM sch_grade WHERE teacher_id=?1", nativeQuery = true)
    void deleteByTeacherId(String teacherId);

    @Query(value = "SELECT * FROM sch_grade WHERE grade_name LIKE %?1%",
            countQuery = "SELECT count(*) FROM sch_grade WHERE grade_name LIKE %?1%",
            nativeQuery = true)
    Page<Grade> findGradeNameSQL(String gradeName, Pageable pageable);

}
