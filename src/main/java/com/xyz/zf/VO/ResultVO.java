package com.xyz.zf.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * HTTP请求返回的最外层对象
 * Create by liuyang
 * 2018/6/3
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 1371354874302839585L;

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;
}
