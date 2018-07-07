package com.xyz.zf.service.impl;

import com.xyz.zf.dto.OrderMasterDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * Create by liuyang
 * 2018/6/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {
    @Autowired
    private PushMessageServiceImpl pushMessageService;
    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    @Test
    @Transactional
    public void orderStatus() {
        OrderMasterDto orderMasterDto = orderMasterService.findOne("123456");
        pushMessageService.orderStatus(orderMasterDto);
    }
}