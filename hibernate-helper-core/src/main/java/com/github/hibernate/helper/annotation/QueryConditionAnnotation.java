/*
 * QueryCondition.java	 1.0   2014-12-11
 *
 * 沈阳成林健康科技有限公司
 *
 */

package com.github.hibernate.helper.annotation;

import com.github.hibernate.helper.condition.SymbolConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检索条件注解
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2014-12-11 zy 初版
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryConditionAnnotation {

    /**
     * 别名
     */
    String aliasName() default "";

    /**
     * 字段名
     */
    String name() default "";

    String symbol() default SymbolConstants.EQ;

    String order() default "";

    int orderNumber() default 0;

    // String dateFormat() default "yyyy-MM-dd";

}
