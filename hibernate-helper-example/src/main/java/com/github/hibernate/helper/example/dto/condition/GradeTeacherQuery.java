/*
 * GradeCondition  1.0  2018-11-28
 */
package com.github.hibernate.helper.example.dto.condition;

import com.github.hibernate.helper.annotation.SelectWhere;
import com.github.hibernate.helper.condition.SymbolConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 班级条件
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-28 zhuyan 初版
 */
@Data
@ApiModel(description = "班级教师检索条件")
public class GradeTeacherQuery {

    @ApiModelProperty(name = "班级名称")
    @SelectWhere(symbol = SymbolConstants.LIKE)
    private String gradeName;

    @ApiModelProperty(name = "班级状态")
    @SelectWhere(symbol = SymbolConstants.EQ)
    private String gradeState;

    @ApiModelProperty(name = "班级类型")
    @SelectWhere(symbol = SymbolConstants.EQ)
    private String gradeType;

    @ApiModelProperty(name = "教师名称")
    @SelectWhere(aliasName = "teacher", symbol = SymbolConstants.LIKE)
    private String teacherName;

}
