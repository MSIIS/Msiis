package com.util.tools;


import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LDB on 15-4-14.
 */
public class AntiSpamUtil {

    public static boolean checkInValidCommon(HttpServletRequest request, HttpServletResponse response,String userId) {
        String p2 = request.getParameter("_p2");
        if (p2 == null || p2.length() == 0) {
            return true;
        }
        String p2InCookie = null;

        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if ("p2".equals(name)) {
                    p2InCookie = value;
                    break;
                }
            }
        }

        if (p2InCookie == null || p2InCookie.length() == 0) {
            return true;
        }
        CookieUtils.removeCookie(response, "p2");
        if (!p2.equals(p2InCookie)) {
            return true;
        }
        String t1 = request.getParameter("_t");
        String p1 = request.getParameter("_p1");


        return AntiSpamUtil.checkInValidUserId(t1, userId, p1, p2);
    }

    public static boolean checkInValidComment(String t1, String p1, String p2) {
        if (t1 == null || t1.length() != 13 || p1 == null || p1.length() != 32 || p2 == null || p2.length() != 32) {
            return true;
        }
        String t2 = (new StringBuffer(t1)).reverse().toString();
        long t3 = System.currentTimeMillis();

        try {
            long t4 = Long.parseLong(t1);
            if ((Math.abs((t3 - t4) / 60000)) > 600) //超过10分钟
                return true;
        } catch (Exception e) {
            return true;
        }
        String checkp1 = DigestUtils.md5Hex(DigestUtils.md5Hex(t1) + t2.substring(5, 11));
        String checkp2 = DigestUtils.md5Hex((new StringBuffer(DigestUtils.md5Hex(t2).substring(8, 24))).reverse().toString() + t1.substring(5, 11));
        if (!checkp1.equalsIgnoreCase(p1) || !checkp2.equalsIgnoreCase(p2)) {
            return true;
        }
        return false;
    }

    public static boolean checkInValidUserId(String t1, String userId, String p1, String p2) {
        if (t1 == null || t1.length() != 13 || p1 == null || p1.length() != 32 || p2 == null || p2.length() != 32) {
            return true;
        }

        String t2 = (new StringBuffer(t1)).reverse().toString();
        long t3 = System.currentTimeMillis();
        try {
            long t4 = Long.parseLong(t1);
            if ((Math.abs((t3 - t4) / 60000)) > 10) //超过10分钟
                return true;
        } catch (Exception e) {
            return true;
        }
        String checkp1 = DigestUtils.md5Hex(DigestUtils.md5Hex(userId) + t1.substring(5, 11));
        String checkp2 = DigestUtils.md5Hex((new StringBuffer(DigestUtils.md5Hex(userId).substring(8, 24))).reverse().toString() + t2.substring(5, 11));
        if (!checkp1.equalsIgnoreCase(p1) || !checkp2.equalsIgnoreCase(p2)) {
            return true;
        }
        return false;
    }
}
