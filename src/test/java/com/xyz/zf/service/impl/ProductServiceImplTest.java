package com.xyz.zf.service.impl;

import com.xyz.zf.common.SellConstants;
import com.xyz.zf.enums.ProductStatusEnum;
import com.xyz.zf.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by liuyang
 * 2018/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertTrue("123456".equals(productInfo.getProductId()));
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertTrue(productInfoList.size() > 0);
    }

    @Test
    public void findAll() {
        Pageable pageable = new PageRequest(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageable);
        //System.out.println(productInfoPage.getTotalElements());
        Assert.assertTrue(productInfoPage.getTotalElements() > 0);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStore(200);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528033448097&di=88dc2908feb1e7b03009d01f7cfe05d4&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D431866575%2C1411671824%26fm%3D214%26gp%3D0.jpg");
        productInfo.setProductStatus(SellConstants.PRODUCT_DOWN);
        productInfo.setCategoryType(2);
        ProductInfo result = productService.save(productInfo);
        Assert.assertTrue(result != null);
    }

    @Test
    @Transactional
    public void offSale() {
        ProductInfo productInfo = productService.offSale("123456");
        Assert.assertEquals(productInfo.getProductStatus(), ProductStatusEnum.PRODUCT_DOWN.getCode());
    }

    @Test
    @Transactional
    public void onSale() {
        ProductInfo productInfo = productService.onSale("123456");
        Assert.assertEquals(productInfo.getProductStatus(), ProductStatusEnum.PRODUCT_UP.getCode());
    }
}