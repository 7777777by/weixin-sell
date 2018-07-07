<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <!-- 正文内容content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" action="/sell/seller/product/save" method="post">
                        <div hidden class="form-group">
                            <label>商品id</label>
                            <input name="productId" type="text" class="form-control" id="productId" value="${(productInfo.productId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control" id="productName" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control" id="productPrice" value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStore" type="number" class="form-control" id="productStore" value="${(productInfo.productStore)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control" id="productDescription" value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img width="100" height="100" src="${(productInfo.productIcon)!''}">
                            <input name="productIcon" type="text" class="form-control" id="productIcon" value="${(productInfo.productIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list productCategoryList as productCategory>
                                    <option value="${productCategory.categoryType}"
                                        <#if (productInfo.categoryType)?? && productCategory.categoryType == productInfo.categoryType> selected</#if>>
                                            ${productCategory.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <#--<div class="form-group">
                            <label for="exampleInputPassword1">Password</label><input type="password" class="form-control" id="exampleInputPassword1" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputFile">File input</label><input type="file" id="exampleInputFile" />
                            <p class="help-block">
                                Example block-level help text here.
                            </p>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" />Check me out</label>
                        </div> -->
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>