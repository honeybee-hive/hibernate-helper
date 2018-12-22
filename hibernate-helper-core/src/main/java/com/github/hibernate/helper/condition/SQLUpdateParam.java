/*
 * HQLUpdateParam.java	 1.0   2014-10-30
 *
 * 沈阳成林健康科技有限公司
 *
 */

package com.github.hibernate.helper.condition;

import lombok.Data;

/**
 * 更新字段参数
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2014-10-30 zy 初版
 */
@Data
public class SQLUpdateParam {

    private String name;

    private Object value;

    public SQLUpdateParam(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public static SQLUpdateParam builder(String name, Object value) {
        return new SQLUpdateParam(name, value);
    }

}
