package com.xyz.zf.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品（包含类目）
 * Create by liuyang
 * 2018/6/9
 */
@Data
public class ProductVO implements Serializable {
    private static final long serialVersionUID = -8479982319674160081L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
