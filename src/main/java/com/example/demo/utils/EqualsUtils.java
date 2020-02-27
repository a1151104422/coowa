package com.example.demo.utils;

import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Created by JinGangLang.xx on 2018/4/8.
 */
public class EqualsUtils {

    public static boolean ObjectEquals(Object obj1, Object obj2) {
        if (obj1 != null && obj2 != null) {
            if (obj1 == obj2) {
                return true;
            } else if (obj1.toString().equals(obj2.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断obj是否全部不为空。当有一个选项为空的时候，返回false，都不为空的时候才返回true
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object... obj) {
        if (Objects.isNull(obj) || obj.length < 1) {
            return Boolean.FALSE;
        }
        for (Object o : obj) {
            if (o instanceof String && StringUtils.isEmpty((String) o)) {
                return Boolean.FALSE;
            } else if (Objects.isNull(o)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
