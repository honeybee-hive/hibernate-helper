/*
 * SQLHelper  1.0  2018-12-21
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.hibernate.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SQL处理类
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-12-21 zhuyan 初版
 */
@Slf4j
public class HelperUtils {

    /**
     * Map to Object
     *
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();
        BeanUtils.populate(obj, map);

        return obj;
    }

    /**
     * Object to Map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null)
            return null;

        BeanMap beanMap = new BeanMap(obj);

        return Optional.ofNullable(beanMap).map(v -> {
                    Map params = v.entrySet().stream()
                            .filter(e -> checkValue(e.getValue()))
                            .filter(e -> !e.getKey().equals("class"))
                            .collect(Collectors.toMap(
                                    e -> (String) e.getKey(),
                                    e -> e.getValue()
                            ));
                    return params;
                }
        ).orElse(null);
    }

    /**
     * 检测对象是否为空或""
     *
     * @param object
     * @return
     */
    public static boolean checkValue(Object object) {
        if (object instanceof String && "".equals(object)) {
            return false;
        }
        if (null == object) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }

    /**
     * name to sql field(驼峰式转换例如：roleId = role_id)
     *
     * @param name
     * @return
     */
    public static String toColumn(String name) {
        StringBuffer columnName = new StringBuffer("");
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                columnName.append('_').append(c);
            } else {
                columnName.append(c);
            }
        }
        return columnName.toString().toLowerCase();
    }

    /**
     * 实体类字段
     *
     * @param queryClass
     * @return
     * @author zcq
     */
    public static Field[] getWholeFields(Class<?> queryClass) {
        Field[] fields = queryClass.getDeclaredFields();
        Class<?> superClass = queryClass.getSuperclass();
        while (superClass != null) {
            Field[] tempField = superClass.getDeclaredFields();
            Field[] tempResultField = new Field[fields.length + tempField.length];
            for (int i = 0; i < fields.length; i++) {
                tempResultField[i] = fields[i];
            }
            for (int j = 0; j < tempField.length; j++) {
                tempResultField[j + fields.length] = tempField[j];
            }
            fields = tempResultField;
            superClass = superClass.getSuperclass();
        }
        Field.setAccessible(fields, true);
        return fields;
    }


}
