/*
 * QueryCondition.java	 1.0   2014-12-11
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.hibernate.helper.condition;

import lombok.Data;

import java.util.HashMap;

/**
 * 检索条件基本类
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2014-12-11 zy 初版
 */
@Data
public class QueryCondition {

    // 检索语句
    private String queryString = "";
    // 排序语句
    private String orderString = "";
    // 检索条件值
    private HashMap<String, Object> queryParams = new HashMap<String, Object>();

}
