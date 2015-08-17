package com.util.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by nlf on 2015-7-9.
 */
public class JSonUtils {
    private static final Logger logger = Logger.getLogger(JSonUtils.class);

    /**
     * 对象解析成JSON 串
     *
     * @param obj
     * @return
     */
    public static String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString(obj);

    }

    /**
     * <b>解析对象</b>
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * <b>JSON串转化(泛型- 支持简单数组、简单集合)</b>
     *
     * @param json     json串
     * @param objClass 对象类型
     * @return
     */
    public static <T> List<T> parseList(String json, Class<T> objClass) {
        if (StringUtils.isEmpty(json) || objClass == null) {
            return null;
        }
        try {
            return JSON.parseArray(json, objClass);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * <b>解析简单有序MAP</b>
     *
     * @param json json串
     * @return LinkedHashMap<String, String>
     */
    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, JSONObject> parseSimpleLinkedHashMap(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return JSON.parseObject(json, LinkedHashMap.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

}
