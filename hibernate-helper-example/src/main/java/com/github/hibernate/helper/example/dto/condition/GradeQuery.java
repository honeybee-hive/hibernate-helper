/*
 * C_Grade  1.0  2018-12-20
 */
package com.github.hibernate.helper.example.dto.condition;

import com.github.hibernate.helper.annotation.SelectWhere;
import com.github.hibernate.helper.condition.SymbolConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 班级查询条件设置
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-12-20 zhuyan 初版
 */
@Data
@ApiModel(description = "自定义班级检索条件")
public class GradeQuery {

    @ApiModelProperty(name = "班级名称")
    @SelectWhere(symbol = SymbolConstants.LIKE)
    private String gradeName;

    @ApiModelProperty(name = "班级状态")
    @SelectWhere(symbol = SymbolConstants.EQ)
    private String gradeState;

    @ApiModelProperty(name = "班级类型")
    @SelectWhere(symbol = SymbolConstants.EQ)
    private String gradeType;

}
