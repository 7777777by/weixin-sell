package com.xyz.zf.service.impl;

import com.xyz.zf.config.WeChatAccountConfig;
import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Create by liuyang
 * 2018/6/24
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Override
    public void orderStatus(OrderMasterDto orderMasterDto) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(weChatAccountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderMasterDto.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，记得收货"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18888888888"),
                new WxMpTemplateData("keyword3", orderMasterDto.getOrderId()),
                new WxMpTemplateData("keyword4", orderMasterDto.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("keyword5", "￥" + orderMasterDto.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临！")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】 发送失败");
        }
    }
}
