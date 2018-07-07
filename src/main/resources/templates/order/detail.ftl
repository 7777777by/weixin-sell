<html>
<#include "../common/header.ftl">

<body>
    <div id="wrapper" class="toggled">
        <#--边栏sidebar-->
        <#include "../common/nav.ftl">
        <#--正文内容content-->
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-4 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>订单id</th>
                                <th>订单总金额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${orderMasterDto.orderId}</td>
                                <td>${orderMasterDto.orderAmount}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 订单详情表数据 -->
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>商品id</th>
                                <th>商品名称</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                        <#list orderMasterDto.orderDetailList as orderDetailDto>
                        <tr>
                            <td>${orderDetailDto.productId}</td>
                            <td>${orderDetailDto.productName}</td>
                            <td>${orderDetailDto.productPrice}</td>
                            <td>${orderDetailDto.productQuantity}</td>
                            <td>${orderDetailDto.productPrice * orderDetailDto.productQuantity}</td>
                        </tr>
                        </#list>
                            </tbody>
                        </table>
                    </div>

                    <!-- 操作 -->
            <#if orderMasterDto.orderStatusEnum.msg == "新订单">
                <div class="col-md-12 column">
                    <a href="/sell/seller/order/finish?orderId=${orderMasterDto.orderId}" type="button" class="btn btn-primary btn-default">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderMasterDto.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                </div>
            </#if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>