package com.xyz.zf.controller;

import com.xyz.zf.exception.SellException;
import com.xyz.zf.form.CategoryForm;
import com.xyz.zf.pojo.ProductCategory;
import com.xyz.zf.pojo.ProductInfo;
import com.xyz.zf.service.CategoryService;
import com.xyz.zf.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Create by liuyang
 * 2018/6/18
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    /**
     * 类目列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductCategory> productCategoryPage = categoryService.findAll(pageRequest);
        map.put("productCategoryPage", productCategoryPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 保存/修改
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("url", "/sell/seller/category/index");
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }
        try {
            ProductCategory productCategory = new ProductCategory();
            //如果categoryId不为空，表示修改
            if (!StringUtils.isEmpty(categoryForm.getCategoryId())) {
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
                //如果类别变了，需要同步商品表
                if (!categoryForm.getCategoryType().equals(productCategory.getCategoryType())) {
                    List<ProductInfo> productInfoList = productService.findByCategoryType(productCategory.getCategoryType());
                    if (!CollectionUtils.isEmpty(productInfoList)) {
                        for (ProductInfo productInfo : productInfoList) {
                            productInfo.setCategoryType(categoryForm.getCategoryType());
                            productService.save(productInfo);
                        }
                    }
                }
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        } catch (Exception e) {
            map.put("url", "/sell/seller/category/index");
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
