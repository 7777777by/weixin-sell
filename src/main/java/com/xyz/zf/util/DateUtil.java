package com.xyz.zf.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * Create by liuyang
 * 2018/6/24
 */
public class DateUtil {

    public static String getCurrentDateTime() {
        TimeZone zone = TimeZone.getTimeZone("Asia/shanghai");
        TimeZone.setDefault(zone);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
