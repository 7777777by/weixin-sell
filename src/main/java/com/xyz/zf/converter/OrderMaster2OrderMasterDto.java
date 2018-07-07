package com.xyz.zf.converter;

import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by liuyang
 * 2018/6/15
 */
public class OrderMaster2OrderMasterDto {

    public static OrderMasterDto convert(OrderMaster orderMaster) {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        BeanUtils.copyProperties(orderMaster, orderMasterDto);
        return orderMasterDto;
    }

    public static List<OrderMasterDto> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
            convert(e)
        ).collect(Collectors.toList());
    }
}
