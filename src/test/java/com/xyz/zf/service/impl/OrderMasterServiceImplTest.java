package com.xyz.zf.service.impl;

import com.xyz.zf.common.SellConstants;
import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by liuyang
 * 2018/6/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {
    @Autowired
    private OrderMasterServiceImpl orderMasterService;
    private final String OPENID = "110110";
    private final String ORDER_ID = "1528620447046661313";

    @Test
    public void create() {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        orderMasterDto.setBuyerAddress("上海市黄浦区");
        orderMasterDto.setBuyerName("xiaoyangzi");
        orderMasterDto.setBuyerPhone("18712365898");
        orderMasterDto.setBuyerOpenid(OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);
        orderDetail = new OrderDetail();
        orderDetail.setProductId("123457");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);
        orderMasterDto.setOrderDetailList(orderDetailList);
        OrderMasterDto result = orderMasterService.create(orderMasterDto);
        log.info("【创建订单】 result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void findOne() {
        OrderMasterDto orderMasterDto = orderMasterService.findOne(ORDER_ID);
        log.info("【查询单个订单】 result={}", orderMasterDto);
        Assert.assertNotNull(orderMasterDto);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderMasterDto> orderMasterDtoPage = orderMasterService.findList(OPENID, request);
        Assert.assertNotEquals(0, orderMasterDtoPage.getTotalElements());
    }

    @Test
    @Transactional
    public void cancel() {
        OrderMasterDto orderMasterDto = orderMasterService.findOne(ORDER_ID);
        OrderMasterDto masterDto = orderMasterService.cancel(orderMasterDto);
        Assert.assertEquals(masterDto.getOrderStatus(), SellConstants.ORDER_CANCEL);
    }

    @Test
    @Transactional
    public void finish() {
        OrderMasterDto orderMasterDto = orderMasterService.findOne(ORDER_ID);
        OrderMasterDto masterDto = orderMasterService.finish(orderMasterDto);
        Assert.assertEquals(masterDto.getOrderStatus(), SellConstants.ORDER_FINISHED);
    }

    @Test
    @Transactional
    public void paid() {
        OrderMasterDto orderMasterDto = orderMasterService.findOne(ORDER_ID);
        OrderMasterDto masterDto = orderMasterService.paid(orderMasterDto);
        Assert.assertEquals(masterDto.getPayStatus(), SellConstants.PAY_SUCCESS);
    }

    @Test
    public void list() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderMasterDto> orderMasterDtoPage = orderMasterService.findList(request);
        Assert.assertNotEquals(0, orderMasterDtoPage.getTotalElements());
    }
}