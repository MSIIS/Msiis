package com.util.tips;

import com.util.config.BaseConfig;
import com.util.config.ConfigEnumInter;
import org.apache.commons.lang3.StringUtils;

/**
 * 公共提示工具类
 * 
 * @author liangzh
 *
 * 2013年10月21日 下午2:35:42
 *
 */
public class TipCommonUtils  {

    /**
     * 成功提示信息
     * 
     * @param exeName
     * @return
     */
    public static String getSuccess(String exeName) {
        try {
            if (StringUtils.isNotEmpty(exeName)) {
                return BaseConfig.getValue("common.successful", new String[]{exeName}, TipConfigEnum.TIPS_COMMON);
            } else {
                return BaseConfig.getValue("common.successful", new String[] { "操作" }, TipConfigEnum.TIPS_COMMON);
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 失败提示信息
     * 
     * @param exeName
     * @return
     */
    public static String getFail(String exeName) {
        try {
            if (StringUtils.isNotEmpty(exeName)) {
                return BaseConfig.getValue("common.fail", new String[] { exeName }, TipConfigEnum.TIPS_COMMON);
            } else {
                return BaseConfig.getValue("common.fail", new String[] { "操作" }, TipConfigEnum.TIPS_COMMON);
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 系统异常提示信息
     * 
     * @return
     */
    public static String getSystemError() {
        try {
            return BaseConfig.getValue("common.system.error", TipConfigEnum.TIPS_COMMON);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 对象不存在提示信息
     * 
     * @param objType
     * @return
     */
    public static String getNotFind(String objType) {
        try {
            return BaseConfig.getValue("common.objNotFind", new String[] { objType }, TipConfigEnum.TIPS_COMMON);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 参数错误提示
     * 
     * @return
     */
    public static String getArgumentError() {
        try {
            return BaseConfig.getValue("common.argument.error", TipConfigEnum.TIPS_COMMON);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 无权限访问提示
     * 
     * @param objType
     * @return
     */
    public static String getNoPermission(String objType) {
        try {
            return BaseConfig.getValue("common.noPermission", new String[] { objType }, TipConfigEnum.TIPS_COMMON);
        } catch (Exception e) {
            return "";
        }
    }

}
