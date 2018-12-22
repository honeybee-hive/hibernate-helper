/*
 * PageBy  1.0  2018-11-20
 */
package com.github.hibernate.helper.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * 分页条件
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-20 zhuyan 初版
 */
@Data
@ApiModel(value = "PageBy", description = "分页条件")
public class PageBy<T> {

    @ApiModelProperty(value = "当前页，必须从0开始", required = true, dataType = "int")
    int page;

    @ApiModelProperty(value = "每页显示数量，最少从1开始", required = true, dataType = "int")
    int size;

    @Valid
    @ApiModelProperty(value = "检索条件", required = false)
    T condition;

}
