/*
 * TeacherController  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.controller;

import com.github.hibernate.helper.example.entity.Teacher;
import com.github.hibernate.helper.example.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 教师接口
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
@Slf4j
@RestController
@Api(tags = {"ExampleTeacher"}, description = "教师服务接口")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 增加教师接口
     *
     * @param addTeacher
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "增加教师接口", notes = "")
    @RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
    public String add(@RequestBody Teacher addTeacher) {
        return teacherService.addTeacher(addTeacher);
    }

}
