package com.xyz.zf.repository;

import com.xyz.zf.pojo.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by liuyang
 * 2018/6/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("123456");
        orderDetail.setDetailId("1234567890");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("皮皮虾");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528033448097&di=88dc2908feb1e7b03009d01f7cfe05d4&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D431866575%2C1411671824%26fm%3D214%26gp%3D0.jpg");
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAllByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId("123456");
        Assert.assertNotEquals(orderDetailList.size(), 0);
    }
}