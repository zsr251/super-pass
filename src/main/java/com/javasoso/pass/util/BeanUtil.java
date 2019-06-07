package com.javasoso.pass.util;

import com.google.common.collect.Lists;
import jodd.bean.BeanCopy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Bean工具
 *
 * @author jasonzhu
 * @date 2018/1/19
 */
public class BeanUtil {
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 复制单个Bean到目标类
     */
    public static <T> T copyBean(Object o, Class<T> c) {
        T t = null;
        try {
            t = c.newInstance();
        } catch (Exception e) {
            logger.error("复制对象时创建目标对象失败", e);
        }
        BeanCopy.beans(o, t).ignoreNulls(true).copy();
        return t;
    }

    /**
     * 复制Bean列表到目标列表
     */
    public static <T> List<T> copyList(List<?> ol, Class<T> c) {
        List<T> tl = Lists.newArrayList();
        for (Object o : ol) {
            tl.add(copyBean(o,c));
        }
        return tl;
    }

}
