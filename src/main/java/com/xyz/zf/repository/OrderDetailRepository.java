package com.xyz.zf.repository;

import com.xyz.zf.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by liuyang
 * 2018/6/10
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findAllByOrderId(String orderId);
}
