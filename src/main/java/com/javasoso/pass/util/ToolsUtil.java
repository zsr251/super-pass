/*
 *The code is written by 51jiecai.com.
 *All rights reserved.
 */
package com.javasoso.pass.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author forgkan Created on 2014年11月28日
 */
public class ToolsUtil {

    public static Pattern mobilePattern = Pattern.compile("^[1][0-9]{10}$");

    public static boolean isMobile(String str) {
        Matcher m = mobilePattern.matcher(str);
        return m.matches();
    }


    /**
     * 获得user agent
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * 获得真实ip
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getRemoteHost();
        String realIP = request.getHeader("X-Real-IP");
        if (isNotBlank(realIP)) {
            ip = realIP;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
        if(ip != null && ip.length() > 15) {
            int index = ip.indexOf(",");
            if (index > -1) {
                ip = ip.substring(0, index);
            }
        }
        return ip;
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }


    public static boolean isAjaxRequest(HttpServletRequest r) {
        String requestedWith = r.getHeader("x-requested-with");
        if (requestedWith != null && "XMLHttpRequest".equals(requestedWith)) {
            return true;
        }
        return false;
    }

    /**
     * 获取UUID 去除 -
     * @return
     */
    public static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 验证真实姓名 （1-12位中文）
     *
     * @param name
     * @return
     */
    public static boolean checkRealName(String name) {
        boolean flag = false;
        try {
            String check = "^[\u4e00-\u9fa5 a-zA-Z]{1,12}";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(name);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 验证URL
     *
     * @param url
     * @return
     */
    public static boolean checkUrl(String url) {
        boolean flag = false;
        try {
            String check = "^(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(url);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;

    }

    /**
     * 验证身份证号
     *
     * @param cerNo
     * @return
     */
    public static boolean checkCerNo(String cerNo) {
        boolean flag = false;
        try {
            cerNo = cerNo.toUpperCase();
            String check = "^([1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$)|([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(cerNo);
            flag = matcher.matches();
            if (flag == true && cerNo.length() == 18) {
                int sum = 0;
                for (int i = 0; i < 18; i++) {
                    sum += (getOneNum(cerNo.substring(i, i + 1)) * getW(18 - i));
                }
                flag = sum % 11 == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 18位身份证校验 根据字符获得数字
     *
     * @param str
     * @return
     */
    private static Integer getOneNum(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if ("X".equals(str.toUpperCase())) {
            return 10;
        }
        Integer n = null;
        try {
            n = Integer.parseInt(str.substring(0, 1));
        } catch (Exception e) {
            return null;
        }
        return n;
    }

    /**
     * 身份证校验码
     *
     * @param i 从右向左第几位
     */
    private static Integer getW(int i) {
        i = i < 1 ? 0 : (i - 1) % 18;
        return (int) (Math.pow(2, i) % 11);
    }

    /**
     * 使用 * 来替换字符
     *
     * @param oriStr     原始字符串
     * @param headLength 新的字符串头部长度
     * @param footLength 新的字符串尾部长度
     * @return
     */
    public static String maskStr(String oriStr, int headLength, int footLength) {
        if (StringUtils.isEmpty(oriStr.trim())) {
            return null;
        }
        if (headLength < 0 || footLength < 0) {
            return null;
        }
        if (headLength + footLength > oriStr.length()) {
            return null;
        }
        StringBuffer bodyBuffer = new StringBuffer();
        for (int i = 0; i < (oriStr.length() - headLength - footLength); i++) {
            bodyBuffer.append("*");
        }
        return oriStr.substring(0, headLength) + bodyBuffer.toString() + oriStr.substring(oriStr.length() - footLength, oriStr.length());
    }


    public static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static String urlEncodeUTF8(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }
}
