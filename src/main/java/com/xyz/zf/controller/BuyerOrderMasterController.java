package com.xyz.zf.controller;

import com.xyz.zf.VO.ResultVO;
import com.xyz.zf.converter.OrderMasterForm2OrderMasterDto;
import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.exception.SellException;
import com.xyz.zf.form.OrderMasterForm;
import com.xyz.zf.pojo.OrderMaster;
import com.xyz.zf.service.BuyerService;
import com.xyz.zf.service.OrderMasterService;
import com.xyz.zf.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by liuyang
 * 2018/6/15
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderMasterController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderMasterForm orderMasterForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确，orderMasterForm={}", orderMasterForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderMasterDto orderMasterDto = OrderMasterForm2OrderMasterDto.convert(orderMasterForm);
        if (CollectionUtils.isEmpty(orderMasterDto.getOrderDetailList())) {
            log.error("【创建订单】 购物车不能为空", ResultEnum.CART_EMPTY);
        }
        OrderMasterDto createResult = orderMasterService.create(orderMasterDto);
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @PostMapping("/list")
    public ResultVO<List<OrderMasterDto>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderMasterDto> orderMasterDtoPage = orderMasterService.findList(openid, pageRequest);
        return ResultVOUtil.success(orderMasterDtoPage.getContent());
    }

    //订单详情
    @PostMapping("/detail")
    public ResultVO<OrderMasterDto> detail(@RequestParam("openid") String openid,
                                            @RequestParam("orderId") String orderId) {
        OrderMasterDto orderMasterDto = buyerService.findOrderMasterOne(openid, orderId);
        return ResultVOUtil.success(orderMasterDto);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO<OrderMasterDto> cancel(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }
}
