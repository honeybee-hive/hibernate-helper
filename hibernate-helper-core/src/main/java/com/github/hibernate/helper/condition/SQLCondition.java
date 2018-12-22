/**
 *
 */
package com.github.hibernate.helper.condition;

import lombok.Data;

/**
 * 条件参数
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2014-10-30 zy 初版
 */
@Data
public class SQLCondition {

    // 条件名称
    public String name;
    // 条件符合
    public String symbol;
    // 条件值
    public Object value;

    public SQLCondition(String name, String symbol, Object value) {
        super();
        this.name = name;
        this.symbol = symbol;
        this.value = value;
    }

    public static SQLCondition builder(String name, String symbol, Object value) {
        return new SQLCondition(name, symbol, value);
    }


}
