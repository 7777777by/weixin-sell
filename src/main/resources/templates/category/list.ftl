<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th>类目id</th>
                                <th>名称</th>
                                <th>类别</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                        <#list productCategoryPage.content as productCategoryDto>
                        <tr>
                            <td>${productCategoryDto.categoryId}</td>
                            <td>${productCategoryDto.categoryName}</td>
                            <td>${productCategoryDto.categoryType}</td>
                            <td>${productCategoryDto.createTime}</td>
                            <td>${productCategoryDto.updateTime}</td>
                            <td>
                                <a href="/sell/seller/category/index?categoryId=${productCategoryDto.categoryId}">修改</a>
                            </td>
                            <#--<td>
                                    <#if orderMasterDto.getOrderStatusEnum().msg == "新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderMasterDto.orderId}">取消</a>
                                    </#if>
                            </td>-->
                        </tr>
                        </#list>
                            </tbody>
                        </table>
                    </div>
                    <!-- 分页 -->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled">
                            <a href="#">上一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/category/list?page=${currentPage - 1}&size=${size}">上一页</a>
                        </li>
                    </#if>
                    <#list 1..productCategoryPage.totalPages as index>
                        <#if currentPage == index>
                            <li class="disabled">
                                <a href="#">${index}</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/category/list?page=${index}&size=${size}">${index}</a>
                            </li>
                        </#if>
                    </#list>
                    <#if currentPage gte productCategoryPage.totalPages>
                        <li class="disabled">
                            <a href="#">下一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/category/list?page=${currentPage + 1}&size=${size}">下一页</a>
                        </li>
                    </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
</div>
</body>
</html>