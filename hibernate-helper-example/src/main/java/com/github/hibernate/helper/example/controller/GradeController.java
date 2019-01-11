/*
 * GradeController  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.controller;

import com.github.hibernate.helper.example.dto.GradeTeacherDTO;
import com.github.hibernate.helper.example.dto.condition.GradeQuery;
import com.github.hibernate.helper.example.dto.condition.GradeTeacherQuery;
import com.github.hibernate.helper.example.entity.Grade;
import com.github.hibernate.helper.example.model.PageBy;
import com.github.hibernate.helper.example.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级接口
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
@Slf4j
@RestController
@RequestMapping("/grade")
@Api(tags = {"ExampleGrade"}, description = "班级服务接口")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 增加班级接口
     *
     * @param addGrade
     * @return Grade
     */
    @ResponseBody
    @ApiOperation(value = "增加班级接口")
    @PostMapping(value = "/grade/add")
    public Grade addGrade(@RequestBody Grade addGrade, BindingResult result) {
        String id = gradeService.save(addGrade);
        return gradeService.get(Grade.class, id);
    }

    /**
     * 部分更新班级接口
     *
     * @param updateGrade
     */
    @ResponseBody
    @ApiOperation(value = "部分更新班级接口")
    @PatchMapping(value = "/grade/update")
    public void updatePatch(@RequestBody Grade updateGrade) {
        gradeService.updatePatch(updateGrade);
    }

    /**
     * 删除班级接口
     *
     * @param id
     */
    @ResponseBody
    @ApiOperation(value = "删除班级接口")
    @DeleteMapping(value = "/grade/{id}")
    public void deleteGrade(@PathVariable String id) {
        gradeService.deleteById(Grade.class, id);
    }

    @ResponseBody
    @ApiOperation(value = "根据教师删除班级接口", notes = "")
    @DeleteMapping(value = "/grade/teacher/{id}")
    public void deleteByTeacherId(@PathVariable String id) {
        gradeService.deleteByTeacherId(id);
    }


    /**
     * 获取班级
     *
     * @param id
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取班级")
    @GetMapping("/grade/{id}")
    public Grade getGrade(@PathVariable String id) {
        return gradeService.get(Grade.class, id);
    }


    /**
     * 条件分页班级查询接口
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "班级查询")
    @PostMapping(value = "/grade/find")
    public List<Grade> find() {
        return gradeService.find(Grade.class);
    }


    @ResponseBody
    @ApiOperation(value = "班级条件查询")
    @PostMapping(value = "/grade/findCondition")
    public List<Grade> findCondition(@RequestBody Grade grade) {
        return gradeService.findByCondition(Grade.class, grade);
    }

    @ResponseBody
    @ApiOperation(value = "班级指定条件查询")
    @PostMapping(value = "/grade/findByGrade")
    public List<Grade> findByGrade(@RequestBody GradeQuery gradeQuery) {
        return gradeService.findByCondition(Grade.class, gradeQuery);
    }


    /**
     * 条件分页班级查询接口
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "条件分页班级查询接口")
    @GetMapping(value = "/grade/findPage")
    public Page<Grade> findPage(int page, int size) {
        return gradeService.findByPage(Grade.class, page, size);
    }

    /**
     * 条件分页班级查询接口
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "查询班级及班主任信息")
    @PostMapping(value = "/grade/findGradeTeacherDTO")
    public List<GradeTeacherDTO> findGradeTeacherDTO(@RequestBody GradeTeacherQuery condition) {
        return gradeService.findGradeTeacherDTO(condition);
    }

    /**
     * 条件分页班级查询接口
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "查询班级及班主任信息")
    @PostMapping(value = "/grade/findPageByGradeTeacherDTO")
    public Page<GradeTeacherDTO> findPageByGradeTeacherDTO(@RequestBody PageBy<GradeTeacherQuery> condition) {
        return gradeService.findPageByGradeTeacherDTO(condition.getCondition(), condition.getPage(), condition.getSize());
    }

}
