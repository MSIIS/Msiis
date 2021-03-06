package com.util.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h3>配置信息基类</h3>
 * 
 * @author liangzh
 * 
 * @since  common-0.0.1-SNAPSHOT
 * 
 * 2013年8月29日 下午3:22:30
 */
public class BaseConfig {

    private final static String MAIN_CONFIG_PATH = "properties/system";
    private static Map<String, ResourceBundle> bMap = new HashMap<String, ResourceBundle>();
    private static final Logger logger = LoggerFactory.getLogger(BaseConfig.class);

    static {
        try {
            bMap.put(MAIN_CONFIG_PATH, ResourceBundle.getBundle(MAIN_CONFIG_PATH, Locale.SIMPLIFIED_CHINESE));
        } catch (Exception e) {
            logger.warn("【读取配置】properties/system.properties不存在");
        }
    }

    /**
     * <b>获取key对应值(如果key不存在则返回NULL)</b>
     * (如果参数只有key,则从主配置文件获取key对应的值，否则从指定配置文件获取key对应的值configEnumInters长度为1,即只识别第一个)
     * 
     * @param key properties文件key值
     * @param configEnumInters properties文件类型
     * 
     *
     * @return key对应的值
     */
    public static String getValue(String key, ConfigEnumInter... configEnumInters) {
        ResourceBundle bundle = null;
        if (configEnumInters == null || configEnumInters.length == 0) {
            bundle = getBundle(MAIN_CONFIG_PATH);
        } else {
            bundle = getBundle(configEnumInters[0]);
        }

        try {
            return new String(bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            logger.warn("【读取配置-getValue(String key, ConfigEnumInter... configEnumInters)】key=" + key);
            return null;
        }
    }

    /**
     * <b>获取key对应值(如果key不存在则返回NULL)</b>
     * (如果参数只有key,则从主配置文件获取key对应的值，否则从指定配置文件获取key对应的值configEnumInters长度为1,即只识别第一个)
     * 
     * @param key properties文件key值
     * @param params properties文件value值里包含的动态参数值
     * @param configEnumInters properties文件类型
     * 
     *
     * @return key对应的值
     */
    public static String getValue(String key, String[] params, ConfigEnumInter... configEnumInters) {
        ResourceBundle bundle = null;
        if (configEnumInters == null || configEnumInters.length == 0) {
            bundle = getBundle(MAIN_CONFIG_PATH);
        } else {
            bundle = getBundle(configEnumInters[0]);
        }

        try {
            String val = new String(bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    val = val.replace("{" + i + "}", params[i]);
                }
            }
            return val;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <b>获取key对应Integer值</b>
     * (如果参数只有key,则从主配置文件获取key对应的值，否则从指定配置文件获取key对应的值,configEnumInters长度为1,即只识别第一个)
     * 
     * @param key properties文件key值
     * @param configEnumInters properties文件类型
     * 
     *
     * @return key对应的Integer值
     */
    public static Integer getIntValue(String key, ConfigEnumInter... configEnumInters) {
        try {
            return Integer.parseInt(getValue(key, configEnumInters));
        } catch (Exception e) {
            logger.warn("【读取配置-getIntValue】key=" + key);
            return null;
        }
    }

    /**
     * <b>获取key对应Double值</b>
     * (如果参数只有key,则从主配置文件获取key对应的值，否则从指定配置文件获取key对应的值,configEnumInters长度为1,即只识别第一个)
     * 
     * @param key properties文件key值
     * @param configEnumInters properties文件类型
     * 
     *
     * @return key对应的Double值
     */
    public static Double getDoubleValue(String key, ConfigEnumInter... configEnumInters) {
        try {
            return Double.parseDouble(getValue(key, configEnumInters));
        } catch (Exception e) {
            logger.warn("【读取配置-getDoubleValue】key=" + key);
            return null;
        }
    }

    /**
     * <b>获取key对应Long值</b>
     * (如果参数只有key,则从主配置文件获取key对应的值，否则从指定配置文件获取key对应的值,configEnumInters长度为1,即只识别第一个)
     * 
     * @param key properties文件key值
     * @param configEnumInters properties文件类型
     * 
     *
     * @return key对应的Long值
     */
    public static Long getLongValue(String key, ConfigEnumInter... configEnumInters) {
        try {
            return Long.parseLong(getValue(key, configEnumInters));
        } catch (Exception e) {
            logger.warn("【读取配置-getLongValue】key=" + key);
            return null;
        }
    }

    /**
     * <b>获取key对应Short值</b>
     * (如果参数只有key,则从主配置文件获取key对应的值，否则从指定配置文件获取key对应的值,configEnumInters长度为1,即只识别第一个)
     * 
     * @param key properties文件key值
     * @param configEnumInters properties文件类型
     * 
     *
     * @return key对应的Short值
     */
    public static Short getShortValue(String key, ConfigEnumInter... configEnumInters) {
        try {
            return Short.parseShort(getValue(key, configEnumInters));
        } catch (Exception e) {
            logger.warn("【读取配置-getShortValue】key=" + key);
            return null;
        }
    }

    /**
     * <b>获取key对应Boolean值</b>
     * (如果参数只有key,则从主配置文件获取key对应的值，否则从指定配置文件获取key对应的值,configEnumInters长度为1,即只识别第一个)
     * 
     * @param key properties文件key值
     * @param configEnumInters properties文件类型
     * 
     *
     * @return key对应的Boolean值
     */
    public static Boolean getBooleanValue(String key, ConfigEnumInter... configEnumInters) {
        try {
            return Boolean.parseBoolean(getValue(key, configEnumInters));
        } catch (Exception e) {
            logger.warn("【读取配置-getBooleanValue】key=" + key);
            return null;
        }
    }

    /**
     * <b>获取ResourceBundle</b>
     * (该方法可以获取某个properties文件对应的ResourceBundle)
     * 
     * @param configEnumInter properties文件类型
     * 
     *
     * @return properties文件对应ResourceBundle
     */
    public static ResourceBundle getBundle(ConfigEnumInter configEnumInter) {
        return getBundle(configEnumInter.getValue());
    }

    /**
     * <b>获取ResourceBundle</b>
     * (该方法可以获取某个properties文件对应的ResourceBundle)
     * 
     * @param propertiesPath properties文件类路径
     * 
     * @return properties文件对应ResourceBundle
     */
    public static ResourceBundle getBundle(String propertiesPath) {
        try {
            ResourceBundle bundle = bMap.get(propertiesPath);
            if (bundle == null) {
                bundle = ResourceBundle.getBundle(propertiesPath, Locale.SIMPLIFIED_CHINESE);
                bMap.put(propertiesPath, bundle);
            }
            return bundle;
        } catch (Exception e) {
            logger.warn("【读取配置-getBundle】propertiesPath=" + propertiesPath);
            return null;
        }
    }
}
