package com.util.tools;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by LDB on 14-8-12.
 */
public class CookieUtils {
    public static void removeCookie(HttpServletResponse httpservletresponse, String cookieName) {
        try {
            Cookie cookie = new Cookie(URLEncoder.encode(cookieName, "UTF-8"), "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            httpservletresponse.addCookie(cookie);
        } catch (Exception exception) {
            System.out.println("browser doesn't support Cookie. Please set it supported first.");
        }
    }
    public static String getCookieValue(HttpServletRequest httpservletrequest, String cookieName) {
        Cookie acookie[];
        acookie = httpservletrequest.getCookies();
        if (acookie == null || cookieName == null) {
            return "";
        }
        try {
            for (int i = 0; i < acookie.length; i++) {
                if (acookie[i].getName().equals(cookieName)) {
                    return URLDecoder.decode(acookie[i].getValue(), "UTF-8");
                }
            }
        } catch (Exception ex) {
            return "";
        }
        return "";
    }

    public static void setCookie(HttpServletResponse httpservletresponse, String cookieName, String cookievalue) {
        try {
            Cookie cookie = new Cookie(URLEncoder.encode(cookieName, "UTF-8"), URLEncoder.encode(cookievalue, "UTF-8"));
            cookie.setPath("/");
            httpservletresponse.addCookie(cookie);
        } catch (Exception exception) {
            System.out.println("browser doesn't support Cookie. Please set it supported first.");
        }
    }
    public static void setCookie(HttpServletResponse httpservletresponse, String cookieName, String cookievalue,int maxAge) {
        try {
            Cookie cookie = new Cookie(URLEncoder.encode(cookieName, "UTF-8"), URLEncoder.encode(cookievalue, "UTF-8"));
            cookie.setPath("/");
            cookie.setMaxAge(maxAge);
            httpservletresponse.addCookie(cookie);
        } catch (Exception exception) {
            System.out.println("browser doesn't support Cookie. Please set it supported first.");
        }
    }
    public static void setCookie(HttpServletResponse httpservletresponse, String cookieName, String cookievalue,int maxAge,String domain,String path,boolean secure,boolean httpOnly) {
        try {
            Cookie cookie = new Cookie(URLEncoder.encode(cookieName, "UTF-8"), URLEncoder.encode(cookievalue, "UTF-8"));
            cookie.setPath(path);
            cookie.setMaxAge(maxAge);
            cookie.setSecure(secure);
            /**
             * mxh 2015年2月6日17:40:52
             * setDomain不能设置为null，而调用者故意传了null吗，导致使用时发生异常。
             */
            if(StringUtils.isNotEmpty(domain)){
            	cookie.setDomain(domain);
            }
            cookie.setHttpOnly(httpOnly);
            httpservletresponse.addCookie(cookie);
        } catch (Exception exception) {
            System.out.println("browser doesn't support Cookie. Please set it supported first.");
        }
    }
    public static void setCookie(HttpServletResponse httpservletresponse, String cookieName, String value, int maxAge,String domain,String path,boolean secure)
    {
        try {
            Cookie cookie = new Cookie(URLEncoder.encode(cookieName, "UTF-8"), URLEncoder.encode(value, "UTF-8"));
            cookie.setMaxAge(maxAge);
            cookie.setPath(path);
            /**
             * mxh 2015年2月6日17:40:52
             * setDomain不能设置为null，而调用者故意传了null吗，导致使用时发生异常。
             */
            if(StringUtils.isNotEmpty(domain)){
            	cookie.setDomain(domain);
            }
            cookie.setSecure(secure);
            httpservletresponse.addCookie(cookie);
        } catch (Exception exception) {
            System.out.println("<h5>Your browser doesn't support Cookie. Please set it supported first.</h5>");
        }
    }
}
