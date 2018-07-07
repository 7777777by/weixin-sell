package com.xyz.zf.repository;

import com.xyz.zf.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Create by liuyang
 * 2018/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne() {
        Optional<ProductCategory> productCategoryRepositoryById = productCategoryRepository.findById(1);
        System.out.println(productCategoryRepositoryById.orElse(null));
    }

    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("男生栏目", 4);
        ProductCategory category = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(category);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(2, 3, 4));
        Assert.assertTrue(result.size() > 0);
    }

}