package com.xyz.zf.dao;

import com.xyz.zf.dao.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create by liuyang
 * 2018/6/30
 */
@Repository
public class ProductCategoryDao {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public int insertByMap(Map<String, Object> map) {
        return productCategoryMapper.insertByMap(map);
    }

}
