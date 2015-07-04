package com.util.tools;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <h3>泛型工具类</h3>
 * 
 * @author liangzh
 *
 * @since  common-0.0.1-SNAPSHOT
 * 
 * 2013年8月29日 下午3:13:02
 *
 */
@SuppressWarnings("rawtypes")
public class GenericsUtils {
    private static final Log logger = LogFactory.getLog(GenericsUtils.class);

    /**
     * <b>获取泛型的类型</b>
     * 
     * @param clazz
     * @return Class
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * <b>获取泛型的类型</b>
     * 
     * @param clazz Class类型
     * @param index 泛型序号
     * 
     * @return Class
     */
    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            logger.debug(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if ((index >= params.length) || (index < 0)) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);

            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return ((Class) params[index]);
    }
}