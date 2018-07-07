package com.xyz.zf.service.impl;

import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.service.OrderMasterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Create by liuyang
 * 2018/6/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {
    @Autowired
    private PayServiceImpl payService;
    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    @Transactional
    public void create() {
        OrderMasterDto orderMasterDto = orderMasterService.findOne("1529133409920388031");
        payService.create(orderMasterDto);
    }
}