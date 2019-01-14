/*
 * QueryFieldAnnotation.java	 1.0   2014-12-11
 *
 * 沈阳成林健康科技有限公司
 *
 */

package com.github.hibernate.helper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询字段
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2014-12-11 zy 初版
 */
// 字段
@Target(ElementType.FIELD)
// 运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectColumn {

    /**
     * 查询的字段名
     *
     * @return
     */
    String fieldName() default "";

    /**
     * 设置别名
     *
     * @return
     */
    String aliasName() default "";

    boolean ignore() default false;

}
