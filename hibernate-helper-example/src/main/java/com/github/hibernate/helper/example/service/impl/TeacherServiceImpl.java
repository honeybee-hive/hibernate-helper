/*
 * TeacherServiceImpl  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.service.impl;

import com.github.hibernate.helper.HibernateHelper;
import com.github.hibernate.helper.example.entity.Teacher;
import com.github.hibernate.helper.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * [关于类内容的描述]
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private HibernateHelper helper;

    @Transactional(rollbackFor = Exception.class)
    public String addTeacher(Teacher teacher) {
        return helper.save(teacher).toString();
    }
}
