package com.util.tools;

import com.alibaba.fastjson.JSON;

/**
 * Created by nlf on 2015-7-9.
 */
public class JSonUtils {

    public static String  toJSONString(Object obj){
        if(obj==null){
            return  null;
        }
        return JSON.toJSONString(obj);
    }
}
