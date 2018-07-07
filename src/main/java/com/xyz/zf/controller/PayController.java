package com.xyz.zf.controller;

import com.lly835.bestpay.model.PayResponse;
import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.exception.SellException;
import com.xyz.zf.service.OrderMasterService;
import com.xyz.zf.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Create by liuyang
 * 2018/6/17
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private PayService payService;

    /**
     * 微信支付
     * @param orderId
     * @param returnUrl
     * @param map
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //查询订单
        OrderMasterDto orderMasterDto = orderMasterService.findOne(orderId);
        if (orderMasterDto == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderMasterDto);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
