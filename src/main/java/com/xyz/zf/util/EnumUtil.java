package com.xyz.zf.util;

import com.xyz.zf.enums.CodeEnum;

/**
 * Create by liuyang
 * 2018/6/17
 */
public class EnumUtil {
    /**
     * 根据code获取枚举
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static<T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
