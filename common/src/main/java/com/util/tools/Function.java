package com.util.tools;


import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LDB on 14-8-12.
 */
public class Function {

    private static final char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    private static final char[] numbers = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    private static final String allQuan="０１２３４５６７８９ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ";
    private static final String allChar="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String replaceQuanjiaoNum(String str) {
        // StringBuffer sbf = new StringBuffer();
        if (str == null) {
            return null;
        }

        for(int i=0;i<allQuan.length();i++){
            str=str.replace(allQuan.charAt(i),allChar.charAt(i));
        }
      /*  str = str.replaceAll("０", "0");
        str = str.replaceAll("１", "1");
        str = str.replaceAll("２", "2");
        str = str.replaceAll("３", "3");
        str = str.replaceAll("４", "4");
        str = str.replaceAll("５", "5");
        str = str.replaceAll("６", "6");
        str = str.replaceAll("７", "7");
        str = str.replaceAll("８", "8");
        str = str.replaceAll("９", "9");
        str = str.replaceAll("ｘ", "x");
        str = str.replaceAll("a", "9");
        str = str.replaceAll("９", "9");*/
        return str;

    }

    public Function() {}

    /**
     * 截断空白字符
     *
     * @param s String
     * @return String
     */
    public static String trim(String s) {
        String s1 = "";

        try {
            if (s != null) {
                s1 = s.trim();
            }
            String cnSpace = "　";
            // 去除前后的全角空格
            while (s1.startsWith(cnSpace)) {
                s1 = s1.substring(cnSpace.length()).trim();
            }
            while (s1.endsWith(cnSpace)) {
                s1 = s1.substring(0, s1.length() - cnSpace.length()).trim();
            }
        } catch (Exception exception) {}
        return s1;
    }

    public static boolean isValidString(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        int length = str.length();
        char b;

        for (int i = 0; i < length; i++) {
            b = str.charAt(i);
            if ((b >= 'a') && (b <= 'z')) {
                // lower char
            } else if ((b >= 'A') && (b <= 'Z')) {
                // upper char
            } else if ((b >= '0') && (b <= '9')) {
                // numeric char
            } else if (((b == '_') || (b == '.')) && (i != 0)) {
                // _ char
            } else {
                return false;
            }
        } // for
        return true;
    }

    public static boolean isBlankStr(String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * 数字的检查 true:checkStr为数字 FALSE：帐号中存在非数字的字符
     */
    public static boolean check_digit(String checkStr) {
        if (isBlankStr(checkStr)) {
            return false;
        }
        try {
            new java.math.BigDecimal(checkStr);

        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * getRandomNumber产生一个0-max之间的一个随机数
     */
    public static int getRandomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max);

    }

    /**
     * getRandomPassword产生一个长度为length的随机密码
     */
    public static String getRandomPassword(int length) {
        Random rand = new Random();
        char psd[] = new char[length];
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(letters.length);
            psd[i] = letters[index];
        }
        return new String(psd);
    }

    /**
     * getRandomPassword产生一个长度为length的随机纯数字的密码
     */
    public static String getRandomDigitPassword(int length) {
        Random rand = new Random();
        char psd[] = new char[length];
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(numbers.length);
            psd[i] = numbers[index];
        }
        return new String(psd);
    }

    public static int toSafeInt(String s) {
        try {
            if (s == null || s.length() == 0) {
                return 0;
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long toSafeLong(String s) {
        try {
            if (s == null || s.length() == 0) {
                return 0;
            }
            return Long.parseLong(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static float toSafeFloat(String s) {
        try {
            if (s == null || s.length() == 0) {
                return 0;
            }
            return Float.parseFloat(s);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 判断num的二进制位数是否包含在value中。 比如 6二进制表示为110， 则4(100)，和2(010)包含在7中 而1(001)不包含在7中
     *
     * @param value
     * @param num   必定为2的某次幂
     * @return
     */
    public static boolean isBinaryNumberInclude(int value, int num) {
        return (value & num) == num;
    }

    public static String encode(String input, String code) {
        if (isBlankStr(input)) {
            return "";
        }
        try {
            String s = java.net.URLEncoder.encode(input, code);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 检查给定字符串是否包含在StringArray数组中,不区分大小写
     * @param stringArray
     * @param checkstr
     * @return
     */
    public static boolean isContainIgnoreCase(String[] stringArray, String checkstr) {
        if (stringArray == null || isBlankStr(checkstr)) {
            return false;
        }
        for (int i = 0; i < stringArray.length; i++) {
            if (checkstr.equalsIgnoreCase(stringArray[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查给定字符串是否包含在StringArray数组中
     * @param stringArray
     * @param checkstr
     * @return
     */
    public static boolean isContain(String[] stringArray, String checkstr) {

        if (stringArray == null || isBlankStr(checkstr)) {
            return false;
        }
        for (int i = 0; i < stringArray.length; i++) {
            // System.out.println("--" + stringArray[i] + "--" + checkstr);
            if (checkstr.equals(stringArray[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 转换一个62进制的字符为十进制数。 该62进制为ShortURL所用 0,9表示0-9区间的数字 a-b表示10-35 A-Z表示36-61
     *
     * @param s
     * @return
     */
    public static long getNum62(String s) {
        long id = 0;
        if (!Function.isBlankStr(s)) {
            for (int i = 0, len = s.length(); i < len; i++) {
                int c = s.codePointAt(i);
                // char c =idStr.charAt(i);
                long b = 1;
                for (int k = 0; k < i; k++) {
                    b = b * 62;
                }
                if (c >= 48 && c <= 57)// '0'-'9'
                {
                    id += b * (c - 48);
                } else if (c >= 97 && c <= 122) {
                    id += b * (c - 87);
                } else if (c >= 65 && c <= 90) {
                    id += b * (c - 29);
                }
            }
        }
        return id;
    }

    public static String toNum62(long num) {
        /*
         * var n62=""; do{ var y=num%62; var num=parseInt(num/62); if(y<=9 &&
         * y>=0) n62+=""+y; else if(y>=10 && y<=35) n62+=
         * String.fromCharCode(y+87); else n62+= String.fromCharCode(y+29);
         * }while(num>0); return n62;
         */
        StringBuffer sb = new StringBuffer();
        do {
            long y = num % 62;
            num = num / 62;
            if (y >= 0 && y <= 9) {
                sb.append(y);
            } else if (y >= 10 && y <= 35) {
                sb.append(((char) (y + 87)));
            }
            // sb.append((char)(y+87));
            else {
                sb.append(((char) (y + 29)));
            }
        } while (num > 0);
        return sb.toString();
    }

    /**
     * 转换一个36进制的字符为十进制数。 该36进制为ShortURL所用 0,9表示0-9区间的数字 a-b表示10-35,不区分大小写
     *
     * @param s
     * @return
     */
    public static long getNum36(String s) {
        long id = 0;
        if (!Function.isBlankStr(s)) {
            s = s.toLowerCase();
            for (int i = 0, len = s.length(); i < len; i++) {
                int c = s.codePointAt(i);
                // char c =idStr.charAt(i);
                long b = 1;
                for (int k = 0; k < i; k++) {
                    b = b * 36;
                }
                if (c >= 48 && c <= 57)// '0'-'9'
                {
                    id += b * (c - 48);
                } else if (c >= 97 && c <= 122) {
                    id += b * (c - 87);
                }
                // else if(c>=65 && c<=90)//'A-Z'不可能有
                // id+=b*(c-29);
            }
        }
        return id;
    }

    public static String toNum36(long num) {

        StringBuffer sb = new StringBuffer();
        do {
            long y = num % 36;
            num = num / 36;
            if (y >= 0 && y <= 9) {
                sb.append(y);
            } else if (y >= 10 && y <= 35) {
                sb.append(((char) (y + 87)));
            }
        } while (num > 0);
        return sb.toString();
    }

    /**
     * ip地址转成整数.
     *
     * @param ip
     * @return
     */
    public static long ip2long(String ip) {
        if (ip == null || ip.indexOf(".") <= 0) {
            return 0;
        }
        ip = Function.trim(ip);
        String[] ips = ip.split("[.]");
        try {
            long num = 16777216L * Long.parseLong(ips[0]) + 65536L * Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2]) + Long.parseLong(ips[3]);
            return num;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 整数转成ip地址.
     *
     * @param ipLong
     * @return
     */
    public static String long2ip(long ipLong) {
        // long ipLong = 1037591503;
        long mask[] = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
        long num = 0;
        StringBuffer ipInfo = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            num = (ipLong & mask[i]) >> (i * 8);
        if (i > 0) {
                ipInfo.insert(0, ".");
            }
        ipInfo.insert(0, Long.toString(num, 10));
        }
        return ipInfo.toString();
    }

    private static Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);

    /**
     * 根据索引获取字母，传0为A，1为B。。。
     * @param i
     * @return
     */
    public static String getLetter(int i) {
        char a = (char) (i + 65);
        return a + "";
    }

    /**
     * 根据参数返回可缓存的key，对象是一个大小为2的数组，第一个是countKey，第二个是resultKey
     * @param basesql
     * @param paras
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static String[] getCacheKey(String basesql, List<Object> paras, int pageNo, int pageSize, int orderType) {

        Matcher m = p.matcher(basesql);
        StringBuffer cacheCountKey = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(cacheCountKey, "");
        }
        m.appendTail(cacheCountKey);

        StringBuffer cacheListKey = new StringBuffer(basesql);
        if (paras != null) {
            for (Object obj : paras) {
                cacheCountKey.append(obj.toString()).append(",");
                cacheListKey.append(obj.toString()).append(",");
            }
        }
        String[] result = new String[2];
        result[0] = Md5.getMd5Str(cacheCountKey.toString());
        result[1] = Md5.getMd5Str(cacheListKey.toString() + orderType + "," + pageNo + "," + pageSize);
        return result;
    }

    /**
     * 根据参数返回可缓存的key，返回一个md5后的字符串
     * @param sql
     * @param paras
     * @return
     */
    public static String getCacheSQLKey(String sql, List<Object> paras) {
        StringBuffer cacheCountKey = new StringBuffer(sql);
        if (paras != null) {
            for (Object obj : paras) {
                cacheCountKey.append(obj.toString()).append(",");
            }
        }
        return Md5.getMd5Str(cacheCountKey.toString());
    }
}
