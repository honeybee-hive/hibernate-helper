/*
 * OrderEntity.java	 1.0   2015-1-9
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.hibernate.helper.condition;

import lombok.Data;

/**
 * HQL排序类
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2015-1-9 zy 初版
 */
@Data
public class SQLOrder {

    private String fieldName;

    private String order;

    private int orderNumber;

    public SQLOrder() {

    }

    public SQLOrder(String fieldName, String order, int orderNumber) {
        super();
        this.fieldName = fieldName;
        this.order = order;
        this.orderNumber = orderNumber;
    }

    public static SQLOrder builder(String fieldName, String order, int orderNumber) {
        return new SQLOrder(fieldName, order, orderNumber);
    }

}
