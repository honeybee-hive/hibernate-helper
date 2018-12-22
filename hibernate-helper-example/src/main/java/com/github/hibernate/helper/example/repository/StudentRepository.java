/*
 * StudentRepository  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.repository;

import com.github.hibernate.helper.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 学生SQL
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
public interface StudentRepository extends JpaRepository<Student, String> {
}
