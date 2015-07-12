package com.util.tools;


import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;



/**
 * 系统属性工具类
 * 
 * @author liangzh
 *
 * 2013年11月5日 上午10:24:28
 *
 */
public class SystemPropertiesUtils {

    private static SystemPropertiesUtils instance = new SystemPropertiesUtils();;
    //主机IP
    private String host_ip = null;
    //主机名称
    private String host_name = null;
    //系统名称
    private String os_name = null;
    //InetAddress对象
    private InetAddress netAddress = null;

    private SystemPropertiesUtils() {
        try {
            this.netAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("unknown host!");
        }
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static SystemPropertiesUtils getInstance() {
        return instance;
    }

    /**
     * 获取主机IP
     * 
     * @return
     */
    public String getHostIp() {
        if (StringUtils.isEmpty(this.host_ip)) {
            if (null == this.netAddress) {
                return null;
            }
            this.host_ip = this.netAddress.getHostAddress(); //get the ip address
        }
        return this.host_ip;
    }

    /**
     * 获取主机名称
     * 
     * @return
     */
    public String getHostName() {
        if (StringUtils.isEmpty(this.host_name)) {
            if (null == this.netAddress) {
                return null;
            }
            this.host_name = this.netAddress.getHostName(); //get the host address
        }
        return this.host_name;
    }

    /**
     * 获取系统名称
     * 
     * @return
     */
    public String getOSName() {
        if (StringUtils.isEmpty(this.os_name)) {
            if (null == this.netAddress) {
                return null;
            }
            this.os_name = System.getProperty("os.name").toLowerCase();
        }
        return this.os_name;
    }
}
