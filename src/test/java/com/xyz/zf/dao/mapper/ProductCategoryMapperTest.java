package com.xyz.zf.dao.mapper;

import com.xyz.zf.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by liuyang
 * 2018/6/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<String, Object>(){
            {
                put("category_name", "IT专栏");
                put("category_type", 4);
            }
        };
        int result = mapper.insertByMap(map);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("图书");
        productCategory.setCategoryType(1);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = mapper.findByCategoryType(1);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> productCategoryList = mapper.findByCategoryName("图书");
        Assert.assertTrue(productCategoryList.size() > 0);
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("图书音像", 1);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("图书");
        productCategory.setCategoryType(1);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(4);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory productCategory = mapper.selectByCategoryType(1);
        Assert.assertNotNull(productCategory);
    }
}