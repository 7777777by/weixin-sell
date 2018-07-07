package com.xyz.zf.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.exception.SellException;
import com.xyz.zf.form.OrderMasterForm;
import com.xyz.zf.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by liuyang
 * 2018/6/15
 */
@Slf4j
public class OrderMasterForm2OrderMasterDto {

    public static OrderMasterDto convert(OrderMasterForm orderMasterForm) {
        Gson gson = new Gson();
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        orderMasterDto.setBuyerName(orderMasterForm.getName());
        orderMasterDto.setBuyerPhone(orderMasterForm.getPhone());
        orderMasterDto.setBuyerAddress(orderMasterForm.getAddress());
        orderMasterDto.setBuyerOpenid(orderMasterForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        try {
            orderDetailList = gson.fromJson(orderMasterForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】 错误，String={}", orderMasterForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderMasterDto.setOrderDetailList(orderDetailList);
        return orderMasterDto;
    }
}
