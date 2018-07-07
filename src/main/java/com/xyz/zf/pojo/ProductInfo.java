package com.xyz.zf.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.zf.enums.ProductStatusEnum;
import com.xyz.zf.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * Create by liuyang
 * 2018/6/3
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = -340966827541087750L;

    @Id
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品单价
     */
    private BigDecimal productPrice;
    /**
     * 商品库存
     */
    private Integer productStore;
    /**
     * 商品描述
     */
    private String productDescription;
    /**
     * 商品小图
     */
    private String productIcon;
    /**
     * 商品状态（0：正常；1：下架）
     */
    private Integer productStatus = ProductStatusEnum.PRODUCT_UP.getCode();
    /**
     * 类目编号
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
