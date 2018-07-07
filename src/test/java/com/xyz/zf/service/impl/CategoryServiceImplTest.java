package com.xyz.zf.service.impl;

import com.xyz.zf.pojo.ProductCategory;
import com.xyz.zf.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by liuyang
 * 2018/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    @Transactional
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertTrue(productCategory.getCategoryId() == 1);
    }

    @Test
    @Transactional
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertTrue(productCategoryList.size() > 0);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> result = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专享",10);
        ProductCategory category = categoryService.save(productCategory);
        Assert.assertTrue(category.getCategoryType() != null);
    }
}