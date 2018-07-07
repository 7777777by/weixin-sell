package com.xyz.zf.exception;

import com.xyz.zf.enums.ResultEnum;
import lombok.Getter;

/**
 * Create by liuyang
 * 2018/6/10
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
