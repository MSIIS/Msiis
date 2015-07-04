package com.util.tips;



/**
 * 提示信息工具类
 * 
 * @author liangzh
 *
 * 2013年10月22日 上午9:22:55
 *
 */
public class TipUtils {

    /**
     * 获取提示信息
     * 
     * @param key
     * @param params
     * @return
     */
    public static String getTipInfo(String key, String... params) {
        try {
//            return BaseConfig.getValue(key, params, TipConfigEnum.TIPS_TIP);
        } catch (Exception e) {
        }
        return "";
    }
}
