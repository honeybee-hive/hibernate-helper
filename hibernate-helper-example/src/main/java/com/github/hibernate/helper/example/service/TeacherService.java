/*
 * TeacherService  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.service;

import com.github.hibernate.helper.example.entity.Teacher;

/**
 * 教师接口
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
public interface TeacherService {

    /**
     * 增加教师
     *
     * @param teacher
     * @return
     */
    Teacher addTeacher(Teacher teacher);
}
