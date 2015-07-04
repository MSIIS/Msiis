package com.util.tools;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常用工具类
 * 
 * @author liangzh
 *
 * 2013年9月26日 上午8:42:21
 *
 */
public class DataUtils {

    private static final Logger logger = LoggerFactory.getLogger(DataUtils.class);

    // 取得随机数用
    private static Random randGen = new Random();

    private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    /**
     * 截取固定长度字符
     * 
     * @param str
     * @param num
     * 
     * @return
     */
    public static String subStr(String str, int num) {
        int max = num;
        try {
            max = trimGBK(str.getBytes("GBK"), num);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int sum = 0;
        if (str != null && str.length() > max) {
            StringBuilder sb = new StringBuilder(max);
            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i);
                sum += 1;
                if (sum <= max) {
                    sb.append((char) c);
                } else {
                    break;
                }
            }
            return sb.toString();
        } else {
            return str != null ? str : "";
        }
    }

    /**
     * 截取固定长度字符加上...
     * 
     * @param str
     * @param num
     * 
     * @return
     */
    public static String subViewStr(String str, int num) {
        int max = num;
        try {
            max = trimGBK(str.getBytes("GBK"), num);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int sum = 0;
        if (str != null && str.length() > max) {
            StringBuilder sb = new StringBuilder(max);
            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i);
                sum += 1;
                if (sum <= max) {
                    sb.append((char) c);
                } else {
                    break;
                }
            }
            return sb.append("...").toString();
        } else {
            return str != null ? str : "";
        }
    }

    /**
     * trim方法
     * 
     * @param buf
     * @param n
     * @return
     */
    public static int trimGBK(byte[] buf, int n) {
        int num = 0;
        boolean bChineseFirstHalf = false;
        if (buf.length < n) {
            return buf.length;
        }
        for (int i = 0; i < n; i++) {
            if (buf[i] < 0 && !bChineseFirstHalf) {
                bChineseFirstHalf = true;
            } else {
                num++;
                bChineseFirstHalf = false;
            }
        }
        return num;
    }

    /**
     * 字符串去掉一个或多个空格，以逗号分割
     * 
     * @param str
     * @return
     */
    public static String takeOffString(String str) {
        if ("".equals(str) || 0 > str.length()) {
            return "";
        }

        str = str.replaceAll(",", " ").trim();
        // 一个或多个空格 
        String regEx = "\\s{1,}";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        return m.replaceAll(",").trim();
    }

    /**
     * 求多个字符串数组交集
     * 
     * @param stringList
     * 
     * @return String[]
     */
    public static String[] intersection(List<String[]> stringList) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        LinkedList<String> list = new LinkedList<String>();

        if (stringList.size() == 1) {
            String[] arr1 = stringList.get(0);
            for (String str : arr1) {
                if (!map.containsKey(str)) {
                    map.put(str, Boolean.TRUE);
                }
            }
        } else if (stringList.size() > 1) {
            String[] arr1 = stringList.get(0);
            for (String str : arr1) {
                if (!map.containsKey(str)) {
                    map.put(str, Boolean.FALSE);
                }
            }
            for (int i = 1; i < stringList.size(); i++) {
                String[] arr2 = stringList.get(i);
                for (String str : arr2) {
                    if (map.containsKey(str)) {
                        map.put(str, Boolean.TRUE);
                    }
                }
                if (i < stringList.size() - 1) {
                    Iterator<Entry<String, Boolean>> it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Entry<String, Boolean> e = it.next();
                        if (e.getValue().equals(Boolean.TRUE)) {
                            e.setValue(Boolean.FALSE);
                        } else if (e.getValue().equals(Boolean.FALSE)) {
                            it.remove();
                        }
                    }
                }
            }
        }
        for (Entry<String, Boolean> e : map.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
            }
        }
        String[] result = {};
        return list.toArray(result);
    }

    /**
     * 取最大值
     * @author mengdelong
     * @param  String[] unitIntersec数组
     * @return int 最大值
     */
    public static int getMax(String[] unitIntersec) {
        int Max = 1;
        for (int i = 0; i < unitIntersec.length; i++) {
            if (Max < Integer.valueOf(unitIntersec[i])) {
                Max = Integer.valueOf(unitIntersec[i]);
            }
        }
        return Max;
    }

    /**
     * list去重
     * @author mengdelong
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List emphasis(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /**
     * list去重（顺序不变）
     * @author mengdelong
     */
    @SuppressWarnings({ "rawtypes" })
    public static List emphasisOrder(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * 为空或者TRIM后长度为零，转化为NULL输出
     * 
     * @param str
     * @return
     */
    public static String emptyToNull(String str) {
        if (str != null) {
            if (str.trim().equals("")) {
                str = null;
            }
        }
        return str;
    }

    /**
     * 字符转换为数字
     * 
     * 
     * @param s
     * @return
     */
    public static double stringToDouble(String s) {
        double d = 0.0;
        try {
            d = Double.parseDouble(s);
        } catch (Exception e) {
            logger.error("error===" + e.getMessage(), e);
            d = 0.0;
        }
        return d;

    }

    /**
     * 字符转换为数字
     * 
     * 
     * @param s
     * @return
     */

    public static int stringToInt(String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (Exception e) {
            logger.error("error===" + e.getMessage(), e);
            i = 0;
        }
        return i;
    }

    /**
     * 字符转换为数字
     * 
     * 
     * @param s
     * @return
     */
    public static float stringToFloat(String s) {
        float i = 0;
        try {
            i = Float.parseFloat(s);
        } catch (Exception e) {
            logger.error("error===" + e.getMessage(), e);
            i = 0;
        }
        return i;
    }

    /**
     * 字符转换为数字
     * 
     * 
     * @param s
     * @return
     */
    public static long stringToLong(String s) {
        long i = 0;
        try {
            i = Long.parseLong(s);
        } catch (Exception e) {
            logger.error("error===" + e.getMessage(), e);
            i = 0;
        }
        return i;
    }

    /**
     * 字符串的首字母大写
     * 
     * 
     * @param string
     * @return
     */
    public static String firstToUpperCase(String string) {
        String post = string.substring(1, string.length());
        String first = ("" + string.charAt(0)).toUpperCase();
        return first + post;
    }

    /**
     * 取得一个随机字符串,包含数字和字符
     * 
     * 
     * @param length
     *            字符串长度
     * 
     * @return 随机字符串
     * 
     */
    public static final String getRandomString(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    /**
     * 二进制数转化成十进制数,目前只支持正数
     * 
     * 
     * @param str
     *            一个由0，1组成的字符串
     * @return 转化后的十进制数值,若返回-1则表明转化失败，有可能是输入字符串 不合法
     * 
     */
    public static int binsToDecs(String str) {
        int ret = 0;
        int v = 1;
        for (int i = 0; i < str.length(); i++) {
            if (i != 0) {
                v = v * 2;
            }
            if (str.charAt(i) == '0') {
                continue;
            } else if (str.charAt(i) == '1') {
                ret = ret + v;
            } else {
                return -1;
            }
        }
        return ret;
    }

    /**
     * 得到两个数之间的一个随机数
     * 
     * @param iLower
     *            最小值
     * 
     * @param iUpper
     *            最大值
     * 
     * @return 随机数
     * 
     */
    public String getRandom(int iLower, int iUpper) {
        int iRandom = 0;
        Random random = new Random();
        float fRandom = random.nextFloat();
        iRandom = iLower + (int) ((iUpper - iLower) * fRandom);
        String strRandom = String.valueOf(iRandom);
        return strRandom;
    }

    /**
     * 字符串前导符( Escape: '\' ) 自动添加函数.
     * <p>
     * 对于通过 HTML &lt;TEXTAREA&gt; 输入的数据, 因为其中包含回车、换行等特殊字符, 在将这些变量传给HTML中的javascript 变量时,
     * 这些字符串将导致 javascript 程序出错, 因此需要对于包含这些特殊字符的字符串进行转换处理, 因为 javascript, 特殊字符转义前
     * 导符与 java/C 语言一致, 所以实际是在这些特殊字符中加入前导符 '\' .
     * 
     * @param s 要输出或处理的字符串.
     * @return 自动添加了前导符的字符串.
     */
    public static String escape(String s) {
        try {
            int i = 0;
            char c;
            StringBuffer bf = new StringBuffer("");
            while (i < s.length()) {
                c = s.charAt(i);
                if (c == '\\') {
                    bf.append("\\\\");
                } else if (c == '\r') {
                    bf.append("\\r");
                } else if (c == '\n') {
                    bf.append("\\n");
                } else if (c == '\t') {
                    bf.append("\\t");
                } else if (c == '"') {
                    bf.append("\\\"");
                } else {
                    bf.append(c);
                }
                i++;
            }
            return (bf.toString());
        } catch (Exception e) {
            logger.error("error===" + e.getMessage(), e);
            return (null);
        }
    }

    public static String numberToString(double n) {
        String s = java.text.DecimalFormat.getInstance().format(n);
        s = replace(s, ",", "");
        return s;
    }

    public static String numberToString(long n) {
        String s = "";
        s = java.text.DecimalFormat.getInstance().format(n);
        s = replace(s, ",", "");
        return s;
    }

    public static String numberToString(int n) {
        String s = "";
        s = java.text.DecimalFormat.getInstance().format(n);
        s = replace(s, ",", "");
        return s;
    }

    public static String numberToString(float n) {
        String s = "";
        s = java.text.DecimalFormat.getInstance().format(n);
        s = replace(s, ",", "");
        return s;
    }

    public static String numberToString(String n) {
        String s = "";
        if (n == null || n.length() == 0) {
            return "";
        } else {
            try {
                s = java.text.DecimalFormat.getInstance().format(Double.parseDouble(n));
                s = replace(s, ",", "");
            } catch (Exception e) {
                logger.error("error===" + e.getMessage(), e);
            }
        }
        return s;
    }

    /**
     * 判断字符串是否为数据
     * 
     * @param str
     *            String
     * @return boolean
     */
    public static boolean isNumberString(String str) {

        boolean retValue = true;
        if (str == null || str.length() <= 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char t = str.charAt(i);
            if (t < '0' || t > '9') {
                if (t != '.' && t != '-') {
                    retValue = false;
                    break;
                }
            }
        }
        return retValue;

    }

    /**
     * 说明: 将字符串变量由null转换为""串
     * 
     * 
     * @param str
     *            需要处理的字符串
     * 
     * @return 处理后的字符串
     * 
     */
    public static String stringToedit(String str) {
        if (str == null) {
            str = "";
        }
        return str.trim();
    }

    /**
     * 说明: 将字符串变量为空的，转换为nbsp;输出在html中
     * 
     * 
     * @param str
     *            需要处理的字符串
     * 
     * @return 处理后的字符串
     * 
     */
    public static String stringToshow(String str) {
        str = stringToedit(str);
        if (str.length() == 0) {
            str = "&nbsp;";
        }
        return str;
    }

    /**
     * 双精度浮点数取小数点后若干位.
     * <p>
     * 取浮点数后若干位, 其余位数按四舍五入舍去.
     * 
     * @param f
     *            要处理的浮点数.
     * @param n
     *            小数点后要保留的小数位数.
     * @return 进行小数点位数处理后的浮点数转换后的字符串.
     */
    public static String roundString(double f, int n) {
        int r = 1;
        double f2;
        boolean fushuFlag = false;
        // 如果是负数的话

        if (f < 0) {
            fushuFlag = true;
            f = -1 * f;
        }
        for (int i = 1; i <= n; i++) {
            r = r * 10;
        }
        f2 = (Math.round(f * r));
        String s = java.text.DecimalFormat.getInstance().format(f2);
        s = replace(s, ",", "");

        if (n > 0) {
            if (s.length() <= n) {
                for (int i = s.length(); i <= n; i++) {
                    s = "0" + s;
                }
            }
            s = s.substring(0, s.length() - n) + "." + s.substring(s.length() - n);
        }
        if (fushuFlag) {
            s = "-" + s;
        }
        return s;
    }

    /**
     * 将数字转换为字符，例如：1＝》A，2＝》B，27＝》AA，28＝》AB 目前：最大值到ZZ
     * 
     * @param numValue
     * @return
     */
    public static String integerToChar(int numValue) {

        int fatherNum = 0;
        while (numValue > 26) {
            fatherNum++;
            numValue -= 26;
        }
        char numChar = (char) ('A' + numValue - 1);
        String retValue = String.valueOf(numChar);
        if (fatherNum > 0) {
            retValue += String.valueOf((char) ('A' + fatherNum - 1));
        }

        return retValue;
    }

    /**
     * 功能:字符串替换函数
     * 
     * 
     * @param s
     * @return
     */
    public static String replace(String s, String sourceStr, String replaceStr) {
        if (s == null || s.length() == 0 || sourceStr == null || sourceStr.length() == 0 || replaceStr == null) {
            return s;
        }
        int index = 0, startIndex = 0;
        int length = sourceStr.length();
        int length1 = replaceStr.length();
        StringBuffer bf = new StringBuffer(s);

        while (!((index = bf.indexOf(sourceStr, startIndex)) < 0)) {
            startIndex = index - length + length1;
            bf.replace(index, index + length, replaceStr);
            // bf = bf.substring(0, index) + replaceStr + s.substring(index +
            // length);
        }
        return bf.toString();
    }

    /**
     * 功能:字符串替换函数,忽略大小写
     * 
     * 
     * @param s
     * @return
     */
    public static String replaceIgnoreCase(String s, String sourceStr, String replaceStr) {
        if (s == null || s.length() == 0 || sourceStr == null || sourceStr.length() == 0 || replaceStr == null) {
            return s;
        }

        Pattern p = Pattern.compile(sourceStr, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        return m.replaceAll(replaceStr);
    }

    /**
     * 按逗号 - "," 作为分隔符, 将字符串分解字符串数组.
     * <p>
     * 由于 HTTP 传送的数据形式简单, 因此传送负责数据时, 需要在客户端浏览器上将复杂的数据格式编程字符串传送到服务器上, 为服务器区分各个数据部分, 通常采用逗号分隔各个数据部分.
     * <p>
     * 因此, 需要提供这样一个函数, 将这些数据分解出来,形成字符串数组, 然后, java 数据计算部分就可以方便处理了.
     * 
     * @param s
     *            要进行分解处理的字符串.
     * @return 分解成功后的字符串数组.
     */
    public static String[] split(String s) {
        return split(s, ",");
    }

    /**
     * 按指定分隔字符, 将字符串分解字符串数组.
     * <p>
     * 由于 HTTP 传送的数据形式简单, 因此传送负责数据时, 需要在客户端浏览器上将复杂的数据格式编程字符串传送到服务器上, 为服务器区分各个数据部分, 可以采用指定的分隔符分隔数据各个部分.
     * <p>
     * 因此, 需要提供这样一个函数, 将这些数据分解出来,形成字符串数组, 然后, java 数据计算部分就可以方便处理了.
     * 
     * @param s
     *            要进行分解处理的字符串.
     * @param delim
     *            分隔符.
     * @return 分解成功后的字符串数组.
     */
    public static String[] split(String s, String delim) {
        if (s == null || s.length() == 0 || delim == null || delim.length() == 0) {
            return null;
        }
        ArrayList<String> list = new ArrayList<String>();
        int index = 0, startIndex = 0, endIndex = 0;
        int length1 = delim.length();

        while (!((index = s.indexOf(delim, startIndex)) < 0)) {
            endIndex = index;
            list.add(s.substring(startIndex, endIndex));
            startIndex = index + length1;
        }

        // 加上最后一个数据

        list.add(s.substring(startIndex));

        String[] a = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }

        return a;
    }

    public static String decode(String value) {
        String tbuffer = new String();
        int i = 0;
        byte[] Hanzi = new byte[2];
        StringBuffer Hanzi2 = new StringBuffer(4);
        int c = 0;

        if (value == null) {
            return (null);
        }
        while (i < value.length()) {
            if (value.charAt(i) == '%' && value.charAt(i + 1) != 'u') {
                c = Integer.parseInt(String.valueOf(value.charAt(i + 1)) + String.valueOf(value.charAt(i + 2)), 16);
                if (c >= 0x0080 && c <= 0x00fe) {
                    Hanzi[0] = (byte) c;
                    i += 3;
                    if (value.charAt(i) == '%') {
                        c = Integer.parseInt(String.valueOf(value.charAt(i + 1)) + String.valueOf(value.charAt(i + 2)), 16);
                        i += 2;
                    } else {
                        c = value.charAt(i);
                    }
                    Hanzi[1] = (byte) c;
                    tbuffer += String.valueOf(new String(Hanzi));
                } else {
                    tbuffer += String.valueOf((char) c);
                    i += 2;
                }
            } else if (value.charAt(i) == '%' && value.charAt(i + 1) == 'u') {
                Hanzi2.append(value.charAt(i + 2));
                Hanzi2.append(value.charAt(i + 3));
                Hanzi2.append(value.charAt(i + 4));
                Hanzi2.append(value.charAt(i + 5));
                c = Integer.parseInt(Hanzi2.toString(), 16);
                tbuffer += String.valueOf((char) c);
                i += 5;
            } else if (value.charAt(i) == '+') {
                tbuffer += " ";
            } else {
                tbuffer += value.charAt(i);
            }
            i++;
        }
        return (tbuffer);
    }

    /**
     * 得到字符的长度，一个中文字符算两个长度
     * 
     * @param s
     * @return
     */
    public static int len(String s) {
        if (s == null) {
            return 0;
        }
        int ln = 0, i = 0;
        char c;
        for (i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c >= ' ' && c <= '~') {
                ln += 1;
            } else {
                ln += 2;
            }
        }
        return ln;
    }

    public static String justify(String s, int ln) {
        int sLen = len(s);
        String t = "";
        if (sLen <= ln) {
            t = s + space(ln - sLen);
        } else {
            t = cut(s, ln - 3) + "...";
        }
        return t;
    }

    public static String strJustify(String s, int ln) {
        int sLen = len(s);
        String t = "";
        if (sLen <= ln) {
            t = s;
        } else {
            t = cut(s, ln - 3) + "...";
        }
        return t;
    }

    public static String space(int n) {
        int i = 0;
        String s = "";
        for (i = 0; i < n; i++) {
            s += "&#160;";
        }
        return s;
    }

    public static String cut(String s, int xlen) {
        if (s == null) {
            s = "";
        }
        int ln = 0, i = 0;
        String t = "";
        char c;
        for (i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c >= ' ' && c <= '~') {
                ln += 1;
            } else {
                ln += 2;
            }
            if (ln == xlen) {
                i++;
                t = s.substring(0, i);
                break;
            } else if (ln > xlen) {
                t = s.substring(0, i) + " ";
                break;
            }
        }
        return t;
    }

    //  -----------------------------------------------------------------------------
    //二进制到十六进制的转换
    public static String byteToHex(byte[] bInfoDigest) {
        String strInfoDigest = "";
        String strTemp = "";
        for (int i = 0; i < bInfoDigest.length; i++) {
            strTemp = (Integer.toHexString(bInfoDigest[i] & 0XFF));
            if (strTemp.length() == 1) {
                strInfoDigest = strInfoDigest + "0" + strTemp;
            } else {
                strInfoDigest = strInfoDigest + strTemp;
            }
        }
        return strInfoDigest.toUpperCase();
    }

    //-----------------------------------------------------------------------------
    //十六进制到二进制的转换
    public static byte[] hexToByte(String s) {
        int length = s.length();
        String temp = "";
        int i = 0, x = 0;
        byte[] data = new byte[length / 2];
        for (i = 0; i < length; i = i + 2) {
            temp = s.substring(i, i + 2);
            x = Integer.parseInt(temp, 16);
            if (x > 127) {
                x -= 256;
            }
            data[i / 2] = Byte.parseByte(String.valueOf(x), 10);
        }
        return data;
    }

    /**
     * 根据索引获取字母，传0为A，1为B。。。
     * @author ljp
     * @param i
     * @return
     */
    public static String getLetter(int i) {
        char a = (char) (i + 65);
        return a + "";
    }
}
