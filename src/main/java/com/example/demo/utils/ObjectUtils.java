package com.example.demo.utils;

/**
 * Created by JinGangLang.xx on 2018/4/8.
 */
public class ObjectUtils {

    public static String ObjectToString(Object object) {
        if (object != null) {
            return object.toString();
        }
        return "";
    }
}
