package com.xyz.zf.form;

import lombok.Data;

/**
 * Create by liuyang
 * 2018/6/18
 */
@Data
public class CategoryForm {
    /**
     * 类目id
     */
    private Integer categoryId;
    /**
     * 类目名字
     */
    private String categoryName;
    /**
     * 类目类别
     */
    private Integer categoryType;
}
