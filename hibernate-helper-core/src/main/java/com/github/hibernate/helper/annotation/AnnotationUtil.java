/*
 * AnnotationUtil.java    1.0  2015-4-23
 */
package com.github.hibernate.helper.annotation;

import com.github.hibernate.helper.SQLHelper;
import com.github.hibernate.helper.condition.SQLWhere;
import com.github.hibernate.helper.condition.SQLSymbol;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 注解工具类
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2015-4-23 zy 初版
 */
public class AnnotationUtil {

    /**
     * 获取类注解对象
     *
     * @param aclass
     * @param annotationClass
     * @return 返回注解类
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <A extends Annotation> A getClassAnnotation(Class aclass, Class annotationClass) {
        return (A) aclass.getAnnotation(annotationClass);
    }

    /**
     * 获取实体类注解表名
     *
     * @param aclass 表实体类 例： CustomerInfo.class
     * @return 成功返回表名core_db.cus_customer_info，无则返回空串
     */
    @SuppressWarnings({"rawtypes"})
    public static String getTableName(Class aclass) {
        Table table = AnnotationUtil.getClassAnnotation(aclass, Table.class);
        if (table == null) {
            return "";
        } else {
            return table.name();
        }
    }

    /**
     * 获取cloumn注解名
     *
     * @param clazz
     * @param propertyName
     * @return
     */
    public static String getColumn(Class<?> clazz, String propertyName) {
        if (clazz == null || StringUtils.isEmpty(propertyName)) {
            throw new NullPointerException("输入参数不能为空");
        }
        String columnName = "";
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            String methodName = method.getName();
            if (("get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1)).equals(methodName)) {
                Column column = method.getAnnotation(Column.class);
                if (column == null) {
                    return null;
                }
                columnName = column.name();
            }
        }
        return columnName;
    }

    /**
     * 获取实体主键列名
     *
     * @param object
     * @return
     */
    public static SQLWhere getTableId(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            Id idAnnotation = method.getAnnotation(Id.class);
            if (idAnnotation != null) {
                // 通过反射调用带有此注解的方法
                Column columnAnnotation = method.getAnnotation(Column.class);
                String columnName = columnAnnotation.name();
                try {
                    return SQLWhere.builder(columnName, SQLSymbol.EQ.toString(), method.invoke(object));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println(SQLHelper.class.getSimpleName().toLowerCase());

    }

}
