package com.xyz.zf.handler;

import com.xyz.zf.VO.ResultVO;
import com.xyz.zf.config.ProjectUrlConfig;
import com.xyz.zf.exception.SellException;
import com.xyz.zf.exception.SellerAuthorizeException;
import com.xyz.zf.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by liuyang
 * 2018/6/24
 */
@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellAuthorizeException() {
        //微信扫码登录
        /*return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWeChatMpAuthorize())
                .concat("/sell/seller/authorize?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));*/
        //网页登录
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/user/goLogin");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
