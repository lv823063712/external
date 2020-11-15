package com.example.external.mvp.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 获取到的List<Map>结果集转化为JavaBean工具类
 */
public class GsonUtil {

    /**
     * 将jsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T jsonToBean(String gsonString, Type cls) {
        T t = null;
        try {
            Log.i("GsonUtil.jsonToBea=", gsonString);
            //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            t = new Gson().fromJson(gsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(e.toString(), "");
        }
        return t;
    }

    public static String beanToJson(Object cls) {
        String str = null;
        try {
            Log.i("GsonUtil.beanToJson=", cls.getClass().getName());
            //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            str = new Gson().toJson(cls);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(e.toString(), "");
        }
        return str;
    }
}