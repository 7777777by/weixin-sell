package com.xyz.zf.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Create by liuyang
 * 2018/6/17
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
